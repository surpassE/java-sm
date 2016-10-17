package com.sirding.core.utils.reqproxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.sirding.core.utils.FileContentUtil;
import com.sirding.core.utils.NotNullUtil;
/**
 * Ftp测试工具类
 * 
 * 1、ftpTest 执行FTP连接测试操作
 * 2、initFtp 建立FTP连接
 * 3、destroyFtp 销毁FTP连接
 * 4、uploadFile 上传指定路径下的文件
 * 5、downSingleFileAssignedFtpServerPath 从FTP Server中指定的路径下下载与filepath同名的文件
 * 6、downSingleFile 从FTP Server中找到第一个与filePath同名的文件将其下载到本地
 * 7、downAssignedFtpServerPath 将FTP Server中指定路径下的所有文件及目录结果下载到filePath中
 * 8、downFile 从FTP Server 中下载所有的文件及目录结构到filePath中
 * 
 * @author surpassE
 * @version 1.0.0
 * @since 2015-04-14
 * 
 */
public class FtpUtil {
	
	/**
	 * 执行ftp测试操作
	 * @param ip
	 * @param port
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public static String ftpTest(String ip, String port, String userName, String pwd){
		FTPClient ftp = new FTPClient();
		String msg = "连接成功!";
		try {
			//设置连接超时时间
			ftp.setConnectTimeout(1500);
			//绑定ip和port
			ftp.connect(InetAddress.getByName(ip), Integer.parseInt(port));
			Boolean flag = ftp.login(userName, pwd);
			if(flag){
				int reply = ftp.getReplyCode();  
		        if (!FTPReply.isPositiveCompletion(reply)) {  
		            msg = "连接成功!"; 
		        }
			}else{
				msg = "用户名或是密码错误!";
			}
		} catch (Exception e) {
			msg = "连接异常，请检查网络或服务!";
		}finally{
			destroyFtp(ftp);
		}
		return msg;
	}
	
	/**
	 * 建立FTP连接
	 * 
	 * @param ip	FTP Server的ip地址
	 * @param port	FTP服务器端口 
	 * @param userName	FTP登录账号
	 * @param pwd	 FTP登录密码 
	 * @return
	 */
	public static FTPClient initFtp(String ip, String port, String userName, String pwd){
		FTPClient ftp = new FTPClient();
		try {
			ftp.setConnectTimeout(3000);
			//绑定ip和port
			ftp.connect(InetAddress.getByName(ip), Integer.parseInt(port));
			ftp.login(userName, pwd);
			int reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();
	            return null;  
	        }
		} catch (Exception e) {
			destroyFtp(ftp);
			e.printStackTrace();
			ftp = null;
		}
		return ftp;
	}
	
	/**
	 * 销毁FTP连接
	 * @param ftp
	 */
	public static void destroyFtp(FTPClient ftp){
		if(ftp != null && ftp.isConnected()){
			try {
				ftp.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/** 
	 * 向FTP服务器上传文件(支持多级目录)
	 *  
	 * @Version1.0.0
	 * @param filePath 要上传到ftp上的文件路径或是包含文件夹或是多个文件的路径
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(FTPClient ftp, String filePath) {
	    boolean success = false;  
	    if(ftp == null){
	    	return success;
	    }
	    try {  
	        recursionUpload(ftp, filePath, filePath);
	        ftp.logout();  
	        success = true;  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	    	destroyFtp(ftp);
	    }  
	    return success;  
	}
	
	/**
	 * 递归上传filePath下所有文件及子文件夹及其文件夹中文件
	 * 
	 * eg 
	 * rootPath： G:/aa filePath G:/aa 其中aa下 aa/bb/bb.txt aa/cc.txt aa/dd/dd.txt
	 * 那么在ftp server中将创建aa/bb aa/dd文件夹、同时将指定文件放到对应的文件夹中
	 * 
	 * @param ftp
	 * @param rootPath 文件路径的跟文件，初始时与filePath相同
	 * @param filePath 指定文件或是文件路径
	 * @throws Exception
	 */
	private static void recursionUpload(FTPClient ftp, String rootPath, String filePath) throws Exception{
		File file = new File(filePath);
        if(file.isFile()){
        	FileInputStream fis = new FileInputStream(file);
            ftp.storeFile(file.getName(), fis);
            fis.close();
        }else{
        	File[] arr = file.listFiles();
        	if(NotNullUtil.objectArrayNotNull(arr)){
        		for(File fileTmp : arr){
            		//兼容linux路径，需要将\\转为/
            		String workPath = fileTmp.getAbsolutePath().replaceAll("\\\\", "/").replaceAll(rootPath, "");
            		if(fileTmp.isDirectory()){
                		if(NotNullUtil.stringNotNull(workPath)){
                			//在FTP Server上创建文件夹
                			ftp.makeDirectory(workPath);
                			//将新创建的文件夹作为工作路径
                			ftp.changeWorkingDirectory(workPath);
                		}
            		}
            		recursionUpload(ftp, rootPath, fileTmp.getAbsolutePath());
            		//返回上一级路径
            		if(fileTmp.isDirectory() && NotNullUtil.stringNotNull(workPath)){
            			ftp.changeToParentDirectory();
            		}
            	}
        	}
        }
	}
	
	/**
	 * 将FTP Server上指定路径下的与filePath同文件名称的文件下载到本地
	 * 
	 * @param ftp
	 * @param filePath
	 * @param serverPath
	 * @return
	 */
	public static boolean downSingleFileAssignedFtpServerPath(FTPClient ftp, String filePath, String serverPath){
		try {
			ftp.changeWorkingDirectory(serverPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downSingleFile(ftp, filePath);
	}
	
	/**
	 * 下载FTP Server上与filePath同文件名称的文件
	 * 
	 * @param ftp
	 * @param filePath
	 * @return
	 */
	public static boolean downSingleFile(FTPClient ftp, String filePath){
		boolean success = false;
	    if(ftp == null){
	    	return success;
	    }
	    try {
	    	recursionDownloadAssignedFile(ftp, filePath, filePath);
	        ftp.logout();  
	        success = true;  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	    	destroyFtp(ftp); 
	    }  
	    return success; 
	}
	
	/**
	 * 下载FTP Server上指定文件路径下的所有目录结构到本地指定的路径下
	 * 
	 * @param ftp
	 * @param filePath
	 * @param serverPath FTP Server上存在的路径
	 * @return
	 */
	public static boolean downAssignedFtpServerPath(FTPClient ftp, String filePath, String serverPath){
		try {
			ftp.changeWorkingDirectory(serverPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downFile(ftp, filePath);
	}
	
	/**
	 * 在FTP Server上查找与filePath同文件名称的文件、并下载到本地
	 * 
	 * @param ftp
	 * @param rootPath
	 * @param filePath	指定文件名称的路径
	 * @return
	 * @throws Exception
	 */
	private static boolean recursionDownloadAssignedFile(FTPClient ftp, String rootPath, String filePath) throws Exception {
		boolean flag = false;
		FTPFile[] ftpFiles = ftp.listFiles();
		if(NotNullUtil.objectArrayNotNull(ftpFiles)){
			for(FTPFile ftpFile : ftpFiles){
				String name = ftpFile.getName();
				File file = new File(filePath);
				if(ftpFile.isDirectory()){
					ftp.changeWorkingDirectory(ftpFile.getName());
					flag = recursionDownloadAssignedFile(ftp, rootPath, file.getAbsolutePath());
					ftp.changeToParentDirectory();
					if(flag){
						return true;
					}
				}else{
					if(ftpFile.getName().equals(file.getName())){
						FileOutputStream fos = new FileOutputStream(file);
		            	ftp.retrieveFile(name, fos);  
		                fos.close();
		                flag = true;
		                break;
					}
				}
			}
		}
		return flag;
	}
	
	/** 
	 * 从FTP服务器下载根路径下所有文件(支持路径下结构下载)
	 * 
	 * @author surpassE 
	 * @Version1.0.0
	 * @param filePath 将目标上的文件下载到本地
	 * @return 
	 */  
	public static boolean downFile(FTPClient ftp, String filePath) {  
	    boolean success = false;
	    if(ftp == null){
	    	return success;
	    }
	    try {
	    	//创建本地路径
	    	FileContentUtil.createPath(filePath);
	    	recursionDownload(ftp, filePath, filePath);
	        ftp.logout();  
	        success = true;  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	    	destroyFtp(ftp); 
	    }  
	    return success;  
	}
	
	
	/**
	 * 递归将FTP Server中所有的目录结构及文件下载到本地
	 * @param ftp
	 * @param rootPath
	 * @param filePath
	 * @throws Exception
	 */
	private static void recursionDownload(FTPClient ftp, String rootPath, String filePath) throws Exception{
		FTPFile[] ftpFiles = ftp.listFiles();
		if(NotNullUtil.objectArrayNotNull(ftpFiles)){
			for(FTPFile ftpFile : ftpFiles){
				String name = ftpFile.getName();
				File file = new File(filePath + "/" + name);
				if(ftpFile.isDirectory()){
					file.mkdir();
					ftp.changeWorkingDirectory(ftpFile.getName());
					recursionDownload(ftp, rootPath, file.getAbsolutePath());
					ftp.changeToParentDirectory();
				}else{
					FileOutputStream fos = new FileOutputStream(file);
	            	ftp.retrieveFile(name, fos);  
	                fos.close();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String msg = FtpUtil.ftpTest("192.168.8.159", "21", "user-report", "123456");
		System.out.println(msg);
//		String filePath = "G:/beta/report/ftp-test.txt";
//		String filePath = "G:/beta/report";
//		String dfilePath = "G:/beta/reportSuccess/ftp-test.txt";
//		String dfilePath = "G:/beta/reportSuccess/";
//		FTPClient ftp = FtpUtil.initFtp("192.168.8.159", "21", "user-report", "123456");
//		FtpUtil.uploadFile(ftp, filePath);
//		FtpUtil.downFile(ftp, dfilePath);
//		FtpUtil.downAssignedFtpServerPath(ftp, dfilePath, "aa/cc");
//		FtpUtil.downSingleFile(ftp, dfilePath + "bb.txt");
//		FtpUtil.downSingleFileAssignedFtpServerPath(ftp, dfilePath + "dd.txt", "aa/cc");
		System.out.println("success..........");
	}
}

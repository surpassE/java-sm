package com.sirding.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;

/**
 * java执行linux命令行工具类
 * @author surpassE
 */
public class LinuxCmdUtil {
	private static boolean OS_IS_LINUX = true;

	static{
		String os_name = System.getProperty("os.name");
		if(os_name.startsWith("W") || os_name.startsWith("w")){
			OS_IS_LINUX = false;
		}
	}
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LinuxCmdUtil.class);
	
	/**
	 * 执行命令不返回任何参数
	 * @param cmd
	 */
	public static void execCmdsFinal(Object cmd){
		try{
			if(cmd instanceof String){
				Runtime.getRuntime().exec((String)cmd);
			}else if(cmd instanceof String[]){
				Runtime.getRuntime().exec((String[])cmd);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行cmd命令，不抛出异常
	 * @param cmd
	 * @return
	 */
	public static BufferedReader execCmdFinal(Object cmd){
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			try {
				if( (is = execCmdReturnInputStream(cmd)) != null){
					br = new BufferedReader(new InputStreamReader(is));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return br;
	}
	
	/**
	 * 在操作系统是linux的条件执行cmd命令，将命令的执行结果封装到List<String>集合中
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static List<String> execCmd(Object cmd) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}
		return getCmdResult(br);
	}
	
	/**
	 * linux下执行cmd命令，如果是windows那么从指定的文件路径中读取配置信息
	 * @param cmd
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> execCmd(Object cmd, String filePath) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}else{
			if(filePath != null){
				br = new BufferedReader(new FileReader(filePath));
			}
		}
		return getCmdResult(br);
	}
	
	
	/**
	 * linux下执行cmd命令，如果是windows那么从指定的文件路径中读取配置信息
	 * @param cmd
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> execCmdOrReadFile(Object cmd) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}else{
			if(cmd instanceof String[]){
				String[] tmp = (String[])cmd;
				br = new BufferedReader(new FileReader(tmp[2]));
			}else{
				br = new BufferedReader(new FileReader((String)cmd));
			}
		}
		return getCmdResult(br);
	}
	
	
	
	/**
	 * linux下执行cmd命令，如果是windows那么将content添加到返回的集合中
	 * @param cmd
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> execCmdContent(Object cmd, String... content) throws Exception{
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}
		return getCmdResult(br, content);
	}
	
	/**
	 * 在操作系统是linux的条件执行cmd命令,返回命令执行的结果流对象
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static BufferedReader execCmdOverride(Object cmd) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}
		return br;
	}
	
	/**
	 * linux执行linux，window执行winCmd命令
	 * @param linuxCmd
	 * @param winCmd
	 * @return
	 * @throws Exception
	 */
	public static BufferedReader mustExec(Object linuxCmd, Object winCmd) throws Exception{
		BufferedReader br = null;
		Object cmd = "";
		if(OS_IS_LINUX){
			cmd = linuxCmd;
		}else{
			cmd = winCmd;
		}
		InputStream is = null;
		
		InputStream err = null;
		Process proc = null;
		if(cmd instanceof String){
			proc = Runtime.getRuntime().exec((String)cmd);
		}else if(cmd instanceof String[]){
			proc = Runtime.getRuntime().exec((String[])cmd);
		}
		//判断是否有命令执行的错误提示
		err = proc.getErrorStream();
		//如果没有错误提示信息，表示命令执行成功
		if (err != null && err.read() < 0){
			is = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		}else{
			br = new BufferedReader(new InputStreamReader(err));
		}
		return br;
	}
	
	/**
	 * 在操作系统是linux的条件执行cmd命令,如果是windows操作系统，那么返回指定文件的操作流
	 * @param cmd
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static BufferedReader execCmdOverride(Object cmd, String filePath) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd)) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}else{
			if(filePath != null){
				br = new BufferedReader(new FileReader(filePath));
			}
		}
		return br;
	}
	
	/**
	 * 在操作系统是linux的条件执行cmd命令,如果是windows操作系统，那么返回指定文件的操作流
	 * 如果执行命令发生异常、那么将异常信息打印到控制台
	 * 
	 * @param cmd
	 * @param params params[0]=1时，如果cmd执行出现异常，那么将返回异常结果的流，为null将异常结果输出到控制台
	 * @return
	 * @throws Exception
	 */
	public static InputStream execCmdReturnInputStream(Object cmd, String... params) throws Exception{
		showMsg(cmd);
		InputStream is = null;
		InputStream err = null;
		Process proc = null;
		if(OS_IS_LINUX){
			if(cmd instanceof String){
				proc = Runtime.getRuntime().exec((String)cmd);
			}else if(cmd instanceof String[]){
				proc = Runtime.getRuntime().exec((String[])cmd);
			}
			//判断是否有命令执行的错误提示
			err = proc.getErrorStream();
			//如果没有错误提示信息，表示命令执行成功
			if (err != null && err.read() < 0){
				is = proc.getInputStream();
			}else{
				if(NotNullUtil.objectArrayNotNull(params)){
					if("1".equals(params[0])){
						return err;
					}
				}else{
					printCmdErrorMsg(err);
				}
			}
		}
		return is;
	}

	/**
	 * 将cmd命令执行的错误信息打印到控制台中
	 * @param br
	 * @throws Exception
	 */
	protected static void printCmdErrorMsg(BufferedReader br) throws Exception{
		String line = null;
		while((line = br.readLine()) != null){
			//这里通过log4j打印信息
			logger.error(line);
		}
		line = null;
	}
	
	/**
	 * 将cmd命令执行的错误信息打印到控制台中
	 * @param br
	 * @throws Exception
	 */
	private static void printCmdErrorMsg(InputStream is) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while((line = br.readLine()) != null){
			//这里通过log4j打印信息
			logger.error(line);
		}
		line = null;
	}
	
	/**
	 * 获得linux执行cmd命令的返回结果
	 * @param br
	 * @return
	 * @throws Exception
	 */
	private static List<String> getCmdResult(BufferedReader br) throws Exception{
		List<String> list = new ArrayList<String>();
		try{
			if(br != null){
				String line = null;
				while((line = br.readLine()) != null){
					list.add(line);
				}
				line = null;
				br.close();
			}
		}catch (Exception e) {
			br.close();
			throw e;
		}
		return list;
	}
	
	/**
	 * 获得linux执行cmd命令的返回结果,如果没有返回结果，那么将content的内容添加到list集合中
	 * @param br
	 * @return
	 * @throws Exception
	 */
	private static List<String> getCmdResult(BufferedReader br, String... content) throws Exception{
		List<String> list = new ArrayList<String>();
		if(br != null){
			String line = null;
			while((line = br.readLine()) != null){
				list.add(line);
			}
			line = null;
		}else{
			if(NotNullUtil.objectArrayNotNull(content)){
				for(String tmp : content){
					list.add(tmp);
				}
			}
		}
		return list;
	}
	
	/**
	 * 通过返回值判断cmd命令执行是否成功
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static int execCmdStatus(String cmd) throws Exception{
		showMsg(cmd);
		int flag = 1;
		if(OS_IS_LINUX){
			flag = Runtime.getRuntime().exec(cmd).waitFor();
		}
		showMsg(flag);
		return flag;
	}
	
	/**
	 * 在操作系统是linux的条件执行cmd命令，将命令的执行结果封装到List<String>集合中
	 * 如果cmd执行异常，那么将异常结果封装到list中
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static List<String> execCmdForReport(Object cmd) throws Exception{
		showMsg(cmd);
		BufferedReader br = null;
		if(OS_IS_LINUX){
			InputStream is = null;
			if( (is = execCmdReturnInputStream(cmd, "1")) != null){
				br = new BufferedReader(new InputStreamReader(is));
			}
		}
		return getCmdResult(br);
	}
	
	public static void showMsg(Object cmd){
		logger.debug("执行的cmd命令 ：" + JSON.toJSONString(cmd));
	}
	
}

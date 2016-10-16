package com.sirding.utils.reqproxy;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import org.apache.commons.net.telnet.TelnetClient;

/**
 * telnet操作,输入命令,收集命令返回的结果信息
 * @author surpassE
 *
 */
public class TelnetUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TelnetUtil.class);

	private TelnetClient client = new TelnetClient();
	private StringBuffer result = new StringBuffer();
	private boolean SHOW_CONSOLE_DISPLAY = true;

	/**
	 * 初始化telnet连接操作
	 * @param ip
	 * @param port
	 * @param timeOut 连接超时时间
	 * @return
	 * @throws Exception
	 */
	public void initTelnetClient(String ip, String port, int... timeOut) throws Exception{
		logger.debug("telnet地址:" + ip + "\t" + "端口：" + port);
		if(timeOut != null && timeOut.length > 0){
			client.setConnectTimeout(1000 * timeOut[0]);
		}
		client.connect(InetAddress.getByName(ip),Integer.valueOf(port));
		client.setSoTimeout(1000*10);
	}
	
	/**
	 * 执行命令并且读取反馈信息
	 * @param command
	 * @param prmpt
	 * @throws Exception
	 */
	public void sendCommand(String command, String prmpt) throws Exception {
		logger.debug("执行telnet命令 :" + command);
		/*
		if(!"show interface".equals(command) && "out>".equals(prmpt)){
			readConsole(this.client.getInputStream(),"Password:");
		}
		*/
		this.client.getOutputStream().write((command + "\r").getBytes());
		this.client.getOutputStream().flush();
		/*
		if(null != prmpt && !"".equals(prmpt)){
			readConsole(this.client.getInputStream(),prmpt);
		}
		*/
		Thread.sleep(Integer.valueOf(50));
	}
	
	/**
	 * 读取命令执行的结果
	 * @param in
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void readConsole(InputStream in,String prmpt) throws IOException {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		String line = null;
		while((line = br.readLine()) != null && !line.endsWith(prmpt)){
			if(this.SHOW_CONSOLE_DISPLAY){
				System.out.println(line);
			}
		}
		*/
		StringBuffer sb = new StringBuffer();
		int c;
		while((in.available()) != 0 && (c = in.read()) != -1){
			if(this.SHOW_CONSOLE_DISPLAY){
				sb.append((char)c);
			}
		}
		logger.debug("执行单行命令的返回结果 ：" + sb.toString());
	}
	
	/**
	 * 获得执行命令的反馈信息
	 * @throws IOException
	 */
	public String getCommandReault() throws Exception {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		String line = null;
		while((line = br.readLine()) != null){
			this.result.append(line).append("\n");
		}*/
		Thread.sleep(100);
		InputStream is = this.client.getInputStream();
		int c;
		while((is.available()) != 0 && (c = is.read()) != -1){
			this.result.append((char)c);
		}
		logger.debug("命令执行的返回结果：" + result.toString());
		return result.toString();
	}
	
	/**
	 * 关闭client的连接
	 */
	public void  closeConnection(){
		if (null != this.client){
//			if (null != this.client && this.client.isConnected()){
			try {
				this.client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.client = null;
	}
	
	/**
	 * 判断telnet连接是不是正常的
	 * @return
	 */
	public boolean isConnect(){
		return this.client.isConnected();
	}
	
	
	/**
	 * 获得异常的原因
	 * @param e
	 * @return
	 */
	public static String getExceptionCode(Exception e){
		String err_code = "系统异常!";
		if(e instanceof SocketTimeoutException){
			err_code = "连接超时!";
		}else if(e instanceof ConnectException){
			err_code = "网络异常!";
		}
		e.printStackTrace();
		return err_code;
	}
	
}

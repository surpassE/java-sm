package com.sirding.utils.reqproxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author lion
 * 
 */
public class SocketUtil {
	
	/**
	 * 向指定的ip端口发送数据信息
	 * @param ipAddress
	 * @param port
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String sendMessage(String ipAddress, int port, String message) throws Exception {
		Socket socket = null;
		String msg = "";
		try {
			socket = new Socket(ipAddress, port);
			boolean flag = sendMessage(message, socket);
			if(flag){
				msg = readMessage(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return msg;
	}
	
	
	public static Socket initSocket(String ipAddress, int port) throws Exception {
		Socket socket = null;
		try {
			socket = new Socket(ipAddress, port);
			return socket;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public static void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean sendMessage(String message, Socket socket) throws Exception {
		boolean succ = false;
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(message);
			succ = true;
		} catch (Exception e) {
			e.printStackTrace();
			succ = false;
			throw new Exception(e);
		} finally {
			try {
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return succ;

	}

	private static String getMessage(Socket socket) throws Exception {
		String s = "";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (in.ready()) {
				s = s + in.readLine() + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return s;
	}


	public static String readMessage(Socket socket) throws Exception{
		String s="";
		try {
			for(int i=0;i<3;i++){
				Thread.sleep(100);
				s=SocketUtil.getMessage(socket);
				if(s==null||s.equals("")){
					continue;
				}else{
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
}

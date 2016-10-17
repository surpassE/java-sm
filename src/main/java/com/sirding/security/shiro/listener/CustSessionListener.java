package com.sirding.security.shiro.listener;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;

/**
 * 监听session有两种方式
 * 	1、实现org.apache.shiro.session.SessionListener接口
 *  2、继承org.apache.shiro.session.SessionListenerAdapter,重写父类中的方法
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class CustSessionListener implements org.apache.shiro.session.SessionListener{

	private static Logger logger = Logger.getLogger(CustSessionListener.class);
	@Override
	public void onStart(Session session) {
		session.setAttribute("sirding", "hello world");
		logger.debug("初始化session...");
	}

	@Override
	public void onStop(Session session) {
		logger.debug("停止session...");
	}

	@Override
	public void onExpiration(Session session) {
		logger.debug("超时session...");
	}

}

package com.sirding.testutil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.sirding.Base;
import com.sirding.core.utils.ACUtils;
import com.sirding.core.utils.ActiveMqUtil;

public class TestMq {
	
	JmsTemplate jmsTemplate;
	
	/**
	 * @Described			: 基于jmsTemplate发送消息
	 * @author				: zc.ding
	 * @date 				: 2017年1月22日
	 */
	@Test
	public void test0(){
		try {
			JmsTemplate jmsTemplate = ACUtils.getBean("jmsTemplate", JmsTemplate.class);
			jmsTemplate.send("jmsTemlate", new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                return session.createTextMessage("hello queue world");
	            }
	        });
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Test
	public void test(){
		ActiveMqUtil.sendMessage("myQueue", "hello world");
//		ActiveMqUtil.sendMessage("queue1", "bbbbb");
//		ActiveMqUtil.sendMessage("queue2", "ccccc");
//		ActiveMqUtil.sendMessage("queue2", "ddddd");
		System.out.println("okok......");
	}
	
	@Test
	public void test1(){
		String msg = ActiveMqUtil.getMessage("YRTZ-TEST");
		System.out.println(msg);
		System.out.println("okok......");
	}
}

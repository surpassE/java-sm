package com.sirding.core.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @Described	: activeMq工具类
 * @project		: com.sirding.core.utils.ActiveMqUtil
 * @author 		: zc.ding
 * @date 		: 2017年1月17日
 */
public class ActiveMqUtil {
	static ConnectionFactory connectionFactory;
	static Connection connection = null;
	static Session session;
	static Map<String, MessageProducer> sendQueues = new ConcurrentHashMap<String, MessageProducer>();
	static Map<String, MessageConsumer> getQueues = new ConcurrentHashMap<String, MessageConsumer>();
	
	static Logger log = Logger.getLogger(ActiveMqUtil.class);

	static {
//		connectionFactory = new ActiveMQConnectionFactory(
//				ActiveMQConnection.DEFAULT_USER, 
//				ActiveMQConnection.DEFAULT_PASSWORD, 
//				"tcp://127.0.0.1:61616?wireFormat.maxInactivityDuration=0");
		
		connectionFactory = new ActiveMQConnectionFactory(
				"develop", 
				"develop", 
				"tcp://127.0.0.1:61616?wireFormat.maxInactivityDuration=0");
		try{
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.FALSE.booleanValue(), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Described			: 获得消息队列的生产者
	 * @author				: zc.ding
	 * @date 				: 2017年1月19日
	 * @param name
	 * @return
	 */
	static MessageProducer getMessageProducer(String name) {
		if (sendQueues.containsKey(name))
			return ((MessageProducer)sendQueues.get(name));
		try{
			Destination destination = session.createQueue(name);
			MessageProducer producer = session.createProducer(destination);
			sendQueues.put(name, producer);
			return producer;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return ((MessageProducer)sendQueues.get(name));
	}

	/**
	 * @Described			: 获得消息队列的消费者
	 * @author				: zc.ding
	 * @date 				: 2017年1月19日
	 * @param name
	 * @return
	 */
	static MessageConsumer getMessageConsumer(String name) {
		if (getQueues.containsKey(name))
			return ((MessageConsumer)getQueues.get(name));
		try{
			Destination destination = session.createQueue(name);
			MessageConsumer consumer = session.createConsumer(destination);
			getQueues.put(name, consumer);
			return consumer;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return ((MessageConsumer)getQueues.get(name));
	}

	/**
	 * @Described			: 向消息队列中添加消息
	 * @author				: zc.ding
	 * @date 				: 2017年1月19日
	 * @param queue
	 * @param text
	 */
	public static boolean sendMessage(String queue, String text) {
		try {
			TextMessage message = session.createTextMessage(text);
			getMessageProducer(queue).send(message);
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Described			: 处理消息队列中的消息
	 * @author				: zc.ding
	 * @date 				: 2017年1月19日
	 * @param queue
	 * @return
	 */
	public static String getMessage(String queue) {
		try {
			TextMessage message = (TextMessage)getMessageConsumer(queue).receive(10000L);
			if (message != null) 
				return message.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Described			: 监听queue或是topic
	 * @author				: zc.ding
	 * @date 				: 2017年1月22日
	 * @param name
	 * @param isQueue
	 */
	public static void listen(String name, boolean isQueue){
		try {
			DefaultMessageListenerContainer listen = new DefaultMessageListenerContainer();
			listen.setConnectionFactory(connectionFactory);
			Destination destination = null;
			//true:监听topic消息；false:监听queue消息
			listen.setPubSubDomain(!isQueue);
			if(isQueue){
				destination = session.createQueue(name);
			}else{
				destination = session.createTopic(name);
			}
			listen.setDestination(destination);
			//true:不允许统一连接既发送又接收消息；false：允许同一连接既发送又接收消息
			listen.setPubSubNoLocal(false);
			listen.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {
						TextMessage tm = (TextMessage)message;
						System.out.println(tm.getText());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			listen.setSessionTransacted(false);
			listen.setConcurrentConsumers(10);
			listen.setMaxConcurrentConsumers(15);
			listen.setConcurrency("1-9");
			listen.initialize();
			listen.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * @Described			: 管理mq连接
	 * @author				: zc.ding
	 * @date 				: 2017年1月19日
	 */
	public static void close() {
		try {
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}

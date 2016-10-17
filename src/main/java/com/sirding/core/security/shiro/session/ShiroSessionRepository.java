package com.sirding.core.security.shiro.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

import com.sirding.core.redis.JedisManager;
import com.sirding.core.utils.LoggerUtils;
import com.sirding.core.utils.SerializeUtil;

/**
 * session的CURD操作
 * @author zc.ding
 * @date 2016年10月17日
 *
 */
public class ShiroSessionRepository {

	public static final String REDIS_SHIRO_SESSION_PREFFIX = "shiro-session:";
	//这里有个小BUG，因为Redis使用序列化后，Key反序列化回来发现前面有一段乱码，解决的办法是存储缓存不序列化
//	public static final String REDIS_SHIRO_ALL = "*sojson-shiro-demo-session:*";
	private static final int SESSION_VAL_TIME_SPAN = 18000;
	private static final int DB_INDEX = 1;

	private JedisManager jedisManager;

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	/**
	 * <p>
	 * 存储session状态
	 * <p>
	 * @param session
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	public void saveSession(Session session) {
		if (session == null || session.getId() == null)
			throw new NullPointerException("session is empty");
		try {
			byte[] key = getKey(session.getId());
			//判断session中是否用户的状态信息
			if(null == session.getAttribute(CustomSessionManager.SESSION_STATUS)){
				SessionStatus sessionStatus = new SessionStatus();
				session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
			}
			byte[] value = SerializeUtil.serialize(session);
			long sessionTimeOut = session.getTimeout() / 1000;
			Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
			//将session信息序列化后存入到redis缓存中
			getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]",session.getId());
		}
	}

	/**
	 * <p>
	 *  清空session中的缓存
	 * <p>
	 * @param id
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	public void deleteSession(Serializable id) {
		if (id == null) {
			throw new NullPointerException("session id is empty");
		}
		try {
			getJedisManager().deleteByKey(DB_INDEX, getKey(id));
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]",id);
		}
	}

	/**
	 * <p>
	 * 通过id从缓存中获得对应的session信息
	 * <p>
	 * @param id
	 * @return
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	public Session getSession(Serializable id) {
		if (id == null)
			throw new NullPointerException("session id is empty");
		Session session = null;
		try {
			byte[] value = getJedisManager().getValueByKey(DB_INDEX, getKey(id));
			session = SerializeUtil.deserialize(value, Session.class);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "获取session异常，id:[%s]",id);
		}
		return session;
	}

	/**
	 * <p>
	 * 从缓存中获得所有的session数据信息
	 * <p>
	 * @return
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	public Collection<Session> getAllSessions() {
		Collection<Session> sessions = null;
		try {
			sessions = getJedisManager().AllSession(DB_INDEX, REDIS_SHIRO_SESSION_PREFFIX);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "获取全部session异常");
		}
		return sessions;
	}
	
	/**
	 * <p>
	 * 获得key
	 * <p>
	 * @param sessionId
	 * @return
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	private byte[] getKey(Serializable sessionId){
		return SerializeUtil.serialize(buildRedisSessionKey(sessionId));
	}

	/**
	 * <p>
	 * 	创建用于存储session的key
	 * <p>
	 * @param sessionId
	 * @return
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
	private String buildRedisSessionKey(Serializable sessionId) {
		return REDIS_SHIRO_SESSION_PREFFIX + sessionId;
	}
}

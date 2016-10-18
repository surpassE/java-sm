package com.sirding.core.security.shiro.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.core.utils.LoggerUtils;
import com.sirding.domain.CustSession;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUserInfo;

/**
 * 用户session管理
 * @author zc.ding
 * @date 2016年10月18日
 */

public class CustSessionManager {

	/**
	 * session status 
	 */
	public static final String SESSION_STATUS ="sojson-online-status";

	CustShiroSessionDAO custShiroSessionDAO;

	/**
	 * 获取所有的有效Session用户
	 * @return
	 */
	public List<CustSession> getAllUser() {
		//获取所有session
		Collection<Session> sessions = custShiroSessionDAO.getActiveSessions();
		List<CustSession> list = new ArrayList<CustSession>();
		for (Session session : sessions) {
			CustSession cs = getCustSession(session);
			if(null != cs){
				list.add(cs);
			}
		}
		return list;
	}
	/**
	 * 根据ID查询 SimplePrincipalCollection集合
	 * @param userIds	用户ID
	 * @return
	 */
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){
		List<Long> idList = Arrays.asList(userIds);
		//获取所有session
		Collection<Session> sessions = custShiroSessionDAO.getActiveSessions();
		//定义返回
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			SimplePrincipalCollection spc = this.getSimplePrincipalCollection(session);
			Object obj = this.getSessionUser(spc);
			//判断用户，匹配用户ID。
			obj = spc.getPrimaryPrincipal();
			if(null != obj){
				try {
					Long id = Long.parseLong(BeanUtils.getProperty(obj, "id"));
					//比较用户ID，符合即加入集合
					if(idList.contains(id)){
						list.add(spc);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 获取单个Session
	 * @param sessionId
	 * @return
	 */
	public CustSession getSession(String sessionId) {
		Session session = custShiroSessionDAO.getSession(sessionId);
		CustSession cs = getCustSession(session);
		return cs;
	}

	/**
	 * 获得自定义session
	 * @date 2016年10月18日
	 * @author zc.ding
	 * @param session
	 * @return
	 */
	private CustSession getCustSession(Session session){
		Object obj = getSessionUser(getSimplePrincipalCollection(session));
		if(null != obj){
			CustSession custSession = null;
			if(obj instanceof AppUserInfo){
				custSession = new CustSession((AppUserInfo)obj);
			}else if(obj instanceof AppSysUser){
				custSession = new CustSession((AppSysUser)obj);
			}
			try {
				BeanUtils.copyProperties(custSession, session);
			} catch (Exception e) {
				LoggerUtils.error(getClass(), "将session数据进行转存操作失败!");
				e.printStackTrace();
			}
			//是否踢出
			SessionStatus sessionStatus = (SessionStatus)session.getAttribute(SESSION_STATUS);
			boolean status = Boolean.TRUE;
			if(null != sessionStatus){
				status = sessionStatus.getOnlineStatus();
			}
			custSession.setSessionStatus(status);
			return custSession;
		}
		return null;
	}

	/**
	 * 
	 * @date 2016年10月18日
	 * @author zc.ding
	 * @param session
	 * @return
	 */
	private SimplePrincipalCollection getSimplePrincipalCollection(Session session){
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if(null != obj && obj instanceof SimplePrincipalCollection){
			//强转
			SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
			return spc;
		}
		return null;
	}

	/**
	 * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中
	 * return new SimpleAuthenticationInfo(user,user.getPswd(), getName());的user 对象。
	 * @date 2016年10月18日
	 * @author zc.ding
	 * @param spc
	 * @return
	 */
	private Object getSessionUser(SimplePrincipalCollection spc){
		if(spc != null){
			return spc.getPrimaryPrincipal();
		}
		return null;
	}

	/**
	 * 改变Session状态
	 * @param status {true:踢出,false:激活}
	 * @param sessionId
	 * @return
	 */
	public Map<String, Object> changeSessionStatus(Boolean status, String sessionIds) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] sessionIdArray = sessionIds.split(",");
			for (String id : sessionIdArray) {
				Session session = custShiroSessionDAO.getSession(id);
				SessionStatus sessionStatus = new SessionStatus();
				sessionStatus.setOnlineStatus(status);
				session.setAttribute(SESSION_STATUS, sessionStatus);
				custShiroSessionDAO.update(session);
			}
			map.put("status", 200);
			map.put("sessionStatus", status?1:0);
			map.put("sessionStatusText", status?"踢出":"激活");
			map.put("sessionStatusTextTd", status?"有效":"已踢出");
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "改变Session状态错误，sessionId[%s]", sessionIds);
			map.put("status", 500);
			map.put("message", "改变失败，有可能Session不存在，请刷新再试！");
		}
		return map;
	}
	/**
	 * 查询要禁用的用户是否在线。
	 * @param id		用户ID
	 * @param status	用户状态
	 */
	public void forbidUserById(Long id, Long status) {
		//获取所有在线用户
		for(CustSession custSession : getAllUser()){
			Long userId = custSession.getUserId();
			//匹配用户ID
			if(userId.equals(id)){
				//获取用户Session
				Session session = custShiroSessionDAO.getSession(custSession.getId());
				//标记用户Session
				SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
				//是否踢出 true:有效，false：踢出。
				sessionStatus.setOnlineStatus(status.intValue() == 1);
				//更新Session
				custShiroSessionDAO.update(session);
			}
		}
	}
}

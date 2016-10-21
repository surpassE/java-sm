package com.sirding.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;


/**
 * SecurityUtils相关操作扩展
 * @author zc.ding
 * @date 2016年10月21日
 */
public class CustTokenManager {
	
	/**
	 * 加载当前的Subject
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @return
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获得session
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @return
	 */
	public static Session getSession(){
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession();
	}
	
	/**
	 * 向session中添加键值对
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param key
	 * @param value
	 */
	public static void setAttr(Object key, Object value){
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}
	
	/**
	 * 从session中获得key对应的这个
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param key
	 * @return
	 */
	public static String getString(Object key){
		return (String)SecurityUtils.getSubject().getSession().getAttribute(key);
	}
	
	/**
	 * 获得用户类型
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @return
	 */
	public static UserType getUserType(){
		return (UserType)SecurityUtils.getSubject().getSession().getAttribute(Cons.USER_TYPE);
	} 
}

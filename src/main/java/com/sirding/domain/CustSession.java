package com.sirding.domain;

import java.io.Serializable;
import java.util.Date;

import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUserInfo;

public class CustSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AppUserInfo appUserinfo;
	private AppSysUser appSysUser;
	
	private Serializable id;
    private Date startTimestamp;
    private Date stopTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private boolean expired;
    private String host;
	//session 是否踢出
	private boolean sessionStatus = Boolean.TRUE;
	
	
	public CustSession(){}
	
	public CustSession(AppUserInfo appUserInfo){
		this.appUserinfo = appUserInfo;
	}
	
	public CustSession(AppSysUser appSysUser){
		this.appSysUser = appSysUser;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Date getStopTimestamp() {
		return stopTimestamp;
	}

	public void setStopTimestamp(Date stopTimestamp) {
		this.stopTimestamp = stopTimestamp;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	public AppUserInfo getAppUserinfo() {
		return appUserinfo;
	}
	public void setAppUserinfo(AppUserInfo appUserinfo) {
		this.appUserinfo = appUserinfo;
	}
	public AppSysUser getAppSysUser() {
		return appSysUser;
	}
	public void setAppSysUser(AppSysUser appSysUser) {
		this.appSysUser = appSysUser;
	}
	
	public Long getUserId(){
		if(this.appSysUser != null){
			return appSysUser.getId().longValue();
		}
		if(this.appUserinfo != null){
			return appUserinfo.getId().longValue();
		}
		return -1L;
	}
}

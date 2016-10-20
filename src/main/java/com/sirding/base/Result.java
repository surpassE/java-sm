package com.sirding.base;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private boolean success = true;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Object obj = null;
	
	private String msg = "操作成功";
	
	public Result(){}
	
	public Result(boolean success){
		this.success = success;
	}
	
	public Result(String msg){
		this.msg = msg;
	}

	public Result(Object obj){
		this.obj = obj;
	}
	public Result(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public Result(boolean success, Object obj){
		this.success = success;
		this.obj = obj;
	}
	
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

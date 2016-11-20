package com.sirding.domain;
/**
 * @Described	: simple json
 * @project		: com.sirding.domain.dtpage.Dt
 * @author 		: zc.ding
 * @date 		: 2016年11月21日
 */
public class SimpleJson{
	
	public SimpleJson(){}
	
	public SimpleJson(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	private String name;
	private Object value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
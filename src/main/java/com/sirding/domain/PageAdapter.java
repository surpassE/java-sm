package com.sirding.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Described			: 分页适配器，对于不同的组件，分页的参数是不同，通过此适配器，实现service层以下分页一致
 * @project				: com.sirding.domain.Page
 * @author 				: zc.ding
 * @date 				: 2016年12月10日
 */
public class PageAdapter<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 起始页
	 */
	int pageNum;
	/**
	 * 每页条数
	 */
	int pageSize;
	/**
	 * 总条数
	 */
	long total;
	/**
	 * 排序字符串
	 */
	String sort;
	
	private List<T> resultList;
	private T result;
	private Object object;
	
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}

package com.sirding.base;

import java.util.List;

public interface CurdService<T> {
	
	/**
	 * 增加记录
	 * @param record
	 * @return
	 * @author zc.ding
	 * @date 2016年10月19日
	 */
	int add(T record);

	/**
	 * 删除记录
	 * @param id
	 * @return
	 * @author zc.ding
	 * @date 2016年10月19日
	 */
	int del(Integer id);
	
	/**
	 * 更新记录
	 * @param record
	 * @return
	 * @author zc.ding
	 * @date 2016年10月19日
	 */
	int update(T record);
	
	/**
	 * 通过Id检索记录
	 * @param id
	 * @return
	 * @author zc.ding
	 * @date 2016年10月19日
	 */
	T findById(Integer id);
	
	/**
	 * 条件检索记录集合
	 * @param record
	 * @return
	 * @author zc.ding
	 * @date 2016年10月19日
	 */
	List<T> findList(T record);
	
}

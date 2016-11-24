package com.sirding.base;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.sirding.core.utils.ReflectUtil;
import com.sirding.domain.dtpage.Page;

/**
 * 应用层基类
 * @author zc.ding
 * @date 2016年10月19日
 *
 */
public class BaseServiceImpl<T> implements CurdService<T>{
	
	protected Object mapper;

	@Override
	public int add(T record) {
		return ReflectUtil.callForEntity(mapper, "insert", record);
	}

	@Override
	public int del(Integer id) {
		return ReflectUtil.callForEntity(mapper, "deleteByPrimaryKey", id);
	}

	@Override
	public int update(T record) {
		return ReflectUtil.callForEntity(mapper, "updateByPrimaryKey", record);
	}

	@Override
	public T findById(Integer id) {
		return ReflectUtil.callForEntity(mapper, "selectByPrimaryKey", id);
	}

	@Override
	public List<T> findList(T record) {
		return null;
	}
	
	
	public List<T> findByPage(Page page, Object example){
		//设置排序
		if(page.getSort() != null && page.getSort().length() > 0){
			ReflectUtil.callForEntity(example, "setOrderByClause", page.getSort());
		}
		//含有同条数的分页
		com.github.pagehelper.Page<T> p = PageHelper.startPage(page.getStart(), page.getLength(), true);
		//执行数据查询操作
		List<T> list = ReflectUtil.callForEntity(mapper, "selectByExample", example);
		//保存分页总条数
		page.setTotal(p.getTotal());
		return list;
	}

}

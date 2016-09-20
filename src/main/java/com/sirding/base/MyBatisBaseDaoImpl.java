package com.sirding.base;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MyBatis基类
 * @author zc.ding
 * @date 2016年9月19日
 * @param <T>
 * @param <K>
 */
@Component(value="myBatisBaseDao")
public class MyBatisBaseDaoImpl<T, K extends Serializable> {

	private static final Logger logger = Logger.getLogger(MyBatisBaseDaoImpl.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public void showMsg(){
		if(sqlSessionFactory != null){
			logger.debug(sqlSessionFactory.toString());
		}
		if(sqlSessionTemplate != null){
			logger.debug(sqlSessionTemplate.toString());
		}
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}

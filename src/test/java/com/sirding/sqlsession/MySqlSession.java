package com.sirding.sqlsession;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

public class MySqlSession {
	Logger logger = Logger.getLogger(MySqlSession.class);
	
	@Test
	@Ignore
	public void initSqlSession(){
		try {
			String resource = "test/MapperConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("okok....");
	}
}

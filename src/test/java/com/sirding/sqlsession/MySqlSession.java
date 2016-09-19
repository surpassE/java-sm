package com.sirding.sqlsession;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;

import com.sirding.mybatis.model.UserInfo;

public class MySqlSession {
	Logger logger = Logger.getLogger(MySqlSession.class);
	
	@Test
	public void initSqlSession(){
		try {
			String resource = "MapperConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			UserInfo user = session.selectOne("com.sirding.mybatis.mapper.UserInfoMapper.selectByPrimaryKey", 1);
			session.close();
			if(user != null){
				logger.info(user.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("okok....");
	}
}

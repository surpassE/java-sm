package com.sirding.threads;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sirding.mybatis.mapper.UserInfoMapper;
import com.sirding.mybatis.model.UserInfo;
import com.sirding.service.UserService;
import com.sirding.utils.ACUtils;

public class UserMultiThread implements Runnable {

	private UserInfoMapper userInfoMapper;
	private int i;
	private UserService userService;

	public UserMultiThread(){}

	public UserMultiThread(UserInfoMapper userInfoMapper, int i){
		this.userInfoMapper = userInfoMapper;
		this.i = i;
	}

	public UserMultiThread(UserService userService, int i){
		this.userService = userService;
		this.i = i;
	}

	@Override
	public void run() {
		DataSourceTransactionManager transactionManager =
				(DataSourceTransactionManager) ACUtils.getBeanById("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(); 
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED); //事物隔离级别
		TransactionStatus status = transactionManager.getTransaction(def); //获得事务状态
		try {
			UserInfo user = new UserInfo();
			user.setName("thread_" + System.currentTimeMillis());
			user.setAge(20);
			System.out.println("i:" + i);
			//					this.userInfoMapper.insert(user);
			this.userService.addUserNest(user);				
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
		}
	}

}

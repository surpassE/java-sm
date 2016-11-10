package com.sirding.threads;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sirding.core.utils.ACUtils;

public class UserMultiThread implements Runnable {

	@Override
	public void run() {
		DataSourceTransactionManager transactionManager =
				(DataSourceTransactionManager) ACUtils.getBeanById("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(); 
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED); //事物隔离级别
		TransactionStatus status = transactionManager.getTransaction(def); //获得事务状态
		try {
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
		}
	}

}

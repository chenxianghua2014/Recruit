/**
 * 董再兴 TransactionManager.java 2013年7月16日
 */
package com.ttgis.recruit.utility;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

/**
 * @author 董再兴
 */
public class TransactionManager implements TransactionFactory {
	@Override
	public Transaction newTransaction(Connection arg0, boolean arg1) {
		Transaction tx = new JdbcTransaction(arg0, arg1);
		return tx;
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}

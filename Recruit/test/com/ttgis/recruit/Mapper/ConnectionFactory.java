/**
 * 董再兴 ConnectionFactory.java 2013年7月16日
 */
package com.ttgis.recruit.Mapper;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author 董再兴
 * 
 */
public class ConnectionFactory {
	private static SqlSessionFactory factory;
	private static SqlSession sqlSession = null;

	// 读取MyBatis配置文件，创建SqlSessionFactory
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-test.xml");
			factory = new SqlSessionFactoryBuilder().build(reader, "develop");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取SqlSession
	 * 
	 * @return
	 */
	public static SqlSession getSession() {
		if (sqlSession == null) {
			sqlSession = factory.openSession();
		}
		return sqlSession;
	}

	/**
	 * 从配置文件中获取数据库表映射对象信息
	 * 
	 * @param mapper
	 * @return
	 */
	public static <T> T getMapper(Class<T> mapper) {
		SqlSession session = getSession();
		return (T) session.getMapper(mapper);
	}

	/**
	 * 数据提交
	 */
	public static void commit() {
		sqlSession.commit();
	}

	/**
	 * 数据回滚
	 */
	public static void rollback() {
		sqlSession.rollback();
	}

	/**
	 * 关闭sqlsession
	 */
	public static void close() {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

}

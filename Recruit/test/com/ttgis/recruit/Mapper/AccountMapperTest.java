/**
 * 董再兴 SpringTxTestCase.java 2013年7月9日
 */
package com.ttgis.recruit.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 董再兴 测试基类
 */
public class AccountMapperTest
{
	UserinfoMapper YhbMapper;
	long start;

	@Before
	public void init()
	{

	}

	@After
	public void destory()
	{

	}

	/**
	 * 插入账户实体
	 */
	@Test
	public void accountMapperInsertTest()
	{

	}

	/**
	 * 查询账户实体
	 */
	@Test
	public void accountMapperTest()
	{
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String d = format.format(new Date());
		System.out.println(d);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeNow;
		try
		{
			timeNow = df.parse(d);
			Calendar begin = Calendar.getInstance();
			begin.setTime(timeNow);
			int yssj = 1440;
			begin.add(Calendar.MINUTE, yssj);
			String sendTime = df.format(begin.getTime());
			System.out.println("ss" + sendTime);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询更新账号信息
	 */
	@Test
	public void accountMapperUpdateTest()
	{
	}

	@Test
	public void SendMailTest()
	{
		// 设置邮件服务器信息

	}
}

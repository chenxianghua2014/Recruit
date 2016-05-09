/**
 * 董再兴 accountWServiceTest.java 2013年8月29日
 */
package com.ttgis.recruit.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author 董再兴 accountWService 测试
 */
public class accountWServiceTest
{

	static Logger logger = Logger.getLogger(accountWServiceTest.class);

	/**
	 * @param args
	 */
	@Test
	public void getAccountTest()
	{
		RPCServiceClient serviceClient = null;
		try
		{
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference("http://localhost:8080/Water/services/accountWService");
			options.setTo(targetEPR);

			Object[] opAddEntryArgs = new Object[]
			{};
			Class[] classes = new Class[]
			{ String.class };

			// 下面的代码调用了getAccount方法
			QName opAddEntry = new QName("http://ws.water.ttgis.com", "getAccount");
			// String strXml = serviceClient.invokeBlocking(opAddEntry,
			// opAddEntryArgs, classes)[0].toString();
			OMElement rtnString = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs);
			Object[] o = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes);

			if (o.length > 0)
				System.out.println(o[0]);

			System.out.println(rtnString);

		} catch (AxisFault e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void aaa() throws ParseException
	{
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String d =format.format(new Date());
        System.out.println(d);
        
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeNow;
		timeNow = df.parse(d);
		Calendar begin = Calendar.getInstance();
		begin.setTime(timeNow);
		begin.add(Calendar.MINUTE, 30);
		String sendTime = df.format(begin.getTime());
		System.out.println(sendTime);
	}

	@Test
	public void getPointTest()
	{
		RPCServiceClient serviceClient = null;
		try
		{
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference("http://localhost:8080/Water/services/accountWService");
			options.setTo(targetEPR);

			Object[] opAddEntryArgs = new Object[]
			{ "孙郁航" };
			Class[] classes = new Class[]
			{ String.class };

			// 下面的代码调用了getAccount方法
			QName opAddEntry = new QName("http://ws.water.ttgis.com", "getPoint");
			// String strXml = serviceClient.invokeBlocking(opAddEntry,
			// opAddEntryArgs, classes)[0].toString();
			// OMElement rtnString = serviceClient.invokeBlocking(opAddEntry,
			// opAddEntryArgs);
			Object[] o = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes);

			if (o.length > 0)
				System.out.println(o[0]);

			// System.out.println(rtnString);

		} catch (AxisFault e)
		{
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}

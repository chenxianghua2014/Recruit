/**
 * 题库管理 TkglMapper 方法测试 2014年05月19日
 * 董再兴
 */
package com.ttgis.recruit.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ttgis.recruit.domain.XmlEntity;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.utility.Des;
import com.ttgis.recruit.utility.SendMessage;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * @author 董再兴
 * 
 */
public class TkglMapperTest
{
	TkglMapper tkglMapper;
	ZpxwMapper zpxwMapper;
	long start;

	@Before
	public void init()
	{
		System.out.println(start = System.currentTimeMillis());
		tkglMapper = ConnectionFactory.getMapper(com.ttgis.recruit.Mapper.TkglMapper.class);
	}

	@After
	public void destory()
	{
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void insert()
	{
		String ss = "22";
		System.out.println(ss);
		com.ttgis.recruit.domain.Tkgl tkgl = new com.ttgis.recruit.domain.Tkgl();

		tkgl.setTkglId(RandomGUIDUtil.generateKey());
		tkgl.setTkglStlx("1");
		tkgl.setTkglNddj("2");
		tkgl.setTkglSttg("3");
		tkgl.setTkglStxxA("4");
		tkgl.setTkglStxxB("5");
		tkgl.setTkglStxxC("6");
		tkgl.setTkglStxxD("7");
		tkgl.setTkglStda("8");
		tkgl.setTkglDaxj("9");
		tkgl.setTkglTszg("0");

		tkgl.setTkglAddtime(new Date());
		tkgl.setTkglDelflag(1L);

		System.out.println("insert 结果：" + tkglMapper.insert(tkgl));
	}

	@Test
	public void insertSelective()
	{
		com.ttgis.recruit.domain.Tkgl tkgl = new com.ttgis.recruit.domain.Tkgl();

		tkgl.setTkglId(RandomGUIDUtil.generateKey());
		tkgl.setTkglStlx("1");
		tkgl.setTkglNddj("2");
		tkgl.setTkglSttg("3");
		tkgl.setTkglStxxA("4");
		tkgl.setTkglStxxB("5");
		tkgl.setTkglStxxC("6");
		tkgl.setTkglStxxD("7");
		tkgl.setTkglStda("8");
		tkgl.setTkglDaxj("9");
		tkgl.setTkglTszg("0");

		tkgl.setTkglAddtime(new Date());
		tkgl.setTkglDelflag(1L);

		System.out.println("insertSelective 结果：" + tkglMapper.insertSelective(tkgl));
	}

	@Test
	public void updateByPrimaryKeySelective() throws Exception
	{
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String d = format.format(new Date());
		System.out.println(d);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeNow;
		timeNow = df.parse(d);
		Calendar begin = Calendar.getInstance();
		begin.setTime(timeNow);
		begin.add(Calendar.MINUTE, 1440);
		String sendTime = df.format(begin.getTime());
		System.out.println("ss"+sendTime);
		
	}

	@Test
	public void updateByPrimaryKey()
	{

	}

	@Test
	public void re() throws ParseException
	{
		// 发送短信
		String xml = null;
		XmlEntity xmlentity = new XmlEntity();
		SendMessage s = new SendMessage();
		String xxtzMessageContent = "地方撒反对撒富士达范德萨范德萨饭店撒富士达 ";

		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String d = format.format(new Date());
		System.out.println(d);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeNow;
		timeNow = df.parse(d);
		Calendar begin = Calendar.getInstance();
		begin.setTime(timeNow);
		begin.add(Calendar.MINUTE, 1);
		String sendTime = df.format(begin.getTime());

		xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", "18604618859", xxtzMessageContent, sendTime.replaceAll(" ", "%20")).toString();
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setRemainpoint("remainpoint");
		xmlentity.setTaskID("taskID");
		xmlentity.setSuccessCounts("successCounts");
		xmlentity = s.readStringXmlCommen(xmlentity, xml); 
	}

	@Test
	public void ReadHtml()
	{
		Zpxw z = zpxwMapper.selectByPrimaryKey("7B3520CE-845A-04B5-8B9F-0895B6DB248A");
		String str = z.getZpxwContent();

		Document doc = Jsoup.parse(str);
		Elements trs = doc.select("table").select("tr");
		for (int i = 0; i < trs.size(); i++)
		{
			Elements tds = trs.get(i).select("td");
			for (int j = 0; j < tds.size(); j++)
			{
				String text = tds.get(j).text();
				System.out.println(text);
			}
		}
	}

	@Test
	public void selectByPages()
	{
		System.out.print("数据总数：" + tkglMapper.selectCount());
	}

}

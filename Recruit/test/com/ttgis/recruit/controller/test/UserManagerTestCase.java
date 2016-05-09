package com.ttgis.recruit.controller.test;

import org.junit.Test;

import com.ttgis.recruit.utility.MailSenderInfo;
import com.ttgis.recruit.utility.SendEmail;

public class UserManagerTestCase
{
	@Test
	public void testMain()
	{
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 收件人邮箱
		mailInfo.setToAddress("340234888@qq.com");
		// 邮件标题
		mailInfo.setSubject("我的简历");
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("请于2020年3月3日来测评，收到请回复");
		mailInfo.setContent(buffer.toString());
		// 发送html格式
		SendEmail.sendHtmlMail(mailInfo);
	}

	
}

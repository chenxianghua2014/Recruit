package com.ttgis.recruit.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.utility.MailSenderInfo;
import com.ttgis.recruit.utility.SendEmail;

@Component
public class SendEmailController
{
	@Resource
	XxtzService xxtzService;

	@Scheduled(fixedDelay = 5000)
	// @Scheduled(cron = "0 0/1 * * * ?") //一分钟更新一次
	public void WaitSend()
	{
		List<Xxtz> xxtzs = xxtzService.selectCjtsEmail();
		for (Xxtz xxtz : xxtzs)
		{
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 收件人邮箱
			mailInfo.setToAddress(xxtz.getXxtzEmail());
			// 邮件标题
			mailInfo.setSubject("成绩推送————" + xxtz.getXxtzUser());
			// 邮件内容
			StringBuffer buffer = new StringBuffer();
			buffer.append(xxtz.getXxtzEmailContent());
			mailInfo.setContent(buffer.toString());
			// 发送html格式
			SendEmail.sendHtmlMail(mailInfo);
			xxtz.setXxtzEmailResult("Success");
			xxtzService.updateByPrimaryKeySelective(xxtz);
		}
	}
}

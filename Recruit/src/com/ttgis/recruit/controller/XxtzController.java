package com.ttgis.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Cjtsgl;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.QueryXxtz;
import com.ttgis.recruit.domain.XmlEntity;
import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.CjtsglService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.MbglService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.MailSenderInfo;
import com.ttgis.recruit.utility.SendEmail;
import com.ttgis.recruit.utility.SendMessage;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

@Controller
public class XxtzController
{
	@Resource
	XxtzService xxtzService;
	@Resource
	MbglService mbglService;
	@Resource
	JtjlkService jtjlkService;
	@Resource
	UserService userService;
	@Resource
	ZzjgService zzjgService;
	@Resource
	CjtsglService cjtsglService;

	static Logger log = Logger.getLogger(XcxxController.class);
	@Autowired
	private HttpServletRequest request;
	@Autowired
	HttpSession session;

	protected String getRemoteIp()
	{
		String remoteIp = null;
		remoteIp = request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getHeader("X-Real-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getHeader("Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getHeader("HTTP_CLIENT_IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getRemoteAddr();
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = request.getRemoteHost();
		}
		return remoteIp;
	}

	public void logInfo(String _call, String _parameter)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" Ip:");
		stringBuilder.append("【");
		stringBuilder.append(getRemoteIp());
		stringBuilder.append("】");
		stringBuilder.append(" SessionUId:");
		stringBuilder.append("【");
		if (session.getAttribute("userId") != null)
			stringBuilder.append(session.getAttribute("userId"));
		if (session.getAttribute("zzjgId") != null)
			stringBuilder.append(session.getAttribute("zzjgId"));
		stringBuilder.append("】");
		stringBuilder.append(" Call:");
		stringBuilder.append("【");
		stringBuilder.append(_call);
		stringBuilder.append("】");
		stringBuilder.append(" Parameter:");
		stringBuilder.append("【");
		stringBuilder.append(_parameter);
		stringBuilder.append("】");
		log.info(stringBuilder);
	}

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

	@RequestMapping(value = "/Xxtz", method = RequestMethod.GET)
	public ModelAndView Xxtz(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("Xxtz", id);
		return new ModelAndView("Xxtz/Xxtz", "id", id);
	}

	@RequestMapping(value = "/ImportXxtzShow", method = RequestMethod.GET)
	public ModelAndView ImportXxtzShow()
	{
		return new ModelAndView("Xxtz/ImportXxtzShow");
	}

	@ResponseBody
	@RequestMapping(value = "/LoadTzdx", method = RequestMethod.POST)
	public List<Jtjlk> LoadTzdx(String type, HttpSession session)
	{
		logInfo("LoadTzdx", type);
		if (type.equals("测评通知") || type.equals("面试通知"))
			type = "通过";
		else if (type.equals("录用通知"))
			type = "录用";
		else if (type.equals("淘汰通知"))
			type = "淘汰";
		else if (type.equals("面试圈通知"))
			type = "已安排面试";
		Map<String, String> map = new HashMap<String, String>();
		map.put("jlzt", type);
		map.put("zzjgId", session.getAttribute("zzjgId").toString());
		List<Jtjlk> jtjlk = jtjlkService.selectByJlzt(map);
		return jtjlk;
	}

	@ResponseBody
	@RequestMapping(value = "/SendAgain", method = RequestMethod.POST)
	public XmlEntity SendAgain(String id)
	{
		logInfo("SendAgain", id);
		Xxtz xxtz = xxtzService.selectByPrimaryKey(id);
		// 发送短信
		String xml = null;
		XmlEntity xmlentity = new XmlEntity();
		SendMessage s = new SendMessage();
		String xxtzMessageContent = Html2Text(xxtz.getXxtzMessageContent());
		xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", xxtz.getXxtzTelepohne().toString(), xxtzMessageContent, "").toString();
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setRemainpoint("remainpoint");
		xmlentity.setTaskID("taskID");
		xmlentity.setSuccessCounts("successCounts");
		xmlentity = s.readStringXmlCommen(xmlentity, xml);
		xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
		xxtz.setXxtzMessageTime(new Date());
		xxtzService.updateByPrimaryKeySelective(xxtz);
		return xmlentity;
	}

	/**
	 * 查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXxtzData", method = RequestMethod.POST)
	public List<Xxtz> LoadXxtzData(QueryXxtz q, HttpSession session)
	{
		logInfo("LoadXxtzData", JSONArray.fromObject(q).toString());
		q.setZzjgId(session.getAttribute("zzjgId").toString());
		List<Xxtz> xxtz = xxtzService.selectByWhere(q);
		return xxtz;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXxtzDataCount", method = RequestMethod.POST)
	public int LoadXxtzDataCount(QueryXxtz q, HttpSession session)
	{
		logInfo("LoadXxtzDataCount", JSONArray.fromObject(q).toString());
		q.setZzjgId(session.getAttribute("zzjgId").toString());
		int intCount = xxtzService.selectCount(q);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/Xxtx", method = RequestMethod.GET)
	public ModelAndView Xxtx(HttpSession session)
	{
		logInfo("Xxtx", "");
		Cjtsgl cjtsgl = cjtsglService.selectByPrimaryKey(session.getAttribute("zzjgId").toString());
		return new ModelAndView("Xxtz/Xxtx", "cjtsgl", cjtsgl);
	}

	@ResponseBody
	@RequestMapping(value = "/GetZzjg3", method = RequestMethod.POST)
	public List<Zzjg> GetZzjg3(String id)
	{
		logInfo("GetZzjg3", id);
		List<Zzjg> zzjgFOList = zzjgService.selectBySjdw(id);
		return zzjgFOList;
	}

	/**
	 * 保存操作
	 * 
	 * @param xxtz
	 * @return
	 */

	@RequestMapping(value = "/XxtzSava", method = RequestMethod.POST)
	public ModelAndView XxtzSava(Xxtz xxtz)
	{
		logInfo("XxtzSava", JSONArray.fromObject(xxtz).toString());
		String[] type = xxtz.getType();
		for (int i = 0; i < type.length; i++)
		{
			xxtz.setXxtzType(type[i]);
			xxtz.setXxtzDelflag((long) 1);
			xxtz.setXxtzAddtime(new Date());
			xxtz.setXxtzId(RandomGUIDUtil.generateKey());
			xxtzService.insertxxtz(xxtz);
		}

		return new ModelAndView("redirect:/Xxtz");
	}

	@RequestMapping(value = "/DoFileUploadXxtz", method = RequestMethod.POST)
	public ModelAndView DoFileUploadXxtz(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		logInfo("DoFileUploadXxtz", "");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		@SuppressWarnings("deprecation")
		String ctxPath = request.getRealPath("/") + "attach\\";
		// 创建文件夹
		File file = new File(ctxPath);
		if (!file.exists())
		{
			file.mkdirs();
		}
		String fileName = null;
		// 存到session里所有的文件名
		String strFileNames = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
		{
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			strFileNames = fileName;
			File uploadFile = new File(ctxPath + strFileNames);
			try
			{
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e)
			{
				e.printStackTrace();
				logInfo("DoFileUploadXxtz", "******Error");
			}
		}
		request.getSession().setAttribute("FilePath", ctxPath + strFileNames);
		return new ModelAndView("Xxtz/ImportXxtzShow", "CloseWindow", "window.close();");
	}

	public static String Html2Text(String inputString)
	{
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try
		{
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
																										// }
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e)
		{
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr.replace("&nbsp;", "");// 返回文本字符串
	}
}

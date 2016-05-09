package com.ttgis.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.utility.MD5;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。QtZhmmController
 * @创建人：范井龙
 * @日期：
 * @描述：前台账号密码
 * @版本号：
 */
@Controller
public class QtZhmmController
{
	@Autowired
	private UserService userService;

	static Logger log = Logger.getLogger(QtZhmmController.class);
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

	@RequestMapping(value = "/zhmm", method = RequestMethod.GET)
	public String zhmm()
	{
		return "Main/zhmm";
	}

	@RequestMapping(value = "/checkUserIsCZ", method = RequestMethod.POST)
	public String checkUserIsCZ(Userinfo user, Model model)
	{
		logInfo("checkUserIsCZ", JSONArray.fromObject(user).toString());
		Userinfo userCheck = userService.checkUserIsCZ(user);
		if (userCheck == null)
		{
			model.addAttribute("uri", "checkUserIsCZ");
			return "success";
		} else
		{
			model.addAttribute("user", userCheck);
			return "Main/zhmmTwoStep";
		}
	}

	@RequestMapping(value = "/successCzUserUpdPD", method = RequestMethod.POST)
	public ModelAndView successCzUserUpdPD(Userinfo user)
	{
		logInfo("successCzUserUpdPD", JSONArray.fromObject(user).toString());
		user.setUserPassword(MD5.getMD5ofStr(user.getUserPassword()));
		userService.updateByPrimaryKeySelective(user);
		return new ModelAndView("success", "uri", "successCzUserUpdPD");
	}

}

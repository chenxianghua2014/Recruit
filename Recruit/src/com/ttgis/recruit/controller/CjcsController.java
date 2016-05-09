package com.ttgis.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ZzjgService;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。CjcsController
 * @创建人： 范井龙
 * @日期：
 * @描述： 成绩查询
 * @版本号：
 */

@Controller
public class CjcsController
{
	@Autowired
	private ZzjgService zzjgService;

	static Logger log = Logger.getLogger(JkController.class);
	@Autowired
	private HttpServletRequest _request;
	@Autowired
	HttpSession _session;

	protected String getRemoteIp()
	{
		String remoteIp = null;
		remoteIp = _request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getHeader("X-Real-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getHeader("Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getHeader("HTTP_CLIENT_IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getRemoteAddr();
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp))
		{
			remoteIp = _request.getRemoteHost();
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
		if (_session.getAttribute("userId") != null)
			stringBuilder.append(_session.getAttribute("userId"));
		if (_session.getAttribute("zzjgId") != null)
			stringBuilder.append(_session.getAttribute("zzjgId"));
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

	/**
	 * 跳转页面方法
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Cjcx", method = RequestMethod.GET)
	public ModelAndView Article(Model model, HttpSession session)
	{
		logInfo("Cjcx", "");
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (zzjg.getZzjgSjdw().equals(null) || zzjg.getZzjgSjdw().equals(""))
		{
			List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
			List<Zzjg> zzjgSunList = zzjgService.queryUserSun();
			model.addAttribute("zzjgFOList", zzjgFOList);
			model.addAttribute("zzjgSunList", zzjgSunList);
			return new ModelAndView("Cjcx/Cjcx");
		} else if (zzjg.getZzjgSjdw().equals("test001"))
		{
			List<Zzjg> zzjgFOList = zzjgService.EJqueryUserFO(zzjg.getZzjgId());
			List<Zzjg> zzjgSunList = zzjgService.EJqueryUserSun(zzjg.getZzjgId());
			model.addAttribute("zzjgFOList", zzjgFOList);
			model.addAttribute("zzjgSunList", zzjgSunList);
			return new ModelAndView("Cjcx/Cjcx");
		} else
		{
			Zzjg SJzzjg = zzjgService.selectByPrimaryKey(zzjg.getZzjgId());
			model.addAttribute("SJzzjg", SJzzjg);
			return new ModelAndView("Cjcx/Cjcx");
		}
	}

	/**
	 * 分页查询（已经审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */

}

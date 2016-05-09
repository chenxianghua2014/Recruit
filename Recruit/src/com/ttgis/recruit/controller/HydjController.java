package com.ttgis.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Hycpdj;
import com.ttgis.recruit.domain.Hydj;
import com.ttgis.recruit.domain.QueryHydj;
import com.ttgis.recruit.service.HydjService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。HydjController
 * @创建人： 范井龙
 * @日期：
 * @描述： 会员管理收费规则
 * @版本号：
 */

@Controller
public class HydjController
{
	@Autowired
	private HydjService hydjService;

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
	 * @return
	 */
	@RequestMapping(value = "/hydj", method = RequestMethod.GET)
	public ModelAndView hyglHydj(Model model, Hycpdj hycpdj)
	{
		logInfo("hydj", JSONArray.fromObject(hycpdj).toString());
		hycpdj = hydjService.queryHycpdj();
		if (hycpdj != null)
			model.addAttribute("hycpdj", hycpdj.getHycpdj());
		return new ModelAndView("hygl/hydj");
	}

	/**
	 * 分页查询
	 * 
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadHydjData", method = RequestMethod.POST)
	public List<Hydj> LoadHydjData(QueryHydj qh)
	{
		logInfo("LoadHydjData", JSONArray.fromObject(qh).toString());
		List<Hydj> listHydj = hydjService.selectByWhere(qh);
		return listHydj;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadHydjDataCount", method = RequestMethod.POST)
	public int LoadHydjDataCount(QueryHydj qh)
	{
		logInfo("LoadHydjDataCount", JSONArray.fromObject(qh).toString());
		int intCount = hydjService.selectCount(qh);
		return intCount;
	}

	@RequestMapping(value = "/hydjAdd", method = RequestMethod.GET)
	public ModelAndView hydjAdd(String hydjId)
	{
		logInfo("hydjAdd", hydjId);
		if (hydjId == null)
		{
			return new ModelAndView("hygl/hydjAdd");
		} else
		{
			Hydj hydj = new Hydj();
			hydj = hydjService.selectByPrimaryKey(hydjId);
			return new ModelAndView("hygl/hydjAdd", "hydj", hydj);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/SaveHydj", method = RequestMethod.POST)
	public ModelAndView SaveHydj(Hydj hydj)
	{
		logInfo("SaveHydj", JSONArray.fromObject(hydj).toString());
		if (hydj.getHydjId().equals(""))
		{
			hydj.setHydjId(RandomGUIDUtil.generateKey());
			hydjService.insertSelective(hydj);
		} else
		{
			hydjService.updateByPrimaryKeySelective(hydj);
		}
		return new ModelAndView("redirect:/hydj");
	}

	/**
	 * 删除信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delHydj", method = RequestMethod.GET)
	public String delHydj(String hydjId)
	{
		logInfo("delHydj", hydjId);
		hydjService.delHydj(hydjId);
		return "redirect:/hydj";
	}

	@RequestMapping(value = "/UpdHycpdj", method = RequestMethod.POST)
	public String UpdHycpdj(String hycpdj)
	{
		logInfo("UpdHycpdj", hycpdj);
		hydjService.UpdHycpdj(hycpdj);
		return "redirect:/hydj";
	}

}

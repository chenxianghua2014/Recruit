package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.QueryKcgl;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。KcglControler
 * @创建人： 范井龙
 * @日期：
 * @描述： 考场管理
 * @版本号：
 */
@Controller
public class KcglControler
{
	@Autowired
	private KcglService kcglService;
	@Autowired
	private KsxcglService ksxcglService;
	@Autowired
	private BmglService bmglService;

	static Logger log = Logger.getLogger(KcglControler.class);
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
	@RequestMapping(value = "/kcgl", method = RequestMethod.GET)
	public ModelAndView luntan()
	{
		return new ModelAndView("kcgl/kcgl");
	}

	@ResponseBody
	@RequestMapping(value = "/queryKcgl", method = RequestMethod.POST)
	public List<Kcgl> queryKcgl()
	{
		logInfo("QueryKcgl", "");
		List<Kcgl> listKcgl = kcglService.queryKcgl();
		return listKcgl;
	}

	@ResponseBody
	@RequestMapping(value = "/queryKcglById", method = RequestMethod.POST)
	public Kcgl queryKcglById(String kcId)
	{
		logInfo("queryKcglById", "");
		return kcglService.selectByPrimaryKey(kcId);
	}

	/**
	 * 分页查询
	 * 
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/KcglData", method = RequestMethod.POST)
	public List<Kcgl> KcglData(QueryKcgl qk)
	{
		logInfo("KcglData", JSONArray.fromObject(qk).toString());
		List<Kcgl> listKcgl = kcglService.selectByWhere(qk);
		return listKcgl;
	}

	@ResponseBody
	@RequestMapping(value = "/KcglDataCount", method = RequestMethod.POST)
	public int KcglDataCount(QueryKcgl qk)
	{
		logInfo("KcglDataCount", JSONArray.fromObject(qk).toString());
		int intCount = kcglService.selectCount(qk);
		return intCount;
	}

	@RequestMapping(value = "/addKcgl", method = RequestMethod.GET)
	public ModelAndView addKcgl(String kcglId)
	{
		logInfo("addKcgl", kcglId);
		if (kcglId == null)
		{
			return new ModelAndView("kcgl/addKcgl");
		} else
		{
			Kcgl kcgl = kcglService.selectByPrimaryKey(kcglId);
			return new ModelAndView("kcgl/addKcgl", "kcgl", kcgl);
		}
	}

	/**
	 * 添加标签
	 */
	@RequestMapping(value = "/saveKcgl", method = RequestMethod.POST)
	public String SaveKcgl(Kcgl kcgl)
	{
		logInfo("saveKcgl", JSONArray.fromObject(kcgl).toString());
		if (kcgl.getKcglId().equals(""))
		{
			kcgl.setKcglAddtime(new Date());
			kcgl.setKcglDelflag((long) 1);
			kcgl.setKcglId(RandomGUIDUtil.generateKey());
			kcglService.insertSelective(kcgl);
			return "kcgl/kcgl";
		} else
		{
			kcgl.setKcglDelflag((long) 1);
			kcglService.updateByPrimaryKeySelective(kcgl);
			return "kcgl/kcgl";
		}
	}
 
	@RequestMapping(value = "/delKcgl", method = RequestMethod.GET)
	public String delKcgl(String kcglId)
	{
		logInfo("delKcgl", kcglId);
		bmglService.delByKcglId(kcglId);
		Kcgl kcgl = kcglService.selectByPrimaryKey(kcglId);
		ksxcglService.delByKcglName(kcgl.getKcglId());
		kcglService.delKcgl(kcglId);
		return "kcgl/kcgl";
	}

}

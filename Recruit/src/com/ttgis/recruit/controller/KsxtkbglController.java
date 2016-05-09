package com.ttgis.recruit.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Ksxtkbgl;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.service.KsxtkbglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。KsxtkbglController
 * @创建人： 刘毅
 * @日期：
 * @描述： 考场现场开闭管理
 * @版本号：
 */
@Controller
public class KsxtkbglController
{

	@Resource
	private KsxtkbglService ksxtkbglService;
	
	static Logger log = Logger.getLogger(KsxtkbglController.class);
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

	@RequestMapping(value = "/Ksxtkbgl", method = RequestMethod.GET)
	public ModelAndView Ksxtkbgl()
	{
		return new ModelAndView("Ksxtkbgl/Ksxtkbgl");
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
	@RequestMapping(value = "/LoadKsxtkbglData", method = RequestMethod.POST)
	public List<Ksxtkbgl> LoadKsxtkbglData(Integer pageNum, Integer pageSize, Integer currentPageSize, String keywords)
	{
		PageCondition pageCondition = new PageCondition();
		pageCondition.setPageNum(pageNum);
		pageCondition.setPageSize(pageSize);
		pageCondition.setCurrentPageSize(currentPageSize);
		pageCondition.setKeywords(keywords);
		logInfo("LoadKsxtkbglData", JSONArray.fromObject(pageCondition).toString());
		List<Ksxtkbgl> ksxtkbgl = ksxtkbglService.selectByWhere(pageCondition);
		return ksxtkbgl;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadKsxtkbglDataCount", method = RequestMethod.POST)
	public int LoadKsxtkbglDataCount(String keywords)
	{
		PageCondition pageCondition = new PageCondition();
		pageCondition.setKeywords(keywords);
		logInfo("LoadKsxtkbglDataCount", JSONArray.fromObject(pageCondition).toString());
		int intCount = ksxtkbglService.selectCount(pageCondition);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/KsxtkbglAdd", method = RequestMethod.GET)
	public ModelAndView AddKsxtkbgl(@RequestParam(value = "ksxtkbglId", required = false) String ksxtkbglId)
	{
		logInfo("KsxtkbglAdd", ksxtkbglId);
		if (ksxtkbglId == null)
		{
			return new ModelAndView("Ksxtkbgl/Ksxtkbgl");
		} else
		{
			Ksxtkbgl ksxtkbgl = new Ksxtkbgl();
			ksxtkbgl = ksxtkbglService.selectByPrimaryKey(ksxtkbglId);
			return new ModelAndView("Ksxtkbgl/KsxtkbglAdd", "ksxtkbgl", ksxtkbgl);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param
	 * @return
	 * @throws ParseException
	 */

	@RequestMapping(value = "/KsxtkbglSava", method = RequestMethod.POST)
	public ModelAndView KsxtkbglSava(Ksxtkbgl ksxtkbgl) throws ParseException
	{
		logInfo("KsxtkbglSava", JSONArray.fromObject(ksxtkbgl).toString());
		if (ksxtkbgl.getKsxtkbglId().equals(""))
		{
			ksxtkbgl.setKsxtkbglDelflag((long) 1);
			ksxtkbgl.setKsxtkbglAddtime(new Date());
			ksxtkbgl.setKsxtkbglId(RandomGUIDUtil.generateKey());
			ksxtkbglService.insertksxtkbgl(ksxtkbgl);

		} else
		{
			ksxtkbglService.updateByPrimaryKeySelective(ksxtkbgl);
		}
		return new ModelAndView("redirect:/Ksxtkbgl");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/delKsxtkbgl", method = RequestMethod.GET)
	public String delKsxtkbgl(String ksxtkbglId)
	{
		logInfo("delKsxtkbgl", ksxtkbglId);
		ksxtkbglService.deleteByPrimaryKey(ksxtkbglId);
		return "Ksxtkbgl/Ksxtkbgl";
	}
}

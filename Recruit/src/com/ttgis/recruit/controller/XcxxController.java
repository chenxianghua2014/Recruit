/**
 * 宣传信息 2014-05-14 孙建国
 */
package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.QueryXcxx;
import com.ttgis.recruit.domain.Xcxx;
import com.ttgis.recruit.service.XcxxService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class XcxxController
{
	@Resource
	XcxxService xcxxService;

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

	/**
	 * 三级单位简介发布
	 * @return
	 */
	@RequestMapping(value = "/three", method = RequestMethod.GET)
	public ModelAndView Three()
	{
		return new ModelAndView("Xcxx/three");
	}
	
	/**
	 * 二级单位简介发布
	 * @return
	 */
	@RequestMapping(value = "/two", method = RequestMethod.GET)
	public ModelAndView Two()
	{
		return new ModelAndView("Xcxx/two");
	}
	
	@RequestMapping(value = "/Xcxx", method = RequestMethod.GET)
	public ModelAndView Xcxx()
	{
		return new ModelAndView("Xcxx/Xcxx");
	}

	/**
	 * 查询
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXcxxData1", method = RequestMethod.POST)
	public List<Xcxx> LoadXcxxData1(QueryXcxx qXcxx, HttpSession session)
	{
		logInfo("LoadXcxxData1", JSONArray.fromObject(qXcxx).toString());
		qXcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
		List<Xcxx> xcxxs = xcxxService.selectByWhere1(qXcxx);
		return xcxxs;
	}
	
	/**
	 * 查询总条数
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXcxxDataCount1", method = RequestMethod.POST)
	public int LoadXcxxDataCount1(QueryXcxx qXcxx, HttpSession session)
	{
		logInfo("LoadXcxxDataCount1", JSONArray.fromObject(qXcxx).toString());
		qXcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
		int intCount = xcxxService.selectCount1(qXcxx);
		return intCount;
	}
	
	/**
	 * 查询
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXcxxData", method = RequestMethod.POST)
	public List<Xcxx> LoadXcxxData(QueryXcxx qXcxx, HttpSession session)
	{
		logInfo("LoadXcxxData", JSONArray.fromObject(qXcxx).toString());
		qXcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
		List<Xcxx> xcxxs = xcxxService.selectByWhere(qXcxx);
		return xcxxs;
	}

	/**
	 * 查询总条数
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXcxxDataCount", method = RequestMethod.POST)
	public int LoadXcxxDataCount(QueryXcxx qXcxx, HttpSession session)
	{
		logInfo("LoadXcxxDataCount", JSONArray.fromObject(qXcxx).toString());
		qXcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
		int intCount = xcxxService.selectCount(qXcxx);
		return intCount;
	}
	
	/**
	 * 添加修改页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/XcxxView", method = RequestMethod.GET)
	public ModelAndView XcxxView(@RequestParam(value = "id", required = false) String id, HttpSession session)
	{
		logInfo("XcxxView", id);
		Xcxx xcxx = new Xcxx();
		if (id == null)
		{
			xcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
			return new ModelAndView("Xcxx/XcxxView", "xcxx", xcxx);
		} else
		{
			xcxx = xcxxService.selectByPrimaryKey(id);
			return new ModelAndView("Xcxx/XcxxView", "xcxx", xcxx);
		}
	}

	/**
	 * 添加修改页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/XcxxAdd", method = RequestMethod.GET)
	public ModelAndView XcxxAdd(@RequestParam(value = "id", required = false) String id, HttpSession session)
	{
		logInfo("XcxxAdd", id);
		Xcxx xcxx = new Xcxx();
		if (id == null)
		{
			xcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
			return new ModelAndView("Xcxx/XcxxAdd", "xcxx", xcxx);
		} else
		{
			xcxx = xcxxService.selectByPrimaryKey(id);
			return new ModelAndView("Xcxx/XcxxAdd", "xcxx", xcxx);
		}
	}

	@RequestMapping(value = "/XcxxUploadImg", method = RequestMethod.GET)
	public ModelAndView XcxxUploadImg()
	{
		return new ModelAndView("Xcxx/XcxxUploadImg");
	}

	@RequestMapping(value = "/XcxxUploadMov", method = RequestMethod.GET)
	public ModelAndView XcxxUploadMov()
	{
		return new ModelAndView("Xcxx/XcxxUploadMov");
	}

	@RequestMapping(value = "/XcxxMovPreview", method = RequestMethod.GET)
	public ModelAndView XcxxMovPreview()
	{
		logInfo("XcxxMovPreview", "");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		return new ModelAndView("Xcxx/XcxxMovPreview", "path", basePath);
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/XcxxSava", method = RequestMethod.POST)
	public ModelAndView XcxxSava(Xcxx xcxx, HttpSession session)
	{
		logInfo("XcxxSava", JSONArray.fromObject(xcxx).toString());
		xcxx.setXcxxAddcompany(session.getAttribute("zzjgName").toString());
		xcxx.setXcxxAdduser(session.getAttribute("zzjgId").toString());
		xcxx.setXcxxDelflag(1);
		xcxx.setXcxxKqzt("");
		if (!session.getAttribute("zzjgSjdw").toString().equals("") && !session.getAttribute("zzjgSjdw").toString().equals("test001"))
			xcxx.setXcxxCheckstatus("待审核");
		else
			xcxx.setXcxxCheckstatus("已发布");
		if (xcxx.getXcxxId().equals(""))
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("xcxxAdduser", session.getAttribute("zzjgId").toString());
			map.put("xcxxType", xcxx.getXcxxTitle());
			List<Xcxx> x = xcxxService.selectByType(map);
			if (x.size() > 0)
				return new ModelAndView("success", "uri", "XcxxExist");
			xcxx.setXcxxChecktime(null);
			xcxx.setXcxxCheckuser("");
			xcxx.setXcxxAddtime(new Date());
			xcxx.setXcxxId(RandomGUIDUtil.generateKey());
			xcxxService.insertSelective(xcxx);
		} else
		{
			xcxxService.updateByPrimaryKeySelective(xcxx);
		}
		return new ModelAndView("success", "uri", "Xcxx");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/XcxxDel", method = RequestMethod.GET)
	public int XcxxDel(@RequestParam("id") String id)
	{
		logInfo("XcxxDel", id);
		return xcxxService.deleteByPrimaryKey(id);
	}

	/**
	 * 审核开始2
	 * 
	 * @return
	 */
	@RequestMapping(value = "/XcxxCheck2", method = RequestMethod.GET)
	public ModelAndView XcxxCheck2()
	{
		return new ModelAndView("Xcxx/XcxxCheck2");
	}
	
	/**
	 * 审核开始
	 * 
	 * @return
	 */
	@RequestMapping(value = "/XcxxCheck", method = RequestMethod.GET)
	public ModelAndView XcxxCheck()
	{
		return new ModelAndView("Xcxx/XcxxCheck");
	}

	/**
	 * 提交集团审核意见填写页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/XcxxExcuteCheck2", method = RequestMethod.GET)
	public ModelAndView CheckXcxx2(@RequestParam("id") String id)
	{
		logInfo("XcxxExcuteCheck2", id);
		return new ModelAndView("Xcxx/XcxxExcuteCheck2", "id", id);
	}
	
	/**
	 * 审核意见填写页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/XcxxExcuteCheck", method = RequestMethod.GET)
	public ModelAndView CheckXcxx(@RequestParam("id") String id)
	{
		logInfo("XcxxExcuteCheck", id);
		return new ModelAndView("Xcxx/XcxxExcuteCheck", "id", id);
	}

	/**
	 * 保存审核数据
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/XcxxDoCheck2", method = RequestMethod.POST)
	public ModelAndView XcxxDoCheck2(Xcxx xcxx)
	{
		logInfo("XcxxDoCheck2", JSONArray.fromObject(xcxx).toString());
		xcxx.setXcxxChecktime(new Date());
		xcxx.setXcxxCheckuser("");
		if("已发布".equals(xcxx.getXcxxCheckstatus()))
			xcxx.setXcxxCheckstatus("待集团审核");
		
		xcxxService.updateByPrimaryKeySelective(xcxx);
		return new ModelAndView("success", "uri", "XcxxCheck2");
	}
	
	/**
	 * 保存审核数据
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/XcxxDoCheck", method = RequestMethod.POST)
	public ModelAndView XcxxDoCheck(Xcxx xcxx)
	{
		logInfo("XcxxDoCheck", JSONArray.fromObject(xcxx).toString());
		xcxx.setXcxxChecktime(new Date());
		xcxx.setXcxxCheckuser("");
		xcxxService.updateByPrimaryKeySelective(xcxx);
		return new ModelAndView("success", "uri", "XcxxCheck");
	}

	/**
	 * 显示审核意见
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/XcxxCheckDetailed", method = RequestMethod.GET)
	public ModelAndView XcxxCheckDetailed(@RequestParam("id") String id)
	{
		logInfo("XcxxCheckDetailed", id);
		Xcxx xcxx = new Xcxx();
		xcxx = xcxxService.selectByPrimaryKey(id);
		return new ModelAndView("Xcxx/XcxxCheckDetailed", "CheckDetailed", xcxx.getXcxxCheckmsg());
	}
}

/**
 * 招聘新闻 2014-05-16 孙建国
 */
package com.ttgis.recruit.controller;

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

import com.ttgis.recruit.domain.InterfaceResult;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.domain.ZzjgCondition;
import com.ttgis.recruit.service.ZpxwService;
import com.ttgis.recruit.utility.DateUtil;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class ZpxwController
{
	@Resource
	ZpxwService zpxwService;

	static Logger log = Logger.getLogger(ZpxwController.class);
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

	@RequestMapping(value = "/Zpxw", method = RequestMethod.GET)
	public ModelAndView Zpxw()
	{
		return new ModelAndView("Zpxw/Zpxw");
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
	@RequestMapping(value = "/LoadZpxwData", method = RequestMethod.POST)
	public List<Zpxw> LoadZpxwData(ZzjgCondition p, HttpSession session)
	{
		logInfo("LoadZpxwData", JSONArray.fromObject(p).toString());
		p.setZZJG_ID(session.getAttribute("zzjgId").toString());
		List<Zpxw> zpxws = zpxwService.selectZpxwByWhere(p);
		return zpxws;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZpxwDataCount", method = RequestMethod.POST)
	public int LoadZpxwDataCount(ZzjgCondition p, HttpSession session)
	{
		logInfo("LoadZpxwDataCount", JSONArray.fromObject(p).toString());
		p.setZZJG_ID(session.getAttribute("zzjgId").toString());
		int intCount = zpxwService.selectCount(p);
		return intCount;
	}

	@RequestMapping(value = "/ZpxwContent", method = RequestMethod.GET)
	public ModelAndView ZpxwContent(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("ZpxwContent", id);
		Zpxw zpxw = new Zpxw();
		zpxw = zpxwService.selectByPrimaryKey(id);
		return new ModelAndView("Zpxw/ZpxwContent", "zpxw", zpxw);
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ZpxwAdd", method = RequestMethod.GET)
	public ModelAndView ZpxwAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("ZpxwAdd", id);
		if (id == null)
		{
			return new ModelAndView("Zpxw/ZpxwAdd");
		} else
		{
			Zpxw zpxw = new Zpxw();
			zpxw = zpxwService.selectByPrimaryKey(id);
			return new ModelAndView("Zpxw/ZpxwAdd", "zpxw", zpxw);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/ZpxwSava", method = RequestMethod.POST)
	public ModelAndView ZpxwSava(Zpxw zpxw, HttpSession session)
	{
		logInfo("ZpxwSava", JSONArray.fromObject(zpxw).toString());
		zpxw.setZzjgId(session.getAttribute("zzjgId").toString());
		zpxw.setZpxwKqzt("");
		zpxw.setZpxwDelflag(1);
		zpxw.setZpxwAddtime(new Date());
		zpxw.setZpxwAddcompany(session.getAttribute("zzjgName").toString());
		zpxw.setZpxwId(RandomGUIDUtil.generateKey());
		zpxwService.insertSelective(zpxw);

		return new ModelAndView("success", "uri", "Zpxw");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ZpxwDel", method = RequestMethod.GET)
	public int ZpxwDel(@RequestParam("id") String id)
	{
		logInfo("ZpxwDel", id);
		return zpxwService.deleteByPrimaryKey(id);
	}

	/**
	 * 新闻置顶或者取消置顶 status:0 置顶 status：1取消置顶 ltime 置顶至日期
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setTop", method = RequestMethod.GET)
	public InterfaceResult setTop(@RequestParam("id") String id, @RequestParam("status") String status, String rtime, String ltime)
	{
		logInfo("id", id);
		logInfo("status", status);
		logInfo("ltime", ltime);
		logInfo("ltime", ltime);
		InterfaceResult rst = new InterfaceResult();
		if (id == null || id == "" || status == null || status == "")
		{
			rst.setMessage("error");
		} else
		{
			Zpxw zpxw = new Zpxw();
			zpxw.setZpxwId(id);
			zpxw.setZpxwTop(status);
			// zpxw.setTopRtime(DateUtil.string2Date(rtime,
			// DateUtil.PATTERN_DATE));
			zpxw.setTopLtime(DateUtil.string2Date(ltime + " 00:00:00.417", DateUtil.PATTERN_STANDARD));
			zpxwService.setTop(zpxw);
			rst.setMessage("success");
		}
		return rst;
	}

}

/**
 * 模板管理 2014-05-28 孙建国
 */
package com.ttgis.recruit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Mbgl;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.service.MbglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class MbglController
{
	@Resource
	MbglService mbglService;

	static Logger log = Logger.getLogger(MbglController.class);
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

	@ResponseBody
	@RequestMapping(value = "/LoadMbgl", method = RequestMethod.POST)
	public List<Mbgl> LoadMbgl(String type, HttpSession session)
	{
		logInfo("LoadMbgl", type);
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		if (type.equals("测评通知"))
			params.put("zzjgId", "");
		else
			params.put("zzjgId", session.getAttribute("zzjgId").toString());
		return mbglService.selectByMbType(params);
	}

	@RequestMapping(value = "/MbglSxxt", method = RequestMethod.GET)
	public ModelAndView MbglSxxt(String type)
	{
		return new ModelAndView("Mbgl/Sxxt");
	}

	@RequestMapping(value = "/MbglXxtz", method = RequestMethod.GET)
	public ModelAndView MbglXxtz(String type)
	{
		return new ModelAndView("Mbgl/Xxtz");
	}

	@RequestMapping(value = "/MbglZwxx", method = RequestMethod.GET)
	public ModelAndView MbglZwxx(String type)
	{
		return new ModelAndView("Mbgl/Zwxx");
	}

	@RequestMapping(value = "/MbglSxxtAdd", method = RequestMethod.GET)
	public ModelAndView MbglSxxtAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglSxxtAdd", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/SxxtAdd");
		} else
		{
			return new ModelAndView("Mbgl/SxxtAdd", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/MbglXxtzAdd", method = RequestMethod.GET)
	public ModelAndView MbglXxtzAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglXxtzAdd", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/XxtzAdd");
		} else
		{
			return new ModelAndView("Mbgl/XxtzAdd", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/MbglZwxxAdd", method = RequestMethod.GET)
	public ModelAndView MbglZwxxAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglZwxxAdd", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/ZwxxAdd");
		} else
		{
			return new ModelAndView("Mbgl/ZwxxAdd", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/MbglSxxtView", method = RequestMethod.GET)
	public ModelAndView MbglSxxtView(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglSxxtView", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/SxxtView");
		} else
		{
			return new ModelAndView("Mbgl/SxxtView", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/MbglXxtzView", method = RequestMethod.GET)
	public ModelAndView MbglXxtzView(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglXxtzView", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/XxtzView");
		} else
		{
			return new ModelAndView("Mbgl/XxtzView", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/MbglZwxxView", method = RequestMethod.GET)
	public ModelAndView MbglZwglView(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MbglZwxxView", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/ZwxxView");
		} else
		{
			return new ModelAndView("Mbgl/ZwxxView", "mbgl", mbglService.selectByPrimaryKey(id));
		}
	}

	@RequestMapping(value = "/ImportSxxtShow", method = RequestMethod.GET)
	public ModelAndView ImportSxxtShow()
	{
		return new ModelAndView("Mbgl/ImportSxxtShow");
	}

	@RequestMapping(value = "/SaveMbgl", method = RequestMethod.POST)
	public ModelAndView SaveMbgl(Mbgl mbgl, HttpSession session)
	{
		logInfo("SaveMbgl", JSONArray.fromObject(mbgl).toString());
		if (mbgl.getMbglType().equals("测评通知") && !session.getAttribute("zzjgId").equals("test001"))
		{
			return new ModelAndView("success", "uri", "orgError");
		}

		if (mbgl.getMbglId().equals(""))
		{
			mbgl.setMbglAddtime(new Date());
			mbgl.setMbglId(RandomGUIDUtil.generateKey());
			mbgl.setMbglSsdw(session.getAttribute("zzjgId").toString());
			mbglService.insertSelective(mbgl);
		} else
		{
			mbglService.updateByPrimaryKeySelective(mbgl);
		}
		if (mbgl.getMbglType().equals("筛选协同"))
			return new ModelAndView("success", "uri", "MbglSxxt");
		else if (mbgl.getMbglType().indexOf("职位") != -1)
			return new ModelAndView("success", "uri", "MbglZwxx");
		else
			return new ModelAndView("success", "uri", "MbglXxtz");
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
	@RequestMapping(value = "/LoadMbglData", method = RequestMethod.POST)
	public List<Mbgl> LoadSxxtMbglData(PageCondition pageCondition, String mbType, HttpSession session)
	{
		logInfo("LoadMbglData", JSONArray.fromObject(pageCondition).toString() + ",------" + mbType);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageNum", pageCondition.getPageNum());
		params.put("pageSize", pageCondition.getPageSize());
		params.put("currentPageSize", pageCondition.getCurrentPageSize());
		params.put("keywords", pageCondition.getKeywords());
		params.put("zzjgId", session.getAttribute("zzjgId"));
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mbType.split(",").length; i++)
		{
			list.add(mbType.split(",")[i]);
		}
		params.put("mbType", list);
		List<Mbgl> xcxxs = mbglService.selectByWhere(params);
		return xcxxs;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadMbglDataCount", method = RequestMethod.POST)
	public int LoadSxxtMbglDataCount(PageCondition pageCondition, String mbType, HttpSession session)
	{
		logInfo("LoadMbglDataCount", JSONArray.fromObject(pageCondition).toString() + ",------" + mbType);
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mbType.split(",").length; i++)
		{
			list.add(mbType.split(",")[i]);
		}
		params.put("mbType", list);
		params.put("zzjgId", session.getAttribute("zzjgId"));
		int intCount = mbglService.selectCount(params);
		return intCount;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/MbglDel", method = RequestMethod.GET)
	public int MbglDel(@RequestParam("id") String id, HttpSession session)
	{
		logInfo("MbglDel", id);
		Mbgl mbgl = mbglService.selectByPrimaryKey(id);
		if (mbgl.getMbglType().equals("测评通知") && !session.getAttribute("zzjgId").equals("test001"))
		{
			return -1;
		}
		return mbglService.deleteByPrimaryKey(id);
	}

	@ResponseBody
	@RequestMapping(value = "/loadMbContent", method = RequestMethod.POST)
	public Mbgl loadMbContent(String id)
	{
		logInfo("loadMbContent", id);
		return mbglService.selectByPrimaryKey(id);
	}
}

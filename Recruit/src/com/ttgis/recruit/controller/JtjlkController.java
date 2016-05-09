/** 
 * 集团简历库 2014-05-20 孙建国
 */
package com.ttgis.recruit.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.QueryJtjlk;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class JtjlkController
{
	@Resource
	JtjlkService jtjlkServices;
	@Resource
	ZwglService zwglService;
	@Autowired
	private ZzjgService zzjgService;
	static Logger log = Logger.getLogger(JtjlkController.class);
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

	@RequestMapping(value = "/Jtjlk", method = RequestMethod.GET)
	public ModelAndView Jtjlk()
	{
		return new ModelAndView("Jtjlk/Jtjlk");
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
	@RequestMapping(value = "/LoadJtjlkData", method = RequestMethod.POST)
	public List<Jtjlk> LoadJtjlkData(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadJtjlkData", JSONArray.fromObject(queryJtjlk).toString());
		List<Jtjlk> jtjlks = jtjlkServices.selectJtjlkByWhere(queryJtjlk);
		return jtjlks;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadJtjlkDataCount", method = RequestMethod.POST)
	public int LoadJtjlkDataCount(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadJtjlkDataCount", JSONArray.fromObject(queryJtjlk).toString());
		int intCount = jtjlkServices.selectJtjlkCount(queryJtjlk);
		return intCount;
	}

	/**
	 * 查询总条数
	 * 
	 * 
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadBdwJtjlkDataCount", method = RequestMethod.POST)
	public int LoadBdwJtjlkDataCount(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadBdwJtjlkDataCount", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		queryJtjlk.setZzjgId(zzjg.getZzjgId());
		int intCount = jtjlkServices.selectBdwJtjlkCount(queryJtjlk);
		return intCount;
	}

	/**
	 * 查询总条数 一级，二级，三级单位
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadBdwAndXsdwJtjlkDataLevel3Count", method = RequestMethod.POST)
	public int LoadBdwAndXsdwJtjlkDataLevel3Count(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadBdwAndXsdwJtjlkDataLevel3Count", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		List<Zzjg> zzjgs = new ArrayList<Zzjg>();
		List<String> zzjgList = new ArrayList<String>();
		if ("2".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 二級單位
			zzjgs = zzjgService.EJqueryUserSun2Q(zzjg.getZzjgId());
		} else if ("3".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 三級單位
			zzjgs = zzjgService.queryUserSunLevel2();
		} else if ("1".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 一级单位
			zzjgs.add(zzjg);
			queryJtjlk.setZzjgId(zzjg.getZzjgId());
		} else
		{
			// 全部
			zzjgs = zzjgService.selectAll();
		}
		for (Zzjg z : zzjgs)
		{
			zzjgList.add(z.getZzjgId());
		}

		queryJtjlk.setZzjgIds(zzjgList);

		int intCount = jtjlkServices.LoadBdwAndXsdwJtjlkDataLevel3Count(queryJtjlk);
		return intCount;
	}

	/**
	 * 二级单位及三级单位
	 * 
	 * @param queryJtjlk
	 * @param session
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/LoadBdwAndXsdwJtjlkDataCount", method = RequestMethod.POST)
	public int LoadBdwAndXsdwJtjlkDataCount(QueryJtjlk queryJtjlk, HttpSession session)
	{
		// logInfo("LoadBdwAndXsdwJtjlkDataCount", JSONArray
		// .fromObject(queryJtjlk).toString());
		// Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		// queryJtjlk.setZzjgId(zzjg.getZzjgId());

		logInfo("LoadBdwAndXsdwJtjlkDataCount", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		List<Zzjg> zzjgs = new ArrayList<Zzjg>();
		List<String> zzjgList = new ArrayList<String>();
		if ("1".equals(queryJtjlk.getZzjgQueryNum()))
		{
			zzjgs.add(zzjg);
		} else if ("2".equals(queryJtjlk.getZzjgQueryNum()))
		{
			zzjgs = zzjgService.EJqueryUserSun2Q(zzjg.getZzjgId());
		} else
		{
			zzjgs = zzjgService.EJqueryUserSun(zzjg.getZzjgId());
			zzjgs.add(zzjg);
		}
		for (Zzjg z : zzjgs)
		{
			zzjgList.add(z.getZzjgId());
		}
		queryJtjlk.setZzjgIds(zzjgList);

		int intCount = jtjlkServices.selectBdwAndXsdwJtjlkCount(queryJtjlk);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/JtjlkAdd", method = RequestMethod.GET)
	public ModelAndView JtjlkAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("JtjlkAdd", id);
		if (id == null)
		{
			return new ModelAndView("jtjlk/JtjlkAdd");
		} else
		{
			Jtjlk jtjlk = new Jtjlk();
			jtjlk = jtjlkServices.selectByPrimaryKey(id);
			return new ModelAndView("jtjlk/jtjlkAdd", "position", jtjlk);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param jtjlk
	 * @return
	 */
	@RequestMapping(value = "/jtjlkSava", method = RequestMethod.POST)
	public ModelAndView jtjlkSava(Jtjlk jtjlk)
	{
		logInfo("jtjlkSava", JSONArray.fromObject(jtjlk).toString());
		jtjlk.setJtjlkDelflag((long) 1);
		if (jtjlk.getJtjlkId().equals(""))
		{
			jtjlk.setZzjgId("test001");
			jtjlk.setJtjlkAddtime(new Date());
			jtjlk.setJtjlkId(RandomGUIDUtil.generateKey());
			jtjlkServices.insertSelective(jtjlk);
		} else
		{
			jtjlkServices.updateByPrimaryKeySelective(jtjlk);
		}
		return new ModelAndView("success", "uri", "jtjlk");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jtjlkDel", method = RequestMethod.GET)
	public int jtjlkDel(@RequestParam("id") String id)
	{
		logInfo("jtjlkDel", id);
		return jtjlkServices.deleteByPrimaryKey(id);
	}

	@ResponseBody
	@RequestMapping(value = "/CollectionJtjlk", method = RequestMethod.POST)
	public String CollectionJtjlk(String jtjlkId, HttpSession session) throws UnsupportedEncodingException
	{
		logInfo("CollectionJtjlk", jtjlkId);
		Jtjlk jtjlk = jtjlkServices.selectByPrimaryKey(jtjlkId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", jtjlk.getJtjlkUserid());
		map.put("zzjgId", session.getAttribute("zzjgId").toString());
		List<Jtjlk> jtjlks = jtjlkServices.selectCanCollection(map);
		if (jtjlks.size() == 0)
		{
			jtjlk.setJtjlkOldid(jtjlk.getJtjlkId());
			jtjlk.setJtjlkZw("");
			jtjlk.setJtjlkId(RandomGUIDUtil.generateKey());
			jtjlk.setZzjgId(session.getAttribute("zzjgId").toString());
			jtjlk.setJtjlkJlzt("收藏");
			jtjlk.setJtjlkMszt("未安排面试");
			jtjlk.setJtjlkCpcj("未通知");
			jtjlk.setJtjlkCpjg("未测评");
			jtjlk.setJtjlkApcpgwlb(null);
			jtjlk.setJtjlkAddtime(new Date());
			jtjlk.setJtjlkPositionId("");
			jtjlk.setJtjlkGwlb("");
			jtjlk.setJtjlkLy("HR");
			jtjlk.setJtjlkJtjlkflag(1);
			jtjlk.setJtjlkZzlygw("");
			jtjlk.setJtjlkZzlygwId("");
			jtjlkServices.insertSelective(jtjlk);
			return "success";
		} else
		{
			System.out.println(escape(jtjlks.get(0).getJtjlkJlzt()));
			return "error_" + escape(jtjlks.get(0).getJtjlkJlzt());
		}
	}

	public static String escape(String src)
	{
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++)
		{
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256)
			{
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else
			{
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/Apzw", method = RequestMethod.POST)
	public String Apzw(String jtjlkId, String selZw, HttpSession session)
	{
		logInfo("Apzw", jtjlkId + "," + selZw);
		String[] jtjlkIds = jtjlkId.split(",");
		PositionWithBLOBs p = zwglService.selectByPrimaryKey(selZw);
		for (int i = 0; i < jtjlkIds.length; i++)
		{
			Jtjlk jtjlk = jtjlkServices.selectByPrimaryKey(jtjlkIds[i]);
			jtjlk.setJtjlkZw(p.getPositionName());
			jtjlk.setJtjlkPositionId(p.getPositionId());
			jtjlk.setJtjlkGwlb(p.getPositionType());
			jtjlkServices.updateByPrimaryKeySelective(jtjlk);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/selJtjlk", method = RequestMethod.GET)
	public List<Jtjlk> selJtjlk(@RequestParam("idCard") String idCard)
	{
		logInfo("selJtjlk", idCard);
		List<Jtjlk> jtjlks = new ArrayList<Jtjlk>();
		jtjlks = jtjlkServices.selectBySfzh(idCard);
		return jtjlks;
	}

}

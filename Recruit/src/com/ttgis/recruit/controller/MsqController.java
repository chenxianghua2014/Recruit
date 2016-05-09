/**
 * 面试圈 2014-07-12 孙建国
 */
package com.ttgis.recruit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.ttgis.recruit.domain.Msq;
import com.ttgis.recruit.domain.Msqgl_detailed;
import com.ttgis.recruit.domain.QueryMsq;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.service.MsjgService;
import com.ttgis.recruit.service.MsqService;
import com.ttgis.recruit.service.TxlService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class MsqController
{
	@Resource
	MsqService msqService;
	@Resource
	UserService userService;
	@Resource
	TxlService txlService;
	@Resource
	MsjgService msjgService;

	static Logger log = Logger.getLogger(MsqController.class);
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

	@RequestMapping(value = "/Msqgl", method = RequestMethod.GET)
	public ModelAndView Msqgl()
	{
		return new ModelAndView("Zpgl/Msqgl");
	}

	@ResponseBody
	@RequestMapping(value = "/GetMsqInfo", method = RequestMethod.POST)
	public Msq GetMsqInfo(String id)
	{
		logInfo("GetMsqInfo", id);
		Msq msq = msqService.selectByPrimaryKey(id);
		String strName = "";
		for (Userinfo u : userService.selectByMsqId(msq.getMsqId()))
		{
			strName += u.getUserName() + ",";
		}
		if (strName.length() > 0)
			msq.setMsqMsgNames(strName.substring(0, strName.length() - 1));
		return msq;
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
	@RequestMapping(value = "/LoadMsqData", method = RequestMethod.POST)
	public List<Msq> LoadXcxxData(QueryMsq qMsq, String keywords, HttpSession session)
	{
		logInfo("LoadMsqData", JSONArray.fromObject(qMsq).toString() + ",------keywords");
		qMsq.setZzjgId(session.getAttribute("zzjgId").toString());
		qMsq.setMsqName(keywords);
		List<Msq> msqs = new ArrayList<Msq>();
		for (Msq msq : msqService.selectByWhere(qMsq))
		{
			String strName = "";
			for (Userinfo u : userService.selectByMsqId(msq.getMsqId()))
			{
				strName += u.getUserName() + ",";
			}
			if (strName.length() > 0)
				msq.setMsqMsgNames(strName.substring(0, strName.length() - 1));
			msqs.add(msq);
		}
		return msqs;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadMsqDataCount", method = RequestMethod.POST)
	public int LoadMsqDataCount(QueryMsq qMsq, String keywords, HttpSession session)
	{
		logInfo("LoadMsqDataCount", JSONArray.fromObject(qMsq).toString() + ",------keywords");
		qMsq.setZzjgId(session.getAttribute("zzjgId").toString());
		int intCount = msqService.selectCount(qMsq);
		return intCount;
	}

	@ResponseBody
	@RequestMapping(value = "/GetAllMsq", method = RequestMethod.POST)
	public List<Msq> GetAllMsq(HttpSession session)
	{
		logInfo("GetAllMsq", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("zzjgId", session.getAttribute("zzjgId").toString());
		List<Msq> msqs = msqService.selectByType(map);
		return msqs;
	}

	@ResponseBody
	@RequestMapping(value = "/GetUserByMsq", method = RequestMethod.POST)
	public List<Userinfo> GetUserByMsq(String msqId, HttpSession session)
	{
		logInfo("GetUserByMsq", msqId);
		List<Userinfo> userinfos = userService.selectByMsqId(msqId);
		return userinfos;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/MsqAdd", method = RequestMethod.GET)
	public ModelAndView MsqAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MsqAdd", id);
		if (id == null)
		{
			return new ModelAndView("Zpgl/MsqAdd");
		} else
		{
			Msq msq = msqService.selectByPrimaryKey(id);
			ModelAndView mv = new ModelAndView();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			mv.addObject("msq", msq);
			List<String> listId = new ArrayList<String>();
			List<String> listName = new ArrayList<String>();
			for (Userinfo u : userService.selectByMsqId(id))
			{
				listId.add(u.getUserId());
				listName.add(u.getUserName());
			}
			map.put("id", listId);
			map.put("name", listName);
			mv.addObject("msg", map);
			return new ModelAndView("Zpgl/MsqAdd", "msq", mv);
		}
	}

	@RequestMapping(value = "/MsqSave", method = RequestMethod.POST)
	public ModelAndView MsqSave(Msq msq, HttpSession session, HttpServletRequest request)
	{
		logInfo("MsqSave", JSONArray.fromObject(msq).toString());
		Msq msqOld = msqService.selectByPrimaryKey(msq.getMsqId());
		// 正常添加
		if (msqOld == null)
		{
			String strMsqId = RandomGUIDUtil.generateKey();
			// 添加用户
			for (int i = 0; i < msq.getMsqMsgNames().split(",").length; i++)
			{
				String strId = RandomGUIDUtil.generateKey();
				Random r = new Random();
				String loginId = "";
				String loginPswd = "";
				loginId = r.nextInt(1000000) + "";
				loginPswd = r.nextInt(1000000) + "";
				Userinfo userinfo = new Userinfo();
				userinfo.setUserId(strId);
				userinfo.setUserAddtime(new Date());
				userinfo.setUserDelflag((long) 1);
				userinfo.setUserIdcard(loginId);
				userinfo.setUserJlid(strMsqId);
				userinfo.setUserSfls("是");
				userinfo.setUserPassword(loginPswd);
				userinfo.setUserName(msq.getMsqMsgNames().split(",")[i]);
				userService.insertSelective(userinfo);
			}
			msq.setMsqAddtime(new Date());
			msq.setMsqDelflag(1);
			msq.setMsqId(strMsqId);
			msq.setZzjgId(session.getAttribute("zzjgId").toString());
			msqService.insertSelective(msq);
		} else
		{
			List<Userinfo> userinfos = userService.selectByMsqId(msq.getMsqId());
			String userName = "";
			for (Userinfo u : userinfos)
			{
				userName += u.getUserName() + ",";
			}
			msqOld.setMsqMsgNames(userName.substring(0, userName.length() - 1));

			for (int i = 0; i < msqOld.getMsqMsgNames().split(",").length; i++)
			{
				if (msq.getMsqMsgNames().indexOf(msqOld.getMsqMsgNames().split(",")[i]) == -1)
				{
					// 删除面试官记录
					Map<String, String> params = new HashMap<String, String>();
					params.put("msqId", msqOld.getMsqId());
					params.put("msgName", msqOld.getMsqMsgNames().split(",")[i]);
					msjgService.deleteByWhere(params);

					// 删除用户
					Map<String, String> map = new HashMap<String, String>();
					map.put("userName", msqOld.getMsqMsgNames().split(",")[i]);
					map.put("msqId", msqOld.getMsqId());
					userService.delLsUser(map);
					map.clear();
				}
			}
			for (int i = 0; i < msq.getMsqMsgNames().split(",").length; i++)
			{
				if (msqOld.getMsqMsgNames().indexOf(msq.getMsqMsgNames().split(",")[i]) == -1)
				{
					String strId = RandomGUIDUtil.generateKey();
					Random r = new Random();
					String loginId = "";
					String loginPswd = "";
					loginId = r.nextInt(1000000) + "";
					loginPswd = r.nextInt(1000000) + "";
					Userinfo userinfo = new Userinfo();
					userinfo.setUserId(strId);
					userinfo.setUserAddtime(new Date());
					userinfo.setUserDelflag((long) 1);
					userinfo.setUserIdcard(loginId);
					userinfo.setUserJlid(msqOld.getMsqId());
					userinfo.setUserSfls("是");
					userinfo.setUserPassword(loginPswd);
					userinfo.setUserName(msq.getMsqMsgNames().split(",")[i]);
					userService.insertSelective(userinfo);
					// 添加面试官记录
					List<Msqgl_detailed> msqgl_detaileds = msjgService.selectMsInfoByMsq(msq.getMsqId());
					for (Msqgl_detailed msqgl_detailed : msqgl_detaileds)
					{
						msqgl_detailed.setMsqglDetailedAddtime(new Date());
						msqgl_detailed.setMsqglDetailedDelflag((long) 1);
						msqgl_detailed.setMsqglDetailedId(RandomGUIDUtil.generateKey());
						msqgl_detailed.setMsqglDetailedMsg(msq.getMsqMsgNames().split(",")[i]);
						msjgService.insertSelective(msqgl_detailed);
					}
				}
			}
			msqService.updateByPrimaryKeySelective(msq);
		}
		return new ModelAndView("success", "uri", "Msqgl");
	}

	@ResponseBody
	@RequestMapping(value = "/LsyhDel", method = RequestMethod.POST)
	public int LsyhDel(HttpServletRequest request)
	{
		String msqId = request.getParameter("id");
		logInfo("LsyhDel", msqId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("msqId", msqId);
		Msq msq = msqService.selectByPrimaryKey(msqId);
		msq.setMsqMsgNames(msq.getMsqMsgNames() + "(已全部删除)");
		msqService.updateByPrimaryKeySelective(msq);
		return userService.delLsUser(map);
	}

	@ResponseBody
	@RequestMapping(value = "/MsqDel", method = RequestMethod.POST)
	public int MsqDel(HttpServletRequest request)
	{
		String msqId = request.getParameter("id");
		logInfo("MsqDel", msqId);
		Msq msq = msqService.selectByPrimaryKey(msqId);
		msq.setMsqDelflag(0);
		msqService.updateByPrimaryKeySelective(msq);
		return 1;
	}
}

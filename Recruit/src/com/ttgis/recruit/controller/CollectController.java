/**
 * 我的收藏 2014-06-26 孙建国
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Collect_position;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.service.CollectionService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class CollectController
{
	@Resource
	CollectionService collectionService;
	@Resource
	ZwglService zwglService;
	
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

	@RequestMapping(value = "/MyCollection", method = RequestMethod.GET)
	public ModelAndView MyCollection(HttpSession session)
	{
		logInfo("MyCollection", "");
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		else
		{
			Collect_position collect_position = new Collect_position();
			collect_position.setCollectPositionUserId(userinfo.getUserId());
			List<Collect_position> collect_positions = collectionService.selectByWhere(collect_position);
			List<PositionWithBLOBs> positions = new ArrayList<PositionWithBLOBs>();
			List<String> lstPostionIds = new ArrayList<String>();
			for (Collect_position c : collect_positions)
			{
				lstPostionIds.add(c.getPositionId());
			}
			if (lstPostionIds.size() > 0)
			{
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("lstPostionIds", lstPostionIds);
				positions = zwglService.selectByPrimaryKeys(params);
			}
			return new ModelAndView("Main/MyCollection", "positions", positions);
		}

	}

	@RequestMapping(value = "/DoCollection", method = RequestMethod.GET)
	public ModelAndView DoCollection(HttpSession session, @RequestParam(value = "id", required = false) String id)
	{
		logInfo("DoCollection", id);
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		Collect_position collect_positionCheck = new Collect_position();
		collect_positionCheck.setPositionId(id);
		collect_positionCheck.setCollectPositionUserId(userinfo.getUserId());
		if (collectionService.selectByWhere(collect_positionCheck).size() == 0)
		{
			Collect_position collect_position = new Collect_position();
			collect_position.setCollectPositionAddtime(new Date());
			collect_position.setCollectPositionDelflag((long) 1);
			collect_position.setCollectPositionId(RandomGUIDUtil.generateKey());
			collect_position.setCollectPositionUserId(userinfo.getUserId());
			collect_position.setPositionId(id);
			collectionService.insertSelective(collect_position);
			return new ModelAndView("Main/ShowAlert", "uri", "SearchPositionSuccess");
		} else
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SearchPositionError");
		}
	}

	@RequestMapping(value = "/CancelPosition", method = RequestMethod.GET)
	public ModelAndView CancelPosition(@RequestParam(value = "id", required = false) String id, HttpSession session)
	{
		logInfo("CancelPosition", id);
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("positionId", id);
		params.put("userId", userinfo.getUserId());
		collectionService.deleteByPrimaryKey(params);
		return new ModelAndView("Main/ShowAlert", "uri", "MyCollection");
	}

}

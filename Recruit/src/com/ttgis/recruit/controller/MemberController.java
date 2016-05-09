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

import com.ttgis.recruit.domain.Hydj;
import com.ttgis.recruit.domain.Member;
import com.ttgis.recruit.domain.QueryMember;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.HydjService;
import com.ttgis.recruit.service.MemberService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。MemberController
 * @创建人：范井龙
 * @日期：
 * @描述： 会员管理
 * @版本号：
 */
@Controller
public class MemberController
{
	@Autowired
	private MemberService memberService;
	@Autowired
	private ZzjgService zzjgService;
	@Autowired
	private HydjService hydjService;
	
	static Logger log = Logger.getLogger(MemberController.class);
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
	 * 跳转页面方法
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView Member(Model model)
	{
		return new ModelAndView("hygl/hygl");
	}

	/**
	 * 分页查询（已经审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadMemberData", method = RequestMethod.POST)
	public List<Member> LoadMemberData(QueryMember qm)
	{
		logInfo("LoadMemberData", JSONArray.fromObject(qm).toString());
		List<Member> memberList = memberService.selectByWhere(qm);
		return memberList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadMemberDataCount", method = RequestMethod.POST)
	public int LoadMemberDataCount(QueryMember qm)
	{
		logInfo("LoadMemberDataCount", JSONArray.fromObject(qm).toString());
		int intCount = memberService.selectCount(qm);
		return intCount;
	}

	/**
	 * 添加个人博文
	 * 
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/memberAdd", method = RequestMethod.GET)
	public String memberAdd(String memberId, Model model)
	{
		logInfo("memberAdd", memberId);
		List<Zzjg> userFOList = zzjgService.queryUserFO();
		List<Zzjg> userSunList = zzjgService.queryUserSun();
		model.addAttribute("userFOList", userFOList);
		model.addAttribute("userSunList", userSunList);
		return "hygl/hyglAddOne";
	}

	@RequestMapping(value = "/getUserAdd", method = RequestMethod.GET)
	public String getUserAdd(String zzjgId, Model model)
	{
		logInfo("getUserAdd", zzjgId); 
		Zzjg zzjg = new Zzjg();
		List<Hydj> hydjList = hydjService.queryHydj();
		zzjg = zzjgService.selectByPrimaryKey(zzjgId);
		model.addAttribute("hydjList", hydjList);
		model.addAttribute("zzjg", zzjg);
		return "hygl/hyglAddTwo";
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/memberSave", method = RequestMethod.POST)
	public ModelAndView memberSave(Member member)
	{
		logInfo("memberSave", JSONArray.fromObject(member).toString());
		if (member.getMemberId().equals(""))
		{
			member.setMemberDelflag("1");
			member.setMemberYfkye("0");
			member.setMemberId(RandomGUIDUtil.generateKey());
			memberService.insertSelective(member);
		} else
		{
			memberService.updateByPrimaryKeySelective(member);
		}
		return new ModelAndView("redirect:/member");
	}

	/**
	 * 删除信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delMember", method = RequestMethod.GET)
	public String delMember(String memberId)
	{
		logInfo("delMember", memberId); 
		memberService.delMember(memberId);
		return "redirect:/member";
	}

	// -------------------------------预付费余额不足-------------------------------------------------------
	@RequestMapping(value = "/bzmember", method = RequestMethod.GET)
	public ModelAndView BzMember(Model model)
	{
		return new ModelAndView("hygl/yffyebz");
	}

	/**
	 * 分页查询（费用不足）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bzLoadMemberData", method = RequestMethod.POST)
	public List<Member> bzLoadMemberData(QueryMember qm)
	{
		logInfo("bzLoadMemberData", JSONArray.fromObject(qm).toString());
		List<Member> memberList = memberService.bzselectByWhere(qm);
		return memberList;
	}

	@ResponseBody
	@RequestMapping(value = "/bzLoadMemberDataCount", method = RequestMethod.POST)
	public int bzLoadMemberDataCount(QueryMember qm)
	{
		logInfo("bzLoadMemberDataCount", JSONArray.fromObject(qm).toString());
		int intCount = memberService.bzselectCount(qm);
		return intCount;
	}

}

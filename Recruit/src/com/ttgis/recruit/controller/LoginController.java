/**
 * 董再兴 LoginController.java 2013年7月12日
 */
package com.ttgis.recruit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.ttgis.recruit.domain.Bbs;
import com.ttgis.recruit.domain.QueryJtjlk;
import com.ttgis.recruit.domain.QueryXcxx;
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ArticleService;
import com.ttgis.recruit.service.BbsService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.MemberService;
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XcxxService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZpxwService;
import com.ttgis.recruit.utility.FilterStr;

/**
 * @author 董再兴 用户登录
 */
@Controller
public class LoginController extends BaseController
{
	@Resource
	ZpxwService zpxwService;
	@Resource
	XcxxService xcxxService;
	@Resource
	JtjlkService jtjlkService;
	@Autowired
	UserService yhbservice;
	@Resource
	ReviewService reviewService;
	@Resource
	ArticleService articleService;
	@Resource
	BbsService bbsService;
	@Resource
	MemberService memberService;
	@Resource
	UserService userService;
	@Resource
	XxtzService xxtzService;
	
	static Logger log = Logger.getLogger(LoginController.class);
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

	@RequestMapping(value = "/zc", method = RequestMethod.GET)
	public ModelAndView zc()
	{
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/Index")
	public ModelAndView Index(HttpSession session)
	{
		return new ModelAndView("Admin/Index", "loginSession", session.getAttribute("loginSession"));
	}

	@RequestMapping(value = "/LeftAdmin", method = RequestMethod.GET)
	public ModelAndView LeftAdmin()
	{
		return new ModelAndView("Admin/LeftAdmin");
	}

	@RequestMapping(value = "/RightAdmin", method = RequestMethod.GET)
	public ModelAndView RightAdmin(HttpSession session, Zzjg zzjg)
	{
		logInfo("RightAdmin", JSONArray.fromObject(zzjg).toString());
		zzjg = (Zzjg) session.getAttribute("loginSession");
		String strZzjgId = session.getAttribute("zzjgId").toString();
		ModelAndView mv = new ModelAndView();
		// ----------------- 招聘日程 ---------------------------
		Zpxw zpxw = zpxwService.selectZprc(strZzjgId);
		mv.addObject("zpxw", zpxw);
		// ----------------- 代办事项 ---------------------------

		// 宣传信息待审核
		QueryXcxx qXcxx = new QueryXcxx();
		qXcxx.setXcxxAdduser(strZzjgId);
		qXcxx.setXcxxCheckstatus("待审核");
		int intZpxwCount = xcxxService.selectCount(qXcxx);
		mv.addObject("xcxx", intZpxwCount);
		// 社区待审核信息
		int articleDshCount = articleService.articleDshCount();
		mv.addObject("articleDshCount", articleDshCount);
		// 消息通知发送失败
		int msgFailed = xxtzService.selectFailedMsg(strZzjgId);
		mv.addObject("msgFailed", msgFailed);
		// 面试圈待删除帐号
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int waitDeleteCount = userService.selectWaitDelete(formatter.format(date)).size();
		mv.addObject("waitDeleteCount", waitDeleteCount);

		// ----------------- 过程管理---------------------------
		QueryJtjlk queryJtjlk = new QueryJtjlk();
		// 新的简历
		List<String> strJlType_Xdjl = new ArrayList<String>();
		strJlType_Xdjl.add("未筛选");
		queryJtjlk.setJtjlkZt(strJlType_Xdjl);
		queryJtjlk.setZzjgId(strZzjgId);
		int intXdjlCount = jtjlkService.selectCount(queryJtjlk);
		mv.addObject("xdjl", intXdjlCount);
		// 新收测评成绩
		List<String> strJlType_Cpcj = new ArrayList<String>();
		strJlType_Cpcj.add("已考试");
		queryJtjlk.setJtjlkZt(strJlType_Cpcj);
		queryJtjlk.setZzjgId(strZzjgId);
		int intCpCount = jtjlkService.selectCount(queryJtjlk);
		mv.addObject("cpjl", intCpCount);
		// 新收面试结果
		List<String> strJlType_Msjg = new ArrayList<String>();
		strJlType_Msjg.add("已安排面试");
		strJlType_Msjg.add("一面结束");
		strJlType_Msjg.add("二面结束");
		strJlType_Msjg.add("终面结束");
		queryJtjlk.setJtjlkZt(strJlType_Msjg);
		queryJtjlk.setZzjgId(strZzjgId);
		int intMsCount = jtjlkService.selectCount(queryJtjlk);
		mv.addObject("msjl", intMsCount);
		// 预付款余额不足
		int hyCount = memberService.hyCount();
		mv.addObject("hyCount", hyCount);
		// ----------------- 社区动态---------------------------
		// 查询 社区动态评论
		List<Review> reviewList = reviewService.queryByZzjgDwmc(zzjg.getZzjgDwmc());
		for (int i = 0; i < reviewList.size(); i++)
		{
			String bbsId = reviewList.get(i).getBbsId();
			Bbs bbs = bbsService.queryByBbsId(bbsId);
			reviewList.get(i).setBbsNr(FilterStr.FilterHtmlImg(bbs.getBbsNr()));
		}
		mv.addObject("reviewList", reviewList);

		return new ModelAndView("Admin/RightAdmin", "mv", mv);
	}

	@RequestMapping(value = "/error1", method = RequestMethod.GET)
	public ModelAndView error1()
	{
		return new ModelAndView("error");
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Checklogin(Userinfo user, HttpSession session, Model model)
	{
		logInfo("login", JSONArray.fromObject(user).toString());
		log.info("帐号" + user.getUserIdcard() + "密码" + user.getUserPassword());

		if (user.getUserIdcard().trim().equals("") || user.getUserPassword().trim().equals(""))
		{
			return "login/login";
		}
		Userinfo loginUser = yhbservice.CheckLogin(user);
		if (loginUser == null)
		{
			String message = "用户名或者密码错误！";
			model.addAttribute("message", message);
			return "login/login";
		}
		try
		{
			session.setAttribute("loginSession", loginUser);
			return "redirect:/Index";
		} catch (Exception e)
		{
			System.err.println("login操作失败!!");
			e.printStackTrace();
		}
		return "redirect:/Index";
	}

	/**
	 * 获取当前登录用户权限
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetCurrentUserRole", method = RequestMethod.GET)
	public int GetCurrentUserRole(HttpSession session)
	{
		return 1;
	}

	/**
	 * 获取当前登录用户权限
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRule", method = RequestMethod.GET)
	public int getRules(HttpSession session)
	{
		return 2;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showMap()
	{
		return new ModelAndView("/index");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session)
	{
		logInfo("logout", "");
		session.invalidate();
		return "login/login";
	}

}

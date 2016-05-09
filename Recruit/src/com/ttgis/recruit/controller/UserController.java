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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Hydj;
import com.ttgis.recruit.domain.QueryUser;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.service.HydjService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.utility.MD5;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller.UserController
 * @创建人： 范井龙
 * @日期：
 * @描述： 用户管理
 * @版本号：
 */
@Controller
public class UserController
{
	@Resource
	private UserService userService;
	@Resource
	private HydjService hydjService;

	static Logger log = Logger.getLogger(UserController.class);
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

	@RequestMapping(value = "/userIndex1", method = RequestMethod.GET)
	public String userIndex(Model model)
	{
		logInfo("userIndex1", "");
		List<Userinfo> userFOList = userService.queryUserFO();
		model.addAttribute("userFOList", userFOList);
		return "user/userIndex";
	}

	@RequestMapping(value = "/userLeft1", method = RequestMethod.GET)
	public ModelAndView userLeft()
	{
		logInfo("userLeft1", "");
		return new ModelAndView("user/userLeft");
	}

	@RequestMapping(value = "/userAdd", method = RequestMethod.GET)
	public String UserAdd(String userId, Model model)
	{
		logInfo("userAdd", userId);
		if (userId == null)
		{
			List<Userinfo> userFOList = userService.queryUserFO();
			model.addAttribute("userFOList", userFOList);
			return "user/userAdd";
		} else
		{
			Userinfo user = new Userinfo();
			user = userService.selectByPrimaryKey(userId);
			List<Userinfo> userFOList = userService.queryUserFO();
			model.addAttribute("user", user);
			model.addAttribute("userFOList", userFOList);
			return "user/userAdd";
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/userSave1", method = RequestMethod.POST)
	public ModelAndView UserSave(Userinfo user)
	{
		logInfo("userSave1", JSONArray.fromObject(user).toString());
		if (user.getUserId().equals(""))
		{
			user.setSfjy("0");
			user.setUserAddtime(new Date());
			user.setUserId(RandomGUIDUtil.generateKey());
			user.setUserDelflag((long) 1);
			userService.insertSelective(user);
		} else
		{
			userService.updateByPrimaryKeySelective(user);
		}
		return new ModelAndView("redirect:/userAdd");
	}

	/**
	 * 删除信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delUser1", method = RequestMethod.GET)
	public String delUser(String userId)
	{
		logInfo("delUser1", userId);
		userService.deleteByPrimaryKey(userId);
		return "user/userAdd";
	}

	/**
	 * 查询用户菜单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryUserFO1", method = RequestMethod.GET)
	public String queryUserFO(Model model)
	{
		logInfo("queryUserFO1", "");
		List<Userinfo> userFOList = userService.queryUserFO();
		List<Userinfo> userSunList = userService.queryUserSun();
		model.addAttribute("userFOList", userFOList);
		model.addAttribute("userSunList", userSunList);
		return "user/userLeft";
	}

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/czmm1", method = RequestMethod.GET)
	public String czmm(Userinfo user)
	{
		logInfo("czmm1", JSONArray.fromObject(user).toString());
		user.setUserPassword("123456");
		userService.updateByPrimaryKeySelective(user);
		return "redirect:/userIndex";
	}

	@RequestMapping(value = "/getUser1", method = RequestMethod.GET)
	public String getUser(Userinfo user, HttpSession session, Model model)
	{
		logInfo("getUser1", JSONArray.fromObject(user).toString());
		user = (Userinfo) session.getAttribute("loginSession");
		Userinfo userinfo = new Userinfo();
		userinfo = userService.selectByPrimaryKey(user.getUserId());
		model.addAttribute("userinfo", userinfo);
		return "user/updUser";
	}

	@RequestMapping(value = "/updUser1", method = RequestMethod.POST)
	public ModelAndView updUser(Userinfo user)
	{
		logInfo("updUser1", JSONArray.fromObject(user).toString());
		userService.updateByPrimaryKeySelective(user);
		return new ModelAndView("redirect:/getUser");
	}

	// --------------------------------用户添加为会员操作-------------------------------------------------
	// --------------------------------用户添加为会员操作-------------------------------------------------

	/**
	 * 分页查询
	 * 
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadUserinfoData", method = RequestMethod.POST)
	public List<Userinfo> LoadUserinfoData(QueryUser qu)
	{
		logInfo("LoadUserinfoData", JSONArray.fromObject(qu).toString());
		List<Userinfo> userList = userService.hyselectByWhere(qu);
		return userList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadUserinfoDataCount", method = RequestMethod.POST)
	public int LoadUserinfoDataCount(QueryUser qu)
	{
		logInfo("LoadUserinfoDataCount", JSONArray.fromObject(qu).toString());
		int intCount = userService.hyselectCount(qu);
		return intCount;
	}

	@RequestMapping(value = "/userAddHy", method = RequestMethod.GET)
	public ModelAndView userAddHy()
	{
		logInfo("userAddHy", "");
		return new ModelAndView("hygl/userAddHy");
	}

	@RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
	public String queryUserById(Userinfo user, Model model)
	{
		logInfo("queryUserById", JSONArray.fromObject(user).toString());
		List<Hydj> hydjList = hydjService.queryHydj();
		user = userService.selectByPrimaryKey(user.getUserId());
		model.addAttribute("hydjList", hydjList);
		model.addAttribute("user", user);
		return "hygl/queryUserById";
	}

	// --------------------------------前台用户操作-------------------------------------------------
	// --------------------------------前台用户操作-------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(String userId)
	{
		logInfo("register", userId);
		if (userId == null)
		{
			return new ModelAndView("register");
		} else
		{
			Userinfo user = userService.selectByPrimaryKey(userId);
			return new ModelAndView("updRegister", "user", user);
		}
	}

	/**
	 * 注册
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUserIdcard", method = RequestMethod.POST)
	public boolean checkUserIdcard(Userinfo user)
	{
		logInfo("checkUserIdcard", JSONArray.fromObject(user).toString());
		int count = userService.checkUserIdcard(user.getUserIdcard());
		if (count > 0)
		{
			return true;
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public boolean checkUserName(Userinfo user)
	{
		logInfo("checkUserName", JSONArray.fromObject(user).toString());
		if (user.getUserEmail() != null && user.getUserEmail().toString().trim().length() > 0)
		{
			int count = userService.checkUserName(user.getUserEmail());
			if (count > 0)
			{
				return true;
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/checkUserTelephone", method = RequestMethod.POST)
	public boolean checkUserTelephone(Userinfo user)
	{
		logInfo("checkUserTelephone", JSONArray.fromObject(user).toString());
		if (user.getUserTelephone() != null && user.getUserTelephone().toString().trim().length() > 0)
		{
			int count = userService.checkUserTelephone(user.getUserTelephone());
			if (count > 0)
			{
				return true;
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/checkUserSfzh", method = RequestMethod.POST)
	public boolean checkUserSfzh(Userinfo user)
	{
		logInfo("checkUserSfzh", JSONArray.fromObject(user).toString());
		if (user.getUserSfzh() != null && user.getUserSfzh().toString().trim().length() > 0)
		{
			int count = userService.checkUserSfzh(user.getUserSfzh());
			if (count > 0)
			{
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(Userinfo user) throws ParseException
	{
		logInfo("registerUser", JSONArray.fromObject(user).toString());
		int countUserSfzh = userService.checkUserSfzh(user.getUserSfzh());
		int countUserIdcard = userService.checkUserIdcard(user.getUserIdcard());
		if (countUserSfzh > 0 || countUserIdcard > 0)
		{
			return new ModelAndView("success", "uri", "againRegisterUser");
		} else
		{
			if (user.getUserId() == null || user.getUserId().equals(""))
			{
				user.setSfjy("0");
				user.setUserPassword(MD5.getMD5ofStr(user.getUserPassword()));
				user.setUserAddtime(new Date());
				user.setUserId(RandomGUIDUtil.generateKey());
				user.setUserDelflag((long) 1);
				userService.insertSelective(user);
				return new ModelAndView("success", "uri", "registerUser");
			} else
			{
				user.setUserPassword(MD5.getMD5ofStr(user.getUserPassword()));
				userService.updateByPrimaryKeySelective(user);
				return new ModelAndView("success", "uri", "updRegisterUser");
			}
		}
	}

	/**
	 * 前台登陆
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/UserLogin", method = RequestMethod.POST)
	public ModelAndView UserLogin(Userinfo user, HttpSession session, Model model, String page, String loginName)
	{
		logInfo("UserLogin", JSONArray.fromObject(user).toString() + ",------" + page + ",------" + loginName);
		if (loginName != "" || loginName != null)
		{
			String pwdMd5 = MD5.getMD5ofStr(user.getUserPassword());
			user.setUserIdcard(loginName);
			user.setUserPassword(pwdMd5);
			Userinfo loginUser = userService.CheckLogin(user);
			if (loginUser == null)
			{
				user.setUserSfzh(loginName);
				Userinfo loginUser2 = userService.CheckLogin(user);
				if (loginUser2 == null)
				{
					try
					{
						Long ll = Long.parseLong(loginName);
						user.setUserTelephone(ll);
					} catch (Exception e)
					{
						user.setUserTelephone((long) 0);
					}
					Userinfo loginUser3 = userService.CheckLogin(user);
					if (loginUser3 == null)
					{
						String message = "用户名或者密码错误！";
						model.addAttribute("message", message);
						return new ModelAndView("Main/" + page, "result", model);
					} else
					{
						try
						{
							session.setAttribute("userLoginSession", loginUser3);
							session.setAttribute("userId", loginUser3.getUserId());
							if (page.equals("LoginFrmFirst"))
								page = "LoginFrm";
							return new ModelAndView("Main/" + page, "result", model);
						} catch (Exception e)
						{
							System.err.println("login操作失败!!");
							logInfo("UserLogin", JSONArray.fromObject(user).toString() + ",------" + page + ",------" + loginName + "******login操作失败!!");
							e.printStackTrace();
						}
					}
				} else
				{
					try
					{
						session.setAttribute("userLoginSession", loginUser2);
						session.setAttribute("userId", loginUser2.getUserId());
						if (page.equals("LoginFrmFirst"))
							page = "LoginFrm";
						return new ModelAndView("Main/" + page, "result", model);
					} catch (Exception e)
					{
						System.err.println("login操作失败!!");
						logInfo("UserLogin", JSONArray.fromObject(user).toString() + ",------" + page + ",------" + loginName + "******login操作失败!!");
						e.printStackTrace();
					}
				}
			} else
			{
				try
				{
					session.setAttribute("userLoginSession", loginUser);
					session.setAttribute("userId", loginUser.getUserId());
					if (page.equals("LoginFrmFirst"))
						page = "LoginFrm";
					return new ModelAndView("Main/" + page, "result", model);
				} catch (Exception e)
				{
					logInfo("UserLogin", JSONArray.fromObject(user).toString() + ",------" + page + ",------" + loginName + "******login操作失败!!");
					System.err.println("login操作失败!!");
					e.printStackTrace();
				}
			}
		}
		return new ModelAndView("Main/" + page, "result", model);
	}

	@RequestMapping(value = "/UserLogout", method = RequestMethod.GET)
	public ModelAndView UserLogout(HttpSession session)
	{
		logInfo("UserLogout", "");
		session.removeAttribute("userLoginSession");
		return new ModelAndView("Main/ShowAlert", "uri", "Main");
	}

	@RequestMapping(value = "/userInfoManage", method = RequestMethod.GET)
	public ModelAndView userInfoManage(HttpSession session)
	{
		logInfo("userInfoManage", "");
		return new ModelAndView("userInfo/userInfo", "uri", "Main");
	}

	@ResponseBody
	@RequestMapping(value = "/ResetPassword", method = RequestMethod.POST)
	public int ResetPassword(Userinfo user)
	{
		logInfo("ResetPassword", JSONArray.fromObject(user).toString());
		user.setUserPassword("E10ADC3949BA59ABBE56E057F20F883E");
		return userService.updateByPrimaryKeySelective(user);
	}

	@ResponseBody
	@RequestMapping(value = "/LoadUserInfoDataCount", method = RequestMethod.POST)
	public int LoadUserInfoDataCount(QueryUser q)
	{
		logInfo("LoadUserInfoDataCount", JSONArray.fromObject(q).toString());
		return userService.selectCount(q);
	}

	@ResponseBody
	@RequestMapping(value = "/LoadUserInfoData", method = RequestMethod.POST)
	public List<Userinfo> LoadUserInfoData(QueryUser q)
	{
		logInfo("LoadUserInfoData", JSONArray.fromObject(q).toString());
		return userService.selectByWhere(q);
	}

}

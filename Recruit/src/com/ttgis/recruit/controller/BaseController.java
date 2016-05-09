package com.ttgis.recruit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ttgis.recruit.domain.Zzjg;

/**
 * @author 董再兴 登录后的Controller基类
 */
@SessionAttributes("loginSession")
@Controller
public class BaseController
{
	private HttpSession loginSession;

	/**
	 * @return 取得登录后的Session
	 */
	public HttpSession getLoginSession()
	{
		return loginSession;
	}

	/**
	 * @param 设置登录后的Session
	 */
	public void setLoginSession(HttpSession loginSession)
	{
		this.loginSession = loginSession;
	}

	/**
	 * 从session中取得用户帐号实体
	 * 
	 * @return Zzjg
	 */
	public Zzjg getUserinfo()
	{
		Object o = getLoginSession().getAttribute("loginSession");
		if (o == null)
			return null;
		else
			return (Zzjg) o;
	}

	public String getZzjgId()
	{
		Object o = getLoginSession().getAttribute("loginSession");
		if (o == null)
			return null;
		else
			return ((Zzjg) o).getZzjgId();
	}
}
/**
 * 董再兴 LoginSessionInterceptor.java 2013年7月13日
 */
package com.ttgis.recruit.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 董再兴 过滤loginAccount
 */
public class LoginSessionInterceptor extends HandlerInterceptorAdapter
{

	/*
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		if (request.getSession().getAttribute("loginSession") == null)
		{
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">window.top.location.href=/login</script>");
			out.print(builder.toString());
			out.close();
			return false;
		} else
		{
			return super.preHandle(request, response, handler);
		}
	}

}

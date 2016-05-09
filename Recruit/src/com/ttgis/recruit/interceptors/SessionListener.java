package com.ttgis.recruit.interceptors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{

	@Override
	public void sessionCreated(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	// 销毁用户登陆后创建的session
	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		ServletContext context = se.getSession().getServletContext();
		String gids = "";
		if (context.getAttribute("gids") == null || context.getAttribute("gids").equals(null))
			return;

		gids = context.getAttribute("gids").toString();

		String sid = se.getSession().getId();

		if (gids.indexOf(sid) > -1)
		{
			gids = gids.replaceAll(sid, "");
			gids = gids.replaceAll(",,", ",");
			if (gids == ",")
				gids = gids.replaceAll(",", "");
			context.setAttribute("gids", gids);
		} else
		{
			return;
		}
	}
}

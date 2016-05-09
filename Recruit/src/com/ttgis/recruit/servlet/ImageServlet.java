package com.ttgis.recruit.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException
	{
		super.init();
	}

	public void service(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException
	{
		String savePath = "upload/";

		String realPath = context.getRealPath("/" + savePath);// 实际路径

		File saveFiledir = new File(realPath); // 在该实际路径下实例化一个文件
		if (!saveFiledir.exists())
		{
			saveFiledir.mkdirs();
		}
		// 文件已存在（上传了同名的文件）
		// File saveFile = new File(realPath + "/" + name);
		// if (chunk == 0 && saveFile.exists())
		// {
		// saveFile.delete();
		// saveFile = new File(realPath + "/" + name);
		// }
		// try
		// {
		// this.copy(upload.getInputStream(), saveFile);
		// } catch (IOException e)
		// {
		// e.printStackTrace();
		// }
		// ;
		//
		// Map map = new HashMap();
		// map.put("chunk", chunk);
		// map.put("chunks", chunks);
		String string = "32132132";
		System.out.println(string);
	}

	public void destroy()
	{

	}
}

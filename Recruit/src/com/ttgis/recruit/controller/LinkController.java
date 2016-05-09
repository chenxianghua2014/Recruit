package com.ttgis.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Link;
import com.ttgis.recruit.service.LinkService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

@Controller
public class LinkController
{

	@Autowired
	private LinkService linkService;

	static Logger log = Logger.getLogger(LinkController.class);
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

	/**
	 * 返回所有友情链接
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/linklist", method = RequestMethod.GET)
	public ModelAndView LinkList()
	{
		logInfo("linklist", "");
		List<Link> list = linkService.selectAll();
		return new ModelAndView("links/linklist", "ll", list);
	}

	/**
	 * 返回所有友情链接
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LinkListJson", method = RequestMethod.POST)
	public List<Link> LinkListJson()
	{
		logInfo("LinkListJson", "");
		List<Link> list = linkService.selectAll();
		return list;
	}

	/**
	 * 添加友情链接
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tolinkadd", method = RequestMethod.GET)
	public String ToLinkAdd()
	{
		logInfo("tolinkadd", "");
		return "links/linkadd";
	}

	/**
	 * 添加友情链接
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/linkadd", method = RequestMethod.POST)
	public ModelAndView LinkAdd(HttpServletRequest request, Link link, @RequestParam("linkpic") MultipartFile linkpic)
	{
		logInfo("linkadd", "");
		if (link == null)
			return new ModelAndView("links/linkadd");

		if (link.getLinkCat() == null || link.getLinkCat().equals("") || link.getLinkl() == null || link.getLinkl().equals("") || link.getLinkName() == null || link.getLinkName().equals(""))
			return new ModelAndView("links/linkadd");

		link.setLinkId(RandomGUIDUtil.generateKey());

		if (!linkpic.isEmpty())
		{
			String path = request.getRealPath("/") + "images\\link\\";
			String fileName = RandomGUIDUtil.generateKey() + "." + getFileExt(linkpic.getOriginalFilename());

			try
			{
				// String tomcatPath = request.getContextPath() + "/WebContent";
				// // 得到保存的路径
				FileCopyUtils.copy(linkpic.getBytes(), new File(path + "/" + fileName));// FileCopyUtils来自org.springframework.util.FileCopyUtils

				link.setLinkPic("images/link/" + fileName);

			} catch (IOException e)
			{
				logInfo("linkadd", "******Error");
				e.printStackTrace();
			}
		}

		if (linkService.insert(link) > 0)
			return LinkList();
		else
			return LinkList();
	}

	/**
	 *  * 获取文件扩展名  * @param file  * @return  
	 */
	private String getFileExt(String fileName)
	{

		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
		{
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else
		{
			return "";
		}
	}

	/**
	 * 删除友情链接
	 * 
	 * @return
	 */
	@RequestMapping(value = "/linkdel", method = RequestMethod.GET)
	public ModelAndView LinkDel(@RequestParam("linkid") String linkid)
	{
		logInfo("linkdel", linkid);
		if (linkid == null || linkid.trim().equals(""))
			return new ModelAndView("links/linklist");
		linkid = linkid.replaceAll("'", "");

		if (linkService.deleteByPrimaryKey(linkid) > 0)
			return LinkList();
		else
			return LinkList();
	}

	/**
	 * 更新友情链接
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/linkupdate", method = RequestMethod.POST)
	public ModelAndView LinkUpdate(HttpServletRequest request, Link link, @RequestParam("linkpic") MultipartFile linkpic)
	{
		logInfo("linkupdate", "******Error");
		if (link == null)
			return new ModelAndView("links/linkadd");

		if (link.getLinkCat() == null || link.getLinkCat().equals("") || link.getLinkl() == null || link.getLinkl().equals("") || link.getLinkName() == null || link.getLinkName().equals(""))
			return new ModelAndView("links/linkadd");

		Link linkEntity = linkService.selectByPrimaryKey(link.getLinkId());

		if (linkEntity.equals(null))
			return new ModelAndView("links/linkadd");

		if (!linkpic.isEmpty())
		{
			String path = request.getRealPath("/") + "images\\link\\";
			String fileName = RandomGUIDUtil.generateKey() + "." + getFileExt(linkpic.getOriginalFilename());

			try
			{
				// String tomcatPath = request.getContextPath() + "/WebContent";
				// // 得到保存的路径
				FileCopyUtils.copy(linkpic.getBytes(), new File(path + "/" + fileName));// FileCopyUtils来自org.springframework.util.FileCopyUtils

				link.setLinkPic("images/link/" + fileName);

			} catch (IOException e)
			{
				logInfo("linkupdate", "******Error");
				e.printStackTrace();
			}

		}

		if (linkService.insert(linkEntity) > 0)
			return LinkList();
		else
			return LinkList();
	}

}

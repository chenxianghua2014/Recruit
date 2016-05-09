/** 
 * 职位管理 2014-05-16 孙建国
 */
package com.ttgis.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Position;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.QueryJtjlk;
import com.ttgis.recruit.domain.QueryZw;
import com.ttgis.recruit.domain.Zpzy;
import com.ttgis.recruit.domain.Zylb;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.plugin.WordReader;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class ZwglController
{
	@Resource
	ZwglService zwglServices;
	@Resource
	ZzjgService zzjgService;
	@Resource
	JtjlkService jtjlkService;

	static Logger log = Logger.getLogger(ZwglController.class);
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

	@RequestMapping(value = "/Zwgl", method = RequestMethod.GET)
	public ModelAndView Zwgl(Model model, HttpSession session)
	{
		logInfo("GetZprc", "");
		List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
		List<Zzjg> zzjgSunList = zzjgService.queryUserSun();
		model.addAttribute("zzjgFOList", zzjgFOList);
		model.addAttribute("zzjgSunList", zzjgSunList);
		return new ModelAndView("Zwgl/Zwgl");
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
	@RequestMapping(value = "/LoadZwglData", method = RequestMethod.POST)
	public List<PositionWithBLOBs> LoadZwglData(QueryZw queryZw, HttpSession session)
	{
		logInfo("LoadZwglData", JSONArray.fromObject(queryZw).toString());
		String strZzjgId = session.getAttribute("zzjgId").toString();
		if (!strZzjgId.equals("test001"))
		{
			List<String> zzjgIds = new ArrayList<String>();
			// 二级单位
			if (session.getAttribute("zzjgSjdw").equals("test001"))
			{
				List<Zzjg> zzjgs = zzjgService.selectBySjdw(strZzjgId);
				for (Zzjg zzjg : zzjgs)
				{
					zzjgIds.add(zzjg.getZzjgId());
				}
				zzjgIds.add(strZzjgId);
				queryZw.setZzjgId(zzjgIds);
			}
			// 三级单位
			else
			{
				zzjgIds.add(strZzjgId);
				queryZw.setZzjgId(zzjgIds);
			}
		} else
		{
			queryZw.setZzjgId(null);
		}
		List<PositionWithBLOBs> Zwgls = zwglServices.selectByWhere(queryZw);
		for (PositionWithBLOBs zwgl : Zwgls)
		{
			if (zwgl.getZzjgId().equals(strZzjgId))
				zwgl.setPositionCanEdit("yes");
			else
			{
				zwgl.setPositionCanEdit("no");
			}
		}
		return Zwgls;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZwglDataCount", method = RequestMethod.POST)
	public int LoadZwglDataCount(QueryZw queryZw, HttpSession session)
	{
		logInfo("LoadZwglDataCount", JSONArray.fromObject(queryZw).toString());
		String strZzjgId = session.getAttribute("zzjgId").toString();
		if (!strZzjgId.equals("test001"))
		{
			List<String> zzjgIds = new ArrayList<String>();
			// 二级单位
			if (session.getAttribute("zzjgSjdw").equals("test001"))
			{
				List<Zzjg> zzjgs = zzjgService.selectBySjdw(session.getAttribute("zzjgId").toString());
				for (Zzjg zzjg : zzjgs)
				{
					zzjgIds.add(zzjg.getZzjgId());
				}
				zzjgIds.add(session.getAttribute("zzjgId").toString());
				queryZw.setZzjgId(zzjgIds);
			}
			// 三级单位
			else
			{
				zzjgIds.add(session.getAttribute("zzjgId").toString());
				queryZw.setZzjgId(zzjgIds);
			}
		} else
		{
			queryZw.setZzjgId(null);
		}
		int intCount = zwglServices.selectCount(queryZw);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ZwglAdd", method = RequestMethod.GET)
	public ModelAndView AddZwgl(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("ZwglAdd", id);
		if (id == null)
		{
			return new ModelAndView("Zwgl/ZwglAdd");
		} else
		{
			PositionWithBLOBs positionWithBLOBs = new PositionWithBLOBs();
			positionWithBLOBs = zwglServices.selectByPrimaryKey(id);
			return new ModelAndView("Zwgl/ZwglAdd", "position", positionWithBLOBs);
		}
	}
	
	/**
	 * 职位信息浏览
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ZwglView", method = RequestMethod.GET)
	public ModelAndView ZwglView(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("ZwglView", id);
		if (id == null)
		{
			return new ModelAndView("Zwgl/ZwglAdd");
		} else
		{
			PositionWithBLOBs positionWithBLOBs = new PositionWithBLOBs();
			positionWithBLOBs = zwglServices.selectByPrimaryKey(id);
			return new ModelAndView("Zwgl/ZwglView", "position", positionWithBLOBs);
		}
	}

	/**
	 * 保存 修改操作
	 * @param Zwgl
	 * @return
	 */
	@RequestMapping(value = "/ZwglSava", method = RequestMethod.POST)
	public ModelAndView ZwglSava(PositionWithBLOBs positionWithBLOBs, HttpSession session)
	{
		logInfo("ZwglSava", JSONArray.fromObject(positionWithBLOBs).toString());
		positionWithBLOBs.setPositionDelflag(1);
		if (positionWithBLOBs.getPositionId().equals(""))
		{
			positionWithBLOBs.setZzjgId(session.getAttribute("zzjgId").toString());
			positionWithBLOBs.setPositionZsdw(session.getAttribute("zzjgName").toString());
			positionWithBLOBs.setPositionAddtime(new Date());
			positionWithBLOBs.setPositionId(RandomGUIDUtil.generateKey());
			zwglServices.insertSelective(positionWithBLOBs);
		} else
		{
			zwglServices.updateByPrimaryKeySelective(positionWithBLOBs);
		}
		return new ModelAndView("success", "uri", "Zwgl");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ZwglDel", method = RequestMethod.GET)
	public int ZwglDel(@RequestParam("id") String id)
	{
		logInfo("ZwglDel", id);
		QueryJtjlk q = new QueryJtjlk();
		q.setJtjlkPositionId(id);
		if (jtjlkService.selectByWhere(q).size() > 0)
			return -1;
		else
			return zwglServices.deleteByPrimaryKey(id);
	}

	/**
	 * 导入职责 要求页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ImportShow", method = RequestMethod.GET)
	public ModelAndView ImportShow()
	{
		return new ModelAndView("Zwgl/ImportShow");
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/DoFileUpload", method = RequestMethod.POST)
	public ModelAndView DoFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		logInfo("DoFileUpload", "");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		@SuppressWarnings("deprecation")
		String ctxPath = request.getRealPath("/") + "attach\\";
		// 创建文件夹
		File file = new File(ctxPath);
		if (!file.exists())
		{
			file.mkdirs();
		}
		String fileName = null;
		// 存到session里所有的文件名
		String strFileNames = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
		{
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			strFileNames = fileName;
			File uploadFile = new File(ctxPath + strFileNames);
			try
			{
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e)
			{
				e.printStackTrace();
				logInfo("DoFileUpload", "******Error");
			}
		}
		request.getSession().setAttribute("FilePath", ctxPath + strFileNames);
		return new ModelAndView("Zwgl/ImportShow", "CloseWindow", "window.close();");
	}

	/**
	 * 读取文件
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/LoadFile", method = RequestMethod.POST)
	public ModelAndView LoadFile(HttpServletRequest request, HttpServletResponse response, String name) throws IOException
	{
		logInfo("LoadFile", name);
		String strPath;
		if (!name.equals(""))
			strPath = request.getRealPath("/") + "attach\\" + name + ".doc";
		else
			strPath = request.getSession().getAttribute("FilePath").toString();
		String wordReader = new WordReader().readWord(strPath);
		request.getSession().setAttribute("FilePath", null);
		return new ModelAndView("Zwgl/ImportShow", "wordReader", wordReader);
	}

	@ResponseBody
	@RequestMapping(value = "/LoadZylb", method = RequestMethod.POST)
	public List<Zylb> LoadZylb()
	{
		logInfo("LoadZylb", "");
		return zwglServices.LoadZylb();
	}

	@ResponseBody
	@RequestMapping(value = "/LoadZpzy", method = RequestMethod.POST)
	public List<Zpzy> LoadZpzy(String type)
	{
		logInfo("LoadZpzy", type);
		return zwglServices.LoadZpzy(type);
	}

	@ResponseBody
	@RequestMapping(value = "/loadZwmc", method = RequestMethod.POST)
	public List<Position> loadZwmc(HttpSession session)
	{
		logInfo("loadZwmc", "");
		return zwglServices.selectByCompany(session.getAttribute("zzjgId").toString());
	}

}

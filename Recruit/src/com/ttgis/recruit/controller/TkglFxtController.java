package com.ttgis.recruit.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.QueryTkglFxt;
import com.ttgis.recruit.domain.tkglFxt;
import com.ttgis.recruit.service.TkglFxtService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。TkglFxtController
 * @创建人： 范井龙
 * @日期： 2014-06-23
 * @描述：分析题小题题库
 * @版本号：
 */
@Controller
public class TkglFxtController
{
	public static String ToDBC(String strSttg)
	{
		strSttg = strSttg.replaceAll(" ", " ");
		strSttg = strSttg.replaceAll("  ", "  ");
		strSttg = strSttg.replaceAll("   ", "   ");
		strSttg = strSttg.replaceAll("    ", "    ");
		strSttg = strSttg.replaceAll("     ", "     ");
		strSttg = strSttg.replaceAll("      ", "     ");
		strSttg = strSttg.replaceAll("       ", "      ");
		strSttg = strSttg.replaceAll("        ", "       ");
		strSttg = strSttg.replaceAll("         ", "       ");
		strSttg = strSttg.replaceAll("          ", "       ");
		return strSttg;
	}

	@Resource
	TkglFxtService tkglFxtService;

	static Logger log = Logger.getLogger(TkglFxtController.class);
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

	@RequestMapping(value = "/TkglFxt", method = RequestMethod.GET)
	public ModelAndView TkglFxt()
	{
		return new ModelAndView("Tkgl/TkglFxt");
	}

	@RequestMapping(value = "/pldrTkglFxt", method = RequestMethod.GET)
	public ModelAndView pldrTkglFxt()
	{
		return new ModelAndView("Tkgl/pldrTkglFxt");
	}

	@RequestMapping(value = "/importTkglFxt", method = RequestMethod.POST)
	// 设置访问路径
	public ModelAndView importTkglFxt(
	// 模型参数 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{
		logInfo("importTkglFxt", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				tkglFxt tkgl = new tkglFxt();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				tkgl.setId(RandomGUIDUtil.generateKey());
				tkgl.setStbh(row.getCell(0) + ""); // 试题编号
				tkgl.setStlx(row.getCell(1) + ""); // 试题类型
				String strSttg = ToDBC(row.getCell(2).toString());
				String strStxxa = ToDBC(row.getCell(3).toString());
				String strStxxb = ToDBC(row.getCell(4).toString());
				String strStxxc = ToDBC(row.getCell(5).toString());
				String strStxxd = ToDBC(row.getCell(6).toString());
				tkgl.setSttg(strSttg); // 试题题干
				tkgl.setStxxa(strStxxa); // 答案 A
				tkgl.setStxxb(strStxxb); // 答案 B
				tkgl.setStxxc(strStxxc); // 答案 C
				tkgl.setStxxd(strStxxd); // 答案 D
				tkgl.setStda(row.getCell(7) + ""); // 正确答案
				tkglFxtService.insertkgl(tkgl);
			}
		} catch (IOException e)
		{
			logInfo("importTkglFxt", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("success", "uri", "importTkglFxt");
	}

	// ------------------------------------------------------------------------------------------------------------------------
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
	@RequestMapping(value = "/LoadTkglFxtData", method = RequestMethod.POST)
	public List<tkglFxt> LoadTkglData(QueryTkglFxt qt)
	{
		logInfo("LoadTkglFxtData", JSONArray.fromObject(qt).toString());
		List<tkglFxt> tkgl = tkglFxtService.selectByWhere(qt);
		return tkgl;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadTkglFxtDataCount", method = RequestMethod.POST)
	public int LoadTkglFxtDataCount(QueryTkglFxt qt)
	{
		logInfo("LoadTkglFxtDataCount", JSONArray.fromObject(qt).toString());
		int intCount = tkglFxtService.selectCount(qt);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/TkglFxtAdd", method = RequestMethod.GET)
	public String TkglFxtAdd(String id, Model model)
	{
		logInfo("TkglFxtAdd", id);
		if (id == null)
		{
			return "Tkgl/TkglFxtAdd";
		} else
		{
			tkglFxt tkgl = new tkglFxt();
			tkgl = tkglFxtService.selectByPrimaryKey(id);
			model.addAttribute("tkgl", tkgl);
			return "Tkgl/TkglFxtAdd";
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/TkglFxtSava", method = RequestMethod.POST)
	public ModelAndView TkglFxtSava(tkglFxt tkgl)
	{
		logInfo("TkglFxtSava", JSONArray.fromObject(tkgl).toString());
		if (tkgl.getId().equals(""))
		{

			tkgl.setId(RandomGUIDUtil.generateKey());
			tkglFxtService.insertkgl(tkgl);

		} else
		{
			tkglFxtService.updateByPrimaryKeySelective(tkgl);
		}
		return new ModelAndView("redirect:/TkglFxt");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/delTkglFxt", method = RequestMethod.GET)
	public String delTkglFxt(String id)
	{
		logInfo("delTkglFxt", id);
		tkglFxtService.deleteByPrimaryKey(id);
		return "Tkgl/TkglFxt";
	}

}

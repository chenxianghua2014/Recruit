/**
 * 宣传信息 2014-05-14 孙建国
 */
package com.ttgis.recruit.controller;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.Txl;
import com.ttgis.recruit.service.TxlService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class TxlController
{
	@Resource
	TxlService txlService;

	static Logger log = Logger.getLogger(TxlController.class);
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

	@RequestMapping(value = "/Txl", method = RequestMethod.GET)
	public ModelAndView Txl()
	{
		return new ModelAndView("Mbgl/Txl");
	}

	@RequestMapping(value = "/ImportTxlShow", method = RequestMethod.GET)
	public ModelAndView ImportTxlShow()
	{
		return new ModelAndView("Mbgl/ImportTxlShow");
	}

	@RequestMapping(value = "/DoFileUploadTxl", method = RequestMethod.POST)
	public ModelAndView DoFileUploadTxl(@RequestParam("excel") MultipartFile excel, HttpSession session)
	{
		logInfo("DoFileUploadTxl", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			int i = 0;
			while (rows.hasNext())
			{
				Row row = (Row) rows.next(); // 获取一行数据
				i++;
				if (i <= 1)
					continue;
				Txl txl = new Txl();
				txl.setTxlAddtime(new Date());
				txl.setTxlDelflag((long) 1);
				txl.setTxlName(row.getCell(1).toString());
				txl.setTxlPhone(row.getCell(2).toString());
				txl.setTxlEmail(row.getCell(3).toString());
				txl.setTxlDepartment(row.getCell(4).toString());
				txl.setTxlPosition(row.getCell(5).toString());
				txl.setZzjgId(session.getAttribute("zzjgId").toString());
				txl.setTxlId(RandomGUIDUtil.generateKey());
				txlService.insertSelective(txl);
			}

		} catch (IOException e)
		{
			e.printStackTrace();
			logInfo("DoFileUploadTxl", "******Error");
		}
		return new ModelAndView("Mbgl/ImportTxlShow", "CloseWindow", "window.close();");
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
	@RequestMapping(value = "/LoadTxlData", method = RequestMethod.POST)
	public List<Txl> LoadTxlData(PageCondition pageCondition, HttpSession session)
	{
		logInfo("LoadTxlData", JSONArray.fromObject(pageCondition).toString());
		pageCondition.setOrderField(session.getAttribute("zzjgId").toString());
		List<Txl> txls = txlService.selectByWhere(pageCondition);
		return txls;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadTxlDataCount", method = RequestMethod.POST)
	public int LoadTxlDataCount(PageCondition pageCondition, HttpSession session)
	{
		logInfo("LoadTxlDataCount", JSONArray.fromObject(pageCondition).toString());
		pageCondition.setOrderField(session.getAttribute("zzjgId").toString());
		int intCount = txlService.selectCount(pageCondition);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/TxlAdd", method = RequestMethod.GET)
	public ModelAndView TxlAdd(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("TxlAdd", id);
		if (id == null)
		{
			return new ModelAndView("Mbgl/TxlAdd");
		} else
		{
			Txl txl = new Txl();
			txl = txlService.selectByPrimaryKey(id);
			return new ModelAndView("Mbgl/TxlAdd", "txl", txl);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/TxlSava", method = RequestMethod.POST)
	public ModelAndView TxlSava(Txl txl, HttpSession session)
	{
		logInfo("TxlSava", JSONArray.fromObject(txl).toString());
		if (txl.getTxlId().equals(""))
		{
			txl.setTxlAddtime(new Date());
			txl.setTxlDelflag((long) 1);
			txl.setZzjgId(session.getAttribute("zzjgId").toString());
			txl.setTxlId(RandomGUIDUtil.generateKey());
			txlService.insertSelective(txl);
		} else
		{
			txlService.updateByPrimaryKeySelective(txl);
		}
		return new ModelAndView("success", "uri", "Txl");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/TxlDel", method = RequestMethod.GET)
	public int TxlDel(@RequestParam("id") String id)
	{
		logInfo("TxlDel", id);
		return txlService.deleteByPrimaryKey(id);
	}
}

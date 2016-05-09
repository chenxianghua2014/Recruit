package com.ttgis.recruit.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 批量导入用户信息
 * 
 * @author Administrator
 * 
 */
@Controller
public class ImportExcel
{
	@Resource
	private ZzjgService zzjgService;

	static Logger log = Logger.getLogger(JkController.class);
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

	@RequestMapping(value = "/importExcel", method = RequestMethod.GET)
	// 设置访问路径
	public String importExcelDemo(Model model)
	{
		logInfo("importExcel", "");
		List<Zzjg> userFOList = zzjgService.queryUserFO();
		model.addAttribute("userFOList", userFOList);
		return "user/importExcel";
	}

	public static String changeTeltoRight(Double telphone)
	{
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(0);
		String str = formatter.format(telphone);
		String[] str2 = str.split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < str2.length; i++)
		{
			buffer.append(str2[i]);
		}
		String result = buffer.toString();
		return result;
	}

	@RequestMapping(value = "/importExcelDemo", method = RequestMethod.POST)
	// 设置访问路径
	public String importExcelDemo(String zzjgSjdw, Model model, // 模型参数
																// 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{

		logInfo("importExcelDemo", zzjgSjdw);
		HSSFWorkbook workbook = null;
		List<Zzjg> userFOList = zzjgService.queryUserFO();
		model.addAttribute("userFOList", userFOList);
		List<Zzjg> list = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			list = new ArrayList<Zzjg>();
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				Zzjg zzjg = new Zzjg();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				zzjg.setZzjgSjdw(zzjgSjdw);
				zzjg.setZzjgAddtime(new Date());
				zzjg.setZzjgId(RandomGUIDUtil.generateKey());
				zzjg.setZzjgDelflag((long) 1);
				zzjg.setZzjgDwmc(row.getCell(0) + ""); // 单位名称
				zzjg.setZzjgDwjc(row.getCell(1) + ""); // 单位简称
				zzjg.setZzjgDwdm(row.getCell(2) + ""); // 单位代码
				zzjg.setZzjgDwzh(row.getCell(3) + ""); // 单位账号
				zzjg.setZzjgDwfzr(row.getCell(4) + ""); // 单位负责人
				zzjg.setZzjgZplxr(row.getCell(5) + ""); // 招聘联系人
				zzjg.setZzjgGzdh(row.getCell(6) + ""); // 工作电话
				Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
				Matcher m = p_str.matcher((row.getCell(7).toString()));
				if ((row.getCell(7) + "").equals("") || (row.getCell(7) + "").equals(null) || m.find() && m.group(0).equals((row.getCell(7).toString())))
				{
					zzjg.setZzjgLxrdh(row.getCell(7).toString());
				} else
				{
					zzjg.setZzjgLxrdh(changeTeltoRight(Double.parseDouble(row.getCell(7).toString()))); // 联系电话
				}
				zzjg.setZzjgLxremail(row.getCell(8) + ""); // 邮箱
				zzjg.setZzjgZhmm("123456");

				zzjgService.insertSelective(zzjg);
				// Jsxxb.setJsxm( row.getCell(1)+"");
				// Jsxxb.setXb(row.getCell(2)+"");
				list.add(zzjg); // 将实体装入list集合类
			}

		} catch (IOException e)
		{
			logInfo("importExcelDemo", zzjgSjdw + "******Error");
			e.printStackTrace();
		}
		model.addAttribute("list", list);// 将数据装入Spring 值栈中共前台获取
		return "user/importExcel"; // 返回地址
	}

}

package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.InterfaceResult;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.QueryBmgl;
import com.ttgis.recruit.domain.QueryKcgl;
import com.ttgis.recruit.domain.Resume;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.service.ResumeService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.DateConvertUtils;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @类名： com.ttgis.recruit.controller。BmglController
 * @创建人： 范井龙
 * @日期：
 * @描述： 报名管理
 * @版本号：
 */
@Controller
public class BmglController
{
	@Autowired
	private BmglService bmglService;
	@Autowired
	private ZzjgService zzjgService;
	@Autowired
	private KsxcglService ksxcglService;
	@Autowired
	private JtjlkService jtjlkService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private KcglService kcglService;
	@Autowired
	private UserService userService;

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

	/**
	 * 跳转页面方法
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bmgl", method = RequestMethod.GET)
	public ModelAndView bmgl()
	{
		QueryKcgl qk = new QueryKcgl();
		qk.setCurrentPageSize(1000000);
		qk.setPageNum(1);
		qk.setPageSize(1000000);
		return new ModelAndView("bmgl/bmgl", "kcgls", kcglService.selectByWhere(qk));
	}

	/**
	 * 分页查询（已经审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/BmglData", method = RequestMethod.POST)
	public List<Bmgl> BmglData(QueryBmgl qb)
	{
		logInfo("BmglData", JSONArray.fromObject(qb).toString());
		List<Bmgl> bmglList = bmglService.selectByWhere(qb);
		return bmglList;
	}

	@ResponseBody
	@RequestMapping(value = "/BmglDataCount", method = RequestMethod.POST)
	public int BmglDataCount(QueryBmgl qb)
	{
		logInfo("BmglDataCount", JSONArray.fromObject(qb).toString());
		int intCount = bmglService.selectCount(qb);
		return intCount;
	}

	@RequestMapping(value = "/addBmgl", method = RequestMethod.GET)
	public String addBmgl(Model model, String zzjgId)
	{
		logInfo("addBmgl", zzjgId);
		List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
		List<Zzjg> zzjgSunList = zzjgService.queryUserSun();
		model.addAttribute("zzjgFOList", zzjgFOList);
		model.addAttribute("zzjgSunList", zzjgSunList);
		model.addAttribute("zzId", zzjgId);
		return "bmgl/addBmgl";
	}

	@RequestMapping(value = "/saveBmgl", method = RequestMethod.POST)
	public ModelAndView saveBmgl(Bmgl bmgl)
	{
		logInfo("saveBmgl", JSONArray.fromObject(bmgl).toString());
		bmgl.setBmglAddtime(new Date());
		bmgl.setBmglId(RandomGUIDUtil.generateKey());
		bmgl.setBmglDelflag((long) 1);
		bmglService.insertSelective(bmgl);
		return new ModelAndView("redirect:/bmgl");
	}

	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public ModelAndView Registration(@RequestParam(value = "kcId", required = false) String kcId, @RequestParam(value = "id", required = false) String id, HttpSession session) throws ParseException
	{
		logInfo("Registration", kcId + "," + id);
		Userinfo user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		Kcgl kcgl = kcglService.selectByPrimaryKey(kcId);
		if (kcgl.getKcglSyrl() <= 0)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "RegistrationFull");
		}
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(id);
		Bmgl b = new Bmgl();
		b.setBmglBkgw(jtjlk.getJtjlkApcpgwlb());
		b.setBmglSfzh(user.getUserSfzh());
		b.setBmglJtjlkid(jtjlk.getJtjlkId());
		List<Bmgl> bmglSel = bmglService.selectIsExist(b);
		if (bmglSel.size() == 0)
		{
			Bmgl bmgl = new Bmgl();
			Resume resume = resumeService.selectByPrimaryKeySelectiveById(user.getUserId());
			Zzjg zzjg = new Zzjg();
			zzjg = zzjgService.selectByPrimaryKey(jtjlk.getZzjgId());

			Map<String, String> p = new HashMap<String, String>();
			p.put("jtjlkId", id);
			bmglService.deleteByWhere(p);

			bmgl.setBmglAddtime(new Date());
			bmgl.setBmglBkdw(zzjg.getZzjgDwmc());
			bmgl.setBmglBkdwdm(zzjg.getZzjgDwdm());
			bmgl.setBmglBkgw(jtjlk.getJtjlkApcpgwlb());
			bmgl.setBmglByxx(jtjlk.getJtjlkByxx());
			bmgl.setBmglDelflag((long) 1);
			bmgl.setBmglId(RandomGUIDUtil.generateKey());
			bmgl.setBmglJtjlkid(id);
			bmgl.setBmglKcglId(kcId);
			bmgl.setBmglKscj("未测评");
			bmgl.setBmglKssj(kcgl.getKcglKssjStart().trim() + " - " + kcgl.getKcglKssjEnd().trim());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			bmgl.setBmglKsrq(format.parse(kcgl.getKcglKsrq()));
			bmgl.setBmglKsxm(jtjlk.getJtjlkName());
			bmgl.setBmglLxdh(user.getUserTelephone());
			bmgl.setBmglKsxb(user.getUserSex());
			bmgl.setBmglSfqr("否");
			bmgl.setBmglSfzh(resume.getResumeSfzh());
			bmgl.setBmglKsdd(kcgl.getKcglDz());
			bmgl.setBmglTszg("");
			bmgl.setBmglPrintflag((long) 1);
			bmglService.insertSelective(bmgl);

			// Bmgl bmgl = bmglService.queryBmglById(ksxcgl.getKsxmId());
			// Kcgl k = kcglService.selectByPrimaryKey(ksxcgl.getKsxcglKc());

			Map<String, String> map = new HashMap<String, String>();
			map.put("ksxcglIdcard", bmgl.getBmglSfzh());
			map.put("ksxcglKkslx", bmgl.getBmglBkgw());
			ksxcglService.deleteByBmglInfo(map);

			Ksxcgl ksxcgl = new Ksxcgl();
			ksxcgl.setKsxcglDelflag((long) 1);
			ksxcgl.setKsxcglAddtime(new Date());
			ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
			ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
			ksxcgl.setKsxcglBkdw(bmgl.getBmglBkdw());
			ksxcgl.setKsxcglKkslx(bmgl.getBmglBkgw());
			ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
			ksxcgl.setKcglId(kcId);
			ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
			ksxcgl.setKsxcglKc(kcgl.getKcglName());
			ksxcgl.setKsxcglTszg("否");
			ksxcgl.setKsxcglWjqk("否");
			ksxcgl.setKsxcglKc(kcgl.getKcglName());
			ksxcglService.insertksxcgl(ksxcgl);

			return new ModelAndView("Main/ShowAlert", "uri", "RegistrationSuccess");
		} else
			return new ModelAndView("Main/ShowAlert", "uri", "RegistrationIsExist");
	}

	@ResponseBody
	@RequestMapping(value = "/RegistrationByManage", method = RequestMethod.POST)
	public InterfaceResult RegistrationByManage(String kcId, String id, String userId, HttpSession session) throws ParseException
	{
		logInfo("RegistrationByManage", kcId + "," + id + "," + userId);
		InterfaceResult interfaceResult = new InterfaceResult();
		if (session.getAttribute("zzjgId") == null)
		{
			interfaceResult.setMessage("用户数据丢失,请重新登录!");
			return interfaceResult;
		}
		Userinfo user = userService.selectByPrimaryKey(userId);
		if (user == null)
		{
			interfaceResult.setMessage("该用户不存在!");
			return interfaceResult;
		}
		Kcgl kcgl = kcglService.selectByPrimaryKey(kcId);
		if (kcgl.getKcglSyrl() <= 0)
		{
			interfaceResult.setMessage("该考场容量已满!");
			return interfaceResult;
		}
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(id);
		Bmgl b = new Bmgl();
		b.setBmglBkgw(jtjlk.getJtjlkApcpgwlb());
		b.setBmglSfzh(user.getUserSfzh());
		b.setBmglJtjlkid(jtjlk.getJtjlkId());
		List<Bmgl> bmglSel = bmglService.selectIsExist(b);
		if (bmglSel.size() == 0)
		{
			Bmgl bmgl = new Bmgl();
			Resume resume = resumeService.selectByPrimaryKeySelectiveById(user.getUserId());
			Zzjg zzjg = new Zzjg();
			zzjg = zzjgService.selectByPrimaryKey(jtjlk.getZzjgId());

			Map<String, String> p = new HashMap<String, String>();
			p.put("jtjlkId", id);
			bmglService.deleteByWhere(p);

			bmgl.setBmglAddtime(new Date());
			bmgl.setBmglBkdw(zzjg.getZzjgDwmc());
			bmgl.setBmglBkdwdm(zzjg.getZzjgDwdm());
			bmgl.setBmglBkgw(jtjlk.getJtjlkApcpgwlb());
			bmgl.setBmglByxx(jtjlk.getJtjlkByxx());
			bmgl.setBmglDelflag((long) 1);
			bmgl.setBmglId(RandomGUIDUtil.generateKey());
			bmgl.setBmglJtjlkid(id);
			bmgl.setBmglKcglId(kcId);
			bmgl.setBmglKscj("未测评");
			bmgl.setBmglKssj(kcgl.getKcglKssjStart().trim() + " - " + kcgl.getKcglKssjEnd().trim());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			bmgl.setBmglKsrq(format.parse(kcgl.getKcglKsrq()));
			bmgl.setBmglKsxm(jtjlk.getJtjlkName());
			bmgl.setBmglLxdh(user.getUserTelephone());
			bmgl.setBmglKsxb(user.getUserSex());
			bmgl.setBmglSfqr("否");
			bmgl.setBmglSfzh(resume.getResumeSfzh());
			bmgl.setBmglKsdd(kcgl.getKcglDz());
			bmgl.setBmglTszg("");
			bmgl.setBmglPrintflag((long) 1);
			bmglService.insertSelective(bmgl);

			// Bmgl bmgl = bmglService.queryBmglById(ksxcgl.getKsxmId());
			// Kcgl k = kcglService.selectByPrimaryKey(ksxcgl.getKsxcglKc());

			Map<String, String> map = new HashMap<String, String>();
			map.put("ksxcglIdcard", bmgl.getBmglSfzh());
			map.put("ksxcglKkslx", bmgl.getBmglBkgw());
			ksxcglService.deleteByBmglInfo(map);

			Ksxcgl ksxcgl = new Ksxcgl();
			ksxcgl.setKsxcglDelflag((long) 1);
			ksxcgl.setKsxcglAddtime(new Date());
			ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
			ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
			ksxcgl.setKsxcglBkdw(bmgl.getBmglBkdw());
			ksxcgl.setKsxcglKkslx(bmgl.getBmglBkgw());
			ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
			ksxcgl.setKcglId(kcId);
			ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
			ksxcgl.setKsxcglKc(kcgl.getKcglName());
			ksxcgl.setKsxcglTszg("否");
			ksxcgl.setKsxcglWjqk("否");
			ksxcgl.setKsxcglKc(kcgl.getKcglName());
			ksxcglService.insertksxcgl(ksxcgl);
			
			interfaceResult.setMessage("安排报名成功!");
		} else
			interfaceResult.setMessage("该人员报名信息已经存在!");
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/delBmgl", method = RequestMethod.GET)
	public int delBmgl(String bmglId)
	{
		logInfo("delBmgl", bmglId);
		ksxcglService.delByBmglId(bmglId);
		bmglService.delBmgl(bmglId);
		return 1;
	}

	@RequestMapping(value = "/exportBmgl", method = RequestMethod.GET)
	// 设置访问路径
	public ModelAndView exportBmgl(HttpServletRequest request)
	{
		List<Bmgl> bmglList = bmglService.queryAllBmgl();
		File file = new File(request.getSession().getServletContext().getRealPath("")+"\\pdf");
		
		if(!file.exists()||!file.isDirectory())file.mkdir();
		
		String path = new java.util.Date().getTime() + "Bmgl.xls";
		File file1 = null;
		file1 = new File(file + "\\" + path);
		if (!file.exists())
		{
			file.mkdirs();
			if (!file1.exists())
			{
				try
				{
					file1.createNewFile();
				} catch (IOException e)
				{
					logInfo("exportBmgl", "******Error");
					e.printStackTrace();
				}
			}
		}
		try
		{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("报名考生名单");
			sheet.setColumnWidth(0, 2500); // 调整第一列宽度
			sheet.setColumnWidth(1, 2500); // 调整第一列宽度
			sheet.setColumnWidth(2, 5500); // 调整第一列宽度
			sheet.setColumnWidth(3, 5500); // 调整第一列宽度
			sheet.setColumnWidth(4, 3500); // 调整第一列宽度
			sheet.setColumnWidth(5, 5500); // 调整第一列宽度
			sheet.setColumnWidth(6, 5500); // 调整第一列宽度
			sheet.setColumnWidth(7, 3500); // 调整第一列宽度
			sheet.setColumnWidth(8, 5500); // 调整第一列宽度
			sheet.setColumnWidth(9, 2500); // 调整第一列宽度
			sheet.setColumnWidth(10, 3500); // 调整第一列宽度
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 10); // 字体高度
			font.setColor(HSSFFont.BOLDWEIGHT_NORMAL); // 字体颜色
			font.setFontName("黑体"); // 字体
			font.setBoldweight(HSSFFont.ANSI_CHARSET); // 宽度

			// 设置单元格类型
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
			cellStyle.setWrapText(true);
			// 表头为第一行
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell;
			HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("序号");
			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("考生姓名");
			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("身份证号");
			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("报考单位");
			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("报考岗位");
			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("毕业学校");
			cell = row.createCell(6);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("考场名称");
			cell = row.createCell(7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("考试日期");
			cell = row.createCell(8);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("考试时间");
			cell = row.createCell(9);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("是否确认");
			cell = row.createCell(10);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("联系电话");
			for (int i = 0; i < bmglList.size(); i++)
			{
				Bmgl bmgl = bmglList.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(i + 1);
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglKsxm());
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglSfzh());
				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglBkdw());
				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglBkgw());
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglByxx());
				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglKcmc());
				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue(DateConvertUtils.format(bmgl.getBmglKsrq(), "yyyy-MM-dd"));
				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglKssj());
				cell = row.createCell(9);
				cell.setCellStyle(style);
				cell.setCellValue(bmgl.getBmglSfqr());
				cell = row.createCell(10);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(bmgl.getBmglLxdh());
			}
			FileOutputStream fOut = new FileOutputStream(file1);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e)
		{
			logInfo("exportBmgl", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("pdf/bmgl", "uri", "pdf/" + path);
	}

}

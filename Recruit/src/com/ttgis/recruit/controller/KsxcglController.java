package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.Msg;
import com.ttgis.recruit.domain.QueryKsxcgl;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @类名： com.ttgis.recruit.controller.KsxcglController
 * @创建人： 范井龙
 * @日期：
 * @描述： 考场现场管理
 * @版本号：
 */
@Controller
public class KsxcglController {
	@Resource
	private KsxcglService ksxcglService;

	static Logger log = Logger.getLogger(KsxcglController.class);
	@Autowired
	private HttpServletRequest _request;
	@Autowired
	HttpSession _session;

	protected String getRemoteIp() {
		String remoteIp = null;
		remoteIp = _request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getHeader("X-Real-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getHeader("Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getHeader("HTTP_CLIENT_IP");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getRemoteAddr();
		}
		if (remoteIp == null || remoteIp.isEmpty()
				|| "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = _request.getRemoteHost();
		}
		return remoteIp;
	}

	public void logInfo(String _call, String _parameter) {
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

	@RequestMapping(value = "/Ksxcgl", method = RequestMethod.GET)
	public String Ksxcgl(Model model) {
		logInfo("Ksxcgl", "");
		List<Kcgl> kcglList = kcglService.queryKcgl();
		model.addAttribute("kcglList", kcglList);
		return "Ksxcgl/Ksxcgl";
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
	@RequestMapping(value = "/LoadKsxcglData", method = RequestMethod.POST)
	public List<Ksxcgl> LoadKsxcglData(QueryKsxcgl qk) {
		logInfo("LoadKsxcglData", JSONArray.fromObject(qk).toString());
		List<Ksxcgl> ksxcgl = ksxcglService.selectByWhere(qk);
		return ksxcgl;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadKsxcglDataCount", method = RequestMethod.POST)
	public int LoadKsxcglDataCount(QueryKsxcgl qk) {
		logInfo("LoadKsxcglDataCount", JSONArray.fromObject(qk).toString());
		int intCount = ksxcglService.selectCount(qk);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@Resource
	private BmglService bmglService;
	@Resource
	private KcglService kcglService;

	@RequestMapping(value = "/KsxcglAdd", method = RequestMethod.GET)
	public String AddKsxcgl(Model model, String ksxcglId) {
		logInfo("KsxcglAdd", ksxcglId);
		List<Kcgl> kcglList = kcglService.queryKcgl();
		model.addAttribute("kcglList", kcglList);
		if (ksxcglId == null || ksxcglId == "") {
			List<Bmgl> bmglList = bmglService.queryBmgl();
			model.addAttribute("bmglList", bmglList);
			return "Ksxcgl/KsxcglAdd";
		} else {
			Ksxcgl ksxcgl = ksxcglService.queryByKsxcglId(ksxcglId);
			model.addAttribute("ksxcgl", ksxcgl);
			return "Ksxcgl/KsxcglUpd";
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */

	@RequestMapping(value = "/KsxcglSava", method = RequestMethod.POST)
	public ModelAndView KsxcglSava(Ksxcgl ksxcgl) {
		logInfo("KsxcglSava", JSONArray.fromObject(ksxcgl).toString());
		if (ksxcgl.getKsxcglId().equals("")) {
			// 考场容量
			int kcrl = kcglService.queryKcrlByKcglName(ksxcgl.getKsxcglKc());
			// 已经向该考场中添加人数
			int kcNry = ksxcglService.selectCountByKc(ksxcgl.getKsxcglKc());
			if (kcNry >= kcrl) {
				return new ModelAndView("Ksxcgl/KsxcglKcrl");
			} else {
				Bmgl bmgl = bmglService.queryBmglById(ksxcgl.getKsxmId());
				Kcgl k = kcglService.selectByPrimaryKey(ksxcgl.getKsxcglKc());
				ksxcgl.setKsxcglDelflag((long) 1);
				ksxcgl.setKsxcglAddtime(new Date());
				ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
				ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
				ksxcgl.setKsxcglBkdw(bmgl.getBmglBkdw());
				ksxcgl.setKsxcglKkslx(bmgl.getBmglBkgw());
				ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
				ksxcgl.setKcglId(bmgl.getBmglId());
				ksxcgl.setKsxcglKc(k.getKcglName());
				ksxcglService.insertksxcgl(ksxcgl);
				return new ModelAndView("success", "uri", "Ksxcgl");
			}
		} else {
			ksxcgl.setKsxcglDelflag((long) 1);
			ksxcgl.setKsxcglAddtime(new Date());
			ksxcglService.updateByPrimaryKeySelective(ksxcgl);
			return new ModelAndView("success", "uri", "Ksxcgl");
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/DelKsxcgl", method = RequestMethod.GET)
	public Msg DelXcxx(String ksxcglId) {
		logInfo("DelKsxcgl", ksxcglId);
		Ksxcgl k = ksxcglService.selectByPrimaryKey(ksxcglId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("ksxcglId", ksxcglId);
		map.put("bmglId", k.getKsxcglBmglId());
		ksxcglService.deleteByPrimaryKey(map);
		return new Msg("DelKsxcgl", "success", Msg.SUCCESS);

	}

	@RequestMapping(value = "/DelGlgcj", method = RequestMethod.GET)
	public String DelGlgcj(String ksxcglId) {
		logInfo("DelGlgcj", ksxcglId);
		ksxcglService.DelGlgcj(ksxcglId);
		return "Ksxcgl/Ksxcgl";

	}

	@RequestMapping(value = "/DelJsgcj", method = RequestMethod.GET)
	public String DelJsgcj(String ksxcglId) {
		logInfo("DelJsgcj", ksxcglId);
		ksxcglService.DelJsgcj(ksxcglId);
		return "Ksxcgl/Ksxcgl";

	}

	@ResponseBody
	@RequestMapping(value = "/checkKscjEng", method = RequestMethod.POST)
	public int checkKscjEng(String ksxcglIdcard, String ksxcglKkslx) {
		logInfo("checkKscjEng", ksxcglIdcard + "," + ksxcglKkslx);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sfzh", ksxcglIdcard);
		map.put("bkgw", ksxcglKkslx);
		List<Ksxcgl> ksxcgls = ksxcglService.selectByBmglId(map);
		int intRtn = 0;
		if (ksxcgls.size() > 0) {
			for (Ksxcgl ksxcgl : ksxcgls) {
				if ((ksxcgl.getKsxcglJsgYycj() == null && ksxcgl
						.getKsxcglKscjJsg() != null)
						|| (ksxcgl.getKsxcglKscjYycj() == null && ksxcgl
								.getKsxcglKscjGlg() != null))
					intRtn = 1;
				if (ksxcgl.getKsxcglKkslx().equals("技术岗")) {
					if ((ksxcgl.getKsxcglJsgYycj() == null && ksxcgl
							.getKsxcglKscjJsg() == null))
						intRtn = -1;
				}
				if (ksxcgl.getKsxcglKkslx().equals("管理岗")) {
					if (ksxcgl.getKsxcglKscjYycj() == null
							&& ksxcgl.getKsxcglKscjGlg() == null)
						intRtn = -1;
				}
			}
		}
		return intRtn;
	}

	@ResponseBody
	@RequestMapping(value = "/checkKscjGlg", method = RequestMethod.POST)
	public int checkKscjGlg(HttpSession session, String ksxcglIdcard) {
		logInfo("checkKscjGlg", ksxcglIdcard);
		int kscjGlgCount = ksxcglService.checkKscjGlg(ksxcglIdcard);
		if (kscjGlgCount == 0) {
			Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");
			Bmgl bmgl1 = (Bmgl) session.getAttribute("cpglLoginSession2");
			if (bmgl.getBmglBkgw().equals("管理岗"))
				session.setAttribute("cpglLoginSessionCurrent", bmgl);
			else
				session.setAttribute("cpglLoginSessionCurrent", bmgl1);
		}
		return kscjGlgCount;
	}

	@ResponseBody
	@RequestMapping(value = "/checkKscjJsg", method = RequestMethod.POST)
	public int checkKscjJsg(HttpSession session, String ksxcglIdcard) {
		logInfo("checkKscjJsg", ksxcglIdcard);
		int kscjJsgCount = ksxcglService.checkKscjJsg(ksxcglIdcard);
		if (kscjJsgCount == 0) {
			Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");
			Bmgl bmgl1 = (Bmgl) session.getAttribute("cpglLoginSession2");
			if (bmgl.getBmglBkgw().equals("技术岗"))
				session.setAttribute("cpglLoginSessionCurrent", bmgl);
			else
				session.setAttribute("cpglLoginSessionCurrent", bmgl1);
		}
		return kscjJsgCount;
	}

	// =======================导出成绩==================================
	@RequestMapping(value = "/exportScore", method = RequestMethod.GET)
	public ModelAndView Image(HttpServletRequest request,
			HttpServletResponse response) {
		logInfo("exportScore", "");
		List<Ksxcgl> ksxcglList = ksxcglService.queryKsxcgl();
		File file = new File(request.getSession().getServletContext()
				.getRealPath("pdf"));
		String path = new java.util.Date().getTime() + "KSCJ.pdf";
		File file1 = null;
		file1 = new File(file + "\\" + path);
		if (!file.exists()) {
			file.mkdirs();
			if (!file1.exists()) {
				try {
					file1.createNewFile();
				} catch (IOException e) {
					logInfo("exportScore", "******Error");
					e.printStackTrace();
				}
			}
		}
		try {
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(file1));
			doc.open();
			// 标题字体
			BaseFont bf = BaseFont.createFont(request.getSession()
					.getServletContext().getRealPath("pdfFonts")
					+ "\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font titleFont = new Font(bf, 18, Font.BOLD);
			BaseFont bfComic = BaseFont.createFont(request.getSession()
					.getServletContext().getRealPath("pdfFonts")
					+ "\\simkai.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			PdfPTable table = new PdfPTable(13);
			PdfPCell cell = new PdfPCell(new Phrase("综合素质测评成绩单", titleFont));
			cell.setUseAscender(true);
			cell.setUseDescender(true);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(13);
			table.addCell(cell);
			table.addCell(new PdfPCell(new Phrase("单位代码", font)));
			table.addCell(new PdfPCell(new Phrase("单位名称", font)));
			table.addCell(new PdfPCell(new Phrase("考生姓名", font)));
			table.addCell(new PdfPCell(new Phrase("性别", font)));
			table.addCell(new PdfPCell(new Phrase("身份证号", font)));
			table.addCell(new PdfPCell(new Phrase("出生日期", font)));
			table.addCell(new PdfPCell(new Phrase("毕业学校", font)));
			table.addCell(new PdfPCell(new Phrase("专业", font)));
			table.addCell(new PdfPCell(new Phrase("学历", font)));
			table.addCell(new PdfPCell(new Phrase("考试时间", font)));
			table.addCell(new PdfPCell(new Phrase("考场名称", font)));
			table.addCell(new PdfPCell(new Phrase("管理岗成绩", font)));
			table.addCell(new PdfPCell(new Phrase("技术岗成绩", font)));
			for (Ksxcgl ksxcgl : ksxcglList) {
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getDwdm(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getKsxcglBkdw(),
						font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getKsxcglName(),
						font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getSex(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getKsxcglIdcard(),
						font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getBirthday(),
						font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getByyx(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getZyl(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getXl(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getKssj(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getKcname(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getGlgcj(), font)));
				table.addCell(new PdfPCell(new Phrase(ksxcgl.getJsgcj(), font)));

			}
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			logInfo("exportScore", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("pdf/pdf", "uri", "pdf/" + path);
	}

	@RequestMapping(value = "/exportKscj", method = RequestMethod.GET)
	// 设置访问路径
	public ModelAndView importTkgl(HttpServletRequest request, String ksxcglBkdw)
			throws UnsupportedEncodingException {
		logInfo("exportKscj  ksxcglBkdw：", ksxcglBkdw);
		List<Ksxcgl> ksxcglList;
		if (ksxcglBkdw == null || ksxcglBkdw.equals("")) {
			ksxcglList = ksxcglService.queryKsxcgl();
		} else {
			ksxcglList = ksxcglService.queryKsxcglByBkdw(ksxcglBkdw);
		}

		HttpSession hs = request.getSession();

		ServletContext sc = hs.getServletContext();

		String pathContext = sc.getRealPath("");

		File file = new File(pathContext + "\\pdf");

		if (!file.exists() || !file.isDirectory())
			file.mkdir();

		String path = new java.util.Date().getTime() + "KSCJ.xlsx";
		File file1 = null;
		file1 = new File(file + "\\" + path);
		if (!file.exists()) {
			file.mkdirs();
			if (!file1.exists()) {
				try {
					file1.createNewFile();
				} catch (IOException e) {
					logInfo("exportKscj", "******Error" + "e:"
							+ e.getMessage().toString());
					e.printStackTrace();
				}
			}
		}

		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);

		Sheet sheet = workbook.createSheet("考生成绩信息表");
		sheet.setColumnWidth(0, 2500); // 调整第一列宽度
		sheet.setColumnWidth(1, 5500); // 调整第一列宽度
		sheet.setColumnWidth(2, 2500); // 调整第一列宽度
		sheet.setColumnWidth(3, 2500); // 调整第一列宽度
		sheet.setColumnWidth(4, 5500); // 调整第一列宽度
		sheet.setColumnWidth(5, 3500); // 调整第一列宽度
		sheet.setColumnWidth(6, 5500); // 调整第一列宽度
		sheet.setColumnWidth(7, 5500); // 调整第一列宽度
		sheet.setColumnWidth(8, 2500); // 调整第一列宽度
		sheet.setColumnWidth(9, 4500); // 调整第一列宽度
		sheet.setColumnWidth(10, 3500); // 调整第一列宽度
		sheet.setColumnWidth(11, 3500); // 调整第一列宽度
		sheet.setColumnWidth(12, 3500); // 调整第一列宽度
		// 设置字体
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10); // 字体高度
		font.setColor(HSSFFont.BOLDWEIGHT_NORMAL); // 字体颜色
		font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.ANSI_CHARSET); // 宽度

		// 设置单元格类型
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setWrapText(true);
		// 表头为第一行
		Row row = sheet.createRow(0);
		Cell cell;
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("单位代码");
		cell = row.createCell(1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("单位名称");
		cell = row.createCell(2);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("考生姓名");
		cell = row.createCell(3);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("性别");
		cell = row.createCell(4);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("身份证号");
		cell = row.createCell(5);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("出生日期");
		cell = row.createCell(6);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("毕业学校");
		cell = row.createCell(7);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("专业");
		cell = row.createCell(8);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("学历");
		cell = row.createCell(9);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("考试时间");
		cell = row.createCell(10);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("考场名称");
		cell = row.createCell(11);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("管理岗成绩");
		cell = row.createCell(12);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("技术岗成绩");
		for (int i = 0; i < ksxcglList.size(); i++) {
			Ksxcgl ksxcgl = ksxcglList.get(i);
			if (ksxcgl.getKsxcglKscjGlg() == null) {
				ksxcgl.setKsxcglKscjGlg((double) 0);
			}
			if (ksxcgl.getKsxcglKscjYycj() == null) {
				ksxcgl.setKsxcglKscjYycj((double) 0);
			}
			if (ksxcgl.getKsxcglKscjJsg() == null) {
				ksxcgl.setKsxcglKscjJsg((double) 0);
			}
			if (ksxcgl.getKsxcglJsgYycj() == null) {
				ksxcgl.setKsxcglJsgYycj((double) 0);
			}
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getDwdm());
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getKsxcglBkdw());
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getKsxcglName());
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getSex());
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getKsxcglIdcard());
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getBirthday());
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getByyx());
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getZyl());
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getXl());
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(ksxcgl.getKssj());
			cell = row.createCell(10);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(ksxcgl.getKcname());
			cell = row.createCell(11);
			cell.setCellStyle(style);
			if ((ksxcgl.getKsxcglKscjGlg() + ksxcgl.getKsxcglKscjYycj()) == 0) {
				cell.setCellValue("----");
			} else {
				cell.setCellValue(ksxcgl.getKsxcglKscjGlg()
						+ ksxcgl.getKsxcglKscjYycj());
			}

			cell = row.createCell(12);
			cell.setCellStyle(style);
			if ((ksxcgl.getKsxcglKscjJsg() + ksxcgl.getKsxcglJsgYycj()) == 0) {
				cell.setCellValue("----");
			} else {
				cell.setCellValue(ksxcgl.getKsxcglKscjJsg()
						+ ksxcgl.getKsxcglJsgYycj());
			}
		}
		try {
			FileOutputStream fOut = new FileOutputStream(file1);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			logInfo("exportKscj", "******Error" + "e:"
					+ e.getMessage().toString());
		}
		return new ModelAndView("pdf/pdf", "uri", "pdf/" + path);
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
	@RequestMapping(value = "/CjcxData", method = RequestMethod.POST)
	public List<Ksxcgl> CjcxData(QueryKsxcgl qk, HttpSession session) {
		logInfo("CjcxData", JSONArray.fromObject(qk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (zzjg.getZzjgSjdw().equals(null) || zzjg.getZzjgSjdw().equals("")) {
			List<Ksxcgl> ksxcgl = ksxcglService.CjcxData(qk);
			return ksxcgl;
		} else if (zzjg.getZzjgSjdw().equals("test001")) {
			qk.setZzjgDwdm(zzjg.getZzjgId());
			List<Ksxcgl> ksxcgl = ksxcglService.EJCjcxData(qk);
			return ksxcgl;
		} else {
			qk.setZzjgDwdm(zzjg.getZzjgId());
			List<Ksxcgl> ksxcgl = ksxcglService.SJCjcxData(qk);
			return ksxcgl;
		}
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/CjcxCount", method = RequestMethod.POST)
	public int CjcxCount(QueryKsxcgl qk, HttpSession session) {
		logInfo("CjcxCount", JSONArray.fromObject(qk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (zzjg.getZzjgSjdw().equals(null) || zzjg.getZzjgSjdw().equals("")) {
			int intCount = ksxcglService.CjcxCount(qk);
			return intCount;
		} else if (zzjg.getZzjgSjdw().equals("test001")) {
			qk.setZzjgDwdm(zzjg.getZzjgId());
			int intCount = ksxcglService.EJCjcxCount(qk);
			return intCount;
		} else {
			qk.setZzjgDwdm(zzjg.getZzjgId());
			int intCount = ksxcglService.SJCjcxCount(qk);
			return intCount;
		}
	}
}

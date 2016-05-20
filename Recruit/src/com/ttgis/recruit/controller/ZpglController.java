/** 
 * 招聘管理 2014-05-20 孙建国
 */
package com.ttgis.recruit.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.ZipException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.ttgis.recruit.domain.Cjtsgl;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.Msg;
import com.ttgis.recruit.domain.Msq;
import com.ttgis.recruit.domain.Msqgl_detailed;
import com.ttgis.recruit.domain.MyApplication;
import com.ttgis.recruit.domain.QueryJtjlk;
import com.ttgis.recruit.domain.Resume;
import com.ttgis.recruit.domain.Resume_ITjn;
import com.ttgis.recruit.domain.Resume_fj;
import com.ttgis.recruit.domain.Resume_gzjl;
import com.ttgis.recruit.domain.Resume_jyjl;
import com.ttgis.recruit.domain.Resume_pxjl;
import com.ttgis.recruit.domain.Resume_qtxx;
import com.ttgis.recruit.domain.Resume_sjjl;
import com.ttgis.recruit.domain.Resume_sx;
import com.ttgis.recruit.domain.Resume_xmjy;
import com.ttgis.recruit.domain.Resume_xnjl;
import com.ttgis.recruit.domain.Resume_xnzw;
import com.ttgis.recruit.domain.Resume_yynl;
import com.ttgis.recruit.domain.Resume_zs;
import com.ttgis.recruit.domain.Txl;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.XmlEntity;
import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.CjtsglService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.service.MsjgService;
import com.ttgis.recruit.service.MsqService;
import com.ttgis.recruit.service.ResumeService;
import com.ttgis.recruit.service.Resume_ITjnService;
import com.ttgis.recruit.service.Resume_fjService;
import com.ttgis.recruit.service.Resume_gzjlService;
import com.ttgis.recruit.service.Resume_jyjlService;
import com.ttgis.recruit.service.Resume_pxjlService;
import com.ttgis.recruit.service.Resume_qtxxService;
import com.ttgis.recruit.service.Resume_sjjlService;
import com.ttgis.recruit.service.Resume_sxService;
import com.ttgis.recruit.service.Resume_xmjyService;
import com.ttgis.recruit.service.Resume_xnjlService;
import com.ttgis.recruit.service.Resume_xnzwService;
import com.ttgis.recruit.service.Resume_yynlService;
import com.ttgis.recruit.service.Resume_zsService;
import com.ttgis.recruit.service.TxlService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.CompressUtil;
import com.ttgis.recruit.utility.ExcelFileGenerator;
import com.ttgis.recruit.utility.FieldNullFill;
import com.ttgis.recruit.utility.FileUtils;
import com.ttgis.recruit.utility.HtmlSpider;
import com.ttgis.recruit.utility.HtmlToDoc;
import com.ttgis.recruit.utility.MailSenderInfo;
import com.ttgis.recruit.utility.PropertiesUtils;
import com.ttgis.recruit.utility.SendEmail;
import com.ttgis.recruit.utility.SendMessage;
import com.ttgis.recruit.utility.StringUtils;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class ZpglController extends BaseController
{
	@Resource
	JtjlkService jtjlkService;
	@Resource
	MsjgService msjgService;
	@Resource
	UserService userService;
	@Resource
	TxlService txlService;
	@Resource
	XxtzService xxtzService;
	@Resource
	MsqService msqService;
	@Autowired
	private CjtsglService cjtsglService;
	@Autowired
	private ZzjgService zzjgService;
	@Autowired
	KsxcglService ksxcglService;

	@Autowired
	private ResumeService resumeService;
	@Autowired
	private Resume_jyjlService resume_jyjlService;
	@Autowired
	private Resume_xnzwService resume_xnzwService;
	@Autowired
	private Resume_xnjlService resume_xnjlService;
	@Autowired
	private Resume_sjjlService resume_sjjlService;
	@Autowired
	private Resume_sxService resume_sxService;
	@Autowired
	private Resume_gzjlService resume_gzjlService;
	@Autowired
	private Resume_xmjyService resume_xmjyService;
	@Autowired
	private Resume_pxjlService resume_pxjlService;
	@Autowired
	private Resume_yynlService resume_yynlService;
	@Autowired
	private Resume_qtxxService resume_qtxxService;
	@Autowired
	private Resume_fjService resume_fjService;
	@Autowired
	private Resume_ITjnService resume_ITjnService;
	@Autowired
	private Resume_zsService resume_zsService;

	static Logger log = Logger.getLogger(ZpglController.class);
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

	@RequestMapping(value = "/Xdjl", method = RequestMethod.GET)
	public ModelAndView Jtjlk()
	{
		return new ModelAndView("Zpgl/Xdjl");
	}

	@RequestMapping(value = "/Shdjl", method = RequestMethod.GET)
	public ModelAndView Shdjl()
	{
		return new ModelAndView("Zpgl/Shdjl");
	}

	@RequestMapping(value = "/Ytzcpjl", method = RequestMethod.GET)
	public ModelAndView Ytzcpjl()
	{
		return new ModelAndView("Zpgl/Ytzcpjl");
	}

	@RequestMapping(value = "/Yapmsjl", method = RequestMethod.GET)
	public ModelAndView Yapmsjl()
	{
		return new ModelAndView("Zpgl/Yapmsjl");
	}

	@RequestMapping(value = "/Scdjl", method = RequestMethod.GET)
	public ModelAndView Scdjl()
	{
		return new ModelAndView("Zpgl/Scdjl");
	}

	@RequestMapping(value = "/Apms", method = RequestMethod.GET)
	public ModelAndView Apms(@RequestParam(value = "name", required = false) String name) throws UnsupportedEncodingException
	{
		logInfo("Apms", name);
		if (name != null)
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		return new ModelAndView("Zpgl/Apms", "Name", name);
	}

	// @RequestMapping(value = "/Msqgl", method = RequestMethod.GET)
	// public ModelAndView Msqgl()
	// {
	// return new ModelAndView("Zpgl/Msqgl");
	// }

	@RequestMapping(value = "/Sxxt", method = RequestMethod.GET)
	public ModelAndView Sxxt(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("Sxxt", id);
		return new ModelAndView("Zpgl/Sxxt", "Id", id);
	}

	@RequestMapping(value = "/Ylyjl", method = RequestMethod.GET)
	public ModelAndView Ylyjl()
	{
		return new ModelAndView("Zpgl/Ylyjl");
	}

	@RequestMapping(value = "/Yttjl", method = RequestMethod.GET)
	public ModelAndView Yttjl()
	{
		return new ModelAndView("Zpgl/Yttjl");
	}

	@RequestMapping(value = "/Zpgl", method = RequestMethod.GET)
	public ModelAndView Zpgl()
	{
		return new ModelAndView("Zpgl/Zpgl");
	}

	@RequestMapping(value = "/Msjl", method = RequestMethod.GET)
	public ModelAndView Msjl(@RequestParam(value = "jlid", required = false) String jlid)
	{
		logInfo("Msjl", jlid);
		List<Msqgl_detailed> Msqgl_detaileds = msjgService.selectByJlId(jlid);
		return new ModelAndView("Zpgl/Msjl", "msjl", Msqgl_detaileds);
	}

	@ResponseBody
	@RequestMapping(value = "/GetMsCanPass", method = RequestMethod.POST)
	public String GetMsCanPass(@RequestParam(value = "jlid", required = false) String jlid)
	{
		logInfo("GetMsCanPass", jlid);
		List<Msqgl_detailed> Msqgl_detaileds = msjgService.selectByJlId(jlid);
		String strReturn = "false";
		if (Msqgl_detaileds.size() > 0)
		{
			for (Msqgl_detailed msqgl_detailed : Msqgl_detaileds)
			{
				if (msqgl_detailed.getMsqglDetailedPj() != null || msqgl_detailed.getMsqglDetailedJl() != null)
				{
					strReturn = "true";
					break;
				}
			}
		}
		return strReturn;
	}

	@RequestMapping(value = "/MsResult", method = RequestMethod.GET)
	public ModelAndView MsResult(HttpSession session)
	{
		logInfo("MsResult", "");
		Userinfo userinfo = (Userinfo) session.getAttribute("loginSession");
		Map<String, String> params = new HashMap<String, String>();
		params.put("jlId", userinfo.getUserJlid());
		params.put("userId", userinfo.getUserJlid());
		List<Msqgl_detailed> Msqgl_detaileds = msjgService.selectByMsg(params);
		return new ModelAndView("Zpgl/MsResult", "msq", Msqgl_detaileds);
	}

	@RequestMapping(value = "/DoSxxt", method = RequestMethod.POST)
	public ModelAndView DoSxxt(String Receive, String Title, String Content, String Id, String contextString, HttpServletRequest request)
	{
		logInfo("DoSxxt", Receive + "," + Title + "," + Content + "," + Id + "," + contextString);
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 收件人邮箱
		mailInfo.setToAddress(Receive);
		// 邮件标题
		mailInfo.setSubject(Title);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append(Content);
		// 附件
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(Id);
		SavePdf(jtjlk.getJtjlkUserid(), contextString, request);
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\" + jtjlk.getJtjlkUserid() + ".doc";
		File file = new File(path);
		if (file.exists())
		{
			String[] strings =
			{ path };
			mailInfo.setAttachFileNames(strings);
		}
		mailInfo.setContent(buffer.toString());
		// 发送文体格式
		SendEmail.sendHtmlMail(mailInfo);
		jtjlk.setJtjlkJlzt("筛选协同");
		jtjlkService.updateByPrimaryKeySelective(jtjlk);
		return new ModelAndView("success", "uri", "Xdjl");
	}

	public void SavePdf(String resumeId, String contextString, HttpServletRequest request)
	{
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeId);
		List<Resume_xnjl> Xnjl = resume_xnjlService.getlistResumeXnjlByResumeId(resumeId);
		List<Resume_xnzw> Xnzw = resume_xnzwService.getlistResumeXnzwByResumeId(resumeId);
		List<Resume_sjjl> Sjjl = resume_sjjlService.getlistResumeSjjlByResumeId(resumeId);
		List<Resume_sx> Sxjl = resume_sxService.getlistResumeSxByResumeId(resumeId);
		List<Resume_gzjl> Gzjl = resume_gzjlService.getlistResumeGzjlByResumeId(resumeId);
		List<Resume_xmjy> Xmjy = resume_xmjyService.getlistResumeXmjyByResumeId(resumeId);
		List<Resume_pxjl> Pxjl = resume_pxjlService.getlistResumePxjlByResumeId(resumeId);
		List<Resume_yynl> Yynl = resume_yynlService.getlistResumeYynlByResumeId(resumeId);
		List<Resume_qtxx> Qtxx = resume_qtxxService.getlistResumeQtxxByResumeId(resumeId);
		List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(resumeId);
		List<Resume_ITjn> ITjn = resume_ITjnService.getlistResumeITjnByResumeId(resumeId);
		List<Resume_zs> Zs = resume_zsService.getlistResumeZsByResumeId(resumeId);
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File file1 = new File(path + resume.getResumeId() + ".doc");
		try
		{
			// 设置纸张大小
			Document document = new Document(PageSize.A4);
			// 建立一个书写器.，与document对象关联
			RtfWriter2.getInstance(document, new FileOutputStream((file1)));
			document.open();
			// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// 标题字体风格
			Font titleFont = new Font(bfChinese, 12, Font.BOLD);
			// 正文字体风格
			Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
			Paragraph title = new Paragraph(resume.getResumeName() + "简历");
			// 设置标题格式对齐方式
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			document.add(title);
			Paragraph context = new Paragraph(contextString);
			context.setAlignment(Element.ALIGN_LEFT);
			context.setFont(contextFont);
			// 段间距
			context.setSpacingBefore(3);
			// 设置第一行空的列数
			context.setFirstLineIndent(20);
			document.add(context);

			// 设置Table表格,创建一个三列的表格(基本信息table)
			Table table = new Table(3);
			int width[] =
			{ 20, 65, 15 };// 设置每列宽度比例
			table.setWidths(width);
			table.setWidth(90);// 占页面宽度比例
			table.setAlignment(Element.ALIGN_CENTER);// 居中
			table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
			table.setAutoFillEmptyCells(true);// 自动填满
			table.setBorderWidth(1);// 边框宽度
			// 设置表头
			Cell haderCell = new Cell("基本信息");
			haderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			haderCell.setHeader(true);
			haderCell.setColspan(3);
			table.addCell(haderCell);
			table.endHeaders();

			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK);
			Cell cell = new Cell(new Paragraph("姓名:", fontChinese));
			table.addCell(cell);
			table.addCell(new Cell(resume.getResumeName()));
			if (resume.getResumePhotos() != null)
			{
				String PhotosString = resume.getResumePhotos();
				String Photospath = request.getSession().getServletContext().getRealPath("/");
				String final1Path = Photospath + PhotosString;
				Image img = Image.getInstance(final1Path);
				img.scaleAbsolute(70, 80);
				Cell photoCell = new Cell();
				photoCell.add(img);
				table.addCell(photoCell);
				photoCell.setRowspan(5);
			} else
			{
				Cell photoCell = new Cell("Photos");
				table.addCell(photoCell);
				photoCell.setRowspan(5);
			}
			table.addCell(new Cell("性别"));
			table.addCell(new Cell(resume.getResumeSex()));
			table.addCell(new Cell(""));
			table.addCell(new Cell("出生日期"));
			table.addCell(new Cell(resume.getResumeBirthday()));
			table.addCell(new Cell(""));
			table.addCell(new Cell("身份证号"));
			Cell birthdayCell = new Cell(resume.getResumeBirthday());
			// birthdayCell.setColspan(2);
			table.addCell(birthdayCell);
			table.addCell(new Cell(""));
			table.addCell(new Cell("现居地"));
			Cell RxqhkszcsCell = new Cell(resume.getResumeRxqhkszcsProvince() + resume.getResumeRxqhkszcsCity());
			// RxqhkszcsCell.setColspan(2);
			table.addCell(RxqhkszcsCell);
			table.addCell(new Cell(""));
			table.addCell(new Cell("户口地"));
			Cell MqszcsCell = new Cell(resume.getResumeMqszcsProvince() + resume.getResumeMqszcsCity());
			MqszcsCell.setColspan(2);
			table.addCell(MqszcsCell);
			document.add(table);

			// 联系信息table
			if (resume.getResumeTelphone() != null || resume.getResumeEmail() != null)
			{
				Table lxxxtable = new Table(2);
				int lxxxwidth[] =
				{ 20, 80 };
				lxxxtable.setWidths(lxxxwidth);
				lxxxtable.setWidth(90);
				lxxxtable.setAlignment(Element.ALIGN_CENTER);
				lxxxtable.setAlignment(Element.ALIGN_MIDDLE);
				lxxxtable.setAutoFillEmptyCells(true);
				lxxxtable.setBorderWidth(1);

				Cell lxxxCell = new Cell("联系信息");
				lxxxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				lxxxCell.setHeader(true);
				lxxxCell.setColspan(2);
				lxxxtable.addCell(lxxxCell);
				lxxxtable.endHeaders();
				if (resume.getResumeTelphone() != null)
				{
					Cell Telphonecell = new Cell(new Paragraph("手机号码:", fontChinese));
					lxxxtable.addCell(Telphonecell);
					lxxxtable.addCell(new Cell(resume.getResumeTelphone()));
				}
				if (resume.getResumeEmail() != null)
				{
					Cell Emailcell = new Cell(new Paragraph("电子邮箱:", fontChinese));
					lxxxtable.addCell(Emailcell);
					lxxxtable.addCell(new Cell(resume.getResumeEmail()));
				}
				document.add(lxxxtable);
			}
			if (Jyjl.size() > 0)
			{
				Table jyjltable = new Table(2);
				int jyjlwidth[] =
				{ 20, 80 };
				jyjltable.setWidths(jyjlwidth);
				jyjltable.setWidth(90);
				jyjltable.setAlignment(Element.ALIGN_CENTER);
				jyjltable.setAlignment(Element.ALIGN_MIDDLE);
				jyjltable.setAutoFillEmptyCells(true);
				jyjltable.setBorderWidth(1);

				Cell jyjlCell = new Cell("教育经历");
				jyjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				jyjlCell.setHeader(true);
				jyjlCell.setColspan(2);
				jyjltable.addCell(jyjlCell);
				jyjltable.endHeaders();
				for (Resume_jyjl resume2 : Jyjl)
				{
					jyjltable.addCell(new Cell(new Paragraph("就读时间:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeJdsj() + "到" + resume2.getResumeJdsj1()));
					jyjltable.addCell(new Cell(new Paragraph("学校名称:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeXxmc()));
					jyjltable.addCell(new Cell(new Paragraph("专业:", fontChinese)));
					//jyjltable.addCell(new Cell(resume2.getResumeZyl() + resume2.getResumeZy()));
					jyjltable.addCell(new Cell(resume2.getResumeZyl()));
					jyjltable.addCell(new Cell(new Paragraph("学历:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeXl()));
					jyjltable.addCell(new Cell(new Paragraph("专业描述:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeZyms()));
					jyjltable.addCell(new Cell(new Paragraph("院系排名:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeYxpm()));
					jyjltable.addCell(new Cell(new Paragraph("班级排名:", fontChinese)));
					jyjltable.addCell(new Cell(resume2.getResumeBjpm()));

				}
				document.add(jyjltable);
			}
			if (Yynl.size() > 0)
			{
				Table yynltable = new Table(2);
				int yynlwidth[] =
				{ 20, 80 };
				yynltable.setWidths(yynlwidth);
				yynltable.setWidth(90);
				yynltable.setAlignment(Element.ALIGN_CENTER);
				yynltable.setAlignment(Element.ALIGN_MIDDLE);
				yynltable.setAutoFillEmptyCells(true);
				yynltable.setBorderWidth(1);

				Cell yynlCell = new Cell("语言能力");
				yynlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				yynlCell.setHeader(true);
				yynlCell.setColspan(2);
				yynltable.addCell(yynlCell);
				yynltable.endHeaders();
				for (Resume_yynl resume2 : Yynl)
				{
					yynltable.addCell(new Cell(new Paragraph("语言类别:", fontChinese)));
					yynltable.addCell(new Cell(resume2.getResumeYylb()));
					yynltable.addCell(new Cell(new Paragraph("听说能力:", fontChinese)));
					yynltable.addCell(new Cell(resume2.getResumeTsnl()));
					yynltable.addCell(new Cell(new Paragraph("读写能力:", fontChinese)));
					yynltable.addCell(new Cell(resume2.getResumeDxnl()));
					yynltable.addCell(new Cell(new Paragraph("等级考试:", fontChinese)));
					yynltable.addCell(new Cell(resume2.getResumeDjks()));
					yynltable.addCell(new Cell(new Paragraph("语言分数:", fontChinese)));
					yynltable.addCell(new Cell(resume2.getResumeYyfs()));
				}
				document.add(yynltable);
			}
			if (Gzjl.size() > 0)
			{
				Table gzjltable = new Table(2);
				int gzjlwidth[] =
				{ 20, 80 };
				gzjltable.setWidths(gzjlwidth);
				gzjltable.setWidth(90);
				gzjltable.setAlignment(Element.ALIGN_CENTER);
				gzjltable.setAlignment(Element.ALIGN_MIDDLE);
				gzjltable.setAutoFillEmptyCells(true);
				gzjltable.setBorderWidth(1);

				Cell gzjlCell = new Cell("工作经历");
				gzjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				gzjlCell.setHeader(true);
				gzjlCell.setColspan(2);
				gzjltable.addCell(gzjlCell);
				gzjltable.endHeaders();
				for (Resume_gzjl resume2 : Gzjl)
				{
					gzjltable.addCell(new Cell(new Paragraph("工作公司:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzgs()));
					gzjltable.addCell(new Cell(new Paragraph("工作城市:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzcs()));
					gzjltable.addCell(new Cell(new Paragraph("工作部门:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzbm()));
					gzjltable.addCell(new Cell(new Paragraph("工作职位:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzzw()));
					gzjltable.addCell(new Cell(new Paragraph("工作行业:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzhy()));
					gzjltable.addCell(new Cell(new Paragraph("工作时间:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzsj()));
					gzjltable.addCell(new Cell(new Paragraph("工作描述:", fontChinese)));
					gzjltable.addCell(new Cell(resume2.getResumeGzms()));
				}
				document.add(gzjltable);
			}
			if (Xnjl.size() > 0)
			{
				Table xnjltable = new Table(2);
				int xnjlwidth[] =
				{ 20, 80 };
				xnjltable.setWidths(xnjlwidth);
				xnjltable.setWidth(90);
				xnjltable.setAlignment(Element.ALIGN_CENTER);
				xnjltable.setAlignment(Element.ALIGN_MIDDLE);
				xnjltable.setAutoFillEmptyCells(true);
				xnjltable.setBorderWidth(1);

				Cell xnjlCell = new Cell("校内奖励");
				xnjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				xnjlCell.setHeader(true);
				xnjlCell.setColspan(2);
				xnjltable.addCell(xnjlCell);
				xnjltable.endHeaders();
				for (Resume_xnjl resume2 : Xnjl)
				{
					xnjltable.addCell(new Cell(new Paragraph("奖项名称:", fontChinese)));
					xnjltable.addCell(new Cell(resume2.getResumeJxmc()));
					xnjltable.addCell(new Cell(new Paragraph("获奖时间:", fontChinese)));
					xnjltable.addCell(new Cell(resume2.getResumeHjsj()));
					xnjltable.addCell(new Cell(new Paragraph("奖励说明:", fontChinese)));
					xnjltable.addCell(new Cell(resume2.getResumeJlsm()));
				}
				document.add(xnjltable);
			}
			if (Xnzw.size() > 0)
			{
				Table xnzwtable = new Table(2);
				int xnzwwidth[] =
				{ 20, 80 };
				xnzwtable.setWidths(xnzwwidth);
				xnzwtable.setWidth(90);
				xnzwtable.setAlignment(Element.ALIGN_CENTER);
				xnzwtable.setAlignment(Element.ALIGN_MIDDLE);
				xnzwtable.setAutoFillEmptyCells(true);
				xnzwtable.setBorderWidth(1);

				Cell xnzwCell = new Cell("校内职务");
				xnzwCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				xnzwCell.setHeader(true);
				xnzwCell.setColspan(2);
				xnzwtable.addCell(xnzwCell);
				xnzwtable.endHeaders();
				for (Resume_xnzw resume2 : Xnzw)
				{
					xnzwtable.addCell(new Cell(new Paragraph("校内职务名称:", fontChinese)));
					xnzwtable.addCell(new Cell(resume2.getResumeXnzwmc()));
					xnzwtable.addCell(new Cell(new Paragraph("任职时间:", fontChinese)));
					xnzwtable.addCell(new Cell(resume2.getResumeRzsj() + "到" + resume2.getResumeRzsj1()));
					xnzwtable.addCell(new Cell(new Paragraph("职责和业绩:", fontChinese)));
					xnzwtable.addCell(new Cell(resume2.getResumeZzhyj()));
				}
				document.add(xnzwtable);
			}
			if (Sjjl.size() > 0)
			{
				Table sjjltable = new Table(2);
				int sjjlwidth[] =
				{ 20, 80 };
				sjjltable.setWidths(sjjlwidth);
				sjjltable.setWidth(90);
				sjjltable.setAlignment(Element.ALIGN_CENTER);
				sjjltable.setAlignment(Element.ALIGN_MIDDLE);
				sjjltable.setAutoFillEmptyCells(true);
				sjjltable.setBorderWidth(1);

				Cell sjjlCell = new Cell("实践经历");
				sjjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sjjlCell.setHeader(true);
				sjjlCell.setColspan(2);
				sjjltable.addCell(sjjlCell);
				sjjltable.endHeaders();
				for (Resume_sjjl resume2 : Sjjl)
				{
					sjjltable.addCell(new Cell(new Paragraph("实践名称:", fontChinese)));
					sjjltable.addCell(new Cell(resume2.getResumeSjmc()));
					sjjltable.addCell(new Cell(new Paragraph("实践时间:", fontChinese)));
					sjjltable.addCell(new Cell(resume2.getResumeSjsj() + "到" + resume2.getResumeSjsj1()));
					sjjltable.addCell(new Cell(new Paragraph("实践描述:", fontChinese)));
					sjjltable.addCell(new Cell(resume2.getResumeSjms()));
				}
				document.add(sjjltable);
			}
			if (Sxjl.size() > 0)
			{
				Table sxjltable = new Table(2);
				int sxjlwidth[] =
				{ 20, 80 };
				sxjltable.setWidths(sxjlwidth);
				sxjltable.setWidth(90);
				sxjltable.setAlignment(Element.ALIGN_CENTER);
				sxjltable.setAlignment(Element.ALIGN_MIDDLE);
				sxjltable.setAutoFillEmptyCells(true);
				sxjltable.setBorderWidth(1);

				Cell sxjlCell = new Cell("实习经历");
				sxjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sxjlCell.setHeader(true);
				sxjlCell.setColspan(2);
				sxjltable.addCell(sxjlCell);
				sxjltable.endHeaders();
				for (Resume_sx resume2 : Sxjl)
				{
					sxjltable.addCell(new Cell(new Paragraph("实习公司:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxgs()));
					sxjltable.addCell(new Cell(new Paragraph("实习城市:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxcs()));
					sxjltable.addCell(new Cell(new Paragraph("实习部门:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxbm()));
					sxjltable.addCell(new Cell(new Paragraph("实习职位:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxzw()));
					sxjltable.addCell(new Cell(new Paragraph("实习行业:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxhy()));
					sxjltable.addCell(new Cell(new Paragraph("实习时间:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxsj() + "到" + resume2.getResumeSxsj1()));
					sxjltable.addCell(new Cell(new Paragraph("实习描述:", fontChinese)));
					sxjltable.addCell(new Cell(resume2.getResumeSxms()));
				}
				document.add(sxjltable);
			}
			if (Xmjy.size() > 0)
			{
				Table xmjytable = new Table(2);
				int xmjywidth[] =
				{ 20, 80 };
				xmjytable.setWidths(xmjywidth);
				xmjytable.setWidth(90);
				xmjytable.setAlignment(Element.ALIGN_CENTER);
				xmjytable.setAlignment(Element.ALIGN_MIDDLE);
				xmjytable.setAutoFillEmptyCells(true);
				xmjytable.setBorderWidth(1);

				Cell xmjyCell = new Cell("项目经验");
				xmjyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				xmjyCell.setHeader(true);
				xmjyCell.setColspan(2);
				xmjytable.addCell(xmjyCell);
				xmjytable.endHeaders();
				for (Resume_xmjy resume2 : Xmjy)
				{
					xmjytable.addCell(new Cell(new Paragraph("项目名称:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeXmmc()));
					xmjytable.addCell(new Cell(new Paragraph("团队规模:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeTdgm()));
					xmjytable.addCell(new Cell(new Paragraph("项目简介:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeXmjj()));
					xmjytable.addCell(new Cell(new Paragraph("项目角色:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeXmjs()));
					xmjytable.addCell(new Cell(new Paragraph("参与时间:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeCysj() + "到" + resume2.getResumeCysj1()));
					xmjytable.addCell(new Cell(new Paragraph("项目成果:", fontChinese)));
					xmjytable.addCell(new Cell(resume2.getResumeXmcg()));
				}
				document.add(xmjytable);
			}
			if (Pxjl.size() > 0)
			{
				Table pxjltable = new Table(2);
				int pxjlwidth[] =
				{ 20, 80 };
				pxjltable.setWidths(pxjlwidth);
				pxjltable.setWidth(90);
				pxjltable.setAlignment(Element.ALIGN_CENTER);
				pxjltable.setAlignment(Element.ALIGN_MIDDLE);
				pxjltable.setAutoFillEmptyCells(true);
				pxjltable.setBorderWidth(1);

				Cell pxjlCell = new Cell("培训经历");
				pxjlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pxjlCell.setHeader(true);
				pxjlCell.setColspan(2);
				pxjltable.addCell(pxjlCell);
				pxjltable.endHeaders();
				for (Resume_pxjl resume2 : Pxjl)
				{
					pxjltable.addCell(new Cell(new Paragraph("培训名称:", fontChinese)));
					pxjltable.addCell(new Cell(resume2.getResumePxmc()));
					pxjltable.addCell(new Cell(new Paragraph("培训机构:", fontChinese)));
					pxjltable.addCell(new Cell(resume2.getResumePxjg()));
					pxjltable.addCell(new Cell(new Paragraph("培训时间:", fontChinese)));
					pxjltable.addCell(new Cell(resume2.getResumePxsj() + "到" + resume2.getResumePxsj1()));
					pxjltable.addCell(new Cell(new Paragraph("培训内容:", fontChinese)));
					pxjltable.addCell(new Cell(resume2.getResumePxnr()));
				}
				document.add(pxjltable);
			}
			if (Zs.size() > 0)
			{
				Table zstable = new Table(2);
				int zswidth[] =
				{ 20, 80 };
				zstable.setWidths(zswidth);
				zstable.setWidth(90);
				zstable.setAlignment(Element.ALIGN_CENTER);
				zstable.setAlignment(Element.ALIGN_MIDDLE);
				zstable.setAutoFillEmptyCells(true);
				zstable.setBorderWidth(1);

				Cell zsCell = new Cell("证书");
				zsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				zsCell.setHeader(true);
				zsCell.setColspan(2);
				zstable.addCell(zsCell);
				zstable.endHeaders();
				for (Resume_zs resume2 : Zs)
				{
					zstable.addCell(new Cell(new Paragraph("获得证书:", fontChinese)));
					zstable.addCell(new Cell(resume2.getResumeHdzs()));
					zstable.addCell(new Cell(new Paragraph("其他证书:", fontChinese)));
					zstable.addCell(new Cell(resume2.getResumeQtzs()));
				}
				document.add(zstable);
			}
			if (ITjn.size() > 0)
			{
				Table ITjntable = new Table(2);
				int ITjnwidth[] =
				{ 20, 80 };
				ITjntable.setWidths(ITjnwidth);
				ITjntable.setWidth(90);
				ITjntable.setAlignment(Element.ALIGN_CENTER);
				ITjntable.setAlignment(Element.ALIGN_MIDDLE);
				ITjntable.setAutoFillEmptyCells(true);
				ITjntable.setBorderWidth(1);

				Cell ITjnCell = new Cell("IT技能");
				ITjnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ITjnCell.setHeader(true);
				ITjnCell.setColspan(2);
				ITjntable.addCell(ITjnCell);
				ITjntable.endHeaders();
				for (Resume_ITjn resume2 : ITjn)
				{
					ITjntable.addCell(new Cell(new Paragraph("IT精通技能:", fontChinese)));
					ITjntable.addCell(new Cell(resume2.getResumeITjtjn()));
					ITjntable.addCell(new Cell(new Paragraph("IT熟悉技能:", fontChinese)));
					ITjntable.addCell(new Cell(resume2.getResumeITsxjn()));
					ITjntable.addCell(new Cell(new Paragraph("其他技能:", fontChinese)));
					ITjntable.addCell(new Cell(resume2.getResumeQtjn()));
				}
				document.add(ITjntable);
			}
			/*
			 * if (resume.getResumeHdzs() != null || resume.getResumeQtzs() !=
			 * null) { Table zstable = new Table(2); int zswidth[] = { 20, 80 };
			 * zstable.setWidths(zswidth); zstable.setWidth(90);
			 * zstable.setAlignment(Element.ALIGN_CENTER);
			 * zstable.setAlignment(Element.ALIGN_MIDDLE);
			 * zstable.setAutoFillEmptyCells(true); zstable.setBorderWidth(1);
			 * 
			 * Cell zsCell = new Cell("证书");
			 * zsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * zsCell.setHeader(true); zsCell.setColspan(2);
			 * zstable.addCell(zsCell); zstable.endHeaders(); if
			 * (resume.getResumeQtzs() != null) { Cell hdzscell = new Cell( new
			 * Paragraph("获得证书:", fontChinese)); zstable.addCell(hdzscell);
			 * zstable.addCell(new Cell(resume.getResumeQtzs())); } if
			 * (resume.getResumeQtzs() != null) { Cell qtzscell = new Cell( new
			 * Paragraph("其他证书:", fontChinese)); zstable.addCell(qtzscell);
			 * zstable.addCell(new Cell(resume.getResumeQtzs())); }
			 * document.add(zstable); } if (resume.getResumeITjtjn() != null ||
			 * resume.getResumeITsxjn() != null || resume.getResumeQtjn() !=
			 * null) { Table ITtable = new Table(2); int ITwidth[] = { 20, 80 };
			 * ITtable.setWidths(ITwidth); ITtable.setWidth(90);
			 * ITtable.setAlignment(Element.ALIGN_CENTER);
			 * ITtable.setAlignment(Element.ALIGN_MIDDLE);
			 * ITtable.setAutoFillEmptyCells(true); ITtable.setBorderWidth(1);
			 * 
			 * Cell ITCell = new Cell("IT技能");
			 * ITCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * ITCell.setHeader(true); ITCell.setColspan(2);
			 * ITtable.addCell(ITCell); ITtable.endHeaders(); if
			 * (resume.getResumeITjtjn() != null) { Cell ITjtjncell = new
			 * Cell(new Paragraph("IT精通技能:", fontChinese));
			 * ITtable.addCell(ITjtjncell); ITtable.addCell(new
			 * Cell(resume.getResumeITjtjn())); } if (resume.getResumeITsxjn()
			 * != null) { Cell ITsxjncell = new Cell(new Paragraph("IT熟悉技能:",
			 * fontChinese)); ITtable.addCell(ITsxjncell); ITtable.addCell(new
			 * Cell(resume.getResumeITsxjn())); } if (resume.getResumeQtjn() !=
			 * null) { Cell Qtjncell = new Cell( new Paragraph("其他技能:",
			 * fontChinese)); ITtable.addCell(Qtjncell); ITtable.addCell(new
			 * Cell(resume.getResumeQtjn())); } document.add(ITtable); }
			 */
			if (Qtxx.size() > 0)
			{
				Table qtxxtable = new Table(2);
				int qtxxwidth[] =
				{ 20, 80 };
				qtxxtable.setWidths(qtxxwidth);
				qtxxtable.setWidth(90);
				qtxxtable.setAlignment(Element.ALIGN_CENTER);
				qtxxtable.setAlignment(Element.ALIGN_MIDDLE);
				qtxxtable.setAutoFillEmptyCells(true);
				qtxxtable.setBorderWidth(1);

				Cell qtxxCell = new Cell("其他信息");
				qtxxCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				qtxxCell.setHeader(true);
				qtxxCell.setColspan(2);
				qtxxtable.addCell(qtxxCell);
				qtxxtable.endHeaders();
				for (Resume_qtxx resume2 : Qtxx)
				{
					qtxxtable.addCell(new Cell(new Paragraph("其他信息类别:", fontChinese)));
					qtxxtable.addCell(new Cell(resume2.getResumeQtxxlb1() + resume2.getResumeQtxxlb2()));
					qtxxtable.addCell(new Cell(new Paragraph("信息描述:", fontChinese)));
					qtxxtable.addCell(new Cell(resume2.getResumeXxms()));
				}
				document.add(qtxxtable);
			}
			if (Fj.size() > 0)
			{
				Table fjtable = new Table(2);
				int fjwidth[] =
				{ 20, 80 };
				fjtable.setWidths(fjwidth);
				fjtable.setWidth(90);
				fjtable.setAlignment(Element.ALIGN_CENTER);
				fjtable.setAlignment(Element.ALIGN_MIDDLE);
				fjtable.setAutoFillEmptyCells(true);
				fjtable.setBorderWidth(1);

				Cell fjCell = new Cell("附件");
				fjCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				fjCell.setHeader(true);
				fjCell.setColspan(2);
				fjtable.addCell(fjCell);
				fjtable.endHeaders();
				for (Resume_fj resume2 : Fj)
				{
					String FjString = resume2.getResumeFj();
					String Fjpath = request.getSession().getServletContext().getRealPath("\\");
					String finalPath = Fjpath + FjString;
					fjtable.addCell(new Cell(new Paragraph("附件:", fontChinese)));
					Image fjimg = Image.getInstance(finalPath);
					fjimg.scaleAbsolute(350, 200);
					Cell imgCell = new Cell(fjimg);
					fjtable.addCell(imgCell);
				}
				document.add(fjtable);
			}
			document.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			logInfo("SavePdf", "******Error");
		}
	}

	/**
	 * 查询
	 * 
	 * @param queryJtjlk
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZpglData", method = RequestMethod.POST)
	public List<Jtjlk> LoadZpglData(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadZpglData", JSONArray.fromObject(queryJtjlk).toString());
		queryJtjlk.setZzjgId(session.getAttribute("zzjgId").toString());
		List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);

		return jtjlks;
	}

	/**
	 * 加星
	 * 
	 * @param queryJtjlk
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateStar", method = RequestMethod.POST)
	public Msg updateStar(QueryJtjlk queryJtjlk)
	{
		logInfo("updateStar:", JSONArray.fromObject(queryJtjlk).toString());
		jtjlkService.updateStar(queryJtjlk);
		return new Msg("加星", "成功", Msg.SUCCESS);
	}

	public String List2String(List list)
	{
		if (list == null)
			return "";
		String rst = "";
		for (int i = 0; i < list.size(); i++)
		{
			if (i == (list.size() - 1))
				rst += list.get(i);
			else
				rst += list.get(i) + ",";
		}
		return rst;
	}

	/**
	 * 查询导出Excel
	 * 
	 * @param queryJtjlk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZpglDataExp", method = RequestMethod.POST)
	public Msg LoadZpglDataExp(QueryJtjlk queryJtjlk, HttpSession session) throws Exception
	{
		logInfo("LoadZpglDataExp", JSONArray.fromObject(queryJtjlk).toString());
		queryJtjlk.setZzjgId(session.getAttribute("zzjgId").toString());

		String filterStr = this.List2String(queryJtjlk.getJtjlkZt());
		if ("测评".equals(queryJtjlk.getJtjlkCpjg()))
			filterStr = "测评";

		if ("一面进行中,二面进行中,终面进行中".equals(this.List2String(queryJtjlk.getJtjlkMszt())))
			filterStr = "一面进行中,二面进行中,终面进行中";

		List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);

		String fileName = this.ExpExcel(filterStr, jtjlks);

		return new Msg("filename", fileName, Msg.SUCCESS);
	}

	public String ExpExcel(String filterStr, List<Jtjlk> jtjlks) throws Exception
	{
		ArrayList fieldName = new ArrayList();
		ArrayList fieldData = new ArrayList();

		if ("未筛选,筛选协同".equals(filterStr))
		{ // 新的简历
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				fieldData.add(tmpData);
			}
		} else if ("通过,未测评,已测评,已安排面试".equals(filterStr))
		{ // 适合的简历
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试状态");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				fieldData.add(tmpData);
			}
		} else if ("测评".equals(filterStr))
		{ // 已通知测评简历
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试状态");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				fieldData.add(tmpData);
			}
		} else if ("一面进行中,二面进行中,终面进行中".equals(filterStr))
		{ // 已安排面试简历
			System.out.println("一面进行中,二面进行中,终面进行中");
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试结果");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				fieldData.add(tmpData);
			}
		} else if ("录用".equals(filterStr))
		{ // 已录用简历
			System.out.println("录用");
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试状态");
			fieldName.add("测评结果");
			fieldName.add("录用职位");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				tmpData.add(jj.getJtjlkCpjg());
				tmpData.add(jj.getJtjlkZzlygw());
				fieldData.add(tmpData);
			}
		} else if ("淘汰".equals(filterStr))
		{ // 已录用简历
			System.out.println("录用");
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试结果");
			fieldName.add("测评结果");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				tmpData.add(jj.getJtjlkCpjg());
				fieldData.add(tmpData);
			}
		} else if ("收藏".equals(filterStr))
		{ // 已录用简历
			System.out.println("收藏");
			fieldName.add("姓名");
			fieldName.add("出生日期");
			fieldName.add("身份证号");
			fieldName.add("性别");
			fieldName.add("毕业院校");
			fieldName.add("专业");
			fieldName.add("学历");
			fieldName.add("职位");
			fieldName.add("职位类别");
			fieldName.add("测评成绩");
			fieldName.add("面试结果");

			for (int i = 0; i < jtjlks.size(); i++)
			{
				Jtjlk jj = jtjlks.get(i);
				ArrayList tmpData = new ArrayList();
				tmpData.add(jj.getJtjlkName());
				tmpData.add(jj.getJtjlkCsrq());
				tmpData.add(jj.getJtjlkSfzh());
				tmpData.add(jj.getJtjlkSex());
				tmpData.add(jj.getJtjlkByxx());
				tmpData.add(jj.getJtjlkZy());
				tmpData.add(jj.getJtjlkXl());
				tmpData.add(jj.getJtjlkZw());
				tmpData.add(jj.getJtjlkGwlb());
				tmpData.add(jj.getJtjlkCpcj());
				tmpData.add(jj.getJtjlkMszt());
				fieldData.add(tmpData);
			}
		}

		ExcelFileGenerator efg = new ExcelFileGenerator(fieldName, fieldData);
		efg.createWorkbook();

		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File file = new File(path);
		if (!file.exists() || !file.isDirectory())
			file.mkdirs();

		String fileName = RandomGUIDUtil.generateKey() + ".xls";
		OutputStream os = new FileOutputStream(path + fileName);
		efg.exportExcel(os);
		return fileName;
	}

	/**
	 * 查询总条数
	 * 
	 * @param queryJtjlk
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZpglDataCount", method = RequestMethod.POST)
	public int LoadZpglDataCount(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadZpglDataCount", JSONArray.fromObject(queryJtjlk).toString());
		queryJtjlk.setZzjgId(session.getAttribute("zzjgId").toString());
		int intCount = jtjlkService.selectCount(queryJtjlk);
		return intCount;
	}

	@ResponseBody
	@RequestMapping(value = "/UpdateZpglStatus", method = RequestMethod.POST)
	public int UpdateZpglStatus(Jtjlk jtjlk, String page)
	{
		logInfo("UpdateZpglStatus", JSONArray.fromObject(jtjlk).toString());
		Jtjlk jtjlkSel = new Jtjlk();
		String[] jtjlkIds = jtjlk.getJtjlkId().split(",");
		for (int i = 0; i < jtjlkIds.length; i++)
		{
			jtjlk.setJtjlkId(jtjlkIds[i]);
			jtjlkSel = jtjlkService.selectByPrimaryKey(jtjlk.getJtjlkId());
			if (jtjlk.getJtjlkJlzt().equals("恢复"))
			{
				System.out.println("恢复" + jtjlkSel.getJtjlkTtqcpjg() + jtjlkSel.getJtjlkTtqjlzt() + jtjlkSel.getJtjlkTtqmszt());
				jtjlk.setJtjlkCpjg(jtjlkSel.getJtjlkTtqcpjg());
				jtjlk.setJtjlkJlzt(jtjlkSel.getJtjlkTtqjlzt());
				jtjlk.setJtjlkMszt(jtjlkSel.getJtjlkTtqmszt());
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
			} else
			{
				jtjlk.setJtjlkTtqcpjg(jtjlkSel.getJtjlkCpjg());
				jtjlk.setJtjlkTtqmszt(jtjlkSel.getJtjlkMszt());
				jtjlk.setJtjlkTtqjlzt(jtjlkSel.getJtjlkJlzt());
				if (page.equals("Ytzcpjl"))
				{
					if (jtjlk.getJtjlkJlzt().equals("通过"))
						jtjlk.setJtjlkCpjg("测评通过");
					else
					{
						jtjlk.setJtjlkCpjg("测评淘汰");
					}
				}
				if (page.equals("Yapmsjl"))
				{
					if (jtjlk.getJtjlkJlzt().equals("通过"))
						jtjlk.setJtjlkMszt(jtjlkSel.getJtjlkMszt().replace("进行中", "通过"));
					else
					{
						jtjlk.setJtjlkMszt(jtjlkSel.getJtjlkMszt().replace("进行中", "淘汰"));
					}
				}
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
			}
		}
		return 1;
	}

	public void Tszh(String jtjlkId, Txl txl, String strId, String strZzjgId)
	{
		Random r = new Random();
		String loginId = "";
		String loginPswd = "";

		loginId = r.nextInt(1000000) + "";
		loginPswd = r.nextInt(1000000) + "";

		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(strId);
		userinfo.setUserAddtime(new Date());
		userinfo.setUserDelflag((long) 1);
		userinfo.setUserIdcard(loginId);
		userinfo.setUserJlid(jtjlkId);
		userinfo.setUserSfls("是");
		userinfo.setUserPassword(loginPswd);
		userinfo.setUserName(txl.getTxlName());
		userService.insertSelective(userinfo);

		MailSenderInfo mailInfo = new MailSenderInfo();
		// 收件人邮箱
		mailInfo.setToAddress(txl.getTxlEmail());
		// 邮件标题
		mailInfo.setSubject("面试通知");
		// 附件
		String[] strings =
		{ "F:\\2.xls" };
		mailInfo.setAttachFileNames(strings);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("面试通知\r\n面试官:" + txl.getTxlName() + ",此次登录系统帐号信息如下:\r\n帐号:" + loginId + "\r\n密码:" + loginPswd + "\r\n请用本账号登录招聘移动端进行面试和评价.");
		mailInfo.setContent(buffer.toString());
		// 发送邮件
		SendEmail sms = new SendEmail();
		// 发送文体格式
		sms.sendTextMail(mailInfo);

		// 发送短信
		String xml = null;
		XmlEntity xmlentity = new XmlEntity();
		SendMessage s = new SendMessage();
		String xxtzMessageContent = "面试通知——面试官:" + txl.getTxlName() + ",此次登录系统帐号信息如下:帐号:" + loginId + "密码:" + loginPswd + "面试完毕后请登录系统填写面试结果.【中国航天科工培训中心】";
		xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", txl.getTxlPhone(), xxtzMessageContent, "").toString();
		// System.out.println(xml);
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setRemainpoint("remainpoint");
		xmlentity.setTaskID("taskID");
		xmlentity.setSuccessCounts("successCounts");
		xmlentity = s.readStringXmlCommen(xmlentity, xml);
		// System.out.println("状态" + xmlentity.getReturnstatus() + "返回信息" +
		// xmlentity.getMessage() + "成功条数" + xmlentity.getSuccessCounts());
		for (int i = 0; i < txl.getTxlPhone().split(",").length; i++)
		{
			Xxtz xxtz = new Xxtz();
			xxtz.setUserId(strId);
			xxtz.setXxtzAddtime(new Date());
			xxtz.setXxtzCuser(strZzjgId);
			xxtz.setXxtzDelflag((long) 1);
			xxtz.setXxtzEmail("");
			xxtz.setXxtzEmailContent("");
			xxtz.setXxtzEmailResult("");
			xxtz.setXxtzEmailTime(new Date());
			xxtz.setXxtzId(RandomGUIDUtil.generateKey());
			xxtz.setXxtzMessageContent(xxtzMessageContent);
			xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
			xxtz.setXxtzMessageTime(new Date());
			xxtz.setXxtzTelepohne(Long.parseLong(txl.getTxlPhone()));
			xxtz.setXxtzType("面试通知");
			xxtz.setXxtzUser(txl.getTxlName());
			xxtzService.insertxxtz(xxtz);
		}
	}

	public boolean checkCpResult(String type, String id, HttpSession session)
	{
		logInfo("checkCpResult", type + "," + id);
		Map<String, String> map = new HashMap<String, String>();

		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(id);
		map.put("apcsLb", type);
		map.put("userId", jtjlk.getJtjlkUserid());
		Jtjlk cr = jtjlkService.CheckCpResult(map);
		if (cr != null)
		{
			if (!cr.getJtjlkCpcj().equals("未通知") && !cr.getJtjlkCpcj().equals("未完成"))
			{
				jtjlk.setJtjlkJlzt("已测评");
				jtjlk.setJtjlkCpcj(cr.getJtjlkCpcj());
				jtjlk.setJtjlkCpjg("未完成");
				jtjlk.setJtjlkApcpgwlb(cr.getJtjlkApcpgwlb());
				jtjlkService.updateByPrimaryKeySelective(jtjlk);

				Map<String, String> m = new HashMap<String, String>();
				m.put("bkgw", type);
				m.put("sfzh", jtjlk.getJtjlkSfzh());
				List<Ksxcgl> ksxcgls = ksxcglService.selectByBmglId(m);
				for (Ksxcgl ksxcgl : ksxcgls)
				{
					if (ksxcgl.getKsxcglKkslx().equals(cr.getJtjlkApcpgwlb()))
					{
						Ksxcgl k = ksxcgl;
						k.setKsxcglId(RandomGUIDUtil.generateKey());
						k.setKsxcglBkdw(session.getAttribute("zzjgName").toString());
						k.setKsxcglAddtime(new Date());
						ksxcglService.insertksxcgl(ksxcgl);
						break;
					}
				}
				try
				{
					Cjtsgl cjtsgl = cjtsglService.selectByPrimaryKey("test001");
					Zzjg zzjg = zzjgService.selectByPrimaryKey(jtjlk.getZzjgId());
					int yssj = cjtsgl.getCjtsglTsyssj();
					String strContent = "姓名：" + jtjlk.getJtjlkName() + "，" + "学校：" + jtjlk.getJtjlkByxx() + "，" + "专业：" + jtjlk.getJtjlkZy() + "，" + "测评成绩：" + cr.getJtjlkCpcj() + "。请登录后台管理系统查看应聘者性格测试报告。";
					Xxtz xxtz = new Xxtz();
					xxtz.setUserId(zzjg.getZzjgId());
					xxtz.setXxtzAddtime(new Date());
					xxtz.setXxtzCuser(jtjlk.getJtjlkUserid());
					xxtz.setXxtzDelflag((long) 1);
					xxtz.setXxtzId(RandomGUIDUtil.generateKey());
					xxtz.setXxtzTelepohne(Long.parseLong(zzjg.getZzjgLxrdh()));
					xxtz.setXxtzType("成绩推送");
					xxtz.setXxtzUser(jtjlk.getJtjlkName());
					xxtz.setXxtzIsread(1);

					java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String d = format.format(new Date());
					// System.out.println(d);

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date timeNow;
					timeNow = df.parse(d);
					Calendar begin = Calendar.getInstance();
					begin.setTime(timeNow);
					begin.add(Calendar.MINUTE, yssj);
					String sendTime = df.format(begin.getTime());

					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1)
					{
						xxtz.setXxtzEmail(zzjg.getZzjgLxremail());
						xxtz.setXxtzEmailContent(strContent);
						xxtz.setXxtzEmailResult("待发送");
						xxtz.setXxtzEmailTime(begin.getTime());
					}
					if (cjtsgl.getCjtsglTsfs().toString().indexOf("短信") != -1)
					{
						// 发送短信
						String xml = null;
						XmlEntity xmlentity = new XmlEntity();
						SendMessage s = new SendMessage();
						String xxtzMessageContent = strContent + "【中国航天科工培训中心】";

						xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", zzjg.getZzjgLxrdh(), xxtzMessageContent, sendTime.replaceAll(" ", "%20")).toString();
						xmlentity.setReturnstatus("returnstatus");
						xmlentity.setMessage("message");
						xmlentity.setRemainpoint("remainpoint");
						xmlentity.setTaskID("taskID");
						xmlentity.setSuccessCounts("successCounts");
						xmlentity = s.readStringXmlCommen(xmlentity, xml);
						xxtz.setXxtzMessageContent(xxtzMessageContent);
						xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
						xxtz.setXxtzMessageTime(begin.getTime());
					}
					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1 || cjtsgl.getCjtsglTsfs().toString().indexOf("短信") != -1)
						xxtzService.insertxxtz(xxtz);

				} catch (Exception e)
				{
					logInfo("checkCpResult", "******Error");
				}
				return false;
			} else
			{
				return true;
			}
		} else
		{
			return true;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/XxtzDoSava", method = RequestMethod.POST)
	public String XxtzDoSava(Xxtz xxtz, String content, HttpSession session, Jtjlk j, String testType)
	{
		try
		{
			logInfo("XxtzDoSava", JSONArray.fromObject(xxtz).toString() + ",------" + content + "-------," + JSONArray.fromObject(xxtz).toString() + ",------" + "," + testType);

			String[] jtjlkIds = j.getJtjlkId().split(",");
			for (int k = 0; k < jtjlkIds.length; k++)
			{
				if (checkCpResult(testType, jtjlkIds[k], session))
				{
					if (xxtz.getXxtzType().equals("录用通知"))
					{
						Jtjlk jtjlk = new Jtjlk();
						jtjlk.setJtjlkId(jtjlkIds[k]);
						jtjlk.setJtjlkJlzt("录用");
						jtjlk.setJtjlkZzlygw(j.getJtjlkZzlygw().split(",")[k]);
						jtjlk.setJtjlkZzlygwId(j.getJtjlkZzlygwId().split(",")[k]);
						jtjlkService.updateByPrimaryKeySelective(jtjlk);
					} else
					{
						Jtjlk jtjlk = new Jtjlk();
						jtjlk.setJtjlkId(jtjlkIds[k]);
						jtjlk.setJtjlkJlzt("未测评");
						jtjlk.setJtjlkCpcj("未完成");
						jtjlk.setJtjlkCpjg("未完成");
						jtjlk.setJtjlkApcpgwlb(testType);
						jtjlkService.updateByPrimaryKeySelective(jtjlk);
						Jtjlk selJtjlk = jtjlkService.selectByPrimaryKey(jtjlkIds[k]);
						Userinfo userinfo = userService.selectByPrimaryKey(selJtjlk.getJtjlkUserid());
						MailSenderInfo mailInfo = new MailSenderInfo();
						// 收件人邮箱
						mailInfo.setToAddress(userinfo.getUserEmail());
						// 邮件标题
						mailInfo.setSubject(xxtz.getXxtzType());
						// 邮件内容
						StringBuffer buffer = new StringBuffer();
						buffer.append(content);
						mailInfo.setContent(buffer.toString());
						// 发送html格式
						SendEmail.sendHtmlMail(mailInfo);

						// 发送短信
						String xml = null;
						XmlEntity xmlentity = new XmlEntity();
						SendMessage s = new SendMessage();
						String xxtzMessageContent = Html2Text(content) + "【中国航天科工培训中心】";
						xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", userinfo.getUserTelephone().toString(), xxtzMessageContent, "").toString();

						xmlentity.setReturnstatus("returnstatus");
						xmlentity.setMessage("message");
						xmlentity.setRemainpoint("remainpoint");
						xmlentity.setTaskID("taskID");
						xmlentity.setSuccessCounts("successCounts");
						xmlentity = s.readStringXmlCommen(xmlentity, xml);

						xxtz.setUserId(userinfo.getUserId());
						xxtz.setXxtzAddtime(new Date());
						xxtz.setXxtzCuser(session.getAttribute("zzjgId").toString());
						xxtz.setXxtzDelflag((long) 1);
						xxtz.setXxtzEmail(userinfo.getUserEmail());
						xxtz.setXxtzEmailContent(content);
						xxtz.setXxtzEmailResult("");
						xxtz.setXxtzEmailTime(new Date());
						xxtz.setXxtzId(RandomGUIDUtil.generateKey());
						xxtz.setXxtzMessageContent(xxtzMessageContent);
						xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
						xxtz.setXxtzMessageTime(new Date());
						xxtz.setXxtzTelepohne(userinfo.getUserTelephone());
						xxtz.setXxtzType(xxtz.getXxtzType());
						xxtz.setXxtzUser(userinfo.getUserName());
						xxtz.setXxtzIsread(1);
						xxtzService.insertxxtz(xxtz);
						// System.out.println("状态" +
						// xmlentity.getReturnstatus() +
						// "返回信息" + xmlentity.getMessage() + "成功条数" +
						// xmlentity.getSuccessCounts());
					}
				}
			}
			return "success";
		} catch (Exception e)
		{
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/Ts", method = RequestMethod.POST)
	public Map Ts(Msq msq, HttpSession session, HttpServletRequest request)
	{
		logInfo("Ts", JSONArray.fromObject(msq).toString());
		Map<String, String> map = new HashMap<String, String>();
		String[] strJtjlkIds = request.getParameter("jtjlkId").split(",");
		String strMsqId = request.getParameter("msqId");
		String strNames = "";
		List<List<Msqgl_detailed>> msqgl_detailedCounts = new ArrayList<List<Msqgl_detailed>>();
		List<Jtjlk> jtjlks = new ArrayList<Jtjlk>();
		for (int i = 0; i < strJtjlkIds.length; i++)
		{
			List<Msqgl_detailed> msqgl_detailedCount = msjgService.selectCountByForeignKey(strJtjlkIds[i]);
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(strJtjlkIds[i]);
			msqgl_detailedCounts.add(msqgl_detailedCount);
			jtjlks.add(jtjlk);
		}

		for (int i = 0; i < strJtjlkIds.length; i++)
		{
			if (msqgl_detailedCounts.get(i).size() > 2)
				strNames += jtjlks.get(i).getJtjlkName() + " ";
		}
		if (strNames.length() > 0)
		{
			map.put("message", strNames + "已完成终面!");
			return map;
		}

		for (int i = 0; i < strJtjlkIds.length; i++)
		{
			String strMszt = jtjlks.get(i).getJtjlkMszt();
			if (msqgl_detailedCounts.get(i).size() != 0)
			{
				if (strMszt.indexOf("通过") == -1)
					strNames += jtjlks.get(i).getJtjlkName() + " ";
			}
		}
		if (strNames.length() > 0)
		{
			map.put("message", strNames + "上一轮面试还未完成,无法执行本次通知面试!");
			return map;
		}

		msq = msqService.selectByPrimaryKey(strMsqId);
		for (int i = 0; i < strJtjlkIds.length; i++)
		{
			List<Msqgl_detailed> msqgl_detailedCount = msqgl_detailedCounts.get(i);
			Jtjlk jtjlk = jtjlks.get(i);
			String strMslb = "";
			if (msqgl_detailedCount.size() == 0)
				strMslb = "一面";
			else if (msqgl_detailedCount.size() == 1)
				strMslb = "二面";
			else if (msqgl_detailedCount.size() == 2)
				strMslb = "终面";
			List<Userinfo> userinfos = userService.selectByMsqId(msq.getMsqId());
			for (Userinfo userinfo : userinfos)
			{
				Msqgl_detailed msqgl_detailed = new Msqgl_detailed();
				msqgl_detailed.setMsqglDetailedMsg(userinfo.getUserName());
				msqgl_detailed.setMsqglDetailedAddtime(new Date());
				msqgl_detailed.setMsqglDetailedDelflag((long) 1);
				msqgl_detailed.setMsqglDetailedId(RandomGUIDUtil.generateKey());
				msqgl_detailed.setMsqglDetailedLszhId(msq.getMsqId());
				msqgl_detailed.setMsqglDetailedMslb(strMslb);
				msqgl_detailed.setJtjlkId(strJtjlkIds[i]);
				msqgl_detailed.setMsqglDetailedMssj(msq.getMsqMssj());
				msqgl_detailed.setMsqglDetailedMsdd(msq.getMsqMsdd());
				msjgService.insertSelective(msqgl_detailed);

				Jtjlk jtjlkUpdate = new Jtjlk();
				jtjlkUpdate.setJtjlkId(strJtjlkIds[i]);
				jtjlkUpdate.setJtjlkJlzt("已安排面试");
				jtjlkUpdate.setJtjlkMszt(strMslb + "进行中");
				jtjlkService.updateByPrimaryKeySelective(jtjlkUpdate);

				Map<String, String> params = new HashMap<String, String>();
				params.put("name", userinfo.getUserName());
				params.put("zzjgId", session.getAttribute("zzjgId").toString());
				Txl txl = new Txl();
				txl = txlService.selectByName(params);
				// 发送短信
				String xml = null;
				XmlEntity xmlentity = new XmlEntity();
				SendMessage s = new SendMessage();
				String xxtzMessageContent = "面试通知——面试官:" + txl.getTxlName() + ",此次登录系统帐号信息如下:帐号:" + userinfo.getUserIdcard() + "密码:" + userinfo.getUserPassword() + "面试完毕后请登录系统填写面试结果.【中国航天科工培训中心】";
				xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", txl.getTxlPhone(), xxtzMessageContent, "").toString();
				// System.out.println(xml);
				xmlentity.setReturnstatus("returnstatus");
				xmlentity.setMessage("message");
				xmlentity.setRemainpoint("remainpoint");
				xmlentity.setTaskID("taskID");
				xmlentity.setSuccessCounts("successCounts");
				xmlentity = s.readStringXmlCommen(xmlentity, xml);
				// System.out.println("状态" + xmlentity.getReturnstatus() +
				// "返回信息" +
				// xmlentity.getMessage() + "成功条数" +
				// xmlentity.getSuccessCounts());

				Xxtz xxtz = new Xxtz();
				xxtz.setUserId(jtjlk.getJtjlkUserid());
				xxtz.setXxtzAddtime(new Date());
				xxtz.setXxtzCuser(jtjlk.getZzjgId());
				xxtz.setXxtzDelflag((long) 1);
				xxtz.setXxtzEmail("");
				xxtz.setXxtzEmailContent("");
				xxtz.setXxtzEmailResult("");
				xxtz.setXxtzEmailTime(new Date());
				xxtz.setXxtzId(RandomGUIDUtil.generateKey());
				xxtz.setXxtzMessageContent(xxtzMessageContent);
				xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
				xxtz.setXxtzMessageTime(new Date());
				xxtz.setXxtzTelepohne(Long.parseLong(txl.getTxlPhone()));
				xxtz.setXxtzType("面试通知");
				xxtz.setXxtzUser(txl.getTxlName());
				xxtzService.insertxxtz(xxtz);
			}
		}
		map.put("message", "success");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadMsResultData", method = RequestMethod.POST)
	public List<Msqgl_detailed> LoadMsResultData(HttpSession session)
	{
		logInfo("LoadMsResultData", "");
		Userinfo userinfo = (Userinfo) session.getAttribute("loginSession");
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userinfo.getUserName());
		params.put("userId", userinfo.getUserJlid());
		return msjgService.selectByMsg(params);
	}

	@RequestMapping(value = "/MsjgSave", method = RequestMethod.POST)
	public ModelAndView MsjgSave(Msqgl_detailed msqgl_detailed)
	{
		logInfo("MsjgSave", "");
		msjgService.updateByPrimaryKeySelective(msqgl_detailed);
		return new ModelAndView("success", "uri", "MsResult");
	}

	@ResponseBody
	@RequestMapping(value = "/MsjgExcute", method = RequestMethod.POST)
	public int MsjgExcute(Jtjlk jtjlk)
	{
		logInfo("MsjgExcute", JSONArray.fromObject(jtjlk).toString());
		List<Msqgl_detailed> msqgl_detailedCount = msjgService.selectCountByForeignKey(jtjlk.getJtjlkId());
		String strMslb = null;
		if (msqgl_detailedCount.size() == 1)
			strMslb = "一面";
		else if (msqgl_detailedCount.size() == 2)
			strMslb = "二面";
		else if (msqgl_detailedCount.size() == 3)
			strMslb = "终面";
		jtjlk.setJtjlkMszt(strMslb + jtjlk.getJtjlkJlzt());
		jtjlkService.updateByPrimaryKeySelective(jtjlk);
		return 1;
	}

	@ResponseBody
	@RequestMapping(value = "/DelLsUser", method = RequestMethod.POST)
	public int DelLsUser(String jlid, String msgid)
	{
		logInfo("DelLsUser", jlid + "," + msgid);
		Map<String, String> params = new HashMap<String, String>();
		List<Userinfo> userinfos = new ArrayList<Userinfo>();
		if (msgid == null)
		{
			params.put("jlId", jlid);
			userinfos = userService.selectLsyh(params);

		} else
		{
			params.put("jlId", jlid);
			params.put("msgId", msgid);
			userinfos = userService.selectLsyh(params);
		}
		for (Userinfo userinfo : userinfos)
		{
			userinfo.setUserDelflag((long) 0);
			userService.updateByPrimaryKeySelective(userinfo);
		}
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadMsg", method = RequestMethod.POST)
	public List<Txl> LoadMsg(String bm, String zw, HttpSession session)
	{
		logInfo("LoadMsg", bm + "," + zw);
		Map<String, String> params = new HashMap<String, String>();
		params.put("bm", bm);
		params.put("zw", zw);
		params.put("zzjgId", session.getAttribute("zzjgId").toString());
		return txlService.loadMsg(params);
	}

	@ResponseBody
	@RequestMapping(value = "/LoadBm", method = RequestMethod.POST)
	public List<Txl> LoadBm(HttpSession session)
	{
		logInfo("LoadBm", "");
		return txlService.loadBm(session.getAttribute("zzjgId").toString());
	}

	@ResponseBody
	@RequestMapping(value = "/LoadZw", method = RequestMethod.POST)
	public List<Txl> LoadZw(HttpSession session)
	{
		logInfo("LoadZw", "");
		return txlService.loadZw(session.getAttribute("zzjgId").toString());
	}

	@RequestMapping(value = "/Bdwjlk", method = RequestMethod.GET)
	public ModelAndView Bdwjlk()
	{
		return new ModelAndView("Zpgl/Bdwjlk");
	}

	@ResponseBody
	@RequestMapping(value = "/LoadBdwjlkData", method = RequestMethod.POST)
	public List<Jtjlk> LoadBdwjlkData(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadBdwjlkData", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		queryJtjlk.setZzjgId(zzjg.getZzjgId());
		List<Jtjlk> jtjlks = jtjlkService.selectJtjlkByWhere3Level(queryJtjlk);
		return jtjlks;
	}

	/**
	 * 二级级菜单-〉集团简历库-〉本单位简历库 2级筛选
	 * 
	 * @return
	 */
	@RequestMapping(value = "/BdwAndXsdwjlk", method = RequestMethod.GET)
	public ModelAndView BdwAndSxdwjlk()
	{
		return new ModelAndView("Zpgl/BdwAndXsdwjlk");
	}

	@ResponseBody
	@RequestMapping(value = "/LoadBdwAndXsdwjlkData", method = RequestMethod.POST)
	public List<Jtjlk> LoadBdwAndXsdwjlkData(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadBdwAndXsdwjlkData", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		List<Zzjg> zzjgs = new ArrayList<Zzjg>();
		List<String> zzjgList = new ArrayList<String>();
		if ("1".equals(queryJtjlk.getZzjgQueryNum()))
		{
			zzjgs.add(zzjg);
		} else if ("2".equals(queryJtjlk.getZzjgQueryNum()))
		{
			zzjgs = zzjgService.EJqueryUserSun2Q(zzjg.getZzjgId());
		} else
		{
			zzjgs = zzjgService.EJqueryUserSun(zzjg.getZzjgId());
			zzjgs.add(zzjg);
		}
		for (Zzjg z : zzjgs)
		{
			zzjgList.add(z.getZzjgId());
		}
		queryJtjlk.setZzjgIds(zzjgList);
		List<Jtjlk> jtjlks = jtjlkService.selectJtjlkByWhere2Level(queryJtjlk);
		for (Jtjlk j : jtjlks)
		{
			Zzjg z = zzjgService.selectByPrimaryKey(j.getZzjgId());
			j.setZzjgName(z.getZzjgDwmc());
			z = null;
		}
		return jtjlks;
	}

	/**
	 * 一级级菜单-〉集团简历库-〉本单位简历库 3级筛选
	 * 
	 * @return
	 */
	@RequestMapping(value = "/BdwAndXsdwjlkLevel3", method = RequestMethod.GET)
	public ModelAndView BdwAndXsdwjlkLevel3()
	{
		return new ModelAndView("Zpgl/BdwAndXsdwjlkLevel3");
	}

	@ResponseBody
	@RequestMapping(value = "/LoadBdwAndXsdwjlkLevel3Data", method = RequestMethod.POST)
	public List<Jtjlk> LoadBdwAndXsdwjlkLevel3Data(QueryJtjlk queryJtjlk, HttpSession session)
	{
		logInfo("LoadBdwAndXsdwjlkLevel3Data", JSONArray.fromObject(queryJtjlk).toString());
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		List<Zzjg> zzjgs = new ArrayList<Zzjg>();
		List<String> zzjgList = new ArrayList<String>();
		if ("2".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 二級單位
			zzjgs = zzjgService.EJqueryUserSun2Q(zzjg.getZzjgId());
		} else if ("3".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 三級單位
			zzjgs = zzjgService.queryUserSunLevel2();
		} else if ("1".equals(queryJtjlk.getZzjgQueryNum()))
		{
			// 一级单位
			zzjgs.add(zzjg);
			queryJtjlk.setZzjgId(zzjg.getZzjgId());
		} else
		{
			// 全部
			zzjgs = zzjgService.selectAll();
		}
		for (Zzjg z : zzjgs)
		{
			zzjgList.add(z.getZzjgId());
		}

		queryJtjlk.setZzjgIds(zzjgList);
		List<Jtjlk> jtjlks = jtjlkService.selectJtjlkByWhere2Level(queryJtjlk);
		for (Jtjlk j : jtjlks)
		{
			Zzjg z = zzjgService.selectByPrimaryKey(j.getZzjgId());
			j.setZzjgName(z.getZzjgDwmc());
			z = null;
		}
		return jtjlks;
	}

	/**
	 * 导出简历库
	 * 
	 * @param queryJtjlk
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadBdwAndXsdwjlkLevel3DataExp", method = RequestMethod.POST)
	public Msg LoadBdwAndXsdwjlkLevel3DataExp(String ids, HttpServletRequest req) throws Exception
	{
		logInfo("LoadBdwAndXsdwjlkLevel3DataExp", ids);
		if (ids == null || "".equals(ids))
			return new Msg("", "请确认您已选择的记录！", Msg.ERROR);
		String[] idsArray = ids.split(",");
		// 打包目录
		ArrayList fieldData = new ArrayList();

		for (int i = 0; i < idsArray.length; i++)
		{
			Jtjlk jlk = jtjlkService.selectJtjlkById(new Msg(idsArray[i].toString(), null, null));
			if (jlk == null)
				continue;
			fieldData.add(jlk);
		}

		String fileName = this.ExpExcel1(fieldData);
		return new Msg("filename", fileName, Msg.SUCCESS);
	}

	/**
	 * 本单位简历库生成Excel
	 * 
	 * @param jtjlks
	 * @return
	 * @throws Exception
	 */
	public String ExpExcel1(List<Jtjlk> jtjlks) throws Exception
	{
		ArrayList fieldName = new ArrayList();
		ArrayList fieldData = new ArrayList();

		fieldName.add("姓名");
		fieldName.add("出生日期");
		fieldName.add("身份证号");
		fieldName.add("性别");
		fieldName.add("毕业院校");
		fieldName.add("专业");
		fieldName.add("学历");
		fieldName.add("职位");
		fieldName.add("投递时间");
		fieldName.add("生源地");
		fieldName.add("投递单位");
		fieldName.add("招聘过程状态");

		for (int i = 0; i < jtjlks.size(); i++)
		{
			Jtjlk jj = jtjlks.get(i);
			ArrayList tmpData = new ArrayList();
			tmpData.add(jj.getJtjlkName());
			tmpData.add(jj.getJtjlkCsrq());
			tmpData.add(jj.getJtjlkSfzh());
			tmpData.add(jj.getJtjlkSex());
			tmpData.add(jj.getJtjlkByxx());
			tmpData.add(jj.getJtjlkZy());
			tmpData.add(jj.getJtjlkXl());
			tmpData.add(jj.getJtjlkZw());
			tmpData.add(jj.getJtjlkAddtime());
			tmpData.add(jj.getJtjlkHkd());
			tmpData.add(jj.getZzjgName());
			tmpData.add(jj.getJtjlkJlzt());
			fieldData.add(tmpData);
		}

		ExcelFileGenerator efg = new ExcelFileGenerator(fieldName, fieldData);
		efg.createWorkbook();

		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File file = new File(path);
		if (!file.exists() || !file.isDirectory())
			file.mkdirs();

		String fileName = RandomGUIDUtil.generateKey() + ".xls";
		OutputStream os = new FileOutputStream(path + fileName);
		efg.exportExcel(os);
		return fileName;
	}

	/**
	 * 根据集团简历库ID导出用户简历
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/expResumes", method = RequestMethod.POST)
	public Msg expResumes(String ids, HttpServletRequest req) throws Exception
	{
		logInfo("根据集团简历库ID导出用户简历", ids);
		if (ids == null || "".equals(ids))
			return new Msg("", "请确认您已选择的记录！", Msg.ERROR);
		String[] idsArray = ids.split(",");
		// 打包目录
		ArrayList packPath = new ArrayList();

		for (int i = 0; i < idsArray.length; i++)
		{
			Jtjlk jlk = jtjlkService.selectByPrimaryKey(idsArray[i].toString());
			if (jlk != null)
			{
				String userid = jlk.getJtjlkUserid();
				//
				String resumePath = SaveWordByRtf(userid, userid, req);
				packPath.add(resumePath);
			}
		}

		String zipFile = zipDirs(packPath);

		return new Msg("", zipFile, Msg.SUCCESS);
	}

	/** 
	* @Title: saveWord 
	* @Description: pdf导出 利用jsp导出pdf 
	* @param @param resumeid
	* @param @param userid
	* @param @param req
	* @return String    返回生成的pdf的路径xxx/xxx.../xxx.pdf 
	* @param @throws Exception  参数说明 
	*/
	public String saveWord(String resumeid, String userid, HttpServletRequest req) throws Exception
	{
		String url = PropertiesUtils.getValue("server");
		String uri = "/Recruit/expjl?resumeId=" + resumeid + "&userid=" + userid;

		// 要保存的word文件名称
		String fn = getExFileName(resumeid, userid);
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics/resumes/" + resumeid + "/";

		System.out.println("简历保存路径：" + path);

		File file = new File(path);
		if (file.exists())
			file.delete();

		if (!file.exists() || !file.isDirectory())
			file.mkdirs();

		String htmlPath = HtmlSpider.saveHtmlTo(url + uri, path);
		new HtmlToDoc().writeWordFile(htmlPath, path, fn + ".pdf", PropertiesUtils.getValue("server") + "/Recruit/");
		//new HtmlToDoc().SavePdf(resumeid,  "", request);
		//SaveWordByRtf(resumeid, "", req);
		File fileDel = new File(htmlPath);
		fileDel.delete();
		return path + "/" + fn + ".pdf";
	}
	
	
	    /** 
	    * @Title: SaveWordByRtf 
	    * @Description: rtf2Writer方式导出word 
	    * @param @param resumeId
	    * @param @param 没有用到该参数
	    * @param @param request为了获取路径
	    * @return String    返回word的保存路径，无法打包问题是由于路径设置原因
	    */
	    public String SaveWordByRtf(String resumeId, String contextString, HttpServletRequest request) {
		String fillContent = "无"; //若对象中属性为空，用fillContent填充
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		//填充resume中属性为空或者null的项为"无"，只针对类型为Sting的属性
		FieldNullFill.checkFieldValueNull(resume, fillContent);
		List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeId);
		Resume_jyjl latestTimeJyjl = new Resume_jyjl();
		if (Jyjl.size() > 0) {
		    latestTimeJyjl = Jyjl.get(Jyjl.size() - 1);// 查询结果按教育时间升序排列，取最新时间
		}
		FieldNullFill.checkFieldValueNull(latestTimeJyjl, fillContent);
		List<Resume_xnjl> Xnjl = resume_xnjlService.getlistResumeXnjlByResumeId(resumeId);
		List<Resume_xnzw> Xnzw = resume_xnzwService.getlistResumeXnzwByResumeId(resumeId);
		List<Resume_sjjl> Sjjl = resume_sjjlService.getlistResumeSjjlByResumeId(resumeId);
		List<Resume_sx> Sxjl = resume_sxService.getlistResumeSxByResumeId(resumeId);
		List<Resume_gzjl> Gzjl = resume_gzjlService.getlistResumeGzjlByResumeId(resumeId);
		Resume_gzjl latestTimeGzjl = new Resume_gzjl();
		if (Gzjl.size() > 0) {
		    latestTimeGzjl = Gzjl.get(0);// 查询结果按工作时间降序排列，取最新时间
		}
		FieldNullFill.checkFieldValueNull(latestTimeGzjl, fillContent);
		List<Resume_xmjy> Xmjy = resume_xmjyService.getlistResumeXmjyByResumeId(resumeId);
		List<Resume_pxjl> Pxjl = resume_pxjlService.getlistResumePxjlByResumeId(resumeId);
		List<Resume_yynl> Yynl = resume_yynlService.getlistResumeYynlByResumeId(resumeId);
		Resume_yynl firstYynl = new Resume_yynl();
		if (Yynl.size() > 0) {
		    firstYynl = Yynl.get(0);// 查询结果按添加时间升序排列，取第一个即为取第一个添加的外语种类
		}
		FieldNullFill.checkFieldValueNull(firstYynl, fillContent);
		List<Resume_qtxx> Qtxx = resume_qtxxService.getlistResumeQtxxByResumeId(resumeId);
		List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(resumeId);
		List<Resume_ITjn> ITjn = resume_ITjnService.getlistResumeITjnByResumeId(resumeId);
		List<Resume_zs> Zs = resume_zsService.getlistResumeZsByResumeId(resumeId);
		/*String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File file1 = new File(path + resume.getResumeId() + ".doc");*/
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File file1 = new File(path + resume.getResumeName() + "_" + resume.getResumeSex() + "_" + resume.getResumeSfzh() + ".doc");
		try {

		    // 设置纸张大小
		    Document document = new Document(PageSize.A4);
		    // 建立一个书写器.，与document对象关联
		    RtfWriter2.getInstance(document, new FileOutputStream((file1)));
		    document.open();
		    // 设置中文字体
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		    // 标题字体风格
		    Font titleFont = new Font(bfChinese, 14, Font.BOLD);
		    // 正文字体风格
		    Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		    // 设置标题
		    Paragraph title = new Paragraph("应聘人员登记表");
		    // 设置标题格式对齐方式
		    title.setAlignment(Element.ALIGN_CENTER);
		    title.setFont(titleFont);
		    document.add(title);

		    /*
		     * Paragraph context = new Paragraph(contextString);
		     * context.setAlignment(Element.ALIGN_LEFT);
		     * context.setFont(contextFont);
		     * // 段间距
		     * context.setSpacingBefore(3);
		     * // 设置第一行空的列数
		     * context.setFirstLineIndent(20);
		     * document.add(context);
		     */

		    // 设置Table表格,创建一个8列的表格(table前半部分，简历之前，图片占最后两列，四行宽度共10%)
		    Table table = new Table(8);
		    int width[] = { 10, 15, 13, 15, 13, 18, 9, 7 };// 设置每列宽度比例
		    table.setWidths(width);
		    table.setWidth(90);// 占页面宽度比例
		    table.setAlignment(Element.ALIGN_CENTER);// 居中
		    // table.setAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		    table.setAutoFillEmptyCells(true);// 自动填满
		    table.setPadding(5);
		    table.setBorderWidth(1);// 边框宽度

		    // 设置表头 空
		    /*
		     * Cell haderCell = new Cell("");
		     * haderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		     * haderCell.setHeader(true);
		     * haderCell.setColspan(8);
		     * table.addCell(haderCell);
		     * table.endHeaders();
		     */

		    /**
		     * 表格第一行设置
		     * */
		    Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK);

		    Cell cell1_1 = new Cell(new Paragraph("姓名", fontChinese));
		    table.addCell(cell1_1);
		    table.addCell(new Cell(resume.getResumeName()));

		    Cell cell1_3 = new Cell(new Paragraph("性别", fontChinese));
		    table.addCell(cell1_3);
		    table.addCell(new Cell(resume.getResumeSex()));

		    Cell cell1_5 = new Cell(new Paragraph("出生年月", fontChinese));
		    table.addCell(cell1_5);
		    table.addCell(new Cell(resume.getResumeBirthday().replaceAll("-", ".").substring(0, 7)));
		    /*
		     * Cell photoCell = new Cell(new Paragraph("ceshiceshiceshiceshiceshi"));
		     * photoCell.setColspan(2);
		     * photoCell.setRowspan(4);
		     * table.addCell(photoCell);
		     */
		    // 设置图片

		    String PhotosString = resume.getResumePhotos();
		    String Photospath = request.getSession().getServletContext().getRealPath("/");
		    String final1Path = Photospath + PhotosString;
		    Image img = null;
		    try {
			img = Image.getInstance(final1Path);
			if (!(img != null && img.getWidth() >= 0 && img.getHeight() >= 0)) {
			    throw new Exception();
			}
		    } catch (Exception e) {
			System.out.println("图片不存在！");
			img = Image.getInstance(request.getSession().getServletContext().getRealPath("/") + "/images/backupPic.jpg");
		    }

		    img.scaleAbsolute(70, 80);
		    Cell photoCell = new Cell();
		    photoCell.add(img);
		    table.addCell(photoCell);
		    photoCell.setRowspan(4);
		    photoCell.setColspan(2);
		    table.addCell(new Cell(""));

		    /**
		     * 表格第二行设置
		     * */
		    Cell cell2_1 = new Cell(new Paragraph("民族", fontChinese));
		    table.addCell(cell2_1);
		    table.addCell(new Cell(resume.getResumeItjtjn()));

		    Cell cell2_3 = new Cell(new Paragraph("出生地", fontChinese));
		    table.addCell(cell2_3);
		    table.addCell(new Cell(resume.getResumeRxqhkszcsProvince() + resume.getResumeRxqhkszcsCity()));

		    Cell cell2_5 = new Cell(new Paragraph("籍贯", fontChinese));
		    table.addCell(cell2_5);
		    table.addCell(new Cell(resume.getResumeHdzs() + resume.getResumeQtzs()));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第三行设置，学历学位跨两行
		     * */
		    Cell cell3And4_1 = new Cell(new Paragraph("入党时间", fontChinese));
		    table.addCell(cell3And4_1);
		    cell3And4_1.setRowspan(2);
		    // 入党时间设置，如果不是党员，3And4_2单元格的值设为无
		    Cell cell3And4_2 = null;
		    if ((!"无".equals(resume.getResumeZzmm() )) && resume.getResumeZzmm().equals("党员")) {
			cell3And4_2 = new Cell(resume.getResumeQtjn());  

		    } else {
			cell3And4_2 = new Cell("无");
		    }
		    table.addCell(cell3And4_2);
		    cell3And4_2.setRowspan(2);

		    Cell cell3_3 = new Cell(new Paragraph("学历", fontChinese));
		    table.addCell(cell3_3);
		    table.addCell(new Cell(latestTimeJyjl.getResumeXl()));

		    Cell cell3And4_5 = new Cell(new Paragraph("职称/资格", fontChinese));
		    table.addCell(cell3And4_5);
		    cell3And4_5.setRowspan(2);
		    //Cell cell3And4_6 = new Cell(latestTimeGzjl.getResumeGzbm());
		    Cell cell3And4_6 = new Cell(resume.getResumeZczg());
		    table.addCell(cell3And4_6);
		    cell3And4_6.setRowspan(2);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第四行设置，学历学位跨两行
		     * */
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    Cell cell4_3 = new Cell(new Paragraph("学位", fontChinese));
		    table.addCell(cell4_3);
		    table.addCell(new Cell(latestTimeJyjl.getResumeZyms()));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第五行设置，参加工作时间跨两行
		     * */
		    Cell cell5And6_1 = new Cell(new Paragraph("参加工作时间", fontChinese));
		    table.addCell(cell5And6_1);
		    cell5And6_1.setRowspan(2);
		    Cell cell5And6_2 = new Cell(resume.getResumeCjgzsj());
		    table.addCell(cell5And6_2);
		    cell5And6_2.setRowspan(2);

		    Cell cell5_3 = new Cell(new Paragraph("健康状况", fontChinese));
		    table.addCell(cell5_3);
		    Cell cell5_4And5 = new Cell(resume.getResumeItsxjn());
		    table.addCell(cell5_4And5);
		    cell5_4And5.setColspan(2);
		    table.addCell(new Cell(""));

		    Cell cell5_6 = new Cell(new Paragraph("外语种类", fontChinese));
		    table.addCell(cell5_6);
		    Cell cell5_7And8 = new Cell(firstYynl.getResumeYyfs());
		    table.addCell(cell5_7And8);
		    cell5_7And8.setColspan(2);
		    table.addCell(new Cell(""));

		    /**
		     * 表格第六行设置，参加工作时间跨两行
		     * */
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    Cell cell6_3 = new Cell(new Paragraph("户口地", fontChinese));
		    table.addCell(cell6_3);
		    Cell cell6_4And5 = new Cell(resume.getResumeMqszcsProvince() + resume.getResumeMqszcsCity());
		    table.addCell(cell6_4And5);
		    cell6_4And5.setColspan(2);
		    table.addCell(new Cell(""));

		    Cell cell6_6 = new Cell(new Paragraph("外语水平", fontChinese));
		    table.addCell(cell6_6);
		    Cell cell6_7And8 = new Cell(firstYynl.getResumeYylb());
		    table.addCell(cell6_7And8);
		    cell6_7And8.setColspan(2);
		    table.addCell(new Cell(""));

		    /**
		     * 表格第七行设置，
		     * */
		    Cell cell7_1 = new Cell(new Paragraph("毕业院校", fontChinese));
		    table.addCell(cell7_1);
		    Cell cell7_2To4 = new Cell(latestTimeJyjl.getResumeXxmc());
		    table.addCell(cell7_2To4);
		    cell7_2To4.setColspan(3);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    Cell cell7_5 = new Cell(new Paragraph("专业", fontChinese));
		    table.addCell(cell7_5);
		    // Cell cell7_6To8 = new Cell(latestTimeJyjl.getResumeZyl() + latestTimeJyjl.getResumeZy());
		    Cell cell7_6To8 = new Cell(latestTimeJyjl.getResumeZyl());
		    table.addCell(cell7_6To8);
		    cell7_6To8.setColspan(3);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第八行设置，参加工作时间跨两行
		     * */
		    Cell cell8_1 = new Cell(new Paragraph("工作单位及部门", fontChinese));
		    table.addCell(cell8_1);
		    Cell cell8_2To4 = new Cell(latestTimeGzjl.getResumeGzgs());
		    table.addCell(cell8_2To4);
		    cell8_2To4.setColspan(3);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    Cell cell8_5 = new Cell(new Paragraph("职务", fontChinese));
		    table.addCell(cell8_5);
		    Cell cell8_6To8 = new Cell(latestTimeGzjl.getResumeGzcs());
		    table.addCell(cell8_6To8);
		    cell8_6To8.setColspan(3);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第九行设置
		     * */
		    Cell cell9_1 = new Cell(new Paragraph("拟应聘岗位1", fontChinese));
		    table.addCell(cell9_1);
		    Cell cell9_2To8 = new Cell(resume.getResumeNypgw1());
		    table.addCell(cell9_2To8);
		    cell9_2To8.setColspan(7);
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));
		    table.addCell(new Cell(""));

		    /**
		     * 表格第十行行设置，如果拟应聘岗位2存在的话
		     * */
		    if (resume.getResumeNypgw1() != null && !resume.getResumeNypgw1().equals("") && !resume.getResumeNypgw1().equals(fillContent)) {
			Cell cell10_1 = new Cell(new Paragraph("拟应聘岗位2", fontChinese));
			table.addCell(cell10_1);
			Cell cell10_2To8 = new Cell(resume.getResumeNypgw2());
			table.addCell(cell10_2To8);
			cell10_2To8.setColspan(7);
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
		    }

		    /**
		     * 简历第二部分 教育经历、工作经历
		     * */
		    int resumerowSpan = Jyjl.size() + Gzjl.size(); // 简历第二部分教育经历和学习经历所占的行数
		    Paragraph rowHeaderContent = new Paragraph("简历", fontChinese);
		    Cell rowHeader = new Cell(rowHeaderContent);
		    rowHeader.setVerticalAlignment(Element.ALIGN_CENTER);
		    table.addCell(rowHeader);
		    rowHeader.setRowspan(resumerowSpan);
		    /**
		     * 教育经历 按时间先后排列
		     */
		    int indexFor = 0; // 控制list的index
		    for (Resume_jyjl jyjlPer : Jyjl) {
			Paragraph timeDetail = new Paragraph();
			Paragraph detail = new Paragraph();
			if (indexFor != 0) {
			    table.addCell("");
			}
			timeDetail.add(jyjlPer.getResumeJdsj().substring(0, 7).replaceAll("-", ".") + "-" + jyjlPer.getResumeJdsj1().substring(0, 7).replaceAll("-", "."));
			detail.add(" ");
			detail.add(jyjlPer.getResumeXxmc());
			// detail.add(jyjlPer.getResumeZyl() + "-" + jyjlPer.getResumeZy());
			detail.add(jyjlPer.getResumeZyl());
			detail.add("  ");
			detail.add(jyjlPer.getResumeXl());
			Cell timeContent = new Cell(timeDetail);
			// timeContent.setWidth(arg0);;
			timeContent.setBorderWidthRight(0); // 设置时间行右、下边框不显示
			timeContent.setBorderWidthBottom(0);

			table.addCell(timeContent);
			Cell jyjlContent = new Cell(detail);
			jyjlContent.setBorderWidthBottom(0); // 是指内容行下不显示
			jyjlContent.setBorderWidthRight(1); // bug，内容行显示右边框
			table.addCell(jyjlContent);
			jyjlContent.setColspan(6);
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			table.addCell(new Cell(""));
			indexFor++;
		    }
		    /*
		     * for (int i = 0; i < Jyjl.size(); i++) {
		     * Paragraph timeDetail = new Paragraph();
		     * Paragraph detail = new Paragraph();
		     * if (i != 0) {
		     * table.addCell("");
		     * }
		     * timeDetail.add(Jyjl.get(i).getResumeJdsj().substring(0, 7).replaceAll("-", ".") + "-" + Jyjl.get(i).getResumeJdsj1().substring(0, 7).replaceAll("-", "."));
		     * detail.add(" ");
		     * detail.add(Jyjl.get(i).getResumeXxmc());
		     * // detail.add(Jyjl.get(i).getResumeZyl() + "-" + Jyjl.get(i).getResumeZy());
		     * detail.add(Jyjl.get(i).getResumeZyl());
		     * detail.add("  ");
		     * detail.add(Jyjl.get(i).getResumeXl());
		     * Cell timeContent = new Cell(timeDetail);
		     * // timeContent.setWidth(arg0);;
		     * timeContent.setBorderWidthRight(0); // 设置时间行右、下边框不显示
		     * timeContent.setBorderWidthBottom(0);
		     * 
		     * table.addCell(timeContent);
		     * Cell jyjlContent = new Cell(detail);
		     * jyjlContent.setBorderWidthBottom(0); // 是指内容行下不显示
		     * jyjlContent.setBorderWidthRight(1); // bug，内容行显示右边框
		     * table.addCell(jyjlContent);
		     * jyjlContent.setColspan(6);
		     * table.addCell(new Cell(""));
		     * table.addCell(new Cell(""));
		     * table.addCell(new Cell(""));
		     * table.addCell(new Cell(""));
		     * table.addCell(new Cell(""));
		     * }
		     */
		    /**
		     * 工作经历 按时间先后排列 ，由于查询是按时间降序排列，所以需要将list序列颠倒过来
		     */
		    if (Gzjl.size() > 0) {
			for (int j = Jyjl.size() - 1; j >= 0; j--) {
			    table.addCell("");
			    Paragraph timeDetail = new Paragraph();
			    Paragraph detail = new Paragraph();
			    timeDetail.add(Gzjl.get(j).getResumeGzsj().substring(0, 7).replaceAll("-", ".") + "-" + Gzjl.get(j).getResumeGzsj1().substring(0, 7).replaceAll("-", "."));
			    detail.add(" ");
			    detail.add(Gzjl.get(j).getResumeGzgs());
			    detail.add(Gzjl.get(j).getResumeGzcs());
			    detail.add("  ");
			    detail.add(Gzjl.get(j).getResumeGzms());
			    Cell timeContent = new Cell(timeDetail);
			    timeContent.setBorderWidthRight(0); // 设置时间行右、下边框不显示
			    timeContent.setBorderWidthBottom(0);
			    table.addCell(timeContent);

			    Cell GZjlContent = new Cell(detail);
			    GZjlContent.setBorderWidthBottom(0); // 设置内容行下边框不显示
			    GZjlContent.setBorderWidthRight(1); // bug，内容行显示右边框
			    table.addCell(GZjlContent);
			    GZjlContent.setColspan(6);
			    table.addCell(new Cell(""));
			    table.addCell(new Cell(""));
			    table.addCell(new Cell(""));
			    table.addCell(new Cell(""));
			    table.addCell(new Cell(""));
			}
		    }
		    /**
		     * 奖惩情况
		     */
		    // 表头
		    Paragraph jcqkHeaderContent = new Paragraph("奖惩  情况", fontChinese);
		    Cell jcqkHeader = new Cell(jcqkHeaderContent);
		    jcqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    // jcqkHeader.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		    table.addCell(jcqkHeader);
		    jcqkHeader.setRowspan(4);
		    // 内容，默认跨四行
		    Paragraph jcqkContentPar = new Paragraph(resume.getResumeJcqk());
		    jcqkContentPar.setFirstLineIndent(10); // 首行缩进
		    Cell jcqkContentCell = new Cell(jcqkContentPar);
		    table.addCell(jcqkContentCell);
		    jcqkContentCell.setRowspan(4);
		    jcqkContentCell.setColspan(7);
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    // 默认空白三行，控制单元格的高度
		    int blackRowNum = 3;
		    int blackCell = blackRowNum * 8;
		    while (blackCell > 0) {
			table.addCell("");
			blackCell--;
		    }
		    /**
		     * 培训情况
		     */
		    // 表头
		    Paragraph pxqkHeaderContent = new Paragraph("培训  情况", fontChinese);
		    Cell pxqkHeader = new Cell(pxqkHeaderContent);
		    pxqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    table.addCell(pxqkHeader);
		    pxqkHeader.setRowspan(4);
		    // 内容，默认跨四行
		    Paragraph pxqkContentPar = new Paragraph(resume.getResumePxqk());
		    pxqkContentPar.setFirstLineIndent(10);
		    Cell pxqkContentCell = new Cell(pxqkContentPar);
		    table.addCell(pxqkContentCell);
		    pxqkContentCell.setRowspan(4);
		    pxqkContentCell.setColspan(7);
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    // 默认空白三行，控制单元格的高度
		    blackCell = blackRowNum * 8;
		    while (blackCell > 0) {
			table.addCell("");
			blackCell--;
		    }
		    /**
		     * 熟悉何种专业技能有何种特长
		     */
		    // 表头
		    Paragraph sxjnqkHeaderContent = new Paragraph("熟悉何种专业技能有何种特长", fontChinese);
		    Cell sxjnqkHeader = new Cell(sxjnqkHeaderContent);
		    sxjnqkHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    table.addCell(sxjnqkHeader);
		    sxjnqkHeader.setRowspan(4);
		    // 内容，默认跨四行
		    Paragraph sxjnqkContentPar = new Paragraph(resume.getResumeSxhzjn());
		    sxjnqkContentPar.setFirstLineIndent(10);
		    Cell sxjnqkContentCell = new Cell(sxjnqkContentPar);
		    table.addCell(sxjnqkContentCell);
		    sxjnqkContentCell.setRowspan(4);
		    sxjnqkContentCell.setColspan(7);
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    // 默认空白三行，控制单元格的高度
		    blackCell = blackRowNum * 8;
		    while (blackCell > 0) {
			table.addCell("");
			blackCell--;
		    }
		    /**
		     * 兴趣爱好
		     */
		    // 表头
		    Paragraph xqahHeaderContent = new Paragraph("兴趣  爱好", fontChinese);
		    Cell xqahHeader = new Cell(xqahHeaderContent);
		    xqahHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    table.addCell(xqahHeader);
		    xqahHeader.setRowspan(4);
		    // 内容，默认跨四行
		    Paragraph xqahContentPar = new Paragraph(resume.getResumeXqah());
		    xqahContentPar.setFirstLineIndent(10);
		    Cell xqahContentCell = new Cell(xqahContentPar);
		    table.addCell(xqahContentCell);
		    xqahContentCell.setRowspan(4);
		    xqahContentCell.setColspan(7);
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    // 默认空白三行，控制单元格的高度
		    blackCell = blackRowNum * 8;
		    while (blackCell > 0) {
			table.addCell("");
			blackCell--;
		    }

		    /**
		     * 主要家庭成员及社会关系
		     * 
		     */
		    // 表头
		    Paragraph jtcyHeaderContent = new Paragraph("主要家庭成员及社会关系", fontChinese);
		    Cell jtcyhHeader = new Cell(jtcyHeaderContent);
		    jtcyhHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    table.addCell(jtcyhHeader);
		    jtcyhHeader.setRowspan(Pxjl.size() + 1);

		    table.addCell(new Cell(new Paragraph("关系", fontChinese)));
		    table.addCell(new Cell(new Paragraph("姓名", fontChinese)));
		    table.addCell(new Cell(new Paragraph("出生年月", fontChinese)));
		    table.addCell(new Cell(new Paragraph("政治面貌", fontChinese)));
		    Cell jobAnd_ = new Cell(new Paragraph("工作单位及职务", fontChinese));
		    table.addCell(jobAnd_);
		    jobAnd_.setColspan(3);
		    table.addCell("");
		    table.addCell("");
		    for (Resume_pxjl pxjlPer : Pxjl) {
			table.addCell("");
			table.addCell(new Cell(pxjlPer.getResumePxmc()));
			table.addCell(new Cell(pxjlPer.getResumePxjg()));
			table.addCell(new Cell(pxjlPer.getResumePxsj().substring(0, 7).replaceAll("-", ".")));
			table.addCell(new Cell(pxjlPer.getResumePxsj1()));
			Cell jobAndContent = new Cell(pxjlPer.getResumePxnr());
			table.addCell(jobAndContent);
			jobAndContent.setColspan(3);
			table.addCell("");
			table.addCell("");
		    }

		    /**
		     * 最后一行
		     */
		    Cell telphoneCell = new Cell(new Paragraph("联系电话(手机)", fontChinese));
		    telphoneCell.setColspan(2);
		    table.addCell(telphoneCell);

		    Cell telphoneCellContent = new Cell(resume.getResumeTelphone());
		    telphoneCellContent.setColspan(2);
		    table.addCell(telphoneCellContent);

		    Cell emailCell = new Cell(new Paragraph("电子邮箱", fontChinese));
		    table.addCell(emailCell);

		    Cell emailCellContent = new Cell(resume.getResumeEmail());
		    emailCellContent.setColspan(3);
		    table.addCell(emailCellContent);

		    document.add(table);
		    document.close();
		} catch (Exception e) {
		    logInfo("/SavePdf", resumeId + "," + contextString);
		    e.printStackTrace();
		}
		return path + resume.getResumeName() + "_" + resume.getResumeSex() + "_" + resume.getResumeSfzh() + ".doc" ;
	    }

	/**
	 * 目录打包
	 * 
	 * @param files
	 * @return 返回压缩包名称
	 * @throws ZipException
	 * @throws IOException
	 */
	public String zipDirs(ArrayList files) throws ZipException, IOException
	{
		String rsPath = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		File fileRsPath = new File(rsPath);
		if (!fileRsPath.exists() || !fileRsPath.isDirectory())
			fileRsPath.mkdirs();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sdf.format(date);

		File file = new File(rsPath + strDate);
		if (!file.exists())
			file.mkdirs();

		for (int i = 0; i < files.size(); i++)
		{
			String sFile = files.get(i).toString();
			File testFile = new File(sFile);
			if (!testFile.isFile())
				continue;
			File destFile = new File(rsPath + strDate + "/" + testFile.getName());
			FileUtils.copyFile(testFile, destFile);
		}

		String zipFilePath = CompressUtil.zip(rsPath + strDate);
		if (zipFilePath == null || "".equals(zipFilePath))
			return "";
		else
		{
			deleteDir(file);
			File extFile = new File(zipFilePath);
			return extFile.getName();
		}
	}

	public static boolean deleteDir(File dir)
	{
		if (dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 根据简历ID和 用户ID生成文件名称
	 * 
	 * @param resumeid
	 * @param userid
	 * @return
	 */
	public String getExFileName(String resumeid, String userid)
	{
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeid);
		List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeid);
		List<MyApplication> myApplications = jtjlkService.selectMyApplication(userid);

		StringBuffer sbStr = new StringBuffer();

		if (Jyjl != null)
		{
			// 学校名称
			if (sbStr.length() > 0)
				sbStr.append(Jyjl.size() > 0 ? ("_" + Jyjl.get(Jyjl.size() - 1).getResumeXxmc()) : "");
			else
				sbStr.append(Jyjl.size() > 0 ? (Jyjl.get(Jyjl.size() - 1).getResumeXxmc()) : "");
			// 专业
			if (sbStr.length() > 0)
				sbStr.append(Jyjl.size() > 0 ? ("_" + Jyjl.get(Jyjl.size() - 1).getResumeZy()) : "");
			else
				sbStr.append(Jyjl.size() > 0 ? (Jyjl.get(Jyjl.size() - 1).getResumeZy()) : "");
		}
		int i = 0;
		// 报考职位
		if (myApplications != null)
			sbStr.append("_");
		for (MyApplication myApplication : myApplications)
		{
			i += 1;
			sbStr.append(myApplication.getSqgw());
			if (myApplications.size() > 1 && i < myApplications.size())
				sbStr.append("+");
		}

		if (resume != null)
		{
			sbStr.append("_" + resume.getResumeName());
			sbStr.append("_" + resume.getResumeMqszcsProvince() + resume.getResumeMqszcsCity());
			sbStr.append("_" + resume.getResumeSex());
			sbStr.append(Jyjl.size() > 0 ? ("_" + Jyjl.get(Jyjl.size() - 1).getResumeXl()) : "");
		}

		return StringUtils.deleteWhitespace(sbStr.toString().replace("/", "／"));

	}

	public static String Html2Text(String inputString)
	{
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try
		{
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
																										// }
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e)
		{
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr.replace("&nbsp;", "");// 返回文本字符串
	}
}

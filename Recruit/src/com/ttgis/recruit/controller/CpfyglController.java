package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ttgis.recruit.domain.Member;
import com.ttgis.recruit.domain.QueryMember;
import com.ttgis.recruit.service.MemberService;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。CpfyglController
 * @创建人： 范井龙
 * @日期：
 * @描述： 测评费用管理
 * @版本号：
 */
@Controller
public class CpfyglController
{
	@Resource
	MemberService memberService;

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

	@RequestMapping(value = "/Cpfygl", method = RequestMethod.GET)
	public ModelAndView Cpfygl()
	{
		return new ModelAndView("Cpfygl/Cpfygl");
	}

	/**
	 * 分页查询（已经审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/CpfyData", method = RequestMethod.POST)
	public List<Member> CpfyData(QueryMember qm)
	{
		logInfo("CpfyData", JSONArray.fromObject(qm).toString());
		List<Member> memberList = memberService.CpfyData(qm);
		return memberList;
	}

	@ResponseBody
	@RequestMapping(value = "/CpfyDataCount", method = RequestMethod.POST)
	public int CpfyDataCount(QueryMember qm)
	{
		logInfo("CpfyDataCount", JSONArray.fromObject(qm).toString());
		int intCount = memberService.CpfyDataCount(qm);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryCpfyglById", method = RequestMethod.GET)
	public ModelAndView queryCpfyglById(String memberId)
	{
		logInfo("queryCpfyglById", memberId);
		Member member = memberService.selectByPrimaryKey(memberId);
		return new ModelAndView("Cpfygl/CpfyglAdd", "member", member);
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/SaveCpfygl", method = RequestMethod.POST)
	public ModelAndView SaveCpfygl(Member member)
	{
		logInfo("SaveCpfygl", JSONArray.fromObject(member).toString());
		memberService.updateByPrimaryKeySelective(member);
		return new ModelAndView("redirect:/Cpfygl");
	}

	// ===================================导出费用管理（会员）====================================
	@RequestMapping(value = "/exportCpfygl", method = RequestMethod.GET)
	public ModelAndView Image(HttpServletRequest request, HttpServletResponse response)
	{
		logInfo("exportCpfygl", "");
		List<Member> cpfyglList = memberService.queryCpfygl();
		File file = new File(request.getSession().getServletContext().getRealPath("pdf"));
		String path = new java.util.Date().getTime() + "Cpfygl.pdf";
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
					logInfo("exportCpfygl", "******Error");
					e.printStackTrace();
				}
			}
		}
		try
		{
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(file1));
			doc.open();
			// 标题字体
			BaseFont bf = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font titleFont = new Font(bf, 18, Font.BOLD);
			BaseFont bfComic = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			PdfPTable table = new PdfPTable(9);
			PdfPCell cell = new PdfPCell(new Phrase("测评费用（会员）", titleFont));
			cell.setUseAscender(true);
			cell.setUseDescender(true);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(9);
			table.addCell(cell);
			table.addCell(new PdfPCell(new Phrase("序号", font)));
			table.addCell(new PdfPCell(new Phrase("单位代码", font)));
			table.addCell(new PdfPCell(new Phrase("单位名称", font)));
			table.addCell(new PdfPCell(new Phrase("会员等级", font)));
			table.addCell(new PdfPCell(new Phrase("应缴纳费用", font)));
			table.addCell(new PdfPCell(new Phrase("已缴费用", font)));
			table.addCell(new PdfPCell(new Phrase("测评次数", font)));
			table.addCell(new PdfPCell(new Phrase("剩余次数", font)));
			table.addCell(new PdfPCell(new Phrase("费用余额", font)));
			int a = 1;
			for (Member member : cpfyglList)
			{
				table.addCell(new PdfPCell(new Phrase((a++) + "", font)));
				table.addCell(new PdfPCell(new Phrase(member.getMemberIdcard(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getMemberName(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getMemberHydj(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getYjf(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getMemberYfkye())));
				table.addCell(new PdfPCell(new Phrase(member.getCpcs(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getSycpcs(), font)));
				table.addCell(new PdfPCell(new Phrase(member.getYfye(), font)));
			}
			doc.add(table);
			doc.close();
		} catch (Exception e)
		{
			logInfo("exportCpfygl", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("pdf/cpfyglPdf", "uri", "pdf/" + path);
	}
}

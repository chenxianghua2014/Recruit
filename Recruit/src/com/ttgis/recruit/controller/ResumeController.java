package com.ttgis.recruit.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.MyApplication;
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
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Xxmc;
import com.ttgis.recruit.domain.Zpzy;
import com.ttgis.recruit.service.JtjlkService;
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
import com.ttgis.recruit.service.XxmcService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.utility.HtmlSpider;
import com.ttgis.recruit.utility.HtmlToDoc;
import com.ttgis.recruit.utility.PropertiesUtils;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@Controller
public class ResumeController
{

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
	@Autowired
	private ZwglService zwglServices;
	@Autowired
	private JtjlkService jtjlkService;
	@Autowired
	private XxmcService xxmcService;

	static Logger log = Logger.getLogger(ResumeController.class);
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

	// 个人简历
	@RequestMapping(value = "/Grjl", method = RequestMethod.GET)
	public ModelAndView Grjl(HttpSession session)
	{
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		logInfo("Grjl", JSONArray.fromObject(userinfo).toString());
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(userinfo.getUserId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		mv.addObject("userinfo", userinfo);
		return new ModelAndView("Grjl/Grxx", "mv", mv);
	}

	// 加载教育经历
	@ResponseBody
	@RequestMapping(value = "/LoadJyjl", method = RequestMethod.POST)
	public List<Resume_jyjl> LoadJyjl(HttpSession session)
	{
		logInfo("LoadJyjl", "");
		List<Resume_jyjl> jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return jyjls;
	}

	// 加载校内奖励
	@ResponseBody
	@RequestMapping(value = "/LoadXnjl", method = RequestMethod.POST)
	public List<Resume_xnjl> LoadXnjl(HttpSession session)
	{
		logInfo("LoadXnjl", "");
		List<Resume_xnjl> xnjls = resume_xnjlService.getlistResumeXnjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return xnjls;
	}

	// 加载校内职务
	@ResponseBody
	@RequestMapping(value = "/LoadXnzw", method = RequestMethod.POST)
	public List<Resume_xnzw> LoadXnzw(HttpSession session)
	{
		logInfo("LoadXnzw", "");
		List<Resume_xnzw> xnzws = resume_xnzwService.getlistResumeXnzwByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return xnzws;
	}

	// 加载实践经历
	@ResponseBody
	@RequestMapping(value = "/LoadSjjl", method = RequestMethod.POST)
	public List<Resume_sjjl> LoadSjjl(HttpSession session)
	{
		logInfo("LoadSjjl", "");
		List<Resume_sjjl> sjjls = resume_sjjlService.getlistResumeSjjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return sjjls;
	}

	// 加载实习经历
	@ResponseBody
	@RequestMapping(value = "/LoadSxjl", method = RequestMethod.POST)
	public List<Resume_sx> LoadSxjl(HttpSession session)
	{
		logInfo("LoadSxjl", "");
		List<Resume_sx> sxjls = resume_sxService.getlistResumeSxByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return sxjls;
	}

	// 加载工作经历
	@ResponseBody
	@RequestMapping(value = "/LoadGzjl", method = RequestMethod.POST)
	public List<Resume_gzjl> LoadGzjl(HttpSession session)
	{
		logInfo("LoadGzjl", "");
		List<Resume_gzjl> gzjls = resume_gzjlService.getlistResumeGzjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return gzjls;
	}

	// 加载项目经验
	@ResponseBody
	@RequestMapping(value = "/LoadXmjy", method = RequestMethod.POST)
	public List<Resume_xmjy> LoadXmjy(HttpSession session)
	{
		logInfo("LoadXmjy", "");
		List<Resume_xmjy> xmjls = resume_xmjyService.getlistResumeXmjyByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return xmjls;
	}

	// 加载培训经历
	@ResponseBody
	@RequestMapping(value = "/LoadPxjl", method = RequestMethod.POST)
	public List<Resume_pxjl> LoadPxjl(HttpSession session)
	{
		logInfo("LoadPxjl", "");
		List<Resume_pxjl> pxjls = resume_pxjlService.getlistResumePxjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return pxjls;
	}

	// 加载语言能力
	@ResponseBody
	@RequestMapping(value = "/LoadYynl", method = RequestMethod.POST)
	public List<Resume_yynl> LoadYynl(HttpSession session)
	{
		logInfo("LoadYynl", "");
		List<Resume_yynl> yynls = resume_yynlService.getlistResumeYynlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return yynls;
	}

	// 加载其他信息
	@ResponseBody
	@RequestMapping(value = "/LoadQtxx", method = RequestMethod.POST)
	public List<Resume_qtxx> LoadQtxx(HttpSession session)
	{
		logInfo("LoadQtxx", "");
		List<Resume_qtxx> qtxxs = resume_qtxxService.getlistResumeQtxxByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return qtxxs;
	}

	// 加载附件
	@ResponseBody
	@RequestMapping(value = "/LoadFj", method = RequestMethod.POST)
	public List<Resume_fj> LoadFj(HttpSession session)
	{
		logInfo("LoadFj", "");
		List<Resume_fj> fjs = resume_fjService.getlistResumeFjByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return fjs;
	}

	// 加载附件
	@ResponseBody
	@RequestMapping(value = "/LoadZzjl", method = RequestMethod.POST)
	public Resume LoadZzjl(HttpSession session)
	{
		logInfo("LoadZzjl", "");
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		if (resume == null)
			return null;
		return resume;
	}

	// 加载IT技能
	@ResponseBody
	@RequestMapping(value = "/LoadITjn", method = RequestMethod.POST)
	public List<Resume_ITjn> LoadITjn(HttpSession session)
	{
		logInfo("LoadITjn", "");
		List<Resume_ITjn> ITjns = resume_ITjnService.getlistResumeITjnByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return ITjns;
	}

	// 加载证书
	@ResponseBody
	@RequestMapping(value = "/LoadZs", method = RequestMethod.POST)
	public List<Resume_zs> LoadZs(HttpSession session)
	{
		logInfo("LoadZs", "");
		List<Resume_zs> zss = resume_zsService.getlistResumeZsByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
		return zss;
	}

	// 预览简历RESUME查询
	@RequestMapping(value = "/expjl", method = RequestMethod.GET)
	public ModelAndView expjl(@RequestParam("resumeId") String resumeId, @RequestParam("userid") String userid)
	{
		logInfo("Yljl   userid", resumeId + "||" + userid);
		if (resumeId == null || resumeId == "" || userid == null || userid == "")
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");

		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
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
		List<MyApplication> myApplications = jtjlkService.selectMyApplication(userid);
		List<Resume_jyjl> SelJyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeId);
		List<Resume_jyjl> Jyjl = new ArrayList<Resume_jyjl>();

		for (int i = SelJyjl.size() - 1; i >= 0; i--)
		{
			Jyjl.add(SelJyjl.get(i));
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		mv.addObject("Jyjl", Jyjl);
		mv.addObject("Xnjl", Xnjl);
		mv.addObject("Xnzw", Xnzw);
		mv.addObject("Sjjl", Sjjl);
		mv.addObject("Sxjl", Sxjl);
		mv.addObject("Gzjl", Gzjl);
		mv.addObject("Xmjy", Xmjy);
		mv.addObject("Pxjl", Pxjl);
		mv.addObject("Yynl", Yynl);
		mv.addObject("Qtxx", Qtxx);
		mv.addObject("Fj", Fj);
		mv.addObject("ITjn", ITjn);
		mv.addObject("Zs", Zs);
		mv.addObject("Ma", myApplications);
		return new ModelAndView("Grjl/jl", "mv", mv);
	}

	// 预览简历RESUME查询
	@RequestMapping(value = "/Yljl", method = RequestMethod.GET)
	public ModelAndView Yljl(String resumeId)
	{
		logInfo("Yljl", resumeId);
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
		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		mv.addObject("Jyjl", Jyjl);
		mv.addObject("Xnjl", Xnjl);
		mv.addObject("Xnzw", Xnzw);
		mv.addObject("Sjjl", Sjjl);
		mv.addObject("Sxjl", Sxjl);
		mv.addObject("Gzjl", Gzjl);
		mv.addObject("Xmjy", Xmjy);
		mv.addObject("Pxjl", Pxjl);
		mv.addObject("Yynl", Yynl);
		mv.addObject("Qtxx", Qtxx);
		mv.addObject("Fj", Fj);
		mv.addObject("ITjn", ITjn);
		mv.addObject("Zs", Zs);
		return new ModelAndView("Grjl/Yljl", "mv", mv);
		//return new ModelAndView("Grjl/jl", "mv", mv);  
	}

	// 预览简历RESUME查询
	@RequestMapping(value = "/YljlHr", method = RequestMethod.GET)
	public ModelAndView YljlHr(String resumeId, String jtjlkId)
	{
		logInfo("YljlHr", resumeId + "," + jtjlkId);
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
		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		mv.addObject("Jyjl", Jyjl);
		mv.addObject("Xnjl", Xnjl);
		mv.addObject("Xnzw", Xnzw);
		mv.addObject("Sjjl", Sjjl);
		mv.addObject("Sxjl", Sxjl);
		mv.addObject("Gzjl", Gzjl);
		mv.addObject("Xmjy", Xmjy);
		mv.addObject("Pxjl", Pxjl);
		mv.addObject("Yynl", Yynl);
		mv.addObject("Qtxx", Qtxx);
		mv.addObject("Fj", Fj);
		mv.addObject("ITjn", ITjn);
		mv.addObject("Zs", Zs);
		mv.addObject("JtjlkId", jtjlkId);
		return new ModelAndView("Grjl/Yljl", "mv", mv);
	}

	// 预览简历RESUME查询
	@RequestMapping(value = "/YljlHrJtjlk", method = RequestMethod.GET)
	public ModelAndView YljlHrJtjlk(String resumeId, String jtjlkId)
	{
		logInfo("YljlHrJtjlk", resumeId + "," + jtjlkId);
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
		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		mv.addObject("Jyjl", Jyjl);
		mv.addObject("Xnjl", Xnjl);
		mv.addObject("Xnzw", Xnzw);
		mv.addObject("Sjjl", Sjjl);
		mv.addObject("Sxjl", Sxjl);
		mv.addObject("Gzjl", Gzjl);
		mv.addObject("Xmjy", Xmjy);
		mv.addObject("Pxjl", Pxjl);
		mv.addObject("Yynl", Yynl);
		mv.addObject("Qtxx", Qtxx);
		mv.addObject("Fj", Fj);
		mv.addObject("ITjn", ITjn);
		mv.addObject("Zs", Zs);
		mv.addObject("JtjlkId", "jtjlk");
		return new ModelAndView("Grjl/Yljl", "mv", mv);
	}

	// 基本信息
	@RequestMapping(value = "/SaveJbxx", method = RequestMethod.POST)
	public ModelAndView SaveJbxx(Resume resume, String contextString, HttpSession session, String resumeId, HttpServletRequest request)
	{
		logInfo("SaveJbxx", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		if (resumeService.selectByPrimaryKeySelectiveById(resumeId) != null)
		{
			resumeService.updateByPrimaryKeySelective(resume);
			// SavePdf(resumeId, contextString, request);
		} else
		{
			resume.setResumeAddtime(new Date());
			resume.setResumeDelflag((long) 1);
			resume.setResumeId(userinfo.getUserId());
			resumeService.insertSelective(resume);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 联系方式
	@RequestMapping(value = "/SaveLxfs", method = RequestMethod.POST)
	public ModelAndView SaveLxfs(Resume resume, String contextString, String resumeId, HttpServletRequest request)
	{
		logInfo("SaveLxfs", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		resumeService.updateByPrimaryKeySelective(resume);
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}
	
	// 其他信息
	@RequestMapping(value = "/SaveQtxx", method = RequestMethod.POST)
	public ModelAndView SaveQtxx(Resume resume, String contextString, String resumeId, HttpServletRequest request)
	{
		logInfo("SaveQtxx", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		resumeService.updateByPrimaryKeySelective(resume);
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}
	
	// 教育背景 cxh
	@RequestMapping(value = "/SaveJybj", method = RequestMethod.POST)
	public ModelAndView SaveJybj(Resume resume, String contextString, String resumeId, HttpServletRequest request)
	{
		logInfo("SaveJybj", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		resumeService.updateByPrimaryKeySelective(resume);
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}
	
	

	// 证书
	@RequestMapping(value = "/SaveZs", method = RequestMethod.POST)
	public ModelAndView SaveZs(Resume resume, String contextString, String resumeId, HttpServletRequest request)
	{
		logInfo("/SaveZs", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		resumeService.updateByPrimaryKeySelective(resume);
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	/*
	 * // 删除证书
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/DeleteZs", method = RequestMethod.POST) public
	 * ModelAndView DeleteZs(Resume resume, String resumeId, String
	 * contextString, HttpServletRequest request) { resume.setResumeHdzs("");
	 * resume.setResumeQtzs("");
	 * resumeService.updateByPrimaryKeySelective(resume); // SavePdf(resumeId,
	 * contextString, request); return new ModelAndView("Grjl/Grxx", "resume",
	 * resume); }
	 */

	// IT技能

	@RequestMapping(value = "/SaveItjn", method = RequestMethod.POST)
	public ModelAndView SaveITjn(Resume resume, String contextString, String resumeId, HttpServletRequest request)
	{
		logInfo("/SaveItjn", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
		resumeService.updateByPrimaryKeySelective(resume);
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 继续添加教育经历
	@ResponseBody
	@RequestMapping(value = "/SaveJyjlagain", method = RequestMethod.POST)
	public ModelAndView SaveJyjlagain(@RequestParam("resumeJyjlid") String resumeJyjlid, Resume_jyjl resume_jyjl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveJyjlagain", resumeJyjlid + "------," + ",------" + JSONArray.fromObject(resume_jyjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_jyjl.getResumeJyjlid() != "" && resume_jyjl.getResumeJyjlid() != null)
		{
			resume_jyjlService.updateByJyjlId(resume_jyjl);
		} else
		{
			resume_jyjl.setResumeAddtime(new Date());
			resume_jyjl.setResumeDelflag((long) 1);
			resume_jyjl.setResumeJyjlid(RandomGUIDUtil.generateKey());
			resume_jyjlService.JyjlinsertSelective(resume_jyjl);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_jyjl", resume_jyjl);
	}

	// 继续添加校内奖励
	@ResponseBody
	@RequestMapping(value = "/SaveXnjlagain", method = RequestMethod.POST)
	public ModelAndView SaveXnjlagain(@RequestParam("resumeXnjlid") String resumeXnjlid, Resume_xnjl resume_xnjl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveXnjlagain", resumeXnjlid + "------," + "------," + JSONArray.fromObject(resume_xnjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xnjl.getResumeXnjlid() != "" && resume_xnjl.getResumeXnjlid() != null)
		{
			resume_xnjlService.updateByXnjlId(resume_xnjl);
		} else
		{
			resume_xnjl.setResumeAddtime(new Date());
			resume_xnjl.setResumeDelflag((long) 1);
			resume_xnjl.setResumeXnjlid(RandomGUIDUtil.generateKey());
			resume_xnjlService.XnjlinsertSelective(resume_xnjl);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_xnjl", resume_xnjl);
	}

	// 继续添加校内职务
	@ResponseBody
	@RequestMapping(value = "/SaveXnzwagain", method = RequestMethod.POST)
	public ModelAndView SaveXnzwagain(@RequestParam("resumeXnzwid") String resumeXnzwid, Resume_xnzw resume_xnzw, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveXnzwagain", resumeXnzwid + "------," + "------," + JSONArray.fromObject(resume_xnzw).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xnzw.getResumeXnzwid() != "" && resume_xnzw.getResumeXnzwid() != null)
		{
			resume_xnzwService.updateByXnzwId(resume_xnzw);
		} else
		{
			resume_xnzw.setResumeAddtime(new Date());
			resume_xnzw.setResumeDelflag((long) 1);
			resume_xnzw.setResumeXnzwid(RandomGUIDUtil.generateKey());
			resume_xnzwService.XnzwinsertSelective(resume_xnzw);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_xnzw", resume_xnzw);
	}

	// 继续添加实践经历
	@ResponseBody
	@RequestMapping(value = "/SaveSjjlagain", method = RequestMethod.POST)
	public ModelAndView SaveSjjlagain(@RequestParam("resumeSjjlid") String resumeSjjlid, Resume_sjjl resume_sjjl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveSjjlagain", resumeSjjlid + "------," + "------," + JSONArray.fromObject(resume_sjjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_sjjl.getResumeSjjlid() != "" && resume_sjjl.getResumeSjjlid() != null)
		{
			resume_sjjlService.updateBySjjlId(resume_sjjl);
		} else
		{
			resume_sjjl.setResumeAddtime(new Date());
			resume_sjjl.setResumeDelflag((long) 1);
			resume_sjjl.setResumeSjjlid(RandomGUIDUtil.generateKey());
			resume_sjjlService.SjjlinsertSelective(resume_sjjl);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_sjjl", resume_sjjl);
	}

	// 继续添加实习经历
	@ResponseBody
	@RequestMapping(value = "/SaveSxjlagain", method = RequestMethod.POST)
	public ModelAndView SaveSxjlagain(@RequestParam("resumeSxid") String resumeSxid, Resume_sx resume_sx, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveSxjlagain", resumeSxid + "------," + "------," + JSONArray.fromObject(resume_sx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_sx.getResumeSxid() != "" && resume_sx.getResumeSxid() != null)
		{
			resume_sxService.updateBySxId(resume_sx);
		} else
		{
			resume_sx.setResumeAddtime(new Date());
			resume_sx.setResumeDelflag((long) 1);
			resume_sx.setResumeSxid(RandomGUIDUtil.generateKey());
			resume_sxService.SxinsertSelective(resume_sx);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_sx", resume_sx);
	}

	// 继续添加工作经历
	@ResponseBody
	@RequestMapping(value = "/SaveGzjlagain", method = RequestMethod.POST)
	public ModelAndView SaveGzjlagain(@RequestParam("resumeGzjlid") String resumeGzjlid, Resume_gzjl resume_gzjl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveGzjlagain", resumeGzjlid + "------," + "------," + JSONArray.fromObject(resume_gzjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_gzjl.getResumeGzjlid() != "" && resume_gzjl.getResumeGzjlid() != null)
		{
			resume_gzjlService.updateByGzjlId(resume_gzjl);
		} else
		{
			resume_gzjl.setResumeAddtime(new Date());
			resume_gzjl.setResumeDelflag((long) 1);
			resume_gzjl.setResumeGzjlid(RandomGUIDUtil.generateKey());
			resume_gzjlService.GzjlinsertSelective(resume_gzjl);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_gzjl", resume_gzjl);
	}

	// 继续添加项目经验
	@ResponseBody
	@RequestMapping(value = "/SaveXmjyagain", method = RequestMethod.POST)
	public ModelAndView SaveXmjyagain(@RequestParam("resumeXmjyid") String resumeXmjyid, Resume_xmjy resume_xmjy, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveXmjyagain", resumeXmjyid + "------," + "------," + JSONArray.fromObject(resume_xmjy).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xmjy.getResumeXmjyid() != "" && resume_xmjy.getResumeXmjyid() != null)
		{
			resume_xmjyService.updateByXmjyId(resume_xmjy);
		} else
		{
			resume_xmjy.setResumeAddtime(new Date());
			resume_xmjy.setResumeDelflag((long) 1);
			resume_xmjy.setResumeXmjyid(RandomGUIDUtil.generateKey());
			resume_xmjyService.XmjyinsertSelective(resume_xmjy);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_xmjy", resume_xmjy);
	}

	// 继续添加培训经历
	@ResponseBody
	@RequestMapping(value = "/SavePxjlagain", method = RequestMethod.POST)
	public ModelAndView SavePxjlagain(@RequestParam("resumePxjlid") String resumePxjlid, Resume_pxjl resume_pxjl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SavePxjlagain", resumePxjlid + "------," + "------," + JSONArray.fromObject(resume_pxjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_pxjl.getResumePxjlid() != "" && resume_pxjl.getResumePxjlid() != null)
		{
			resume_pxjlService.updateByPxjlId(resume_pxjl);
		} else
		{
			resume_pxjl.setResumeAddtime(new Date());
			resume_pxjl.setResumeDelflag((long) 1);
			resume_pxjl.setResumePxjlid(RandomGUIDUtil.generateKey());
			resume_pxjlService.PxjlinsertSelective(resume_pxjl);

		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_pxjl", resume_pxjl);
	}

	// 继续添加语言能力
	@ResponseBody
	@RequestMapping(value = "/SaveYynlagain", method = RequestMethod.POST)
	public ModelAndView SaveYynlagain(@RequestParam("resumeYynlid") String resumeYynlid, Resume_yynl resume_yynl, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveYynlagain", resumeYynlid + "------," + "------," + JSONArray.fromObject(resume_yynl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_yynl.getResumeYynlid() != "" && resume_yynl.getResumeYynlid() != null)
		{
			resume_yynlService.updateByYynlId(resume_yynl);
		} else
		{
			resume_yynl.setResumeAddtime(new Date());
			resume_yynl.setResumeDelflag((long) 1);
			resume_yynl.setResumeYynlid(RandomGUIDUtil.generateKey());
			resume_yynlService.YynlinsertSelective(resume_yynl);
		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_yynl", resume_yynl);
	}

	// 继续添加其他信息
	@ResponseBody
	@RequestMapping(value = "/SaveQtxxagain", method = RequestMethod.POST)
	public ModelAndView SaveQtxxagain(@RequestParam("resumeQtxxid") String resumeQtxxid, Resume_qtxx resume_qtxx, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveQtxxagain", resumeQtxxid + "------," + "------," + JSONArray.fromObject(resume_qtxx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_qtxx.getResumeQtxxid() != "" && resume_qtxx.getResumeQtxxid() != null)
		{
			resume_qtxxService.updateByQtxxId(resume_qtxx);
		} else
		{
			resume_qtxx.setResumeAddtime(new Date());
			resume_qtxx.setResumeDelflag((long) 1);
			resume_qtxx.setResumeQtxxid(RandomGUIDUtil.generateKey());
			resume_qtxxService.QtxxinsertSelective(resume_qtxx);
		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_qtxx", resume_qtxx);
	}

	// 继续添加IT技能
	@ResponseBody
	@RequestMapping(value = "/SaveITjnagain", method = RequestMethod.POST)
	public ModelAndView SaveITjnagain(@RequestParam("resumeITjnid") String resumeITjnid, Resume_ITjn resume_ITjn, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveITjnagain", resumeITjnid + "------," + "------," + JSONArray.fromObject(resume_ITjn).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_ITjn.getResumeITjnid() != "" && resume_ITjn.getResumeITjnid() != null)
		{
			resume_ITjnService.updateByITjnId(resume_ITjn);
		} else
		{
			resume_ITjn.setResumeAddtime(new Date());
			resume_ITjn.setResumeDelflag((long) 1);
			resume_ITjn.setResumeITjnid(RandomGUIDUtil.generateKey());
			resume_ITjnService.ITjninsertSelective(resume_ITjn);
		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_ITjn", resume_ITjn);
	}

	// 继续添加证书
	@ResponseBody
	@RequestMapping(value = "/SaveZsagain", method = RequestMethod.POST)
	public ModelAndView SaveZsagain(@RequestParam("resumeZsid") String resumeZsid, Resume_zs resume_zs, Resume resume, String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/SaveZsagain", resumeZsid + "------," + "------," + JSONArray.fromObject(resume_zs).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_zs.getResumeZsid() != "" && resume_zs.getResumeZsid() != null)
		{
			resume_zsService.updateByZsId(resume_zs);
		} else
		{
			resume_zs.setResumeAddtime(new Date());
			resume_zs.setResumeDelflag((long) 1);
			resume_zs.setResumeZsid(RandomGUIDUtil.generateKey());
			resume_zsService.ZsinsertSelective(resume_zs);
		}
		// SavePdf(resumeId, contextString, request);
		return new ModelAndView("Grjl/Grxx", "resume_zs", resume_zs);
	}

	// 删除证书
	@RequestMapping(value = "/DeleteZs", method = RequestMethod.POST)
	public ModelAndView DeleteZs(@RequestParam("resumeZsid") String resumeZsid, Resume_zs resume_zs, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteZs", resumeZsid + "------," + "------," + resumeZsid + ",------" + JSONArray.fromObject(resume_zs).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_zs.getResumeZsid() != "" && resume_zs.getResumeZsid() != null)
		{
			resume_zsService.deleteByZsid(resumeZsid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除IT技能
	@RequestMapping(value = "/DeleteITjn", method = RequestMethod.POST)
	public ModelAndView DeleteITjn(@RequestParam("resumeITjnid") String resumeITjnid, Resume_ITjn resume_ITjn, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteITjn", resumeITjnid + "------," + "------," + JSONArray.fromObject(resume_ITjn).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_ITjn.getResumeITjnid() != "" && resume_ITjn.getResumeITjnid() != null)
		{
			resume_ITjnService.deleteByITjnid(resumeITjnid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除校内经历
	@RequestMapping(value = "/DeleteXnjl", method = RequestMethod.POST)
	public ModelAndView DeleteXnjl(@RequestParam("resumeXnjlid") String resumeXnjlid, Resume_xnjl resume_xnjl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteXnjl", resumeXnjlid + "------," + "------," + JSONArray.fromObject(resume_xnjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xnjl.getResumeXnjlid() != "" && resume_xnjl.getResumeXnjlid() != null)
		{
			resume_xnjlService.deleteByXnjlid(resumeXnjlid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除语言能力
	@RequestMapping(value = "/DeleteYynl", method = RequestMethod.POST)
	public ModelAndView DeleteYynl(@RequestParam("resumeYynlid") String resumeYynlid, Resume_yynl resume_yynl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteYynl", resumeYynlid + "------," + "------," + JSONArray.fromObject(resume_yynl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_yynl.getResumeYynlid() != "" && resume_yynl.getResumeYynlid() != null)
		{
			resume_yynlService.deleteByYynlid(resumeYynlid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除工作经历
	@RequestMapping(value = "/DeleteGzjl", method = RequestMethod.POST)
	public ModelAndView DeleteGzjl(@RequestParam("resumeGzjlid") String resumeGzjlid, Resume_gzjl resume_gzjl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteGzjl", resumeGzjlid + "------," + "------," + JSONArray.fromObject(resume_gzjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_gzjl.getResumeGzjlid() != "" && resume_gzjl.getResumeGzjlid() != null)
		{
			resume_gzjlService.deleteByGzjlid(resumeGzjlid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除校内职务
	@RequestMapping(value = "/DeleteXnzw", method = RequestMethod.POST)
	public ModelAndView DeleteXnzw(@RequestParam("resumeXnzwid") String resumeXnzwid, Resume_xnzw resume_xnzw, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteXnzw", resumeXnzwid + "------," + "------," + JSONArray.fromObject(resume_xnzw).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xnzw.getResumeXnzwid() != "" && resume_xnzw.getResumeXnzwid() != null)
		{
			resume_xnzwService.deleteByXnzwid(resumeXnzwid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除实践经历
	@RequestMapping(value = "/DeleteSjjl", method = RequestMethod.POST)
	public ModelAndView DeleteSjjl(@RequestParam("resumeSjjlid") String resumeSjjlid, Resume_sjjl resume_sjjl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteSjjl", resumeSjjlid + "------," + "------," + JSONArray.fromObject(resume_sjjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_sjjl.getResumeSjjlid() != "" && resume_sjjl.getResumeSjjlid() != null)
		{
			resume_sjjlService.deleteBySjjlid(resumeSjjlid);
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除实习经历
	@RequestMapping(value = "/DeleteSx", method = RequestMethod.POST)
	public ModelAndView DeleteSx(@RequestParam("resumeSxid") String resumeSxid, Resume_sx resume_sx, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteSx", resumeSxid + "------," + "------," + JSONArray.fromObject(resume_sx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_sx.getResumeSxid() != "" && resume_sx.getResumeSxid() != null)
		{
			resume_sxService.deleteBySxid(resumeSxid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除项目经验
	@RequestMapping(value = "/DeleteXmjy", method = RequestMethod.POST)
	public ModelAndView DeleteXmjy(@RequestParam("resumeXmjyid") String resumeXmjyid, Resume_xmjy resume_xmjy, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteXmjy", resumeXmjyid + "------," + "------," + JSONArray.fromObject(resume_xmjy).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_xmjy.getResumeXmjyid() != "" && resume_xmjy.getResumeXmjyid() != null)
		{
			resume_xmjyService.deleteByXmjyid(resumeXmjyid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除教育经历
	@RequestMapping(value = "/DeleteJyjl", method = RequestMethod.POST)
	public ModelAndView DeleteJyjl(@RequestParam("resumeJyjlid") String resumeJyjlid, Resume_jyjl resume_jyjl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteJyjl", resumeJyjlid + "------," + "------," + JSONArray.fromObject(resume_jyjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_jyjl.getResumeJyjlid() != "" && resume_jyjl.getResumeJyjlid() != null)
		{
			resume_jyjlService.deleteByJyjlid(resumeJyjlid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}
		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除培训经历
	@RequestMapping(value = "/DeletePxjl", method = RequestMethod.POST)
	public ModelAndView DeletePxjl(@RequestParam("resumePxjlid") String resumePxjlid, Resume_pxjl resume_pxjl, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeletePxjl", resumePxjlid + "------," + "------," + JSONArray.fromObject(resume_pxjl).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_pxjl.getResumePxjlid() != "" && resume_pxjl.getResumePxjlid() != null)
		{
			resume_pxjlService.deleteByPxjlid(resumePxjlid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除其他信息
	@RequestMapping(value = "/DeleteQtxx", method = RequestMethod.POST)
	public ModelAndView DeleteQtxx(@RequestParam("resumeQtxxid") String resumeQtxxid, Resume_qtxx resume_qtxx, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteQtxx", resumeQtxxid + "------," + "------," + JSONArray.fromObject(resume_qtxx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_qtxx.getResumeQtxxid() != "" && resume_qtxx.getResumeQtxxid() != null)
		{
			resume_qtxxService.deleteByQtxxid(resumeQtxxid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除自制简历
	@RequestMapping(value = "/DeleteZzjl", method = RequestMethod.POST)
	public ModelAndView DeleteZzjl(@RequestParam("resumeId") String resumeId, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteZzjl", "resumeId-------" + resumeId);
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		if (resume != null)
		{
			String path = request.getSession().getServletContext().getRealPath("/");
			File file = new File(path + resume.getResumeZzjl());
			resume.setResumeZzjl("");
			resumeService.updateByPrimaryKeySelective(resume);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists())
				file.delete();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 删除附件
	@RequestMapping(value = "/DeleteFj", method = RequestMethod.POST)
	public ModelAndView DeleteFj(@RequestParam("resumeFjid") String resumeFjid, Resume_fj resume_fj, Resume resume, String resumeId, String contextString, HttpServletRequest request, HttpSession session)
	{
		logInfo("/DeleteFj", resumeFjid + "------," + "------," + JSONArray.fromObject(resume_fj).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
		if (resume_fj.getResumeFjid() != "" && resume_fj.getResumeFjid() != null)
		{
			Resume_fj fj = resume_fjService.selectByPrimaryKey(resume_fj.getResumeFjid());
			resume_fjService.deleteByFjid(resumeFjid);
			resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
			String path = request.getSession().getServletContext().getRealPath("/");
			File file = new File(path + fj.getResumeFj());
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists())
				file.delete();
			// SavePdf(resumeId, contextString, request);
		}

		return new ModelAndView("Grjl/Grxx", "resume", resume);
	}

	// 上传附件
	@ResponseBody
	@RequestMapping(value = "/SaveFj")
	public String addGoods(HttpServletRequest request, HttpSession session, @RequestParam("resumeFj") MultipartFile file, @RequestParam("resumeId") String resumeId, String contextString)
	{
		logInfo("/SaveFj", resumeId + "," + contextString);
		Userinfo userinfo = ((Userinfo) session.getAttribute("userLoginSession"));
		if (userinfo != null)
		{
			if (file.getSize() > 512000)
			{
				return "false";
			}
			List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(userinfo.getUserId());
			if (Fj.size() > 4)
			{
				return "fjfalse";
			} else
			{
				ModelAndView mav = new ModelAndView();
				// 项目路径
				String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
				String fileName = file.getOriginalFilename();
				String strId = RandomGUIDUtil.generateKey();
				fileName = strId + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
				fileName = fileName.toLowerCase();
				fileName = RandomGUIDUtil.generateKey() + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
				// 得到保存的路径
				try
				{
					FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));

					Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
					resume.setResumeFj("uppics/" + fileName);
					resume.setResumeAddtime(new Date());
					resume.setResumeDelflag((long) 1);
					resume.setResumeFjid(strId);
					resumeService.FjinsertSelective(resume);
					logInfo("/SaveFj", resumeId + "," + contextString + "," + resume.getResumeFj());
				} catch (IOException e)
				{
					logInfo("/SaveFj", resumeId + "," + contextString + ",******Error");
					e.printStackTrace();
				}
				mav.setViewName("test");
				// SavePdf(resumeId, contextString, request);
				return "true";
			}
		} else
		{
			return "SessionLost";
		}
	}

	// 上传自制简历
	@ResponseBody
	@RequestMapping(value = "/SaveZzjl")
	public String addZzjl(HttpServletRequest request, HttpSession session, @RequestParam("resumeZzjl") MultipartFile file, @RequestParam("zzjlresumeId") String resumeId, String contextString)
	{
		logInfo("/SaveZzjl", resumeId + "," + contextString);
		Userinfo userinfo = ((Userinfo) session.getAttribute("userLoginSession"));
		if (userinfo != null)
		{
			if (file.getSize() > 20480000)
			{
				return "false";
			}
			ModelAndView mav = new ModelAndView();
			// 项目路径
			String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";

			File f = new File(path);
			if (!f.exists() || !f.isDirectory())
				f.mkdir();

			String fileName = file.getOriginalFilename();
			String strId = RandomGUIDUtil.generateKey();
			fileName = strId + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			fileName = fileName.toLowerCase();
			fileName = RandomGUIDUtil.generateKey() + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			// 得到保存的路径
			try
			{
				FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));

				Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
				resume.setResumeZzjl("uppics/" + fileName);
				resumeService.updateByPrimaryKeySelective(resume);
				logInfo("/SaveZzjl", resumeId + "," + contextString + "," + resume.getResumeFj());
			} catch (IOException e)
			{
				logInfo("/SaveZzjl", resumeId + "," + contextString + ",******Error");
				e.printStackTrace();
			}
			mav.setViewName("test");
			// SavePdf(resumeId, contextString, request);
			return "true";
		} else
		{
			return "SessionLost";
		}
	}

	// PDF导出
	@RequestMapping(value = "/DownloadResume", method = RequestMethod.GET)
	public void DownloadResume(@RequestParam(value = "id", required = false) String id, HttpServletRequest req, HttpServletResponse response, HttpSession session) throws Exception
	{
		logInfo("/DownloadResume", id);

		if (id != null && !"".equals(id) && (session.getAttribute("userLoginSession") != null || session.getAttribute("loginSession") != null))
		{
			String strFilePath = this.saveWord(id, id, req);
			String strFileName = strFilePath.split("//")[strFilePath.split("//").length - 1];
			byte b[] = new byte[500];
			OutputStream o = response.getOutputStream();
			File fileLoad = new File(strFilePath);
			response.reset();
			response.setCharacterEncoding("gb2312");
			response.setContentType("application/vnd.ms-excel");

			response.setHeader("Content-Disposition", "attachment;filename=" + new String(strFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
			long fileLength = fileLoad.length();
			String length1 = String.valueOf(fileLength);
			response.setHeader("Content_Length", length1);
			FileInputStream in = new FileInputStream(fileLoad);
			try
			{
				int n;
				while ((n = in.read(b)) != -1)
				{
					o.write(b, 0, n);
				}

			} catch (Exception e)
			{
				logInfo("DownloadResumeError:", id);
			} finally
			{
				in.close();
				o.close();
			}
		}
	}

	// 上传照片
	@ResponseBody
	@RequestMapping(value = "/SavePhotos")
	public String addPhotos(HttpServletRequest request, HttpSession session, @RequestParam("resumePhotos") MultipartFile file, @RequestParam("resumeId") String resumeId, String contextString)
	{
		logInfo("/SavePhotos", resumeId + "," + contextString);
		ModelAndView mav = new ModelAndView();
		if (file.getSize() > 10240000)
		{
			return "false";
		} else
		{
			// 项目路径
			String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";

			File f = new File(path);
			if (!f.exists() || !f.isDirectory())
			{
				f.mkdir();
			}

			String fileName = new java.util.Date().getTime() + file.getOriginalFilename();
			fileName = fileName.toLowerCase();
			fileName = resumeId + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();

			// 得到保存的路径
			try
			{
				FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));
				Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
				resume.setResumePhotos("uppics/" + fileName);
				resumeService.updateByPrimaryKeySelective(resume);
				logInfo("/SavePhotos", resumeId + "," + contextString + "," + resume.getResumePhotos());
			} catch (IOException e)
			{
				logInfo("/SavePhotos", resumeId + "," + contextString + ",******Error");
				e.printStackTrace();
			}
			mav.setViewName("test");
			// SavePdf(resumeId, contextString, request);
			return "true";
		}
	}

	// PDF导出
	@RequestMapping(value = "/newPDFHR", method = RequestMethod.GET)
	public void newPDFHR(String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/newPDFHR", resumeId + "," + contextString);
		SavePdf(resumeId, contextString, request);
	}

	// PDF导出
	@RequestMapping(value = "/newPDFjtjlk", method = RequestMethod.GET)
	public void newPDFjtjlk(String resumeId, String contextString, HttpServletRequest request)
	{
		logInfo("/newPDFjtjlk", resumeId + "," + contextString);
		SavePdf(resumeId, contextString, request);
	}

	// PDF导出
	@RequestMapping(value = "/DownloadResumeHr", method = RequestMethod.GET)
	public void DownloadResumeHr(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "jtjlkId", required = false) String jtjlkId, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		logInfo("/DownloadResumeHr", id + "," + jtjlkId);
		OutputStream o = response.getOutputStream();
		byte b[] = new byte[500];
		String nowPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "uppics" + "\\" + id + ".doc";

		File fileLoad = new File(nowPath);
		response.reset();
		response.setCharacterEncoding("gb2312");
		response.setContentType("application/vnd.ms-excel");

		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(jtjlkId);
		String strNewFileName = "";
		strNewFileName += jtjlk.getJtjlkZw() + "-" + jtjlk.getJtjlkName() + "-" + jtjlk.getJtjlkByxx() + "-" + jtjlk.getJtjlkXl() + ".doc";
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(strNewFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		long fileLength = fileLoad.length();
		String length1 = String.valueOf(fileLength);
		response.setHeader("Content_Length", length1);
		FileInputStream in = new FileInputStream(fileLoad);
		int n;
		while ((n = in.read(b)) != -1)
		{
			o.write(b, 0, n);
		}

		in.close();
		o.close();
	}

	// PDF导出
	@RequestMapping(value = "/DownloadResumeHrJtjlk", method = RequestMethod.GET)
	public void DownloadResumeHrJtjlk(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		logInfo("/DownloadResumeHrJtjlk", id);
		OutputStream o = response.getOutputStream();
		byte b[] = new byte[500];
		String nowPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "uppics" + "\\" + id + ".doc";

		File fileLoad = new File(nowPath);
		response.reset();
		response.setCharacterEncoding("gb2312");
		response.setContentType("application/vnd.ms-excel");
		Resume r = resumeService.selectByPrimaryKeySelectiveById(id);
		List<Resume_jyjl> jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(id);
		String strNewFileName = "";
		Resume_jyjl j = new Resume_jyjl();
		if (jyjls.size() > 0)
		{
			j = jyjls.get(jyjls.size() - 1);
		}
		strNewFileName += r.getResumeName() + "-" + j.getResumeXxmc() + "-" + j.getResumeXl() + ".doc";
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(strNewFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		long fileLength = fileLoad.length();
		String length1 = String.valueOf(fileLength);
		response.setHeader("Content_Length", length1);
		FileInputStream in = new FileInputStream(fileLoad);
		int n;
		while ((n = in.read(b)) != -1)
		{
			o.write(b, 0, n);
		}

		in.close();
		o.close();
	}

	public String saveWord(String resumeid, String userid, HttpServletRequest req) throws Exception
	{
		String url = PropertiesUtils.getValue("server");
		String uri = "/Recruit/expjl?resumeId=" + resumeid + "&userid=" + userid;

		// 要保存的word文件名称
		String fn = getExFileName(resumeid, userid);
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics/resumes/" + resumeid + "/";

		File file = new File(path);
		if (file.exists())
			file.delete();

		if (!file.exists() || !file.isDirectory())
			file.mkdirs();

		String htmlPath = HtmlSpider.saveHtmlTo(url + uri, path);
		new HtmlToDoc().writeWordFile(htmlPath, path, fn + ".pdf", url + "/Recruit/");
		File fileDel = new File(htmlPath);
		fileDel.delete();
		return path + "/" + fn + ".pdf";
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
					jyjltable.addCell(new Cell(resume2.getResumeZyl() + resume2.getResumeZy()));
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
					gzjltable.addCell(new Cell(new Paragraph("工作单位:", fontChinese)));
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
					sxjltable.addCell(new Cell(new Paragraph("实习单位:", fontChinese)));
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
					String fileName = resume2.getResumeFj();
					String panduan = fileName.substring(fileName.lastIndexOf("."));
					if (panduan.equals(".jpg") || panduan.equals(".gif") || panduan.equals(".png") || panduan.equals(".bmp"))
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
				}
				document.add(fjtable);
			}
			document.close();
		} catch (Exception e)
		{
			logInfo("/SavePdf", resumeId + "," + contextString);
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/LoadJlzy", method = RequestMethod.POST)
	public List<Zpzy> LoadZpzy(String type)
	{
		logInfo("/LoadJlzy", type);
		return zwglServices.LoadZpzy(type);
	}

	@ResponseBody
	@RequestMapping(value = "/LoadXxmc", method = RequestMethod.POST)
	public List<Xxmc> LoadXxmc(String type)
	{
		logInfo("/LoadXxmc", type);
		return xxmcService.LoadXxmc(type);
	}
}
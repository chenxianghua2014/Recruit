package com.ttgis.recruit.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.itextpdf.text.TabStop.Alignment;
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
import com.ttgis.recruit.utility.FieldNullFill;
import com.ttgis.recruit.utility.HtmlSpider;
import com.ttgis.recruit.utility.HtmlToDoc;
import com.ttgis.recruit.utility.PropertiesUtils;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@Controller
public class ResumeController {

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

    protected String getRemoteIp() {
	String remoteIp = null;
	remoteIp = request.getHeader("x-forwarded-for");
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getHeader("X-Real-IP");
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getHeader("Proxy-Client-IP");
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getHeader("WL-Proxy-Client-IP");
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getHeader("HTTP_CLIENT_IP");
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getRemoteAddr();
	}
	if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
	    remoteIp = request.getRemoteHost();
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
    public ModelAndView Grjl(HttpSession session) {
	Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
	logInfo("Grjl", JSONArray.fromObject(userinfo).toString());
	if (userinfo == null) {
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
    public List<Resume_jyjl> LoadJyjl(HttpSession session) {
	logInfo("LoadJyjl", "");
	List<Resume_jyjl> jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return jyjls;
    }

    // 加载校内奖励
    @ResponseBody
    @RequestMapping(value = "/LoadXnjl", method = RequestMethod.POST)
    public List<Resume_xnjl> LoadXnjl(HttpSession session) {
	logInfo("LoadXnjl", "");
	List<Resume_xnjl> xnjls = resume_xnjlService.getlistResumeXnjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return xnjls;
    }

    // 加载校内职务
    @ResponseBody
    @RequestMapping(value = "/LoadXnzw", method = RequestMethod.POST)
    public List<Resume_xnzw> LoadXnzw(HttpSession session) {
	logInfo("LoadXnzw", "");
	List<Resume_xnzw> xnzws = resume_xnzwService.getlistResumeXnzwByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return xnzws;
    }

    // 加载实践经历
    @ResponseBody
    @RequestMapping(value = "/LoadSjjl", method = RequestMethod.POST)
    public List<Resume_sjjl> LoadSjjl(HttpSession session) {
	logInfo("LoadSjjl", "");
	List<Resume_sjjl> sjjls = resume_sjjlService.getlistResumeSjjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return sjjls;
    }

    // 加载实习经历
    @ResponseBody
    @RequestMapping(value = "/LoadSxjl", method = RequestMethod.POST)
    public List<Resume_sx> LoadSxjl(HttpSession session) {
	logInfo("LoadSxjl", "");
	List<Resume_sx> sxjls = resume_sxService.getlistResumeSxByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return sxjls;
    }

    // 加载工作经历
    @ResponseBody
    @RequestMapping(value = "/LoadGzjl", method = RequestMethod.POST)
    public List<Resume_gzjl> LoadGzjl(HttpSession session) {
	logInfo("LoadGzjl", "");
	List<Resume_gzjl> gzjls = resume_gzjlService.getlistResumeGzjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return gzjls;
    }

    // 加载项目经验
    @ResponseBody
    @RequestMapping(value = "/LoadXmjy", method = RequestMethod.POST)
    public List<Resume_xmjy> LoadXmjy(HttpSession session) {
	logInfo("LoadXmjy", "");
	List<Resume_xmjy> xmjls = resume_xmjyService.getlistResumeXmjyByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return xmjls;
    }

    // 加载培训经历
    @ResponseBody
    @RequestMapping(value = "/LoadPxjl", method = RequestMethod.POST)
    public List<Resume_pxjl> LoadPxjl(HttpSession session) {
	logInfo("LoadPxjl", "");
	List<Resume_pxjl> pxjls = resume_pxjlService.getlistResumePxjlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return pxjls;
    }

    // 加载语言能力
    @ResponseBody
    @RequestMapping(value = "/LoadYynl", method = RequestMethod.POST)
    public List<Resume_yynl> LoadYynl(HttpSession session) {
	logInfo("LoadYynl", "");
	List<Resume_yynl> yynls = resume_yynlService.getlistResumeYynlByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return yynls;
    }

    // 加载其他信息
    @ResponseBody
    @RequestMapping(value = "/LoadQtxx", method = RequestMethod.POST)
    public List<Resume_qtxx> LoadQtxx(HttpSession session) {
	logInfo("LoadQtxx", "");
	List<Resume_qtxx> qtxxs = resume_qtxxService.getlistResumeQtxxByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return qtxxs;
    }

    // 加载附件
    @ResponseBody
    @RequestMapping(value = "/LoadFj", method = RequestMethod.POST)
    public List<Resume_fj> LoadFj(HttpSession session) {
	logInfo("LoadFj", "");
	List<Resume_fj> fjs = resume_fjService.getlistResumeFjByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return fjs;
    }

    // 加载附件
    @ResponseBody
    @RequestMapping(value = "/LoadZzjl", method = RequestMethod.POST)
    public Resume LoadZzjl(HttpSession session) {
	logInfo("LoadZzjl", "");
	Resume resume = resumeService.selectByPrimaryKeySelectiveById(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	if (resume == null)
	    return null;
	return resume;
    }

    // 加载IT技能
    @ResponseBody
    @RequestMapping(value = "/LoadITjn", method = RequestMethod.POST)
    public List<Resume_ITjn> LoadITjn(HttpSession session) {
	logInfo("LoadITjn", "");
	List<Resume_ITjn> ITjns = resume_ITjnService.getlistResumeITjnByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return ITjns;
    }

    // 加载证书
    @ResponseBody
    @RequestMapping(value = "/LoadZs", method = RequestMethod.POST)
    public List<Resume_zs> LoadZs(HttpSession session) {
	logInfo("LoadZs", "");
	List<Resume_zs> zss = resume_zsService.getlistResumeZsByResumeId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
	return zss;
    }

    // 预览简历RESUME查询
    @RequestMapping(value = "/expjl", method = RequestMethod.GET)
    public ModelAndView expjl(@RequestParam("resumeId") String resumeId, @RequestParam("userid") String userid) {
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

	for (int i = SelJyjl.size() - 1; i >= 0; i--) {
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
    public ModelAndView Yljl(String resumeId) {
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
	// return new ModelAndView("Grjl/jl", "mv", mv);
    }

    // 预览简历RESUME查询
    @RequestMapping(value = "/YljlHr", method = RequestMethod.GET)
    public ModelAndView YljlHr(String resumeId, String jtjlkId) {
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
	//return new ModelAndView("Grjl/jl", "mv", mv);
    }

    // 预览简历RESUME查询
    @RequestMapping(value = "/YljlHrJtjlk", method = RequestMethod.GET)
    public ModelAndView YljlHrJtjlk(String resumeId, String jtjlkId) {
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
    public ModelAndView SaveJbxx(Resume resume, String contextString, HttpSession session, String resumeId, HttpServletRequest request) {
	logInfo("SaveJbxx", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
	Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
	if (userinfo == null) {
	    return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
	}
	if (resumeService.selectByPrimaryKeySelectiveById(resumeId) != null) {
	    resumeService.updateByPrimaryKeySelective(resume);
	    // SavePdf(resumeId, contextString, request);
	} else {
	    resume.setResumeAddtime(new Date());
	    resume.setResumeDelflag((long) 1);
	    resume.setResumeId(userinfo.getUserId());
	    resumeService.insertSelective(resume);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 联系方式
    @RequestMapping(value = "/SaveLxfs", method = RequestMethod.POST)
    public ModelAndView SaveLxfs(Resume resume, String contextString, String resumeId, HttpServletRequest request) {
	logInfo("SaveLxfs", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
	resumeService.updateByPrimaryKeySelective(resume);
	// SavePdf(resumeId, contextString, request);
	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 其他信息
    @RequestMapping(value = "/SaveQtxx", method = RequestMethod.POST)
    public ModelAndView SaveQtxx(Resume resume, String contextString, String resumeId, HttpServletRequest request) {
	logInfo("SaveQtxx", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
	resumeService.updateByPrimaryKeySelective(resume);
	// SavePdf(resumeId, contextString, request);
	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 教育背景 cxh
    @RequestMapping(value = "/SaveJybj", method = RequestMethod.POST)
    public ModelAndView SaveJybj(Resume resume, String contextString, String resumeId, HttpServletRequest request) {
	logInfo("SaveJybj", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
	resumeService.updateByPrimaryKeySelective(resume);
	// SavePdf(resumeId, contextString, request);
	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 证书
    @RequestMapping(value = "/SaveZs", method = RequestMethod.POST)
    public ModelAndView SaveZs(Resume resume, String contextString, String resumeId, HttpServletRequest request) {
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
    public ModelAndView SaveITjn(Resume resume, String contextString, String resumeId, HttpServletRequest request) {
	logInfo("/SaveItjn", JSONArray.fromObject(resume).toString() + ",------" + contextString + ",------" + resumeId);
	resumeService.updateByPrimaryKeySelective(resume);
	// SavePdf(resumeId, contextString, request);
	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 继续添加教育经历
    @ResponseBody
    @RequestMapping(value = "/SaveJyjlagain", method = RequestMethod.POST)
    public ModelAndView SaveJyjlagain(@RequestParam("resumeJyjlid") String resumeJyjlid, Resume_jyjl resume_jyjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveJyjlagain", resumeJyjlid + "------," + ",------" + JSONArray.fromObject(resume_jyjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_jyjl.getResumeJyjlid() != "" && resume_jyjl.getResumeJyjlid() != null) {
	    resume_jyjlService.updateByJyjlId(resume_jyjl);
	} else {
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
    public ModelAndView SaveXnjlagain(@RequestParam("resumeXnjlid") String resumeXnjlid, Resume_xnjl resume_xnjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveXnjlagain", resumeXnjlid + "------," + "------," + JSONArray.fromObject(resume_xnjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xnjl.getResumeXnjlid() != "" && resume_xnjl.getResumeXnjlid() != null) {
	    resume_xnjlService.updateByXnjlId(resume_xnjl);
	} else {
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
    public ModelAndView SaveXnzwagain(@RequestParam("resumeXnzwid") String resumeXnzwid, Resume_xnzw resume_xnzw, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveXnzwagain", resumeXnzwid + "------," + "------," + JSONArray.fromObject(resume_xnzw).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xnzw.getResumeXnzwid() != "" && resume_xnzw.getResumeXnzwid() != null) {
	    resume_xnzwService.updateByXnzwId(resume_xnzw);
	} else {
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
    public ModelAndView SaveSjjlagain(@RequestParam("resumeSjjlid") String resumeSjjlid, Resume_sjjl resume_sjjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveSjjlagain", resumeSjjlid + "------," + "------," + JSONArray.fromObject(resume_sjjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_sjjl.getResumeSjjlid() != "" && resume_sjjl.getResumeSjjlid() != null) {
	    resume_sjjlService.updateBySjjlId(resume_sjjl);
	} else {
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
    public ModelAndView SaveSxjlagain(@RequestParam("resumeSxid") String resumeSxid, Resume_sx resume_sx, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveSxjlagain", resumeSxid + "------," + "------," + JSONArray.fromObject(resume_sx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------"
		+ resumeId + ",------" + contextString);
	if (resume_sx.getResumeSxid() != "" && resume_sx.getResumeSxid() != null) {
	    resume_sxService.updateBySxId(resume_sx);
	} else {
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
    public ModelAndView SaveGzjlagain(@RequestParam("resumeGzjlid") String resumeGzjlid, Resume_gzjl resume_gzjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveGzjlagain", resumeGzjlid + "------," + "------," + JSONArray.fromObject(resume_gzjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_gzjl.getResumeGzjlid() != "" && resume_gzjl.getResumeGzjlid() != null) {
	    resume_gzjlService.updateByGzjlId(resume_gzjl);
	} else {
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
    public ModelAndView SaveXmjyagain(@RequestParam("resumeXmjyid") String resumeXmjyid, Resume_xmjy resume_xmjy, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveXmjyagain", resumeXmjyid + "------," + "------," + JSONArray.fromObject(resume_xmjy).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xmjy.getResumeXmjyid() != "" && resume_xmjy.getResumeXmjyid() != null) {
	    resume_xmjyService.updateByXmjyId(resume_xmjy);
	} else {
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
    public ModelAndView SavePxjlagain(@RequestParam("resumePxjlid") String resumePxjlid, Resume_pxjl resume_pxjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SavePxjlagain", resumePxjlid + "------," + "------," + JSONArray.fromObject(resume_pxjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_pxjl.getResumePxjlid() != "" && resume_pxjl.getResumePxjlid() != null) {
	    resume_pxjlService.updateByPxjlId(resume_pxjl);
	} else {
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
    public ModelAndView SaveYynlagain(@RequestParam("resumeYynlid") String resumeYynlid, Resume_yynl resume_yynl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveYynlagain", resumeYynlid + "------," + "------," + JSONArray.fromObject(resume_yynl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_yynl.getResumeYynlid() != "" && resume_yynl.getResumeYynlid() != null) {
	    resume_yynlService.updateByYynlId(resume_yynl);
	} else {
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
    public ModelAndView SaveQtxxagain(@RequestParam("resumeQtxxid") String resumeQtxxid, Resume_qtxx resume_qtxx, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveQtxxagain", resumeQtxxid + "------," + "------," + JSONArray.fromObject(resume_qtxx).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_qtxx.getResumeQtxxid() != "" && resume_qtxx.getResumeQtxxid() != null) {
	    resume_qtxxService.updateByQtxxId(resume_qtxx);
	} else {
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
    public ModelAndView SaveITjnagain(@RequestParam("resumeITjnid") String resumeITjnid, Resume_ITjn resume_ITjn, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveITjnagain", resumeITjnid + "------," + "------," + JSONArray.fromObject(resume_ITjn).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_ITjn.getResumeITjnid() != "" && resume_ITjn.getResumeITjnid() != null) {
	    resume_ITjnService.updateByITjnId(resume_ITjn);
	} else {
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
    public ModelAndView SaveZsagain(@RequestParam("resumeZsid") String resumeZsid, Resume_zs resume_zs, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request) {
	logInfo("/SaveZsagain", resumeZsid + "------," + "------," + JSONArray.fromObject(resume_zs).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------"
		+ resumeId + ",------" + contextString);
	if (resume_zs.getResumeZsid() != "" && resume_zs.getResumeZsid() != null) {
	    resume_zsService.updateByZsId(resume_zs);
	} else {
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
    public ModelAndView DeleteZs(@RequestParam("resumeZsid") String resumeZsid, Resume_zs resume_zs, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteZs", resumeZsid + "------," + "------," + resumeZsid + ",------" + JSONArray.fromObject(resume_zs).toString() + ",------"
		+ JSONArray.fromObject(resume).toString() + ",------" + resumeId + ",------" + contextString);
	if (resume_zs.getResumeZsid() != "" && resume_zs.getResumeZsid() != null) {
	    resume_zsService.deleteByZsid(resumeZsid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除IT技能
    @RequestMapping(value = "/DeleteITjn", method = RequestMethod.POST)
    public ModelAndView DeleteITjn(@RequestParam("resumeITjnid") String resumeITjnid, Resume_ITjn resume_ITjn, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteITjn", resumeITjnid + "------," + "------," + JSONArray.fromObject(resume_ITjn).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_ITjn.getResumeITjnid() != "" && resume_ITjn.getResumeITjnid() != null) {
	    resume_ITjnService.deleteByITjnid(resumeITjnid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除校内经历
    @RequestMapping(value = "/DeleteXnjl", method = RequestMethod.POST)
    public ModelAndView DeleteXnjl(@RequestParam("resumeXnjlid") String resumeXnjlid, Resume_xnjl resume_xnjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteXnjl", resumeXnjlid + "------," + "------," + JSONArray.fromObject(resume_xnjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xnjl.getResumeXnjlid() != "" && resume_xnjl.getResumeXnjlid() != null) {
	    resume_xnjlService.deleteByXnjlid(resumeXnjlid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除语言能力
    @RequestMapping(value = "/DeleteYynl", method = RequestMethod.POST)
    public ModelAndView DeleteYynl(@RequestParam("resumeYynlid") String resumeYynlid, Resume_yynl resume_yynl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteYynl", resumeYynlid + "------," + "------," + JSONArray.fromObject(resume_yynl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_yynl.getResumeYynlid() != "" && resume_yynl.getResumeYynlid() != null) {
	    resume_yynlService.deleteByYynlid(resumeYynlid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除工作经历
    @RequestMapping(value = "/DeleteGzjl", method = RequestMethod.POST)
    public ModelAndView DeleteGzjl(@RequestParam("resumeGzjlid") String resumeGzjlid, Resume_gzjl resume_gzjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteGzjl", resumeGzjlid + "------," + "------," + JSONArray.fromObject(resume_gzjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_gzjl.getResumeGzjlid() != "" && resume_gzjl.getResumeGzjlid() != null) {
	    resume_gzjlService.deleteByGzjlid(resumeGzjlid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除校内职务
    @RequestMapping(value = "/DeleteXnzw", method = RequestMethod.POST)
    public ModelAndView DeleteXnzw(@RequestParam("resumeXnzwid") String resumeXnzwid, Resume_xnzw resume_xnzw, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteXnzw", resumeXnzwid + "------," + "------," + JSONArray.fromObject(resume_xnzw).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xnzw.getResumeXnzwid() != "" && resume_xnzw.getResumeXnzwid() != null) {
	    resume_xnzwService.deleteByXnzwid(resumeXnzwid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除实践经历
    @RequestMapping(value = "/DeleteSjjl", method = RequestMethod.POST)
    public ModelAndView DeleteSjjl(@RequestParam("resumeSjjlid") String resumeSjjlid, Resume_sjjl resume_sjjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteSjjl", resumeSjjlid + "------," + "------," + JSONArray.fromObject(resume_sjjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_sjjl.getResumeSjjlid() != "" && resume_sjjl.getResumeSjjlid() != null) {
	    resume_sjjlService.deleteBySjjlid(resumeSjjlid);
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除实习经历
    @RequestMapping(value = "/DeleteSx", method = RequestMethod.POST)
    public ModelAndView DeleteSx(@RequestParam("resumeSxid") String resumeSxid, Resume_sx resume_sx, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteSx", resumeSxid + "------," + "------," + JSONArray.fromObject(resume_sx).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------"
		+ resumeId + ",------" + contextString);
	if (resume_sx.getResumeSxid() != "" && resume_sx.getResumeSxid() != null) {
	    resume_sxService.deleteBySxid(resumeSxid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除项目经验
    @RequestMapping(value = "/DeleteXmjy", method = RequestMethod.POST)
    public ModelAndView DeleteXmjy(@RequestParam("resumeXmjyid") String resumeXmjyid, Resume_xmjy resume_xmjy, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteXmjy", resumeXmjyid + "------," + "------," + JSONArray.fromObject(resume_xmjy).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_xmjy.getResumeXmjyid() != "" && resume_xmjy.getResumeXmjyid() != null) {
	    resume_xmjyService.deleteByXmjyid(resumeXmjyid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除教育经历
    @RequestMapping(value = "/DeleteJyjl", method = RequestMethod.POST)
    public ModelAndView DeleteJyjl(@RequestParam("resumeJyjlid") String resumeJyjlid, Resume_jyjl resume_jyjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteJyjl", resumeJyjlid + "------," + "------," + JSONArray.fromObject(resume_jyjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_jyjl.getResumeJyjlid() != "" && resume_jyjl.getResumeJyjlid() != null) {
	    resume_jyjlService.deleteByJyjlid(resumeJyjlid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}
	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除培训经历
    @RequestMapping(value = "/DeletePxjl", method = RequestMethod.POST)
    public ModelAndView DeletePxjl(@RequestParam("resumePxjlid") String resumePxjlid, Resume_pxjl resume_pxjl, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeletePxjl", resumePxjlid + "------," + "------," + JSONArray.fromObject(resume_pxjl).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_pxjl.getResumePxjlid() != "" && resume_pxjl.getResumePxjlid() != null) {
	    resume_pxjlService.deleteByPxjlid(resumePxjlid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除其他信息
    @RequestMapping(value = "/DeleteQtxx", method = RequestMethod.POST)
    public ModelAndView DeleteQtxx(@RequestParam("resumeQtxxid") String resumeQtxxid, Resume_qtxx resume_qtxx, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteQtxx", resumeQtxxid + "------," + "------," + JSONArray.fromObject(resume_qtxx).toString() + ",------" + JSONArray.fromObject(resume).toString()
		+ ",------" + resumeId + ",------" + contextString);
	if (resume_qtxx.getResumeQtxxid() != "" && resume_qtxx.getResumeQtxxid() != null) {
	    resume_qtxxService.deleteByQtxxid(resumeQtxxid);
	    resumeId = ((Userinfo) session.getAttribute("userLoginSession")).getUserId();
	    // SavePdf(resumeId, contextString, request);
	}

	return new ModelAndView("Grjl/Grxx", "resume", resume);
    }

    // 删除自制简历
    @RequestMapping(value = "/DeleteZzjl", method = RequestMethod.POST)
    public ModelAndView DeleteZzjl(@RequestParam("resumeId") String resumeId, HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteZzjl", "resumeId-------" + resumeId);
	Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
	if (resume != null) {
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
    public ModelAndView DeleteFj(@RequestParam("resumeFjid") String resumeFjid, Resume_fj resume_fj, Resume resume, String resumeId, String contextString,
	    HttpServletRequest request, HttpSession session) {
	logInfo("/DeleteFj", resumeFjid + "------," + "------," + JSONArray.fromObject(resume_fj).toString() + ",------" + JSONArray.fromObject(resume).toString() + ",------"
		+ resumeId + ",------" + contextString);
	if (resume_fj.getResumeFjid() != "" && resume_fj.getResumeFjid() != null) {
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
    public String addGoods(HttpServletRequest request, HttpSession session, @RequestParam("resumeFj") MultipartFile file, @RequestParam("resumeId") String resumeId,
	    String contextString) {
	logInfo("/SaveFj", resumeId + "," + contextString);
	Userinfo userinfo = ((Userinfo) session.getAttribute("userLoginSession"));
	if (userinfo != null) {
	    if (file.getSize() > 512000) {
		return "false";
	    }
	    List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(userinfo.getUserId());
	    if (Fj.size() > 4) {
		return "fjfalse";
	    } else {
		ModelAndView mav = new ModelAndView();
		// 项目路径
		String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";
		String fileName = file.getOriginalFilename();
		String strId = RandomGUIDUtil.generateKey();
		fileName = strId + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
		fileName = fileName.toLowerCase();
		fileName = RandomGUIDUtil.generateKey() + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
		// 得到保存的路径
		try {
		    FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));

		    Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		    resume.setResumeFj("uppics/" + fileName);
		    resume.setResumeAddtime(new Date());
		    resume.setResumeDelflag((long) 1);
		    resume.setResumeFjid(strId);
		    resumeService.FjinsertSelective(resume);
		    logInfo("/SaveFj", resumeId + "," + contextString + "," + resume.getResumeFj());
		} catch (IOException e) {
		    logInfo("/SaveFj", resumeId + "," + contextString + ",******Error");
		    e.printStackTrace();
		}
		mav.setViewName("test");
		// SavePdf(resumeId, contextString, request);
		return "true";
	    }
	} else {
	    return "SessionLost";
	}
    }

    // 上传自制简历
    @ResponseBody
    @RequestMapping(value = "/SaveZzjl")
    public String addZzjl(HttpServletRequest request, HttpSession session, @RequestParam("resumeZzjl") MultipartFile file, @RequestParam("zzjlresumeId") String resumeId,
	    String contextString) {
	logInfo("/SaveZzjl", resumeId + "," + contextString);
	Userinfo userinfo = ((Userinfo) session.getAttribute("userLoginSession"));
	if (userinfo != null) {
	    if (file.getSize() > 20480000) {
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
	    try {
		FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));

		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		resume.setResumeZzjl("uppics/" + fileName);
		resumeService.updateByPrimaryKeySelective(resume);
		logInfo("/SaveZzjl", resumeId + "," + contextString + "," + resume.getResumeFj());
	    } catch (IOException e) {
		logInfo("/SaveZzjl", resumeId + "," + contextString + ",******Error");
		e.printStackTrace();
	    }
	    mav.setViewName("test");
	    // SavePdf(resumeId, contextString, request);
	    return "true";
	} else {
	    return "SessionLost";
	}
    }

    // PDF导出
    @RequestMapping(value = "/DownloadResume", method = RequestMethod.GET)
    public void DownloadResume(@RequestParam(value = "id", required = false) String id, HttpServletRequest req, HttpServletResponse response, HttpSession session) throws Exception {
	logInfo("/DownloadResume", id);

	if (id != null && !"".equals(id) && (session.getAttribute("userLoginSession") != null || session.getAttribute("loginSession") != null)) {
	    String strFilePath = this.saveWord(id, id, req);
	    String strFileName = strFilePath.split("//")[strFilePath.split("//").length - 1];
	    byte b[] = new byte[500];
	    OutputStream o = response.getOutputStream();
	    File fileLoad = new File(strFilePath);
	    response.reset();

	    response.setCharacterEncoding("gb2312");
	    // response.setCharacterEncoding("UTF-8");
	    // 后缀excel改为word saveWord中修改了方法
	    response.setContentType("application/msword");
	    response.setHeader("Content-Disposition", "attachment;filename=" + new String(strFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码

	    long fileLength = fileLoad.length();
	    String length1 = String.valueOf(fileLength);
	    response.setHeader("Content_Length", length1);
	    FileInputStream in = new FileInputStream(fileLoad);
	    try {
		int n;
		while ((n = in.read(b)) != -1) {
		    o.write(b, 0, n);
		}

	    } catch (Exception e) {
		logInfo("DownloadResumeError:", id);
	    } finally {
		in.close();
		o.close();
	    }
	}
    }

    // 上传照片
    @ResponseBody
    @RequestMapping(value = "/SavePhotos")
    public String addPhotos(HttpServletRequest request, HttpSession session, @RequestParam("resumePhotos") MultipartFile file, @RequestParam("resumeId") String resumeId,
	    String contextString) {
	logInfo("/SavePhotos", resumeId + "," + contextString);
	ModelAndView mav = new ModelAndView();
	if (file.getSize() > 10240000) {
	    return "false";
	} else {
	    // 项目路径
	    String path = request.getSession().getServletContext().getRealPath("/") + "uppics\\";

	    File f = new File(path);
	    if (!f.exists() || !f.isDirectory()) {
		f.mkdir();
	    }

	    String fileName = new java.util.Date().getTime() + file.getOriginalFilename();
	    fileName = fileName.toLowerCase();
	    fileName = resumeId + "." + fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();

	    // 得到保存的路径
	    try {
		FileCopyUtils.copy(file.getBytes(), new File(path + "/" + fileName));
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeId);
		resume.setResumePhotos("uppics/" + fileName);
		resumeService.updateByPrimaryKeySelective(resume);
		logInfo("/SavePhotos", resumeId + "," + contextString + "," + resume.getResumePhotos());
	    } catch (IOException e) {
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
    public void newPDFHR(String resumeId, String contextString, HttpServletRequest request) {
	logInfo("/newPDFHR", resumeId + "," + contextString);
	SavePdf(resumeId, contextString, request);
	// new HtmlToDoc().SavePdf(resumeId);
    }

    // PDF导出
    @RequestMapping(value = "/newPDFjtjlk", method = RequestMethod.GET)
    public void newPDFjtjlk(String resumeId, String contextString, HttpServletRequest request) {
	logInfo("/newPDFjtjlk", resumeId + "," + contextString);
	SavePdf(resumeId, contextString, request);
    }

    // PDF导出
    @RequestMapping(value = "/DownloadResumeHr", method = RequestMethod.GET)
    public void DownloadResumeHr(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "jtjlkId", required = false) String jtjlkId,
	    HttpServletRequest request, HttpServletResponse response) throws IOException {
	logInfo("/DownloadResumeHr", id + "," + jtjlkId);
	OutputStream o = response.getOutputStream();
	byte b[] = new byte[500];
	String nowPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "uppics" + "\\" + id + ".doc";

	File fileLoad = new File(nowPath);
	response.reset();
	response.setCharacterEncoding("gb2312");
	response.setContentType("application/vnd.ms-excel");

	String strNewFileName = "";
	/*
	 * Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(jtjlkId);
	 * String strNewFileName = "";
	 * strNewFileName += jtjlk.getJtjlkZw() + "-" + jtjlk.getJtjlkName() + "-" + jtjlk.getJtjlkByxx() + "-" + jtjlk.getJtjlkXl() + ".doc";
	 */
	Resume resume = resumeService.selectByPrimaryKeySelectiveById(id);
	strNewFileName = resume.getResumeName() + "_" + resume.getResumeSfzh() + ".doc";

	response.setHeader("Content-Disposition", "attachment;filename=" + new String(strNewFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
	long fileLength = fileLoad.length();
	String length1 = String.valueOf(fileLength);
	response.setHeader("Content_Length", length1);
	FileInputStream in = new FileInputStream(fileLoad);
	int n;
	while ((n = in.read(b)) != -1) {
	    o.write(b, 0, n);
	}

	in.close();
	o.close();
    }

    // PDF导出
    @RequestMapping(value = "/DownloadResumeHrJtjlk", method = RequestMethod.GET)
    public void DownloadResumeHrJtjlk(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
	if (jyjls.size() > 0) {
	    j = jyjls.get(jyjls.size() - 1);
	}
	strNewFileName += r.getResumeName() + "-" + j.getResumeXxmc() + "-" + j.getResumeXl() + ".doc";
	response.setHeader("Content-Disposition", "attachment;filename=" + new String(strNewFileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
	long fileLength = fileLoad.length();
	String length1 = String.valueOf(fileLength);
	response.setHeader("Content_Length", length1);
	FileInputStream in = new FileInputStream(fileLoad);
	int n;
	while ((n = in.read(b)) != -1) {
	    o.write(b, 0, n);
	}

	in.close();
	o.close();
    }

    public String saveWord(String resumeid, String userid, HttpServletRequest req) throws Exception {
	String url = PropertiesUtils.getValue("server"); // url=http://localhost:8080
	String uri = "/Recruit/expjl?resumeId=" + resumeid + "&userid=" + userid;
	// uri=/Recruit/expjl?resumeId=60030F27-A73C-5E2D-4DE5-A5BBF6537D7E&userid=60030F27-A73C-5E2D-4DE5-A5BBF6537D7E

	// 要保存的word文件名称
	String fn = getExFileName(resumeid, userid);// 提取学校，专业，姓名等信息组成文件名

	// path="D:\Tomcat 7.0\webapps\Recruit\\uppics/resumes/60030F27-A73C-5E2D-4DE5-A5BBF6537D7E/"
	// 文件实际存放路径
	String path = request.getSession().getServletContext().getRealPath("/") + "uppics/resumes/" + resumeid + "/";

	File file = new File(path);
	if (file.exists())
	    file.delete();

	if (!file.exists() || !file.isDirectory())
	    file.mkdirs();

	String htmlPath = HtmlSpider.saveHtmlTo(url + uri, path);
	new HtmlToDoc().writeWordFile(htmlPath, path, fn + ".doc", url + "/Recruit/");
	File fileDel = new File(htmlPath);
	fileDel.delete();
	return path + "/" + fn + ".doc";
    }

    /**
     * 根据简历ID和 用户ID生成文件名称
     * 
     * @param resumeid
     * @param userid
     * @return
     */
    public String getExFileName(String resumeid, String userid) {
	Resume resume = resumeService.selectByPrimaryKeySelectiveById(resumeid);
	List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(resumeid);
	List<MyApplication> myApplications = jtjlkService.selectMyApplication(userid);

	StringBuffer sbStr = new StringBuffer();

	if (Jyjl != null) {
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
	for (MyApplication myApplication : myApplications) {
	    i += 1;
	    sbStr.append(myApplication.getSqgw());
	    if (myApplications.size() > 1 && i < myApplications.size())
		sbStr.append("+");
	}

	if (resume != null) {
	    sbStr.append("_" + resume.getResumeName());
	    sbStr.append("_" + resume.getResumeMqszcsProvince() + resume.getResumeMqszcsCity());
	    sbStr.append("_" + resume.getResumeSex());
	    sbStr.append(Jyjl.size() > 0 ? ("_" + Jyjl.get(Jyjl.size() - 1).getResumeXl()) : "");
	}

	return StringUtils.deleteWhitespace(sbStr.toString().replace("/", "／"));

    }

    /** 
    * @Title: SavePdf 
    * @Description: word导出格式的设置itext
    * @param @param resumeId
    * @param @param contextString
    * @param @param request  参数说明 
    * @return void    返回类型 
    * @throws 
    */
    public void SavePdf(String resumeId, String contextString, HttpServletRequest request) {
	
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
	File file1 = new File(path + resume.getResumeId() + ".doc");
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
    }

    @ResponseBody
    @RequestMapping(value = "/LoadJlzy", method = RequestMethod.POST)
    public List<Zpzy> LoadZpzy(String type) {
	logInfo("/LoadJlzy", type);
	return zwglServices.LoadZpzy(type);
    }

    @ResponseBody
    @RequestMapping(value = "/LoadXxmc", method = RequestMethod.POST)
    public List<Xxmc> LoadXxmc(String type) {
	logInfo("/LoadXxmc", type);
	return xxmcService.LoadXxmc(type);
    }
}
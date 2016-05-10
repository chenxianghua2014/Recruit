/**
 * 前台主页 2014-06-25 孙建国
 */
package com.ttgis.recruit.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.MyApplication;
import com.ttgis.recruit.domain.Position;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.QueryKcgl;
import com.ttgis.recruit.domain.QueryXxtz;
import com.ttgis.recruit.domain.QueryZw;
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
import com.ttgis.recruit.domain.Xcxx;
import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.domain.Zpzy;
import com.ttgis.recruit.domain.Zylb;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.domain.ZzjgCondition;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.CollectionService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.MsjgService;
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
import com.ttgis.recruit.service.XcxxService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZpxwService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class MainController
{
	@Resource
	ZpxwService zpxwService;
	@Resource
	XcxxService xcxxService;
	@Resource
	ZwglService zwglService;
	@Resource
	XxtzService xxtzService;
	@Resource
	ZzjgService zzjgService;
	@Resource
	ResumeService resumeService;
	@Resource
	JtjlkService jtjlkService;
	@Resource
	CollectionService collectionService;
	@Autowired
	KcglService kcglService;
	@Autowired
	BmglService bmglService;
	@Autowired
	MsjgService msjgService;
	@Autowired
	Resume_jyjlService resume_jyjlService;
	
	
	
	
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

	static Logger log = Logger.getLogger(MainController.class);
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

	@RequestMapping(value = "/Main", method = RequestMethod.GET)
	public ModelAndView Main()
	{
		logInfo("Main", "");
		List<Zpxw> zpxws = zpxwService.select();
		ModelAndView mv = new ModelAndView();
		mv.addObject("zpxw", zpxws);
		List<Zzjg> zzjgs = zzjgService.selectBySjdw("test001");
		mv.addObject("zzjg", zzjgs);
		List<Zzjg> zzjgs3 = zzjgService.selectSanjidw("");
		mv.addObject("zzjg3", zzjgs3);
		List<Zylb> zylbs = zwglService.LoadZylbMain();
		for (Zylb zylb : zylbs)
		{
			List<Zpzy> zpzys = zwglService.LoadZpzy(zylb.getZylbName());
			zylb.setZpzys(zpzys);
		}
		mv.addObject("zylb", zylbs);
		return new ModelAndView("Main/Main", "mv", mv);
	}

	// a页面
	@RequestMapping(value = "/ErospaceAndDefense", method = RequestMethod.GET)
	public ModelAndView ErospaceAndDefense()
	{
		return new ModelAndView("shouyesidanye/ErospaceAndDefense");
	}

	// o页面
	@RequestMapping(value = "/TheProperty", method = RequestMethod.GET)
	public ModelAndView TheProperty()
	{
		return new ModelAndView("shouyesidanye/TheProperty");
	}

	// i页面
	@RequestMapping(value = "/NformationTechnology", method = RequestMethod.GET)
	public ModelAndView NformationTechnology()
	{
		return new ModelAndView("shouyesidanye/NformationTechnology");
	}

	// e页面
	@RequestMapping(value = "/QuipmentManufaturing", method = RequestMethod.GET)
	public ModelAndView QuipmentManufaturing()
	{
		return new ModelAndView("shouyesidanye/QuipmentManufaturing");
	}

	@RequestMapping(value = "/LoginFrmFirst", method = RequestMethod.GET)
	public ModelAndView LoginFrmFirst(HttpSession session)
	{
		return new ModelAndView("Main/LoginFrmFirst", "", session.getAttribute("userLoginSession"));
	}

	@RequestMapping(value = "/LoginFrm", method = RequestMethod.GET)
	public ModelAndView LoginFrm(HttpSession session)
	{
		return new ModelAndView("Main/LoginFrm", "", session.getAttribute("userLoginSession"));
	}

	@RequestMapping(value = "/ViewNews", method = RequestMethod.GET)
	public ModelAndView ViewNews(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("ViewNews", id);
		Zpxw zpxw = zpxwService.selectByPrimaryKey(id);
		return new ModelAndView("Main/ViewNews", "zpxw", zpxw);
	}

	@RequestMapping(value = "/Group", method = RequestMethod.GET)
	public ModelAndView Group(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("Group", id);
		Xcxx xcxx = xcxxService.selectByPrimaryKey("09E57BF4-128B-6D1F-D988-269C158134C4");
		return new ModelAndView("Main/Group", "xcxx", xcxx);
	}

	@RequestMapping(value = "/SearchPosition", method = RequestMethod.GET)
	public ModelAndView SearchPosition()
	{
		return new ModelAndView("Main/SearchPosition");
	}

	@ResponseBody
	@RequestMapping(value = "/LoadPositionData", method = RequestMethod.POST)
	public ModelAndView LoadPositionData(QueryZw q, HttpSession session)
	{
		logInfo("LoadPositionData", JSONArray.fromObject(q).toString());
		List<PositionWithBLOBs> positions = zwglService.selectByWhere1(q);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", positions);
		if (session.getAttribute("userLoginSession") != null)
			mv.addObject("isLogin", true);
		else
			mv.addObject("isLogin", false);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadPositionDataCount", method = RequestMethod.POST)
	public int LoadPositionDataCount(QueryZw q)
	{
		logInfo("LoadPositionDataCount", JSONArray.fromObject(q).toString());
		int intCount = zwglService.selectCount1(q);
		return intCount;
	}

	@RequestMapping(value = "/PositionDetails", method = RequestMethod.GET)
	public ModelAndView PositionDetails(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("PositionDetails", id);
		Position p = zwglService.selectByPrimaryKey(id);
		return new ModelAndView("Main/PositionDetails", "position", p);
	}

	@RequestMapping(value = "/SearchEnterprise", method = RequestMethod.GET)
	public ModelAndView SearchEnterprise(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("SearchEnterprise", id);
		id = "test001";
		String con = "";
		if (id == null || id.trim() == "")
		{
			con = "";
		} else
		{
			Zpxw zpxw = zpxwService.selectZprc(id);
			if (zpxw == null)
				con = "";
			else
				con = zpxw.getZpxwContent();
		}
		return new ModelAndView("Main/SearchEnterprise", "mav", con);
	}

	@RequestMapping(value = "/MyRecruit", method = RequestMethod.GET)
	public ModelAndView MyRecruit()
	{
		return new ModelAndView("Main/MyRecruit");
	}

	@RequestMapping(value = "/Message", method = RequestMethod.GET)
	public ModelAndView Message(HttpSession session)
	{
		logInfo("Message", "");
		ModelAndView mv = new ModelAndView();
		List<Xxtz> xxtzs = new ArrayList<Xxtz>();
		if (session.getAttribute("userLoginSession") != null)
		{
			mv.addObject("isLogin", true);
			QueryXxtz q = new QueryXxtz();
			q.setCurrentPageSize(1000000);
			q.setPageSize(1000000);
			q.setPageNum(1);
			q.setUserId(((Userinfo) session.getAttribute("userLoginSession")).getUserId());
			xxtzs = xxtzService.selectByWhere(q);
		} else
		{
			mv.addObject("isLogin", false);
		}
		mv.addObject("xxtzs", xxtzs);
		return new ModelAndView("Main/Message", "mv", mv);
	}

	@RequestMapping(value = "/MessageDetails", method = RequestMethod.GET)
	public ModelAndView MessageDetails(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("MessageDetails", id);
		ModelAndView mv = new ModelAndView();
		Xxtz xxtz = xxtzService.selectByPrimaryKey(id);
		xxtz.setXxtzIsread(0);
		Zzjg zzjg = zzjgService.selectByPrimaryKey(xxtz.getXxtzCuser());
		mv.addObject("zzjg", zzjg.getZzjgDwmc());
		mv.addObject("xxtz", xxtz);
		xxtzService.updateByPrimaryKeySelective(xxtz);
		return new ModelAndView("Main/MessageDetails", "mv", mv);
	}

	@RequestMapping(value = "/ApplyPosition", method = RequestMethod.GET)
	public ModelAndView ApplyPosition(HttpSession session, @RequestParam(value = "id", required = false) String id, String page) throws UnsupportedEncodingException
	{
		logInfo("ApplyPosition", id + "," + page);
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("positionId", id);
		params.put("userId", userinfo.getUserId());
		// 如果在已收藏职位列表里,删除
		//collectionService.deleteByPrimaryKey(params);
		// 已经在该单位已经报名了
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(id);
		List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(id);
		List<Resume_xnjl> Xnjl = resume_xnjlService.getlistResumeXnjlByResumeId(id);
		List<Resume_xnzw> Xnzw = resume_xnzwService.getlistResumeXnzwByResumeId(id);
		List<Resume_sjjl> Sjjl = resume_sjjlService.getlistResumeSjjlByResumeId(id);
		List<Resume_sx> Sxjl = resume_sxService.getlistResumeSxByResumeId(id);
		List<Resume_gzjl> Gzjl = resume_gzjlService.getlistResumeGzjlByResumeId(id);
		List<Resume_xmjy> Xmjy = resume_xmjyService.getlistResumeXmjyByResumeId(id);
		List<Resume_pxjl> Pxjl = resume_pxjlService.getlistResumePxjlByResumeId(id);
		List<Resume_yynl> Yynl = resume_yynlService.getlistResumeYynlByResumeId(id);
		List<Resume_qtxx> Qtxx = resume_qtxxService.getlistResumeQtxxByResumeId(id);
		List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(id);
		List<Resume_ITjn> ITjn = resume_ITjnService.getlistResumeITjnByResumeId(id);
		List<Resume_zs> Zs = resume_zsService.getlistResumeZsByResumeId(id);
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
		/*int isExist = jtjlkService.selectIsExist(params).size();
		if (isExist > 0)
		{
			
//			return new ModelAndView("Main/ShowAlert", "uri", page + "IsExist");
			return new ModelAndView("Grjl/Yljl", "mv", mv);
		}*/

		/*Map<String, String> paramsMore = new HashMap<String, String>();
		paramsMore.put("userId", userinfo.getUserId());
		// 不允许同时投递简历数量超过5家企业
		if (jtjlkService.selectIsExist(paramsMore).size() >= 5)
		{
			return new ModelAndView("Main/ShowAlert", "uri", page + "More");
		}*/
		
		// 没填写简历
		if (resume == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", page + "NoResume");
		}
		List<Resume_jyjl> resume_Jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(resume.getResumeId());
		Resume_jyjl resume_Jyjl = new Resume_jyjl();
		
		List<Resume_gzjl> resume_Gzjls = resume_gzjlService.getlistResumeGzjlByResumeId(resume.getResumeId());
		Resume_gzjl resume_Gzjl = resume_Gzjls.get(0); //取得最新工作经历
		// 没填写教育经历
		if (resume_Jyjls.size() == 0)
		{
			return new ModelAndView("Main/ShowAlert", "uri", page + "NoResume");
		} else
		{
			resume_Jyjl = resume_Jyjls.get(resume_Jyjls.size() - 1); //取得最高学历
		}

		Position position = zwglService.selectByPrimaryKey(id);
		Jtjlk jtjlk = new Jtjlk();
		jtjlk.setJtjlkAddtime(new Date());
		jtjlk.setJtjlkByxx(resume_Jyjl.getResumeXxmc()); //毕业学校
		
		//jtjlk.setJtjlkCpcj("未通知");
		jtjlk.setJtjlkCpcj(resume_Gzjl.getResumeGzgs()); //工作单位
		jtjlk.setJtjlkDelflag((long) 1);
		//jtjlk.setJtjlkGwlb(position.getPositionType()); 
		//jtjlk.setJtjlkZw(position.getPositionName()); 
		jtjlk.setJtjlkGwlb(resume.getResumeNypgw1());  //岗位类别改为拟应聘岗位1
		if(resume.getResumeNypgw2().equals("") || resume.getResumeNypgw2().equals(null)){
			resume.setResumeNypgw2("无");
		}
		jtjlk.setJtjlkZw(resume.getResumeNypgw2());    //岗位名称改为拟应聘岗位2
		
		
		jtjlk.setJtjlkJlzt(resume_Gzjl.getResumeGzcs());  //简历状态改为职务
		//jtjlk.setJtjlkCpjg("未测评"); //面试结果改为外语种类
		jtjlk.setJtjlkCpjg(Yynl.get(0).getResumeYylb()); 
		
		//jtjlk.setJtjlkMszt("未安排面试");//改为联系电话
		jtjlk.setJtjlkMszt(resume.getResumeTelphone());
		
		jtjlk.setJtjlkName(userinfo.getUserName());  //姓名
		//jtjlk.setJtjlkPositionId(position.getPositionId());
		jtjlk.setJtjlkPositionId(id);
		
		jtjlk.setJtjlkSex(resume.getResumeSex()); //性别
		jtjlk.setJtjlkUserid(userinfo.getUserId());
		jtjlk.setJtjlkXl(resume_Jyjl.getResumeXl()); //学历
		
		jtjlk.setJtjlkZy(resume_Jyjl.getResumeZy()); //专业
		//jtjlk.setZzjgId(position.getZzjgId()); //组织机构代码
		jtjlk.setZzjgId("AD782CAC-E5E4-33FD-1763-67A6F967328C"); //组织机构代码 设置为培训中心组织代码
		
		//jtjlk.setJtjlkLy("应聘者");//改为学位
		jtjlk.setJtjlkLy(resume_Jyjl.getResumeZyms());
		jtjlk.setJtjlkHkd(resume.getResumeRxqhkszcsProvince() + "," + resume.getResumeRxqhkszcsCity());  //户口所在地
		//jtjlk.setJtjlkYxpm(resume_Jyjl.getResumeYxpm()); //院系排名
		jtjlk.setJtjlkYxpm(resume.getResumeCjgzsj()); //院系排名改为参加工作时间
		
		//jtjlk.setJtjlkBjpm(resume_Jyjl.getResumeBjpm()); // 班级排名
		if(!resume.getResumeZzmm().equals("党员")){
			resume.setResumeQtjn("无");
		}
		jtjlk.setJtjlkBjpm(resume.getResumeQtjn()); // 班级排名改为入党时间
		jtjlk.setJtjlkCsrq(resume.getResumeBirthday()); //出生年月
		jtjlk.setJtjlkSfzh(userinfo.getUserSfzh());  //身份证号
		List<Jtjlk> seList = jtjlkService.selectStatusByUserId(userinfo.getUserId());
		if (seList.size() == 0)
			jtjlk.setJtjlkJtjlkflag(2);
		else
			jtjlk.setJtjlkJtjlkflag(1);
		
		List<Jtjlk> jtjlkList = jtjlkService.selectBySfzh(userinfo.getUserSfzh());
		if(jtjlkList.size() != 0){
			jtjlk.setJtjlkId(jtjlkList.get(0).getJtjlkId());
			jtjlkService.updateByPrimaryKeySelective(jtjlk);
		}else{
			jtjlk.setJtjlkId(RandomGUIDUtil.generateKey());
			jtjlkService.insertSelective(jtjlk);
		}
		//return new ModelAndView("Main/ShowAlert", "uri", page + "ApplySuccess");
		return new ModelAndView("Grjl/Yljl", "mv", mv);
	}

	@RequestMapping(value = "/MyApplication", method = RequestMethod.GET)
	public ModelAndView MyApplication(HttpSession session)
	{
		logInfo("MyApplication", "");
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		} else
		{
			List<MyApplication> myApplications = jtjlkService.selectMyApplication(userinfo.getUserId());
			return new ModelAndView("Main/MyApplication", "Applications", myApplications);
		}
	}

	@RequestMapping(value = "/Revoke", method = RequestMethod.GET)
	public ModelAndView Revoke(HttpSession session, @RequestParam(value = "id", required = false) String id)
	{
		logInfo("Revoke", id);
		Userinfo userinfo = (Userinfo) session.getAttribute("userLoginSession");
		if (userinfo == null)
		{
			return new ModelAndView("Main/ShowAlert", "uri", "SessionLost");
		} else
		{
			bmglService.delByJtjlkId(id);
			msjgService.deleteByForeignKey(id);
			jtjlkService.deleteByPrimaryKey(id);
			return new ModelAndView("Main/ShowAlert", "uri", "MyApplicationRevoke");
		}
	}

	@RequestMapping(value = "/Xzcc", method = RequestMethod.GET)
	public ModelAndView Xzcc()
	{
		return new ModelAndView("kcgl/xzcc");
	}

	@ResponseBody
	@RequestMapping(value = "/qtKcglData", method = RequestMethod.POST)
	public List<Kcgl> qtKcglData(QueryKcgl qk)
	{
		logInfo("qtKcglData", JSONArray.fromObject(qk).toString());
		qk.setKcglKczt("开放");
		List<Kcgl> listKcgl = kcglService.selectByWhere(qk);
		return listKcgl;
	}

	@ResponseBody
	@RequestMapping(value = "/qtKcglDataCount", method = RequestMethod.POST)
	public int qtKcglDataCount(QueryKcgl qk)
	{
		logInfo("qtKcglDataCount", JSONArray.fromObject(qk).toString());
		qk.setKcglKczt("开放");
		int intCount = kcglService.selectCount(qk);
		return intCount;
	}

	@RequestMapping(value = "/PrintTestCard", method = RequestMethod.GET)
	public ModelAndView PrintTestCard(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("PrintTestCard", id);
		Bmgl bmgl = new Bmgl();
		bmgl = bmglService.queryBmglById(id);
		return new ModelAndView("Main/PrintTestCard", "bmgl", bmgl);
	}

	@ResponseBody
	@RequestMapping(value = "/DoPrintTestCard", method = RequestMethod.POST)
	public int DoPrintTestCard(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("DoPrintTestCard", id);
		Bmgl bmgl = new Bmgl();
		bmgl = bmglService.queryBmglById(id);
		bmgl.setBmglPrintflag((long) 0);
		bmgl.setBmglSfqr("是");
		bmglService.updKscjById(bmgl);
		return 0;
	}

	/**
	 * 咨询中心Count
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/InformationCenterCount", method = RequestMethod.POST)
	public int InformationCenterCount(ZzjgCondition p)
	{
		if (p == null)
		{
			logInfo("InformationCenterCount", null);
			return 0;
		}
		logInfo("InformationCenterCount", JSONArray.fromObject(p).toString());
		p.setZPXW_CONTENT(p.getZPXW_CONTENT().replaceAll("'", ""));
		int count = zpxwService.selectCountNoTop(p);
		return count;
	}

	/**
	 * 查找上级单位和二级单位 当参数为空时查找的是所有上级单位 不为空时查找的是上级单位对应的二级单位
	 * 
	 * @param p
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectBySjdw", method = RequestMethod.POST)
	public List<Zzjg> selectBySjdw(String sjdw)
	{
		logInfo("selectBySjdw", sjdw);
		List<Zzjg> list = null;
		if (sjdw == null || sjdw == "")
			list = zzjgService.selectBySjdw("");// 返回一级单位
		else
			list = zzjgService.selectBySjdw(sjdw);// 返回下级单位
		return list;
	}

	/**
	 * 资讯中心分页查询
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/InformationCenterData", method = RequestMethod.POST)
	public List<Zpxw> InformationCenterData(ZzjgCondition p)
	{
		logInfo("InformationCenterData", JSONArray.fromObject(p).toString());
		if (p == null)
			return null;
		p.setZPXW_CONTENT(p.getZPXW_CONTENT().replaceAll("'", ""));
		List<Zpxw> zpxws = zpxwService.selectZpxwByWhereNoTop(p);
		List<Zpxw> tops = zpxwService.selectZpxwByWhereTop(p);
		for(Zpxw z : tops){
			z.setZpxwTop("top");
			zpxws.add(z);
		}
		return zpxws;
	}

	/**
	 * 资讯中心
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/InformationCenter", method = RequestMethod.GET)
	public ModelAndView InformationCenter(HttpSession session, ZzjgCondition p)
	{
		logInfo("InformationCenter", JSONArray.fromObject(p).toString());
		return new ModelAndView("Main/InformationCenter");
	}

	@RequestMapping(value = "/GroupIntroduction", method = RequestMethod.GET)
	public ModelAndView GroupIntroduction(HttpSession session)
	{
		logInfo("GroupIntroduction", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "集团简介");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/GroupIntroduction", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/OfficialsAddress", method = RequestMethod.GET)
	public ModelAndView OfficialsAddress(HttpSession session)
	{
		logInfo("OfficialsAddress", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "领导致辞");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/OfficialsAddress", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Organization", method = RequestMethod.GET)
	public ModelAndView Organization(HttpSession session)
	{
		logInfo("Organization", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "组织机构");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Organization", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/DevelopmentProcess", method = RequestMethod.GET)
	public ModelAndView DevelopmentProcess(HttpSession session)
	{
		logInfo("DevelopmentProcess", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "发展历程");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/DevelopmentProcess", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Corporateleadership", method = RequestMethod.GET)
	public ModelAndView Corporateleadership(HttpSession session)
	{
		logInfo("Corporateleadership", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "企业领导");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Corporateleadership", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/CorporateCulture", method = RequestMethod.GET)
	public ModelAndView CorporateCulture(HttpSession session)
	{
		logInfo("CorporateCulture", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "企业文化");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/CorporateCulture", "xcxxs", xcxxs);
	}

	// 招聘指南

	@RequestMapping(value = "/AboutOnlineApplication", method = RequestMethod.GET)
	public ModelAndView AboutOnlineApplication(HttpSession session)
	{
		logInfo("AboutOnlineApplication", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "关于网申");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/AboutOnlineApplication", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/AboutTheTest", method = RequestMethod.GET)
	public ModelAndView AboutTheTest(HttpSession session)
	{
		logInfo("AboutTheTest", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "关于约考");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/AboutTheTest", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/AboutEvaluation", method = RequestMethod.GET)
	public ModelAndView AboutEvaluation(HttpSession session)
	{
		logInfo("AboutEvaluation", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "关于测评");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/AboutEvaluation", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/AboutMobile", method = RequestMethod.GET)
	public ModelAndView AboutMobile(HttpSession session)
	{
		logInfo("AboutMobile", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "关于移动招聘");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/AboutMobile", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Recruitment", method = RequestMethod.GET)
	public ModelAndView Recruitment(HttpSession session)
	{
		logInfo("Recruitment", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "招聘");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Recruitment", "xcxxs", xcxxs);
	}

	// 员工发展
	@RequestMapping(value = "/TalentStrategy", method = RequestMethod.GET)
	public ModelAndView TalentStrategy(HttpSession session)
	{
		logInfo("TalentStrategy", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "人才战略");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/TalentStrategy", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Personnel", method = RequestMethod.GET)
	public ModelAndView Personnel(HttpSession session)
	{
		logInfo("Personnel", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "人才队伍");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Personnel", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/AcademiciansAndExperts", method = RequestMethod.GET)
	public ModelAndView AcademiciansAndExperts(HttpSession session)
	{
		logInfo("AcademiciansAndExperts", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "院士专家");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/AcademiciansAndExperts", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Career", method = RequestMethod.GET)
	public ModelAndView Career(HttpSession session)
	{
		logInfo("Career", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "职业生涯");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Career", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/Employee", method = RequestMethod.GET)
	public ModelAndView Employee(HttpSession session)
	{
		logInfo("Employee", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "员工风采");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/Employee", "xcxxs", xcxxs);
	}

	@RequestMapping(value = "/StaffDevelopment", method = RequestMethod.GET)
	public ModelAndView StaffDevelopment(HttpSession session)
	{
		logInfo("StaffDevelopment", "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxType", "员工成长");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		return new ModelAndView("Main/StaffDevelopment", "xcxxs", xcxxs);
	}

	/**
	 * 单位简介
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/InstituteOf", method = RequestMethod.GET)
	public ModelAndView InstituteOf(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("InstituteOf", "");
		ModelAndView mv = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		map.put("xcxxId", "");
		map.put("xcxxAdduser", id);
		map.put("xcxxType", "单位简介");
		List<Xcxx> xcxxs = xcxxService.selectByType(map);
		mv.addObject("xcxxs", xcxxs);
		mv.addObject("zzjgs", GetSubZzjgs(id));
		return new ModelAndView("Main/InstituteOf", "mv", mv);
	}

	public List<Zzjg> GetSubZzjgs(String strZzjgId)
	{
		List<Zzjg> zzjgs = new ArrayList<Zzjg>();
		zzjgs = zzjgService.selectBySjdw(strZzjgId);
		Zzjg cZzjg = zzjgService.selectByPrimaryKey(strZzjgId);
		if (zzjgs.size() == 0 && !cZzjg.getZzjgSjdw().equals("test001"))
		{
			zzjgs = zzjgService.selectBySjdw(cZzjg.getZzjgSjdw());
			zzjgs.add(0, zzjgService.selectByPrimaryKey(cZzjg.getZzjgSjdw()));
		} else
		{
			zzjgs.add(0, zzjgService.selectByPrimaryKey(strZzjgId));
		}
		return zzjgs;
	}

	/**
	 * 二级单位招聘动态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/RecruitmentDynamics", method = RequestMethod.GET)
	public ModelAndView RecruitmentDynamics(@RequestParam(value = "id", required = false) String id)
	{
		logInfo("RecruitmentDynamics", id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("zzjgs", GetSubZzjgs(id));
		return new ModelAndView("Main/RecruitmentDynamics", "mv", mv);
	}

	/**
	 * 二级单位招聘动态详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/RecruitmentDynamicsDetails", method = RequestMethod.GET)
	public ModelAndView RecruitmentDynamicsDetails(@RequestParam(value = "newsId", required = false) String newsId)
	{
		logInfo("RecruitmentDynamicsDetails", newsId);
		Zpxw zpxw = zpxwService.selectByPrimaryKey(newsId);
		return new ModelAndView("Main/RecruitmentDynamicsDetails", "zpxw", zpxw);
	}

}

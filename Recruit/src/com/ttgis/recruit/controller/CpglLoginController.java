package com.ttgis.recruit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.Ksxtkbgl;
import com.ttgis.recruit.domain.Resume_jyjl;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.service.KsxtkbglService;
import com.ttgis.recruit.service.Resume_jyjlService;
import com.ttgis.recruit.service.TkglService;
import com.ttgis.recruit.service.ZzjgService;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。CpglLoginController
 * @创建人： 范井龙
 * @日期：
 * @描述： 测评登陆及前台页面链接方法
 * @版本号：
 */
@Controller
public class CpglLoginController
{
	@Autowired
	private BmglService bmglService;
	@Autowired
	private KsxcglService ksxcglService;
	@Autowired
	private TkglService tkglService;
	@Autowired
	private KsxtkbglService ksxtkbglService;
	@Autowired
	private KcglService kcglService;
	@Autowired
	private JtjlkService jtjlkService;
	@Autowired
	private ZzjgService zzjgService;
	@Autowired
	private Resume_jyjlService resume_jyjlService;
	
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
	
	@RequestMapping(value = "/DLcpgl", method = RequestMethod.GET)
	public ModelAndView DLcpgl()
	{
		return new ModelAndView("cpgl/cpglLogin");
	}

	@RequestMapping(value = "/cpglKszl", method = RequestMethod.GET)
	public ModelAndView cpglKszl()
	{
		return new ModelAndView("cpgl/kszl");
	}

	@RequestMapping(value = "/cpglCxsm", method = RequestMethod.GET)
	public ModelAndView cpglCxsm()
	{
		return new ModelAndView("cpgl/cxsm");
	}

	@RequestMapping(value = "/cpglKssm", method = RequestMethod.GET)
	public ModelAndView cpglKssm(HttpSession session)
	{
		logInfo("cpglKssm", "");
		Bmgl bmgl = ((Bmgl) session.getAttribute("cpglLoginSession1"));
		if (bmgl.getBmglBkgw().equals("管理岗"))
			session.setAttribute("currentBmgl", bmgl);
		else
			session.setAttribute("currentBmgl", (Bmgl) session.getAttribute("cpglLoginSession2"));
		return new ModelAndView("cpgl/kssm");
	}

	@RequestMapping(value = "/cpglKssm1", method = RequestMethod.GET)
	public ModelAndView cpglKssm1(HttpSession session)
	{
		logInfo("cpglKssm1", "");
		Bmgl bmgl = ((Bmgl) session.getAttribute("cpglLoginSession1"));
		if (bmgl.getBmglBkgw().equals("技术岗"))
			session.setAttribute("currentBmgl", bmgl);
		else
			session.setAttribute("currentBmgl", (Bmgl) session.getAttribute("cpglLoginSession2"));
		return new ModelAndView("cpgl/kssm1");
	}

	@RequestMapping(value = "/cpglKssm2", method = RequestMethod.GET)
	public ModelAndView cpglKssm2()
	{
		return new ModelAndView("cpgl/kssm2");
	}

	@RequestMapping(value = "/cpglGxsm", method = RequestMethod.GET)
	public ModelAndView cpglGxsm(HttpSession session, Model model)
	{
		logInfo("cpglGxsm", "");
		Bmgl bmgl = ((Bmgl) session.getAttribute("cpglLoginSession1"));
		Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(bmgl.getBmglJtjlkid());
		List<Resume_jyjl> jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(jtjlk.getJtjlkUserid());
		Resume_jyjl resume_Jyjl = new Resume_jyjl();
		// 没填写教育经历
		if (jyjls.size() > 0)
		{ 
			resume_Jyjl = jyjls.get(jyjls.size() - 1);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "管理岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		model.addAttribute("kcglName", kcgl.getKcglName());
		model.addAttribute("jyjl", resume_Jyjl);
		model.addAttribute("zzjgs", zzjgs);
		return new ModelAndView("cpgl/gxsm");
	}

	@RequestMapping(value = "/cpgljsgGxsm", method = RequestMethod.GET)
	public ModelAndView cpgljsgGxsm(HttpSession session, Model model)
	{
		logInfo("cpgljsgGxsm", "");
		Bmgl bmgl = ((Bmgl) session.getAttribute("cpglLoginSession1"));
		Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(bmgl.getBmglJtjlkid());
		List<Resume_jyjl> jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(jtjlk.getJtjlkUserid());
		Resume_jyjl resume_Jyjl = new Resume_jyjl();
		// 没填写教育经历
		if (jyjls.size() > 0)
		{ 
			resume_Jyjl = jyjls.get(jyjls.size() - 1);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "技术岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		model.addAttribute("kcglName", kcgl.getKcglName());
		model.addAttribute("jyjl", resume_Jyjl);
		model.addAttribute("zzjgs", zzjgs);
		return new ModelAndView("cpgl/jsgGxsm");
	}

	@RequestMapping(value = "/successSubmit", method = RequestMethod.GET)
	public ModelAndView successSubmit()
	{
		return new ModelAndView("cpgl/successSubmit");
	}

	@RequestMapping(value = "/successSubmit1", method = RequestMethod.GET)
	public ModelAndView successSubmit1()
	{
		return new ModelAndView("cpgl/successSubmit1");
	}

	@RequestMapping(value = "/successSubmit2", method = RequestMethod.GET)
	public ModelAndView successSubmit2()
	{
		return new ModelAndView("cpgl/successSubmit2");
	}

	@RequestMapping(value = "/cpglCpsj", method = RequestMethod.GET)
	public ModelAndView cpglCpsj()
	{
		return new ModelAndView("cpgl/cpsj");
	}

	@RequestMapping(value = "/CpglLogin", method = RequestMethod.POST)
	public String CpglLogin(Bmgl bmgl, HttpSession session, Model model)
	{
		logInfo("CpglLogin", JSONArray.fromObject(bmgl).toString());
		// 看该考试系统内现在是否存在考试安排（时间段查询）
		List<Ksxtkbgl> kssjList = ksxtkbglService.queryKssj();
		if (kssjList.size() > 0)
		{
			// 查询改考生成绩
			List<Ksxcgl> ksxcglList = ksxcglService.queryByName(bmgl.getBmglKsxm());
			// 查询报名人员登陆信息
			List<Bmgl> listLoginBmgl = bmglService.CheckCpglLogin(bmgl);
			if (ksxcglList.size() == 0)
			{
				if (listLoginBmgl == null || listLoginBmgl.size() <= 0)
				{
					String message = "姓名或者身份证号错误，请重新输入！";
					model.addAttribute("message", message);
					return "cpgl/cpglLogin";
				}
				if (listLoginBmgl.size() == 1)
				{
					session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));

				} else
				{
					session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
					session.setAttribute("cpglLoginSession2", listLoginBmgl.get(1));
				}
			} else if (ksxcglList.size() > 0)
			{
				// 判断是否存在个性测试成绩
				int gxcsCount = tkglService.checkGxcsCount(bmgl.getBmglSfzh());
				if (gxcsCount <= 0)
				{
					if (listLoginBmgl == null || listLoginBmgl.size() <= 0)
					{
						String message = "姓名或者身份证号错误，请重新输入！";
						model.addAttribute("message", message);
						return "cpgl/cpglLogin";
					} else if (listLoginBmgl.size() == 1)
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						return "cpgl/cxsm";

					} else
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						session.setAttribute("cpglLoginSession2", listLoginBmgl.get(1));
						return "cpgl/cxsm";
					}
				} else if (ksxcglList.get(0).getKsxcglKscjGlg() == null && ksxcglList.get(0).getKsxcglKscjJsg() == null)
				{
					if (listLoginBmgl == null || listLoginBmgl.size() <= 0)
					{
						String message = "姓名或者身份证号错误，请重新输入！";
						model.addAttribute("message", message);
						return "cpgl/cpglLogin";
					} else if (listLoginBmgl.size() == 1)
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						return "cpgl/cxsm";

					} else
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						session.setAttribute("cpglLoginSession2", listLoginBmgl.get(1));
						return "cpgl/cxsm";
					}
				} else if (((ksxcglList.get(0).getKsxcglKscjGlg()) != null && (ksxcglList.get(0).getKsxcglKscjJsg()) == null) || ((ksxcglList.get(0).getKsxcglKscjGlg()) == null && (ksxcglList.get(0).getKsxcglKscjJsg()) != null))
				{
					if (listLoginBmgl == null || listLoginBmgl.size() <= 0)
					{
						String message = "姓名或者身份证号错误，请重新输入！";
						model.addAttribute("message", message);
						return "cpgl/cpglLogin";
					} else if (listLoginBmgl.size() == 1)
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						return "cpgl/cxsm";

					} else
					{
						session.setAttribute("cpglLoginSession1", listLoginBmgl.get(0));
						session.setAttribute("cpglLoginSession2", listLoginBmgl.get(1));
						return "cpgl/cxsm";
					}
				} else
				{
					String message = "抱歉您已经参加过考试了，不能重复进行！";
					model.addAttribute("message", message);
					return "cpgl/cpglLogin";
				}

			} else
			{
				String message = "抱歉您已经参加过考试了，不能重复进行！";
				model.addAttribute("message", message);
				return "cpgl/cpglLogin";
			}
			return "cpgl/cxsm";
		} else
		{
			String message = "抱歉，现在没有考试安排，请关注最新考试安排！";
			model.addAttribute("message", message);
			return "cpgl/cpglLogin";
		}

	}

	@RequestMapping(value = "/CpglLogout", method = RequestMethod.GET)
	public String CpglLogout(HttpSession session)
	{
		logInfo("CpglLogin", "");
		session.removeAttribute("cpglLoginSession");
		return "Main/LoginFrm";
	}

}

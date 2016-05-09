package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
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
import com.ttgis.recruit.domain.QueryJygl;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.MD5;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @类名： com.ttgis.recruit.controller。ZzjgController
 * @创建人： 范井龙
 * @日期：
 * @描述：组织机构管理（后台登陆用户）
 * @版本号：
 */
@Controller
public class ZzjgController
{
	static Logger log = Logger.getLogger(ZzjgController.class);
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

	@Autowired
	private ZzjgService zzjgService;

	@RequestMapping(value = "/htRecruit", method = RequestMethod.GET)
	public ModelAndView Recruit()
	{
		return new ModelAndView("login/login");
	}

	@RequestMapping(value = "/dlsuccess", method = RequestMethod.GET)
	public ModelAndView Index()
	{
		return new ModelAndView("Admin/Index");
	}

	@RequestMapping(value = "/Calendar", method = RequestMethod.GET)
	public ModelAndView Calendar()
	{
		return new ModelAndView("Admin/Calendar");
	}

	/**
	 * 后台用户登录
	 * 
	 * @param zzjg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/zzjgLogin", method = RequestMethod.POST)
	public Model Checklogin(HttpServletRequest request, HttpSession session, Model model)
	{
		String pwdMd5 = MD5.getMD5ofStr(request.getParameter("txtPassword"));
		String userName = request.getParameter("txtUserName");
		logInfo("zzjgLogin", userName + "," + pwdMd5);
		Zzjg zzjg = new Zzjg();
		zzjg.setZzjgDwzh(userName);
		zzjg.setZzjgZhmm(pwdMd5);
		log.info("帐号" + zzjg.getZzjgDwzh() + "密码" + zzjg.getZzjgZhmm());
		zzjg = zzjgService.CheckLogin(zzjg);
		if (zzjg == null)
		{
			model.addAttribute("message", "用户名或者密码错误！");
			return model;
		}
		try
		{
			session.setAttribute("loginSession", zzjg);
			session.setAttribute("zzjgId", zzjg.getZzjgId());
			session.setAttribute("zzjgName", zzjg.getZzjgDwmc());
			session.setAttribute("zzjgSjdw", zzjg.getZzjgSjdw());
			model.addAttribute("message", "登录成功！");
		} catch (Exception e)
		{
			System.err.println("login操作失败!!");
			e.printStackTrace();
			model.addAttribute("message", "登录失败,请联系管理员！");
		}
		return model;
	}

	@RequestMapping(value = "/zzjgLogout", method = RequestMethod.GET)
	public String logout(HttpSession session)
	{
		logInfo("zzjgLogout", "");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/zzjgIndex", method = RequestMethod.GET)
	public String zzjgIndex(Model model)
	{
		logInfo("zzjgIndex", "");
		List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
		model.addAttribute("zzjgFOList", zzjgFOList);
		return "user/userIndex";
	}

	@RequestMapping(value = "/zzjgLeft", method = RequestMethod.GET)
	public ModelAndView zzjgLeft()
	{
		return new ModelAndView("zzjg/zzjgLeft");
	}

	@RequestMapping(value = "/zzjgAdd", method = RequestMethod.GET)
	public String zzjgAdd(String zzjgId, Model model)
	{
		logInfo("zzjgAdd", zzjgId);
		if (zzjgId == null)
		{
			List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
			model.addAttribute("zzjgFOList", zzjgFOList);
			return "user/userAdd";
		} else
		{
			Zzjg zzjg = new Zzjg();
			zzjg = zzjgService.selectByPrimaryKey(zzjgId);
			List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
			model.addAttribute("zzjg", zzjg);
			model.addAttribute("zzjgFOList", zzjgFOList);
			return "user/userAdd";
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/zzjgSave", method = RequestMethod.POST)
	public ModelAndView zzjgSave(Zzjg zzjg)
	{
		logInfo("zzjgSave", JSONArray.fromObject(zzjg).toString());
		String pwdMd5 = MD5.getMD5ofStr(zzjg.getZzjgZhmm());
		if (zzjg.getZzjgId().equals(""))
		{
			zzjg.setSfjy("0");
			zzjg.setZzjgAddtime(new Date());
			zzjg.setZzjgZhmm(pwdMd5);
			zzjg.setZzjgId(RandomGUIDUtil.generateKey());
			zzjg.setZzjgDelflag((long) 1);
			zzjgService.insertSelective(zzjg);
		} else
		{
			zzjg.setZzjgZhmm(pwdMd5);
			zzjgService.updateByPrimaryKeySelective(zzjg);
		}
		return new ModelAndView("redirect:/queryZzjgFO");
	}

	/**
	 * 删除信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delZzjg", method = RequestMethod.GET)
	public String delzzjg(Zzjg zzjg)
	{
		logInfo("delZzjg", JSONArray.fromObject(zzjg).toString());
		zzjg.setZzjgDelflag((long) 0);
		zzjgService.updateByPrimaryKeySelective(zzjg);
		return "redirect:/queryZzjgFO";
	}

	/**
	 * 查询用户菜单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryZzjgFO", method = RequestMethod.GET)
	public String queryzzjgFO(Model model)
	{
		logInfo("queryZzjgFO", "");
		List<Zzjg> zzjgFOList = zzjgService.queryUserFO();
		List<Zzjg> zzjgSunList = zzjgService.queryUserSun();
		model.addAttribute("zzjgFOList", zzjgFOList);
		model.addAttribute("zzjgSunList", zzjgSunList);
		return "user/userLeft";
	}

	/**
	 * 重置密码
	 * 
	 * @param zzjg
	 * @return
	 */
	@RequestMapping(value = "/czmm", method = RequestMethod.GET)
	public String czmm(Zzjg zzjg)
	{
		logInfo("czmm", JSONArray.fromObject(zzjg).toString());
		String pwdMd5 = MD5.getMD5ofStr("123456");
		zzjg.setZzjgZhmm(pwdMd5);
		zzjgService.updateByPrimaryKeySelective(zzjg);
		return "redirect:/zzjgIndex";
	}

	@RequestMapping(value = "/getZzjg", method = RequestMethod.GET)
	public String getzzjg(Zzjg zzjgq, HttpSession session, Model model)
	{
		logInfo("getZzjg", JSONArray.fromObject(zzjgq).toString());
		zzjgq = (Zzjg) session.getAttribute("loginSession");
		Zzjg zzjg = new Zzjg();
		zzjg = zzjgService.selectByPrimaryKey(zzjgq.getZzjgId());
		model.addAttribute("zzjg", zzjg);
		return "user/updUser";
	}

	@RequestMapping(value = "/updZzjg", method = RequestMethod.POST)
	public ModelAndView updzzjg(Zzjg zzjg)
	{
		logInfo("updZzjg", JSONArray.fromObject(zzjg).toString());
		String pwdMd5 = MD5.getMD5ofStr(zzjg.getZzjgZhmm());
		zzjg.setZzjgZhmm(pwdMd5);
		zzjgService.updateByPrimaryKeySelective(zzjg);
		return new ModelAndView("success", "uri", "updZzjg");
	}

	@RequestMapping(value = "/reviewZzjg", method = RequestMethod.GET)
	public String reviewZzjg(Zzjg zzjgq, HttpSession session, Model model)
	{
		logInfo("reviewZzjg", JSONArray.fromObject(zzjgq).toString());
		zzjgq = (Zzjg) session.getAttribute("loginSession");
		Zzjg zzjg = new Zzjg();
		zzjg = zzjgService.selectByPrimaryKey(zzjgq.getZzjgId());
		model.addAttribute("zzjg", zzjg);
		return "user/reviewUser";
	}

	// =======================================================================================

	@RequestMapping(value = "/fhyfy", method = RequestMethod.GET)
	public ModelAndView fhyfy()
	{
		return new ModelAndView("hygl/fhyfy");
	}

	@ResponseBody
	@RequestMapping(value = "/queryFhyfy", method = RequestMethod.POST)
	public List<Zzjg> queryFhyfy(QueryJygl qj)
	{
		logInfo("queryFhyfy", JSONArray.fromObject(qj).toString());
		List<Zzjg> zzjgList = zzjgService.fhyfySelectByWhere(qj);
		return zzjgList;
	}

	@ResponseBody
	@RequestMapping(value = "/queryFhyfyCount", method = RequestMethod.POST)
	public int queryFhyfyCount(QueryJygl qj)
	{
		logInfo("queryFhyfyCount", JSONArray.fromObject(qj).toString());
		int intCount = zzjgService.fhyfySelectCount(qj);
		return intCount;
	}

	@RequestMapping(value = "/queryFhyfyById", method = RequestMethod.GET)
	public String queryFhyfyById(String zzjgId, Model model)
	{
		logInfo("queryFhyfyById", zzjgId);
		Zzjg zzjg = zzjgService.selectByPrimaryKey(zzjgId);
		model.addAttribute("zzjg", zzjg);
		return "hygl/updFhyfy";
	}

	@RequestMapping(value = "/SaveFhyfy", method = RequestMethod.POST)
	public ModelAndView SaveCpfygl(Zzjg zzjg)
	{
		logInfo("SaveFhyfy", JSONArray.fromObject(zzjg).toString());
		zzjgService.updateByPrimaryKeySelective(zzjg);
		return new ModelAndView("redirect:/fhyfy");
	}

	// ==============生成PDF非会员====================================================================
	@RequestMapping(value = "/exportFhyfy", method = RequestMethod.GET)
	public ModelAndView Image(HttpServletRequest request, HttpServletResponse response)
	{
		logInfo("exportFhyfy", "");
		List<Zzjg> cpfyglList = zzjgService.queryFhyfy();
		File file = new File(request.getSession().getServletContext().getRealPath("pdf"));
		String path = new java.util.Date().getTime() + "Fhyfygl.pdf";
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
					e.printStackTrace();
					logInfo("exportFhyfy", "******Error-1");
				}
			}
		}
		try
		{
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(file1));
			doc.open();
			// 标题字体
			// 标题字体
			BaseFont bf = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font titleFont = new Font(bf, 18, Font.BOLD);
			BaseFont bfComic = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			PdfPTable table = new PdfPTable(7);
			PdfPCell cell = new PdfPCell(new Phrase("测评费用（非会员）", titleFont));
			cell.setUseAscender(true);
			cell.setUseDescender(true);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(7);
			table.addCell(cell);
			table.addCell(new PdfPCell(new Phrase("序号", font)));
			table.addCell(new PdfPCell(new Phrase("单位代码", font)));
			table.addCell(new PdfPCell(new Phrase("单位名称", font)));
			table.addCell(new PdfPCell(new Phrase("应缴纳费用", font)));
			table.addCell(new PdfPCell(new Phrase("已缴费用", font)));
			table.addCell(new PdfPCell(new Phrase("测评次数", font)));
			table.addCell(new PdfPCell(new Phrase("费用余额", font)));
			int a = 1;
			for (Zzjg zzjg : cpfyglList)
			{
				table.addCell(new PdfPCell(new Phrase((a++) + "", font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getZzjgDwdm(), font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getZzjgDwmc(), font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getYfkye(), font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getYffy(), font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getCpcs(), font)));
				table.addCell(new PdfPCell(new Phrase(zzjg.getSycs(), font)));
			}
			doc.add(table);
			doc.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			logInfo("exportFhyfy", "******Error-2");
		}
		return new ModelAndView("pdf/fhyfy", "uri", "pdf/" + path);
	}

	// ==============二级密码修改====================================================================
	@RequestMapping(value = "/updEJMM", method = RequestMethod.GET)
	public ModelAndView updEJMM()
	{
		return new ModelAndView("user/updEJMM");
	}

	@RequestMapping(value = "/updZzjgEjmm", method = RequestMethod.POST)
	public ModelAndView updZzjgEjmm(Zzjg zzjg, HttpSession session)
	{
		logInfo("updZzjgEjmm", JSONArray.fromObject(zzjg).toString());
		zzjgService.updateByPrimaryKeySelective(zzjg);
		Zzjg zzig1 = (Zzjg) session.getAttribute("loginSession");
		zzig1.setZzjgEjmm(zzjg.getZzjgEjmm());
		session.setAttribute("loginSession", zzig1);
		return new ModelAndView("success", "uri", "updZzjgEjmm");
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkmm", method = RequestMethod.POST)
	public String checkmm(String mm, HttpSession session)
	{
		logInfo("checkmm", mm);
		Zzjg zzig1 = (Zzjg) session.getAttribute("loginSession");
		if(mm==null||"".equals(mm))return "false";
		if(mm.equals(zzig1.getZzjgEjmm()))return "true";
		return "false";
	}

	@RequestMapping(value = "/updNewZzjgEjmm", method = RequestMethod.POST)
	public ModelAndView updNewZzjgEjmm(Zzjg zzjg, HttpSession session, String newZzjgEjmm, String oldZzjgEjmm)
	{
		logInfo("updNewZzjgEjmm", JSONArray.fromObject(zzjg).toString() + "------," + newZzjgEjmm + "," + oldZzjgEjmm);
		Zzjg oldZzjg = zzjgService.selectByPrimaryKey(zzjg.getZzjgId());
		if (!oldZzjgEjmm.equals(oldZzjg.getZzjgEjmm()))
		{
			return new ModelAndView("user/fail");
		} else
		{
			zzjg.setZzjgEjmm(newZzjgEjmm);
			zzjgService.updateByPrimaryKeySelective(zzjg);
			Zzjg zzig1 = (Zzjg) session.getAttribute("loginSession");
			zzig1.setZzjgEjmm(zzjg.getZzjgEjmm());
			session.setAttribute("loginSession", zzig1);
			return new ModelAndView("success", "uri", "updZzjgEjmm");
		}
	}
}

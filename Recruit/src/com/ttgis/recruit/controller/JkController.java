package com.ttgis.recruit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttgis.recruit.domain.Jk;
import com.ttgis.recruit.domain.JkglFxtWithBLOBs;
import com.ttgis.recruit.domain.JkglWithBLOBs;
import com.ttgis.recruit.domain.QueryJk;
import com.ttgis.recruit.domain.Tkgl;
import com.ttgis.recruit.domain.tkglFxt;
import com.ttgis.recruit.service.JkService;
import com.ttgis.recruit.service.TkglFxtService;
import com.ttgis.recruit.service.TkglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。JkController
 * @创建人： 范井龙
 * @日期：
 * @描述： 卷库管理
 * @版本号：
 */

@Controller
public class JkController
{
	@Autowired
	private JkService jkService;
	@Resource
	private TkglService tkglService;
	@Resource
	private TkglFxtService tkglFxtService;

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

	@RequestMapping(value = "/Jkgl", method = RequestMethod.GET)
	public String Jkgl()
	{
		return "Jkgl/Jkgl";
	}

	@RequestMapping(value = "/ejmmSession", method = RequestMethod.GET)
	public String ejmmSession(HttpSession session, String ejmm)
	{
		logInfo("ejmmSession", ejmm);
		session.setAttribute("ejmm", ejmm);
		return "Jkgl/Jkgl";
	}

	@RequestMapping(value = "/zhmmSession", method = RequestMethod.GET)
	public String zhmmSession(HttpSession session, String zhmm)
	{
		logInfo("zhmmSession", zhmm);
		session.setAttribute("zhmm", zhmm);
		return "Tkgl/Tkgl";
	}

	@RequestMapping(value = "/zhmmfxtSession", method = RequestMethod.GET)
	public String zhmmfxtSession(HttpSession session, String zhmmfxt)
	{
		logInfo("zhmmfxtSession", zhmmfxt);
		session.setAttribute("zhmmfxt", zhmmfxt);
		return "Tkgl/TkglFxt";
	}

	@ResponseBody
	@RequestMapping(value = "/JKData", method = RequestMethod.POST)
	public List<Jk> JKData(QueryJk qj)
	{
		logInfo("JKData", JSONArray.fromObject(qj).toString());
		List<Jk> jkList = jkService.selectByWhere(qj);
		return jkList;
	}

	@ResponseBody
	@RequestMapping(value = "/JKDataCount", method = RequestMethod.POST)
	public int JKDataCount(QueryJk qj)
	{
		logInfo("JKDataCount", JSONArray.fromObject(qj).toString());
		int intCount = jkService.selectCount(qj);
		return intCount;
	}

	@RequestMapping(value = "/JkAdd", method = RequestMethod.GET)
	public String JkAdd(String jkId, Model model)
	{
		logInfo("JkAdd", jkId);
		if (jkId == null)
		{
			return "Jkgl/JkglAdd";
		} else
		{
			Jk jk = jkService.selectByPrimaryKey(jkId);
			model.addAttribute("jk", jk);
			return "Jkgl/JkglAdd";
		}
	}

	@RequestMapping(value = "/SaveJk", method = RequestMethod.POST)
	public String SaveJk(Jk Jk)
	{
		logInfo("SaveJk", JSONArray.fromObject(Jk).toString());
		if (Jk.getJkId().equals(""))
		{
			Jk.setJkAddtime(new Date());
			Jk.setJkId(RandomGUIDUtil.generateKey());
			jkService.insertSelective(Jk);
			return "Jkgl/Jkgl";
		} else
		{
			Jk.setJkAddtime(new Date());
			jkService.updateByPrimaryKeySelective(Jk);
			return "Jkgl/Jkgl";
		}
	}

	@RequestMapping(value = "/delJk", method = RequestMethod.GET)
	public String deleteByPrimaryKey(String jkId)
	{
		logInfo("delJk", jkId);
		jkService.deleteByJkId(jkId);
		jkService.deleteJkglFxtByJkId(jkId);
		jkService.deleteByPrimaryKey(jkId);
		return "Jkgl/Jkgl";
	}

	// ===================生成试卷及查看=============================
	public List<Tkgl> getTk(String strTx, int intTs)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tkglNddj", strTx);
		params.put("ts", intTs);
		return tkglService.querySj(params);
	}

	@RequestMapping(value = "/scsjJkgl", method = RequestMethod.GET)
	public String scsjJkgl(JkglWithBLOBs jkgl, String jkId, Model model)
	{
		logInfo("scsjJkgl", JSONArray.fromObject(jkgl).toString() + ",------" + jkId);
		int countSjbh = jkService.queryJkglSJbh(jkId);
		if (countSjbh > 0)
		{
			model.addAttribute("uri", "jkglSCwb");
			return "success";
		} else
		{
			List<Tkgl> tkglList1 = getTk("A", 10);
			for (Tkgl tkgl : tkglList1)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList2 = getTk("D", 3);
			for (Tkgl tkgl : tkglList2)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList3 = getTk("E", 3);
			for (Tkgl tkgl : tkglList3)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList4 = getTk("F", 4);
			for (Tkgl tkgl : tkglList4)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList5 = getTk("I", 5);
			for (Tkgl tkgl : tkglList5)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList6 = getTk("H", 10);
			for (Tkgl tkgl : tkglList6)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglList7 = getTk("G", 2);
			List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
			for (Tkgl tkgl : tkglList7)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
				for (tkglFxt tkglFxt : zlfxListFxt)
				{
					if (tkgl.getTkglStbh().endsWith(tkglFxt.getStbh()))
					{
						JkglFxtWithBLOBs jkglFxt = new JkglFxtWithBLOBs();
						jkglFxt.setSttg(tkglFxt.getSttg());
						jkglFxt.setId(RandomGUIDUtil.generateKey());
						jkglFxt.setStbh(tkglFxt.getStbh());
						jkglFxt.setStlx(tkglFxt.getStlx());
						jkglFxt.setSjbh(jkId);
						jkglFxt.setStxxa(tkglFxt.getStxxa());
						jkglFxt.setStxxb(tkglFxt.getStxxb());
						jkglFxt.setStxxc(tkglFxt.getStxxc());
						jkglFxt.setStxxd(tkglFxt.getStxxd());
						jkglFxt.setStda(tkglFxt.getStda());
						jkService.insertSelective(jkglFxt);
					}
				}
			}
			// 英语试题生成
			List<Tkgl> tkglEngList1 = getTk("J", 5);
			for (Tkgl tkgl : tkglEngList1)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglEngList2 = getTk("K", 10);
			for (Tkgl tkgl : tkglEngList2)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStxxa(tkgl.getTkglStxxA());
				jkgl.setJkglStxxb(tkgl.getTkglStxxB());
				jkgl.setJkglStxxc(tkgl.getTkglStxxC());
				jkgl.setJkglStxxd(tkgl.getTkglStxxD());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglEngList3 = getTk("L", 1);
			for (Tkgl tkgl : tkglEngList3)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglStda(tkgl.getTkglStda());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
			}
			List<Tkgl> tkglEngList4 = getTk("M-2", 1);
			List<tkglFxt> engYdljListFxt1 = tkglFxtService.queryTkglFxt("M-2");
			for (Tkgl tkgl : tkglEngList4)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
				for (tkglFxt tkglFxt : engYdljListFxt1)
				{
					if (tkgl.getTkglStbh().endsWith(tkglFxt.getStbh()))
					{
						JkglFxtWithBLOBs jkglFxt = new JkglFxtWithBLOBs();
						jkglFxt.setSttg(tkglFxt.getSttg());
						jkglFxt.setId(RandomGUIDUtil.generateKey());
						jkglFxt.setStbh(tkglFxt.getStbh());
						jkglFxt.setStlx(tkglFxt.getStlx());
						jkglFxt.setSjbh(jkId);
						jkglFxt.setStxxa(tkglFxt.getStxxa());
						jkglFxt.setStxxb(tkglFxt.getStxxb());
						jkglFxt.setStxxc(tkglFxt.getStxxc());
						jkglFxt.setStxxd(tkglFxt.getStxxd());
						jkglFxt.setStda(tkglFxt.getStda());
						jkService.insertSelective(jkglFxt);
					}
				}
			}
			List<Tkgl> tkglEngList5 = getTk("N-A", 1);
			List<tkglFxt> engYydwListFxt2 = tkglFxtService.queryTkglFxt("N-A");
			for (Tkgl tkgl : tkglEngList5)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
				for (tkglFxt tkglFxt : engYydwListFxt2)
				{
					if (tkgl.getTkglStbh().endsWith(tkglFxt.getStbh()))
					{
						JkglFxtWithBLOBs jkglFxt = new JkglFxtWithBLOBs();
						jkglFxt.setSttg(tkglFxt.getSttg());
						jkglFxt.setId(RandomGUIDUtil.generateKey());
						jkglFxt.setStbh(tkglFxt.getStbh());
						jkglFxt.setStlx(tkglFxt.getStlx());
						jkglFxt.setSjbh(jkId);
						jkglFxt.setStxxa(tkglFxt.getStxxa());
						jkglFxt.setStxxb(tkglFxt.getStxxb());
						jkglFxt.setStxxc(tkglFxt.getStxxc());
						jkglFxt.setStxxd(tkglFxt.getStxxd());
						jkglFxt.setStda(tkglFxt.getStda());
						jkService.insertSelective(jkglFxt);
					}
				}
			}
			List<Tkgl> tkglEngList6 = getTk("N-B", 1);
			List<tkglFxt> engYydwListFxt3 = tkglFxtService.queryTkglFxt("N-B");
			for (Tkgl tkgl : tkglEngList6)
			{
				jkgl.setJkglId(RandomGUIDUtil.generateKey());
				jkgl.setJkglStbh(tkgl.getTkglStbh());
				jkgl.setJkglStlx(tkgl.getTkglStlx());
				jkgl.setJkglSttg(tkgl.getTkglSttg());
				jkgl.setJkglSjbh(jkId);
				jkService.insertSelectiveJkgl(jkgl);
				for (tkglFxt tkglFxt : engYydwListFxt3)
				{
					if (tkgl.getTkglStbh().endsWith(tkglFxt.getStbh()))
					{
						JkglFxtWithBLOBs jkglFxt = new JkglFxtWithBLOBs();
						jkglFxt.setSttg(tkglFxt.getSttg());
						jkglFxt.setId(RandomGUIDUtil.generateKey());
						jkglFxt.setStbh(tkglFxt.getStbh());
						jkglFxt.setStlx(tkglFxt.getStlx());
						jkglFxt.setSjbh(jkId);
						jkglFxt.setStxxa(tkglFxt.getStxxa());
						jkglFxt.setStxxb(tkglFxt.getStxxb());
						jkglFxt.setStxxc(tkglFxt.getStxxc());
						jkglFxt.setStxxd(tkglFxt.getStxxd());
						jkglFxt.setStda(tkglFxt.getStda());
						jkService.insertSelective(jkglFxt);
					}
				}
			}
		}
		return "Jkgl/Jkgl";
	}

	public List<JkglWithBLOBs> getJk(String jkglSjbh, String jkglStlx)
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("jkglSjbh", jkglSjbh);
		param.put("jkglStlx", jkglStlx);
		return jkService.queryJkglBySJbh(param);
	}

	@RequestMapping(value = "/JkglView", method = RequestMethod.GET)
	public String JkglView(String jkId, Model model)
	{
		logInfo("JkglView", jkId);
		// ADEFIHG
		List<JkglWithBLOBs> jkglListA = getJk(jkId, "A");
		List<JkglWithBLOBs> jkglListD = getJk(jkId, "D");
		List<JkglWithBLOBs> jkglListE = getJk(jkId, "E");
		List<JkglWithBLOBs> jkglListF = getJk(jkId, "F");
		List<JkglWithBLOBs> jkglListI = getJk(jkId, "I");
		List<JkglWithBLOBs> jkglListH = getJk(jkId, "H");
		List<JkglWithBLOBs> jkglListG = getJk(jkId, "G");
		Map<String, String> param = new HashMap<String, String>();
		param.put("sjbh", jkId);
		param.put("stlx", "G");
		List<JkglFxtWithBLOBs> jkglFxtList = jkService.queryJkglFxt(param);
		model.addAttribute("jkglListA", jkglListA);
		model.addAttribute("jkglListD", jkglListD);
		model.addAttribute("jkglListE", jkglListE);
		model.addAttribute("jkglListF", jkglListF);
		model.addAttribute("jkglListI", jkglListI);
		model.addAttribute("jkglListH", jkglListH);
		model.addAttribute("jkglListG", jkglListG);
		model.addAttribute("jkglFxtList", jkglFxtList);
		model.addAttribute("jkId", jkId);
		if (jkglListA.size() <= 0 || jkglListD.size() <= 0 || jkglListE.size() <= 0 || jkglListF.size() <= 0 || jkglListI.size() <= 0 || jkglListH.size() <= 0 || jkglListG.size() <= 0)
		{
			return "Jkgl/JkglNull";
		}
		return "Jkgl/JkglView";
	}

	//
	@RequestMapping(value = "/JkglEngView", method = RequestMethod.GET)
	public String JkglEngView(String jkId, Model model)
	{
		logInfo("JkglEngView", jkId);
		// JKLM-2 N-A N-B
		List<JkglWithBLOBs> jkglListJ = getJk(jkId, "J");
		List<JkglWithBLOBs> jkglListK = getJk(jkId, "K");
		List<JkglWithBLOBs> jkglListL = getJk(jkId, "L");

		List<JkglWithBLOBs> jkglListM = getJk(jkId, "M-2");
		Map<String, String> paramM = new HashMap<String, String>();
		paramM.put("sjbh", jkId);
		paramM.put("stlx", "M-2");
		List<JkglFxtWithBLOBs> jkglFxtListM = jkService.queryJkglFxt(paramM);
		List<JkglWithBLOBs> jkglListNA = getJk(jkId, "N-A");
		Map<String, String> paramNA = new HashMap<String, String>();
		paramNA.put("sjbh", jkId);
		paramNA.put("stlx", "N-A");
		List<JkglFxtWithBLOBs> jkglFxtListNA = jkService.queryJkglFxt(paramNA);
		List<JkglWithBLOBs> jkglListNB = getJk(jkId, "N-B");
		Map<String, String> paramNB = new HashMap<String, String>();
		paramNB.put("sjbh", jkId);
		paramNB.put("stlx", "N-B");
		List<JkglFxtWithBLOBs> jkglFxtListNB = jkService.queryJkglFxt(paramNB);
		model.addAttribute("jkglListJ", jkglListJ);
		model.addAttribute("jkglListK", jkglListK);
		model.addAttribute("jkglListL", jkglListL);
		model.addAttribute("jkglListM", jkglListM);
		model.addAttribute("jkglFxtListM", jkglFxtListM);
		model.addAttribute("jkglListNA", jkglListNA);
		model.addAttribute("jkglFxtListNA", jkglFxtListNA);
		model.addAttribute("jkglListNB", jkglListNB);
		model.addAttribute("jkglFxtListNB", jkglFxtListNB);
		model.addAttribute("jkId", jkId);
		if (jkglListJ.size() <= 0 || jkglListK.size() <= 0 || jkglListL.size() <= 0 || jkglListM.size() <= 0 || jkglFxtListM.size() <= 0 || jkglListNA.size() <= 0 || jkglFxtListNA.size() <= 0 || jkglListNB.size() <= 0 || jkglFxtListNB.size() <= 0)
		{
			return "Jkgl/JkglNull";
		}
		return "Jkgl/JkglEngView";
	}

	// ===================================生成PDF=======================================
	@RequestMapping(value = "/JkglViewImage", method = RequestMethod.GET)
	public ModelAndView Image(String jkId, HttpServletRequest request, HttpServletResponse response)
	{
		logInfo("JkglViewImage", jkId);
		List<JkglWithBLOBs> jkglListA = getJk(jkId, "A");
		List<JkglWithBLOBs> jkglListD = getJk(jkId, "D");
		List<JkglWithBLOBs> jkglListE = getJk(jkId, "E");
		List<JkglWithBLOBs> jkglListF = getJk(jkId, "F");
		List<JkglWithBLOBs> jkglListI = getJk(jkId, "I");
		List<JkglWithBLOBs> jkglListH = getJk(jkId, "H");
		List<JkglWithBLOBs> jkglListG = getJk(jkId, "G");
		Map<String, String> param = new HashMap<String, String>();
		param.put("sjbh", jkId);
		param.put("stlx", "G");
		List<JkglFxtWithBLOBs> jkglFxtList = jkService.queryJkglFxt(param);
		File file = new File(request.getSession().getServletContext().getRealPath("pdf"));
		String path = new java.util.Date().getTime() + "Jkgl.pdf";
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
					logInfo("JkglViewImage", jkId + "******Error");
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
			Paragraph titleP = new Paragraph("高校毕业生招聘考试试题 ——职业基本能力", titleFont);
			titleP.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(titleP);
			// 内容字体
			BaseFont bfComic = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			doc.add(new Paragraph("答题说明:", font));
			doc.add(new Paragraph("1. 测试时间为50分钟，总分75分。", font));
			doc.add(new Paragraph("2. 将答案填入答题卡", font));
			doc.add(new Paragraph("一、选择题(共10题)", font));
			doc.add(new Paragraph("言语理解与表达 每题1.5分", font));
			int a = 1;
			for (JkglWithBLOBs jkgl : jkglListA)
			{
				doc.add(new Paragraph((a++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			// ADEFIHG
			doc.add(new Paragraph("二、判断推理(共10题)", font));
			doc.add(new Paragraph("判断推理 每题1.5分", font));
			doc.add(new Paragraph("(1)、定义推理(共3题)", font));
			int d = 1;
			for (JkglWithBLOBs jkgl : jkglListD)
			{
				doc.add(new Paragraph((d++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.add(new Paragraph("(2)、类比推理(共3题)", font));
			int e = 1;
			for (JkglWithBLOBs jkgl : jkglListE)
			{
				doc.add(new Paragraph((e++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.add(new Paragraph("(3)、逻辑推理(共4题)", font));
			int f = 1;
			for (JkglWithBLOBs jkgl : jkglListF)
			{
				doc.add(new Paragraph((f++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.add(new Paragraph("三、资料分析(共2大题，10小题)", font));
			doc.add(new Paragraph("资料分析 每题3分", font));
			int g = 1;
			int gg = 1;
			for (JkglWithBLOBs jkgl : jkglListG)
			{
				doc.add(new Paragraph((g++) + "、" + jkgl.getJkglSttg(), font));
				for (JkglFxtWithBLOBs jkglFxt : jkglFxtList)
				{
					if (jkgl.getJkglStlx().equals(jkglFxt.getStlx()) && jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
					{
						doc.add(new Paragraph("(" + (gg++) + ")、" + jkglFxt.getSttg() + "(" + jkglFxt.getStda() + ")", font));
						doc.add(new Paragraph("A、" + jkglFxt.getStxxa(), font));
						doc.add(new Paragraph("B、" + jkglFxt.getStxxb(), font));
						doc.add(new Paragraph("C、" + jkglFxt.getStxxc(), font));
						doc.add(new Paragraph("D、" + jkglFxt.getStxxd(), font));
					}
				}
			}
			doc.add(new Paragraph("四、Office及一般管理(共15小题)", font));
			doc.add(new Paragraph("Office及一般管理 每题1分", font));
			doc.add(new Paragraph("(1)、一般管理(共5题)", font));
			int i = 1;
			for (JkglWithBLOBs jkgl : jkglListI)
			{
				doc.add(new Paragraph((i++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.add(new Paragraph("(2)、office题(共10题)", font));
			int h = 1;
			for (JkglWithBLOBs jkgl : jkglListH)
			{
				doc.add(new Paragraph((h++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.close();
		} catch (Exception e)
		{
			logInfo("JkglViewImage", jkId + "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("pdf/jkglPdf", "uri", "pdf/" + path);
	}

	@RequestMapping(value = "/JkglEngViewImage", method = RequestMethod.GET)
	public ModelAndView JkglEngViewImage(String jkId, HttpServletRequest request, HttpServletResponse response)
	{
		logInfo("JkglEngViewImage", jkId);
		File file = new File(request.getSession().getServletContext().getRealPath("pdf"));
		String path = new java.util.Date().getTime() + "Jkgl.pdf";
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
					logInfo("JkglEngViewImage", jkId + "******Error");
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
			Paragraph titleP = new Paragraph("高校毕业生招聘英语考试", titleFont);
			titleP.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(titleP);
			// 内容字体
			BaseFont bfComic = BaseFont.createFont(request.getSession().getServletContext().getRealPath("pdfFonts") + "\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			// 抽取英语试题
			List<JkglWithBLOBs> jkglListJ = getJk(jkId, "J");
			List<JkglWithBLOBs> jkglListK = getJk(jkId, "K");
			List<JkglWithBLOBs> jkglListL = getJk(jkId, "L");

			List<JkglWithBLOBs> jkglListM = getJk(jkId, "M-2");
			Map<String, String> paramM = new HashMap<String, String>();
			paramM.put("sjbh", jkId);
			paramM.put("stlx", "M-2");
			List<JkglFxtWithBLOBs> jkglFxtListM = jkService.queryJkglFxt(paramM);
			List<JkglWithBLOBs> jkglListNA = getJk(jkId, "N-A");
			Map<String, String> paramNA = new HashMap<String, String>();
			paramNA.put("sjbh", jkId);
			paramNA.put("stlx", "N-A");
			List<JkglFxtWithBLOBs> jkglFxtListNA = jkService.queryJkglFxt(paramNA);
			List<JkglWithBLOBs> jkglListNB = getJk(jkId, "N-B");
			Map<String, String> paramNB = new HashMap<String, String>();
			paramNB.put("sjbh", jkId);
			paramNB.put("stlx", "N-B");
			List<JkglFxtWithBLOBs> jkglFxtListNB = jkService.queryJkglFxt(paramNB);
			doc.add(new Paragraph("第一部分 语言知识及应用", font));
			doc.add(new Paragraph("第一节 情景应用", font));
			doc.add(new Paragraph("阅读1-5问题框，选择正确的一个。", font));
			int a = 1;
			for (JkglWithBLOBs jkgl : jkglListJ)
			{
				doc.add(new Paragraph((a++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
			}
			// ADEFIHG
			doc.add(new Paragraph("第二节 商务选择", font));
			doc.add(new Paragraph("选择你认为正确的答案。", font));
			int d = 1;
			for (JkglWithBLOBs jkgl : jkglListK)
			{
				doc.add(new Paragraph((d++) + "、" + jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
				doc.add(new Paragraph("A、" + jkgl.getJkglStxxa(), font));
				doc.add(new Paragraph("B、" + jkgl.getJkglStxxb(), font));
				doc.add(new Paragraph("C、" + jkgl.getJkglStxxc(), font));
				doc.add(new Paragraph("D、" + jkgl.getJkglStxxd(), font));
			}
			doc.add(new Paragraph("第二部分 阅读", font));
			doc.add(new Paragraph("第一节 信息匹配", font));
			doc.add(new Paragraph("填写你认为正确的答案。", font));
			for (JkglWithBLOBs jkgl : jkglListL)
			{
				doc.add(new Paragraph(jkgl.getJkglSttg() + "(" + jkgl.getJkglStda() + ")", font));
			}
			doc.add(new Paragraph("第二节 阅读理解", font));
			doc.add(new Paragraph("请根据文章的内容，从题所给的4个选项中选择1个最佳答案。", font));
			int g = 1;
			int gg = 1;
			for (JkglWithBLOBs jkgl : jkglListM)
			{
				doc.add(new Paragraph((g++) + "、" + jkgl.getJkglSttg(), font));
				for (JkglFxtWithBLOBs jkglFxt : jkglFxtListM)
				{
					if (jkgl.getJkglStlx().equals(jkglFxt.getStlx()) && jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
					{
						doc.add(new Paragraph("(" + (gg++) + ")、" + jkglFxt.getSttg() + "(" + jkglFxt.getStda() + ")", font));
						doc.add(new Paragraph("A、" + jkglFxt.getStxxa(), font));
						doc.add(new Paragraph("B、" + jkglFxt.getStxxb(), font));
						doc.add(new Paragraph("C、" + jkglFxt.getStxxc(), font));
						doc.add(new Paragraph("D、" + jkglFxt.getStxxd(), font));
					}
				}
			}
			doc.add(new Paragraph("第三节 应用短文阅读", font));
			doc.add(new Paragraph("请根据文章的内容，从题所给的4个选项中选择1个最佳答案。", font));
			int NA = 1;
			int nNA = 1;
			for (JkglWithBLOBs jkgl : jkglListNA)
			{
				doc.add(new Paragraph((NA++) + "、" + jkgl.getJkglSttg(), font));
				for (JkglFxtWithBLOBs jkglFxt : jkglFxtListNA)
				{
					if (jkgl.getJkglStlx().equals(jkglFxt.getStlx()) && jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
					{
						doc.add(new Paragraph("(" + (nNA++) + ")、" + jkglFxt.getSttg() + "(" + jkglFxt.getStda() + ")", font));
						doc.add(new Paragraph("A、" + jkglFxt.getStxxa(), font));
						doc.add(new Paragraph("B、" + jkglFxt.getStxxb(), font));
						doc.add(new Paragraph("C、" + jkglFxt.getStxxc(), font));
						doc.add(new Paragraph("D、" + jkglFxt.getStxxd(), font));
					}
				}
			}
			int NB = 1;
			int nNB = 1;
			for (JkglWithBLOBs jkgl : jkglListNB)
			{
				doc.add(new Paragraph((NB++) + "、" + jkgl.getJkglSttg(), font));
				for (JkglFxtWithBLOBs jkglFxt : jkglFxtListNB)
				{
					if (jkgl.getJkglStlx().equals(jkglFxt.getStlx()) && jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
					{
						doc.add(new Paragraph("(" + (nNB++) + ")、" + jkglFxt.getSttg() + "(" + jkglFxt.getStda() + ")", font));
						doc.add(new Paragraph("A、" + jkglFxt.getStxxa(), font));
						doc.add(new Paragraph("B、" + jkglFxt.getStxxb(), font));
						doc.add(new Paragraph("C、" + jkglFxt.getStxxc(), font));
						doc.add(new Paragraph("D、" + jkglFxt.getStxxd(), font));
					}
				}
			}
			doc.close();
		} catch (Exception e)
		{
			logInfo("JkglEngViewImage", jkId + "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("pdf/jkglEngPdf", "uri", "pdf/" + path);
	}
}

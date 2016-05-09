package com.ttgis.recruit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.Cjtsgl;
import com.ttgis.recruit.domain.Gxcs;
import com.ttgis.recruit.domain.Hdlst;
import com.ttgis.recruit.domain.JkglFxtWithBLOBs;
import com.ttgis.recruit.domain.JkglWithBLOBs;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.QueryTkgl;
import com.ttgis.recruit.domain.Tkgl;
import com.ttgis.recruit.domain.XmlEntity;
import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.domain.tkglFxt;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.CjtsglService;
import com.ttgis.recruit.service.JkService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.KsxcglService;
import com.ttgis.recruit.service.TkglFxtService;
import com.ttgis.recruit.service.TkglService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.SendMessage;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。TkglController
 * @创建人：范井龙
 * @日期：
 * @描述：题库管理
 * @版本号：
 */
@Controller
public class TkglController
{
	public static String ToDBC(String strSttg)
	{
		strSttg = strSttg.replaceAll(" ", " ");
		strSttg = strSttg.replaceAll("  ", "  ");
		strSttg = strSttg.replaceAll("   ", "   ");
		strSttg = strSttg.replaceAll("    ", "    ");
		strSttg = strSttg.replaceAll("     ", "     ");
		strSttg = strSttg.replaceAll("      ", "     ");
		strSttg = strSttg.replaceAll("       ", "      ");
		strSttg = strSttg.replaceAll("        ", "       ");
		strSttg = strSttg.replaceAll("         ", "       ");
		strSttg = strSttg.replaceAll("          ", "       ");
		return strSttg;
	}

	@Resource
	private TkglService tkglService;
	@Resource
	private TkglFxtService tkglFxtService;
	@Resource
	private KsxcglService ksxcglService;
	@Resource
	private JkService jkService;
	@Resource
	private KcglService kcglService;
	@Resource
	private ZzjgService zzjgService;
	@Resource
	private JtjlkService jtjlkService;
	@Resource
	private XxtzService xxtzService;
	@Autowired
	private CjtsglService cjtsglService;
	@Autowired
	private BmglService bmglService;

	static Logger log = Logger.getLogger(TkglController.class);
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
		try
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
			if (session.getAttribute("cpglLoginSession1") != null)
				stringBuilder.append(((Bmgl) session.getAttribute("cpglLoginSession1")).getBmglSfzh());
			if (session.getAttribute("cpglLoginSession2") != null)
				stringBuilder.append(((Bmgl) session.getAttribute("cpglLoginSession2")).getBmglSfzh());
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
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	/**
	 * 以下是批量导入试题信息 请勿轻易删除
	 * 
	 * @return
	 */
	// ------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/pldrTkgl", method = RequestMethod.GET)
	public ModelAndView pldrTkgl()
	{
		return new ModelAndView("Tkgl/pldrTkgl");
	}

	@RequestMapping(value = "/pldrTkglThree", method = RequestMethod.GET)
	public ModelAndView pldrTkglThree()
	{
		return new ModelAndView("Tkgl/pldrTkglThree");
	}

	@RequestMapping(value = "/pldrTkglOne", method = RequestMethod.GET)
	public ModelAndView pldrTkglOne()
	{
		return new ModelAndView("Tkgl/pldrTkglOne");
	}

	@RequestMapping(value = "/pldrTkglZero", method = RequestMethod.GET)
	public ModelAndView pldrTkglZero()
	{
		return new ModelAndView("Tkgl/pldrTkglZero");
	}

	/**
	 * 
	 * 四个选项试题导入
	 */
	@RequestMapping(value = "/importTkgl", method = RequestMethod.POST)
	// 设置访问路径
	public ModelAndView importTkgl(
	// 模型参数 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{
		logInfo("importTkgl", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				Tkgl tkgl = new Tkgl();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				tkgl.setTkglId(RandomGUIDUtil.generateKey());
				tkgl.setTkglAddtime(new Date());
				tkgl.setTkglDelflag((long) 1);
				tkgl.setTkglStbh(row.getCell(0) + ""); // 试题编号
				tkgl.setTkglStlx(row.getCell(1) + ""); // 试题类型
				String strSttg = ToDBC(row.getCell(2).toString());
				String strStxxa = ToDBC(row.getCell(3).toString());
				String strStxxb = ToDBC(row.getCell(4).toString());
				String strStxxc = ToDBC(row.getCell(5).toString());
				String strStxxd = ToDBC(row.getCell(6).toString());
				tkgl.setTkglSttg(strSttg); // 试题题干
				tkgl.setTkglStxxA(strStxxa); // 答案 A
				tkgl.setTkglStxxB(strStxxb); // 答案 B
				tkgl.setTkglStxxC(strStxxc); // 答案 C
				tkgl.setTkglStxxD(strStxxd); // 答案 D
				tkgl.setTkglStda(row.getCell(7) + ""); // 正确答案
				tkglService.insertkgl(tkgl);
			}
		} catch (IOException e)
		{
			logInfo("importTkgl", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("success", "uri", "importTkgl");
	}

	/**
	 * 三个选项试题导入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/importTkglThree", method = RequestMethod.POST)
	// 设置访问路径
	public ModelAndView importTkglThree(
	// 模型参数 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{
		logInfo("importTkglThree", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				Tkgl tkgl = new Tkgl();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				tkgl.setTkglId(RandomGUIDUtil.generateKey());
				tkgl.setTkglAddtime(new Date());
				tkgl.setTkglDelflag((long) 1);
				tkgl.setTkglStbh(row.getCell(0) + ""); // 试题编号
				tkgl.setTkglStlx(row.getCell(1) + ""); // 试题类型
				String strSttg = ToDBC(row.getCell(2).toString());
				String strStxxa = ToDBC(row.getCell(3).toString());
				String strStxxb = ToDBC(row.getCell(4).toString());
				String strStxxc = ToDBC(row.getCell(5).toString());
				tkgl.setTkglSttg(strSttg); // 试题题干
				tkgl.setTkglStxxA(strStxxa); // 答案 A
				tkgl.setTkglStxxB(strStxxb); // 答案 B
				tkgl.setTkglStxxC(strStxxc); // 答案 C
				tkgl.setTkglStda(row.getCell(6) + ""); // 正确答案
				tkglService.insertkgl(tkgl);
			}
		} catch (IOException e)
		{
			logInfo("importTkglThree", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("success", "uri", "importTkglThree");
	}

	/**
	 * 一个选项试题导入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/importTkglOne", method = RequestMethod.POST)
	// 设置访问路径
	public ModelAndView importTkglOne(
	// 模型参数 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{
		logInfo("importTkglOne", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				Tkgl tkgl = new Tkgl();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				tkgl.setTkglId(RandomGUIDUtil.generateKey());
				tkgl.setTkglAddtime(new Date());
				tkgl.setTkglDelflag((long) 1);
				tkgl.setTkglStbh(row.getCell(0) + ""); // 试题编号
				tkgl.setTkglStlx(row.getCell(1) + ""); // 试题类型
				String strSttg = ToDBC(row.getCell(2).toString());
				tkgl.setTkglSttg(strSttg); // 试题题干
				tkgl.setTkglStxxA(ToDBC(row.getCell(3).toString())); // 图形推理
				tkgl.setTkglStda(row.getCell(4) + ""); // 正确答案
				tkglService.insertkgl(tkgl);
			}
		} catch (IOException e)
		{
			logInfo("importTkglOne", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("success", "uri", "importTkglOne");
	}

	/**
	 * 零选项试题导入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/importTkglZero", method = RequestMethod.POST)
	// 设置访问路径
	public ModelAndView importTkglZero(
	// 模型参数 提供返回信息等方法
			@RequestParam("excel") MultipartFile excel // 文件流获取参数
														// @RequestParam("excel")中
														// 参数与页面form
														// 文件上传标签name对应
	)
	{
		logInfo("importTkglZero", "");
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			while (rows.hasNext())
			{ // 迭代Excel 行
				Tkgl tkgl = new Tkgl();// 创建实体接收数据
				Row row = (Row) rows.next(); // 获取一行数据
				tkgl.setTkglId(RandomGUIDUtil.generateKey());
				tkgl.setTkglAddtime(new Date());
				tkgl.setTkglDelflag((long) 1);
				tkgl.setTkglStbh(row.getCell(0) + ""); // 试题编号
				tkgl.setTkglStlx(row.getCell(1) + ""); // 试题类型
				String strSttg = ToDBC(row.getCell(2).toString());
				tkgl.setTkglSttg(strSttg); // 试题题干
				tkglService.insertkgl(tkgl);
			}
		} catch (IOException e)
		{
			logInfo("importTkglZero", "******Error");
			e.printStackTrace();
		}
		return new ModelAndView("success", "uri", "importTkglZero");
	}

	// ------------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/Tkgl", method = RequestMethod.GET)
	public ModelAndView Tkgl()
	{
		return new ModelAndView("Tkgl/Tkgl");
	}

	@RequestMapping(value = "/Cpksgl", method = RequestMethod.GET)
	public ModelAndView Cpksgl()
	{
		return new ModelAndView("Cpks/Cpksgl");
	}

	@RequestMapping(value = "/Gxcs", method = RequestMethod.GET)
	public ModelAndView Gxcs()
	{
		return new ModelAndView("Tkgl/gxcs");
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
	@RequestMapping(value = "/LoadTkglData", method = RequestMethod.POST)
	public List<Tkgl> LoadTkglData(QueryTkgl qt)
	{
		logInfo("LoadTkglData", JSONArray.fromObject(qt).toString());
		List<Tkgl> tkgl = tkglService.selectByWhere(qt);
		return tkgl;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadTkglDataCount", method = RequestMethod.POST)
	public int LoadTkglDataCount(QueryTkgl qt)
	{
		logInfo("LoadTkglDataCount", JSONArray.fromObject(qt).toString());
		int intCount = tkglService.selectCount(qt);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/TkglAdd", method = RequestMethod.GET)
	public String AddTkgl(String tkglId, Model model)
	{
		logInfo("TkglAdd", tkglId);
		if (tkglId == null)
		{
			return "Tkgl/TkglAdd";
		} else
		{
			Tkgl tkgl = new Tkgl();
			tkgl = tkglService.selectByPrimaryKey(tkglId);
			model.addAttribute("tkgl", tkgl);
			return "Tkgl/TkglAdd";
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/TkglSava", method = RequestMethod.POST)
	public ModelAndView TkglSava(Tkgl tkgl)
	{
		logInfo("TkglSava", JSONArray.fromObject(tkgl).toString());
		System.out.println(tkgl.getTkglId());
		if (tkgl.getTkglId().equals(""))
		{

			tkgl.setTkglDelflag((long) 1);
			tkgl.setTkglAddtime(new Date());
			tkgl.setTkglId(RandomGUIDUtil.generateKey());
			tkglService.insertkgl(tkgl);

		} else
		{
			tkglService.updateByPrimaryKeySelective(tkgl);
		}
		return new ModelAndView("redirect:/Tkgl");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/delTkgl", method = RequestMethod.GET)
	public String delTkgl(String tkglId)
	{
		logInfo("delTkgl", tkglId);
		tkglService.deleteByPrimaryKey(tkglId);
		return "Tkgl/Tkgl";
	}

	public List<Tkgl> getTk(String strTx, int intTs)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tkglNddj", strTx);
		params.put("ts", intTs);
		return tkglService.querySj(params);
	}

	/**
	 * 生成-管理岗-汉语-试题
	 * 
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/querySj", method = RequestMethod.GET)
	public String querySj(HttpSession session)
	{
		logInfo("querySj", "");
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");

		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", bmgl.getBmglBkgw());
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);

		if (ksxcglList.size() > 0)
		{
			if (ksxcglList.get(0).getKsxcglTszg().equals("是"))
			{
				List<JkglWithBLOBs> jkglListA = jkService.queryRandomJk("A");
				List<JkglWithBLOBs> jkglListD = jkService.queryRandomJk("D");
				List<JkglWithBLOBs> jkglListE = jkService.queryRandomJk("E");
				List<JkglWithBLOBs> jkglListF = jkService.queryRandomJk("F");
				List<JkglWithBLOBs> jkglListI = jkService.queryRandomJk("I");
				List<JkglWithBLOBs> jkglListH = jkService.queryRandomJk("H");
				List<JkglWithBLOBs> jkglListG = jkService.queryRandomJk("G");
				List<JkglFxtWithBLOBs> jkglFxtList = jkService.jkqueryJkglFxt("G");
				session.setAttribute("jkglListA", jkglListA);
				session.setAttribute("jkglListD", jkglListD);
				session.setAttribute("jkglListE", jkglListE);
				session.setAttribute("jkglListF", jkglListF);
				session.setAttribute("jkglListI", jkglListI);
				session.setAttribute("jkglListH", jkglListH);
				session.setAttribute("jkglListG", jkglListG);
				session.setAttribute("jkglFxtList", jkglFxtList);
				return "Tkgl/glgTszgView";
			} else
			{
				Object o = session.getAttribute("tkglList");
				List<Tkgl> tkglList = new ArrayList<Tkgl>();
				Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
				if (o == null)
				{
					tkglList = getTk("A", 10);
					mv.put("lx1", tkglList);
					tkglList = getTk("D", 3);
					mv.put("lx2", tkglList);
					tkglList = getTk("E", 3);
					mv.put("lx3", tkglList);
					tkglList = getTk("F", 4);
					mv.put("lx4", tkglList);
					tkglList = getTk("I", 5);
					mv.put("lx5", tkglList);
					tkglList = getTk("H", 10);
					mv.put("lx6", tkglList);
					tkglList = getTk("G", 2);
					mv.put("lx7", tkglList);
					List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
					session.setAttribute("zlfxListFxt", zlfxListFxt);
					session.setAttribute("tkglList", mv);
				} else
				{
					List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
					session.setAttribute("zlfxListFxt", zlfxListFxt);
					mv = (Map<String, List<Tkgl>>) session.getAttribute("tkglList");
				}
				return "Tkgl/tkglView";
			}
		} else
		{
			Object o = session.getAttribute("tkglList");
			List<Tkgl> tkglList = new ArrayList<Tkgl>();
			Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
			if (o == null)
			{
				tkglList = getTk("A", 10);
				mv.put("lx1", tkglList);
				tkglList = getTk("D", 3);
				mv.put("lx2", tkglList);
				tkglList = getTk("E", 3);
				mv.put("lx3", tkglList);
				tkglList = getTk("F", 4);
				mv.put("lx4", tkglList);
				tkglList = getTk("I", 5);
				mv.put("lx5", tkglList);
				tkglList = getTk("H", 10);
				mv.put("lx6", tkglList);
				tkglList = getTk("G", 2);
				mv.put("lx7", tkglList);
				List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
				session.setAttribute("zlfxListFxt", zlfxListFxt);
				session.setAttribute("tkglList", mv);
				List<Tkgl> tkglListlx1 = mv.get("lx1");
				for (Tkgl tkgl : tkglListlx1)
				{
					System.out.println("A-言语表达与理解-1691:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx2 = mv.get("lx2");
				for (Tkgl tkgl : tkglListlx2)
				{
					System.out.println("D-定义推理-539:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx3 = mv.get("lx3");
				for (Tkgl tkgl : tkglListlx3)
				{
					System.out.println("E-类比推理-350:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx4 = mv.get("lx4");
				for (Tkgl tkgl : tkglListlx4)
				{
					System.out.println("F-逻辑推理-626:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx5 = mv.get("lx5");
				for (Tkgl tkgl : tkglListlx5)
				{
					System.out.println("I-一般管理试题-123:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx6 = mv.get("lx6");
				for (Tkgl tkgl : tkglListlx6)
				{
					System.out.println("H-OFFICE试题-300:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx7 = mv.get("lx7");
				for (Tkgl tkgl : tkglListlx7)
				{
					System.out.println("G-资料分析-240:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				for (tkglFxt tkglFxt : zlfxListFxt)
				{
					System.out.println("G-资料分析-240 小题:试题编号：" + tkglFxt.getStbh() + ";题干:" + tkglFxt.getSttg() + ";正确答案:" + tkglFxt.getStda());
				}
			} else
			{
				List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
				session.setAttribute("zlfxListFxt", zlfxListFxt);
				mv = (Map<String, List<Tkgl>>) session.getAttribute("tkglList");
			}
			return "Tkgl/tkglView";
		}

	}

	/**
	 * 提交-管理岗-汉语-成绩
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/TestSubmit", method = RequestMethod.POST)
	public String TestSubmit(HttpServletRequest request, HttpSession session)
	{
		logInfo("TestSubmit", "");
		double dblFsCount = 0;
		double dblFs = 0;
		Map<String, List<Tkgl>> mv = (Map<String, List<Tkgl>>) session.getAttribute("tkglList");
		List<tkglFxt> zlfxListFxt = (List<tkglFxt>) session.getAttribute("zlfxListFxt");
		for (Map.Entry<String, List<Tkgl>> tkglList : mv.entrySet())
		{
			if (tkglList.getKey().equals("lx1"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("A-言语表达与理解-1691 :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "A-言语表达与理解-1691:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx2"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{

						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("D-定义推理-539:试题编号:选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
							dblFsCount += 0;
						logInfo("testSum", "D-定义推理-539:试题编号:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx3"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("E-类比推理-350:试题编号：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
							dblFsCount += 0;
						logInfo("testSum", "E-类比推理-350:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx4"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("F-逻辑推理-626:试题编号：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "F-逻辑推理-626:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx5"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("I-一般管理试题-123:试题编号：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "I-一般管理试题-123:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx6"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("H-OFFICE试题-300:试题编号：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "H-OFFICE试题-300:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lx7"))
			{
				dblFs = 3;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : zlfxListFxt)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
									System.out.println("G-资料分析-240:试题编号：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "G-资料分析-240:题干试题编号：" + tkgl.getTkglStbh() + "小题编号：" + tkglFxt.getStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}

			} else
			{
				dblFsCount = 0;
			}
		}
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");
		Bmgl bmgl1 = (Bmgl) session.getAttribute("cpglLoginSession2");
		Ksxcgl ksxcgl = new Ksxcgl();
		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", "管理岗");
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "管理岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		for (Zzjg zzjg : zzjgs)
		{
			if (ksxcglList.size() == 0)
			{
				if (bmgl.getBmglBkgw().equals("管理岗"))
				{
					ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
					Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglDelflag((long) 1);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
					ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
					ksxcgl.setKsxcglKscjGlg(dblFsCount);
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
					ksxcgl.setKsxcglKkslx("管理岗");
					ksxcgl.setKcglId(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglKc(kcgl.getKcglName());
					ksxcgl.setKsxcglWjqk("否");
					ksxcgl.setKsxcglTszg("否");
					ksxcglService.insertksxcgl(ksxcgl);
				} else
				{
					if (bmgl1 != null)
					{
						ksxcgl.setKsxcglBmglId(bmgl1.getBmglId());
						Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
						ksxcgl.setKsxcglDelflag((long) 1);
						ksxcgl.setKsxcglAddtime(new Date());
						ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
						ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
						ksxcgl.setKsxcglKscjGlg(dblFsCount);
						ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
						ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
						ksxcgl.setKsxcglKkslx("管理岗");
						ksxcgl.setKcglId(bmgl.getBmglKcglId());
						ksxcgl.setKsxcglKc(kcgl.getKcglName());
						ksxcgl.setKsxcglWjqk("否");
						ksxcgl.setKsxcglTszg("否");
						ksxcglService.insertksxcgl(ksxcgl);
					}
				}

			} else
			{
				ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
				ksxcgl.setKsxcglName(((Bmgl) session.getAttribute("cpglLoginSession1")).getBmglKsxm());
				ksxcgl.setKsxcglKscjGlg(dblFsCount);
				ksxcgl.setKsxcglAddtime(new Date());
				ksxcglService.updKscjByName(ksxcgl);
			}
		}
		return "cpgl/successSubmit";
	}

	/**
	 * 生成-管理岗-英语-试题
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryGlgEng", method = RequestMethod.GET)
	public String queryGlgEng(HttpSession session, Model model)
	{
		logInfo("queryGlgEng", "");
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");

		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", bmgl.getBmglBkgw());
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);

		if (ksxcglList.size() > 0)
		{
			if (ksxcglList.get(0).getKsxcglTszg().equals("是"))
			{
				List<JkglWithBLOBs> jkglListJ = jkService.queryRandomJk("J");
				List<JkglWithBLOBs> jkglListK = jkService.queryRandomJk("K");
				List<JkglWithBLOBs> jkglListL = jkService.queryRandomJk("L");

				List<JkglWithBLOBs> jkglListM = jkService.queryRandomJk("M-2");
				List<JkglFxtWithBLOBs> jkglFxtListM = jkService.jkqueryJkglFxt("M-2");
				List<JkglWithBLOBs> jkglListNA = jkService.queryRandomJk("N-A");
				List<JkglFxtWithBLOBs> jkglFxtListNA = jkService.jkqueryJkglFxt("N-A");
				List<JkglWithBLOBs> jkglListNB = jkService.queryRandomJk("N-B");
				List<JkglFxtWithBLOBs> jkglFxtListNB = jkService.jkqueryJkglFxt("N-B");
				session.setAttribute("jkglListJ", jkglListJ);
				session.setAttribute("jkglListK", jkglListK);
				session.setAttribute("jkglListL", jkglListL);
				session.setAttribute("jkglListM", jkglListM);
				session.setAttribute("jkglFxtListM", jkglFxtListM);
				session.setAttribute("jkglListNA", jkglListNA);
				session.setAttribute("jkglFxtListNA", jkglFxtListNA);
				session.setAttribute("jkglListNB", jkglListNB);
				session.setAttribute("jkglFxtListNB", jkglFxtListNB);
				return "Tkgl/tszgEng";
			} else
			{
				Object o = session.getAttribute("glgEngList");
				List<Tkgl> glgEngList = new ArrayList<Tkgl>();
				Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
				if (o == null)
				{
					glgEngList = getTk("J", 5);
					mv.put("qjyy", glgEngList);
					glgEngList = getTk("K", 10);
					mv.put("swxz", glgEngList);
					glgEngList = getTk("L", 1);
					mv.put("xxpp", glgEngList);
					glgEngList = getTk("M-2", 1);
					mv.put("ydlj", glgEngList);
					List<tkglFxt> ydljListFxt2 = tkglFxtService.queryTkglFxt("M-2");
					glgEngList = getTk("N-A", 1);
					mv.put("yydw1", glgEngList);
					List<tkglFxt> yydwListFxt1 = tkglFxtService.queryTkglFxt("N-A");
					glgEngList = getTk("N-B", 1);
					mv.put("yydw2", glgEngList);
					List<tkglFxt> yydwListFxt2 = tkglFxtService.queryTkglFxt("N-B");
					session.setAttribute("ydljListFxt2", ydljListFxt2);
					session.setAttribute("yydwListFxt1", yydwListFxt1);
					session.setAttribute("yydwListFxt2", yydwListFxt2);
					session.setAttribute("glgEngList", mv);
				} else
				{
					mv = (Map<String, List<Tkgl>>) session.getAttribute("glgEngList");
				}
				return "Tkgl/glgEng";
			}

		} else
		{
			Object o = session.getAttribute("glgEngList");
			List<Tkgl> glgEngList = new ArrayList<Tkgl>();
			Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
			if (o == null)
			{
				glgEngList = getTk("J", 5);
				mv.put("qjyy", glgEngList);
				glgEngList = getTk("K", 10);
				mv.put("swxz", glgEngList);
				glgEngList = getTk("L", 1);
				mv.put("xxpp", glgEngList);
				glgEngList = getTk("M-2", 1);
				mv.put("ydlj", glgEngList);
				List<tkglFxt> ydljListFxt2 = tkglFxtService.queryTkglFxt("M-2");
				glgEngList = getTk("N-A", 1);
				mv.put("yydw1", glgEngList);
				List<tkglFxt> yydwListFxt1 = tkglFxtService.queryTkglFxt("N-A");
				glgEngList = getTk("N-B", 1);
				mv.put("yydw2", glgEngList);
				List<tkglFxt> yydwListFxt2 = tkglFxtService.queryTkglFxt("N-B");
				session.setAttribute("ydljListFxt2", ydljListFxt2);
				session.setAttribute("yydwListFxt1", yydwListFxt1);
				session.setAttribute("yydwListFxt2", yydwListFxt2);
				session.setAttribute("glgEngList", mv);
				List<Tkgl> tkglListqjyy = mv.get("qjyy");
				for (Tkgl tkgl : tkglListqjyy)
				{
					System.out.println("J:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListswxz = mv.get("swxz");
				for (Tkgl tkgl : tkglListswxz)
				{
					System.out.println("K:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListxxpp = mv.get("xxpp");
				for (Tkgl tkgl : tkglListxxpp)
				{
					System.out.println("L:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListydlj = mv.get("ydlj");
				for (Tkgl tkgl : tkglListydlj)
				{
					System.out.println("M-2:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListyydw1 = mv.get("yydw1");
				for (Tkgl tkgl : tkglListyydw1)
				{
					System.out.println("N-A:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListyydw2 = mv.get("yydw2");
				for (Tkgl tkgl : tkglListyydw2)
				{
					System.out.println("N-B" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}

			} else
			{
				mv = (Map<String, List<Tkgl>>) session.getAttribute("glgEngList");
			}
			return "Tkgl/glgEng";
		}
	}

	@RequestMapping(value = "/tkglsuccessSubmit", method = RequestMethod.GET)
	public String tkglsuccessSubmit()
	{
		return "cpgl/successSubmit";
	}

	/**
	 * 提交-特殊照顾-汉语-成绩
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/glgTszgSubmit", method = RequestMethod.POST)
	public String glgTszgSubmit(HttpServletRequest request, HttpSession session)
	{
		logInfo("glgTszgSubmit", "");
		List<JkglWithBLOBs> jkglListA = (List<JkglWithBLOBs>) session.getAttribute("jkglListA");
		List<JkglWithBLOBs> jkglListD = (List<JkglWithBLOBs>) session.getAttribute("jkglListD");
		List<JkglWithBLOBs> jkglListE = (List<JkglWithBLOBs>) session.getAttribute("jkglListE");
		List<JkglWithBLOBs> jkglListF = (List<JkglWithBLOBs>) session.getAttribute("jkglListF");
		List<JkglWithBLOBs> jkglListI = (List<JkglWithBLOBs>) session.getAttribute("jkglListI");
		List<JkglWithBLOBs> jkglListH = (List<JkglWithBLOBs>) session.getAttribute("jkglListH");
		List<JkglWithBLOBs> jkglListG = (List<JkglWithBLOBs>) session.getAttribute("jkglListG");
		List<JkglFxtWithBLOBs> jkglFxtList = (List<JkglFxtWithBLOBs>) session.getAttribute("jkglFxtList");
		double dblFsCount = 0;
		double dblFs = 0;
		for (JkglWithBLOBs jkgl : jkglListA)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "A:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListD)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "D:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListE)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "E:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListF)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "F:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListI)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "I:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListH)
		{
			dblFs = 1.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "H:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListG)
		{
			dblFs = 3;
			for (JkglFxtWithBLOBs jkglFxt : jkglFxtList)
			{
				if (jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
				{
					if (request.getParameter(jkglFxt.getId()) == null || request.getParameter(jkglFxt.getId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(jkglFxt.getId()).equals(jkglFxt.getStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "G：" + jkgl.getJkglStbh() + "小题编号：" + jkglFxt.getStbh() + "选择的答案：" + request.getParameter(jkglFxt.getId()) + "正确答案：" + jkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			}
		}

		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSessionCurrent");
		Map<String, String> params = new HashMap<String, String>();

		if (bmgl.getBmglBkgw().equals("技术岗"))
		{
			Ksxcgl ksxcgl = new Ksxcgl();
			Map<String, String> ksxcglparams = new HashMap<String, String>();
			ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
			ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
			ksxcglparams.put("ksxcglKkslx", "技术岗");
			List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);
			params.put("jtjlkId", bmgl.getBmglJtjlkid());
			params.put("bkgw", "技术岗");
			List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
			for (Zzjg zzjg : zzjgs)
			{
				if (ksxcglList.size() == 0)
				{
					ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
					Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglDelflag((long) 1);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
					ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
					ksxcgl.setKsxcglKscjJsg(dblFsCount);
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
					ksxcgl.setKsxcglKkslx("技术岗");
					ksxcgl.setKcglId(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglKc(kcgl.getKcglName());
					ksxcgl.setKsxcglWjqk("否");
					ksxcgl.setKsxcglTszg("是");
					ksxcglService.insertksxcgl(ksxcgl);
				} else
				{
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglName(((Bmgl) session.getAttribute("cpglLoginSession1")).getBmglKsxm());
					ksxcgl.setKsxcglKscjJsg(dblFsCount);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcglService.updKscjByName(ksxcgl);
				}
			}
		} else
		{
			Ksxcgl ksxcgl = new Ksxcgl();
			Map<String, String> ksxcglparams = new HashMap<String, String>();
			ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
			ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
			ksxcglparams.put("ksxcglKkslx", "管理岗");
			List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);
			params.put("jtjlkId", bmgl.getBmglJtjlkid());
			params.put("bkgw", "管理岗");
			List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
			for (Zzjg zzjg : zzjgs)
			{
				if (ksxcglList.size() == 0)
				{
					ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
					Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglDelflag((long) 1);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
					ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
					ksxcgl.setKsxcglKscjGlg(dblFsCount);
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
					ksxcgl.setKsxcglKkslx("管理岗");
					ksxcgl.setKcglId(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglKc(kcgl.getKcglName());
					ksxcgl.setKsxcglWjqk("否");
					ksxcgl.setKsxcglTszg("是");
					ksxcglService.insertksxcgl(ksxcgl);
				} else
				{
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglName(((Bmgl) session.getAttribute("cpglLoginSession1")).getBmglKsxm());
					ksxcgl.setKsxcglKscjGlg(dblFsCount);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcglService.updKscjByName(ksxcgl);
				}
			}
		}
		return "cpgl/successSubmit";
	}

	/**
	 * 生成-技术岗-汉语-试题
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryJsgSj", method = RequestMethod.GET)
	public String queryJsgSj(HttpSession session, Model model)
	{
		logInfo("queryJsgSj", "");
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");

		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", bmgl.getBmglBkgw());
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);

		if (ksxcglList.size() > 0)
		{
			if (ksxcglList.get(0).getKsxcglTszg().equals("是"))
			{
				List<JkglWithBLOBs> jkglListA = jkService.queryRandomJk("A");
				List<JkglWithBLOBs> jkglListD = jkService.queryRandomJk("D");
				List<JkglWithBLOBs> jkglListE = jkService.queryRandomJk("E");
				List<JkglWithBLOBs> jkglListF = jkService.queryRandomJk("F");
				List<JkglWithBLOBs> jkglListI = jkService.queryRandomJk("I");
				List<JkglWithBLOBs> jkglListH = jkService.queryRandomJk("H");
				List<JkglWithBLOBs> jkglListG = jkService.queryRandomJk("G");
				List<JkglFxtWithBLOBs> jkglFxtList = jkService.jkqueryJkglFxt("G");
				session.setAttribute("jkglListA", jkglListA);
				session.setAttribute("jkglListD", jkglListD);
				session.setAttribute("jkglListE", jkglListE);
				session.setAttribute("jkglListF", jkglListF);
				session.setAttribute("jkglListI", jkglListI);
				session.setAttribute("jkglListH", jkglListH);
				session.setAttribute("jkglListG", jkglListG);
				session.setAttribute("jkglFxtList", jkglFxtList);
				return "Tkgl/glgTszgView";
			} else
			{
				Object o = session.getAttribute("JsgTkglList");
				List<Tkgl> tkglList = new ArrayList<Tkgl>();
				Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
				if (o == null)
				{
					tkglList = getTk("A", 10);
					mv.put("yylj", tkglList);
					tkglList = getTk("C", 3);
					mv.put("txtl", tkglList);
					tkglList = getTk("E", 2);
					mv.put("lbtl", tkglList);
					tkglList = getTk("D", 3);
					mv.put("dypd", tkglList);
					tkglList = getTk("F", 2);
					mv.put("ljtl", tkglList);
					tkglList = getTk("B", 10);
					mv.put("slgx", tkglList);
					tkglList = getTk("G", 2);
					mv.put("zlfx", tkglList);
					List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
					session.setAttribute("JsgZlfxListFxt", zlfxListFxt);
					session.setAttribute("JsgTkglList", mv);
				} else
				{
					List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
					session.setAttribute("JsgZlfxListFxt", zlfxListFxt);
					mv = (Map<String, List<Tkgl>>) session.getAttribute("JsgTkglList");
				}
				return "Tkgl/jsgSjView";
			}
		} else
		{
			Object o = session.getAttribute("JsgTkglList");
			List<Tkgl> tkglList = new ArrayList<Tkgl>();
			Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
			if (o == null)
			{
				tkglList = getTk("A", 10);
				mv.put("yylj", tkglList);
				tkglList = getTk("C", 3);
				mv.put("txtl", tkglList);
				tkglList = getTk("E", 2);
				mv.put("lbtl", tkglList);
				tkglList = getTk("D", 3);
				mv.put("dypd", tkglList);
				tkglList = getTk("F", 2);
				mv.put("ljtl", tkglList);
				tkglList = getTk("B", 10);
				mv.put("slgx", tkglList);
				tkglList = getTk("G", 2);
				mv.put("zlfx", tkglList);
				List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
				session.setAttribute("JsgZlfxListFxt", zlfxListFxt);
				session.setAttribute("JsgTkglList", mv);
				List<Tkgl> tkglListlx1 = mv.get("yylj");
				for (Tkgl tkgl : tkglListlx1)
				{
					System.out.println("A:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx2 = mv.get("txtl");
				for (Tkgl tkgl : tkglListlx2)
				{
					System.out.println("C:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx3 = mv.get("lbtl");
				for (Tkgl tkgl : tkglListlx3)
				{
					System.out.println("E:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx4 = mv.get("dypd");
				for (Tkgl tkgl : tkglListlx4)
				{
					System.out.println("D:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx5 = mv.get("ljtl");
				for (Tkgl tkgl : tkglListlx5)
				{
					System.out.println("F:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx6 = mv.get("slgx");
				for (Tkgl tkgl : tkglListlx6)
				{
					System.out.println("B:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListlx7 = mv.get("zlfx");
				for (Tkgl tkgl : tkglListlx7)
				{
					System.out.println("G-资料分析-240:试题编号：" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
			} else
			{
				List<tkglFxt> zlfxListFxt = tkglFxtService.queryTkglFxt("G");
				session.setAttribute("JsgZlfxListFxt", zlfxListFxt);
				mv = (Map<String, List<Tkgl>>) session.getAttribute("JsgTkglList");
			}
			return "Tkgl/jsgSjView";
		}

	}

	/**
	 * 提交-技术岗-汉语-成绩
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/JsgSjSubmit", method = RequestMethod.POST)
	public String JsgSjSubmit(HttpServletRequest request, HttpSession session)
	{
		logInfo("JsgSjSubmit", "");
		Map<String, List<Tkgl>> mv = (Map<String, List<Tkgl>>) session.getAttribute("JsgTkglList");
		List<tkglFxt> zlfxListFxt = (List<tkglFxt>) session.getAttribute("JsgZlfxListFxt");
		double dblFsCount = 0;
		double dblFs = 0;
		for (Map.Entry<String, List<Tkgl>> tkglList : mv.entrySet())
		{
			if (tkglList.getKey().equals("yylj"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("A :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "yylj:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("txtl"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("C :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "txtl:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("lbtl"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("E :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "lbtl:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("dypd"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("D :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "dypd:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("ljtl"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("F :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "ljtl:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("slgx"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("B :选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "slgx:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("zlfx"))
			{
				dblFs = 3;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : zlfxListFxt)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
									System.out.println("G-资料分析-240:试题编号：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "zlfxListFxt：" + tkgl.getTkglStbh() + "小题编号：" + tkglFxt.getStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else
			{
				dblFsCount = 0;
			}

		}
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");
		Bmgl bmgl1 = (Bmgl) session.getAttribute("cpglLoginSession2");
		Ksxcgl ksxcgl = new Ksxcgl();
		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", "技术岗");
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "技术岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		for (Zzjg zzjg : zzjgs)
		{
			if (ksxcglList.size() == 0)
			{
				if (bmgl.getBmglBkgw().equals("技术岗"))
				{
					ksxcgl.setKsxcglBmglId(bmgl.getBmglId());
					Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglDelflag((long) 1);
					ksxcgl.setKsxcglAddtime(new Date());
					ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
					ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
					ksxcgl.setKsxcglKscjJsg(dblFsCount);
					ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
					ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
					ksxcgl.setKsxcglKkslx("技术岗");
					ksxcgl.setKcglId(bmgl.getBmglKcglId());
					ksxcgl.setKsxcglKc(kcgl.getKcglName());
					ksxcgl.setKsxcglWjqk("否");
					ksxcgl.setKsxcglTszg("否");
					ksxcglService.insertksxcgl(ksxcgl);
				} else
				{
					if (bmgl1 != null)
					{
						ksxcgl.setKsxcglBmglId(bmgl1.getBmglId());
						Kcgl kcgl = kcglService.selectByPrimaryKey(bmgl.getBmglKcglId());
						ksxcgl.setKsxcglDelflag((long) 1);
						ksxcgl.setKsxcglAddtime(new Date());
						ksxcgl.setKsxcglId(RandomGUIDUtil.generateKey());
						ksxcgl.setKsxcglName(bmgl.getBmglKsxm());
						ksxcgl.setKsxcglKscjJsg(dblFsCount);
						ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
						ksxcgl.setKsxcglIdcard(bmgl.getBmglSfzh());
						ksxcgl.setKsxcglKkslx("技术岗");
						ksxcgl.setKcglId(bmgl.getBmglKcglId());
						ksxcgl.setKsxcglKc(kcgl.getKcglName());
						ksxcgl.setKsxcglWjqk("否");
						ksxcgl.setKsxcglTszg("否");
						ksxcglService.insertksxcgl(ksxcgl);
					}
				}

			} else
			{
				ksxcgl.setKsxcglBkdw(zzjg.getZzjgDwmc());
				ksxcgl.setKsxcglName(((Bmgl) session.getAttribute("cpglLoginSession1")).getBmglKsxm());
				ksxcgl.setKsxcglKscjJsg(dblFsCount);
				ksxcgl.setKsxcglAddtime(new Date());
				ksxcglService.updKscjByName(ksxcgl);
			}
		}
		return "cpgl/jsgSuccessSubmit";
	}

	/**
	 * 生成-技术岗-英语-试题
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryJsgEng", method = RequestMethod.GET)
	public String queryJsgEng(HttpSession session, Model model)
	{
		logInfo("queryJsgEng", "");
		Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");

		Map<String, String> ksxcglparams = new HashMap<String, String>();
		ksxcglparams.put("ksxcglName", bmgl.getBmglKsxm());
		ksxcglparams.put("ksxcglIdcard", bmgl.getBmglSfzh());
		ksxcglparams.put("ksxcglKkslx", bmgl.getBmglBkgw());
		List<Ksxcgl> ksxcglList = ksxcglService.queryByNameAndBkdw(ksxcglparams);

		if (ksxcglList.size() > 0)
		{
			if (ksxcglList.get(0).getKsxcglTszg().equals("是"))
			{
				List<JkglWithBLOBs> jkglListJ = jkService.queryRandomJk("J");
				List<JkglWithBLOBs> jkglListK = jkService.queryRandomJk("K");
				List<JkglWithBLOBs> jkglListL = jkService.queryRandomJk("L");

				List<JkglWithBLOBs> jkglListM = jkService.queryRandomJk("M-2");
				List<JkglFxtWithBLOBs> jkglFxtListM = jkService.jkqueryJkglFxt("M-2");
				List<JkglWithBLOBs> jkglListNA = jkService.queryRandomJk("N-A");
				List<JkglFxtWithBLOBs> jkglFxtListNA = jkService.jkqueryJkglFxt("N-A");
				List<JkglWithBLOBs> jkglListNB = jkService.queryRandomJk("N-B");
				List<JkglFxtWithBLOBs> jkglFxtListNB = jkService.jkqueryJkglFxt("N-B");
				session.setAttribute("jkglListJ", jkglListJ);
				session.setAttribute("jkglListK", jkglListK);
				session.setAttribute("jkglListL", jkglListL);
				session.setAttribute("jkglListM", jkglListM);
				session.setAttribute("jkglFxtListM", jkglFxtListM);
				session.setAttribute("jkglListNA", jkglListNA);
				session.setAttribute("jkglFxtListNA", jkglFxtListNA);
				session.setAttribute("jkglListNB", jkglListNB);
				session.setAttribute("jkglFxtListNB", jkglFxtListNB);
				return "Tkgl/tszgEng";
			} else
			{
				Object o = session.getAttribute("jsgEngList");
				List<Tkgl> jsgEngList = new ArrayList<Tkgl>();
				Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
				if (o == null)
				{
					jsgEngList = getTk("J", 5);
					mv.put("qjyy", jsgEngList);
					jsgEngList = getTk("K", 10);
					mv.put("swxz", jsgEngList);
					jsgEngList = getTk("L", 1);
					mv.put("xxpp", jsgEngList);
					jsgEngList = getTk("M-1", 1);
					mv.put("ydlj", jsgEngList);
					List<tkglFxt> ydljListFxtM = tkglFxtService.queryTkglFxt("M-1");
					jsgEngList = getTk("N-A", 1);
					mv.put("yydw1", jsgEngList);
					List<tkglFxt> yydwListFxtNA = tkglFxtService.queryTkglFxt("N-A");
					jsgEngList = getTk("N-B", 1);
					mv.put("yydw2", jsgEngList);
					List<tkglFxt> yydwListFxtNB = tkglFxtService.queryTkglFxt("N-B");
					session.setAttribute("ydljListFxtM", ydljListFxtM);
					session.setAttribute("yydwListFxtNA", yydwListFxtNA);
					session.setAttribute("yydwListFxtNB", yydwListFxtNB);
					session.setAttribute("jsgEngList", mv);
					List<Tkgl> tkglListqjyy = mv.get("qjyy");
					for (Tkgl tkgl : tkglListqjyy)
					{
						System.out.println("J:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
					List<Tkgl> tkglListswxz = mv.get("swxz");
					for (Tkgl tkgl : tkglListswxz)
					{
						System.out.println("K:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
					List<Tkgl> tkglListxxpp = mv.get("xxpp");
					for (Tkgl tkgl : tkglListxxpp)
					{
						System.out.println("L:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
					List<Tkgl> tkglListydlj = mv.get("ydlj");
					for (Tkgl tkgl : tkglListydlj)
					{
						System.out.println("M-2:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
					List<Tkgl> tkglListyydw1 = mv.get("yydw1");
					for (Tkgl tkgl : tkglListyydw1)
					{
						System.out.println("N-A:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
					List<Tkgl> tkglListyydw2 = mv.get("yydw2");
					for (Tkgl tkgl : tkglListyydw2)
					{
						System.out.println("N-B" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
					}
				} else
				{
					mv = (Map<String, List<Tkgl>>) session.getAttribute("jsgEngList");
				}
				return "Tkgl/jsgEng";
			}

		} else
		{
			Object o = session.getAttribute("jsgEngList");
			List<Tkgl> jsgEngList = new ArrayList<Tkgl>();
			Map<String, List<Tkgl>> mv = new HashMap<String, List<Tkgl>>();
			if (o == null)
			{
				jsgEngList = getTk("J", 5);
				mv.put("qjyy", jsgEngList);
				jsgEngList = getTk("K", 10);
				mv.put("swxz", jsgEngList);
				jsgEngList = getTk("L", 1);
				mv.put("xxpp", jsgEngList);
				jsgEngList = getTk("M-1", 1);
				mv.put("ydlj", jsgEngList);
				List<tkglFxt> ydljListFxtM = tkglFxtService.queryTkglFxt("M-1");
				jsgEngList = getTk("N-A", 1);
				mv.put("yydw1", jsgEngList);
				List<tkglFxt> yydwListFxtNA = tkglFxtService.queryTkglFxt("N-A");
				jsgEngList = getTk("N-B", 1);
				mv.put("yydw2", jsgEngList);
				List<tkglFxt> yydwListFxtNB = tkglFxtService.queryTkglFxt("N-B");
				session.setAttribute("ydljListFxtM", ydljListFxtM);
				session.setAttribute("yydwListFxtNA", yydwListFxtNA);
				session.setAttribute("yydwListFxtNB", yydwListFxtNB);
				session.setAttribute("jsgEngList", mv);
				List<Tkgl> tkglListqjyy = mv.get("qjyy");
				for (Tkgl tkgl : tkglListqjyy)
				{
					System.out.println("J:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListswxz = mv.get("swxz");
				for (Tkgl tkgl : tkglListswxz)
				{
					System.out.println("K:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListxxpp = mv.get("xxpp");
				for (Tkgl tkgl : tkglListxxpp)
				{
					System.out.println("L:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListydlj = mv.get("ydlj");
				for (Tkgl tkgl : tkglListydlj)
				{
					System.out.println("M-2:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListyydw1 = mv.get("yydw1");
				for (Tkgl tkgl : tkglListyydw1)
				{
					System.out.println("N-A:" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
				List<Tkgl> tkglListyydw2 = mv.get("yydw2");
				for (Tkgl tkgl : tkglListyydw2)
				{
					System.out.println("N-B" + tkgl.getTkglStbh() + ";题干:" + tkgl.getTkglSttg() + ";正确答案:" + tkgl.getTkglStda());
				}
			} else
			{
				mv = (Map<String, List<Tkgl>>) session.getAttribute("jsgEngList");
			}
			return "Tkgl/jsgEng";
		}
	}

	/**
	 * 提交-技术岗-英语-成绩
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/JsgEngSubmit", method = RequestMethod.POST)
	public String JsgEngSubmit(HttpServletRequest request, HttpSession session) throws ParseException
	{
		logInfo("JsgEngSubmit", "");
		Map<String, List<Tkgl>> mv = (Map<String, List<Tkgl>>) session.getAttribute("jsgEngList");
		List<tkglFxt> ydljListFxtM = (List<tkglFxt>) session.getAttribute("ydljListFxtM");
		List<tkglFxt> yydwListFxtNA = (List<tkglFxt>) session.getAttribute("yydwListFxtNA");
		List<tkglFxt> yydwListFxtNB = (List<tkglFxt>) session.getAttribute("yydwListFxtNB");
		double dblFsCount = 0;
		double dblFs = 0;
		for (Map.Entry<String, List<Tkgl>> tkglList : mv.entrySet())
		{
			if (tkglList.getKey().equals("qjyy"))
			{
				dblFs = 0.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "qjyy:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("swxz"))
			{
				dblFs = 0.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "swxz:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("xxpp"))
			{
				dblFs = 5.0;
				for (Tkgl tkgl : tkglList.getValue())
				{
					String xxppString = "";
					for (int i = 1; i <= 5; i++)
					{
						xxppString += request.getParameter(tkgl.getTkglId() + i);
					}
					if (xxppString == null || xxppString == "")
					{
						dblFsCount += 0;
					} else
					{
						if (xxppString.equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "xxpp:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + xxppString + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("ydlj"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : ydljListFxtM)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "ydlj:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else if (tkglList.getKey().equals("yydw1"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : yydwListFxtNA)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "yydw1:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else if (tkglList.getKey().equals("yydw2"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : yydwListFxtNB)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "yydw2:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else
			{
				dblFsCount = 0;
			}
		}
		Bmgl bmgl = ((Bmgl) session.getAttribute("currentBmgl"));
		Ksxcgl ksxcglUpdate = new Ksxcgl();
		ksxcglUpdate.setKsxcglName(bmgl.getBmglKsxm());
		ksxcglUpdate.setKsxcglJsgYycj(dblFsCount);
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "技术岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		for (Zzjg zzjg : zzjgs)
		{
			ksxcglUpdate.setKsxcglBkdw(zzjg.getZzjgDwmc());
			ksxcglService.updKscjByName(ksxcglUpdate);
		}
		// 判断是否存在个性测试成绩
		int gxcsCount = tkglService.checkGxcsCount(bmgl.getBmglSfzh());
		try
		{
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(bmgl.getBmglJtjlkid());
			Cjtsgl cjtsgl = cjtsglService.selectByPrimaryKey("test001");

			double dblCpcj = 0;
			System.out.println("----------------------" + bmgl.getBmglId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("bkgw", bmgl.getBmglBkgw());
			map.put("sfzh", bmgl.getBmglSfzh());
			List<Ksxcgl> ksxcglMessageList = ksxcglService.selectByBmglId(map);
			System.out.println("===================" + ksxcglMessageList.get(0).getKsxcglKkslx());

			dblCpcj = ksxcglMessageList.get(0).getKsxcglKscjJsg() + ksxcglMessageList.get(0).getKsxcglJsgYycj();

			bmgl.setBmglKscj(Double.toString(dblCpcj));
			bmglService.updKscjById(bmgl);

			jtjlk.setJtjlkCpcj(Double.toString(dblCpcj));
			jtjlk.setJtjlkJlzt("已测评");
			jtjlkService.updateCpcj(jtjlk);

			List<Jtjlk> jtjlks = jtjlkService.selectSameGw(jtjlk);
			for (Jtjlk j : jtjlks)
			{
				Zzjg zzjg = zzjgService.selectByBmglId(bmgl.getBmglId());
				if (j != null && zzjg != null && cjtsgl != null)
				{
					int yssj = cjtsgl.getCjtsglTsyssj();
					String strContent = "姓名：" + j.getJtjlkName() + "，" + "学校：" + j.getJtjlkByxx() + "，" + "专业：" + j.getJtjlkZy() + "，" + "测评成绩：" + dblCpcj + "。请登录后台管理系统查看应聘者性格测试报告。";
					Xxtz xxtz = new Xxtz();
					xxtz.setUserId(zzjg.getZzjgId());
					xxtz.setXxtzAddtime(new Date());
					xxtz.setXxtzCuser(j.getJtjlkUserid());
					xxtz.setXxtzDelflag((long) 1);
					xxtz.setXxtzId(RandomGUIDUtil.generateKey());
					xxtz.setXxtzTelepohne(Long.parseLong(zzjg.getZzjgLxrdh()));
					xxtz.setXxtzType("成绩推送");
					xxtz.setXxtzUser(bmgl.getBmglKsxm());
					xxtz.setXxtzIsread(1);

					java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String d = format.format(new Date());
					System.out.println(d);

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date timeNow;
					timeNow = df.parse(d);
					Calendar begin = Calendar.getInstance();
					begin.setTime(timeNow);
					begin.add(Calendar.MINUTE, yssj);
					String sendTime = df.format(begin.getTime());

					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1)
					{
						// MailSenderInfo mailInfo = new MailSenderInfo();
						// // 收件人邮箱
						// mailInfo.setToAddress(zzjg.getZzjgLxremail());
						// // 邮件标题
						// mailInfo.setSubject("测评成绩——" + bmgl.getBmglKsxm());
						// // 邮件内容
						// StringBuffer buffer = new StringBuffer();
						// buffer.append(strContent);
						// mailInfo.setContent(buffer.toString());
						// // 发送html格式
						// SendEmail.sendHtmlMail(mailInfo);
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
						logInfo("短信平台", JSONArray.fromObject(xxtz).toString() + "-----当前时间:" + d + "  延时时间:" + yssj + "  发送时间:" + sendTime);
					}
					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1 || cjtsgl.getCjtsglTsfs().toString().indexOf("短信") != -1)
						xxtzService.insertxxtz(xxtz);
				}
			}
		} catch (Exception e)
		{
			logInfo("JsgEngSubmit", "******Error");
		}
		if (gxcsCount <= 0)
		{
			return "cpgl/successSubmit1";
		} else
		{
			return "cpgl/successSubmit2";
		}
	}

	/**
	 * 提交-管理岗-英语-成绩
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/GlgEngSubmit", method = RequestMethod.POST)
	public String GlgEngSubmit(HttpServletRequest request, HttpSession session) throws ParseException
	{
		logInfo("GlgEngSubmit", "");
		Map<String, List<Tkgl>> mv = (Map<String, List<Tkgl>>) session.getAttribute("glgEngList");
		List<tkglFxt> ydljListFxt2 = (List<tkglFxt>) session.getAttribute("ydljListFxt2");
		List<tkglFxt> yydwListFxt1 = (List<tkglFxt>) session.getAttribute("yydwListFxt1");
		List<tkglFxt> yydwListFxt2 = (List<tkglFxt>) session.getAttribute("yydwListFxt2");
		double dblFsCount = 0;
		double dblFs = 0;
		for (Map.Entry<String, List<Tkgl>> tkglList : mv.entrySet())
		{
			if (tkglList.getKey().equals("qjyy"))
			{
				dblFs = 0.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("J：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "qjyy:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("swxz"))
			{
				dblFs = 0.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					if (request.getParameter(tkgl.getTkglId()) == null || request.getParameter(tkgl.getTkglId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(tkgl.getTkglId()).equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("K：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "swxz:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkgl.getTkglId()) + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("xxpp"))
			{
				dblFs = 5.0;
				for (Tkgl tkgl : tkglList.getValue())
				{
					String xxppString = "";
					for (int i = 1; i <= 5; i++)
					{
						xxppString += request.getParameter(tkgl.getTkglId() + i);
					}
					if (xxppString == null || xxppString == "")
					{
						dblFsCount += 0;
					} else
					{
						if (xxppString.equals(tkgl.getTkglStda()))
						{
							dblFsCount += dblFs;
							System.out.println("L：" + xxppString + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "xxpp:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + xxppString + "正确答案：" + tkgl.getTkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			} else if (tkglList.getKey().equals("ydlj"))
			{
				dblFs = 1.5;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : ydljListFxt2)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
									System.out.println("M-2：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "ydlj:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else if (tkglList.getKey().equals("yydw1"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : yydwListFxt1)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
									System.out.println("N-A：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "yydw1:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else if (tkglList.getKey().equals("yydw2"))
			{
				dblFs = 1;
				for (Tkgl tkgl : tkglList.getValue())
				{
					for (tkglFxt tkglFxt : yydwListFxt2)
					{
						if (tkglFxt.getStbh().equals(tkgl.getTkglStbh()))
						{
							if (request.getParameter(tkglFxt.getId()) == null || request.getParameter(tkglFxt.getId()) == "")
							{
								dblFsCount += 0;
							} else
							{
								if (request.getParameter(tkglFxt.getId()).equals(tkglFxt.getStda()))
								{
									dblFsCount += dblFs;
									System.out.println("N-B：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);

								} else
								{
									dblFsCount += 0;
								}
								logInfo("testSum", "yydw2:试题编号：" + tkgl.getTkglStbh() + "选择的答案：" + request.getParameter(tkglFxt.getId()) + "正确答案：" + tkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
							}
						}
					}
				}
			} else
			{
				dblFsCount = 0;
			}
		}
		Bmgl bmgl = ((Bmgl) session.getAttribute("currentBmgl"));
		Ksxcgl ksxcglUpdate = new Ksxcgl();
		ksxcglUpdate.setKsxcglName(bmgl.getBmglKsxm());
		ksxcglUpdate.setKsxcglKscjYycj(dblFsCount);
		Map<String, String> params = new HashMap<String, String>();
		params.put("jtjlkId", bmgl.getBmglJtjlkid());
		params.put("bkgw", "管理岗");
		List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
		for (Zzjg zzjg : zzjgs)
		{
			ksxcglUpdate.setKsxcglBkdw(zzjg.getZzjgDwmc());
			ksxcglService.updKscjByName(ksxcglUpdate);
		}
		// 判断是否存在个性测试成绩
		int gxcsCount = tkglService.checkGxcsCount(bmgl.getBmglSfzh());
		try
		{
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(bmgl.getBmglJtjlkid());
			Cjtsgl cjtsgl = cjtsglService.selectByPrimaryKey("test001");

			double dblCpcj = 0;
			System.out.println("----------------------" + bmgl.getBmglId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("bkgw", bmgl.getBmglBkgw());
			map.put("sfzh", bmgl.getBmglSfzh());
			List<Ksxcgl> ksxcglMessageList = ksxcglService.selectByBmglId(map);
			System.out.println("===================" + ksxcglMessageList.get(0).getKsxcglKkslx());

			dblCpcj = ksxcglMessageList.get(0).getKsxcglKscjGlg() + ksxcglMessageList.get(0).getKsxcglKscjYycj();

			bmgl.setBmglKscj(Double.toString(dblCpcj));
			bmglService.updKscjById(bmgl);

			jtjlk.setJtjlkCpcj(Double.toString(dblCpcj));
			jtjlk.setJtjlkJlzt("已测评");
			jtjlkService.updateCpcj(jtjlk);

			List<Jtjlk> jtjlks = jtjlkService.selectSameGw(jtjlk);
			for (Jtjlk j : jtjlks)
			{
				Zzjg zzjg = zzjgService.selectByBmglId(bmgl.getBmglId());
				if (j != null && zzjg != null && cjtsgl != null)
				{
					int yssj = cjtsgl.getCjtsglTsyssj();
					String strContent = "姓名：" + j.getJtjlkName() + "，" + "学校：" + j.getJtjlkByxx() + "，" + "专业：" + j.getJtjlkZy() + "，" + "测评成绩：" + dblCpcj + "。请登录后台管理系统查看应聘者性格测试报告。";
					Xxtz xxtz = new Xxtz();
					xxtz.setUserId(zzjg.getZzjgId());
					xxtz.setXxtzAddtime(new Date());
					xxtz.setXxtzCuser(j.getJtjlkUserid());
					xxtz.setXxtzDelflag((long) 1);
					xxtz.setXxtzId(RandomGUIDUtil.generateKey());
					xxtz.setXxtzTelepohne(Long.parseLong(zzjg.getZzjgLxrdh()));
					xxtz.setXxtzType("成绩推送");
					xxtz.setXxtzUser(bmgl.getBmglKsxm());
					xxtz.setXxtzIsread(1);

					java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String d = format.format(new Date());
					System.out.println(d);

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date timeNow;
					timeNow = df.parse(d);
					Calendar begin = Calendar.getInstance();
					begin.setTime(timeNow);
					begin.add(Calendar.MINUTE, yssj);
					String sendTime = df.format(begin.getTime());

					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1)
					{
						// MailSenderInfo mailInfo = new MailSenderInfo();
						// // 收件人邮箱
						// mailInfo.setToAddress(zzjg.getZzjgLxremail());
						// // 邮件标题
						// mailInfo.setSubject("测评成绩——" + bmgl.getBmglKsxm());
						// // 邮件内容
						// StringBuffer buffer = new StringBuffer();
						// buffer.append(strContent);
						// mailInfo.setContent(buffer.toString());
						// // 发送html格式
						// SendEmail.sendHtmlMail(mailInfo);
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
						logInfo("短信平台", JSONArray.fromObject(xxtz).toString() + "-----当前时间:" + d + "  延时时间:" + yssj + "  发送时间:" + sendTime);
					}
					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1 || cjtsgl.getCjtsglTsfs().toString().indexOf("短信") != -1)
						xxtzService.insertxxtz(xxtz);
				}
			}
		} catch (Exception e)
		{
			logInfo("GlgEngSubmit", "******Error");
		}
		if (gxcsCount <= 0)
		{
			return "cpgl/successSubmit1";
		} else
		{
			return "cpgl/successSubmit2";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/TszgEngSubmit", method = RequestMethod.POST)
	public String TszgEngSubmit(HttpServletRequest request, HttpSession session) throws ParseException
	{
		logInfo("TszgEngSubmit", "");
		List<JkglWithBLOBs> jkglListJ = (List<JkglWithBLOBs>) session.getAttribute("jkglListJ");
		List<JkglWithBLOBs> jkglListK = (List<JkglWithBLOBs>) session.getAttribute("jkglListK");
		List<JkglWithBLOBs> jkglListL = (List<JkglWithBLOBs>) session.getAttribute("jkglListL");

		List<JkglWithBLOBs> jkglListM = (List<JkglWithBLOBs>) session.getAttribute("jkglListM");
		List<JkglFxtWithBLOBs> jkglFxtListM = (List<JkglFxtWithBLOBs>) session.getAttribute("jkglFxtListM");
		List<JkglWithBLOBs> jkglListNA = (List<JkglWithBLOBs>) session.getAttribute("jkglListNA");
		List<JkglFxtWithBLOBs> jkglFxtListNA = (List<JkglFxtWithBLOBs>) session.getAttribute("jkglFxtListNA");
		List<JkglWithBLOBs> jkglListNB = (List<JkglWithBLOBs>) session.getAttribute("jkglListNB");
		List<JkglFxtWithBLOBs> jkglFxtListNB = (List<JkglFxtWithBLOBs>) session.getAttribute("jkglFxtListNB");
		double dblFsCount = 0;
		double dblFs = 0;
		for (JkglWithBLOBs jkgl : jkglListJ)
		{
			dblFs = 0.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "J:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListK)
		{
			dblFs = 0.5;
			if (request.getParameter(jkgl.getJkglId()) == null || request.getParameter(jkgl.getJkglId()) == "")
			{
				dblFsCount += 0;
			} else
			{
				if (request.getParameter(jkgl.getJkglId()).equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "K:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkgl.getJkglId()) + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListL)
		{
			dblFs = 5.0;
			String xxppString = "";
			for (int i = 1; i <= 5; i++)
			{
				xxppString += request.getParameter(jkgl.getJkglId() + i);
			}
			if (xxppString == null || xxppString == "")
			{
				dblFsCount += 0;
			} else
			{
				if (xxppString.equals(jkgl.getJkglStda()))
				{
					dblFsCount += dblFs;
				} else
				{
					dblFsCount += 0;
				}
				logInfo("testSum", "L:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + xxppString + "正确答案：" + jkgl.getJkglStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
			}
		}
		for (JkglWithBLOBs jkgl : jkglListM)
		{
			dblFs = 1.5;
			for (JkglFxtWithBLOBs jkglFxt : jkglFxtListM)
			{
				if (jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
				{
					if (request.getParameter(jkglFxt.getId()) == null || request.getParameter(jkglFxt.getId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(jkglFxt.getId()).equals(jkglFxt.getStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "M:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkglFxt.getId()) + "正确答案：" + jkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			}
		}
		for (JkglWithBLOBs jkgl : jkglListNB)
		{
			dblFs = 1.5;
			for (JkglFxtWithBLOBs jkglFxt : jkglFxtListNB)
			{
				if (jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
				{
					if (request.getParameter(jkglFxt.getId()) == null || request.getParameter(jkglFxt.getId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(jkglFxt.getId()).equals(jkglFxt.getStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "N-B:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkglFxt.getId()) + "正确答案：" + jkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			}
		}
		for (JkglWithBLOBs jkgl : jkglListNA)
		{
			dblFs = 1.5;
			for (JkglFxtWithBLOBs jkglFxt : jkglFxtListNA)
			{
				if (jkgl.getJkglStbh().equals(jkglFxt.getStbh()))
				{
					if (request.getParameter(jkglFxt.getId()) == null || request.getParameter(jkglFxt.getId()) == "")
					{
						dblFsCount += 0;
					} else
					{
						if (request.getParameter(jkglFxt.getId()).equals(jkglFxt.getStda()))
						{
							dblFsCount += dblFs;
						} else
						{
							dblFsCount += 0;
						}
						logInfo("testSum", "N-A:试题编号：" + jkgl.getJkglStbh() + "选择的答案：" + request.getParameter(jkglFxt.getId()) + "正确答案：" + jkglFxt.getStda() + "本题得分" + dblFs + "最终得分：" + dblFsCount);
					}
				}
			}
		}
		Bmgl bmgl = ((Bmgl) session.getAttribute("currentBmgl"));
		Ksxcgl ksxcglUpdate = new Ksxcgl();
		ksxcglUpdate.setKsxcglName(bmgl.getBmglKsxm());
		if (bmgl.getBmglBkgw().equals("技术岗"))
		{
			ksxcglUpdate.setKsxcglJsgYycj(dblFsCount);
			Map<String, String> params = new HashMap<String, String>();
			params.put("jtjlkId", bmgl.getBmglJtjlkid());
			params.put("bkgw", "技术岗");
			List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
			for (Zzjg zzjg : zzjgs)
			{
				ksxcglUpdate.setKsxcglBkdw(zzjg.getZzjgDwmc());
				ksxcglService.updKscjByName(ksxcglUpdate);
			}
		} else
		{
			ksxcglUpdate.setKsxcglKscjYycj(dblFsCount);
			Map<String, String> params = new HashMap<String, String>();
			params.put("jtjlkId", bmgl.getBmglJtjlkid());
			params.put("bkgw", "管理岗");
			List<Zzjg> zzjgs = zzjgService.selectAllBkdw(params);
			for (Zzjg zzjg : zzjgs)
			{
				ksxcglUpdate.setKsxcglBkdw(zzjg.getZzjgDwmc());
				ksxcglService.updKscjByName(ksxcglUpdate);
			}
		}
		// 判断是否存在个性测试成绩
		int gxcsCount = tkglService.checkGxcsCount(bmgl.getBmglSfzh());
		try
		{
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(bmgl.getBmglJtjlkid());
			Cjtsgl cjtsgl = cjtsglService.selectByPrimaryKey("test001");

			double dblCpcj = 0;
			System.out.println("----------------------" + bmgl.getBmglId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("bkgw", bmgl.getBmglBkgw());
			map.put("sfzh", bmgl.getBmglSfzh());
			List<Ksxcgl> ksxcglMessageList = ksxcglService.selectByBmglId(map);
			System.out.println("===================" + ksxcglMessageList.get(0).getKsxcglKkslx());

			for (Ksxcgl ksxcgl : ksxcglMessageList)
			{
				if (bmgl.getBmglBkgw().equals("管理岗"))
					dblCpcj = ksxcgl.getKsxcglKscjGlg() + ksxcgl.getKsxcglKscjYycj();
				else
					dblCpcj = ksxcgl.getKsxcglKscjJsg() + ksxcgl.getKsxcglJsgYycj();

				bmgl.setBmglKscj(Double.toString(dblCpcj));
				bmglService.updKscjById(bmgl);

				jtjlk.setJtjlkCpcj(Double.toString(dblCpcj));
				jtjlk.setJtjlkJlzt("已测评");

			}
			jtjlkService.updateCpcj(jtjlk);
			List<Jtjlk> jtjlks = jtjlkService.selectSameGw(jtjlk);
			for (Jtjlk j : jtjlks)
			{
				Zzjg zzjg = zzjgService.selectByBmglId(bmgl.getBmglId());
				if (j != null && zzjg != null && cjtsgl != null)
				{
					int yssj = cjtsgl.getCjtsglTsyssj();
					String strContent = "姓名：" + j.getJtjlkName() + "，" + "学校：" + j.getJtjlkByxx() + "，" + "专业：" + j.getJtjlkZy() + "，" + "测评成绩：" + dblCpcj + "。请登录后台管理系统查看应聘者性格测试报告。";
					Xxtz xxtz = new Xxtz();
					xxtz.setUserId(zzjg.getZzjgId());
					xxtz.setXxtzAddtime(new Date());
					xxtz.setXxtzCuser(j.getJtjlkUserid());
					xxtz.setXxtzDelflag((long) 1);
					xxtz.setXxtzId(RandomGUIDUtil.generateKey());
					xxtz.setXxtzTelepohne(Long.parseLong(zzjg.getZzjgLxrdh()));
					xxtz.setXxtzType("成绩推送");
					xxtz.setXxtzUser(bmgl.getBmglKsxm());
					xxtz.setXxtzIsread(1);

					java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String d = format.format(new Date());
					System.out.println(d);

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date timeNow;
					timeNow = df.parse(d);
					Calendar begin = Calendar.getInstance();
					begin.setTime(timeNow);
					begin.add(Calendar.MINUTE, yssj);
					String sendTime = df.format(begin.getTime());

					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1)
					{
						// MailSenderInfo mailInfo = new MailSenderInfo();
						// // 收件人邮箱
						// mailInfo.setToAddress(zzjg.getZzjgLxremail());
						// // 邮件标题
						// mailInfo.setSubject("测评成绩——" + bmgl.getBmglKsxm());
						// // 邮件内容
						// StringBuffer buffer = new StringBuffer();
						// buffer.append(strContent);
						// mailInfo.setContent(buffer.toString());
						// // 发送html格式
						// SendEmail.sendHtmlMail(mailInfo);
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
						logInfo("短信平台", JSONArray.fromObject(xxtz).toString() + "-----当前时间:" + d + "  延时时间:" + yssj + "  发送时间:" + sendTime);
					}
					if (cjtsgl.getCjtsglTsfs().toString().indexOf("邮件") != -1 || cjtsgl.getCjtsglTsfs().toString().indexOf("短信") != -1)
						xxtzService.insertxxtz(xxtz);
				}
			}
		} catch (Exception e)
		{
			logInfo("TszgEngSubmit", "******Error");
		}
		if (gxcsCount <= 0)
		{
			return "cpgl/successSubmit1";
		} else
		{
			return "cpgl/successSubmit2";
		}
	}

	/**
	 * 个性测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/querySjgx", method = RequestMethod.GET)
	public ModelAndView querySjgx()
	{
		return new ModelAndView("Tkgl/gxcs");
	}

	/**
	 * 个性测试提交算分
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/TestSubmitgx", method = RequestMethod.POST)
	public String TestSubmitgx(Hdlst hdlst, Model model, HttpSession session)
	{
		logInfo("TestSubmitgx", JSONArray.fromObject(hdlst).toString());
		Map<String, Integer> mapCount = new HashMap<String, Integer>();
		/**
		 * C
		 */

		if (hdlst.getName1() == null || hdlst.getName2() == null || hdlst.getName3() == null || hdlst.getName4() == null || hdlst.getName5() == null || hdlst.getName6() == null || hdlst.getName7() == null || hdlst.getName8() == null || hdlst.getName9() == null || hdlst.getName10() == null || hdlst.getName11() == null || hdlst.getName12() == null || hdlst.getName13() == null || hdlst.getName14() == null || hdlst.getName15() == null || hdlst.getName16() == null || hdlst.getName17() == null || hdlst.getName18() == null || hdlst.getName19() == null || hdlst.getName20() == null || hdlst.getName21() == null || hdlst.getName22() == null || hdlst.getName23() == null || hdlst.getName24() == null || hdlst.getName25() == null || hdlst.getName26() == null || hdlst.getName27() == null
				|| hdlst.getName28() == null || hdlst.getName29() == null || hdlst.getName30() == null || hdlst.getName31() == null || hdlst.getName32() == null || hdlst.getName33() == null || hdlst.getName34() == null || hdlst.getName35() == null || hdlst.getName36() == null || hdlst.getName37() == null || hdlst.getName38() == null || hdlst.getName39() == null || hdlst.getName40() == null || hdlst.getName41() == null || hdlst.getName42() == null || hdlst.getName43() == null || hdlst.getName44() == null || hdlst.getName45() == null || hdlst.getName46() == null || hdlst.getName47() == null || hdlst.getName48() == null || hdlst.getName49() == null || hdlst.getName50() == null || hdlst.getName51() == null || hdlst.getName52() == null || hdlst.getName53() == null || hdlst.getName54() == null
				|| hdlst.getName55() == null || hdlst.getName56() == null || hdlst.getName57() == null || hdlst.getName58() == null || hdlst.getName59() == null || hdlst.getName60() == null || hdlst.getName61() == null || hdlst.getName62() == null || hdlst.getName63() == null || hdlst.getName64() == null || hdlst.getName65() == null || hdlst.getName66() == null || hdlst.getName67() == null || hdlst.getName68() == null || hdlst.getName69() == null || hdlst.getName70() == null || hdlst.getName71() == null || hdlst.getName72() == null || hdlst.getName73() == null || hdlst.getName74() == null || hdlst.getName75() == null || hdlst.getName76() == null || hdlst.getName77() == null || hdlst.getName78() == null || hdlst.getName79() == null || hdlst.getName80() == null || hdlst.getName81() == null
				|| hdlst.getName82() == null || hdlst.getName83() == null || hdlst.getName84() == null || hdlst.getName85() == null || hdlst.getName86() == null || hdlst.getName87() == null || hdlst.getName88() == null || hdlst.getName89() == null || hdlst.getName90() == null || hdlst.getName91() == null || hdlst.getName92() == null || hdlst.getName93() == null || hdlst.getName94() == null || hdlst.getName95() == null || hdlst.getName96() == null || hdlst.getName97() == null || hdlst.getName98() == null || hdlst.getName99() == null || hdlst.getName100() == null || hdlst.getName101() == null || hdlst.getName102() == null || hdlst.getName103() == null || hdlst.getName104() == null || hdlst.getName105() == null || hdlst.getName106() == null || hdlst.getName107() == null
				|| hdlst.getName108() == null || hdlst.getName109() == null || hdlst.getName110() == null || hdlst.getName111() == null || hdlst.getName112() == null || hdlst.getName113() == null || hdlst.getName114() == null || hdlst.getName115() == null || hdlst.getName116() == null || hdlst.getName117() == null || hdlst.getName118() == null || hdlst.getName119() == null || hdlst.getName120() == null || hdlst.getName121() == null || hdlst.getName122() == null || hdlst.getName123() == null || hdlst.getName124() == null || hdlst.getName125() == null || hdlst.getName126() == null || hdlst.getName127() == null || hdlst.getName128() == null || hdlst.getName129() == null || hdlst.getName130() == null || hdlst.getName131() == null || hdlst.getName132() == null || hdlst.getName133() == null
				|| hdlst.getName134() == null || hdlst.getName135() == null || hdlst.getName136() == null || hdlst.getName137() == null || hdlst.getName138() == null || hdlst.getName139() == null || hdlst.getName140() == null || hdlst.getName141() == null || hdlst.getName142() == null || hdlst.getName143() == null || hdlst.getName144() == null || hdlst.getName145() == null || hdlst.getName146() == null || hdlst.getName147() == null || hdlst.getName148() == null || hdlst.getName149() == null || hdlst.getName150() == null || hdlst.getName151() == null || hdlst.getName152() == null || hdlst.getName153() == null || hdlst.getName1() == "" || hdlst.getName2() == "" || hdlst.getName3() == "" || hdlst.getName4() == "" || hdlst.getName5() == "" || hdlst.getName6() == "" || hdlst.getName7() == ""
				|| hdlst.getName8() == "" || hdlst.getName9() == "" || hdlst.getName10() == "" || hdlst.getName11() == "" || hdlst.getName12() == "" || hdlst.getName13() == "" || hdlst.getName14() == "" || hdlst.getName15() == "" || hdlst.getName16() == "" || hdlst.getName17() == "" || hdlst.getName18() == "" || hdlst.getName19() == "" || hdlst.getName20() == "" || hdlst.getName21() == "" || hdlst.getName22() == "" || hdlst.getName23() == "" || hdlst.getName24() == "" || hdlst.getName25() == "" || hdlst.getName26() == "" || hdlst.getName27() == "" || hdlst.getName28() == "" || hdlst.getName29() == "" || hdlst.getName30() == "" || hdlst.getName31() == "" || hdlst.getName32() == "" || hdlst.getName33() == "" || hdlst.getName34() == "" || hdlst.getName35() == "" || hdlst.getName36() == ""
				|| hdlst.getName37() == "" || hdlst.getName38() == "" || hdlst.getName39() == "" || hdlst.getName40() == "" || hdlst.getName41() == "" || hdlst.getName42() == "" || hdlst.getName43() == "" || hdlst.getName44() == "" || hdlst.getName45() == "" || hdlst.getName46() == "" || hdlst.getName47() == "" || hdlst.getName48() == "" || hdlst.getName49() == "" || hdlst.getName50() == "" || hdlst.getName51() == "" || hdlst.getName52() == "" || hdlst.getName53() == "" || hdlst.getName54() == "" || hdlst.getName55() == "" || hdlst.getName56() == "" || hdlst.getName57() == "" || hdlst.getName58() == "" || hdlst.getName59() == "" || hdlst.getName60() == "" || hdlst.getName61() == "" || hdlst.getName62() == "" || hdlst.getName63() == "" || hdlst.getName64() == "" || hdlst.getName65() == ""
				|| hdlst.getName66() == "" || hdlst.getName67() == "" || hdlst.getName68() == "" || hdlst.getName69() == "" || hdlst.getName70() == "" || hdlst.getName71() == "" || hdlst.getName72() == "" || hdlst.getName73() == "" || hdlst.getName74() == "" || hdlst.getName75() == "" || hdlst.getName76() == "" || hdlst.getName77() == "" || hdlst.getName78() == "" || hdlst.getName79() == "" || hdlst.getName80() == "" || hdlst.getName81() == "" || hdlst.getName82() == "" || hdlst.getName83() == "" || hdlst.getName84() == "" || hdlst.getName85() == "" || hdlst.getName86() == "" || hdlst.getName87() == "" || hdlst.getName88() == "" || hdlst.getName89() == "" || hdlst.getName90() == "" || hdlst.getName91() == "" || hdlst.getName92() == "" || hdlst.getName93() == "" || hdlst.getName94() == ""
				|| hdlst.getName95() == "" || hdlst.getName96() == "" || hdlst.getName97() == "" || hdlst.getName98() == "" || hdlst.getName99() == "" || hdlst.getName100() == "" || hdlst.getName101() == "" || hdlst.getName102() == "" || hdlst.getName103() == "" || hdlst.getName104() == "" || hdlst.getName105() == "" || hdlst.getName106() == "" || hdlst.getName107() == "" || hdlst.getName108() == "" || hdlst.getName109() == "" || hdlst.getName110() == "" || hdlst.getName111() == "" || hdlst.getName112() == "" || hdlst.getName113() == "" || hdlst.getName114() == "" || hdlst.getName115() == "" || hdlst.getName116() == "" || hdlst.getName117() == "" || hdlst.getName118() == "" || hdlst.getName119() == "" || hdlst.getName120() == "" || hdlst.getName121() == "" || hdlst.getName122() == ""
				|| hdlst.getName123() == "" || hdlst.getName124() == "" || hdlst.getName125() == "" || hdlst.getName126() == "" || hdlst.getName127() == "" || hdlst.getName128() == "" || hdlst.getName129() == "" || hdlst.getName130() == "" || hdlst.getName131() == "" || hdlst.getName132() == "" || hdlst.getName133() == "" || hdlst.getName134() == "" || hdlst.getName135() == "" || hdlst.getName136() == "" || hdlst.getName137() == "" || hdlst.getName138() == "" || hdlst.getName139() == "" || hdlst.getName140() == "" || hdlst.getName141() == "" || hdlst.getName142() == "" || hdlst.getName143() == "" || hdlst.getName144() == "" || hdlst.getName145() == "" || hdlst.getName146() == "" || hdlst.getName147() == "" || hdlst.getName148() == "" || hdlst.getName149() == "" || hdlst.getName150() == ""
				|| hdlst.getName151() == "" || hdlst.getName152() == "" || hdlst.getName153() == "")
		{
		} else
		{
			int countC = 0;
			if (hdlst.getName7() == null || hdlst.getName7() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName7().equals("是"))
				{
					countC += 1;
				} else
				{
					countC += 0;
				}
			}
			if (hdlst.getName19() == null || hdlst.getName19() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName19().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}
			if (hdlst.getName29() == null || hdlst.getName29() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName29().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}
			if (hdlst.getName39() == null || hdlst.getName39() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName39().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}
			if (hdlst.getName41() == null || hdlst.getName41() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName41().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}

			if (hdlst.getName51() == null || hdlst.getName51() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName51().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}

			if (hdlst.getName57() == null || hdlst.getName57() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName57().equals("是"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}

			if (hdlst.getName5() == null || hdlst.getName5() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName5().equals("否"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}
			if (hdlst.getName18() == null || hdlst.getName18() == "")
			{
				countC += 0;
			} else
			{

				if (hdlst.getName18().equals("否"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}

			if (hdlst.getName40() == null || hdlst.getName40() == "")
			{
				countC += 0;
			} else
			{
				if (hdlst.getName40().equals("否"))
				{
					countC = countC + 1;
				} else
				{
					countC += 0;
				}
			}
			System.out.println(countC);
			mapCount.put("C", countC);
			/**
			 * R
			 */
			int countR = 0;
			if (hdlst.getName2() == null || hdlst.getName2() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName2().equals("是"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}
			if (hdlst.getName13() == null || hdlst.getName13() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName13().equals("是"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName22() == null || hdlst.getName22() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName22().equals("是"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName36() == null || hdlst.getName36() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName36().equals("是"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName43() == null || hdlst.getName43() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName43().equals("是"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName14() == null || hdlst.getName14() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName14().equals("否"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName23() == null || hdlst.getName23() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName23().equals("否"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName44() == null || hdlst.getName44() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName44().equals("否"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName47() == null || hdlst.getName47() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName47().equals("否"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}

			if (hdlst.getName48() == null || hdlst.getName48() == "")
			{
				countR += 0;
			} else
			{
				if (hdlst.getName48().equals("否"))
				{
					countR = countR + 1;
				} else
				{
					countR += 0;
				}
			}
			mapCount.put("R", countR);
			System.out.println(countR);
			/**
			 * I
			 */
			int countI = 0;
			if (hdlst.getName6() == null || hdlst.getName6() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName6().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName8() == null || hdlst.getName8() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName8().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName20() == null || hdlst.getName20() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName20().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName30() == null || hdlst.getName30() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName30().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName31() == null || hdlst.getName31() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName31().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName42() == null || hdlst.getName42() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName42().equals("是"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName21() == null || hdlst.getName21() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName21().equals("否"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName55() == null || hdlst.getName55() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName55().equals("否"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName56() == null || hdlst.getName56() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName56().equals("否"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}

			if (hdlst.getName58() == null || hdlst.getName58() == "")
			{
				countI += 0;
			} else
			{
				if (hdlst.getName58().equals("否"))
				{
					countI = countI + 1;
				} else
				{
					countI += 0;
				}
			}
			System.out.println(countI);
			mapCount.put("I", countI);
			/**
			 * E
			 */
			int countE = 0;
			if (hdlst.getName11() == null || hdlst.getName11() == "")
			{
				countE += 0;
			} else
			{
				if (hdlst.getName11().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName24() == null || hdlst.getName24() == "")
			{
				countE += 0;
			} else
			{
				if (hdlst.getName24().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName28() == null || hdlst.getName28() == "")
			{
				countE += 0;
			} else
			{
				if (hdlst.getName28().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName35() == null || hdlst.getName35() == "")
			{
				countE += 0;
			} else
			{

				if (hdlst.getName35().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName38() == null || hdlst.getName38() == "")
			{
				countE += 0;
			} else
			{

				if (hdlst.getName38().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName46() == null || hdlst.getName46() == "")
			{
				countE += 0;
			} else
			{

				if (hdlst.getName46().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName60() == null || hdlst.getName60() == "")
			{
				countE += 0;
			} else
			{

				if (hdlst.getName60().equals("是"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName2() == null || hdlst.getName2() == "")
			{
				countE += 0;
			} else
			{

				if (hdlst.getName2().equals("否"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}

			if (hdlst.getName16() == null || hdlst.getName16() == "")
			{
				countE += 0;
			} else
			{
				if (hdlst.getName16().equals("否"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}

			}

			if (hdlst.getName25() == null || hdlst.getName25() == "")
			{
				countE += 0;
			} else
			{
				if (hdlst.getName25().equals("否"))
				{
					countE = countE + 1;
				} else
				{
					countE += 0;
				}
			}
			System.out.println(countE);
			mapCount.put("E", countE);
			/**
			 * S
			 */
			int countS = 0;

			if (hdlst.getName26() == null || hdlst.getName26() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName26().equals("是"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName37() == null || hdlst.getName37() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName37().equals("是"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}

			}

			if (hdlst.getName52() == null || hdlst.getName52() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName52().equals("是"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName59() == null || hdlst.getName59() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName59().equals("是"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName1() == null || hdlst.getName1() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName1().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName53() == null || hdlst.getName53() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName53().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName12() == null || hdlst.getName12() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName12().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName15() == null || hdlst.getName15() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName15().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName27() == null || hdlst.getName27() == "")
			{
				countS += 0;
			} else
			{
				if (hdlst.getName27().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}

			if (hdlst.getName45() == null || hdlst.getName45() == "")
			{
				countS += 0;
			} else
			{

				if (hdlst.getName45().equals("否"))
				{
					countS = countS + 1;
				} else
				{
					countS += 0;
				}
			}
			System.out.println(countS);
			mapCount.put("S", countS);
			/**
			 * A
			 */
			int countA = 0;
			if (hdlst.getName32() == null || hdlst.getName32() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName32().equals("否"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName4() == null || hdlst.getName4() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName4().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName54() == null || hdlst.getName54() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName54().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName9() == null || hdlst.getName9() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName9().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName10() == null || hdlst.getName10() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName10().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName17() == null || hdlst.getName17() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName17().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName33() == null || hdlst.getName33() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName33().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName34() == null || hdlst.getName34() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName34().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName49() == null || hdlst.getName49() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName49().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}

			if (hdlst.getName50() == null || hdlst.getName50() == "")
			{
				countA += 0;
			} else
			{
				if (hdlst.getName50().equals("是"))
				{
					countA = countA + 1;
				} else
				{
					countA += 0;
				}
			}
			System.out.println(countA);
			mapCount.put("A", countA);
			int J = 0;
			int P = 0;
			int E = 0;
			double I = 0;
			int S = 0;
			int N = 0;
			int T = 0;
			int F = 0;
			if (hdlst.getName61() == null || hdlst.getName61() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName61().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName62() == null || hdlst.getName62() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName62().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName63() == null || hdlst.getName63() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName63().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName64() == null || hdlst.getName64() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName64().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName65() == null || hdlst.getName65() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName65().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName66() == null || hdlst.getName66() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName66().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName67() == null || hdlst.getName67() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName67().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName68() == null || hdlst.getName68() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName68().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName69() == null || hdlst.getName69() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName69().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName70() == null || hdlst.getName70() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName70().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName71() == null || hdlst.getName71() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName71().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName72() == null || hdlst.getName72() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName72().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}

			if (hdlst.getName73() == null || hdlst.getName73() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName73().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}
			if (hdlst.getName74() == null || hdlst.getName74() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName74().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}
			if (hdlst.getName75() == null || hdlst.getName75() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName75().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName76() == null || hdlst.getName76() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName76().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName76() == null || hdlst.getName76() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName77().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName78() == null || hdlst.getName78() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName78().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}

			if (hdlst.getName79() == null || hdlst.getName79() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName79().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName80() == null || hdlst.getName80() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName80().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName81() == null || hdlst.getName81() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName81().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName82() == null || hdlst.getName82() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName82().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}

			if (hdlst.getName83() == null || hdlst.getName83() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName83().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName84() == null || hdlst.getName84() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName84().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}
			if (hdlst.getName85() == null || hdlst.getName85() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName85().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName86() == null || hdlst.getName86() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName86().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName87() == null || hdlst.getName87() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName87().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName88() == null || hdlst.getName88() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName88().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName89() == null || hdlst.getName89() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName89().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName90() == null || hdlst.getName90() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName90().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName91() == null || hdlst.getName91() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName91().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName91() == null || hdlst.getName91() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName92().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName93() == null || hdlst.getName93() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName93().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName94() == null || hdlst.getName94() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName94().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName95() == null || hdlst.getName95() == "")
			{
				E += 0;
				I += 0;
			} else
			{

				if (hdlst.getName95().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}

			if (hdlst.getName96() == null || hdlst.getName96() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName96().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}
			if (hdlst.getName97() == null || hdlst.getName97() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName97().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName98() == null || hdlst.getName98() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName98().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName99() == null || hdlst.getName99() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName99().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName100() == null || hdlst.getName100() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName100().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName101() == null || hdlst.getName101() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName101().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}
			if (hdlst.getName102() == null || hdlst.getName102() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName102().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName103() == null || hdlst.getName103() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName103().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}
			if (hdlst.getName104() == null || hdlst.getName104() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName104().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName105() == null || hdlst.getName105() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName105().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}
			if (hdlst.getName106() == null || hdlst.getName106() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName106().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName107() == null || hdlst.getName107() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName107().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName108() == null || hdlst.getName108() == "")
			{
				I += 0;
				N += 0;
			} else
			{
				if (hdlst.getName108().equals("I"))
				{
					I = I + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName109() == null || hdlst.getName109() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName109().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName110() == null || hdlst.getName110() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName110().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName111() == null || hdlst.getName111() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName111().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName112() == null || hdlst.getName112() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName112().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}
			if (hdlst.getName113() == null || hdlst.getName113() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName113().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName114() == null || hdlst.getName114() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName114().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName115() == null || hdlst.getName115() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName115().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName116() == null || hdlst.getName116() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName116().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName117() == null || hdlst.getName117() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName117().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName118() == null || hdlst.getName118() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName118().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName119() == null || hdlst.getName119() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName119().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName120() == null || hdlst.getName120() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName120().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}

			if (hdlst.getName121() == null || hdlst.getName121() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName121().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName122() == null || hdlst.getName122() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName122().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName123() == null || hdlst.getName123() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName123().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName124() == null || hdlst.getName124() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName124().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName125() == null || hdlst.getName125() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName125().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName126() == null || hdlst.getName126() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName126().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName127() == null || hdlst.getName127() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName127().equals("E"))
				{
					E = E + 1;
				} else
				{
					I = I + 1;
				}
			}
			if (hdlst.getName128() == null || hdlst.getName128() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName128().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName129() == null || hdlst.getName129() == "")
			{
				T += 0;
				F += 0;
			} else
			{
				if (hdlst.getName129().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName130() == null || hdlst.getName130() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName130().equals("J"))
				{
					J = J + 1;
				} else
					P = P + 1;
			}

			if (hdlst.getName131() == null || hdlst.getName131() == "")
			{
				J += 0;
				P += 0;
			} else
			{
				if (hdlst.getName131().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}
			if (hdlst.getName132() == null || hdlst.getName132() == "")
			{
				I += 0;
				E += 0;
			} else
			{
				if (hdlst.getName132().equals("I"))
				{
					I = I + 1;
				} else
					E = E + 1;
			}
			if (hdlst.getName133() == null || hdlst.getName133() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName133().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName134() == null || hdlst.getName134() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName134().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName135() == null || hdlst.getName135() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName135().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName136() == null || hdlst.getName136() == "")
			{
				P += 0;
				J += 0;
			} else
			{
				if (hdlst.getName136().equals("P"))
				{
					P = P + 1;
				} else
					J = J + 1;
			}

			if (hdlst.getName137() == null || hdlst.getName137() == "")
			{
				E += 0;
				I += 0;
			} else
			{
				if (hdlst.getName137().equals("E"))
				{
					E = E + 1;
				} else
					I = I + 1;
			}

			if (hdlst.getName138() == null || hdlst.getName138() == "")
			{
				T += 0;
				F += 0;
			} else
			{
				if (hdlst.getName138().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName139() == null || hdlst.getName139() == "")
			{
				N += 0;
				S += 0;
			} else
			{
				if (hdlst.getName139().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName140() == null || hdlst.getName140() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName140().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName141() == null || hdlst.getName141() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName141().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName142() == null || hdlst.getName142() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName142().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName143() == null || hdlst.getName143() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName143().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName144() == null || hdlst.getName144() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName144().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName145() == null || hdlst.getName145() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName145().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName146() == null || hdlst.getName146() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName146().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			if (hdlst.getName147() == null || hdlst.getName147() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName147().equals("N"))
				{
					N = N + 1;
				} else
					S = S + 1;
			}

			if (hdlst.getName148() == null || hdlst.getName148() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName148().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName149() == null || hdlst.getName149() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName149().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName150() == null || hdlst.getName150() == "")
			{
				S += 0;
				T += 0;
			} else
			{
				if (hdlst.getName150().equals("S"))
				{
					S = S + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName151() == null || hdlst.getName151() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName151().equals("F"))
				{
					F = F + 1;
				} else
					T = T + 1;
			}

			if (hdlst.getName152() == null || hdlst.getName152() == "")
			{
				F += 0;
				T += 0;
			} else
			{
				if (hdlst.getName152().equals("T"))
				{
					T = T + 1;
				} else
					F = F + 1;
			}

			if (hdlst.getName153() == null || hdlst.getName153() == "")
			{
				S += 0;
				N += 0;
			} else
			{
				if (hdlst.getName153().equals("S"))
				{
					S = S + 1;
				} else
					N = N + 1;
			}

			Gxcs gxcs = new Gxcs();
			Bmgl bmgl = (Bmgl) session.getAttribute("cpglLoginSession1");
			gxcs.setGxcsKsxm(bmgl.getBmglKsxm());
			gxcs.setGxcsId(RandomGUIDUtil.generateKey());
			gxcs.setGxcsNeixiang((int) I);
			gxcs.setGxcsChuantongxing(countC);
			gxcs.setGxcsPanduan(J);
			gxcs.setGxcsQinggan(F);
			gxcs.setGxcsQiyexing(countE);
			gxcs.setGxcsShehuixing(countS);
			gxcs.setGxcsShigan(S);
			gxcs.setGxcsRenzhi(P);
			gxcs.setGxcsSikao(T);
			gxcs.setGxcsWaixiang(E);
			gxcs.setGxcsXianshixing(countR);
			gxcs.setGxcsYanjiuxing(countI);
			gxcs.setGxcsYishuxing(countA);
			gxcs.setGxcsZhijue(N);
			gxcs.setGxcsSfzh(bmgl.getBmglSfzh());
			tkglService.insertGxcs(gxcs);
		}

		return "cpgl/successSubmit2";
	}

	@RequestMapping(value = "/ShowCharts", method = RequestMethod.GET)
	public ModelAndView ShowCharts()
	{
		return new ModelAndView("Tkgl/ShowCharts");
	}

	@ResponseBody
	@RequestMapping(value = "/queryByGxcsId", method = RequestMethod.POST)
	public Gxcs queryByGxcsId(String sfzh, String kslx)
	{
		logInfo("queryByGxcsId", sfzh);
		Gxcs gxcs = tkglService.queryBySfzh(sfzh);
		return gxcs;
	}

	@ResponseBody
	@RequestMapping(value = "/checkGxcs", method = RequestMethod.POST)
	public boolean checkGxcs(String bmglSfzh, String bmglKsxm)
	{
		logInfo("checkGxcs", bmglSfzh + "," + bmglKsxm);
		System.out.println("身份证号：" + bmglSfzh + "姓名：" + bmglKsxm);
		int kscjJsgCount = ksxcglService.checkKscjJsg(bmglSfzh);
		int kscjGlgCount = ksxcglService.checkKscjGlg(bmglSfzh);

		int gxcsCount = tkglService.checkGxcsCount(bmglSfzh);
		if ((kscjJsgCount > 0 || kscjGlgCount > 0) && gxcsCount <= 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

}

/**
 * 移动端接口 2014-06-27 孙建国
 */
package com.ttgis.recruit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Article;
import com.ttgis.recruit.domain.ArticleReview;
import com.ttgis.recruit.domain.Bbs;
import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.InterfaceLoginResult;
import com.ttgis.recruit.domain.InterfaceResult;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.Ltbk;
import com.ttgis.recruit.domain.Msq;
import com.ttgis.recruit.domain.Msqgl_detailed;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.Position;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.QueryBmgl;
import com.ttgis.recruit.domain.QueryArticle;
import com.ttgis.recruit.domain.QueryBbs;
import com.ttgis.recruit.domain.QueryJtjlk;
import com.ttgis.recruit.domain.QueryKcgl;
import com.ttgis.recruit.domain.QueryMsq;
import com.ttgis.recruit.domain.QueryReview;
import com.ttgis.recruit.domain.QueryXqqz;
import com.ttgis.recruit.domain.QueryXxtz;
import com.ttgis.recruit.domain.QueryZw;
import com.ttgis.recruit.domain.Reply;
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
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Tlq;
import com.ttgis.recruit.domain.Txl;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.XmlEntity;
import com.ttgis.recruit.domain.Xqqz;
import com.ttgis.recruit.domain.XqqzReview;
import com.ttgis.recruit.domain.Xxtz;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.domain.ZzjgCondition;
import com.ttgis.recruit.service.ArticleReviewService;
import com.ttgis.recruit.service.ArticleService;
import com.ttgis.recruit.service.BbsService;
import com.ttgis.recruit.service.BmglService;
import com.ttgis.recruit.service.CollectionService;
import com.ttgis.recruit.service.JtjlkService;
import com.ttgis.recruit.service.KcglService;
import com.ttgis.recruit.service.LtbkService;
import com.ttgis.recruit.service.MsjgService;
import com.ttgis.recruit.service.MsqService;
import com.ttgis.recruit.service.ReplyService;
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
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.service.TlqService;
import com.ttgis.recruit.service.TxlService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XqqzReviewService;
import com.ttgis.recruit.service.XqqzService;
import com.ttgis.recruit.service.XxtzService;
import com.ttgis.recruit.service.ZpxwService;
import com.ttgis.recruit.service.ZwglService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.MD5;
import com.ttgis.recruit.utility.MailSenderInfo;
import com.ttgis.recruit.utility.SendEmail;
import com.ttgis.recruit.utility.SendMessage;
import com.ttgis.recruit.utility.encrypt.Base64;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author SJG
 * 
 */
@Controller
@RequestMapping(value = "/Mobile")
public class InterfaceController
{
	@Resource
	ZpxwService zpxwService;
	@Resource
	ZwglService zwglService;
	@Resource
	JtjlkService jtjlkService;
	@Resource
	CollectionService collectionService;
	@Resource
	UserService userService;
	@Resource
	XxtzService xxtzService;
	@Resource
	UserService yhbservice;
	@Resource
	ZzjgService zzjgService;
	@Resource
	MsjgService msjgService;
	@Resource
	ResumeService resumeService;
	@Resource
	MsqService msqService;
	@Resource
	TxlService txlService;
	@Resource
	KcglService kcglService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LtbkService ltbkService;
	@Autowired
	private TlqService tlqService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private XqqzService xqqzService;
	@Autowired
	private ArticleReviewService articleReviewService;
	@Autowired
	private XqqzReviewService xqqzReviewService;
	@Autowired
	private Resume_jyjlService resume_jyjlService;
	@Autowired
	private Resume_xnjlService resume_xnjlService;
	@Autowired
	private Resume_xnzwService resume_xnzwService;
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
	private Resume_zsService resume_zsService;
	@Autowired
	private Resume_ITjnService resume_itjnService;
	@Autowired
	private Resume_fjService resume_fjService;
	@Autowired
	private BmglService bmglService;
	@Autowired
	private ReplyService replyService;

	static Logger log = Logger.getLogger(InterfaceController.class);
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
		if (_session.getAttribute("IUserId") != null)
			stringBuilder.append(_session.getAttribute("IUserId"));
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

	InterfaceResult interfaceResult = new InterfaceResult();

	public String GetGids(ServletContext context, String type, String sessionId)
	{
		String gids = "";
		if (context.getAttribute("gids") == null || context.getAttribute("gids").equals(null))
			return "";

		gids = context.getAttribute("gids").toString();
		String[] strArr = gids.split(",");
		for (int i = 0; i < strArr.length; i++)
		{
			String str = Base64.getFromBase64(strArr[i]);
			if (str.indexOf(sessionId) != -1)
			{
				String[] strReturnArr = str.split(",");
				if (strReturnArr.length == 3)
				{
					if (type.equals("0"))
						return strReturnArr[0];
				}
			}
		}
		return "";
	}

	@RequestMapping(value = "/InterfaceTest", method = RequestMethod.GET)
	public ModelAndView Mobile()
	{
		return new ModelAndView("Mobile/InterfaceTest");
	}

	/**
	 * 查询招聘日程
	 * 
	 * @param strZzjgId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/GetZprc", method = RequestMethod.POST)
	public InterfaceResult GetZprc(String sessionId, ZzjgCondition p, HttpServletRequest req)
	{
		logInfo("GetZprc", JSONArray.fromObject(p).toString());
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			p.setSFZPRC("是");
			int intCount = zpxwService.selectCount(p);
			p.setCurrentPageSize(intCount < p.getPageNum() * p.getPageSize() ? intCount - (p.getPageNum() * p.getPageSize() - p.getPageSize()) : p.getPageSize());

			List<Zpxw> zpxws = zpxwService.selectByWhere(p);
			interfaceResult.setRowCount(intCount);
			List<Zpxw> resultZpxws = new ArrayList<Zpxw>();
			for (Zpxw zpxw : zpxws)
			{
				String str = zpxw.getZpxwContent().replaceAll("\"", "");
				Document doc = Jsoup.parse(str);
				Elements trs = doc.select("table").select("tr");
				String jsonBody = "{\"" + "contentValue" + "\":[";
				JSONObject jsonObject = new JSONObject();
				if (trs.size() > 0)
				{
					ArrayList td = geTr(trs, 0);

					String jsonK = "{";
					for (int k = 0; k < td.size(); k++)
					{
						jsonK += "\"" + td.get(k) + "\":\"" + "1234567890_" + k + "\"";
						if (k != td.size() - 1)
							jsonK += ",";
					}
					jsonK += "}";
					for (int i = 1; i < trs.size(); i++)
					{
						String jsonQ = jsonK;
						ArrayList tmp = geTr(trs, i);
						for (int k = 0; k < td.size(); k++)
						{
							try
							{
								jsonObject.put(td.get(k), tmp.get(k).toString());
							} catch (Exception e)
							{
								jsonObject.put(td.get(k), "");
							}
						}
						for (int j = 0; j < tmp.size(); j++)
						{
							jsonQ = jsonQ.replaceAll("1234567890_" + j, tmp.get(j).toString());
						}
						if (i != trs.size() - 1)
							jsonQ += ",";
						jsonBody += jsonQ;
					}
					// System.out.println(jsonBody);
				}
				jsonBody += "]}";
				zpxw.setZpxwContent(jsonBody);
				// JSONObject node = JSONObject.fromObject(jsonBody);
				// JSONArray jsons =
				// JSONArray.fromObject(node.get("contentValue"));
				// for (Object o : jsons)
				// {
				// JSONObject jsonNode = JSONObject.fromObject(o);
				// System.out.println(jsonNode.getString("学校"));
				// }
				resultZpxws.add(zpxw);
			}
			interfaceResult.setCode("10000");
			if (resultZpxws.size() > 0)
				interfaceResult.setMessage("ok");
			else
				interfaceResult.setMessage("no");
			interfaceResult.setResult(resultZpxws);
		} catch (Exception e)
		{
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
			logInfo("GetZprc", JSONArray.fromObject(p).toString() + "******Error");
		}
		return interfaceResult;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/GetZprcIos", method = RequestMethod.POST)
	public InterfaceResult GetZprcIos(String sessionId, ZzjgCondition p, HttpServletRequest req)
	{
		logInfo("GetZprc", JSONArray.fromObject(p).toString());
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			p.setSFZPRC("是");
			int intCount = zpxwService.selectCount(p);
			p.setCurrentPageSize(intCount < p.getPageNum() * p.getPageSize() ? intCount - (p.getPageNum() * p.getPageSize() - p.getPageSize()) : p.getPageSize());
			List<Zpxw> zpxws = zpxwService.selectByWhere(p);
			interfaceResult.setRowCount(intCount);
			List<Zpxw> resultZpxws = new ArrayList<Zpxw>();
			for (Zpxw zpxw : zpxws)
			{
				String str = zpxw.getZpxwContent().replaceAll("\"", "").replaceAll("学校", "School").replaceAll("宣讲会时间", "TalkTime").replaceAll("宣讲会地点", "TalkAddress").replaceAll("招聘会时间", "RecruitTime").replaceAll("招聘会地点", "RecruitAddress");

				Document doc = Jsoup.parse(str);
				Elements trs = doc.select("table").select("tr");
				String jsonBody = "[";
				JSONObject jsonObject = new JSONObject();
				if (trs.size() > 0)
				{
					ArrayList td = geTr(trs, 0);

					String jsonK = "{";
					for (int k = 0; k < td.size(); k++)
					{
						jsonK += "" + td.get(k) + ":\"" + "1234567890_" + k + "\"";
						if (k != td.size() - 1)
							jsonK += ",";
					}
					jsonK += "}";
					for (int i = 1; i < trs.size(); i++)
					{
						String jsonQ = jsonK;
						ArrayList tmp = geTr(trs, i);
						for (int k = 0; k < td.size(); k++)
						{
							try
							{
								jsonObject.put(td.get(k), tmp.get(k).toString());
							} catch (Exception e)
							{
								jsonObject.put(td.get(k), "");
							}
						}
						for (int j = 0; j < tmp.size(); j++)
						{
							jsonQ = jsonQ.replaceAll("1234567890_" + j, tmp.get(j).toString());
						}
						if (i != trs.size() - 1)
							jsonQ += ",";
						jsonBody += jsonQ;
					}
					// System.out.println(jsonBody);
				}
				jsonBody += "]";
				zpxw.setZpxwContent(jsonBody);
				// JSONObject node = JSONObject.fromObject(jsonBody);
				// JSONArray jsons =
				// JSONArray.fromObject(node.get("contentValue"));
				// for (Object o : jsons)
				// {
				// JSONObject jsonNode = JSONObject.fromObject(o);
				// System.out.println(jsonNode.getString("学校"));
				// }
				resultZpxws.add(zpxw);
			}
			interfaceResult.setCode("10000");
			if (resultZpxws.size() > 0)
				interfaceResult.setMessage("ok");
			else
				interfaceResult.setMessage("no");
			interfaceResult.setResult(resultZpxws);
		} catch (Exception e)
		{
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
			logInfo("GetZprc", JSONArray.fromObject(p).toString() + "******Error");
		}
		return interfaceResult;
	}

	/**
	 * 查询招聘日程
	 * 
	 * @param strZzjgId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/GetZprcContent", method = RequestMethod.POST)
	public InterfaceResult GetZprcContent(String sessionId, String zpxwId, HttpServletRequest req)
	{
		logInfo("zpxwId", zpxwId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Zpxw zpxw = zpxwService.selectByPrimaryKey(zpxwId);
			JSONArray jr = new JSONArray();
			String str = zpxw.getZpxwContent().replaceAll("\"", "").replaceAll("学校", "School").replaceAll("宣讲会时间", "TalkTime").replaceAll("宣讲会地点", "TalkAddress").replaceAll("招聘会时间", "RecruitTime").replaceAll("招聘会地点", "RecruitAddress");
			Document doc = Jsoup.parse(str);
			Elements trs = doc.select("table").select("tr");
			JSONObject jsonObject = new JSONObject();
			int intContentCount = 0;
			if (trs.size() > 0)
			{
				ArrayList td = geTr(trs, 0);
				for (int i = 1; i < trs.size(); i++)
				{
					ArrayList tmp = geTr(trs, i);
					for (int k = 0; k < td.size(); k++)
					{
						try
						{
							jsonObject.put(td.get(k), tmp.get(k).toString());
						} catch (Exception e)
						{
							jsonObject.put(td.get(k), "");
						}
					}
					intContentCount += 1;
					jr.add(i - 1, jsonObject);
				}
			}
			interfaceResult.setCode("10000");
			if (intContentCount > 0)
				interfaceResult.setMessage("ok");
			else
				interfaceResult.setMessage("no");
			interfaceResult.setResult(jr);
			interfaceResult.setRowCount(intContentCount);
		} catch (Exception e)
		{
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
			logInfo("GetZprc", zpxwId + "******Error");
		}
		return interfaceResult;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	ArrayList geTr(Elements elements, int trIndex)
	{
		if (elements.size() < trIndex)
			return new ArrayList();
		ArrayList al = new ArrayList();
		Elements tds = elements.get(trIndex).select("td");
		for (int i = 0; i < tds.size(); i++)
		{
			tds = elements.get(trIndex).select("td");
			al.add(tds.get(i).text());
		}
		return al;
	}

	/**
	 * 用户登录
	 * 
	 * @param txtUserName
	 * @param txtPassword
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/CheckLogin", method = RequestMethod.POST)
	public InterfaceLoginResult CheckLogin(String strUserName, String strPassword, HttpServletRequest req)
	{
		logInfo("CheckLogin", strUserName + "," + strPassword);
		InterfaceLoginResult interfaceLoginResult = new InterfaceLoginResult();
		String pwdMd5 = MD5.getMD5ofStr(strPassword);// 加密后的密码
		interfaceResult = CheckManageLogin(strUserName, pwdMd5, req);
		if (interfaceResult.getMessage().indexOf("Failed") == -1)
		{
			interfaceLoginResult.setCode(interfaceResult.getCode());
			interfaceLoginResult.setMessage("Success");
			ModelAndView mv = new ModelAndView();
			mv.addObject("sessionId", interfaceResult.getMessage());
			mv.addObject("user", interfaceResult.getResult());
			interfaceLoginResult.setResult(mv.getModel());
			interfaceLoginResult.setUserType(0);
			return interfaceLoginResult;
		}
		interfaceResult = CheckMsgLogin(strUserName, strPassword, req);
		if (interfaceResult.getMessage().indexOf("Failed") == -1)
		{
			interfaceLoginResult.setCode(interfaceResult.getCode());
			interfaceLoginResult.setMessage("Success");
			ModelAndView mv = new ModelAndView();
			mv.addObject("sessionId", interfaceResult.getMessage());
			mv.addObject("user", interfaceResult.getResult());
			interfaceLoginResult.setResult(mv.getModel());
			interfaceLoginResult.setUserType(2);
			return interfaceLoginResult;
		}
		interfaceResult = CheckUserLogin(strUserName, pwdMd5, req);
		if (interfaceResult.getMessage().indexOf("Failed") == -1)
		{
			interfaceLoginResult.setCode(interfaceResult.getCode());
			interfaceLoginResult.setMessage("Success");
			ModelAndView mv = new ModelAndView();
			mv.addObject("sessionId", interfaceResult.getMessage());
			mv.addObject("user", interfaceResult.getResult());
			interfaceLoginResult.setResult(mv.getModel());
			interfaceLoginResult.setUserType(1);
			return interfaceLoginResult;
		}

		interfaceLoginResult.setCode("10000");
		interfaceLoginResult.setMessage("用户名或密码错误!");
		ModelAndView mv = new ModelAndView();
		mv.addObject("sessionId", null);
		mv.addObject("user", null);
		interfaceLoginResult.setResult(mv.getModel());
		interfaceLoginResult.setUserType(-1);
		return interfaceLoginResult;
	}

	public InterfaceResult CheckUserLogin(String strUserName, String pwdMd5, HttpServletRequest req)
	{
		Userinfo userinfo = new Userinfo();
		userinfo.setUserIdcard(strUserName);
		userinfo.setUserPassword(pwdMd5);
		Userinfo loginUser = yhbservice.CheckLogin(userinfo);
		if (loginUser == null)
		{
			interfaceResult.setMessage("Failed");
			interfaceResult.setResult(null);
		} else
		{
			String strUid = loginUser.getUserId();
			String strIp = req.getRemoteHost();
			String strSid = req.getSession().getId();
			String strNewId = strUid + "," + strIp + "," + strSid;
			strNewId = Base64.getBase64(strNewId);
			req.getSession().setAttribute("MobileLogin_User", loginUser);
			req.getSession().setAttribute("IUserId", strUid);
			ServletContext sc = req.getSession().getServletContext();

			// 登陆后sessionid放到全局变量里
			if (sc.getAttribute("gids") == null || sc.getAttribute("gids").equals(null))
				sc.setAttribute("gids", "");

			String gids = sc.getAttribute("gids").toString();

			if (gids.equals(""))
			{
				sc.setAttribute("gids", strNewId);
			} else
			{
				gids = gids.replaceAll(gids, strNewId);
				gids = gids.replaceAll(gids, "," + strNewId);
				sc.setAttribute("gids", gids + "," + strNewId);
			}

			interfaceResult.setMessage(strSid);
			interfaceResult.setResult(loginUser);
		}
		interfaceResult.setCode("10000");
		return interfaceResult;
	}

	public InterfaceResult CheckMsgLogin(String strUserName, String pwdMd5, HttpServletRequest req)
	{
		Userinfo userinfo = new Userinfo();
		userinfo.setUserIdcard(strUserName);
		userinfo.setUserPassword(pwdMd5);
		userinfo.setUserSfls("是");
		Userinfo loginUser = userService.CheckLogin(userinfo);
		if (loginUser == null)
		{
			interfaceResult.setMessage("Failed");
			interfaceResult.setResult(null);
		} else
		{
			String strUid = loginUser.getUserId();
			String strIp = req.getRemoteHost();
			String strSid = req.getSession().getId();
			String strNewId = strUid + "," + strIp + "," + strSid;
			strNewId = Base64.getBase64(strNewId);
			req.getSession().setAttribute("MobileLogin_Msg", loginUser);
			req.getSession().setAttribute("IUserId", strUid);
			ServletContext sc = req.getSession().getServletContext();

			// 登陆后sessionid放到全局变量里
			if (sc.getAttribute("gids") == null || sc.getAttribute("gids").equals(null))
				sc.setAttribute("gids", "");

			String gids = sc.getAttribute("gids").toString();

			if (gids.equals(""))
			{
				sc.setAttribute("gids", strNewId);
			} else
			{
				gids = gids.replaceAll(gids, strNewId);
				gids = gids.replaceAll(gids, "," + strNewId);
				sc.setAttribute("gids", gids + "," + strNewId);
			}

			interfaceResult.setMessage(strSid);
			interfaceResult.setResult(loginUser);
		}
		interfaceResult.setCode("10000");
		return interfaceResult;
	}

	public InterfaceResult CheckManageLogin(String strUserName, String pwdMd5, HttpServletRequest req)
	{
		Zzjg zzjg = new Zzjg();
		zzjg.setZzjgDwzh(strUserName);
		zzjg.setZzjgZhmm(pwdMd5);
		Zzjg loginZzjg = zzjgService.CheckLogin(zzjg);
		if (loginZzjg == null)
		{
			interfaceResult.setMessage("Failed");
			interfaceResult.setResult(null);
		} else
		{
			String strUid = loginZzjg.getZzjgId();
			String strIp = req.getRemoteHost();
			String strSid = req.getSession().getId();
			String strNewId = strUid + "," + strIp + "," + strSid;
			strNewId = Base64.getBase64(strNewId);
			req.getSession().setAttribute("MobileLogin_Manage", loginZzjg);
			req.getSession().setAttribute("IUserId", strUid);
			ServletContext sc = req.getSession().getServletContext();

			// 登陆后sessionid放到全局变量里
			if (sc.getAttribute("gids") == null || sc.getAttribute("gids").equals(null))
				sc.setAttribute("gids", "");

			String gids = sc.getAttribute("gids").toString();

			if (gids.equals(""))
			{
				sc.setAttribute("gids", strNewId);
			} else
			{
				gids = gids.replaceAll(gids, strNewId);
				gids = gids.replaceAll(gids, "," + strNewId);
				sc.setAttribute("gids", gids + "," + strNewId);
			}

			if (loginZzjg.getZzjgSjdw().equals(""))
			{
				loginZzjg.setZzjgLevel(1);
			} else if (loginZzjg.getZzjgSjdw().equals("test001"))
			{
				loginZzjg.setZzjgLevel(2);
			} else
			{
				loginZzjg.setZzjgLevel(3);
			}

			interfaceResult.setMessage(strSid);
			interfaceResult.setResult(loginZzjg);
		}
		interfaceResult.setCode("10000");
		return interfaceResult;
	}

	/**
	 * 查询职位
	 * 
	 * @param name
	 * @param address
	 * @param type
	 * @param dt
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/SearchPosition", method = RequestMethod.POST)
	public InterfaceResult SearchPosition(String sessionId, QueryZw queryZw, HttpServletRequest req)
	{
		logInfo("SearchPosition", JSONArray.fromObject(queryZw).toString());
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		int intCount = zwglService.selectCount(queryZw);
		queryZw.setCurrentPageSize(intCount < queryZw.getPageNum() * queryZw.getPageSize() ? intCount - (queryZw.getPageNum() * queryZw.getPageSize() - queryZw.getPageSize()) : queryZw.getPageSize());
		List<PositionWithBLOBs> positions = zwglService.selectByWhere(queryZw);
		interfaceResult.setRowCount(intCount);
		if (positions.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(positions);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 申请职位
	 * 
	 * @param strUserId
	 * @param strPositionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ApplyPosition", method = RequestMethod.POST)
	public InterfaceResult ApplyPosition(String sessionId, String strUserId, String strPositionId, HttpServletRequest req)
	{
		logInfo("ApplyPosition", strUserId + "," + strPositionId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		Userinfo userinfo = userService.selectByPrimaryKey(strUserId);
		Map<String, String> params = new HashMap<String, String>();
		params.put("positionId", strPositionId);
		params.put("userId", strUserId);
		// 如果在已收藏职位列表里,删除
		collectionService.deleteByPrimaryKey(params);
		// 已经在该单位已经报名了
		if (jtjlkService.selectIsExist(params).size() > 0)
		{
			interfaceResult.setMessage("已经在该单位已经报名了!");
			interfaceResult.setCode("10013");
			interfaceResult.setResult("Failed");
			return interfaceResult;
		}

		Map<String, String> paramsMore = new HashMap<String, String>();
		paramsMore.put("userId", strUserId);
		// 不允许同时投递简历数量超过5家企业
		if (jtjlkService.selectIsExist(paramsMore).size() >= 5)
		{
			interfaceResult.setMessage("不允许同时投递简历数量超过5家企业!");
			interfaceResult.setCode("10013");
			interfaceResult.setResult("Failed");
			return interfaceResult;
		}
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(strUserId);
		// 没填写简历
		if (resume == null)
		{
			interfaceResult.setMessage("简历信息不完整!");
			interfaceResult.setCode("10013");
			interfaceResult.setResult("Failed");
			return interfaceResult;
		}
		List<Resume_jyjl> resume_Jyjls = resume_jyjlService.getlistResumeJyjlByResumeId(resume.getResumeId());
		Resume_jyjl resume_Jyjl = new Resume_jyjl();
		// 没填写教育经历
		if (resume_Jyjls.size() == 0)
		{
			interfaceResult.setMessage("简历信息不完整!");
			interfaceResult.setCode("10013");
			interfaceResult.setResult("Failed");
			return interfaceResult;
		} else
		{
			resume_Jyjl = resume_Jyjls.get(resume_Jyjls.size() - 1);
		}

		Position position = zwglService.selectByPrimaryKey(strPositionId);
		Jtjlk jtjlk = new Jtjlk();
		jtjlk.setJtjlkAddtime(new Date());
		jtjlk.setJtjlkByxx(resume_Jyjl.getResumeXxmc());
		jtjlk.setJtjlkCpcj("未通知");
		jtjlk.setJtjlkDelflag((long) 1);
		jtjlk.setJtjlkGwlb(position.getPositionType());
		jtjlk.setJtjlkId(RandomGUIDUtil.generateKey());
		jtjlk.setJtjlkJlzt("未筛选");
		jtjlk.setJtjlkCpjg("未测评");
		jtjlk.setJtjlkMszt("未安排面试");
		jtjlk.setJtjlkName(userinfo.getUserName());
		jtjlk.setJtjlkPositionId(position.getPositionId());
		jtjlk.setJtjlkSex(resume.getResumeSex());
		jtjlk.setJtjlkUserid(userinfo.getUserId());
		jtjlk.setJtjlkXl(resume_Jyjl.getResumeXl());
		jtjlk.setJtjlkZw(position.getPositionName());
		jtjlk.setJtjlkZy(resume_Jyjl.getResumeZy());
		jtjlk.setZzjgId(position.getZzjgId());
		jtjlk.setJtjlkLy("应聘者");
		jtjlk.setJtjlkHkd(resume.getResumeRxqhkszcsProvince() + "," + resume.getResumeRxqhkszcsCity());
		jtjlk.setJtjlkYxpm(resume_Jyjl.getResumeYxpm());
		jtjlk.setJtjlkBjpm(resume_Jyjl.getResumeBjpm());
		jtjlk.setJtjlkCsrq(resume.getResumeBirthday());
		jtjlk.setJtjlkSfzh(userinfo.getUserSfzh());
		List<Jtjlk> seList = jtjlkService.selectStatusByUserId(userinfo.getUserId());
		if (seList.size() == 0)
			jtjlk.setJtjlkJtjlkflag(2);
		else
			jtjlk.setJtjlkJtjlkflag(1);
		jtjlkService.insertSelective(jtjlk);
		interfaceResult.setCode("10000");
		interfaceResult.setMessage("ok");
		interfaceResult.setResult("Success");
		return interfaceResult;
	}

	/**
	 * 查询申请状态
	 * 
	 * @param strUserId
	 * @param strPositionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetResumeStatus", method = RequestMethod.POST)
	public InterfaceResult GetResumeStatus(String sessionId, String strUserId, HttpServletRequest req)
	{
		logInfo("GetResumeStatus", strUserId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Jtjlk> jtjlks = jtjlkService.selectStatusByUserId(strUserId);
		interfaceResult.setCode("10000");
		interfaceResult.setMessage("ok");
		if (jtjlks.size() > 0)
		{
			interfaceResult.setResult(jtjlks);
		} else
		{
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 查询职位申请
	 * 
	 * @param strZzjgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetAllResume", method = RequestMethod.POST)
	public InterfaceResult GetAllResume(String sessionId, QueryJtjlk queryJtjlk, String strZzjgId, HttpServletRequest req)
	{
		logInfo("GetAllResume", JSONArray.fromObject(queryJtjlk).toString() + "------" + strZzjgId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			queryJtjlk.setZzjgId(strZzjgId);
			int intCount = jtjlkService.selectCount(queryJtjlk);
			queryJtjlk.setCurrentPageSize(intCount < queryJtjlk.getPageNum() * queryJtjlk.getPageSize() ? intCount - (queryJtjlk.getPageNum() * queryJtjlk.getPageSize() - queryJtjlk.getPageSize()) : queryJtjlk.getPageSize());
			List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
			interfaceResult.setRowCount(intCount);
			if (jtjlks.size() > 0)
			{
				interfaceResult.setMessage("ok");
				interfaceResult.setResult(jtjlks);
			} else
			{
				interfaceResult.setMessage("no");
				interfaceResult.setResult(null);
			}
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("GetAllResume", JSONArray.fromObject(queryJtjlk).toString() + "------" + strZzjgId + "******Error");
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 搜索简历
	 * 
	 * @param queryJtjlk
	 * @param strZzjgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetResume", method = RequestMethod.POST)
	public InterfaceResult GetResume(String sessionId, QueryJtjlk queryJtjlk, HttpServletRequest req)
	{
		logInfo("GetResume", JSONArray.fromObject(queryJtjlk).toString());
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			queryJtjlk.setJtjlkJtjlkflag(2);
			queryJtjlk.setJtjlkLy("应聘者");
			int intCount = jtjlkService.selectCount(queryJtjlk);
			queryJtjlk.setCurrentPageSize(intCount < queryJtjlk.getPageNum() * queryJtjlk.getPageSize() ? intCount - (queryJtjlk.getPageNum() * queryJtjlk.getPageSize() - queryJtjlk.getPageSize()) : queryJtjlk.getPageSize());
			List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
			interfaceResult.setRowCount(intCount);
			if (jtjlks.size() > 0)
			{
				interfaceResult.setMessage("ok");
				interfaceResult.setResult(jtjlks);
			} else
			{
				interfaceResult.setMessage("no");
				interfaceResult.setResult(null);
			}
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("GetResume", JSONArray.fromObject(queryJtjlk).toString() + "******Error");
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 收藏简历
	 * 
	 * @param strZzjgId
	 * @param strResumeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/CollectionJtjlk", method = RequestMethod.POST)
	public InterfaceResult CollectionJtjlk(String sessionId, String strZzjgId, String strResumeId, HttpServletRequest req)
	{
		logInfo("CollectionJtjlk", strZzjgId + "," + strResumeId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(strResumeId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", jtjlk.getJtjlkUserid());
		map.put("zzjgId", strZzjgId);
		List<Jtjlk> jtjlks = jtjlkService.selectCanCollection(map);
		if (jtjlks.size() == 0)
		{
			jtjlk.setJtjlkOldid(jtjlk.getJtjlkId());
			jtjlk.setJtjlkZw("");
			jtjlk.setJtjlkId(RandomGUIDUtil.generateKey());
			jtjlk.setZzjgId(strZzjgId);
			jtjlk.setJtjlkJlzt("收藏");
			jtjlk.setJtjlkMszt("未安排面试");
			jtjlk.setJtjlkCpcj("未通知");
			jtjlk.setJtjlkCpjg("未测评");
			jtjlk.setJtjlkApcpgwlb(null);
			jtjlk.setJtjlkAddtime(new Date());
			jtjlk.setJtjlkPositionId("");
			jtjlk.setJtjlkGwlb("");
			jtjlk.setJtjlkLy("HR");
			jtjlk.setJtjlkZzlygw("");
			jtjlk.setJtjlkZzlygwId("");
			jtjlkService.insertSelective(jtjlk);
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult("Success");
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("该简历已在流程中");
			interfaceResult.setResult("Failed");
		}
		return interfaceResult;
	}

	/**
	 * 查询简历收藏
	 * 
	 * @param strZzjgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetCollectionResume", method = RequestMethod.POST)
	public InterfaceResult GetCollectionResume(String sessionId, String strZzjgId, HttpServletRequest req)
	{
		logInfo("GetCollectionResume", strZzjgId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			List<String> s = new ArrayList<String>();
			s.add("收藏");
			QueryJtjlk queryJtjlk = new QueryJtjlk();
			queryJtjlk.setJtjlkZt(s);
			queryJtjlk.setZzjgId(strZzjgId);
			queryJtjlk.setPageNum(1);
			queryJtjlk.setCurrentPageSize(10000000);
			queryJtjlk.setPageSize(10000000);
			List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
			if (jtjlks.size() > 0)
			{
				interfaceResult.setMessage("ok");
				interfaceResult.setResult(jtjlks);
			} else
			{
				interfaceResult.setMessage("no");
				interfaceResult.setResult(null);
			}
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("GetCollectionResume", strZzjgId + "******Error");
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoSxxt", method = RequestMethod.POST)
	public InterfaceResult DoSxxt(String sessionId, String strJtjlkId, String strEmail, String strTitle, String strContent, HttpServletRequest req)
	{
		logInfo("DoSxxt", strJtjlkId + "," + strEmail + "," + strTitle + "," + strContent);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 收件人邮箱
			mailInfo.setToAddress(strEmail);
			// 邮件标题
			mailInfo.setSubject(strTitle);
			// 邮件内容
			StringBuffer buffer = new StringBuffer();
			buffer.append(strContent);
			// 附件
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(strJtjlkId);
			String path = req.getSession().getServletContext().getRealPath("/") + "uppics\\" + jtjlk.getJtjlkUserid() + ".pdf";
			File file = new File(path);
			if (file.exists())
			{
				String[] strings =
				{ path };
				// System.out.println(path);
				mailInfo.setAttachFileNames(strings);
			}
			mailInfo.setContent(buffer.toString());

			// 发送邮件
			SendEmail sms = new SendEmail();
			// 发送文体格式
			sms.sendTextMail(mailInfo);
			jtjlk.setJtjlkJlzt("筛选协同");
			jtjlkService.updateByPrimaryKeySelective(jtjlk);
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult("Success");
		} catch (Exception e)
		{
			logInfo("DoSxxt", strJtjlkId + "," + strEmail + "," + strTitle + "," + strContent + "******Error");
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult("Failed");
		}
		return interfaceResult;
	}

	/**
	 * 发送通知
	 * 
	 * @param strUserId
	 * @param strType
	 * @param strContent
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
	public InterfaceResult SendMessage(String sessionId, String strUserId, String strTzfs, String strType, String strContent, HttpServletRequest req)
	{
		logInfo("SendMessage", strUserId + "," + strTzfs + "," + strType + "," + strContent);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Xxtz xxtz = new Xxtz();
			Userinfo userinfo = userService.selectByPrimaryKey(strUserId);
			if (!strTzfs.equals("1"))
			{
				MailSenderInfo mailInfo = new MailSenderInfo();
				// 收件人邮箱
				mailInfo.setToAddress(userinfo.getUserEmail());
				// 邮件标题
				mailInfo.setSubject(strType);
				// 邮件内容
				StringBuffer buffer = new StringBuffer();
				buffer.append(strContent);
				mailInfo.setContent(buffer.toString());
				// 发送html格式
				SendEmail.sendHtmlMail(mailInfo);
				xxtz.setXxtzEmail(userinfo.getUserEmail());
				xxtz.setXxtzEmailContent(strContent);
				xxtz.setXxtzEmailResult("");
				xxtz.setXxtzEmailTime(new Date());
			}
			if (!strTzfs.equals("2"))
			{
				// 发送短信
				String xml = null;
				XmlEntity xmlentity = new XmlEntity();
				SendMessage s = new SendMessage();
				xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", userinfo.getUserTelephone().toString(), strContent + "【中国航天科工培训中心】", "").toString();
				// System.out.println(xml);
				xmlentity.setReturnstatus("returnstatus");
				xmlentity.setMessage("message");
				xmlentity.setRemainpoint("remainpoint");
				xmlentity.setTaskID("taskID");
				xmlentity.setSuccessCounts("successCounts");
				xmlentity = s.readStringXmlCommen(xmlentity, xml);
				// System.out.println("状态" + xmlentity.getReturnstatus() +
				// "返回信息" + xmlentity.getMessage() + "成功条数" +
				// xmlentity.getSuccessCounts());
				xxtz.setXxtzMessageContent(strContent);
				xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
				xxtz.setXxtzMessageTime(new Date());
				xxtz.setXxtzTelepohne(userinfo.getUserTelephone());
			}
			xxtz.setUserId(userinfo.getUserId());
			xxtz.setXxtzAddtime(new Date());
			xxtz.setXxtzCuser("");
			xxtz.setXxtzDelflag((long) 1);
			xxtz.setXxtzId(RandomGUIDUtil.generateKey());
			xxtz.setXxtzType(strType);
			xxtz.setXxtzUser(userinfo.getUserName());
			xxtzService.insertxxtz(xxtz);
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult("Success");
		} catch (Exception e)
		{
			logInfo("SendMessage", strUserId + "," + strTzfs + "," + strType + "," + strContent + "******Error");
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult("Failed");
		}
		return interfaceResult;
	}

	/**
	 * 查看测评成绩
	 * 
	 * @param strZzjgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetTestResults", method = RequestMethod.POST)
	public InterfaceResult GetTestResults(String sessionId, String strZzjgId, QueryJtjlk queryJtjlk, HttpServletRequest req)
	{
		logInfo("GetTestResults", strZzjgId + "------," + JSONArray.fromObject(queryJtjlk).toString());
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		queryJtjlk.setZzjgId(strZzjgId);
		int intCount = jtjlkService.selectCount(queryJtjlk);
		queryJtjlk.setCurrentPageSize(intCount < queryJtjlk.getPageNum() * queryJtjlk.getPageSize() ? intCount - (queryJtjlk.getPageNum() * queryJtjlk.getPageSize() - queryJtjlk.getPageSize()) : queryJtjlk.getPageSize());
		List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
		interfaceResult.setRowCount(intCount);
		if (jtjlks.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(jtjlks);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		interfaceResult.setCode("10000");
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/CreateMsq", method = RequestMethod.POST)
	public InterfaceResult CreateMsq(String sessionId, String strZzjgId, String strMsqName, String strMsgNames, String strMsqMssj, String strMsqMsdd, String strMsqMslb, HttpServletRequest req)
	{
		logInfo("CreateMsq", strZzjgId + "," + strMsqName + "," + strMsgNames + "," + strMsqMssj + "," + strMsqMsdd + "," + strMsqMslb);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			List<Userinfo> userinfos = new ArrayList<Userinfo>();
			Msq msq = new Msq();
			String strMsqId = RandomGUIDUtil.generateKey();
			// 添加用户
			for (int i = 0; i < strMsgNames.split(",").length; i++)
			{
				String strId = RandomGUIDUtil.generateKey();
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
				userinfo.setUserJlid(strMsqId);
				userinfo.setUserSfls("是");
				userinfo.setUserPassword(loginPswd);
				userinfo.setUserName(strMsgNames.split(",")[i]);
				userService.insertSelective(userinfo);
				userinfos.add(userinfo);
			}
			msq.setMsqAddtime(new Date());
			msq.setMsqDelflag(1);
			msq.setMsqId(strMsqId);
			msq.setZzjgId(strZzjgId);
			msq.setMsqName(strMsqName);
			msq.setMsqMsgNames(strMsgNames);
			msq.setMsqMssj(strMsqMssj);
			msq.setMsqMsdd(strMsqMsdd);
			msq.setMsqMslb(strMsqMslb);
			msqService.insertSelective(msq);
			interfaceResult.setResult(userinfos);
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("CreateMsq", strZzjgId + "," + strMsqName + "," + strMsgNames + "," + strMsqMssj + "," + strMsqMsdd + "," + strMsqMslb + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoCptz", method = RequestMethod.POST)
	public InterfaceResult DoCptz(String sessionId, String strJtjlkId, String testType, HttpServletRequest req)
	{
		logInfo("DoCptz", strJtjlkId + "," + testType);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Jtjlk jSel = jtjlkService.selectByPrimaryKey(strJtjlkId);
			if (jSel.getJtjlkCpcj().equals("未完成"))
			{
				interfaceResult.setMessage("该简历已被通知过测评");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
			} else if (!jSel.getJtjlkCpcj().equals("未通知"))
			{
				interfaceResult.setMessage("该简历已参加过测评");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
			} else
			{
				Jtjlk jtjlk = new Jtjlk();
				jtjlk.setJtjlkId(strJtjlkId);
				jtjlk.setJtjlkJlzt("未测评");
				jtjlk.setJtjlkCpcj("未完成");
				jtjlk.setJtjlkCpjg("未完成");
				jtjlk.setJtjlkApcpgwlb(testType);
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			}
		} catch (Exception e)
		{
			logInfo("DoCptz", strJtjlkId + "," + testType + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoLy", method = RequestMethod.POST)
	public InterfaceResult DoLy(String sessionId, String strJtjlkId, Jtjlk j, HttpServletRequest req)
	{
		logInfo("DoLy", strJtjlkId + "------," + JSONArray.fromObject(j).toString());
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Jtjlk jSel = jtjlkService.selectByPrimaryKey(strJtjlkId);
			if (jSel.getJtjlkCpjg().indexOf("通过") == -1)
			{
				interfaceResult.setMessage("该简历测评结果为'" + jSel.getJtjlkCpjg() + "',不能执行录用操作!");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
			} else if (jSel.getJtjlkMszt().indexOf("通过") == -1)
			{
				interfaceResult.setMessage("该简历面试状态为'" + jSel.getJtjlkCpjg() + "',不能执行录用操作!");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
			} else
			{
				Jtjlk jtjlk = new Jtjlk();
				jtjlk.setJtjlkId(strJtjlkId);
				jtjlk.setJtjlkJlzt("录用");
				jtjlk.setJtjlkZzlygwId(j.getJtjlkZzlygwId());
				jtjlk.setJtjlkZzlygw(j.getJtjlkZzlygw());
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			}
		} catch (Exception e)
		{
			logInfo("DoLy", strJtjlkId + "------," + JSONArray.fromObject(j).toString() + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoCptg", method = RequestMethod.POST)
	public InterfaceResult DoCptg(String sessionId, String strJtjlkId, HttpServletRequest req)
	{
		logInfo("DoCptg", strJtjlkId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Jtjlk jSel = jtjlkService.selectByPrimaryKey(strJtjlkId);
			if (jSel.getJtjlkCpcj().equals("未完成") || jSel.getJtjlkCpcj().equals("未通知"))
			{
				interfaceResult.setMessage("该简历测成绩为'" + jSel.getJtjlkCpcj() + "',不能执行通过操作");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
			} else
			{
				Jtjlk jtjlk = new Jtjlk();
				jtjlk.setJtjlkId(strJtjlkId);
				jtjlk.setJtjlkCpjg("测评通过");
				jtjlk.setJtjlkJlzt("通过");
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			}
		} catch (Exception e)
		{
			logInfo("DoCptg", strJtjlkId + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoMstg", method = RequestMethod.POST)
	public InterfaceResult DoMstg(String sessionId, String strJtjlkId, HttpServletRequest req)
	{
		logInfo("DoMstg", strJtjlkId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			// Map<String, String> params = new HashMap<String, String>();
			// params.put("jlId", strJtjlkId);
			// List<Msqgl_detailed> Msqgl_detaileds =
			// msjgService.selectByJlId(params);
			// String strReturn = "false";
			// if (Msqgl_detaileds.size() > 0)
			// {
			// for (Msqgl_detailed msqgl_detailed : Msqgl_detaileds)
			// {
			// if (msqgl_detailed.getMsqglDetailedPj() != null ||
			// msqgl_detailed.getMsqglDetailedJl() != null)
			// {
			// strReturn = "true";
			// break;
			// }
			// }
			// }
			// if (!strReturn.equals("true"))
			// {
			// interfaceResult.setMessage("面试官还未填写结论及评价,不能执行通过操作");
			// interfaceResult.setCode("10000");
			// interfaceResult.setResult("Failed");
			// } else
			// {
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(strJtjlkId);
			jtjlk.setJtjlkMszt(jtjlk.getJtjlkMszt().replace("进行中", "通过"));
			jtjlk.setJtjlkJlzt("通过");
			jtjlkService.updateByPrimaryKeySelective(jtjlk);
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
			// }
		} catch (Exception e)
		{
			logInfo("DoMstg", strJtjlkId + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoTt", method = RequestMethod.POST)
	public InterfaceResult DoTt(String sessionId, String strJtjlkId, String strCpjg, String strMsjg, HttpServletRequest req)
	{
		logInfo("DoTt", strJtjlkId + "," + strCpjg + "," + strMsjg);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Jtjlk jSel = jtjlkService.selectByPrimaryKey(strJtjlkId);
			if (!jSel.getJtjlkJlzt().equals("录用"))
			{
				Jtjlk jtjlk = new Jtjlk();
				jtjlk.setJtjlkId(strJtjlkId);
				if (strCpjg != null)
				{
					if (!strCpjg.trim().equals(""))
						jtjlk.setJtjlkCpjg(strCpjg);
				}
				if (strMsjg != null)
				{
					if (!strMsjg.trim().equals(""))
						jtjlk.setJtjlkMszt(strMsjg);
				}
				jtjlk.setJtjlkJlzt("淘汰");
				jtjlk.setJtjlkTtqcpjg(jSel.getJtjlkCpjg());
				jtjlk.setJtjlkTtqmszt(jSel.getJtjlkMszt());
				jtjlk.setJtjlkTtqjlzt(jSel.getJtjlkJlzt());
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			} else
			{
				interfaceResult.setResult("Failed");
				interfaceResult.setMessage("该简历已被录用,无法执行淘汰操作");
				interfaceResult.setCode("10000");
			}
		} catch (Exception e)
		{
			logInfo("DoTt", strJtjlkId + "," + strCpjg + "," + strMsjg + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/DoTg", method = RequestMethod.POST)
	public InterfaceResult DoTg(String sessionId, String strJtjlkId, String strCpjg, String strMsjg, HttpServletRequest req)
	{
		logInfo("DoTt", strJtjlkId + "," + strCpjg + "," + strMsjg);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Jtjlk jSel = jtjlkService.selectByPrimaryKey(strJtjlkId);
			if (jSel.getJtjlkJlzt().equals("未筛选") || jSel.getJtjlkJlzt().equals("筛选协同"))
			{
				Jtjlk jtjlk = new Jtjlk();
				jtjlk.setJtjlkId(strJtjlkId);
				jtjlk.setJtjlkJlzt("通过");
				jtjlkService.updateByPrimaryKeySelective(jtjlk);
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			} else
			{
				interfaceResult.setResult("Failed");
				interfaceResult.setMessage("接口调用错误,该接口只支持简历状态为'未筛选',或'筛选协同'的简历");
				interfaceResult.setCode("10000");
			}
		} catch (Exception e)
		{
			logInfo("DoTt", strJtjlkId + "," + strCpjg + "," + strMsjg + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 推送到面试圈
	 * 
	 * @param msq
	 * @param strJtjlkId
	 * @param strMsqId
	 * @param strZzjgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/TsMsq", method = RequestMethod.POST)
	public InterfaceResult TsMsq(String sessionId, String strJtjlkId, String strMsqId, String strZzjgId, HttpServletRequest req)
	{
		logInfo("TsMsq", strJtjlkId + "," + strMsqId + "," + strZzjgId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Msq msq = msqService.selectByPrimaryKey(strMsqId);
			List<Msqgl_detailed> msqgl_detailedCount = msjgService.selectCountByForeignKey(strJtjlkId);
			String strMslb = "";
			if (msqgl_detailedCount.size() == 0)
				strMslb = "一面";
			else if (msqgl_detailedCount.size() == 1)
				strMslb = "二面";
			else if (msqgl_detailedCount.size() == 2)
				strMslb = "终面";
			else
			{
				interfaceResult.setMessage("已完成终面");
				interfaceResult.setCode("10000");
				interfaceResult.setResult("Failed");
				return interfaceResult;
			}
			Jtjlk jtjlk = jtjlkService.selectByPrimaryKey(strJtjlkId);
			String strMszt = jtjlk.getJtjlkMszt();
			if (!strMslb.equals("一面"))
			{
				if (strMszt.indexOf("通过") == -1)
				{
					interfaceResult.setMessage("上一轮面试未完成,无法进行本轮");
					interfaceResult.setCode("10000");
					interfaceResult.setResult("Failed");
					return interfaceResult;
				}
			}

			msq = msqService.selectByPrimaryKey(strMsqId);

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
				msqgl_detailed.setJtjlkId(strJtjlkId);
				msqgl_detailed.setMsqglDetailedMssj(msq.getMsqMssj());
				msqgl_detailed.setMsqglDetailedMsdd(msq.getMsqMsdd());
				msjgService.insertSelective(msqgl_detailed);

				Jtjlk jtjlkUpdate = new Jtjlk();
				jtjlkUpdate.setJtjlkId(strJtjlkId);
				jtjlkUpdate.setJtjlkJlzt("已安排面试");
				jtjlkUpdate.setJtjlkMszt(strMslb + "进行中");
				jtjlkService.updateByPrimaryKeySelective(jtjlkUpdate);

				Map<String, String> params = new HashMap<String, String>();
				params.put("name", userinfo.getUserName());
				params.put("zzjgId", strZzjgId);
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
				// "返回信息" + xmlentity.getMessage() + "成功条数" +
				// xmlentity.getSuccessCounts());

				Xxtz xxtz = new Xxtz();
				xxtz.setUserId(jtjlk.getJtjlkUserid());
				xxtz.setXxtzAddtime(new Date());
				xxtz.setXxtzCuser(strJtjlkId);
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
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("TsMsq", strJtjlkId + "," + strMsqId + "," + strZzjgId + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 面试官填写结果
	 * 
	 * @param strMsgId
	 * @param strResumeId
	 * @param strPj
	 * @param strJl
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/SaveMsgResult", method = RequestMethod.POST)
	public InterfaceResult SaveMsgResult(String sessionId, String strMsgName, String strJtjlkId, String strPj, String strJl, HttpServletRequest req)
	{
		logInfo("SaveMsgResult", strMsgName + "," + strJtjlkId + "," + strPj + "," + strJl);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Map<String, String> params = new HashMap<String, String>(2);
			params.put("jlId", strJtjlkId);
			params.put("userName", strMsgName);
			List<Msqgl_detailed> msqgl_detaileds = msjgService.selectByMsg(params);
			if (msqgl_detaileds.size() > 0)
			{
				for (Msqgl_detailed msqgl_detailed : msqgl_detaileds)
				{
					msqgl_detailed.setMsqglDetailedPj(strPj);
					msqgl_detailed.setMsqglDetailedJl(strJl);
					msjgService.updateByPrimaryKeySelective(msqgl_detailed);
				}
				interfaceResult.setResult("Success");
				interfaceResult.setMessage("ok");
				interfaceResult.setCode("10000");
			} else
			{
				interfaceResult.setResult("Failed");
				interfaceResult.setMessage("error");
				interfaceResult.setCode("10008");
			}
		} catch (Exception e)
		{
			logInfo("SaveMsgResult", strMsgName + "," + strJtjlkId + "," + strPj + "," + strJl + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 删除临时用户
	 * 
	 * @param strResumeId
	 * @param strMsgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/DelMsg", method = RequestMethod.POST)
	public InterfaceResult DelMsg(String sessionId, String strMsqId, HttpServletRequest req)
	{
		logInfo("DelMsg", strMsqId);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("msqId", strMsqId);
			Msq msq = msqService.selectByPrimaryKey(strMsqId);
			msq.setMsqMsgNames(msq.getMsqMsgNames() + "(已全部删除)");
			msqService.updateByPrimaryKeySelective(msq);
			userService.delLsUser(map);
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("DelMsg", strMsqId + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 更新面试圈
	 * 
	 * @param strMsqId
	 * @param strMsgNames
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/UpdateMsq", method = RequestMethod.POST)
	public InterfaceResult UpdateMsq(String sessionId, String strMsqId, String strMsqName, String strMsgNames, String strMsqMssj, String strMsqMsdd, String strMsqMslb, HttpServletRequest req)
	{
		logInfo("UpdateMsq", strMsqId + "," + strMsqName + "," + strMsgNames + "," + strMsqMssj + "," + strMsqMsdd + "," + strMsqMslb);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Msq msqOld = new Msq();
			msqOld = msqService.selectByPrimaryKey(strMsqId);
			List<Userinfo> userinfos = userService.selectByMsqId(strMsqId);
			String userName = "";
			for (Userinfo u : userinfos)
			{
				userName += u.getUserName() + ",";
			}
			msqOld.setMsqMsgNames(userName.substring(0, userName.length() - 1));

			for (int i = 0; i < msqOld.getMsqMsgNames().split(",").length; i++)
			{
				if (strMsgNames.indexOf(msqOld.getMsqMsgNames().split(",")[i]) == -1)
				{
					// 删除面试官记录
					Map<String, String> params = new HashMap<String, String>();
					params.put("msqId", msqOld.getMsqId());
					params.put("msgName", msqOld.getMsqMsgNames().split(",")[i]);
					msjgService.deleteByWhere(params);

					// 删除用户
					Map<String, String> map = new HashMap<String, String>();
					map.put("userName", msqOld.getMsqMsgNames().split(",")[i]);
					map.put("msqId", msqOld.getMsqId());
					userService.delLsUser(map);
					map.clear();
				}
			}
			for (int i = 0; i < strMsgNames.split(",").length; i++)
			{
				if (msqOld.getMsqMsgNames().indexOf(strMsgNames.split(",")[i]) == -1)
				{
					String strId = RandomGUIDUtil.generateKey();
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
					userinfo.setUserJlid(msqOld.getMsqId());
					userinfo.setUserSfls("是");
					userinfo.setUserPassword(loginPswd);
					userinfo.setUserName(strMsgNames.split(",")[i]);
					userService.insertSelective(userinfo);
					// 添加面试官记录
					List<Msqgl_detailed> msqgl_detaileds = msjgService.selectMsInfoByMsq(strMsqId);
					for (Msqgl_detailed msqgl_detailed : msqgl_detaileds)
					{
						msqgl_detailed.setMsqglDetailedAddtime(new Date());
						msqgl_detailed.setMsqglDetailedDelflag((long) 1);
						msqgl_detailed.setMsqglDetailedId(RandomGUIDUtil.generateKey());
						msqgl_detailed.setMsqglDetailedMsg(strMsgNames.split(",")[i]);
						msjgService.insertSelective(msqgl_detailed);
					}
				}
			}
			Msq msq = new Msq();
			msq.setMsqId(strMsqId);
			msq.setMsqName(strMsqName);
			msq.setMsqMssj(strMsqMssj);
			msq.setMsqMsdd(strMsqMsdd);
			msq.setMsqMsgNames(strMsgNames);
			msq.setMsqMslb(strMsqMslb);
			msqService.updateByPrimaryKeySelective(msq);
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("UpdateMsq", strMsqId + "," + strMsqName + "," + strMsgNames + "," + strMsqMssj + "," + strMsqMsdd + "," + strMsqMslb + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetMyResume", method = RequestMethod.POST)
	public InterfaceResult GetMyResume(String sessionId, String strUserId, HttpServletRequest req)
	{
		logInfo("GetMyResume", strUserId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		Resume resume = resumeService.selectByPrimaryKeySelectiveById(strUserId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("resume", resume);
		if (resume != null)
			interfaceResult.setMessage("ok");
		else
			interfaceResult.setMessage("no");
		interfaceResult.setCode("10000");
		interfaceResult.setResult(mv.getModel());
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetMyResumeDetails", method = RequestMethod.POST)
	public InterfaceResult GetMyResumeDetails(String sessionId, String strUserId, HttpServletRequest req)
	{
		logInfo("GetMyResumeDetails", strUserId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Resume_jyjl> Jyjl = resume_jyjlService.getlistResumeJyjlByResumeId(strUserId);
		List<Resume_xnjl> Xnjl = resume_xnjlService.getlistResumeXnjlByResumeId(strUserId);
		List<Resume_xnzw> Xnzw = resume_xnzwService.getlistResumeXnzwByResumeId(strUserId);
		List<Resume_sjjl> Sjjl = resume_sjjlService.getlistResumeSjjlByResumeId(strUserId);
		List<Resume_sx> Sxjl = resume_sxService.getlistResumeSxByResumeId(strUserId);
		List<Resume_gzjl> Gzjl = resume_gzjlService.getlistResumeGzjlByResumeId(strUserId);
		List<Resume_xmjy> Xmjy = resume_xmjyService.getlistResumeXmjyByResumeId(strUserId);
		List<Resume_pxjl> Pxjl = resume_pxjlService.getlistResumePxjlByResumeId(strUserId);
		List<Resume_yynl> Yynl = resume_yynlService.getlistResumeYynlByResumeId(strUserId);
		List<Resume_qtxx> Qtxx = resume_qtxxService.getlistResumeQtxxByResumeId(strUserId);
		List<Resume_zs> Zs = resume_zsService.getlistResumeZsByResumeId(strUserId);
		List<Resume_ITjn> Itjn = resume_itjnService.getlistResumeITjnByResumeId(strUserId);
		List<Resume_fj> Fj = resume_fjService.getlistResumeFjByResumeId(strUserId);
		ModelAndView mv = new ModelAndView();
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
		mv.addObject("Zs", Zs);
		mv.addObject("Itjn", Itjn);
		mv.addObject("Fj", Fj);
		if (Jyjl.size() > 0 || Xnjl.size() > 0 || Xnzw.size() > 0 || Sjjl.size() > 0 || Sxjl.size() > 0 || Gzjl.size() > 0 || Xmjy.size() > 0 || Pxjl.size() > 0 || Yynl.size() > 0 || Qtxx.size() > 0 || Fj.size() > 0 || Zs.size() > 0 || Itjn.size() > 0)
			interfaceResult.setMessage("ok");
		else
			interfaceResult.setMessage("no");
		interfaceResult.setCode("10000");
		interfaceResult.setResult(mv.getModel());
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/MsqSave", method = RequestMethod.POST)
	public InterfaceResult MsqSave(String sessionId, String strMsqId, String strMsgNames, HttpServletRequest req)
	{
		logInfo("MsqSave", strMsqId + "," + strMsgNames);
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			Msq msqOld = msqService.selectByPrimaryKey(strMsqId);
			// 正常添加
			List<Userinfo> userinfos = userService.selectByMsqId(strMsqId);
			String userName = "";
			for (Userinfo u : userinfos)
			{
				userName += u.getUserName() + ",";
			}
			msqOld.setMsqMsgNames(userName.substring(0, userName.length() - 1));

			for (int i = 0; i < msqOld.getMsqMsgNames().split(",").length; i++)
			{
				if (strMsgNames.indexOf(msqOld.getMsqMsgNames().split(",")[i]) == -1)
				{
					// 删除面试官记录
					Map<String, String> params = new HashMap<String, String>();
					params.put("msqId", msqOld.getMsqId());
					params.put("msgName", msqOld.getMsqMsgNames().split(",")[i]);
					msjgService.deleteByWhere(params);

					// 删除用户
					Map<String, String> map = new HashMap<String, String>();
					map.put("userName", msqOld.getMsqMsgNames().split(",")[i]);
					map.put("msqId", msqOld.getMsqId());
					userService.delLsUser(map);
					map.clear();
				}
			}
			for (int i = 0; i < strMsgNames.split(",").length; i++)
			{
				if (msqOld.getMsqMsgNames().indexOf(strMsgNames.split(",")[i]) == -1)
				{
					String strId = RandomGUIDUtil.generateKey();
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
					userinfo.setUserJlid(msqOld.getMsqId());
					userinfo.setUserSfls("是");
					userinfo.setUserPassword(loginPswd);
					userinfo.setUserName(strMsgNames.split(",")[i]);
					userService.insertSelective(userinfo);
					// 添加面试官记录
					List<Msqgl_detailed> msqgl_detaileds = msjgService.selectMsInfoByMsq(strMsqId);
					for (Msqgl_detailed msqgl_detailed : msqgl_detaileds)
					{
						msqgl_detailed.setMsqglDetailedAddtime(new Date());
						msqgl_detailed.setMsqglDetailedDelflag((long) 1);
						msqgl_detailed.setMsqglDetailedId(RandomGUIDUtil.generateKey());
						msqgl_detailed.setMsqglDetailedMsg(strMsgNames.split(",")[i]);
						msjgService.insertSelective(msqgl_detailed);
					}
				}
			}
			Msq msq = new Msq();
			msq.setMsqId(strMsqId);
			msq.setMsqMsgNames(strMsgNames);
			msqService.updateByPrimaryKeySelective(msq);
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
			interfaceResult.setCode("10000");
		} catch (Exception e)
		{
			logInfo("MsqSave", strMsqId + "," + strMsgNames + "******Error");
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 联系人列表
	 * 
	 * @param sessionId
	 * @param strZzjgId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetLxr", method = RequestMethod.POST)
	public InterfaceResult GetLxr(String sessionId, String strZzjgId, HttpServletRequest req)
	{
		logInfo("GetLxr", strZzjgId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		PageCondition pageCondition = new PageCondition();
		pageCondition.setOrderField(strZzjgId);
		pageCondition.setPageNum(1);
		pageCondition.setPageNum(1000000);
		pageCondition.setCurrentPageSize(1000000);
		List<Txl> txls = txlService.selectByWhere(pageCondition);
		if (txls.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(txls);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetMsq", method = RequestMethod.POST)
	public InterfaceResult GetMsq(String sessionId, QueryMsq qMsq, String msqName, HttpServletRequest req)
	{
		logInfo("GetMsq", JSONArray.fromObject(qMsq).toString() + ",------" + msqName);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		qMsq.setZzjgId(qMsq.getZzjgId());
		qMsq.setMsqName(msqName);
		int intCount = msqService.selectCount(qMsq);
		qMsq.setCurrentPageSize(intCount < qMsq.getPageNum() * qMsq.getPageSize() ? intCount - (qMsq.getPageNum() * qMsq.getPageSize() - qMsq.getPageSize()) : qMsq.getPageSize());
		List<Msq> msqs = new ArrayList<Msq>();
		List<Msq> qMsqs = msqService.selectByWhere(qMsq);
		for (Msq msq : qMsqs)
		{
			String strName = "";
			List<Userinfo> userinfos = userService.selectByMsqId(msq.getMsqId());
			for (Userinfo u : userinfos)
			{
				strName += u.getUserName() + ",";
			}
			if (strName.length() > 0)
				msq.setMsqMsgNames(strName.substring(0, strName.length() - 1));

			msq.setMsqMsgList(userinfos);
			msqs.add(msq);
		}
		interfaceResult.setRowCount(intCount);
		if (msqs.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(msqs);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 面试圈里的简历
	 * 
	 * @param sessionId
	 * @param strMsqId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetYpzByMsq", method = RequestMethod.POST)
	public InterfaceResult GetYpzByMsq(String sessionId, String strMsqId, HttpServletRequest req)
	{
		logInfo("GetYpzByMsq", strMsqId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Msqgl_detailed> rs = msjgService.selectMsInfoByMsq(strMsqId);
		if (rs.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(rs);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 查看面试结果
	 * 
	 * @param sessionId
	 * @param strJtjlkId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetMsjg", method = RequestMethod.POST)
	public InterfaceResult GetMsjg(String sessionId, String strJtjlkId, HttpServletRequest req)
	{
		logInfo("GetMsjg", strJtjlkId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Msqgl_detailed> rs = msjgService.selectByJlId(strJtjlkId);
		if (rs.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(rs);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 面试圈里的简历库
	 * 
	 * @param sessionId
	 * @param strMsqId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetJtjlkByMsq", method = RequestMethod.POST)
	public InterfaceResult GetJtjlkByMsq(String sessionId, String strMsqId, HttpServletRequest req)
	{
		logInfo("GetJtjlkByMsq", strMsqId);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Jtjlk> rs = jtjlkService.selectByMsqId(strMsqId);
		if (rs.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(rs);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		interfaceResult.setRowCount(rs.size());
		return interfaceResult;
	}

	/**
	 * 
	 * @param sessionId
	 * @param strZzjgId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/GetResumeByStatus", method = RequestMethod.POST)
	public InterfaceResult GetResumeByStatus(String sessionId, String strZzjgId, QueryJtjlk queryJtjlk, HttpServletRequest req)
	{
		logInfo("GetResumeByStatus", strZzjgId + "," + JSONArray.fromObject(queryJtjlk).toString());
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		queryJtjlk.setZzjgId(strZzjgId);
		List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
		int intCount = jtjlkService.selectCount(queryJtjlk);
		interfaceResult.setRowCount(intCount);
		if (jtjlks.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(jtjlks);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetKcgl", method = RequestMethod.POST)
	public InterfaceResult GetKcgl(String sessionId, QueryKcgl queryKcgl, HttpServletRequest req)
	{
		logInfo("GetKcgl", JSONArray.fromObject(queryKcgl).toString());
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		int intCount = kcglService.selectCount(queryKcgl);
		queryKcgl.setCurrentPageSize(intCount < queryKcgl.getPageNum() * queryKcgl.getPageSize() ? intCount - (queryKcgl.getPageNum() * queryKcgl.getPageSize() - queryKcgl.getPageSize()) : queryKcgl.getPageSize());
		List<Kcgl> kcgls = kcglService.selectByWhere(queryKcgl);
		interfaceResult.setRowCount(intCount);
		if (kcgls.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(kcgls);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetBmgl", method = RequestMethod.POST)
	public InterfaceResult GetBmgl(String sessionId, QueryBmgl queryBmgl, HttpServletRequest req)
	{
		logInfo("GetBmgl", JSONArray.fromObject(queryBmgl).toString());
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		int intCount = bmglService.selectCount(queryBmgl);
		queryBmgl.setCurrentPageSize(intCount < queryBmgl.getPageNum() * queryBmgl.getPageSize() ? intCount - (queryBmgl.getPageNum() * queryBmgl.getPageSize() - queryBmgl.getPageSize()) : queryBmgl.getPageSize());
		List<Bmgl> bmgls = bmglService.selectByWhere(queryBmgl);
		interfaceResult.setRowCount(intCount);
		if (bmgls.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(bmgls);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/GetJlByWhere", method = RequestMethod.POST)
	public InterfaceResult GetJlByWhere(String sessionId, QueryJtjlk queryJtjlk, Integer type, HttpServletRequest req)
	{
		logInfo("GetJlByWhere", JSONArray.fromObject(queryJtjlk).toString() + ",------" + type);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		try
		{
			List<String> list = new ArrayList<String>();
			switch (type)
			{
			case 1:
				list.add("未筛选");
				list.add("筛选协同");
				queryJtjlk.setJtjlkZt(list);
				break;
			case 2:
				list.add("通过");
				list.add("未测评");
				list.add("已测评");
				list.add("已安排面试");
				queryJtjlk.setJtjlkZt(list);
				break;
			case 3:
				queryJtjlk.setJtjlkCpjg("测评");
				break;
			case 4:
				list.add("一面进行中");
				list.add("二面进行中");
				list.add("终面进行中");
				queryJtjlk.setJtjlkMszt(list);
				break;
			case 5:
				list.add("录用");
				queryJtjlk.setJtjlkZt(list);
				break;
			case 6:
				list.add("淘汰");
				queryJtjlk.setJtjlkZt(list);
				break;
			case 7:
				list.add("收藏");
				queryJtjlk.setJtjlkZt(list);
				break;
			default:
				break;
			}
			int intCount = jtjlkService.selectCount(queryJtjlk);
			queryJtjlk.setCurrentPageSize(intCount < queryJtjlk.getPageNum() * queryJtjlk.getPageSize() ? intCount - (queryJtjlk.getPageNum() * queryJtjlk.getPageSize() - queryJtjlk.getPageSize()) : queryJtjlk.getPageSize());
			List<Jtjlk> jtjlks = jtjlkService.selectByWhere(queryJtjlk);
			interfaceResult.setRowCount(intCount);
			if (jtjlks.size() > 0)
			{
				interfaceResult.setCode("10000");
				interfaceResult.setMessage("ok");
				interfaceResult.setResult(jtjlks);
			} else
			{
				interfaceResult.setCode("10000");
				interfaceResult.setMessage("no");
				interfaceResult.setResult(null);
			}
			return interfaceResult;

		} catch (Exception e)
		{
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
			return interfaceResult;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/GetJlByMsg", method = RequestMethod.POST)
	public InterfaceResult GetJlByMsg(String sessionId, String strMsgName, String strMsg_Jlid, HttpServletRequest req)
	{
		logInfo("GetJlByMsg", strMsgName + "," + strMsg_Jlid);
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", strMsgName);
		params.put("userId", strMsg_Jlid);
		List<Msqgl_detailed> m = msjgService.selectByMsg(params);
		if (m.size() > 0)
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(m);
		} else
		{
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 讨论区
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tlqList", method = RequestMethod.POST)
	public InterfaceResult BbsTlq(String sessionId, InterfaceResult interfaceResult, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Tlq> tlqList = tlqService.queryTlq();
		interfaceResult.setCode("10000");
		if (tlqList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(tlqList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 版块
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ltbkList", method = RequestMethod.POST)
	public InterfaceResult BbsLtbk(String sessionId, String tlqId, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Ltbk> ltbkList = ltbkService.queryLtbkByTlqId(tlqId);
		interfaceResult.setCode("10000");
		if (ltbkList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(ltbkList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 帖子
	 * 
	 * @param ltbkId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bbsList", method = RequestMethod.POST)
	public InterfaceResult bbsList(String sessionId, String ltbkId, QueryBbs qb, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Bbs> bbsList = bbsService.BbsByLtbkIdData(qb);
		int intCount = bbsService.BbsByLtbkIdDataCount(qb);
		interfaceResult.setRowCount(intCount);
		interfaceResult.setCode("10000");
		if (bbsList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(bbsList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 回复
	 * 
	 * @param bbsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reviewList", method = RequestMethod.POST)
	public InterfaceResult reviewList(String sessionId, String bbsId, QueryReview qr, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Review> reviewList = reviewService.selectByWhere(qr);
		int intCount = reviewService.selectCount(qr);
		interfaceResult.setRowCount(intCount);
		interfaceResult.setCode("10000");
		if (reviewList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(reviewList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 回复
	 * 
	 * @param reviewId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/replyList", method = RequestMethod.POST)
	public InterfaceResult replyList(String sessionId, String reviewId, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Reply> replyList = replyService.qtGetReplyByReviewId(reviewId);
		interfaceResult.setCode("10000");
		if (replyList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(replyList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * BBS评论保存
	 * 
	 * @param review
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reviewSave", method = RequestMethod.POST)
	public InterfaceResult reviewSave(String sessionId, Review review, HttpServletRequest req)
	{
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			review.setReviewPlsj(new Date());
			review.setReviewId(RandomGUIDUtil.generateKey());
			review.setReviewDelflag((long) 1);
			reviewService.insertSelective(review);
			interfaceResult.setCode("10000");
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
		} catch (Exception e)
		{
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * BBS回复保存
	 * 
	 * @param review
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/replySave", method = RequestMethod.POST)
	public InterfaceResult replySave(String sessionId, Reply reply, HttpServletRequest req)
	{
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			reply.setReplyAddtime(new Date());
			reply.setReplyId(RandomGUIDUtil.generateKey());
			reply.setReplyDelflag((long) 1);
			replyService.insertSelective(reply);
			interfaceResult.setCode("10000");
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
		} catch (Exception e)
		{
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 经验博文（员工）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/articleYgfxList", method = RequestMethod.POST)
	public InterfaceResult articleYgfxList(String sessionId, QueryArticle qa, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Article> articleYgfxList = articleService.LoadZytyData(qa);
		int intCount = articleService.LoadZytyDataCount(qa);
		interfaceResult.setRowCount(intCount);
		interfaceResult.setCode("10000");
		if (articleYgfxList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(articleYgfxList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 经验博文（新人）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/articleXrfxList", method = RequestMethod.POST)
	public InterfaceResult articleXrfxList(String sessionId, QueryArticle qa, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Article> articleXrfxList = articleService.LoadRmbwData(qa);
		int intCount = articleService.LoadRmbwDataCount(qa);
		interfaceResult.setRowCount(intCount);
		interfaceResult.setCode("10000");
		if (articleXrfxList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(articleXrfxList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	/**
	 * 兴趣圈子评论保存
	 * 
	 * @param articleReview
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobileArticleReviewSave", method = RequestMethod.POST)
	public InterfaceResult mobileArticleReviewSave(String sessionId, ArticleReview articleReview, HttpServletRequest req)
	{
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			articleReview.setArticleReviewPlsj(new Date());
			articleReview.setArticleReviewAddtime(new Date());
			articleReview.setArticleReviewId(RandomGUIDUtil.generateKey());
			articleReview.setArticleReviewDelflag((long) 1);
			articleReviewService.insertSelective(articleReview);
			interfaceResult.setCode("10000");
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
		} catch (Exception e)
		{
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	/**
	 * 兴趣圈子
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/xqqzList", method = RequestMethod.POST)
	public InterfaceResult xqqzList(String sessionId, QueryXqqz qx, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		List<Xqqz> xqqzList = xqqzService.selectByWhere(qx);
		int intCount = xqqzService.selectCount(qx);
		interfaceResult.setRowCount(intCount);
		interfaceResult.setCode("10000");
		if (xqqzList.size() > 0)
		{
			interfaceResult.setMessage("ok");
			interfaceResult.setResult(xqqzList);
		} else
		{
			interfaceResult.setMessage("no");
			interfaceResult.setResult(null);
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/mobileXqqzReviewSave", method = RequestMethod.POST)
	public InterfaceResult mobileXqqzReviewSave(String sessionId, XqqzReview xqqzReview, HttpServletRequest req)
	{
		String status = GetGids(req.getServletContext(), "0", sessionId);
		if (status.equals(""))
		{
			interfaceResult.setCode("10025");
			interfaceResult.setMessage("loginTimeout");
			interfaceResult.setResult(null);
			return interfaceResult;
		}
		try
		{
			xqqzReview.setXqqzReviewPlsj(new Date());
			xqqzReview.setXqqzReviewAddtime(new Date());
			xqqzReview.setXqqzReviewId(RandomGUIDUtil.generateKey());
			xqqzReview.setXqqzReviewDelflag((long) 1);
			xqqzReviewService.insertSelective(xqqzReview);
			interfaceResult.setCode("10000");
			interfaceResult.setResult("Success");
			interfaceResult.setMessage("ok");
		} catch (Exception e)
		{
			interfaceResult.setResult("Failed");
			interfaceResult.setMessage("error");
			interfaceResult.setCode("10008");
		}
		return interfaceResult;
	}

	@ResponseBody
	@RequestMapping(value = "/SendAllMessage", method = RequestMethod.POST)
	public InterfaceResult SendAllMessage(String sessionId, HttpServletRequest req)
	{
		try
		{
			String status = GetGids(req.getServletContext(), "0", sessionId);
			if (status.equals(""))
			{
				interfaceResult.setCode("10025");
				interfaceResult.setMessage("loginTimeout");
				interfaceResult.setResult(null);
				return interfaceResult;
			}
			QueryXxtz queryXxtz = new QueryXxtz();
			queryXxtz.setPageNum(1);
			queryXxtz.setPageSize(10000);
			queryXxtz.setType("成绩推送");
			queryXxtz.setCurrentPageSize(10000);
			List<Xxtz> xxtzs = xxtzService.selectByWhere(queryXxtz);
			for (Xxtz xxtz : xxtzs)
			{
				System.out.println(xxtz.getXxtzMessageResult());
				if (xxtz.getXxtzMessageResult() != null)
					continue;
				// MailSenderInfo mailInfo = new MailSenderInfo();
				// // 收件人邮箱
				// mailInfo.setToAddress(xxtz.getXxtzEmail());
				// // 邮件标题
				// mailInfo.setSubject(xxtz.getXxtzType());
				// // 邮件内容
				// StringBuffer buffer = new StringBuffer();
				// buffer.append(xxtz.getXxtzEmailContent());
				// mailInfo.setContent(buffer.toString());
				// // 发送html格式
				// SendEmail.sendHtmlMail(mailInfo);
				// xxtz.setXxtzEmail(xxtz.getXxtzEmail());
				// xxtz.setXxtzEmailContent(xxtz.getXxtzEmailContent());
				// xxtz.setXxtzEmailResult("");
				// xxtz.setXxtzEmailTime(new Date());

				String xml = null;
				XmlEntity xmlentity = new XmlEntity();
				SendMessage s = new SendMessage();
				xml = s.SendMessage("5726", "xpt20111", "htpxzx123456", xxtz.getXxtzTelepohne().toString(), xxtz.getXxtzEmailContent() + "【中国航天科工培训中心】", "").toString();
				xmlentity.setReturnstatus("returnstatus");
				xmlentity.setMessage("message");
				xmlentity.setRemainpoint("remainpoint");
				xmlentity.setTaskID("taskID");
				xmlentity.setSuccessCounts("successCounts");
				xmlentity = s.readStringXmlCommen(xmlentity, xml);
				// System.out.println("状态" + xmlentity.getReturnstatus() +
				// "返回信息" + xmlentity.getMessage() + "成功条数" +
				// xmlentity.getSuccessCounts());
				xxtz.setXxtzMessageContent(xxtz.getXxtzEmailContent() + "【中国航天科工培训中心】");
				xxtz.setXxtzMessageResult(xmlentity.getReturnstatus());
				xxtz.setXxtzMessageTime(new Date());
				xxtz.setXxtzEmailTime(xxtz.getXxtzEmailTime());
				xxtzService.updateByPrimaryKeySelective(xxtz);
				logInfo("SendAllMessage", xxtz.getXxtzTelepohne().toString() + "," + xxtz.getXxtzMessageContent() + "状态" + xmlentity.getReturnstatus() + "返回信息" + xmlentity.getMessage());
			}
			// Userinfo userinfo = userService.selectByPrimaryKey(strUserId);
			interfaceResult.setCode("10000");
			interfaceResult.setMessage("ok");
			interfaceResult.setResult("Success");
		} catch (Exception e)
		{
			interfaceResult.setCode("10008");
			interfaceResult.setMessage("error");
			interfaceResult.setResult("Failed");
		}
		return interfaceResult;
	}
}

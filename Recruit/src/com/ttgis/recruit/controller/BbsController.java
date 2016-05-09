package com.ttgis.recruit.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Bbs;
import com.ttgis.recruit.domain.Ltbk;
import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.QueryBbs;
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Tlq;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zan;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.BbsService;
import com.ttgis.recruit.service.LtbkService;
import com.ttgis.recruit.service.LtbqService;
import com.ttgis.recruit.service.ReplyService;
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.service.TlqService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.ZanService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。BbsController
 * @创建人： 范井龙
 * @日期：
 * @描述： 论坛基本操作
 * @版本号：
 */
@Controller
public class BbsController
{

	static Logger logger = Logger.getLogger(BbsController.class);

	@Autowired
	private BbsService bbsService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LtbqService ltbqService;
	@Autowired
	private LtbkService ltbkService;
	@Autowired
	private UserService userService;
	@Autowired
	private TlqService tlqService;
	@Autowired
	private ZanService zanService;
	@Autowired
	private ReplyService replyService;

	/**
	 * 跳转页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bbs", method = RequestMethod.GET)
	public String Bbs(Model model)
	{
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbkList", ltbkList);
		return "sqlt/bbsList";
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/BbsSave", method = RequestMethod.POST)
	public ModelAndView BbsSave(Bbs bbs, HttpSession session, Zzjg zzjg)
	{
		zzjg = (Zzjg) session.getAttribute("loginSession");
		if (!zzjg.getSfjy().equals("1"))
		{
			bbs.setBbsFbr(zzjg.getZzjgDwmc());
			bbs.setBbsFbsj(new Date());
			bbs.setBbsId(RandomGUIDUtil.generateKey());
			bbs.setBbsDelflag((long) 1);
			bbs.setBbsZan("0");
			bbsService.insertBbs(bbs);
		} else
		{
			return new ModelAndView("sqlt/userJY");
		}
		return new ModelAndView("redirect:/bbs");
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadBbsData", method = RequestMethod.POST)
	public ModelAndView queryLtbqByPage(QueryBbs qb, Model model)
	{
		List<Bbs> bbsList = bbsService.selectByWhere(qb);
		List<Review> reviewList = reviewService.queryReview();
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		ModelAndView m = new ModelAndView();
		m.addObject("bbsList", bbsList);
		m.addObject("reviewList", reviewList);
		m.addObject("ltbqList", ltbqList);
		return m;
		// return bbsList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadBbsDataCount", method = RequestMethod.POST)
	public int queryLtbqByPageCount(QueryBbs qb)
	{
		int intCount = bbsService.selectCount(qb);
		return intCount;
	}

	/**
	 * 删除信息
	 * 
	 * @param bbsId
	 * @return
	 */
	@RequestMapping(value = "/delBbs", method = RequestMethod.GET)
	public String delBbs(String bbsId)
	{
		List<Review> reviewList = reviewService.queryByBbsId(bbsId);
		if (reviewList != null)
		{
			for (Review review : reviewList)
			{
				replyService.delhtBbsReviewReplyByReviewId(review.getReviewId());
			}
			reviewService.delReviewByBbsId(bbsId);
			bbsService.delBbs(bbsId);
		} else
		{
			reviewService.delReviewByBbsId(bbsId);
			bbsService.delBbs(bbsId);
		}
		return "redirect:/htBbs";
	}

	@RequestMapping(value = "/delBbs1", method = RequestMethod.GET)
	public String delBbs1(String bbsId)
	{
		reviewService.delReviewByBbsId(bbsId);
		bbsService.delBbs(bbsId);
		return "redirect:/queryMarkBbs";
	}

	/**
	 * 查询 发表的内容及评论等信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryBbsAndHf", method = RequestMethod.GET)
	public String queryBbsAndHf(Model model)
	{
		List<Bbs> bbsList = bbsService.queryBbs();
		List<Review> reviewList = reviewService.queryReview();
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/bbsList";
	}

	/**
	 * 通过标签实现分类查询
	 * 
	 * @param ltbqName
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/queryBbsByLtbq", method = RequestMethod.GET)
	public String queryBbsByLtbq(Ltbq ltbq, Model model, String ltbqName) throws UnsupportedEncodingException
	{
		ltbqName = new String(ltbqName.getBytes("ISO-8859-1"), "UTF-8");
		ltbq.setLtbqName(ltbqName);
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		List<Bbs> bbsList = bbsService.queryBbsByLtbq(ltbq);
		List<Review> reviewList = reviewService.queryReview();
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("reviewList", reviewList);
		return "sqlt/queryBbsByBQ";
	}

	// --------------------------------------------前台Bbs------------------------------------------------------------
	@RequestMapping(value = "/jlsq", method = RequestMethod.GET)
	public String jlsq(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		List<Tlq> tlqList = tlqService.queryTlq();
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbkList", ltbkList);
		model.addAttribute("tlqList", tlqList);
		return "jlsq/jlsq";
	}

	/**
	 * 跳转
	 * 
	 * @param model
	 * @param ltbk
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "/queryByBq", method = RequestMethod.GET)
	public String queryByBq(Model model, Ltbq ltbq) throws UnsupportedEncodingException
	{
		String name = new String(ltbq.getLtbqName().getBytes("ISO-8859-1"), "UTF-8");
		ltbq.setLtbqName(name);
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbq", ltbq);
		return "bbs/queryByBq";
	}

	@RequestMapping(value = "/redirectqtBbs", method = RequestMethod.GET)
	public String redirectqtBbs(Model model, Ltbk ltbk) throws UnsupportedEncodingException
	{
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		String ltbkName = ltbk.getLtbkName();
		String name = new String(ltbkName.getBytes("ISO-8859-1"), "UTF-8");
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbkName", name);
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbkList", ltbkList);
		return "bbs/qtBbs";
	}

	// ------------------------------------------根据标签查询-----------------------
	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/queryByBqData", method = RequestMethod.POST)
	public ModelAndView queryByBqData(QueryBbs qb, Model model, String name)
	{
		qb.setLtbqName(name);
		List<Bbs> bbsList = bbsService.selectByWhere(qb);
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		ModelAndView m = new ModelAndView();
		m.addObject("bbsList", bbsList);
		m.addObject("ltbqList", ltbqList);
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/queryByBqDataCount", method = RequestMethod.POST)
	public int queryByBqDataCount(QueryBbs qb, String name)
	{
		qb.setLtbqName(name);
		int intCount = bbsService.selectByLtbqCount(qb);
		return intCount;
	}

	@RequestMapping(value = "/getBByLtbq", method = RequestMethod.GET)
	public String getBByLtbq(Bbs bbs, Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		bbs = bbsService.getBbsById(bbs.getBbsId());
		model.addAttribute("bbs", bbs);
		return "bbs/twoBbs";
	}

	@RequestMapping(value = "/BkBbsSave", method = RequestMethod.POST)
	public ModelAndView BkBbsSave(Bbs bbs, HttpSession session, Userinfo user)
	{

		user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return new ModelAndView("sqlt/Noqtlogin");
		} else
		{
			if (!(user.getSfjy()).equals("1"))
			{
				bbs.setBbsFbr(user.getUserName());
				bbs.setBbsFbsj(new Date());
				bbs.setBbsId(RandomGUIDUtil.generateKey());
				bbs.setBbsDelflag((long) 1);
				bbs.setBbsZan("0");
				bbsService.insertBbs(bbs);
				ltbkService.updateLtbkSJ(bbs.getLtbkId());
			} else
			{
				return new ModelAndView("sqlt/qtUserJy");
			}
			return new ModelAndView("redirect:/queryBbsByLtbkId?ltbkId=" + bbs.getLtbkId());
		}
	}

	@RequestMapping(value = "/BqBbsSave", method = RequestMethod.POST)
	public ModelAndView BqBbsSave(Bbs bbs, HttpSession session, Userinfo user, Ltbq ltbq)
	{
		user = (Userinfo) session.getAttribute("userLoginSession");
		if (!(user.getSfjy()).equals("1"))
		{
			bbs.setBbsFbr(user.getUserName());
			bbs.setBbsFbsj(new Date());
			bbs.setBbsId(RandomGUIDUtil.generateKey());
			bbs.setBbsDelflag((long) 1);
			bbs.setBbsZan("0");
			bbsService.insertBbs(bbs);
		} else
		{
			return new ModelAndView("sqlt/userJY");
		}
		return new ModelAndView("redirect:/queryByBq?ltbqName=" + ltbq.getLtbqName() + "&ltbqId=" + ltbq.getLtbqId());
	}

	@ResponseBody
	@RequestMapping(value = "/jyglUser", method = RequestMethod.GET)
	public int jyglUser(String reviewPlr) throws UnsupportedEncodingException
	{
		String olr = new String(reviewPlr.getBytes("ISO-8859-1"), "UTF-8");
		return userService.userJygl(olr);

	}

	@ResponseBody
	@RequestMapping(value = "/checkPlrSFinUser", method = RequestMethod.GET)
	public int checkPlrSFinUser(String reviewPlr) throws UnsupportedEncodingException
	{
		String olr = new String(reviewPlr.getBytes("ISO-8859-1"), "UTF-8");
		return userService.checkPlrSFinUser(olr);

	}

	@RequestMapping(value = "/qtBbs", method = RequestMethod.GET)
	public String qtBbs(Model model, Ltbk ltbk)
	{
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		ltbk = ltbkList.get(0);
		String ltbkName = ltbk.getLtbkName();
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbkName", ltbkName);
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbkList", ltbkList);
		return "bbs/qtBbs";
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadqtBbsData", method = RequestMethod.POST)
	public ModelAndView LoadqtBbsData(QueryBbs qb, Model model)
	{
		if (qb.getBbsBqid().equals(""))
		{
			List<Ltbk> ltbkList = ltbkService.queryLtbk();
			Ltbk ltbk = new Ltbk();
			ltbk = ltbkList.get(0);
			qb.setBbsBqid(ltbk.getLtbkName());
			List<Bbs> bbsList = bbsService.selectByWhere(qb);
			// List<Review> reviewList = reviewService.queryReview();
			// m.addObject("reviewList", reviewList);
			List<Ltbq> ltbqList = ltbqService.queryLtbq();
			ModelAndView m = new ModelAndView();
			m.addObject("bbsList", bbsList);
			m.addObject("ltbqList", ltbqList);
			return m;
		} else
		{
			ModelAndView m = new ModelAndView();
			List<Bbs> bbsList = bbsService.selectqtBbsByWhere(qb);
			// List<Review> reviewList = reviewService.queryReview();
			// m.addObject("reviewList", reviewList);
			List<Ltbq> ltbqList = ltbqService.queryLtbq();
			m.addObject("bbsList", bbsList);
			m.addObject("ltbqList", ltbqList);
			return m;
		}
		// return bbsList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadqtBbsDataCount", method = RequestMethod.POST)
	public int LoadqtBbsDataCount(QueryBbs qb)
	{
		if (qb.getBbsBqid().equals(""))
		{
			List<Ltbk> ltbkList = ltbkService.queryLtbk();
			Ltbk ltbk = new Ltbk();
			ltbk = ltbkList.get(0);
			qb.setBbsBqid(ltbk.getLtbkName());
			int intCount = bbsService.selectqtBbsCount(qb);
			return intCount;
		} else
		{
			int intCount = bbsService.selectCount(qb);
			return intCount;
		}
	}

	// ==================================================================================================================
	/**
	 * 
	 */
	@RequestMapping(value = "/queryBbsByLtbkId", method = RequestMethod.GET)
	public String queryBbsByLtbkId(Bbs bbs, Model model, HttpSession session)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		List<Bbs> bbsList = bbsService.queryBbsByLtbkId(bbs.getLtbkId());
		model.addAttribute("bbsList", bbsList);
		Ltbk ltbk = ltbkService.selectByPrimaryKey(bbs.getLtbkId());
		model.addAttribute("ltbk", ltbk);
		String s = (String) session.getAttribute("ltbkId");
		if (s == null || s == "")
		{
			session.setAttribute("ltbkId", bbs.getLtbkId());
		} else
		{
			session.removeAttribute("ltbkId");
			session.setAttribute("ltbkId", bbs.getLtbkId());
		}
		return "jlsq/kglt";
	}

	@ResponseBody
	@RequestMapping(value = "/addZan", method = RequestMethod.POST)
	public boolean addZan(Zan zan, HttpSession session)
	{
		int checkCount = zanService.checkZan(zan);
		if (checkCount > 0)
		{
			return false;
		} else
		{
			zan.setZanId(RandomGUIDUtil.generateKey());
			zan.setZanAddtime(new Date());
			int count = zanService.addZan(zan);
			if (count > 0)
			{
				ltbkService.updateLtbkSJ((String) session.getAttribute("ltbkId"));
				return true;
			} else
			{
				return false;
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/BbsByLtbkIdData", method = RequestMethod.POST)
	public ModelAndView BbsByLtbkIdData(QueryBbs qb)
	{
		List<Review> reviewList = reviewService.queryReview();
		List<Bbs> bbsList = bbsService.BbsByLtbkIdData(qb);
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewList", reviewList);
		mav.addObject("bbsList", bbsList);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/BbsByLtbkIdDataCount", method = RequestMethod.POST)
	public int BbsByLtbkIdDataCount(QueryBbs qb)
	{
		int intCount = bbsService.BbsByLtbkIdDataCount(qb);
		return intCount;
	}

	@RequestMapping(value = "/getBbsById", method = RequestMethod.GET)
	public String getBbsById(Bbs bbs, Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		bbs = bbsService.getBbsById(bbs.getBbsId());
		model.addAttribute("bbs", bbs);
		return "bbs/oneBbs";
	}

	@RequestMapping(value = "/htqueryBbsById", method = RequestMethod.GET)
	public String htqueryBbsById(String bbsId, Model model)
	{
		Bbs bbs = bbsService.getBbsById(bbsId);
		List<Review> reviewList = reviewService.queryByBbsId(bbsId);
		model.addAttribute("bbs", bbs);
		model.addAttribute("reviewList", reviewList);
		return "sqlt/showBbsById";
	}

	// --------====================================================================================================
	/**
	 * 跳转页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/htBbs", method = RequestMethod.GET)
	public String htBbs(Model model)
	{
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		model.addAttribute("ltbkList", ltbkList);
		return "bbs/htBbs";
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/htBbsData", method = RequestMethod.POST)
	public ModelAndView htBbsData(QueryBbs qb)
	{
		List<Bbs> bbsList = bbsService.htBbsData(qb);
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		ModelAndView m = new ModelAndView();
		m.addObject("bbsList", bbsList);
		m.addObject("ltbqList", ltbqList);
		return m;
		// return bbsList;
	}

	@ResponseBody
	@RequestMapping(value = "/htBbsCount", method = RequestMethod.POST)
	public int htBbsCount(QueryBbs qb)
	{
		int intCount = bbsService.htBbsCount(qb);
		return intCount;
	}

	@RequestMapping(value = "/htBbsAdd", method = RequestMethod.GET)
	public String htBbsAdd(Model model, Bbs bbs)
	{
		List<Ltbk> ltbkList = ltbkService.queryLtbk();
		model.addAttribute("ltbkList", ltbkList);
		if (bbs.getBbsId() == null || bbs.getBbsId() == "")
		{
			return "bbs/htBbsAdd";
		} else
		{
			bbs = bbsService.selectByPrimaryKey(bbs.getBbsId());
			model.addAttribute("bbs", bbs);
			return "bbs/htBbsAdd";
		}
	}

	@RequestMapping(value = "/htBbsSave", method = RequestMethod.POST)
	public String htBbsSave(Bbs bbs, HttpSession session)
	{
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (bbs.getBbsId() == null || bbs.getBbsId() == "")
		{
			bbs.setBbsFbr(zzjg.getZzjgDwmc());
			bbs.setBbsFbsj(new Date());
			bbs.setBbsId(RandomGUIDUtil.generateKey());
			bbs.setBbsDelflag((long) 1);
			bbs.setBbsZan("0");
			bbsService.insertBbs(bbs);
		} else
		{
			bbs.setBbsFbr(zzjg.getZzjgDwmc());
			bbs.setBbsFbsj(new Date());
			bbsService.updateByPrimaryKeySelective(bbs);
		}
		return "bbs/htBbs";
	}

}
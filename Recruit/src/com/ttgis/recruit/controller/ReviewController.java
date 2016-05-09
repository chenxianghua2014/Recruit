package com.ttgis.recruit.controller;

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
import com.ttgis.recruit.domain.QueryReview;
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.BbsService;
import com.ttgis.recruit.service.LtbkService;
import com.ttgis.recruit.service.ReplyService;
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。ReviewController
 * @创建人： 范井龙
 * @日期：
 * @描述： 论坛回复操作
 * @版本号：
 */
@Controller
public class ReviewController
{

	static Logger logger = Logger.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LtbkService ltbkService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ReplyService replyService;

	/**
	 * 保存 修改操作
	 * 
	 * @param review
	 * @return
	 */
	@RequestMapping(value = "/ReviewSave", method = RequestMethod.POST)
	public ModelAndView BbsSave(Review review, HttpSession session, Userinfo user, String bbsId)
	{
		user = (Userinfo) session.getAttribute("userSession");
		review.setBbsId(bbsId);
		review.setReviewPlr(user.getUserName());
		review.setReviewPlsj(new Date());
		review.setReviewId(RandomGUIDUtil.generateKey());
		review.setReviewDelflag((long) 1);
		reviewService.insertSelective(review);
		return new ModelAndView("redirect:/queryMark");
	}

	@RequestMapping(value = "/ReviewSave1", method = RequestMethod.POST)
	public ModelAndView BbsSave1(Review review, HttpSession session, Zzjg zzjg, String bbsId)
	{

		zzjg = (Zzjg) session.getAttribute("loginSession");
		if (!zzjg.getSfjy().equals("1"))
		{
			review.setBbsId(bbsId);
			review.setReviewPlr(zzjg.getZzjgDwmc());
			review.setReviewPlsj(new Date());
			review.setReviewId(RandomGUIDUtil.generateKey());
			review.setReviewDelflag((long) 1);
			reviewService.insertSelective(review);
		} else
		{
			return new ModelAndView("sqlt/userJY");
		}
		return new ModelAndView("redirect:/bbs");
	}

	@RequestMapping(value = "/queryByBbsId", method = RequestMethod.GET)
	public ModelAndView queryByBbsId(Model model, String bbsId)
	{
		List<Review> reviewList = reviewService.queryByBbsId(bbsId);
		model.addAttribute("reviewList", reviewList);
		return new ModelAndView("sqlt/bbsList");
	}

	/**
	 * 删除标签信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delReview", method = RequestMethod.GET)
	public String delReview(String reviewId)
	{
		reviewService.delReview(reviewId);
		return "redirect:/bbs";
	}

	@RequestMapping(value = "/delReview1", method = RequestMethod.GET)
	public String delReview1(String reviewId)
	{
		reviewService.delReview(reviewId);
		return "redirect:/queryMarkBbs";
	}

	// ---------------------------------前台评论分页---------------------------------------------------------
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
	@RequestMapping(value = "/LoadReviewData", method = RequestMethod.POST)
	public List<Review> LoadArticleData(QueryReview qr)
	{
		List<Review> reviewList = reviewService.selectByWhere(qr);
		return reviewList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadReviewDataCount", method = RequestMethod.POST)
	public int LoadArticleDataCount(QueryReview qr)
	{
		int intCount = reviewService.selectCount(qr);
		return intCount;
	}

	@RequestMapping(value = "/qtReviewSave", method = RequestMethod.POST)
	public ModelAndView qtReviewSave(Review review, HttpSession session, Userinfo user, String bbsId)
	{

		user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return new ModelAndView("sqlt/Noqtlogin");
		} else
		{
			if (!(user.getSfjy()).equals("1"))
			{
				review.setBbsId(bbsId);
				review.setReviewPlr(user.getUserName());
				review.setReviewPlsj(new Date());
				review.setReviewId(RandomGUIDUtil.generateKey());
				review.setReviewDelflag((long) 1);
				reviewService.insertSelective(review);
				ltbkService.updateLtbkSJ((String) session.getAttribute("ltbkId"));
			} else
			{
				return new ModelAndView("sqlt/qtUserJy");
			}
			return new ModelAndView("redirect:/getBbsById?bbsId=" + bbsId);
		}
	}

	@RequestMapping(value = "/qtLtbqReviewSave", method = RequestMethod.POST)
	public ModelAndView qtLtbqReviewSave(Review review, HttpSession session, Userinfo user, String bbsId)
	{

		user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return new ModelAndView("sqlt/Noqtlogin");
		} else
		{
			if (!(user.getSfjy()).equals("1"))
			{
				review.setBbsId(bbsId);
				review.setReviewPlr(user.getUserName());
				review.setReviewPlsj(new Date());
				review.setReviewId(RandomGUIDUtil.generateKey());
				review.setReviewDelflag((long) 1);
				reviewService.insertSelective(review);
			} else
			{
				return new ModelAndView("sqlt/qtUserJY");
			}
			return new ModelAndView("redirect:/getBByLtbq?bbsId=" + bbsId);

		}
	}

	// ================================================================================================
	@RequestMapping(value = "/htBbsReview", method = RequestMethod.GET)
	public String htBbsReview(String bbsId, Model model)
	{
		Bbs bbs = bbsService.selectByPrimaryKey(bbsId);
		model.addAttribute("bbs", bbs);
		model.addAttribute("bbsId", bbsId);
		return "bbs/htBbsReview";
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
	@RequestMapping(value = "/htBbsReviewData", method = RequestMethod.POST)
	public List<Review> htBbsReviewData(QueryReview qr)
	{
		List<Review> reviewList = reviewService.htBbsReviewData(qr);
		return reviewList;
	}

	@ResponseBody
	@RequestMapping(value = "/htBbsReviewCount", method = RequestMethod.POST)
	public int htBbsReviewCount(QueryReview qr)
	{
		int intCount = reviewService.htBbsReviewCount(qr);
		return intCount;
	}

	@RequestMapping(value = "/delhtBbsReview", method = RequestMethod.GET)
	public String delhtBbsReview(Review review)
	{
		replyService.delhtBbsReviewReplyByReviewId(review.getReviewId());
		reviewService.delhtBbsReview(review.getReviewId());
		return "redirect:/htBbsReview?bbsId=" + review.getBbsId();
	}

	@RequestMapping(value = "/htBbsReviewSave", method = RequestMethod.POST)
	public String htBbsReviewSave(Review review, HttpSession session)
	{

		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		review.setBbsId(review.getBbsId());
		review.setReviewPlr(zzjg.getZzjgDwmc());
		review.setReviewPlsj(new Date());
		review.setReviewId(RandomGUIDUtil.generateKey());
		review.setReviewDelflag((long) 1);
		reviewService.insertSelective(review);
		return "redirect:/htBbsReview?bbsId=" + review.getBbsId();

	}

}

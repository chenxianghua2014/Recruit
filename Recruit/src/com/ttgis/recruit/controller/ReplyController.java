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

import com.ttgis.recruit.domain.QueryReply;
import com.ttgis.recruit.domain.Reply;
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ReplyService;
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

@Controller
public class ReplyController
{
	static Logger logger = Logger.getLogger(ReplyController.class);
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ReviewService reviewService;

	/**
	 * BbsReview 后台回复基本操作
	 */
	@RequestMapping(value = "/htBbsReviewReply", method = RequestMethod.GET)
	public String htBbsReviewReply(String reviewId, Model model)
	{
		Review review = reviewService.selectByPrimaryKey(reviewId);
		model.addAttribute("review", review);
		return "bbs/htBbsReviewReply";
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
	@RequestMapping(value = "/htBbsReviewReplyData", method = RequestMethod.POST)
	public List<Reply> htBbsReviewReplyData(QueryReply qr)
	{
		List<Reply> replyList = replyService.htBbsReviewReplyData(qr);
		return replyList;
	}

	@ResponseBody
	@RequestMapping(value = "/htBbsReviewReplyCount", method = RequestMethod.POST)
	public int htBbsReviewReplyCount(QueryReply qr)
	{
		int intCount = replyService.htBbsReviewReplyCount(qr);
		return intCount;
	}

	@RequestMapping(value = "/delhtBbsReviewReply", method = RequestMethod.GET)
	public String delhtBbsReviewReply(Reply reply)
	{
		replyService.delhtBbsReviewReply(reply.getReplyId());
		return "redirect:/htBbsReviewReply?reviewId=" + reply.getReviewId();
	}

	@RequestMapping(value = "/htBbsReviewReplySave", method = RequestMethod.POST)
	public String htBbsReviewReplySave(Reply reply, HttpSession session)
	{

		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		reply.setReviewId(reply.getReviewId());
		reply.setReplyName(zzjg.getZzjgDwmc());
		reply.setReplyAddtime(new Date());
		reply.setReplyId(RandomGUIDUtil.generateKey());
		reply.setReplyDelflag((long) 1);
		replyService.insertSelective(reply);
		return "redirect:/htBbsReviewReply?reviewId=" + reply.getReviewId();

	}

	/**
	 * reply 回复前台操作方法
	 */
	@ResponseBody
	@RequestMapping(value = "/qtGetReplyByReviewId", method = RequestMethod.POST)
	public List<Reply> qtGetReplyByReviewId(String reviewId)
	{
		List<Reply> replyList = replyService.qtGetReplyByReviewId(reviewId);
		return replyList;
	}

	@RequestMapping(value = "/qtBbsReplySave", method = RequestMethod.POST)
	public String qtBbsReplySave(Reply reply, HttpSession session, String bbsId, Model model)
	{
		Userinfo user = (Userinfo) session.getAttribute("userLoginSession");
		model.addAttribute("bbsId", bbsId);
		if (!(user.getSfjy()).equals("1"))
		{
			reply.setReviewId(reply.getReviewId());
			reply.setReplyName(user.getUserName());
			reply.setReplyAddtime(new Date());
			reply.setReplyId(RandomGUIDUtil.generateKey());
			reply.setReplyDelflag((long) 1);
			replyService.insertSelective(reply);
			return "redirect:/getBbsById";
		} else
		{
			return "bbs/qtBbsReplyJY";
		}
	}

	@RequestMapping(value = "/qtBbsLtbqReplySave", method = RequestMethod.POST)
	public String qtBbsLtbqReplySave(Reply reply, HttpSession session, String bbsId, Model model)
	{
		Userinfo user = (Userinfo) session.getAttribute("userLoginSession");
		model.addAttribute("bbsId", bbsId);
		if (!(user.getSfjy()).equals("1"))
		{
			reply.setReviewId(reply.getReviewId());
			reply.setReplyName(user.getUserName());
			reply.setReplyAddtime(new Date());
			reply.setReplyId(RandomGUIDUtil.generateKey());
			reply.setReplyDelflag((long) 1);
			replyService.insertSelective(reply);
			return "redirect:/getBByLtbq";
		} else
		{

			return "bbs/qtBbsLtbqReplyJY";
		}
	}
}

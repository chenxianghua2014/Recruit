package com.ttgis.recruit.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttgis.recruit.domain.QueryXqqzReview;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.XqqzReview;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XqqzReviewService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

/**
 * 
 * @类名： com.ttgis.recruit.controller。XqqzReviewController
 * @创建人： 范井龙
 * @日期：
 * @描述： 兴趣圈子回复管理
 * @版本号：
 */
@Controller
public class XqqzReviewController
{
	static Logger log = Logger.getLogger(XqqzReviewController.class);
	@Autowired
	private XqqzReviewService XqqzReviewService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/XqqzReviewData", method = RequestMethod.POST)
	public List<XqqzReview> XqqzReviewData(QueryXqqzReview qxr)
	{
		List<XqqzReview> xqqzReviewList = XqqzReviewService.selectByWhere(qxr);
		return xqqzReviewList;
	}

	@ResponseBody
	@RequestMapping(value = "/XqqzReviewDataCount", method = RequestMethod.POST)
	public int XqqzReviewDataCount(QueryXqqzReview qxr)
	{
		int intCount = XqqzReviewService.selectCount(qxr);
		return intCount;
	}

	@RequestMapping(value = "/saveXqqzReview", method = RequestMethod.POST)
	public String saveXqqzReview(XqqzReview xqqzReview, HttpSession session)
	{
		Userinfo user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return "sqlt/Noqtlogin";
		} else
		{
			if (!(user.getSfjy()).equals("1"))
			{
				xqqzReview.setXqqzReviewPlsj(new Date());
				xqqzReview.setXqqzReviewDelflag((long) 1);
				xqqzReview.setXqqzReviewAddtime(new Date());
				xqqzReview.setXqqzReviewId(RandomGUIDUtil.generateKey());
				xqqzReview.setXqqzReviewPlr(user.getUserName());
				XqqzReviewService.saveXqqzReview(xqqzReview);
			} else
			{
				return "sqlt/userArticleXqqzJy";
			}
			return "redirect:/queryXqqzById?xqqzId=" + xqqzReview.getXqqzId();
		}
	}

	@RequestMapping(value = "/htsaveXqqzReview", method = RequestMethod.POST)
	public String htsaveXqqzReview(XqqzReview xqqzReview, HttpSession session)
	{
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (!zzjg.getSfjy().equals("1"))
		{
			xqqzReview.setXqqzReviewPlsj(new Date());
			xqqzReview.setXqqzReviewDelflag((long) 1);
			xqqzReview.setXqqzReviewAddtime(new Date());
			xqqzReview.setXqqzReviewId(RandomGUIDUtil.generateKey());
			xqqzReview.setXqqzReviewPlr(zzjg.getZzjgDwmc());
			XqqzReviewService.saveXqqzReview(xqqzReview);
		} else
		{
			return "sqlt/xqqzJy";
		}
		return "redirect:/htqueryXqqzById?xqqzId=" + xqqzReview.getXqqzId();
	}

	@RequestMapping(value = "/queryXqqzReviewById", method = RequestMethod.GET)
	public String queryXqqzReviewById(String xqqzId, Model model)
	{
		model.addAttribute("xqqzId", xqqzId);
		return "sqlt/xqqzReviewList";
	}

	@ResponseBody
	@RequestMapping(value = "/xqqzReviewJy", method = RequestMethod.GET)
	public int xqqzReviewJy(String xqqzReviewPlr) throws UnsupportedEncodingException
	{
		String olr = new String(xqqzReviewPlr.getBytes("ISO-8859-1"), "UTF-8");
		return userService.userJygl(olr);

	}

	@RequestMapping(value = "/delXqqzReview", method = RequestMethod.GET)
	public String delXqqzReview(XqqzReview xqqzReview)
	{
		XqqzReviewService.delXqqzReview(xqqzReview.getXqqzReviewId());
		return "redirect:/queryXqqzReviewById??xqqzId=" + xqqzReview.getXqqzId();
	}

}

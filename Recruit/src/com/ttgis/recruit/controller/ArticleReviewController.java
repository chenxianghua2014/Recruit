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

import com.ttgis.recruit.domain.ArticleReview;
import com.ttgis.recruit.domain.QueryArticleReview;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ArticleReviewService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。ArticleReviewController
 * @创建人： 范井龙
 * @日期：
 * @描述： 博文回复
 * @版本号：
 */
@Controller
public class ArticleReviewController
{
	static Logger logger = Logger.getLogger(ArticleReviewController.class);
	@Autowired
	private ArticleReviewService articleReviewService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/ArticleReviewData", method = RequestMethod.POST)
	public List<ArticleReview> ArticleReviewData(QueryArticleReview qar)
	{
		List<ArticleReview> articleReviewList = articleReviewService.selectByWhere(qar);
		return articleReviewList;
	}

	@ResponseBody
	@RequestMapping(value = "/ArticleReviewDataCount", method = RequestMethod.POST)
	public int ArticleReviewDataCount(QueryArticleReview qar)
	{
		int intCount = articleReviewService.selectCount(qar);
		return intCount;
	}

	@RequestMapping(value = "/saveArticleReview", method = RequestMethod.POST)
	public String saveArticleReview(ArticleReview articleReview, HttpSession session)
	{
		Userinfo user = (Userinfo) session.getAttribute("userLoginSession");
		if (user == null)
		{
			return "sqlt/Noqtlogin";
		} else
		{
			if (!(user.getSfjy()).equals("1"))
			{
				articleReview.setArticleReviewPlsj(new Date());
				articleReview.setArticleReviewDelflag((long) 1);
				articleReview.setArticleReviewAddtime(new Date());
				articleReview.setArticleReviewId(RandomGUIDUtil.generateKey());
				articleReview.setArticleReviewPlr(user.getUserName());
				articleReviewService.saveArticleReview(articleReview);
			} else
			{
				return "sqlt/qtuserArticleJy";
			}
			return "redirect:/queryZytyById?articleId=" + articleReview.getArticleId();
		}
	}

	@RequestMapping(value = "/htsaveArticleReview", method = RequestMethod.POST)
	public String htsaveArticleReview(ArticleReview articleReview, HttpSession session)
	{
		Zzjg zzjg = (Zzjg) session.getAttribute("loginSession");
		if (!zzjg.getSfjy().equals("1"))
		{
			articleReview.setArticleReviewPlsj(new Date());
			articleReview.setArticleReviewDelflag((long) 1);
			articleReview.setArticleReviewAddtime(new Date());
			articleReview.setArticleReviewId(RandomGUIDUtil.generateKey());
			articleReview.setArticleReviewPlr(zzjg.getZzjgDwmc());
			articleReviewService.saveArticleReview(articleReview);
		} else
		{
			return "sqlt/userArticleJY";
		}
		return "redirect:/htqueryArticleById?articleId=" + articleReview.getArticleId();
	}

	@RequestMapping(value = "/queryArticleReviewById", method = RequestMethod.GET)
	public String queryArticleReviewById(String articleId, Model model)
	{
		model.addAttribute("articleId", articleId);
		return "sqlt/articleReviewList";
	}

	@ResponseBody
	@RequestMapping(value = "/articleReviewJy", method = RequestMethod.GET)
	public int articleReviewJy(String articleReviewPlr) throws UnsupportedEncodingException
	{
		String olr = new String(articleReviewPlr.getBytes("ISO-8859-1"), "UTF-8");
		return userService.userJygl(olr);

	}

	@RequestMapping(value = "/delArticleReview", method = RequestMethod.GET)
	public String delArticleReview(ArticleReview articleReview)
	{
		articleReviewService.delArticleReview(articleReview.getArticleReviewId());
		return "redirect:/queryarticleReviewById??articleId=" + articleReview.getArticleId();
	}

}

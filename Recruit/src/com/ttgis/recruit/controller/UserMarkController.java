package com.ttgis.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Article;
import com.ttgis.recruit.domain.Bbs;
import com.ttgis.recruit.domain.Review;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Xqqz;
import com.ttgis.recruit.service.ArticleService;
import com.ttgis.recruit.service.BbsService;
import com.ttgis.recruit.service.ReviewService;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.XqqzService;
import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。UserMarkController
 * @创建人： 范井龙
 * @日期：
 * @描述： 用户操作记录读取管理
 * @版本号：
 */
@Controller
public class UserMarkController
{
	static Logger logger = Logger.getLogger(UserMarkController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private XqqzService xqqzService;
	@Autowired
	private UserService userService;

	/**
	 * 查询用户操作记录
	 * 
	 * @param session
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dqyhjl", method = RequestMethod.GET)
	public ModelAndView dqyhjl()
	{
		return new ModelAndView("dqyhjl/userList");
	}

	@RequestMapping(value = "/queryMarkBbs", method = RequestMethod.GET)
	public String queryMarkBbs(HttpSession session, Userinfo user, Model model)
	{
		if (user.getUserId() == null)
		{
			user = (Userinfo) session.getAttribute("userSession");
		} else
		{
			user = userService.selectByPrimaryKey(user.getUserId());
			if (session.getAttribute("userSession") == null)
			{
				session.setAttribute("userSession", user);
			} else
			{
				session.removeAttribute("userSession");
				session.setAttribute("userSession", user);
			}
		}
		List<Bbs> bbsList = bbsService.queryBbsMark(user.getUserName());
		List<Review> reviewList = reviewService.queryReviewMark(user.getUserName());
		Bbs bbs = new Bbs();
		Review review = new Review();
		if (bbsList.size() > 0)
		{
			for (int i = 0; i < reviewList.size(); i++)
			{
				review = reviewList.get(i);
				for (int j = 0; j < bbsList.size(); j++)
				{
					bbs = bbsList.get(j);
					if (!bbs.getBbsId().equals(review.getBbsId()))
					{
						bbs = bbsService.getBbsById(review.getBbsId());
						boolean b = false;
						for (Bbs _bbs : bbsList)
						{
							if (_bbs.getBbsId().equals(bbs.getBbsId()))
								b = true;
						}
						if (!b)
						{
							bbsList.add(bbs);
						}
					}
				}
			}
		} else
		{
			for (int i = 0; i < reviewList.size(); i++)
			{
				review = reviewList.get(i);
				bbs = bbsService.getBbsById(review.getBbsId());
				bbsList.add(bbs);
			}
		}
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("bbsList", bbsList);
		return "sqlt/userMark";
	}

	@RequestMapping(value = "/queryMarkArticle", method = RequestMethod.GET)
	public String queryMarkArticle(HttpSession session, Userinfo user, Model model)
	{
		user = (Userinfo) session.getAttribute("userSession");
		List<Article> articleList = articleService.queryArticleMark(user.getUserName());
		model.addAttribute("articleList", articleList);
		return "sqlt/userMarkArticle";
	}

	@RequestMapping(value = "/queryMarkXqqz", method = RequestMethod.GET)
	public String queryMarkXqqz(HttpSession session, Userinfo user, Model model)
	{
		user = (Userinfo) session.getAttribute("userSession");
		List<Xqqz> xqqzList = xqqzService.queryXqqzMark(user.getUserName());
		model.addAttribute("xqqzList", xqqzList);
		return "sqlt/userMarkXqqz";
	}

	@RequestMapping(value = "/articleRview", method = RequestMethod.GET)
	public String articleRview(Article article, Model model)
	{
		article = articleService.selectByPrimaryKey(article.getArticleId());
		model.addAttribute("article", article);
		return "sqlt/articleReview";
	}

	@RequestMapping(value = "/xqqzRview", method = RequestMethod.GET)
	public String xqqzRview(Xqqz xqqz, Model model)
	{
		xqqz = xqqzService.selectByPrimaryKey(xqqz.getXqqzId());
		model.addAttribute("xqqz", xqqz);
		return "sqlt/xqqzReview";
	}
}

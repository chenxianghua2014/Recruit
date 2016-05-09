package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Article;
import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.QueryArticle;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.ArticleService;
import com.ttgis.recruit.service.LtbqService;
import com.ttgis.recruit.service.ZzjgService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。ArticleController
 * @创建人： 范井龙
 * @日期： 2014-05-15
 * @描述： 个人博文、经验博文，职业体验操作
 * @版本号：
 */
@Controller
public class ArticleController
{
	static Logger logger = Logger.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private LtbqService ltbqService;
	@Autowired
	private ZzjgService zzjgService;

	/**
	 * 跳转页面方法
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ModelAndView Article(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbqByBW();
		model.addAttribute("ltbqList", ltbqList);
		return new ModelAndView("sqlt/grbw");
	}

	/**
	 * 分页查询（已经审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadArticleData", method = RequestMethod.POST)
	public List<Article> LoadArticleData(QueryArticle qa)
	{
		List<Article> articleList = articleService.selectByWhere(qa);
		return articleList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadArticleDataCount", method = RequestMethod.POST)
	public int LoadArticleDataCount(QueryArticle qa)
	{
		int intCount = articleService.selectCount(qa);
		return intCount;
	}

	/**
	 * 跳转审核页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/articlesh", method = RequestMethod.GET)
	public ModelAndView Articlesh()
	{
		return new ModelAndView("sqlt/grbwsh");
	}

	/**
	 * 分页查询（未审核通过的信息）
	 * 
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadArticleData1", method = RequestMethod.POST)
	public List<Article> queryLtbqByPage1(QueryArticle qa)
	{
		List<Article> articleList = articleService.selectByWhere1(qa);
		return articleList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadArticleDataCount1", method = RequestMethod.POST)
	public int queryLtbqByPageCount1(QueryArticle qa)
	{
		int intCount = articleService.selectCount1(qa);
		return intCount;
	}

	/**
	 * 添加
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping(value = "/articleAdd", method = RequestMethod.GET)
	public ModelAndView articleAdd(@RequestParam(value = "articleId", required = false) String articleId, HttpSession session, Zzjg zzjg)
	{
		zzjg = (Zzjg) session.getAttribute("loginSession");
		if (articleId == null)
		{
			if (!zzjg.getSfjy().equals("1"))
			{
				return new ModelAndView("sqlt/grbwAdd");
			} else
			{
				return new ModelAndView("sqlt/userArticleJY");
			}
		} else
		{
			Article article = new Article();
			article = articleService.selectByPrimaryKey(articleId);
			return new ModelAndView("sqlt/grbwAdd", "article", article);
		}
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/ArticleSave", method = RequestMethod.POST)
	public ModelAndView ArticleSave(Article article, HttpSession session, Zzjg zzjg)
	{
		zzjg = (Zzjg) session.getAttribute("loginSession");
		if (article.getArticleId().equals(""))
		{
			if (!zzjg.getSfjy().equals("1"))
			{
				article.setArticleFbr(zzjg.getZzjgDwmc());
				article.setArticleAddtime(new Date());
				article.setArticleViewer("待审核");
				article.setArticleId(RandomGUIDUtil.generateKey());
				article.setArticleDelflag((long) 1);
				articleService.insertSelective(article);
			} else
			{
				return new ModelAndView("sqlt/userArticleJY");
			}
		} else
		{
			article.setArticleFbr(zzjg.getZzjgDwmc());
			article.setArticleViewer("待审核");
			articleService.updateByPrimaryKeySelective(article);
		}
		return new ModelAndView("redirect:/article");
	}

	/**
	 * 删除信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delArticle", method = RequestMethod.GET)
	public String delArticle(String articleId)
	{
		articleService.delArticle(articleId);
		return "sqlt/grbw";
	}

	@RequestMapping(value = "/delArticle1", method = RequestMethod.GET)
	public String delArticle1(String articleId)
	{
		articleService.delArticle(articleId);
		return "redirect:/queryMarkArticle";
	}

	/**
	 * 审核操作（查看需要审核信息）
	 * 
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shArticle", method = RequestMethod.GET)
	public String shArticle(String articleId, Model model)
	{
		Article article = articleService.selectByPrimaryKey(articleId);
		model.addAttribute("article", article);
		return "sqlt/showgrbw";
	}

	/**
	 * 审核操作
	 * 
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Articlesh", method = RequestMethod.POST)
	public String Articlesh(HttpSession session, Article article, Model model, Zzjg zzjg, String sfjy)
	{
		// 修改审核是否通过
		articleService.updateByPrimaryKeySelective(article);
		// 修改是否禁言
		zzjg = (Zzjg) session.getAttribute("loginSession");
		zzjg.setSfjy(sfjy);
		zzjgService.updateByPrimaryKeySelective(zzjg);
		return "redirect:/articlesh";
	}

	@RequestMapping(value = "/htqueryArticleById", method = RequestMethod.GET)
	public String htqueryArticleById(Article article, Model model)
	{
		article = articleService.selectByPrimaryKey(article.getArticleId());
		model.addAttribute("article", article);
		return "sqlt/articleLook";
	}

	// @RequestMapping(value = "/queryArticleByLtbq", method =
	// RequestMethod.GET)
	// public String queryArticleByLtbq(@RequestParam("ltbqName")String
	// ltbqName,Model model) throws UnsupportedEncodingException{
	// ltbqName = new String(ltbqName.getBytes("ISO-8859-1"), "UTF-8");
	// List<Ltbq> ltbqList = ltbqService.queryLtbq();
	// model.addAttribute("ltbqList", ltbqList);
	// List<Review> reviewList = reviewService.queryReview();
	// model.addAttribute("reviewList", reviewList);
	// List<Bbs> bbsList = bbsService.queryBbsByLtbq(ltbqName);
	// model.addAttribute("bbsList", bbsList);
	// return "sqlt/bbsList";
	// }

}

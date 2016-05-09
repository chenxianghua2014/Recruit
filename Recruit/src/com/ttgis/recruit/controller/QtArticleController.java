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

import com.ttgis.recruit.domain.Article;
import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.QueryArticle;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.service.ArticleService;
import com.ttgis.recruit.service.LtbqService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。QtArticleController
 * @创建人：范井龙
 * @日期： 
 * @描述：前台博文
 * @版本号：
 */
@Controller
public class QtArticleController {
	static Logger logger = Logger.getLogger(QtArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private LtbqService ltbqService;
	/**
	 * 跳转页面方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zyty", method = RequestMethod.GET)
	public ModelAndView zyty(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbqByBW();
		model.addAttribute("ltbqList", ltbqList);
		return new ModelAndView("zyty/zyty");
	}
	
	@RequestMapping(value = "/rmbwAdd", method = RequestMethod.GET)
	public ModelAndView rmbwAdd()
	{
		return new ModelAndView("rmbw/rmbwAdd");
	}
	/**
	 * 分页查询（已经审核通过的信息）
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZytyData", method = RequestMethod.POST)
	public List<Article> LoadZytyData(QueryArticle qa)
	{
		qa.setArticleType("经验博文（员工）");
		List<Article> articleList = articleService.LoadZytyData(qa);
		return articleList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadZytyDataCount", method = RequestMethod.POST)
	public int LoadZytyDataCount(QueryArticle qa)
	{
		qa.setArticleType("经验博文（员工）");
		int intCount = articleService.LoadZytyDataCount(qa);
		return intCount;
	}
	@RequestMapping(value = "/queryZytyById", method = RequestMethod.GET)
	public ModelAndView queryZytyById(Article article){
		article = articleService.selectByPrimaryKey(article.getArticleId());
		return new ModelAndView("zyty/zytyView", "article", article);
	}
	/**
	 * 跳转页面方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rmbw", method = RequestMethod.GET)
	public ModelAndView rmbw(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbqByBW();
		model.addAttribute("ltbqList", ltbqList);
		return new ModelAndView("rmbw/rmbw");
	}
	/**
	 * 分页查询（已经审核通过的信息）
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadRmbwData", method = RequestMethod.POST)
	public List<Article> LoadRmbwData(QueryArticle qa)
	{
		qa.setArticleType("经验博文（新人）");
		List<Article> articleList = articleService.LoadRmbwData(qa);
		return articleList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadRmbwDataCount", method = RequestMethod.POST)
	public int LoadRmbwDataCount(QueryArticle qa)
	{
		qa.setArticleType("经验博文（新人）");
		int intCount = articleService.LoadRmbwDataCount(qa);
		return intCount;
	}
	@RequestMapping(value = "/qtNewArticleSave", method = RequestMethod.POST)
	public ModelAndView qtNewArticleSave(Article article, HttpSession session,Userinfo user)
	{
		user = (Userinfo) session.getAttribute("userLoginSession");
		if(user == null){
			return new ModelAndView("sqlt/Noqtlogin");
		}else{
			if(!(user.getSfjy()).equals("1")){
				article.setArticleFbr(user.getUserName());
				article.setArticleAuthor(user.getUserName());
				article.setArticleType("经验博文（新人）");
				article.setArticleAddtime(new Date());
				article.setArticleViewer("待审核");
				article.setArticleId(RandomGUIDUtil.generateKey());
				article.setArticleDelflag((long) 1);
				articleService.insertSelective(article);
				return new ModelAndView("success","uri","qtNewArticleSave");
			}else{
				return new ModelAndView("sqlt/qtArticleSaveJy");
			}
		}
	}

}

package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.LtbqArticle;
import com.ttgis.recruit.domain.LtbqBbs;
import com.ttgis.recruit.domain.LtbqXqqz;
import com.ttgis.recruit.domain.QueryLtbq;
import com.ttgis.recruit.service.LtbqService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @类名： com.ttgis.recruit.controller。LtbqController
 * @创建人： 范井龙
 * @日期：
 * @描述： 标签操作
 * @版本号：
 */
@Controller
public class LtbqController
{
	static Logger logger = Logger.getLogger(LtbqController.class);

	@Autowired
	private LtbqService ltbqService;

	/**
	 * 跳转页面方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/luntan", method = RequestMethod.GET)
	public ModelAndView luntan()
	{
		return new ModelAndView("sqlt/luntan");
	}

	/**
	 * 分页查询
	 * 
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLtbqByPage", method = RequestMethod.POST)
	public List<Ltbq> queryLtbqByPage(QueryLtbq ql)
	{
		List<Ltbq> listLtbq = ltbqService.selectByWhere(ql);
		return listLtbq;
	}

	@ResponseBody
	@RequestMapping(value = "/queryLtbqByPageCount", method = RequestMethod.POST)
	public int queryLtbqByPageCount(QueryLtbq ql)
	{
		int intCount = ltbqService.selectCount(ql);
		return intCount;
	}

	@RequestMapping(value = "/ltbqAdd", method = RequestMethod.GET)
	public ModelAndView ltbqAdd(String ltbqId)
	{

		if (ltbqId == null)
		{
			return new ModelAndView("sqlt/ltbqAdd");
		} else
		{
			Ltbq ltbq = new Ltbq();
			ltbq = ltbqService.selectByPrimaryKey(ltbqId);
			return new ModelAndView("sqlt/ltbqAdd", "ltbq", ltbq);
		}
	}

	/**
	 * 添加标签
	 */
	@RequestMapping(value = "/SaveLtbq", method = RequestMethod.POST)
	public ModelAndView SaveLtbq(Ltbq ltbq)
	{

		if (ltbq.getLtbqId().equals(""))
		{
			Ltbq ltbqName = ltbqService.getLtbqByltbqName(ltbq.getLtbqName());
			if (ltbqName == null)
			{
				ltbq.setLtbqAddtime(new Date());
				ltbq.setLtbqDelflag((long) 1);
				ltbq.setLtbqId(RandomGUIDUtil.generateKey());
				ltbqService.insertLtbq(ltbq);
				return new ModelAndView("success", "uri", "luntan");
			} else
			{
				return new ModelAndView("window.onload=function(){alert('用户名已存在');}");
			}
		} else
		{
			ltbq.setLtbqAddtime(new Date());
			ltbq.setLtbqDelflag((long) 1);
			ltbqService.updateByPrimaryKey(ltbq);
			return new ModelAndView("sqlt/luntan");
		}
	}

	/**
	 * 通过标签名称删除
	 * 
	 * @param ltbqName
	 * @return
	 */
	@RequestMapping(value = "/UpdateLtbqDel", method = RequestMethod.POST)
	public ModelAndView UpdateLtbqDel(String ltbqName)
	{
		ltbqService.updateLtbqDel(ltbqName);
		return new ModelAndView("sqlt/luntan");
	}

	/**
	 * 查询标签信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryLtbq", method = RequestMethod.GET)
	public String queryLtbq(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbq();
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/bbsList";
	}

	/**
	 * 查询一条数据
	 * 
	 * @param ltbqId
	 * @param model
	 * @param ltbq
	 * @return
	 */
	@RequestMapping(value = "/getLtbqById", method = RequestMethod.GET)
	public String getLtbqById(String ltbqId, Model model, Ltbq ltbq)
	{
		ltbq = ltbqService.selectByPrimaryKey(ltbqId);
		model.addAttribute("ltbq", ltbq);
		return "sqlt/ltbqAdd";
	}

	/**
	 * 删除标签信息
	 * 
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delLtbq", method = RequestMethod.GET)
	public String delLtbq(String ltbqId)
	{
		ltbqService.delLtbq(ltbqId);
		ltbqService.delLtbqBbs(ltbqId);
		ltbqService.delLtbqArticle(ltbqId);
		ltbqService.delLtbqXqqz(ltbqId);
		return "sqlt/luntan";
	}

	/**
	 * 为标签不能搜索出的bbs添加到一个标签下
	 * 
	 * @param model
	 * @param bbsId
	 * @return
	 */
	@RequestMapping(value = "/checkLtbq", method = RequestMethod.GET)
	public String checkLtbq(Model model, String bbsId)
	{
		// 论坛的标签
		List<Ltbq> ltbqList = ltbqService.checkLtbq();
		model.addAttribute("bbsId", bbsId);
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/checkLtbq";
	}

	@RequestMapping(value = "/checkLtbqArticle", method = RequestMethod.GET)
	public String checkLtbqArticle(Model model, String articleId)
	{
		// 论坛的标签
		List<Ltbq> ltbqList = ltbqService.checkLtbqArticle();
		model.addAttribute("articleId", articleId);
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/checkLtbqArticle";
	}

	@RequestMapping(value = "/checkLtbqXqqz", method = RequestMethod.GET)
	public String checkLtbqXqqz(Model model, String xqqzId)
	{
		// 论坛的标签
		List<Ltbq> ltbqList = ltbqService.checkLtbqXqqz();
		model.addAttribute("xqqzId", xqqzId);
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/checkLtbqXqqz";
	}

	/**
	 * 为BBS选择个标签归属
	 * 
	 * @param lb
	 * @return
	 */
	@RequestMapping(value = "/addLtbqBbs", method = RequestMethod.POST)
	public ModelAndView addLtbqBbs(LtbqBbs lb)
	{
		ltbqService.delLtbqBbs(lb.getBbsId());
		ltbqService.addLtbqBbs(lb);
		return new ModelAndView("redirect:/bbs");
	}

	@RequestMapping(value = "/addLtbqArticle", method = RequestMethod.POST)
	public ModelAndView addLtbqArticle(LtbqArticle la)
	{
		ltbqService.delLtbqArticle(la.getArticleId());
		ltbqService.addLtbqArticle(la);
		return new ModelAndView("redirect:/article");
	}

	@RequestMapping(value = "/addLtbqXqqz", method = RequestMethod.POST)
	public ModelAndView addLtbqXqqz(LtbqXqqz lx)
	{
		ltbqService.delLtbqXqqz(lx.getXqqzId());
		ltbqService.addLtbqXqqz(lx);
		return new ModelAndView("redirect:/xqqz");
	}

	// ===========================================================================
	@RequestMapping(value = "/htBbsCheckLtbq", method = RequestMethod.GET)
	public String htBbsCheckLtbq(Model model, String bbsId)
	{
		// 论坛的标签
		List<Ltbq> ltbqList = ltbqService.checkLtbq();
		model.addAttribute("bbsId", bbsId);
		model.addAttribute("ltbqList", ltbqList);
		return "bbs/checkLtbq";
	}

	/**
	 * 为BBS选择个标签归属
	 * 
	 * @param lb
	 * @return
	 */
	@RequestMapping(value = "/htBbsAddLtbqBbs", method = RequestMethod.POST)
	public ModelAndView htBbsAddLtbqBbs(LtbqBbs lb)
	{
		ltbqService.delLtbqBbs(lb.getBbsId());
		ltbqService.addLtbqBbs(lb);
		return new ModelAndView("redirect:/htBbs");
	}
}

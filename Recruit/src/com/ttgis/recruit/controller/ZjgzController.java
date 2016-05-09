package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.QueryZjgz;
import com.ttgis.recruit.domain.Zjgz;
import com.ttgis.recruit.service.ZjgzService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

@Controller
public class ZjgzController
{
	@Resource
	ZjgzService zjgzService;

	@RequestMapping(value = "/Zjgz", method = RequestMethod.GET)
	public ModelAndView Zjgz()
	{
		return new ModelAndView("Zjgz/Zjgz");
	}

	@RequestMapping(value = "/Zjgzxz", method = RequestMethod.GET)
	public ModelAndView Zjgzxz()
	{
		return new ModelAndView("Zjgz/Zjgzxz");
	}

	@RequestMapping(value = "/Zjgzjs", method = RequestMethod.GET)
	public ModelAndView Zjgzxzjs()
	{
		return new ModelAndView("Zjgz/Zjgzjs");
	}

	/**
	 * 查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param currentPageSize
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZjgzData", method = RequestMethod.POST)
	public List<Zjgz> LoadZjgzData(QueryZjgz qz)
	{
		List<Zjgz> zjgz = zjgzService.selectByWhere(qz);
		return zjgz;
	}

	/**
	 * 查询总条数
	 * 
	 * @param keywords
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadZjgzDataCount", method = RequestMethod.POST)
	public int LoadZjgzDataCount(QueryZjgz qz, String keywords)
	{

		PageCondition pageCondition = new PageCondition();
		pageCondition.setKeywords(keywords);
		int intCount = zjgzService.selectCount(qz);
		return intCount;
	}

	/**
	 * 添加修改页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ZjgzAdd", method = RequestMethod.GET)
	public ModelAndView AddZjgz(@RequestParam(value = "zjgzId", required = false) String zjgzId)
	{
		if (zjgzId == null)
		{
			return new ModelAndView("Zjgz/ZjgzAdd");
		} else
		{
			Zjgz zjgz = new Zjgz();
			zjgz = zjgzService.selectByPrimaryKey(zjgzId);
			return new ModelAndView("Zjgz/ZjgzAdd", "zjgz", zjgz);
		}
	}

	@RequestMapping(value = "/ZjgzView", method = RequestMethod.GET)
	public String ZjgzView(Zjgz zjgz, Model model)
	{
		zjgz = zjgzService.selectByPrimaryKey(zjgz.getZjgzId());
		model.addAttribute("zjgz", zjgz);
		return "Zjgz/ZjgzView";
	}

	/**
	 * 保存 修改操作
	 * 
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/ZjgzSava", method = RequestMethod.POST)
	public ModelAndView ZjgzSava(Zjgz zjgz)
	{
		if (zjgz.getZjgzId().equals(""))
		{
			zjgz.setZjgzDelflag((long) 1);
			zjgz.setZjgzAddtime(new Date());
			zjgz.setZjgzId(RandomGUIDUtil.generateKey());
			zjgzService.insertzjgz(zjgz);
			;

		} else
		{
			zjgzService.updateByPrimaryKeySelective(zjgz);
		}
		return new ModelAndView("redirect:/Zjgz");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/delZjgz", method = RequestMethod.GET)
	public String delZjgz(String zjgzId)
	{

		zjgzService.deleteByPrimaryKey(zjgzId);
		return "Zjgz/Zjgz";
	}
}

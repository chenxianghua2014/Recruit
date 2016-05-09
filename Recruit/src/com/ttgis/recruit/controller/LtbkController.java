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

import com.ttgis.recruit.domain.Ltbk;
import com.ttgis.recruit.domain.QueryLtbk;
import com.ttgis.recruit.domain.Tlq;
import com.ttgis.recruit.service.LtbkService;
import com.ttgis.recruit.service.TlqService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。LtbkController
 * @创建人：范井龙
 * @日期： 
 * @描述： 论坛板块
 * @版本号：
 */
@Controller
public class LtbkController {
	static Logger logger = Logger.getLogger(LtbkController.class);
	@Autowired
	private LtbkService ltbkService;
	@Autowired
	private TlqService tlqService;
	
	/**
	 * 跳转页面方法
	 * @return
	 */
	@RequestMapping(value = "/ltbk", method = RequestMethod.GET)
	public ModelAndView luntan()
	{
		return new ModelAndView("sqlt/ltbk");
	}
	
	/**
	 * 分页查询
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadLtbkData", method = RequestMethod.POST)
	public List<Ltbk> queryLtbkByPage(QueryLtbk ql)
	{
		List<Ltbk> listLtbk = ltbkService.selectByWhere(ql);
		return listLtbk;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadLtbkDataCount", method = RequestMethod.POST)
	public int queryLtbkByPageCount(QueryLtbk ql)
	{
		int intCount = ltbkService.selectCount(ql);
		return intCount;
	}
	@RequestMapping(value = "/LtbkAdd", method = RequestMethod.GET)
	public String LtbkAdd(String ltbkId,Model model)
	{
		List<Tlq> tlqList = tlqService.queryTlq();
		model.addAttribute("tlqList", tlqList);
		if (ltbkId == null)
		{
			
			return "sqlt/ltbkAdd";
		} else
		{
			Ltbk ltbk = ltbkService.selectByPrimaryKey(ltbkId);
			model.addAttribute("ltbk", ltbk);
			return "sqlt/ltbkAdd";
		}
	}
	/**
	 * 添加标签
	 */
	@RequestMapping(value="/SaveLtbk",method = RequestMethod.POST)
	public String SaveLtbk(Ltbk ltbk){
		
		if(ltbk.getLtbkId().equals("")){
			ltbk.setLtbkAddtime(new Date());
			ltbk.setLtbkDelflag("1");
			ltbk.setLtbkId(RandomGUIDUtil.generateKey());
			ltbkService.insertLtbk(ltbk);
			return "sqlt/ltbk";
		}else{
			ltbk.setLtbkAddtime(new Date());
			ltbk.setLtbkDelflag("1");
			ltbkService.updateByPrimaryKey(ltbk);
			return "sqlt/ltbk";
		}
	}
	/**
	 * 删除标签信息
	 * @param LtbkId
	 * @return
	 */
	@RequestMapping(value = "/delLtbk",method = RequestMethod.GET)
	public String delLtbk(String ltbkId){
		ltbkService.delLtbk(ltbkId);
		return "sqlt/ltbk";
	}
}

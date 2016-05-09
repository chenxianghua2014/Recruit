package com.ttgis.recruit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttgis.recruit.domain.QueryXqqzLxgl;
import com.ttgis.recruit.domain.XqqzLxgl;
import com.ttgis.recruit.service.XqqzLxglService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;

import org.apache.log4j.Logger;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。ArticleController
 * @创建人： 范井龙
 * @日期： 
 * @描述：  兴趣圈子类型管理
 * @版本号：
 */
@Controller
public class XqqzLxglController {
	static Logger logger = Logger.getLogger(XqqzLxglController.class);
	@Autowired
	private XqqzLxglService xqqzLxglService;
	
	@RequestMapping(value = "/xqqzLxgl", method = RequestMethod.GET)
	public String xqqzLxgl(){
		return "sqlt/xqqzLxgl";
	}
	/**
	 * 分页查询
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/XqqzLxglData", method = RequestMethod.POST)
	public List<XqqzLxgl> XqqzLxglData(QueryXqqzLxgl qxl)
	{
		List<XqqzLxgl> xqqzLxglList = xqqzLxglService.XqqzLxglData(qxl);
		return xqqzLxglList;
	}

	@ResponseBody
	@RequestMapping(value = "/XqqzLxglCount", method = RequestMethod.POST)
	public int XqqzLxglCount(QueryXqqzLxgl qxl)
	{
		int intCount = xqqzLxglService.XqqzLxglCount(qxl);
		return intCount;
	}
	
	@RequestMapping(value = "/xqqzLxglAdd", method = RequestMethod.GET)
	public String xqqzLxglAdd(XqqzLxgl xqqzLxgl,Model model){
		if(xqqzLxgl.getXqqzlxglId() == null || xqqzLxgl.getXqqzlxglId() == ""){
			
			return "sqlt/xqqzLxglAdd";
		}else{
			xqqzLxgl = xqqzLxglService.selectByPrimaryKey(xqqzLxgl.getXqqzlxglId());
			model.addAttribute("xqqzLxgl", xqqzLxgl);
			return "sqlt/xqqzLxglAdd";
		}

	}
	
	@RequestMapping(value = "/xqqzLxglSave", method = RequestMethod.POST)
	public String xqqzLxglSave(XqqzLxgl xqqzLxgl){
		if(xqqzLxgl.getXqqzlxglId() == null || xqqzLxgl.getXqqzlxglId() == ""){
			xqqzLxgl.setXqqzlxglId(RandomGUIDUtil.generateKey());
			xqqzLxgl.setXqqzlxglDelflag((long) 1);
			xqqzLxgl.setXqqzlxglAddtime(new Date());
			xqqzLxglService.insertSelective(xqqzLxgl);
			return "redirect:/xqqzLxgl";
		}else{
			xqqzLxglService.updateByPrimaryKeySelective(xqqzLxgl);
			return "redirect:/xqqzLxgl";
		}

	}
	
	@RequestMapping(value = "/delXqqzLxgl", method = RequestMethod.GET)
	public String delXqqzLxgl(String xqqzlxglId){
		xqqzLxglService.deleteByPrimaryKey(xqqzlxglId);
		return "redirect:/xqqzLxgl";
	}
	@ResponseBody
	@RequestMapping(value = "/XqqzCount", method = RequestMethod.GET)
	public int XqqzCount(String xqqzlxglId)
	{
		int intCount = xqqzLxglService.XqqzCount(xqqzlxglId);
		return intCount;
	}

}

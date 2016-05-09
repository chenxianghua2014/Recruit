package com.ttgis.recruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.QueryTlq;
import com.ttgis.recruit.domain.Tlq;
import com.ttgis.recruit.service.LtbkService;
import com.ttgis.recruit.service.TlqService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
import org.apache.log4j.Logger;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。TlqController
 * @创建人： 范井龙
 * @日期： 
 * @描述：讨论区管理
 * @版本号：
 */
@Controller
public class TlqController {
	static Logger logger = Logger.getLogger(TlqController.class);
	
	@Autowired
	private TlqService tlqService;
	@Autowired
	private LtbkService ltbkService;
	
	@RequestMapping(value = "/tlq", method = RequestMethod.GET)
	public String Tlq(){
		return "tlq/tlq";
	}
	/**
	 * 分页查询
	 * @param ql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/TlqData", method = RequestMethod.POST)
	public List<Tlq> TlqData(QueryTlq qt)
	{
		List<Tlq> listTlq = tlqService.selectByWhere(qt);
		return listTlq;
	}

	@ResponseBody
	@RequestMapping(value = "/TlqDataCount", method = RequestMethod.POST)
	public int TlqDataCount(QueryTlq qt)
	{
		int intCount = tlqService.selectCount(qt);
		return intCount;
	}
	@RequestMapping(value = "/tlqAdd", method = RequestMethod.GET)
	public ModelAndView TlqAdd(String tlqId)
	{
		
		if (tlqId == null)
		{
			return new ModelAndView("tlq/addTlq");
		} else
		{
			Tlq tlq = new Tlq();
			tlq = tlqService.selectByPrimaryKey(tlqId);
			return new ModelAndView("tlq/addTlq", "tlq", tlq);
		}
	}
	/**
	 * 添加标签
	 */
	@RequestMapping(value="/tlqSave",method = RequestMethod.POST)
	public String SaveTlq(Tlq Tlq){
		
		if(Tlq.getTlqId().equals("")){
			Tlq.setTlqDelflag((long)1);
			Tlq.setTlqId(RandomGUIDUtil.generateKey());
			tlqService.insertTlq(Tlq);
			return "tlq/tlq";
		}else{
			Tlq.setTlqDelflag((long)1);
			tlqService.updateByPrimaryKey(Tlq);
			return "tlq/tlq";
		}
	}
	/**
	 * 删除标签信息
	 * @param TlqId
	 * @return
	 */
	@RequestMapping(value = "/delTlq",method = RequestMethod.GET)
	public String delTlq(String tlqId){
		ltbkService.delLtbkByTlqId(tlqId);
		tlqService.delTlq(tlqId);
		return "tlq/tlq";
	}
	

}

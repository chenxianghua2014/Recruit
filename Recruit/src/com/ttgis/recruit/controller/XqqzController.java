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

import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.QueryXqqz;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Xqqz;
import com.ttgis.recruit.domain.XqqzLxgl;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.LtbqService;
import com.ttgis.recruit.service.XqqzLxglService;
import com.ttgis.recruit.service.XqqzService;
import com.ttgis.recruit.utility.random.RandomGUIDUtil;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。XqqzController
 * @创建人： 范井龙
 * @日期： 
 * @描述： 兴趣圈子管理
 * @版本号：
 */
@Controller
public class XqqzController {
	
	@Autowired
	private XqqzService xqqzService;
	@Autowired
	private LtbqService ltbqService;
	@Autowired
	private XqqzLxglService xqqzLxglService;
	/**
	 * 跳转页面方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/xqqz", method = RequestMethod.GET)
	public String xqqz(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbqByQZ();
		List<XqqzLxgl> xqqzLxglList = xqqzLxglService.queryXqqzLxgl();
		model.addAttribute("xqqzLxglList", xqqzLxglList);
		model.addAttribute("ltbqList", ltbqList);
		return "sqlt/xqqz";
	}
	
	/**
	 * 分页查询
	 * @param qx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadXqqzData", method = RequestMethod.POST)
	public List<Xqqz> queryLtbqByPage(QueryXqqz qx)
	{
		List<Xqqz> xqqzList = xqqzService.selectByWhere(qx);
		return xqqzList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadXqqzDataCount", method = RequestMethod.POST)
	public int queryLtbqByPageCount(QueryXqqz qx)
	{
		int intCount = xqqzService.selectCount(qx);
		return intCount;
	}
	/**
	 * 添加个人博文
	 * @param xqqz
	 * @return
	 */
	@RequestMapping(value = "/xqqzAdd", method = RequestMethod.GET)
	public ModelAndView xqqzAdd(String xqqzId,Model model)
	{
		List<XqqzLxgl> xqqzLxglList = xqqzLxglService.queryXqqzLxgl();
		model.addAttribute("xqqzLxglList", xqqzLxglList);
		if (xqqzId == null)
		{
			return new ModelAndView("sqlt/xqqzAdd");
		} else
		{
			Xqqz xqqz = new Xqqz();
			xqqz = xqqzService.selectByPrimaryKey(xqqzId);
			return new ModelAndView("sqlt/xqqzAdd", "xqqz", xqqz);
		}
	}
	@RequestMapping(value = "/htqueryXqqzById", method = RequestMethod.GET)
	public String htqueryXqqzById(Xqqz xqqz,Model model){
		xqqz = xqqzService.selectByPrimaryKey(xqqz.getXqqzId());
		model.addAttribute("xqqz", xqqz);
		return "sqlt/xqqzLook";
	}
	/**
	 * 保存 修改操作
	 * @param xcxx
	 * @return
	 */
	@RequestMapping(value = "/xqqzSave", method = RequestMethod.POST)
	public ModelAndView xqqzSave(Xqqz xqqz, HttpSession session,Zzjg zzjg)
	{
		if (xqqz.getXqqzId().equals(""))
		{
			zzjg = (Zzjg) session.getAttribute("loginSession");
			xqqz.setXqqzFbr(zzjg.getZzjgDwmc());
			xqqz.setXqqzFbsj(new Date());
			xqqz.setXqqzId(RandomGUIDUtil.generateKey());
			xqqz.setXqqzDelflag("1");
			xqqzService.insertSelective(xqqz);
		} else
		{
			xqqzService.updateByPrimaryKeySelective(xqqz);
		}
		return new ModelAndView("redirect:/xqqz");
	}
	
	/**
	 * 删除信息
	 * @param ltbqId
	 * @return
	 */
	@RequestMapping(value = "/delXqqz",method = RequestMethod.GET)
	public String delXqqz(String xqqzId){
		xqqzService.delXqqz(xqqzId);
		return "redirect:/xqqz";
	}
	@RequestMapping(value = "/delXqqz1",method = RequestMethod.GET)
	public String delXqqz1(String xqqzId){
		xqqzService.delXqqz(xqqzId);
		return "redirect:/queryMarkXqqz";
	}
//---------------------------------------前台兴趣圈子-------------------------------------------------
	
	@RequestMapping(value = "/qtXqqz", method = RequestMethod.GET)
	public String qtXqqz(Model model)
	{
		List<Ltbq> ltbqList = ltbqService.queryLtbqByQZ();
		List<XqqzLxgl> xqqzLxglList = xqqzLxglService.queryXqqzLxgl();
		model.addAttribute("xqqzLxglList", xqqzLxglList);
		model.addAttribute("ltbqList", ltbqList);
		return "xqqz/qtXqqz";
	}
	@ResponseBody
	@RequestMapping(value = "/LoadqtXqqzData", method = RequestMethod.POST)
	public List<Xqqz> LoadqtXqqzData(QueryXqqz qx)
	{
		List<Xqqz> xqqzList = xqqzService.qtselectByWhere(qx);
		return xqqzList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadqtXqqzDataCount", method = RequestMethod.POST)
	public int LoadqtXqqzDataCount(QueryXqqz qx)
	{
		int intCount = xqqzService.qtselectCount(qx);
		return intCount;
	}
	
	@RequestMapping(value = "/queryXqqzById", method = RequestMethod.GET)
	public String queryXqqzById(Xqqz xqqz,Model model){
		xqqz = xqqzService.selectByPrimaryKey(xqqz.getXqqzId());
		model.addAttribute("xqqz", xqqz);
		return "xqqz/qtXqqzReview";
	}
	@RequestMapping(value = "/qtxqqzAdd", method = RequestMethod.GET)
	public ModelAndView qtxqqzAdd(Model model)
	{
		List<XqqzLxgl> xqqzLxglList = xqqzLxglService.queryXqqzLxgl();
		model.addAttribute("xqqzLxglList", xqqzLxglList);
		return new ModelAndView("xqqz/xqqzAdd");
	}
	
	@RequestMapping(value = "/qtNewXqqzSave", method = RequestMethod.POST)
	public ModelAndView qtNewXqqzSave(Xqqz xqqz, HttpSession session,Userinfo user)
	{
		user = (Userinfo) session.getAttribute("userLoginSession");
		if(user == null){
			return new ModelAndView("sqlt/Noqtlogin");
		}else{
			if(!(user.getSfjy()).equals("1")){
				xqqz.setXqqzFbr(user.getUserName());
				xqqz.setXqqzFbsj(new Date());
				xqqz.setXqqzId(RandomGUIDUtil.generateKey());
				xqqz.setXqqzDelflag("1");
				xqqzService.insertSelective(xqqz);
				return new ModelAndView("redirect:/qtXqqz");
			}else{
				return new ModelAndView("sqlt/qtXqqzSaveJy");
			}
		}
	}
}

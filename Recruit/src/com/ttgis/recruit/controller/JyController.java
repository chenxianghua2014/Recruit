package com.ttgis.recruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.recruit.domain.QueryJygl;
import com.ttgis.recruit.domain.QueryUser;
import com.ttgis.recruit.domain.Userinfo;
import com.ttgis.recruit.domain.Zzjg;
import com.ttgis.recruit.service.UserService;
import com.ttgis.recruit.service.ZzjgService;
import org.apache.log4j.Logger;
/**
 * 
 * @类名：  com.ttgis.recruit.controller。JyController
 * @创建人： 范井龙
 * @日期： 
 * @描述：  禁言管理
 * @版本号：
 */


@Controller
public class JyController {
	static Logger logger = Logger.getLogger(JyController.class);
	
	@Autowired
	private ZzjgService zzjgService;
	
	@Autowired
	private UserService userService;
	/** 
	 * 跳转页面方法
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jygl", method = RequestMethod.GET)
	public ModelAndView jygl()
	{
		return new ModelAndView("user/jygl");
	}
	@RequestMapping(value = "/userJygl", method = RequestMethod.GET)
	public ModelAndView userJygl()
	{
		return new ModelAndView("user/userJygl");
	}
	/**
	 * 分页查询（已经审核通过的信息）
	 * @param qa
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/LoadJyglData", method = RequestMethod.POST)
	public List<Zzjg> LoadArticleData(QueryJygl qj)
	{
		List<Zzjg> zzjgList = zzjgService.selectByWhere(qj);
		return zzjgList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadJyglDataCount", method = RequestMethod.POST)
	public int LoadArticleDataCount(QueryJygl qj)
	{
		int intCount = zzjgService.selectCount(qj);
		return intCount;
	}
	@ResponseBody
	@RequestMapping(value = "/LoadUserJyglData", method = RequestMethod.POST)
	public List<Userinfo> LoadUserJyglData(QueryUser qu)
	{
		qu.setSfjy("1");
		List<Userinfo> userList = userService.selectByWhere(qu);
		return userList;
	}

	@ResponseBody
	@RequestMapping(value = "/LoadUserJyglDataCount", method = RequestMethod.POST)
	public int LoadUserJyglDataCount(QueryUser qu)
	{
		qu.setSfjy("1");
		int intCount = userService.selectCount(qu);
		return intCount;
	}
	/**
	 * 组织机构恢复禁言
	 */
	@RequestMapping(value="/hfjy",method = RequestMethod.GET)
	public String hfjy(String zzjgId){
			zzjgService.hfjy(zzjgId);
			return "user/jygl";
	}
	/**
	 * 用户恢复禁言
	 */
	@RequestMapping(value="/userHfjy",method = RequestMethod.GET)
	public String userHfjy(String userId){
			userService.userHfjy(userId);
			return "user/userJygl";
	}

}

/**
 * 招聘新闻 2014-05-16 孙建国
 */
package com.ttgis.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class UeditorController
{

	@RequestMapping(value = "resources/ueditor/jsp/imageUp", method = RequestMethod.GET)
	public ModelAndView Zpxw()
	{
		return new ModelAndView("Zpxw/Zpxw");
	}

}

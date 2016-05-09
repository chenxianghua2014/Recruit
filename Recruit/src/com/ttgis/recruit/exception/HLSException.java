/**
 * 董再兴 HLSException.java 2013年7月3日
 */
package com.ttgis.recruit.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董再兴
 *  spring mvc 异常的实现
 */
public class HLSException implements HandlerExceptionResolver  {
	static Logger log = Logger.getLogger(HLSException.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		log.info("HLSException异常："+ex.getMessage());
		
		return new ModelAndView("error");
	}
}

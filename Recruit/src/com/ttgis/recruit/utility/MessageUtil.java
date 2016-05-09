/**
 * 董再兴 MessageUtil.java 2013年7月3日
 */
package com.ttgis.recruit.utility;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

/**
 * @author 董再兴
 * 消息处理工具类
 */
public class MessageUtil {

	/**
	 * 参数使用{0}开始设定
	 * @param pattern
	 * @param args
	 * @return
	 */
	public static String pattern2String(String pattern, Object[] args) {
		if (StringUtils.isEmpty(pattern)) {
			throw new java.lang.IllegalArgumentException();
		}
		return MessageFormat.format(pattern, args);
	}

	public static void main(String[] args) {
		String[] params = new String[]{"皮皮","123456%……（","192.168.1.103"};
		String pattern = "系统登陆用户--用户名:{0}--密码:{1}--IP地址:{2}";
		System.out.println(pattern2String(pattern, params));
	}
}

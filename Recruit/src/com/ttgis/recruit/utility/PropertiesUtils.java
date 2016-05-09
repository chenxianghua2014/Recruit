/**
 * 董再兴 PropertiesUtils.java 2013年7月3日
 */
package com.ttgis.recruit.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 属性文件读取工具类
 * 
 * @author 董再兴
 */
public class PropertiesUtils
{

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Log logger = LogFactory.getLog(PropertiesUtils.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring Resource格式,
	 * 文件编码使用UTF-8.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths) throws IOException
	{
		Properties props = new Properties();

		for (String location : resourcesPaths)
		{

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try
			{
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex)
			{
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally
			{
				if (is != null)
				{
					is.close();
				}
			}
		}
		return props;
	}

	/**
	 * 根据key取出value 孙建国 2013-07-24
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getValue(String key) throws IOException
	{
		return getValue(key, "smsInfo.properties");
	}

	/**
	 * 取得属性文件中的键值
	 * 
	 * @param key
	 *            键名
	 * @param proFile
	 *            属性文件名
	 * @return 键值
	 * @throws IOException
	 */
	public static String getValue(String key, String proFile) throws IOException
	{
		Properties ps = PropertiesUtils.loadProperties(proFile);
		return ps.get(key).toString();
	}

}

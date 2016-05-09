package com.ttgis.recruit.controller.test;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

public abstract class AbstractTestCase {
	
	// 应用程序根配置文件(不包含applicationContext.xml配置文件)
	private final static String[] ROOT_CONFIGLOCATION = { "classpath:/spring/*-test.xml" };
	// 随Servlet启动时加载配置文件。（默认为applicationContext.xml/servletName-servlet.xml）
	private final static String DEFAULT_CONFIG_LOCATION = "classpath:/spring/applicationContext.xml";
	// 模块功能CRUD
	protected final static String MODULE_QUERY = "query";
	protected final static String MODULE_LIST = "list";
	protected final static String MODULE_SHOW = "show";
	protected final static String MODULE_CREATE = "create";
	protected final static String MODULE_SAVE = "save";
	protected final static String MODULE_EDIT = "edit";
	protected final static String MODULE_DELETE = "delete";
	// Logger
	protected final static Logger logger;
	// 对象映射处理器（映射到controller类）
	protected HandlerMapping handlerMapping;
	// 方法适配处理器（映射到具体方法）
	protected HandlerAdapter handlerAdapter;
	// Spring容器上下文
	protected XmlWebApplicationContext webApplicationContext;
	static {
		// 清除旧的测试日志文件
		File logFile = null;
		logFile = new File("logs/test.log");
		if (logFile.exists()) {
			logFile.setReadable(true);
			logFile.setWritable(true);
			logFile.delete();
		}
		// 加载测试日志配置文件
		PropertyConfigurator.configure(AbstractTestCase.class
				.getResource("/log4j-test.properties"));
		logger = Logger.getLogger(AbstractTestCase.class);
	}

	@Before
	public void setUp() {
		// 创建web应用程序根上下文,该上下文解析并管理系统实例
		XmlWebApplicationContext rootWebApplicationContext = new XmlWebApplicationContext();
		rootWebApplicationContext.setConfigLocations(ROOT_CONFIGLOCATION);
		// 创建servletContext上下文
		MockServletContext servletContext = new MockServletContext();
		rootWebApplicationContext.setServletContext(servletContext);
		rootWebApplicationContext.refresh();
		// servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
		// rootWebApplicationContext);

		// 创建web应用程序上下文，管理controller层请求业务
		webApplicationContext = new XmlWebApplicationContext();
		webApplicationContext.setConfigLocation(DEFAULT_CONFIG_LOCATION);
		webApplicationContext.setParent(rootWebApplicationContext);
		webApplicationContext.refresh();
		servletContext.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				webApplicationContext);

		// 获取默认mapping,adapter
		handlerMapping = webApplicationContext
				.getBean(ControllerClassNameHandlerMapping.class);
		handlerAdapter = webApplicationContext
				.getBean(AnnotationMethodHandlerAdapter.class);
	}

	// TODO 测试流程 创建->修改->查询-> 删除
	@Test
	public void testMain() {
		// 创建
		this.testCreateUI();
		this.testSave();
		// 修改
		this.testEditUI();
		this.testUpdate();
		// 查询
		this.testQuery();
		this.testList();
		this.testShow();
		// 删除
		this.testDelete();
	}

	/**
	 * 跳转到创建UI
	 */
	protected abstract void testCreateUI();

	/**
	 * 创建功能
	 */
	protected abstract void testSave();

	/**
	 * 跳转到编辑UI
	 */
	protected abstract void testEditUI();

	/**
	 * 修改功能
	 */
	protected abstract void testUpdate();

	/**
	 * 按条件查询
	 */
	protected abstract void testQuery();

	/**
	 * 分页查询所有记录
	 */
	protected abstract void testList();

	/**
	 * 查询某条记录详情
	 */
	protected abstract void testShow();

	/**
	 * 删除某条记录
	 */
	protected abstract void testDelete();

	/**
	 * 执行请求
	 * 
	 * @param request
	 *            http请求对象
	 * @param response
	 *            http响应对象
	 * @return UI信息
	 */
	protected ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HandlerExecutionChain chain = handlerMapping.getHandler(request);
			return handlerAdapter.handle(request, response, chain.getHandler());
		} catch (Exception e) {
			// TODO 忽略 no thread bind request exception。
			// 如报错,在业务方法引入HttpServletRequest即可。（eg. public void
			// save(HttpServletRequest request){ // statement}）
		}
		return null;
	}

	/**
	 * 封装请求信息
	 * 
	 * @param uri
	 *            请求uri
	 * @param method
	 *            请求类型 默认请求类型为GET eg POST GET PUT DELETE HEAD TRACE OPTIONS
	 * @param params
	 *            请求参数
	 * @return http请求对象
	 */
	protected HttpServletRequest createRequest(String uri,
			Map<String, String> params, RequestMethod... method) {
		if (null == uri || uri.isEmpty()) {
			throw new IllegalArgumentException("It must contains request uri!");
		}
		// 构造请求
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI(uri);
		if (null == method || method.length == 0) {
			request.setMethod(RequestMethod.GET.name());
		} else {
			request.setMethod(method[0].name());
		}
		if (null != params && !params.isEmpty()) {
			request.addParameters(params);
		}
		return request;
	}

	@After
	public void tearDown() {
	}

	// 查询相关
	/**
	 * 查询所有数据记录
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 */
	protected void list(String module, String operate) {
		ModelAndView view = this.processRequest(module + operate, null);
		logger.info("【Unit Testing Info】query " + module + ": " + view + ".");
	}

	/**
	 * 查询某个记录详情
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 * @param params
	 *            某记录数据参数
	 * @param method
	 *            请求类型
	 */
	protected void show(String module, String operate,
			Map<String, String> params, RequestMethod method) {
		Assert.notEmpty(params, "The request params is null!");
		ModelAndView view = this.processRequest(module + operate, params,
				method);
		logger.info("【Unit Testing Info】 query " + module + " by params "
				+ params + ": " + view + ".");
	}

	/**
	 * 多条件查询数据
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 * @param params
	 *            条件数据参数
	 * @param method
	 *            请求类型
	 */
	protected void query(String module, String operate,
			Map<String, String> params, RequestMethod method) {
		ModelAndView view = this.processRequest(module + operate, params,
				method);
		logger.info("【Unit Testing Info】 query " + module + " by params "
				+ params + ": " + view + ".");
	}

	// 创建修改相关
	/**
	 * 请求待创建页面
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 */
	protected void create(String module, String operate) {
		ModelAndView view = this.processRequest(module + operate, null);
		logger.info("【Unit Testing Info】 " + module + " create url: " + view
				+ ".");
	}

	/**
	 * 请求待修改页面
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 * @param params
	 *            待数据参数
	 */
	protected void edit(String module, String operate,
			Map<String, String> params) {
		Assert.notEmpty(params, "The request params is null!");
		ModelAndView view = this.processRequest(module + operate, params);
		logger.info("【Unit Testing Info】 " + module + " edit url: " + view
				+ ".");
	}

	/**
	 * 创建/修改数据请求
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 * @param params
	 *            创建/修改数据参数
	 * @param method
	 *            请求类型
	 */
	protected void saveOrModify(String module, String operate,
			Map<String, String> params, RequestMethod method) {
		Assert.notEmpty(params, "The request params is null!");
		this.processRequest(module + operate, params, method);
		logger.info("【Unit Testing Info】 " + module + " save " + params
				+ " success.");
	}

	// 删除操作
	/**
	 * 删除数据请求
	 * 
	 * @param module
	 *            当前模块
	 * @param operate
	 *            当前操作
	 * @param params
	 *            删除数据参数
	 */
	protected void delete(String module, String operate,
			Map<String, String[]> params) {
		Assert.notEmpty(params, "The request params is null!");
		this.processRequest(module + operate, params);
		logger.info("【Unit Testing Info】 " + module + " delete "
				+ Arrays.asList(params.get("ids")) + " success");
	}

	/**
	 * 处理用户请求
	 * 
	 * 实现构造请求数据，并发送请求，处理请求业务
	 * 
	 * @param uri
	 *            请求uri
	 * @param method
	 *            请求类型 默认请求类型为GET eg POST GET PUT DELETE HEAD TRACE OPTIONS
	 * @param params
	 *            请求参数
	 * @return 请求结果
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ModelAndView processRequest(String uri, Map params,
			RequestMethod... method) {
		HttpServletRequest request = this.createRequest(uri, params);
		HttpServletResponse response = new MockHttpServletResponse();
		return this.execute(request, response);
	}
}

package com.ttgis.recruit.domain;

public class QueryXcxx extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7334405580047770272L;
	/**
	 * 添加公司
	 */
	private String xcxxAdduser;
	/**
	 * 审核状态
	 */
	private String xcxxCheckstatus;
	/**
	 * 提交单位
	 */
	private String xcxxAddcompany;
	/**
	 * 是否只查本单位
	 */
	private String onlySelf;

	public String getOnlySelf()
	{
		return onlySelf;
	}

	public void setOnlySelf(String onlySelf)
	{
		this.onlySelf = onlySelf;
	}

	public String getXcxxAdduser()
	{
		return xcxxAdduser;
	}

	public void setXcxxAdduser(String xcxxAdduser)
	{
		this.xcxxAdduser = xcxxAdduser;
	}

	public String getXcxxCheckstatus()
	{
		return xcxxCheckstatus;
	}

	public void setXcxxCheckstatus(String xcxxCheckstatus)
	{
		this.xcxxCheckstatus = xcxxCheckstatus;
	}

	public String getXcxxAddcompany()
	{
		return xcxxAddcompany;
	}

	public void setXcxxAddcompany(String xcxxAddcompany)
	{
		this.xcxxAddcompany = xcxxAddcompany;
	}

}

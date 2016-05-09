package com.ttgis.recruit.domain;

import java.io.Serializable;

public class QueryJkgl implements Serializable
{
	private static final long serialVersionUID = -7682116786109170755L;
	private String jkglStlx;
	private String jkglSjbh;

	public String getJkglStlx()
	{
		return jkglStlx;
	}

	public void setJkglStlx(String jkglStlx)
	{
		this.jkglStlx = jkglStlx;
	}

	public String getJkglSjbh()
	{
		return jkglSjbh;
	}

	public void setJkglSjbh(String jkglSjbh)
	{
		this.jkglSjbh = jkglSjbh;
	}

}

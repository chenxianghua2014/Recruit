package com.ttgis.recruit.domain;

import java.io.Serializable;

public class CpResult implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3132192518725897366L;
	private String jsgCj;
	private String glgCj;

	public String getJsgCj()
	{
		return jsgCj;
	}

	public void setJsgCj(String jsgCj)
	{
		this.jsgCj = jsgCj;
	}

	public String getGlgCj()
	{
		return glgCj;
	}

	public void setGlgCj(String glgCj)
	{
		this.glgCj = glgCj;
	}

}
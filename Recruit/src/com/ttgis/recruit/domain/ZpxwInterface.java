package com.ttgis.recruit.domain;

import java.io.Serializable;

public class ZpxwInterface implements Serializable
{
	private static final long serialVersionUID = 2311968391965718385L;
	String school;
	String xTime;
	String xAddr;
	String rTime;
	String rAddr;

	public String getSchool()
	{
		return school;
	}

	public void setSchool(String school)
	{
		this.school = school;
	}

	public String getxTime()
	{
		return xTime;
	}

	public void setxTime(String xTime)
	{
		this.xTime = xTime;
	}

	public String getxAddr()
	{
		return xAddr;
	}

	public void setxAddr(String xAddr)
	{
		this.xAddr = xAddr;
	}

	public String getrTime()
	{
		return rTime;
	}

	public void setrTime(String rTime)
	{
		this.rTime = rTime;
	}

	public String getrAddr()
	{
		return rAddr;
	}

	public void setrAddr(String rAddr)
	{
		this.rAddr = rAddr;
	}

}

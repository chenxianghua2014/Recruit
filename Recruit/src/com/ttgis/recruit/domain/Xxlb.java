package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.List;

public class Xxlb implements Serializable
{
	private static final long serialVersionUID = 8358568861316467064L;

	private String xxlbName;

	private List<Xxlb> xxlbs;

	public List<Xxlb> getZpzys()
	{
		return xxlbs;
	}

	public void setZpzys(List<Xxlb> xxlbs)
	{
		this.xxlbs = xxlbs;
	}

	public String getXxlbName()
	{
		return xxlbName;
	}

	public void setXxlbName(String xxlbName)
	{
		this.xxlbName = xxlbName;
	}

}
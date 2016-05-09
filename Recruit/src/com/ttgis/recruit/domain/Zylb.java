package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.List;

public class Zylb implements Serializable
{
	private static final long serialVersionUID = 3632458374285888614L;

	private String zylbName;

	private List<Zpzy> zpzys;

	public List<Zpzy> getZpzys()
	{
		return zpzys;
	}

	public void setZpzys(List<Zpzy> zpzys)
	{
		this.zpzys = zpzys;
	}

	public String getZylbName()
	{
		return zylbName;
	}

	public void setZylbName(String zylbName)
	{
		this.zylbName = zylbName;
	}

}
package com.ttgis.recruit.domain;

import java.io.Serializable;

public class MyApplication implements Serializable
{
	private static final long serialVersionUID = -5018096987155146078L;
	private String sqdw;
	private String sqgw;
	private String jlsx;
	private String cpcj;
	private String msjg;
	private String apcpgwlb;
	private String jtjlkid;
	private String bmglid;
	private long bmglprintflag;
	private String pid;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public long getBmglprintflag()
	{
		return bmglprintflag;
	}

	public void setBmglprintflag(long bmglprintflag)
	{
		this.bmglprintflag = bmglprintflag;
	}

	public String getSqdw()
	{
		return sqdw;
	}

	public void setSqdw(String sqdw)
	{
		this.sqdw = sqdw;
	}

	public String getSqgw()
	{
		return sqgw;
	}

	public void setSqgw(String sqgw)
	{
		this.sqgw = sqgw;
	}

	public String getJlsx()
	{
		return jlsx;
	}

	public void setJlsx(String jlsx)
	{
		this.jlsx = jlsx;
	}

	public String getCpcj()
	{
		return cpcj;
	}

	public void setCpcj(String cpcj)
	{
		this.cpcj = cpcj;
	}

	public String getMsjg()
	{
		return msjg;
	}

	public void setMsjg(String msjg)
	{
		this.msjg = msjg;
	}

	public String getApcpgwlb()
	{
		return apcpgwlb;
	}

	public void setApcpgwlb(String apcpgwlb)
	{
		this.apcpgwlb = apcpgwlb;
	}

	public String getJtjlkid()
	{
		return jtjlkid;
	}

	public void setJtjlkid(String jtjlkid)
	{
		this.jtjlkid = jtjlkid;
	}

	public String getBmglid()
	{
		return bmglid;
	}

	public void setBmglid(String bmglid)
	{
		this.bmglid = bmglid;
	}

}
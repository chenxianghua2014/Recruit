package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Zan implements Serializable
{
	private static final long serialVersionUID = 8022962693036312908L;
	private String zanId;
	private String userId;
	private String bbsId;
	private Date zanAddtime;

	public String getZanId()
	{
		return zanId;
	}

	public void setZanId(String zanId)
	{
		this.zanId = zanId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

	public Date getZanAddtime()
	{
		return zanAddtime;
	}

	public void setZanAddtime(Date zanAddtime)
	{
		this.zanAddtime = zanAddtime;
	}

}

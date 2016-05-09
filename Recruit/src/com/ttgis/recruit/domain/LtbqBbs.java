package com.ttgis.recruit.domain;

import java.io.Serializable;

public class LtbqBbs implements Serializable
{
	private static final long serialVersionUID = -4958715934543578494L;

	private String ltbqId;

	private String bbsId;

	public String getLtbqId()
	{
		return ltbqId;
	}

	public void setLtbqId(String ltbqId)
	{
		this.ltbqId = ltbqId;
	}

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

}

package com.ttgis.recruit.domain;

public class QueryBbs extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2239439887694116998L;
	private String bbsNr;
	private String bbsFbr;
	private String ltbqType;
	private String ltbqName;
	private String ltbqId;
	private String bbsBqid;
	private String ltbkId;

	public String getLtbkId()
	{
		return ltbkId;
	}

	public void setLtbkId(String ltbkId)
	{
		this.ltbkId = ltbkId;
	}

	public String getBbsBqid()
	{
		return bbsBqid;
	}

	public void setBbsBqid(String bbsBqid)
	{
		this.bbsBqid = bbsBqid;
	}

	public String getLtbqName()
	{
		return ltbqName;
	}

	public void setLtbqName(String ltbqName)
	{
		this.ltbqName = ltbqName;
	}

	public String getLtbqId()
	{
		return ltbqId;
	}

	public void setLtbqId(String ltbqId)
	{
		this.ltbqId = ltbqId;
	}

	public String getBbsNr()
	{
		return bbsNr;
	}

	public void setBbsNr(String bbsNr)
	{
		this.bbsNr = bbsNr;
	}

	public String getLtbqType()
	{
		return ltbqType;
	}

	public void setLtbqType(String ltbqType)
	{
		this.ltbqType = ltbqType;
	}

	public String getBbsFbr()
	{
		return bbsFbr;
	}

	public void setBbsFbr(String bbsFbr)
	{
		this.bbsFbr = bbsFbr;
	}

}

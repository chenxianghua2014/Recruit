package com.ttgis.recruit.domain;

public class QueryLtbq extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1559626815523699707L;

	private String ltbqName;

	private String ltbqType;

	public String getLtbqName()
	{
		return ltbqName;
	}

	public void setLtbqName(String ltbqName)
	{
		this.ltbqName = ltbqName;
	}

	public String getLtbqType()
	{
		return ltbqType;
	}

	public void setLtbqType(String ltbqType)
	{
		this.ltbqType = ltbqType;
	}

}

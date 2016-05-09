package com.ttgis.recruit.domain;

public class QueryMsq extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4686874964041103547L;

	private String zzjgId;

	private String msqName;

	private String msqMslb;

	public String getZzjgId()
	{
		return zzjgId;
	}

	public void setZzjgId(String zzjgId)
	{
		this.zzjgId = zzjgId;
	}

	public String getMsqName()
	{
		return msqName;
	}

	public void setMsqName(String msqName)
	{
		this.msqName = msqName;
	}

	public String getMsqMslb()
	{
		return msqMslb;
	}

	public void setMsqMslb(String msqMslb)
	{
		this.msqMslb = msqMslb;
	}

}

package com.ttgis.recruit.domain;

public class QueryKcgl extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3980784419963792103L;
	private String kcglName;
	private String kcglKczt;
	private String kcglKsrq;
	private String kcglKssjStart;
	private String kcglKssjEnd;

	public String getKcglName()
	{
		return kcglName;
	}

	public void setKcglName(String kcglName)
	{
		this.kcglName = kcglName;
	}

	public String getKcglKczt()
	{
		return kcglKczt;
	}

	public void setKcglKczt(String kcglKczt)
	{
		this.kcglKczt = kcglKczt;
	}

	public String getKcglKsrq()
	{
		return kcglKsrq;
	}

	public void setKcglKsrq(String kcglKsrq)
	{
		this.kcglKsrq = kcglKsrq;
	}

	public String getKcglKssjStart()
	{
		return kcglKssjStart;
	}

	public void setKcglKssjStart(String kcglKssjStart)
	{
		this.kcglKssjStart = kcglKssjStart;
	}

	public String getKcglKssjEnd()
	{
		return kcglKssjEnd;
	}

	public void setKcglKssjEnd(String kcglKssjEnd)
	{
		this.kcglKssjEnd = kcglKssjEnd;
	}

}

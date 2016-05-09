package com.ttgis.recruit.domain;

public class QueryTkgl extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6244288460195988727L;
	private String tkglStbh;
	private String tkglStlx;
	private String tkglSttg;

	public String getTkglStlx()
	{
		return tkglStlx;
	}

	public void setTkglStlx(String tkglStlx)
	{
		this.tkglStlx = tkglStlx;
	}

	public String getTkglStbh()
	{
		return tkglStbh;
	}

	public void setTkglStbh(String tkglStbh)
	{
		this.tkglStbh = tkglStbh;
	}

	public String getTkglSttg()
	{
		return tkglSttg;
	}

	public void setTkglSttg(String tkglSttg)
	{
		this.tkglSttg = tkglSttg;
	}

}

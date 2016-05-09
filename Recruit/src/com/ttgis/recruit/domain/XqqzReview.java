package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class XqqzReview implements Serializable
{
	private static final long serialVersionUID = 9223312814826816492L;
	private String xqqzReviewId;
	private Date xqqzReviewPlsj;
	private String plsj;
	private String xqqzId;
	private String xqqzReviewNr;
	private String xqqzReviewPlr;
	private Long xqqzReviewDelflag;
	private Date xqqzReviewAddtime;

	public String getXqqzId()
	{
		return xqqzId;
	}

	public void setXqqzId(String xqqzId)
	{
		this.xqqzId = xqqzId;
	}

	public String getXqqzReviewId()
	{
		return xqqzReviewId;
	}

	public void setXqqzReviewId(String xqqzReviewId)
	{
		this.xqqzReviewId = xqqzReviewId;
	}

	public Date getXqqzReviewPlsj()
	{
		return xqqzReviewPlsj;
	}

	public void setXqqzReviewPlsj(Date xqqzReviewPlsj)
	{
		this.xqqzReviewPlsj = xqqzReviewPlsj;
	}

	public String getPlsj()
	{
		return plsj;
	}

	public void setPlsj(String plsj)
	{
		this.plsj = plsj;
	}

	public String getXqqzReviewNr()
	{
		return xqqzReviewNr;
	}

	public void setXqqzReviewNr(String xqqzReviewNr)
	{
		this.xqqzReviewNr = xqqzReviewNr;
	}

	public String getXqqzReviewPlr()
	{
		return xqqzReviewPlr;
	}

	public void setXqqzReviewPlr(String xqqzReviewPlr)
	{
		this.xqqzReviewPlr = xqqzReviewPlr;
	}

	public Long getXqqzReviewDelflag()
	{
		return xqqzReviewDelflag;
	}

	public void setXqqzReviewDelflag(Long xqqzReviewDelflag)
	{
		this.xqqzReviewDelflag = xqqzReviewDelflag;
	}

	public Date getXqqzReviewAddtime()
	{
		return xqqzReviewAddtime;
	}

	public void setXqqzReviewAddtime(Date xqqzReviewAddtime)
	{
		this.xqqzReviewAddtime = xqqzReviewAddtime;
	}

}
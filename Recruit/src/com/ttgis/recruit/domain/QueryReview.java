package com.ttgis.recruit.domain;

public class QueryReview extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4459372640558346109L;

	private String reviewNr;

	private String reviewPlr;

	public String getReviewNr()
	{
		return reviewNr;
	}

	public void setReviewNr(String reviewNr)
	{
		this.reviewNr = reviewNr;
	}

	public String getReviewPlr()
	{
		return reviewPlr;
	}

	public void setReviewPlr(String reviewPlr)
	{
		this.reviewPlr = reviewPlr;
	}

	private String bbsId;

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

}

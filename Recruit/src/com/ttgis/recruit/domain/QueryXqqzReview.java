package com.ttgis.recruit.domain;

public class QueryXqqzReview extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100587908434069921L;
	private String xqqzReviewPlr;
	private String xqqzReviewNr;
	private String xqqzId;

	public String getXqqzId()
	{
		return xqqzId;
	}

	public void setXqqzId(String xqqzId)
	{
		this.xqqzId = xqqzId;
	}

	public String getXqqzReviewPlr()
	{
		return xqqzReviewPlr;
	}

	public void setXqqzReviewPlr(String xqqzReviewPlr)
	{
		this.xqqzReviewPlr = xqqzReviewPlr;
	}

	public String getXqqzReviewNr()
	{
		return xqqzReviewNr;
	}

	public void setXqqzReviewNr(String xqqzReviewNr)
	{
		this.xqqzReviewNr = xqqzReviewNr;
	}

}

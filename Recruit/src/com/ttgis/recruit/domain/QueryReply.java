package com.ttgis.recruit.domain;

public class QueryReply extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7169288629452532753L;
	private String reviewId;
	private String replyName;
	private String replyNr;
	private String xqqzReviewId;
	private String articleReviewId;

	public String getReviewId()
	{
		return reviewId;
	}

	public void setReviewId(String reviewId)
	{
		this.reviewId = reviewId;
	}

	public String getReplyName()
	{
		return replyName;
	}

	public void setReplyName(String replyName)
	{
		this.replyName = replyName;
	}

	public String getReplyNr()
	{
		return replyNr;
	}

	public void setReplyNr(String replyNr)
	{
		this.replyNr = replyNr;
	}

	public String getXqqzReviewId()
	{
		return xqqzReviewId;
	}

	public void setXqqzReviewId(String xqqzReviewId)
	{
		this.xqqzReviewId = xqqzReviewId;
	}

	public String getArticleReviewId()
	{
		return articleReviewId;
	}

	public void setArticleReviewId(String articleReviewId)
	{
		this.articleReviewId = articleReviewId;
	}

}

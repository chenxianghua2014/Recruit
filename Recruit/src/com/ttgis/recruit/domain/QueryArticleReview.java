package com.ttgis.recruit.domain;

public class QueryArticleReview extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5443074503850379685L;
	private String articleReviewPlr;
	private String articleId;
	private String articleReviewNr;

	public String getArticleReviewNr()
	{
		return articleReviewNr;
	}

	public void setArticleReviewNr(String articleReviewNr)
	{
		this.articleReviewNr = articleReviewNr;
	}

	public String getArticleId()
	{
		return articleId;
	}

	public void setArticleId(String articleId)
	{
		this.articleId = articleId;
	}

	public String getArticleReviewPlr()
	{
		return articleReviewPlr;
	}

	public void setArticleReviewPlr(String articleReviewPlr)
	{
		this.articleReviewPlr = articleReviewPlr;
	}

}

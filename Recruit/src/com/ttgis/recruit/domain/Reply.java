package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable
{
	private static final long serialVersionUID = -2506234653541326812L;
	private String replyId;
	private String replyName;
	private Date replyAddtime;
	private String replyNr;
	private Long replyDelflag;
	private String reviewId;
	private String articleReviewId;
	private String xqqzReviewId;
	private String hfsj;

	public String getHfsj()
	{
		return hfsj;
	}

	public void setHfsj(String hfsj)
	{
		this.hfsj = hfsj;
	}

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
	}

	public String getReplyName()
	{
		return replyName;
	}

	public void setReplyName(String replyName)
	{
		this.replyName = replyName;
	}

	public Date getReplyAddtime()
	{
		return replyAddtime;
	}

	public void setReplyAddtime(Date replyAddtime)
	{
		this.replyAddtime = replyAddtime;
	}

	public String getReplyNr()
	{
		return replyNr;
	}

	public void setReplyNr(String replyNr)
	{
		this.replyNr = replyNr;
	}

	public Long getReplyDelflag()
	{
		return replyDelflag;
	}

	public void setReplyDelflag(Long replyDelflag)
	{
		this.replyDelflag = replyDelflag;
	}

	public String getReviewId()
	{
		return reviewId;
	}

	public void setReviewId(String reviewId)
	{
		this.reviewId = reviewId;
	}

	public String getArticleReviewId()
	{
		return articleReviewId;
	}

	public void setArticleReviewId(String articleReviewId)
	{
		this.articleReviewId = articleReviewId;
	}

	public String getXqqzReviewId()
	{
		return xqqzReviewId;
	}

	public void setXqqzReviewId(String xqqzReviewId)
	{
		this.xqqzReviewId = xqqzReviewId;
	}

}
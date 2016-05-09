package com.ttgis.recruit.domain;

import java.io.Serializable;

public class LtbqArticle implements Serializable
{
	private static final long serialVersionUID = -7021221003270860582L;

	private String ltbqId;

	private String articleId;

	public String getLtbqId()
	{
		return ltbqId;
	}

	public void setLtbqId(String ltbqId)
	{
		this.ltbqId = ltbqId;
	}

	public String getArticleId()
	{
		return articleId;
	}

	public void setArticleId(String articleId)
	{
		this.articleId = articleId;
	}

}

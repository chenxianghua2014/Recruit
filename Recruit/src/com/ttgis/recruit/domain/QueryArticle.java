package com.ttgis.recruit.domain;

public class QueryArticle extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 662931806843714918L;
	private String articleTitle;
	private String articleAuthor;
	private String articleType;

	private String ltbqType;

	private String ltbqName;
	private String ltbqId;
	private String articleViewer;

	public String getArticleAuthor()
	{
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor)
	{
		this.articleAuthor = articleAuthor;
	}

	public String getArticleViewer()
	{
		return articleViewer;
	}

	public void setArticleViewer(String articleViewer)
	{
		this.articleViewer = articleViewer;
	}

	public String getLtbqName()
	{
		return ltbqName;
	}

	public void setLtbqName(String ltbqName)
	{
		this.ltbqName = ltbqName;
	}

	public String getLtbqId()
	{
		return ltbqId;
	}

	public void setLtbqId(String ltbqId)
	{
		this.ltbqId = ltbqId;
	}

	public String getLtbqType()
	{
		return ltbqType;
	}

	public void setLtbqType(String ltbqType)
	{
		this.ltbqType = ltbqType;
	}

	public String getArticleTitle()
	{
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle)
	{
		this.articleTitle = articleTitle;
	}

	public String getArticleType()
	{
		return articleType;
	}

	public void setArticleType(String articleType)
	{
		this.articleType = articleType;
	}

}

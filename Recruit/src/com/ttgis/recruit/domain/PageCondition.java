/**
 * 董再兴 PageCondation.java 2013年8月2日
 */
package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 董再兴 分页查询条件实体
 */
public class PageCondition implements Serializable
{
	private static final long serialVersionUID = -3497439847404728425L;
	/**
	 * 页数
	 */
	private int pageNum = 2;
	/**
	 * 每页显示数量
	 */
	private int pageSize = 1;
	/**
	 * 当前页显示数量
	 */
	private int currentPageSize = 1;
	/**
	 * 起始记录数
	 */
	private int recordStart = 1;
	/**
	 * 结束记录数
	 */
	private int recordEnd = 10;
	/**
	 * 排序字段
	 */
	private String orderField;
	/**
	 * 排序方式
	 */
	private String orderDirection;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 起始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;

	/**
	 * @return the pageNum
	 */
	public int getPageNum()
	{
		return pageNum;
	}

	/**
	 * @param pageNum
	 *            the pageNum to set
	 */
	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getCurrentPageSize()
	{
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize)
	{
		this.currentPageSize = currentPageSize;
	}

	/**
	 * @return the orderField
	 */
	public String getOrderField()
	{
		return orderField;
	}

	/**
	 * @param orderField
	 *            the orderField to set
	 */
	public void setOrderField(String orderField)
	{
		this.orderField = orderField;
	}

	/**
	 * @return the orderDirection
	 */
	public String getOrderDirection()
	{
		return orderDirection;
	}

	/**
	 * @param orderDirection
	 *            the orderDirection to set
	 */
	public void setOrderDirection(String orderDirection)
	{
		this.orderDirection = orderDirection;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords()
	{
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return the recordStart
	 */
	public int getRecordStart()
	{
		return recordStart;
	}

	/**
	 * @param recordStart
	 *            the recordStart to set
	 */
	public void setRecordStart(int recordStart)
	{
		this.recordStart = recordStart;
	}

	/**
	 * @return the recordEnd
	 */
	public int getRecordEnd()
	{
		return recordEnd;
	}

	/**
	 * @param recordEnd
	 *            the recordEnd to set
	 */
	public void setRecordEnd(int recordEnd)
	{
		this.recordEnd = recordEnd;
	}

}

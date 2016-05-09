package com.ttgis.recruit.domain;

/**
 * 
 * @author SJG
 * 
 */
public class QueryXxtz extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 442778347395584569L;
	private String zzjgId;
	private String userName;
	private String userId;
	private String type;
	private String status;

	public String getZzjgId()
	{
		return zzjgId;
	}

	public void setZzjgId(String zzjgId)
	{
		this.zzjgId = zzjgId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}

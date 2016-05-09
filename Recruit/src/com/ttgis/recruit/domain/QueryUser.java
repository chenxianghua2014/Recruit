package com.ttgis.recruit.domain;

public class QueryUser extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939850079583598422L;
	private String userName;
	private String userIdcard;
	private String userTelephone;
	private String userEmail;
	private String userSfzh;
	private String sfjy;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserIdcard()
	{
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard)
	{
		this.userIdcard = userIdcard;
	}

	public String getUserTelephone()
	{
		return userTelephone;
	}

	public void setUserTelephone(String userTelephone)
	{
		this.userTelephone = userTelephone;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserSfzh()
	{
		return userSfzh;
	}

	public void setUserSfzh(String userSfzh)
	{
		this.userSfzh = userSfzh;
	}

	public String getSfjy()
	{
		return sfjy;
	}

	public void setSfjy(String sfjy)
	{
		this.sfjy = sfjy;
	}

}

package com.ttgis.recruit.domain;

import java.io.Serializable;

public class InterfaceLoginResult implements Serializable
{
	private static final long serialVersionUID = -9188828709513485844L;
	private String code;
	private String message;
	private Object result;
	private int userType;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}

	public int getUserType()
	{
		return userType;
	}

	public void setUserType(int i)
	{
		this.userType = i;
	}

}
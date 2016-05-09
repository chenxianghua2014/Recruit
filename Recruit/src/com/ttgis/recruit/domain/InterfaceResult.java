package com.ttgis.recruit.domain;

import java.io.Serializable;

public class InterfaceResult implements Serializable
{
	private static final long serialVersionUID = -7033754172035830579L;
	private String code;
	private String message;
	private Object result;
	private int rowCount = 0;

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

	public int getRowCount()
	{
		return rowCount;
	}

	public void setRowCount(int rowCount)
	{
		this.rowCount = rowCount;
	}

}
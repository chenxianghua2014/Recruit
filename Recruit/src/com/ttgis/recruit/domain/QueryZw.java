package com.ttgis.recruit.domain;

import java.util.List;

public class QueryZw extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3712808531956609539L;
	/**
	 * 名称
	 */
	private String positionName;
	/**
	 * 类别
	 */
	private String positionType;
	/**
	 * 组织机构
	 */
	private List<String> zzjgId;
	/**
	 * 招收单位
	 */
	private String positionZsdw;
	/**
	 * 工作地点
	 */
	private String positionAddress;
	/**
	 * 发布时间段
	 */
	private int positionAddtime;
	/**
	 * 职位状态
	 */
	private String positionStatus;
	private String positionZpzy;
	private String positionXqrs;
	private String positionXlyq;

	public String getPositionZpzy()
	{
		return positionZpzy;
	}

	public void setPositionZpzy(String positionZpzy)
	{
		this.positionZpzy = positionZpzy;
	}

	public String getPositionXqrs()
	{
		return positionXqrs;
	}

	public void setPositionXqrs(String positionXqrs)
	{
		this.positionXqrs = positionXqrs;
	}

	public String getPositionXlyq()
	{
		return positionXlyq;
	}

	public void setPositionXlyq(String positionXlyq)
	{
		this.positionXlyq = positionXlyq;
	}

	public String getPositionAddress()
	{
		return positionAddress;
	}

	public void setPositionAddress(String positionAddress)
	{
		this.positionAddress = positionAddress;
	}

	public int getPositionAddtime()
	{
		return positionAddtime;
	}

	public void setPositionAddtime(int positionAddtime)
	{
		this.positionAddtime = positionAddtime;
	}

	public String getPositionName()
	{
		return positionName;
	}

	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}

	public String getPositionType()
	{
		return positionType;
	}

	public void setPositionType(String positionType)
	{
		this.positionType = positionType;
	}

	public List<String> getZzjgId()
	{
		return zzjgId;
	}

	public void setZzjgId(List<String> zzjgId)
	{
		this.zzjgId = zzjgId;
	}

	public String getPositionZsdw()
	{
		return positionZsdw;
	}

	public void setPositionZsdw(String positionZsdw)
	{
		this.positionZsdw = positionZsdw;
	}

	public String getPositionStatus()
	{
		return positionStatus;
	}

	public void setPositionStatus(String positionStatus)
	{
		this.positionStatus = positionStatus;
	}
}

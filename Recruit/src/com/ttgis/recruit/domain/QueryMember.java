package com.ttgis.recruit.domain;

public class QueryMember extends PageCondition
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2507851529760822914L;
	private String memberIdcard;
	private String memberName;
	private String memberHydj;
	private String fyye;

	public String getFyye()
	{
		return fyye;
	}

	public void setFyye(String fyye)
	{
		this.fyye = fyye;
	}

	public String getMemberIdcard()
	{
		return memberIdcard;
	}

	public void setMemberIdcard(String memberIdcard)
	{
		this.memberIdcard = memberIdcard;
	}

	public String getMemberName()
	{
		return memberName;
	}

	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}

	public String getMemberHydj()
	{
		return memberHydj;
	}

	public void setMemberHydj(String memberHydj)
	{
		this.memberHydj = memberHydj;
	}

}

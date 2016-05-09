package com.ttgis.recruit.domain;

import java.util.List;

/**
 * 
 * @author SJG
 * 
 */
public class QueryJtjlk extends PageCondition
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7760165684017905572L;
	/**
	 * 组织机构ID
	 */
	private String zzjgId;
	/**
	 * 姓名
	 */
	private String jtjlkName;
	/**
	 * 毕业院校
	 */
	private String jtjlkByyx;
	/**
	 * 专业
	 */
	private String jtjlkZy;
	/**
	 * 学历
	 */
	private String jtjlkXl;
	/**
	 * 职位
	 */
	private String jtjlkZw;
	/**
	 * 简历状态
	 */
	private List<String> jtjlkZt;
	/**
	 * 面试状态
	 */
	private List<String> jtjlkMszt;
	/**
	 * 岗位类别
	 */
	private String jtjlkGwlb;
	/**
	 * 职位ID
	 */
	private String jtjlkPositionId;
	/**
	 * 来源
	 */
	private String jtjlkLy;
	/**
	 * 原有id
	 */
	private String jtjlkOldid;
	/**
	 * 户口所在地
	 */
	private String jtjlkHkd;
	/**
	 * 身份证号
	 */
	private String jtjlkSfzh;

	private String jtjlkCpjg;

	private int jtjlkJtjlkflag;

	private List<String> zzjgIds;

	private String zzjgQueryNum;

	private String jtjlZzjgIds;

	private int star;

	public int getStar()
	{
		return star;
	}

	public void setStar(int star)
	{
		this.star = star;
	}

	public String getJtjlZzjgIds()
	{
		return jtjlZzjgIds;
	}

	public void setJtjlZzjgIds(String jtjlZzjgIds)
	{
		this.jtjlZzjgIds = jtjlZzjgIds;
	}

	public String getZzjgQueryNum()
	{
		return zzjgQueryNum;
	}

	public void setZzjgQueryNum(String zzjgQueryNum)
	{
		this.zzjgQueryNum = zzjgQueryNum;
	}

	public List<String> getZzjgIds()
	{
		return zzjgIds;
	}

	public void setZzjgIds(List<String> zzjgIds)
	{
		this.zzjgIds = zzjgIds;
	}

	public String getJtjlkPositionId()
	{
		return jtjlkPositionId;
	}

	public void setJtjlkPositionId(String jtjlkPositionId)
	{
		this.jtjlkPositionId = jtjlkPositionId;
	}

	public String getJtjlkGwlb()
	{
		return jtjlkGwlb;
	}

	public void setJtjlkGwlb(String jtjlkGwlb)
	{
		this.jtjlkGwlb = jtjlkGwlb;
	}

	private String jtjlkId;

	public String getZzjgId()
	{
		return zzjgId;
	}

	public void setZzjgId(String zzjgId)
	{
		this.zzjgId = zzjgId;
	}

	public String getJtjlkName()
	{
		return jtjlkName;
	}

	public void setJtjlkName(String jtjlkName)
	{
		this.jtjlkName = jtjlkName;
	}

	public String getJtjlkByyx()
	{
		return jtjlkByyx;
	}

	public void setJtjlkByyx(String jtjlkByyx)
	{
		this.jtjlkByyx = jtjlkByyx;
	}

	public String getJtjlkZy()
	{
		return jtjlkZy;
	}

	public void setJtjlkZy(String jtjlkZy)
	{
		this.jtjlkZy = jtjlkZy;
	}

	public String getJtjlkXl()
	{
		return jtjlkXl;
	}

	public void setJtjlkXl(String jtjlkXl)
	{
		this.jtjlkXl = jtjlkXl;
	}

	public String getJtjlkZw()
	{
		return jtjlkZw;
	}

	public void setJtjlkZw(String jtjlkZw)
	{
		this.jtjlkZw = jtjlkZw;
	}

	public List<String> getJtjlkZt()
	{
		return jtjlkZt;
	}

	public void setJtjlkZt(List<String> jtjlkZt)
	{
		this.jtjlkZt = jtjlkZt;
	}

	public List<String> getJtjlkMszt()
	{
		return jtjlkMszt;
	}

	public void setJtjlkMszt(List<String> jtjlkMszt)
	{
		this.jtjlkMszt = jtjlkMszt;
	}

	public String getJtjlkId()
	{
		return jtjlkId;
	}

	public void setJtjlkId(String jtjlkId)
	{
		this.jtjlkId = jtjlkId;
	}

	public String getJtjlkLy()
	{
		return jtjlkLy;
	}

	public void setJtjlkLy(String jtjlkLy)
	{
		this.jtjlkLy = jtjlkLy;
	}

	public String getJtjlkOldid()
	{
		return jtjlkOldid;
	}

	public void setJtjlkOldid(String jtjlkOldid)
	{
		this.jtjlkOldid = jtjlkOldid;
	}

	public String getJtjlkCpjg()
	{
		return jtjlkCpjg;
	}

	public void setJtjlkCpjg(String jtjlkCpjg)
	{
		this.jtjlkCpjg = jtjlkCpjg;
	}

	public String getJtjlkHkd()
	{
		return jtjlkHkd;
	}

	public void setJtjlkHkd(String jtjlkHkd)
	{
		this.jtjlkHkd = jtjlkHkd;
	}

	public String getJtjlkSfzh()
	{
		return jtjlkSfzh;
	}

	public void setJtjlkSfzh(String jtjlkSfzh)
	{
		this.jtjlkSfzh = jtjlkSfzh;
	}

	public int getJtjlkJtjlkflag()
	{
		return jtjlkJtjlkflag;
	}

	public void setJtjlkJtjlkflag(int jtjlkJtjlkflag)
	{
		this.jtjlkJtjlkflag = jtjlkJtjlkflag;
	}

	@Override
	public String toString()
	{
		return "QueryJtjlk [zzjgId=" + zzjgId + ", jtjlkName=" + jtjlkName + ", jtjlkByyx=" + jtjlkByyx + ", jtjlkZy=" + jtjlkZy + ", jtjlkXl=" + jtjlkXl + ", jtjlkZw=" + jtjlkZw + ", jtjlkZt=" + jtjlkZt + ", jtjlkMszt=" + jtjlkMszt + ", jtjlkGwlb=" + jtjlkGwlb + ", jtjlkPositionId=" + jtjlkPositionId + ", jtjlkLy=" + jtjlkLy + ", jtjlkOldid=" + jtjlkOldid + ", jtjlkHkd=" + jtjlkHkd + ", jtjlkCpjg=" + jtjlkCpjg + ", jtjlkJtjlkflag=" + jtjlkJtjlkflag + ", zzjgIds=" + zzjgIds + ", zzjgQueryNum=" + zzjgQueryNum + ", jtjlkId=" + jtjlkId + "]";
	}

}

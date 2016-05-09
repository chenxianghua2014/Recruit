/**
 * 招聘日程查询条件继承PageCondition
 * 2014年07月12日
 */
package com.ttgis.recruit.domain;

/**
 * @author 董再兴
 * 
 */
public class ZzjgCondition extends PageCondition
{

	private static final long serialVersionUID = -2938230716209606912L;

	/**
	 * 招聘日程标题
	 */
	String ZPXW_TITLE;

	/**
	 * 招聘日程内容
	 */
	String ZPXW_CONTENT;

	/**
	 * 组织机构ID
	 */
	String ZZJG_ID;

	/**
	 * 是否招聘日程
	 */
	String SFZPRC;

	/**
	 * 发布单位
	 */
	String zpxwAddcompany;

	/**
	 * 学校
	 */
	String zpxwSchool;

	public String getSFZPRC()
	{
		return SFZPRC;
	}

	public void setSFZPRC(String sFZPRC)
	{
		SFZPRC = sFZPRC;
	}

	public String getZPXW_TITLE()
	{
		return ZPXW_TITLE;
	}

	public void setZPXW_TITLE(String zPXW_TITLE)
	{
		ZPXW_TITLE = zPXW_TITLE;
	}

	public String getZPXW_CONTENT()
	{
		return ZPXW_CONTENT;
	}

	public void setZPXW_CONTENT(String zPXW_CONTENT)
	{
		ZPXW_CONTENT = zPXW_CONTENT;
	}

	public String getZZJG_ID()
	{
		return ZZJG_ID;
	}

	public void setZZJG_ID(String zZJG_ID)
	{
		ZZJG_ID = zZJG_ID;
	}

	public String getZpxwAddcompany()
	{
		return zpxwAddcompany;
	}

	public void setZpxwAddcompany(String zpxwAddcompany)
	{
		this.zpxwAddcompany = zpxwAddcompany;
	}

	public String getZpxwSchool()
	{
		return zpxwSchool;
	}

	public void setZpxwSchool(String zpxwSchool)
	{
		this.zpxwSchool = zpxwSchool;
	}

}

/**
 * 题库管理 2014年05月19日
 * 董再兴
 */
package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Tkgl implements Serializable
{
	private static final long serialVersionUID = -6734582129875597864L;

	private String tkglStbh;

	public String getTkglStbh()
	{
		return tkglStbh;
	}

	public void setTkglStbh(String tkglStbh)
	{
		this.tkglStbh = tkglStbh;
	}

	/**
	 * 题库管理ID
	 */
	private String tkglId;

	/**
	 * 试题类型
	 */
	private String tkglStlx;

	/**
	 * 难度等级
	 */
	private String tkglNddj;

	/**
	 * 试题提干
	 */
	private String tkglSttg;

	/**
	 * A
	 */
	private String tkglStxxA;

	/**
	 * B
	 */
	private String tkglStxxB;

	/**
	 * C
	 */
	private String tkglStxxC;

	/**
	 * D
	 */
	private String tkglStxxD;

	/**
	 * 试题答案
	 */
	private String tkglStda;

	/**
	 * 答案详解
	 */
	private String tkglDaxj;

	/**
	 * 特殊照顾
	 */
	private String tkglTszg;

	/**
	 * 添加时间
	 */
	private Date tkglAddtime;

	/**
	 * 删除标记
	 */
	private Long tkglDelflag;

	/**
	 * @return the tkglId
	 */

	public String getTkglId()
	{
		return tkglId;
	}

	/**
	 * @param tkglId
	 *            the tkglId to set
	 */
	public void setTkglId(String tkglId)
	{
		this.tkglId = tkglId;
	}

	/**
	 * @return the tkglStlx
	 */
	public String getTkglStlx()
	{
		return tkglStlx;
	}

	/**
	 * @param tkglStlx
	 *            the tkglStlx to set
	 */
	public void setTkglStlx(String tkglStlx)
	{
		this.tkglStlx = tkglStlx;
	}

	/**
	 * @return the tkglNddj
	 */
	public String getTkglNddj()
	{
		return tkglNddj;
	}

	/**
	 * @param tkglNddj
	 *            the tkglNddj to set
	 */
	public void setTkglNddj(String tkglNddj)
	{
		this.tkglNddj = tkglNddj;
	}

	/**
	 * @return the tkglSttg
	 */
	public String getTkglSttg()
	{
		return tkglSttg;
	}

	/**
	 * @param tkglSttg
	 *            the tkglSttg to set
	 */
	public void setTkglSttg(String tkglSttg)
	{
		this.tkglSttg = tkglSttg;
	}

	/**
	 * @return the tkglStxxA
	 */
	public String getTkglStxxA()
	{
		return tkglStxxA;
	}

	/**
	 * @param tkglStxxA
	 *            the tkglStxxA to set
	 */
	public void setTkglStxxA(String tkglStxxA)
	{
		this.tkglStxxA = tkglStxxA;
	}

	/**
	 * @return the tkglStxxB
	 */
	public String getTkglStxxB()
	{
		return tkglStxxB;
	}

	/**
	 * @param tkglStxxB
	 *            the tkglStxxB to set
	 */
	public void setTkglStxxB(String tkglStxxB)
	{
		this.tkglStxxB = tkglStxxB;
	}

	/**
	 * @return the tkglStxxC
	 */
	public String getTkglStxxC()
	{
		return tkglStxxC;
	}

	/**
	 * @param tkglStxxC
	 *            the tkglStxxC to set
	 */
	public void setTkglStxxC(String tkglStxxC)
	{
		this.tkglStxxC = tkglStxxC;
	}

	/**
	 * @return the tkglStxxD
	 */
	public String getTkglStxxD()
	{
		return tkglStxxD;
	}

	/**
	 * @param tkglStxxD
	 *            the tkglStxxD to set
	 */
	public void setTkglStxxD(String tkglStxxD)
	{
		this.tkglStxxD = tkglStxxD;
	}

	/**
	 * @return the tkglStda
	 */
	public String getTkglStda()
	{
		return tkglStda;
	}

	/**
	 * @param tkglStda
	 *            the tkglStda to set
	 */
	public void setTkglStda(String tkglStda)
	{
		this.tkglStda = tkglStda;
	}

	/**
	 * @return the tkglDaxj
	 */
	public String getTkglDaxj()
	{
		return tkglDaxj;
	}

	/**
	 * @param tkglDaxj
	 *            the tkglDaxj to set
	 */
	public void setTkglDaxj(String tkglDaxj)
	{
		this.tkglDaxj = tkglDaxj;
	}

	/**
	 * @return the tkglTszg
	 */
	public String getTkglTszg()
	{
		return tkglTszg;
	}

	/**
	 * @param tkglTszg
	 *            the tkglTszg to set
	 */
	public void setTkglTszg(String tkglTszg)
	{
		this.tkglTszg = tkglTszg;
	}

	/**
	 * @return the tkglAddtime
	 */
	public Date getTkglAddtime()
	{
		return tkglAddtime;
	}

	/**
	 * @param tkglAddtime
	 *            the tkglAddtime to set
	 */
	public void setTkglAddtime(Date tkglAddtime)
	{
		this.tkglAddtime = tkglAddtime;
	}

	/**
	 * @return the tkglDelflag
	 */
	public Long getTkglDelflag()
	{
		return tkglDelflag;
	}

	/**
	 * @param tkglDelflag
	 *            the tkglDelflag to set
	 */
	public void setTkglDelflag(Long tkglDelflag)
	{
		this.tkglDelflag = tkglDelflag;
	}

}
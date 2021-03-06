package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Resume_fj implements Serializable
{
	private static final long serialVersionUID = 6315445037779480800L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column RESUME_FJ.RESUME_FJID
	 * 
	 * @mbggenerated
	 */
	private String resumeFjid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column RESUME_FJ.RESUME_ID
	 * 
	 * @mbggenerated
	 */
	private String resumeId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column RESUME_FJ.RESUME_FJ
	 * 
	 * @mbggenerated
	 */
	private String resumeFj;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column RESUME_FJ.RESUME_ADDTIME
	 * 
	 * @mbggenerated
	 */
	private Date resumeAddtime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column RESUME_FJ.RESUME_DELFLAG
	 * 
	 * @mbggenerated
	 */
	private Long resumeDelflag;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column RESUME_FJ.RESUME_FJID
	 * 
	 * @return the value of RESUME_FJ.RESUME_FJID
	 * 
	 * @mbggenerated
	 */
	public String getResumeFjid()
	{
		return resumeFjid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column RESUME_FJ.RESUME_FJID
	 * 
	 * @param resumeFjid
	 *            the value for RESUME_FJ.RESUME_FJID
	 * 
	 * @mbggenerated
	 */
	public void setResumeFjid(String resumeFjid)
	{
		this.resumeFjid = resumeFjid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column RESUME_FJ.RESUME_ID
	 * 
	 * @return the value of RESUME_FJ.RESUME_ID
	 * 
	 * @mbggenerated
	 */
	public String getResumeId()
	{
		return resumeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column RESUME_FJ.RESUME_ID
	 * 
	 * @param resumeId
	 *            the value for RESUME_FJ.RESUME_ID
	 * 
	 * @mbggenerated
	 */
	public void setResumeId(String resumeId)
	{
		this.resumeId = resumeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column RESUME_FJ.RESUME_FJ
	 * 
	 * @return the value of RESUME_FJ.RESUME_FJ
	 * 
	 * @mbggenerated
	 */
	public String getResumeFj()
	{
		return resumeFj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column RESUME_FJ.RESUME_FJ
	 * 
	 * @param resumeFj
	 *            the value for RESUME_FJ.RESUME_FJ
	 * 
	 * @mbggenerated
	 */
	public void setResumeFj(String resumeFj)
	{
		this.resumeFj = resumeFj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column RESUME_FJ.RESUME_ADDTIME
	 * 
	 * @return the value of RESUME_FJ.RESUME_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public Date getResumeAddtime()
	{
		return resumeAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column RESUME_FJ.RESUME_ADDTIME
	 * 
	 * @param resumeAddtime
	 *            the value for RESUME_FJ.RESUME_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public void setResumeAddtime(Date resumeAddtime)
	{
		this.resumeAddtime = resumeAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column RESUME_FJ.RESUME_DELFLAG
	 * 
	 * @return the value of RESUME_FJ.RESUME_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public Long getResumeDelflag()
	{
		return resumeDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column RESUME_FJ.RESUME_DELFLAG
	 * 
	 * @param resumeDelflag
	 *            the value for RESUME_FJ.RESUME_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public void setResumeDelflag(Long resumeDelflag)
	{
		this.resumeDelflag = resumeDelflag;
	}
}
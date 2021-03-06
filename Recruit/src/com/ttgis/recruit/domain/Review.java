package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable
{
	private static final long serialVersionUID = -304815297890254351L;
	private String bbsNr;

	public String getBbsNr()
	{
		return bbsNr;
	}

	public void setBbsNr(String bbsNr)
	{
		this.bbsNr = bbsNr;
	}

	/**
	 * This field was genera;
	 * 
	 * ted by MyBatis Generator. This field corresponds to the database column
	 * REVIEW.Review_ID
	 * 
	 * @mbggenerated
	 */
	private String reviewId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column REVIEW.Review_PLSJ
	 * 
	 * @mbggenerated
	 */
	private Date reviewPlsj;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column REVIEW.Review_NR
	 * 
	 * @mbggenerated
	 */
	private String reviewNr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column REVIEW.Review_PLR
	 * 
	 * @mbggenerated
	 */
	private String reviewPlr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column REVIEW.Review_DELFLAG
	 * 
	 * @mbggenerated
	 */
	private Long reviewDelflag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column REVIEW.BBS_ID
	 * 
	 * @mbggenerated
	 */
	private String bbsId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.Review_ID
	 * 
	 * @return the value of REVIEW.Review_ID
	 * @mbggenerated
	 */
	public String getReviewId()
	{
		return reviewId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.Review_ID
	 * 
	 * @param reviewId
	 *            the value for REVIEW.Review_ID
	 * @mbggenerated
	 */
	public void setReviewId(String reviewId)
	{
		this.reviewId = reviewId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.Review_PLSJ
	 * 
	 * @return the value of REVIEW.Review_PLSJ
	 * @mbggenerated
	 */
	public Date getReviewPlsj()
	{
		return reviewPlsj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.Review_PLSJ
	 * 
	 * @param reviewPlsj
	 *            the value for REVIEW.Review_PLSJ
	 * @mbggenerated
	 */
	public void setReviewPlsj(Date reviewPlsj)
	{
		this.reviewPlsj = reviewPlsj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.Review_NR
	 * 
	 * @return the value of REVIEW.Review_NR
	 * @mbggenerated
	 */
	public String getReviewNr()
	{
		return reviewNr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.Review_NR
	 * 
	 * @param reviewNr
	 *            the value for REVIEW.Review_NR
	 * @mbggenerated
	 */
	public void setReviewNr(String reviewNr)
	{
		this.reviewNr = reviewNr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.Review_PLR
	 * 
	 * @return the value of REVIEW.Review_PLR
	 * @mbggenerated
	 */
	public String getReviewPlr()
	{
		return reviewPlr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.Review_PLR
	 * 
	 * @param reviewPlr
	 *            the value for REVIEW.Review_PLR
	 * @mbggenerated
	 */
	public void setReviewPlr(String reviewPlr)
	{
		this.reviewPlr = reviewPlr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.Review_DELFLAG
	 * 
	 * @return the value of REVIEW.Review_DELFLAG
	 * @mbggenerated
	 */
	public Long getReviewDelflag()
	{
		return reviewDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.Review_DELFLAG
	 * 
	 * @param reviewDelflag
	 *            the value for REVIEW.Review_DELFLAG
	 * @mbggenerated
	 */
	public void setReviewDelflag(Long reviewDelflag)
	{
		this.reviewDelflag = reviewDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column REVIEW.BBS_ID
	 * 
	 * @return the value of REVIEW.BBS_ID
	 * @mbggenerated
	 */
	public String getBbsId()
	{
		return bbsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column REVIEW.BBS_ID
	 * 
	 * @param bbsId
	 *            the value for REVIEW.BBS_ID
	 * @mbggenerated
	 */
	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

	private String plsj;

	public String getPlsj()
	{
		return plsj;
	}

	public void setPlsj(String plsj)
	{
		this.plsj = plsj;
	}

}
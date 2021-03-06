package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Userinfo implements Serializable
{
	private static final long serialVersionUID = -8055619889441282343L;

	private String userSfzh;

	public String getUserSfzh()
	{
		return userSfzh;
	}

	public void setUserSfzh(String userSfzh)
	{
		this.userSfzh = userSfzh;
	}

	private String sfjy;

	public String getSfjy()
	{
		return sfjy;
	}

	public void setSfjy(String sfjy)
	{
		this.sfjy = sfjy;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_ID
	 * 
	 * @mbggenerated
	 */
	private String userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_NAME
	 * 
	 * @mbggenerated
	 */
	private String userName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_PASSWORD
	 * 
	 * @mbggenerated
	 */
	private String userPassword;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_IDCARD
	 * 
	 * @mbggenerated
	 */
	private String userIdcard;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_AGE
	 * 
	 * @mbggenerated
	 */
	private Long userAge;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_SEX
	 * 
	 * @mbggenerated
	 */
	private String userSex;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_TELEPHONE
	 * 
	 * @mbggenerated
	 */
	private Long userTelephone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_EMAIL
	 * 
	 * @mbggenerated
	 */
	private String userEmail;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_ADDTIME
	 * 
	 * @mbggenerated
	 */
	private Date userAddtime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_DELFLAG
	 * 
	 * @mbggenerated
	 */
	private Long userDelflag;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_FZR
	 * 
	 * @mbggenerated
	 */
	private String userFzr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_LXR
	 * 
	 * @mbggenerated
	 */
	private String userLxr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_DWJJ
	 * 
	 * @mbggenerated
	 */
	private String userDwjj;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERINFO.USER_SJDM
	 * 
	 * @mbggenerated
	 */
	private String userSjdm;
	/**
	 * 是否临时账号
	 */
	private String userSfls;
	/**
	 * 对应简历ID
	 */
	private String userJlid;

	public String getUserSfls()
	{
		return userSfls;
	}

	public void setUserSfls(String userSfls)
	{
		this.userSfls = userSfls;
	}

	public String getUserJlid()
	{
		return userJlid;
	}

	public void setUserJlid(String userJlid)
	{
		this.userJlid = userJlid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_ID
	 * 
	 * @return the value of USERINFO.USER_ID
	 * 
	 * @mbggenerated
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_ID
	 * 
	 * @param userId
	 *            the value for USERINFO.USER_ID
	 * 
	 * @mbggenerated
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_NAME
	 * 
	 * @return the value of USERINFO.USER_NAME
	 * 
	 * @mbggenerated
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_NAME
	 * 
	 * @param userName
	 *            the value for USERINFO.USER_NAME
	 * 
	 * @mbggenerated
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_PASSWORD
	 * 
	 * @return the value of USERINFO.USER_PASSWORD
	 * 
	 * @mbggenerated
	 */
	public String getUserPassword()
	{
		return userPassword;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_PASSWORD
	 * 
	 * @param userPassword
	 *            the value for USERINFO.USER_PASSWORD
	 * 
	 * @mbggenerated
	 */
	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_IDCARD
	 * 
	 * @return the value of USERINFO.USER_IDCARD
	 * 
	 * @mbggenerated
	 */
	public String getUserIdcard()
	{
		return userIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_IDCARD
	 * 
	 * @param userIdcard
	 *            the value for USERINFO.USER_IDCARD
	 * 
	 * @mbggenerated
	 */
	public void setUserIdcard(String userIdcard)
	{
		this.userIdcard = userIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_AGE
	 * 
	 * @return the value of USERINFO.USER_AGE
	 * 
	 * @mbggenerated
	 */
	public Long getUserAge()
	{
		return userAge;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_AGE
	 * 
	 * @param userAge
	 *            the value for USERINFO.USER_AGE
	 * 
	 * @mbggenerated
	 */
	public void setUserAge(Long userAge)
	{
		this.userAge = userAge;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_SEX
	 * 
	 * @return the value of USERINFO.USER_SEX
	 * 
	 * @mbggenerated
	 */
	public String getUserSex()
	{
		return userSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_SEX
	 * 
	 * @param userSex
	 *            the value for USERINFO.USER_SEX
	 * 
	 * @mbggenerated
	 */
	public void setUserSex(String userSex)
	{
		this.userSex = userSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_TELEPHONE
	 * 
	 * @return the value of USERINFO.USER_TELEPHONE
	 * 
	 * @mbggenerated
	 */
	public Long getUserTelephone()
	{
		return userTelephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_TELEPHONE
	 * 
	 * @param userTelephone
	 *            the value for USERINFO.USER_TELEPHONE
	 * 
	 * @mbggenerated
	 */
	public void setUserTelephone(Long userTelephone)
	{
		this.userTelephone = userTelephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_EMAIL
	 * 
	 * @return the value of USERINFO.USER_EMAIL
	 * 
	 * @mbggenerated
	 */
	public String getUserEmail()
	{
		return userEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_EMAIL
	 * 
	 * @param userEmail
	 *            the value for USERINFO.USER_EMAIL
	 * 
	 * @mbggenerated
	 */
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_ADDTIME
	 * 
	 * @return the value of USERINFO.USER_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public Date getUserAddtime()
	{
		return userAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_ADDTIME
	 * 
	 * @param userAddtime
	 *            the value for USERINFO.USER_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public void setUserAddtime(Date userAddtime)
	{
		this.userAddtime = userAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_DELFLAG
	 * 
	 * @return the value of USERINFO.USER_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public Long getUserDelflag()
	{
		return userDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_DELFLAG
	 * 
	 * @param userDelflag
	 *            the value for USERINFO.USER_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public void setUserDelflag(Long userDelflag)
	{
		this.userDelflag = userDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_FZR
	 * 
	 * @return the value of USERINFO.USER_FZR
	 * 
	 * @mbggenerated
	 */
	public String getUserFzr()
	{
		return userFzr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_FZR
	 * 
	 * @param userFzr
	 *            the value for USERINFO.USER_FZR
	 * 
	 * @mbggenerated
	 */
	public void setUserFzr(String userFzr)
	{
		this.userFzr = userFzr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_LXR
	 * 
	 * @return the value of USERINFO.USER_LXR
	 * 
	 * @mbggenerated
	 */
	public String getUserLxr()
	{
		return userLxr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_LXR
	 * 
	 * @param userLxr
	 *            the value for USERINFO.USER_LXR
	 * 
	 * @mbggenerated
	 */
	public void setUserLxr(String userLxr)
	{
		this.userLxr = userLxr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_DWJJ
	 * 
	 * @return the value of USERINFO.USER_DWJJ
	 * 
	 * @mbggenerated
	 */
	public String getUserDwjj()
	{
		return userDwjj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_DWJJ
	 * 
	 * @param userDwjj
	 *            the value for USERINFO.USER_DWJJ
	 * 
	 * @mbggenerated
	 */
	public void setUserDwjj(String userDwjj)
	{
		this.userDwjj = userDwjj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERINFO.USER_SJDM
	 * 
	 * @return the value of USERINFO.USER_SJDM
	 * 
	 * @mbggenerated
	 */
	public String getUserSjdm()
	{
		return userSjdm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERINFO.USER_SJDM
	 * 
	 * @param userSjdm
	 *            the value for USERINFO.USER_SJDM
	 * 
	 * @mbggenerated
	 */
	public void setUserSjdm(String userSjdm)
	{
		this.userSjdm = userSjdm;
	}
}
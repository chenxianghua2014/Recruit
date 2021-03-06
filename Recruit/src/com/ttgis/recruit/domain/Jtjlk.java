package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Jtjlk implements Serializable {
	private static final long serialVersionUID = 4274498008573367617L;
	private String bmglId;
	private String zzjgName;

	private int jtjlkstar;
	
	private String txt;

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public int getJtjlkstar() {
		return jtjlkstar;
	}

	public void setJtjlkstar(int jtjlkstar) {
		this.jtjlkstar = jtjlkstar;
	}

	public String getBmglId() {
		return bmglId;
	}

	public String getZzjgName() {
		return zzjgName;
	}

	public void setZzjgName(String zzjgName) {
		this.zzjgName = zzjgName;
	}

	public void setBmglId(String bmglId) {
		this.bmglId = bmglId;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ID
	 * 
	 * @mbggenerated
	 */
	private String jtjlkId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.ZZJG_ID
	 * 
	 * @mbggenerated
	 */
	private String zzjgId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_NAME
	 * 
	 * @mbggenerated
	 */
	private String jtjlkName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_SEX
	 * 
	 * @mbggenerated
	 */
	private String jtjlkSex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_BYXX
	 * 
	 * @mbggenerated
	 */
	private String jtjlkByxx;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ZY
	 * 
	 * @mbggenerated
	 */
	private String jtjlkZy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_XL
	 * 
	 * @mbggenerated
	 */
	private String jtjlkXl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ZW
	 * 
	 * @mbggenerated
	 */
	private String jtjlkZw;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_JLZT
	 * 
	 * @mbggenerated
	 */
	private String jtjlkJlzt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_MSZT
	 * 
	 * @mbggenerated
	 */
	private String jtjlkMszt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_CPJG
	 * 
	 * @mbggenerated
	 */
	private String jtjlkCpjg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ADDTIME
	 * 
	 * @mbggenerated
	 */
	private Date jtjlkAddtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_DELFLAG
	 * 
	 * @mbggenerated
	 */
	private Long jtjlkDelflag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_CPCJ
	 * 
	 * @mbggenerated
	 */
	private String jtjlkCpcj;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_USERID
	 * 
	 * @mbggenerated
	 */
	private String jtjlkUserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_GWLB
	 * 
	 * @mbggenerated
	 */
	private String jtjlkGwlb;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_POSITION_ID
	 * 
	 * @mbggenerated
	 */
	private String jtjlkPositionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_APCPGWLB
	 * 
	 * @mbggenerated
	 */
	private String jtjlkApcpgwlb;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ZZLYGW
	 * 
	 * @mbggenerated
	 */
	private String jtjlkZzlygw;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_ZZLYGW_ID
	 * 
	 * @mbggenerated
	 */
	private String jtjlkZzlygwId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_YXPM
	 * 
	 * @mbggenerated
	 */
	private String jtjlkYxpm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_BJPM
	 * 
	 * @mbggenerated
	 */
	private String jtjlkBjpm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_HKD
	 * 
	 * @mbggenerated
	 */
	private String jtjlkHkd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_LY
	 * 
	 * @mbggenerated
	 */
	private String jtjlkLy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_OLDID
	 * 
	 * @mbggenerated
	 */
	private String jtjlkOldid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_CSRQ
	 * 
	 * @mbggenerated
	 */
	private String jtjlkCsrq;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_SFZH
	 * 
	 * @mbggenerated
	 */
	private String jtjlkSfzh;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column JTJLK.JTJLK_JTJLKFLAG
	 * 
	 * @mbggenerated
	 */
	private Integer jtjlkJtjlkflag;

	private String jtjlkTtqjlzt;

	private String jtjlkTtqmszt;

	private String jtjlkTtqcpjg;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ID
	 * 
	 * @return the value of JTJLK.JTJLK_ID
	 * @mbggenerated
	 */
	public String getJtjlkId() {
		return jtjlkId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ID
	 * 
	 * @param jtjlkId
	 *            the value for JTJLK.JTJLK_ID
	 * @mbggenerated
	 */
	public void setJtjlkId(String jtjlkId) {
		this.jtjlkId = jtjlkId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.ZZJG_ID
	 * 
	 * @return the value of JTJLK.ZZJG_ID
	 * @mbggenerated
	 */
	public String getZzjgId() {
		return zzjgId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.ZZJG_ID
	 * 
	 * @param zzjgId
	 *            the value for JTJLK.ZZJG_ID
	 * @mbggenerated
	 */
	public void setZzjgId(String zzjgId) {
		this.zzjgId = zzjgId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_NAME
	 * 
	 * @return the value of JTJLK.JTJLK_NAME
	 * @mbggenerated
	 */
	public String getJtjlkName() {
		return jtjlkName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_NAME
	 * 
	 * @param jtjlkName
	 *            the value for JTJLK.JTJLK_NAME
	 * @mbggenerated
	 */
	public void setJtjlkName(String jtjlkName) {
		this.jtjlkName = jtjlkName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_SEX
	 * 
	 * @return the value of JTJLK.JTJLK_SEX
	 * @mbggenerated
	 */
	public String getJtjlkSex() {
		return jtjlkSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_SEX
	 * 
	 * @param jtjlkSex
	 *            the value for JTJLK.JTJLK_SEX
	 * @mbggenerated
	 */
	public void setJtjlkSex(String jtjlkSex) {
		this.jtjlkSex = jtjlkSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_BYXX
	 * 
	 * @return the value of JTJLK.JTJLK_BYXX
	 * @mbggenerated
	 */
	public String getJtjlkByxx() {
		return jtjlkByxx;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_BYXX
	 * 
	 * @param jtjlkByxx
	 *            the value for JTJLK.JTJLK_BYXX
	 * @mbggenerated
	 */
	public void setJtjlkByxx(String jtjlkByxx) {
		this.jtjlkByxx = jtjlkByxx;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ZY
	 * 
	 * @return the value of JTJLK.JTJLK_ZY
	 * @mbggenerated
	 */
	public String getJtjlkZy() {
		return jtjlkZy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ZY
	 * 
	 * @param jtjlkZy
	 *            the value for JTJLK.JTJLK_ZY
	 * @mbggenerated
	 */
	public void setJtjlkZy(String jtjlkZy) {
		this.jtjlkZy = jtjlkZy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_XL
	 * 
	 * @return the value of JTJLK.JTJLK_XL
	 * @mbggenerated
	 */
	public String getJtjlkXl() {
		return jtjlkXl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_XL
	 * 
	 * @param jtjlkXl
	 *            the value for JTJLK.JTJLK_XL
	 * @mbggenerated
	 */
	public void setJtjlkXl(String jtjlkXl) {
		this.jtjlkXl = jtjlkXl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ZW
	 * 
	 * @return the value of JTJLK.JTJLK_ZW
	 * @mbggenerated
	 */
	public String getJtjlkZw() {
		return jtjlkZw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ZW
	 * 
	 * @param jtjlkZw
	 *            the value for JTJLK.JTJLK_ZW
	 * @mbggenerated
	 */
	public void setJtjlkZw(String jtjlkZw) {
		this.jtjlkZw = jtjlkZw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_JLZT
	 * 
	 * @return the value of JTJLK.JTJLK_JLZT
	 * @mbggenerated
	 */
	public String getJtjlkJlzt() {
		return jtjlkJlzt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_JLZT
	 * 
	 * @param jtjlkJlzt
	 *            the value for JTJLK.JTJLK_JLZT
	 * @mbggenerated
	 */
	public void setJtjlkJlzt(String jtjlkJlzt) {
		this.jtjlkJlzt = jtjlkJlzt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_MSZT
	 * 
	 * @return the value of JTJLK.JTJLK_MSZT
	 * @mbggenerated
	 */
	public String getJtjlkMszt() {
		return jtjlkMszt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_MSZT
	 * 
	 * @param jtjlkMszt
	 *            the value for JTJLK.JTJLK_MSZT
	 * @mbggenerated
	 */
	public void setJtjlkMszt(String jtjlkMszt) {
		this.jtjlkMszt = jtjlkMszt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_CPJG
	 * 
	 * @return the value of JTJLK.JTJLK_CPJG
	 * @mbggenerated
	 */
	public String getJtjlkCpjg() {
		return jtjlkCpjg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_CPJG
	 * 
	 * @param jtjlkCpjg
	 *            the value for JTJLK.JTJLK_CPJG
	 * @mbggenerated
	 */
	public void setJtjlkCpjg(String jtjlkCpjg) {
		this.jtjlkCpjg = jtjlkCpjg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ADDTIME
	 * 
	 * @return the value of JTJLK.JTJLK_ADDTIME
	 * @mbggenerated
	 */
	public Date getJtjlkAddtime() {
		return jtjlkAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ADDTIME
	 * 
	 * @param jtjlkAddtime
	 *            the value for JTJLK.JTJLK_ADDTIME
	 * @mbggenerated
	 */
	public void setJtjlkAddtime(Date jtjlkAddtime) {
		this.jtjlkAddtime = jtjlkAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_DELFLAG
	 * 
	 * @return the value of JTJLK.JTJLK_DELFLAG
	 * @mbggenerated
	 */
	public Long getJtjlkDelflag() {
		return jtjlkDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_DELFLAG
	 * 
	 * @param jtjlkDelflag
	 *            the value for JTJLK.JTJLK_DELFLAG
	 * @mbggenerated
	 */
	public void setJtjlkDelflag(Long jtjlkDelflag) {
		this.jtjlkDelflag = jtjlkDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_CPCJ
	 * 
	 * @return the value of JTJLK.JTJLK_CPCJ
	 * @mbggenerated
	 */
	public String getJtjlkCpcj() {
		return jtjlkCpcj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_CPCJ
	 * 
	 * @param jtjlkCpcj
	 *            the value for JTJLK.JTJLK_CPCJ
	 * @mbggenerated
	 */
	public void setJtjlkCpcj(String jtjlkCpcj) {
		this.jtjlkCpcj = jtjlkCpcj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_USERID
	 * 
	 * @return the value of JTJLK.JTJLK_USERID
	 * @mbggenerated
	 */
	public String getJtjlkUserid() {
		return jtjlkUserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_USERID
	 * 
	 * @param jtjlkUserid
	 *            the value for JTJLK.JTJLK_USERID
	 * @mbggenerated
	 */
	public void setJtjlkUserid(String jtjlkUserid) {
		this.jtjlkUserid = jtjlkUserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_GWLB
	 * 
	 * @return the value of JTJLK.JTJLK_GWLB
	 * @mbggenerated
	 */
	public String getJtjlkGwlb() {
		return jtjlkGwlb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_GWLB
	 * 
	 * @param jtjlkGwlb
	 *            the value for JTJLK.JTJLK_GWLB
	 * @mbggenerated
	 */
	public void setJtjlkGwlb(String jtjlkGwlb) {
		this.jtjlkGwlb = jtjlkGwlb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_POSITION_ID
	 * 
	 * @return the value of JTJLK.JTJLK_POSITION_ID
	 * @mbggenerated
	 */
	public String getJtjlkPositionId() {
		return jtjlkPositionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_POSITION_ID
	 * 
	 * @param jtjlkPositionId
	 *            the value for JTJLK.JTJLK_POSITION_ID
	 * @mbggenerated
	 */
	public void setJtjlkPositionId(String jtjlkPositionId) {
		this.jtjlkPositionId = jtjlkPositionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_APCPGWLB
	 * 
	 * @return the value of JTJLK.JTJLK_APCPGWLB
	 * @mbggenerated
	 */
	public String getJtjlkApcpgwlb() {
		return jtjlkApcpgwlb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_APCPGWLB
	 * 
	 * @param jtjlkApcpgwlb
	 *            the value for JTJLK.JTJLK_APCPGWLB
	 * @mbggenerated
	 */
	public void setJtjlkApcpgwlb(String jtjlkApcpgwlb) {
		this.jtjlkApcpgwlb = jtjlkApcpgwlb;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ZZLYGW
	 * 
	 * @return the value of JTJLK.JTJLK_ZZLYGW
	 * @mbggenerated
	 */
	public String getJtjlkZzlygw() {
		return jtjlkZzlygw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ZZLYGW
	 * 
	 * @param jtjlkZzlygw
	 *            the value for JTJLK.JTJLK_ZZLYGW
	 * @mbggenerated
	 */
	public void setJtjlkZzlygw(String jtjlkZzlygw) {
		this.jtjlkZzlygw = jtjlkZzlygw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_ZZLYGW_ID
	 * 
	 * @return the value of JTJLK.JTJLK_ZZLYGW_ID
	 * @mbggenerated
	 */
	public String getJtjlkZzlygwId() {
		return jtjlkZzlygwId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_ZZLYGW_ID
	 * 
	 * @param jtjlkZzlygwId
	 *            the value for JTJLK.JTJLK_ZZLYGW_ID
	 * @mbggenerated
	 */
	public void setJtjlkZzlygwId(String jtjlkZzlygwId) {
		this.jtjlkZzlygwId = jtjlkZzlygwId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_YXPM
	 * 
	 * @return the value of JTJLK.JTJLK_YXPM
	 * @mbggenerated
	 */
	public String getJtjlkYxpm() {
		return jtjlkYxpm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_YXPM
	 * 
	 * @param jtjlkYxpm
	 *            the value for JTJLK.JTJLK_YXPM
	 * @mbggenerated
	 */
	public void setJtjlkYxpm(String jtjlkYxpm) {
		this.jtjlkYxpm = jtjlkYxpm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_BJPM
	 * 
	 * @return the value of JTJLK.JTJLK_BJPM
	 * @mbggenerated
	 */
	public String getJtjlkBjpm() {
		return jtjlkBjpm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_BJPM
	 * 
	 * @param jtjlkBjpm
	 *            the value for JTJLK.JTJLK_BJPM
	 * @mbggenerated
	 */
	public void setJtjlkBjpm(String jtjlkBjpm) {
		this.jtjlkBjpm = jtjlkBjpm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_HKD
	 * 
	 * @return the value of JTJLK.JTJLK_HKD
	 * @mbggenerated
	 */
	public String getJtjlkHkd() {
		return jtjlkHkd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_HKD
	 * 
	 * @param jtjlkHkd
	 *            the value for JTJLK.JTJLK_HKD
	 * @mbggenerated
	 */
	public void setJtjlkHkd(String jtjlkHkd) {
		this.jtjlkHkd = jtjlkHkd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_LY
	 * 
	 * @return the value of JTJLK.JTJLK_LY
	 * @mbggenerated
	 */
	public String getJtjlkLy() {
		return jtjlkLy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_LY
	 * 
	 * @param jtjlkLy
	 *            the value for JTJLK.JTJLK_LY
	 * @mbggenerated
	 */
	public void setJtjlkLy(String jtjlkLy) {
		this.jtjlkLy = jtjlkLy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_OLDID
	 * 
	 * @return the value of JTJLK.JTJLK_OLDID
	 * @mbggenerated
	 */
	public String getJtjlkOldid() {
		return jtjlkOldid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_OLDID
	 * 
	 * @param jtjlkOldid
	 *            the value for JTJLK.JTJLK_OLDID
	 * @mbggenerated
	 */
	public void setJtjlkOldid(String jtjlkOldid) {
		this.jtjlkOldid = jtjlkOldid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_CSRQ
	 * 
	 * @return the value of JTJLK.JTJLK_CSRQ
	 * @mbggenerated
	 */
	public String getJtjlkCsrq() {
		return jtjlkCsrq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_CSRQ
	 * 
	 * @param jtjlkCsrq
	 *            the value for JTJLK.JTJLK_CSRQ
	 * @mbggenerated
	 */
	public void setJtjlkCsrq(String jtjlkCsrq) {
		this.jtjlkCsrq = jtjlkCsrq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_SFZH
	 * 
	 * @return the value of JTJLK.JTJLK_SFZH
	 * @mbggenerated
	 */
	public String getJtjlkSfzh() {
		return jtjlkSfzh;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_SFZH
	 * 
	 * @param jtjlkSfzh
	 *            the value for JTJLK.JTJLK_SFZH
	 * @mbggenerated
	 */
	public void setJtjlkSfzh(String jtjlkSfzh) {
		this.jtjlkSfzh = jtjlkSfzh;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column JTJLK.JTJLK_JTJLKFLAG
	 * 
	 * @return the value of JTJLK.JTJLK_JTJLKFLAG
	 * @mbggenerated
	 */
	public Integer getJtjlkJtjlkflag() {
		return jtjlkJtjlkflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column JTJLK.JTJLK_JTJLKFLAG
	 * 
	 * @param jtjlkJtjlkflag
	 *            the value for JTJLK.JTJLK_JTJLKFLAG
	 * @mbggenerated
	 */
	public void setJtjlkJtjlkflag(Integer jtjlkJtjlkflag) {
		this.jtjlkJtjlkflag = jtjlkJtjlkflag;
	}

	public String getJtjlkTtqjlzt() {
		return jtjlkTtqjlzt;
	}

	public void setJtjlkTtqjlzt(String jtjlkTtqjlzt) {
		this.jtjlkTtqjlzt = jtjlkTtqjlzt;
	}

	public String getJtjlkTtqmszt() {
		return jtjlkTtqmszt;
	}

	public void setJtjlkTtqmszt(String jtjlkTtqmszt) {
		this.jtjlkTtqmszt = jtjlkTtqmszt;
	}

	public String getJtjlkTtqcpjg() {
		return jtjlkTtqcpjg;
	}

	public void setJtjlkTtqcpjg(String jtjlkTtqcpjg) {
		this.jtjlkTtqcpjg = jtjlkTtqcpjg;
	}

}
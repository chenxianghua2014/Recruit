package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Ksxcgl implements Serializable
{
	private static final long serialVersionUID = -5036419507152916994L;
	private String sex;
	private String birthday;
	private String kcname;
	private String kssj;
	private String byyx;
	private String zyl;
	private String xl;

	public String getByyx()
	{
		return byyx;
	}

	public void setByyx(String byyx)
	{
		this.byyx = byyx;
	}

	public String getZyl()
	{
		return zyl;
	}

	public void setZyl(String zyl)
	{
		this.zyl = zyl;
	}

	public String getXl()
	{
		return xl;
	}

	public void setXl(String xl)
	{
		this.xl = xl;
	}

	public String getKssj()
	{
		return kssj;
	}

	public void setKssj(String kssj)
	{
		this.kssj = kssj;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getKcname()
	{
		return kcname;
	}

	public void setKcname(String kcname)
	{
		this.kcname = kcname;
	}

	private Double ksxcglJsgYycj;

	public Double getKsxcglJsgYycj()
	{
		return ksxcglJsgYycj;
	}

	public void setKsxcglJsgYycj(Double ksxcglJsgYycj)
	{
		this.ksxcglJsgYycj = ksxcglJsgYycj;
	}

	private String ksxcglBmglId;

	public String getKsxcglBmglId()
	{
		return ksxcglBmglId;
	}

	public void setKsxcglBmglId(String ksxcglBmglId)
	{
		this.ksxcglBmglId = ksxcglBmglId;
	}

	private String glgcj;
	private String jsgcj;

	public String getGlgcj()
	{
		return glgcj;
	}

	public void setGlgcj(String glgcj)
	{
		this.glgcj = glgcj;
	}

	public String getJsgcj()
	{
		return jsgcj;
	}

	public void setJsgcj(String jsgcj)
	{
		this.jsgcj = jsgcj;
	}

	private String dwdm;

	public String getDwdm()
	{
		return dwdm;
	}

	public void setDwdm(String dwdm)
	{
		this.dwdm = dwdm;
	}

	private Double ksxcglKscjYycj;

	public Double getKsxcglKscjYycj()
	{
		return ksxcglKscjYycj;
	}

	public void setKsxcglKscjYycj(Double ksxcglKscjYycj)
	{
		this.ksxcglKscjYycj = ksxcglKscjYycj;
	}

	private String ksxcglKc;

	public String getKsxcglKc()
	{
		return ksxcglKc;
	}

	public void setKsxcglKc(String ksxcglKc)
	{
		this.ksxcglKc = ksxcglKc;
	}

	private String ksxmId;

	public String getKsxmId()
	{
		return ksxmId;
	}

	public void setKsxmId(String ksxmId)
	{
		this.ksxmId = ksxmId;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_ID
	 * 
	 * @mbggenerated
	 */
	private String ksxcglId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KCGL_ID
	 * 
	 * @mbggenerated
	 */
	private String kcglId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_NAME
	 * 
	 * @mbggenerated
	 */
	private String ksxcglName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_IDCARD
	 * 
	 * @mbggenerated
	 */
	private String ksxcglIdcard;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_BKDW
	 * 
	 * @mbggenerated
	 */
	private String ksxcglBkdw;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_KKSLX
	 * 
	 * @mbggenerated
	 */
	private String ksxcglKkslx;
	private String[] kkslx;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_WJQK
	 * 
	 * @mbggenerated
	 */
	private String ksxcglWjqk;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_TSZG
	 * 
	 * @mbggenerated
	 */
	private String ksxcglTszg;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_KSCJ_GLG
	 * 
	 * @mbggenerated
	 */
	private Double ksxcglKscjGlg;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_KSCJ_JSG
	 * 
	 * @mbggenerated
	 */
	private Double ksxcglKscjJsg;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_YCSJ
	 * 
	 * @mbggenerated
	 */
	private Long ksxcglYcsj;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_ADDTIME
	 * 
	 * @mbggenerated
	 */
	private Date ksxcglAddtime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_DELFLAG
	 * 
	 * @mbggenerated
	 */
	private Long ksxcglDelflag;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column KSXCGL.KSXCGL_XGCSBG
	 * 
	 * @mbggenerated
	 */
	private String ksxcglXgcsbg;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_ID
	 * 
	 * @return the value of KSXCGL.KSXCGL_ID
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglId()
	{
		return ksxcglId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_ID
	 * 
	 * @param ksxcglId
	 *            the value for KSXCGL.KSXCGL_ID
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglId(String ksxcglId)
	{
		this.ksxcglId = ksxcglId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KCGL_ID
	 * 
	 * @return the value of KSXCGL.KCGL_ID
	 * 
	 * @mbggenerated
	 */
	public String getKcglId()
	{
		return kcglId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KCGL_ID
	 * 
	 * @param kcglId
	 *            the value for KSXCGL.KCGL_ID
	 * 
	 * @mbggenerated
	 */
	public void setKcglId(String kcglId)
	{
		this.kcglId = kcglId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_NAME
	 * 
	 * @return the value of KSXCGL.KSXCGL_NAME
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglName()
	{
		return ksxcglName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_NAME
	 * 
	 * @param ksxcglName
	 *            the value for KSXCGL.KSXCGL_NAME
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglName(String ksxcglName)
	{
		this.ksxcglName = ksxcglName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_IDCARD
	 * 
	 * @return the value of KSXCGL.KSXCGL_IDCARD
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglIdcard()
	{
		return ksxcglIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_IDCARD
	 * 
	 * @param ksxcglIdcard
	 *            the value for KSXCGL.KSXCGL_IDCARD
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglIdcard(String ksxcglIdcard)
	{
		this.ksxcglIdcard = ksxcglIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_BKDW
	 * 
	 * @return the value of KSXCGL.KSXCGL_BKDW
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglBkdw()
	{
		return ksxcglBkdw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_BKDW
	 * 
	 * @param ksxcglBkdw
	 *            the value for KSXCGL.KSXCGL_BKDW
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglBkdw(String ksxcglBkdw)
	{
		this.ksxcglBkdw = ksxcglBkdw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_KKSLX
	 * 
	 * @return the value of KSXCGL.KSXCGL_KKSLX
	 * 
	 * @mbggenerated
	 */

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_KKSLX
	 * 
	 * @param ksxcglKkslx
	 *            the value for KSXCGL.KSXCGL_KKSLX
	 * 
	 * @mbggenerated
	 */

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_WJQK
	 * 
	 * @return the value of KSXCGL.KSXCGL_WJQK
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglWjqk()
	{
		return ksxcglWjqk;
	}

	public String getKsxcglKkslx()
	{
		return ksxcglKkslx;
	}

	public void setKsxcglKkslx(String ksxcglKkslx)
	{
		this.ksxcglKkslx = ksxcglKkslx;
	}

	public String[] getKkslx()
	{
		return kkslx;
	}

	public void setKkslx(String[] kkslx)
	{
		this.kkslx = kkslx;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_WJQK
	 * 
	 * @param ksxcglWjqk
	 *            the value for KSXCGL.KSXCGL_WJQK
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglWjqk(String ksxcglWjqk)
	{
		this.ksxcglWjqk = ksxcglWjqk;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_TSZG
	 * 
	 * @return the value of KSXCGL.KSXCGL_TSZG
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglTszg()
	{
		return ksxcglTszg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_TSZG
	 * 
	 * @param ksxcglTszg
	 *            the value for KSXCGL.KSXCGL_TSZG
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglTszg(String ksxcglTszg)
	{
		this.ksxcglTszg = ksxcglTszg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_KSCJ_GLG
	 * 
	 * @return the value of KSXCGL.KSXCGL_KSCJ_GLG
	 * 
	 * @mbggenerated
	 */
	public Double getKsxcglKscjGlg()
	{
		return ksxcglKscjGlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_KSCJ_GLG
	 * 
	 * @param ksxcglKscjGlg
	 *            the value for KSXCGL.KSXCGL_KSCJ_GLG
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglKscjGlg(Double ksxcglKscjGlg)
	{
		this.ksxcglKscjGlg = ksxcglKscjGlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_KSCJ_JSG
	 * 
	 * @return the value of KSXCGL.KSXCGL_KSCJ_JSG
	 * 
	 * @mbggenerated
	 */
	public Double getKsxcglKscjJsg()
	{
		return ksxcglKscjJsg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_KSCJ_JSG
	 * 
	 * @param ksxcglKscjJsg
	 *            the value for KSXCGL.KSXCGL_KSCJ_JSG
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglKscjJsg(Double ksxcglKscjJsg)
	{
		this.ksxcglKscjJsg = ksxcglKscjJsg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_YCSJ
	 * 
	 * @return the value of KSXCGL.KSXCGL_YCSJ
	 * 
	 * @mbggenerated
	 */
	public Long getKsxcglYcsj()
	{
		return ksxcglYcsj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_YCSJ
	 * 
	 * @param ksxcglYcsj
	 *            the value for KSXCGL.KSXCGL_YCSJ
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglYcsj(Long ksxcglYcsj)
	{
		this.ksxcglYcsj = ksxcglYcsj;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_ADDTIME
	 * 
	 * @return the value of KSXCGL.KSXCGL_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public Date getKsxcglAddtime()
	{
		return ksxcglAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_ADDTIME
	 * 
	 * @param ksxcglAddtime
	 *            the value for KSXCGL.KSXCGL_ADDTIME
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglAddtime(Date ksxcglAddtime)
	{
		this.ksxcglAddtime = ksxcglAddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_DELFLAG
	 * 
	 * @return the value of KSXCGL.KSXCGL_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public Long getKsxcglDelflag()
	{
		return ksxcglDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_DELFLAG
	 * 
	 * @param ksxcglDelflag
	 *            the value for KSXCGL.KSXCGL_DELFLAG
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglDelflag(Long ksxcglDelflag)
	{
		this.ksxcglDelflag = ksxcglDelflag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column KSXCGL.KSXCGL_XGCSBG
	 * 
	 * @return the value of KSXCGL.KSXCGL_XGCSBG
	 * 
	 * @mbggenerated
	 */
	public String getKsxcglXgcsbg()
	{
		return ksxcglXgcsbg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column KSXCGL.KSXCGL_XGCSBG
	 * 
	 * @param ksxcglXgcsbg
	 *            the value for KSXCGL.KSXCGL_XGCSBG
	 * 
	 * @mbggenerated
	 */
	public void setKsxcglXgcsbg(String ksxcglXgcsbg)
	{
		this.ksxcglXgcsbg = ksxcglXgcsbg;
	}
}
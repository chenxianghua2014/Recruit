package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Resume implements Serializable
{
	private static final long serialVersionUID = 3089891437062436437L;
	private String resumeId;
	private Date resumeAddtime;
	private long resumeDelflag;

	private String resumeName;
	private String resumeSex;
	private String resumeSfzh;
	private String resumeBirthday;
	private String resumeRxqhkszcsProvince;
	private String resumeRxqhkszcsCity;
	private String resumeMqszcsProvince;
	private String resumeMqszcsCity;
	private String resumePhotos;
	//自制简历
    private String resumeZzjl;
    
	private String resumeTelphone;
	private String resumeEmail;
	private String resumeFj;
	private String resumeFjid;
	
	//添加民族RESUME_ITJTJN、籍贯省份RESUME_HDZS、籍贯城市RESUME_QTZS、健康状况RESUME_ITSXJN
	//入党时间RESUME_QTJN 参加工作时间RESUME_CJGZSJ、拟应聘工作岗位1 RESUME_NYPGW1拟应聘工作岗位RESUME_NYPGW2
	private String resumeItjtjn;
	private String resumeHdzs;
	private String resumeQtzs;
	private String resumeItsxjn;
	private String resumeQtjn;
	private String resumeCjgzsj;
	private String resumeNypgw1;
	private String resumeNypgw2;
	
	//政治面貌RESUME_ZZMM  职称（资格）RESUME_ZCZG 办公电话RESUME_BGSDH 学位RESUME_XUEW 
	//奖惩情况RESUME_JCQK  培训情况RESUME_PXQK  专业技能特长RESUME_SXHZJN 兴趣爱好RESUME_XQAH
	private String resumeZzmm;
	private String resumeZczg;
	private String resumeBgSdh;
	private String resumeXuew;
	private String resumeJcqk;
	private String resumePxqk;
	private String resumeSxhzjn;
	private String resumeXqah;
	
	public String getResumeZzjl() {
		return resumeZzjl;
	}

	public void setResumeZzjl(String resumeZzjl) {
		this.resumeZzjl = resumeZzjl;
	}



	public String getResumeFj()
	{
		return resumeFj;
	}

	public void setResumeFj(String resumeFj)
	{
		this.resumeFj = resumeFj;
	}


	public String getResumeFjid()
	{
		return resumeFjid;
	}

	public String ResumeFjid()
	{
		return resumeFjid;
	}

	public void setResumeFjid(String resumeFjid)
	{
		this.resumeFjid = resumeFjid;
	}

	public String ResumePhotos()
	{
		return resumePhotos;
	}

	public String getResumePhotos()
	{
		return resumePhotos;
	}

	public void setResumePhotos(String resumePhotos)
	{
		this.resumePhotos = resumePhotos;
	}

	public String ResumeSfzh()
	{
		return resumeSfzh;
	}

	public String getResumeSfzh()
	{
		return resumeSfzh;
	}

	public void setResumeSfzh(String resumeSfzh)
	{
		this.resumeSfzh = resumeSfzh;
	}

	public String getResumeId()
	{
		return resumeId;
	}

	public void setResumeId(String resumeId)
	{
		this.resumeId = resumeId;
	}

	public Date getResumeAddtime()
	{
		return resumeAddtime;
	}

	public void setResumeAddtime(Date resumeAddtime)
	{
		this.resumeAddtime = resumeAddtime;
	}

	public long getResumeDelflag()
	{
		return resumeDelflag;
	}

	public void setResumeDelflag(long resumeDelflag)
	{
		this.resumeDelflag = resumeDelflag;
	}

	public String getResumeName()
	{
		return resumeName;
	}

	public void setResumeName(String resumeName)
	{
		this.resumeName = resumeName;
	}

	public String getResumeSex()
	{
		return resumeSex;
	}

	public void setResumeSex(String resumeSex)
	{
		this.resumeSex = resumeSex;
	}

	public String getResumeBirthday()
	{
		return resumeBirthday;
	}

	public void setResumeBirthday(String resumeBirthday)
	{
		this.resumeBirthday = resumeBirthday;
	}

	public String getResumeRxqhkszcsProvince()
	{
		return resumeRxqhkszcsProvince;
	}

	public void setResumeRxqhkszcsProvince(String resumeRxqhkszcsProvince)
	{
		this.resumeRxqhkszcsProvince = resumeRxqhkszcsProvince;
	}

	public String getResumeRxqhkszcsCity()
	{
		return resumeRxqhkszcsCity;
	}

	public void setResumeRxqhkszcsCity(String resumeRxqhkszcsCity)
	{
		this.resumeRxqhkszcsCity = resumeRxqhkszcsCity;
	}

	public String getResumeMqszcsProvince()
	{
		return resumeMqszcsProvince;
	}

	public void setResumeMqszcsProvince(String resumeMqszcsProvince)
	{
		this.resumeMqszcsProvince = resumeMqszcsProvince;
	}

	public String getResumeMqszcsCity()
	{
		return resumeMqszcsCity;
	}

	public void setResumeMqszcsCity(String resumeMqszcsCity)
	{
		this.resumeMqszcsCity = resumeMqszcsCity;
	}

	public String getResumeTelphone()
	{
		return resumeTelphone;
	}

	public void setResumeTelphone(String resumeTelphone)
	{
		this.resumeTelphone = resumeTelphone;
	}

	public String getResumeEmail()
	{
		return resumeEmail;
	}

	public void setResumeEmail(String resumeEmail)
	{
		this.resumeEmail = resumeEmail;
	}

	public String getResumeItjtjn() {
		return resumeItjtjn;
	}

	public void setResumeItjtjn(String resumeItjtjn) {
		this.resumeItjtjn = resumeItjtjn;
	}

	public String getResumeHdzs() {
		return resumeHdzs;
	}

	public void setResumeHdzs(String resumeHdzs) {
		this.resumeHdzs = resumeHdzs;
	}

	public String getResumeQtzs() {
		return resumeQtzs;
	}

	public void setResumeQtzs(String resumeQtzs) {
		this.resumeQtzs = resumeQtzs;
	}

	public String getResumeItsxjn() {
		return resumeItsxjn;
	}

	public void setResumeItsxjn(String resumeItsxjn) {
		this.resumeItsxjn = resumeItsxjn;
	}

	public String getResumeQtjn() {
		return resumeQtjn;
	}

	public void setResumeQtjn(String resumeQtjn) {
		this.resumeQtjn = resumeQtjn;
	}

	public String getResumeCjgzsj() {
		return resumeCjgzsj;
	}

	public void setResumeCjgzsj(String resumeCjgzsj) {
		this.resumeCjgzsj = resumeCjgzsj;
	}

	public String getResumeNypgw1() {
		return resumeNypgw1;
	}

	public void setResumeNypgw1(String resumeNypgw1) {
		this.resumeNypgw1 = resumeNypgw1;
	}

	public String getResumeNypgw2() {
		return resumeNypgw2;
	}

	public void setResumeNypgw2(String resumeNypgw2) {
		this.resumeNypgw2 = resumeNypgw2;
	}

	public String getResumeZzmm() {
		return resumeZzmm;
	}

	public void setResumeZzmm(String resumeZzmm) {
		this.resumeZzmm = resumeZzmm;
	}

	public String getResumeZczg() {
		return resumeZczg;
	}

	public void setResumeZczg(String resumeZczg) {
		this.resumeZczg = resumeZczg;
	}

	public String getResumeBgSdh() {
		return resumeBgSdh;
	}

	public void setResumeBgSdh(String resumeBgSdh) {
		this.resumeBgSdh = resumeBgSdh;
	}

	public String getResumeXuew() {
		return resumeXuew;
	}

	public void setResumeXuew(String resumeXuew) {
		this.resumeXuew = resumeXuew;
	}

	public String getResumeJcqk() {
		return resumeJcqk;
	}

	public void setResumeJcqk(String resumeJcqk) {
		this.resumeJcqk = resumeJcqk;
	}

	public String getResumePxqk() {
		return resumePxqk;
	}

	public void setResumePxqk(String resumePxqk) {
		this.resumePxqk = resumePxqk;
	}

	public String getResumeSxhzjn() {
		return resumeSxhzjn;
	}

	public void setResumeSxhzjn(String resumeSxhzjn) {
		this.resumeSxhzjn = resumeSxhzjn;
	}

	public String getResumeXqah() {
		return resumeXqah;
	}

	public void setResumeXqah(String resumeXqah) {
		this.resumeXqah = resumeXqah;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
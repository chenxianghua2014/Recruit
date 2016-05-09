package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Resume_zs implements Serializable
{
	private static final long serialVersionUID = 1377183419886737708L;
	private String resumeZsid;
	private String resumeId;
	private String resumeHdzs;
	private String resumeQtzs;
	private Date resumeAddtime;
	private long resumeDelflag;

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

	public String getResumeZsid()
	{
		return resumeZsid;
	}

	public String ResumeZsid()
	{
		return resumeZsid;
	}

	public void setResumeZsid(String resumeZsid)
	{
		this.resumeZsid = resumeZsid;
	}

	public String getResumeId()
	{
		return resumeId;
	}

	public String ResumeId()
	{
		return resumeId;
	}

	public void setResumeId(String resumeId)
	{
		this.resumeId = resumeId;
	}

	public String getResumeHdzs()
	{
		return resumeHdzs;
	}

	public String ResumeHdzs()
	{
		return resumeHdzs;
	}

	public void setResumeHdzs(String resumeHdzs)
	{
		this.resumeHdzs = resumeHdzs;
	}

	public String getResumeQtzs()
	{
		return resumeQtzs;
	}

	public String ResumeQtzs()
	{
		return resumeQtzs;
	}

	public void setResumeQtzs(String resumeQtzs)
	{
		this.resumeQtzs = resumeQtzs;
	}
}
package com.ttgis.recruit.domain;

import java.io.Serializable;
import java.util.Date;

public class Resume_ITjn implements Serializable
{
	private static final long serialVersionUID = -2533918148176607359L;
	private String resumeITjnid;
	private String resumeId;
	private String resumeITjtjn;
	private String resumeITsxjn;
	private String resumeQtjn;
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

	public String getResumeITjnid()
	{
		return resumeITjnid;
	}

	public String ResumeITjnid()
	{
		return resumeITjnid;
	}

	public void setResumeITjnid(String resumeITjnid)
	{
		this.resumeITjnid = resumeITjnid;
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

	public String getResumeITjtjn()
	{
		return resumeITjtjn;
	}

	public String ResumeITjtjn()
	{
		return resumeITjtjn;
	}

	public void setResumeITjtjn(String resumeITjtjn)
	{
		this.resumeITjtjn = resumeITjtjn;
	}

	public String getResumeITsxjn()
	{
		return resumeITsxjn;
	}

	public String ResumeITsxjn()
	{
		return resumeITsxjn;
	}

	public void setResumeITsxjn(String resumeITsxjn)
	{
		this.resumeITsxjn = resumeITsxjn;
	}

	public String getResumeQtjn()
	{
		return resumeQtjn;
	}

	public String ResumeQtjn()
	{
		return resumeQtjn;
	}

	public void setResumeQtjn(String resumeQtjn)
	{
		this.resumeQtjn = resumeQtjn;
	}
}

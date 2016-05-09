package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_xmjyMapper;
import com.ttgis.recruit.domain.Resume_xmjy;

@Repository
@Service
public class Resume_xmjyService
{
	@Resource
	private Resume_xmjyMapper resume_xmjyMapper;

	public void updateByXmjyId(Resume_xmjy resume_xmjy)
	{
		resume_xmjyMapper.updateByXmjyId(resume_xmjy);
	}

	public void XmjyinsertSelective(Resume_xmjy resume_xmjy)
	{
		resume_xmjyMapper.XmjyinsertSelective(resume_xmjy);
	}

	public List<Resume_xmjy> getlistResumeXmjyByResumeId(String ResumeId)
	{
		return resume_xmjyMapper.getlistResumeXmjyByResumeId(ResumeId);
	}

	public void deleteByXmjyid(String resumeXmjyid)
	{
		resume_xmjyMapper.deleteByXmjyid(resumeXmjyid);
	}
}

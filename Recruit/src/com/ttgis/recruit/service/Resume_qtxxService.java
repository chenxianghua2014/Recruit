package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_qtxxMapper;
import com.ttgis.recruit.domain.Resume_qtxx;

@Repository
@Service
public class Resume_qtxxService
{
	@Resource
	private Resume_qtxxMapper resume_qtxxMapper;

	public void updateByQtxxId(Resume_qtxx resume_qtxx)
	{
		resume_qtxxMapper.updateByQtxxId(resume_qtxx);
	}

	public void QtxxinsertSelective(Resume_qtxx resume_qtxx)
	{
		resume_qtxxMapper.QtxxinsertSelective(resume_qtxx);
	}

	public List<Resume_qtxx> getlistResumeQtxxByResumeId(String ResumeId)
	{
		return resume_qtxxMapper.getlistResumeQtxxByResumeId(ResumeId);
	}

	public void deleteByQtxxid(String resumeQtxxid)
	{
		resume_qtxxMapper.deleteByQtxxid(resumeQtxxid);
	}
}

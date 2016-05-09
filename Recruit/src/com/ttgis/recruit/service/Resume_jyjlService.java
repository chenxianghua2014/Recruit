package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_jyjlMapper;
import com.ttgis.recruit.domain.Resume_jyjl;

@Repository
@Service
public class Resume_jyjlService
{
	@Resource
	private Resume_jyjlMapper resume_jyjlMapper;

	public void updateByJyjlId(Resume_jyjl resume_jyjl)
	{
		resume_jyjlMapper.updateByJyjlId(resume_jyjl);
	}

	public void JyjlinsertSelective(Resume_jyjl resume_jyjl)
	{
		resume_jyjlMapper.JyjlinsertSelective(resume_jyjl);
	}

	public List<Resume_jyjl> getlistResumeJyjlByResumeId(String ResumeId)
	{
		return resume_jyjlMapper.getlistResumeJyjlByResumeId(ResumeId);
	}

	public void deleteByJyjlid(String resumeJyjlid)
	{
		resume_jyjlMapper.deleteByJyjlid(resumeJyjlid);
	}
}

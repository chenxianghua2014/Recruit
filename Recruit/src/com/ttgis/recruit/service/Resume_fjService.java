package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_fjMapper;
import com.ttgis.recruit.domain.Resume_fj;

@Repository
@Service
public class Resume_fjService
{
	@Resource
	private Resume_fjMapper resume_fjMapper;

	public void updateByFjId(Resume_fj resume_fj)
	{
		resume_fjMapper.updateByFjId(resume_fj);
	}

	public void FjinsertSelective(Resume_fj resume_fj)
	{
		resume_fjMapper.FjinsertSelective(resume_fj);
	}

	public Resume_fj selectByPrimaryKey(String ResumeId)
	{
		return resume_fjMapper.selectByPrimaryKey(ResumeId);
	}

	public List<Resume_fj> getlistResumeFjByResumeId(String ResumeId)
	{
		return resume_fjMapper.getlistResumeFjByResumeId(ResumeId);
	}

	public void deleteByFjid(String resumeFjid)
	{
		resume_fjMapper.deleteByFjid(resumeFjid);
	}
}

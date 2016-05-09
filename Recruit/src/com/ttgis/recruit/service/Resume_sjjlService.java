package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_sjjlMapper;
import com.ttgis.recruit.domain.Resume_sjjl;

@Repository
@Service
public class Resume_sjjlService
{
	@Resource
	private Resume_sjjlMapper resume_sjjlMapper;

	public void updateBySjjlId(Resume_sjjl resume_sjjl)
	{
		resume_sjjlMapper.updateBySjjlId(resume_sjjl);
	}

	public void SjjlinsertSelective(Resume_sjjl resume_sjjl)
	{
		resume_sjjlMapper.SjjlinsertSelective(resume_sjjl);
	}

	public List<Resume_sjjl> getlistResumeSjjlByResumeId(String ResumeId)
	{
		return resume_sjjlMapper.getlistResumeSjjlByResumeId(ResumeId);
	}

	public void deleteBySjjlid(String resumeSjjlid)
	{
		resume_sjjlMapper.deleteBySjjlid(resumeSjjlid);
	}
}

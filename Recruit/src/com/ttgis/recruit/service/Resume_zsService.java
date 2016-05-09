package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_zsMapper;
import com.ttgis.recruit.domain.Resume_zs;

@Repository
@Service
public class Resume_zsService
{
	@Resource
	private Resume_zsMapper resume_zsMapper;

	public void updateByZsId(Resume_zs resume_zs)
	{
		resume_zsMapper.updateByZsId(resume_zs);
	}

	public void ZsinsertSelective(Resume_zs resume_zs)
	{
		resume_zsMapper.ZsinsertSelective(resume_zs);
	}

	public List<Resume_zs> getlistResumeZsByResumeId(String ResumeId)
	{
		return resume_zsMapper.getlistResumeZsByResumeId(ResumeId);
	}

	public void deleteByZsid(String resumeFjid)
	{
		resume_zsMapper.deleteByZsid(resumeFjid);
	}
}

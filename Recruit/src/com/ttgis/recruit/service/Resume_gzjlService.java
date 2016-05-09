package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_gzjlMapper;
import com.ttgis.recruit.domain.Resume_gzjl;

@Repository
@Service
public class Resume_gzjlService
{
	@Resource
	private Resume_gzjlMapper resume_gzjlMapper;

	public void updateByGzjlId(Resume_gzjl resume_gzjl)
	{
		resume_gzjlMapper.updateByGzjlId(resume_gzjl);
	}

	public void GzjlinsertSelective(Resume_gzjl resume_gzjl)
	{
		resume_gzjlMapper.GzjlinsertSelective(resume_gzjl);
	}

	public List<Resume_gzjl> getlistResumeGzjlByResumeId(String ResumeId)
	{
		return resume_gzjlMapper.getlistResumeGzjlByResumeId(ResumeId);
	}

	public void deleteByGzjlid(String resumeGzjlid)
	{
		resume_gzjlMapper.deleteByGzjlid(resumeGzjlid);
	}
}
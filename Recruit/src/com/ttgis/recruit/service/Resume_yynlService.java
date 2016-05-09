package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_yynlMapper;
import com.ttgis.recruit.domain.Resume_yynl;

@Repository
@Service
public class Resume_yynlService
{
	@Resource
	private Resume_yynlMapper resume_yynlMapper;

	public void updateByYynlId(Resume_yynl resume_yynl)
	{
		resume_yynlMapper.updateByYynlId(resume_yynl);
	}

	public void YynlinsertSelective(Resume_yynl resume_yynl)
	{
		resume_yynlMapper.YynlinsertSelective(resume_yynl);
	}

	public List<Resume_yynl> getlistResumeYynlByResumeId(String ResumeId)
	{
		return resume_yynlMapper.getlistResumeYynlByResumeId(ResumeId);
	}

	public void deleteByYynlid(String resumeYynlid)
	{
		resume_yynlMapper.deleteByYynlid(resumeYynlid);
	}
}

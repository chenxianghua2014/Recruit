package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_xnjlMapper;
import com.ttgis.recruit.domain.Resume_xnjl;

@Repository
@Service
public class Resume_xnjlService
{
	@Resource
	private Resume_xnjlMapper resume_xnjlMapper;

	public void updateByXnjlId(Resume_xnjl resume_xnjl)
	{
		resume_xnjlMapper.updateByXnjlId(resume_xnjl);
	}

	public void XnjlinsertSelective(Resume_xnjl resume_xnjl)
	{
		resume_xnjlMapper.XnjlinsertSelective(resume_xnjl);
	}

	public List<Resume_xnjl> getlistResumeXnjlByResumeId(String ResumeId)
	{
		return resume_xnjlMapper.getlistResumeXnjlByResumeId(ResumeId);
	}

	public void deleteByXnjlid(String resumeXnjlid)
	{
		resume_xnjlMapper.deleteByXnjlid(resumeXnjlid);
	}
}

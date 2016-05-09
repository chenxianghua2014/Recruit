package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_pxjlMapper;
import com.ttgis.recruit.domain.Resume_pxjl;

@Repository
@Service
public class Resume_pxjlService
{
	@Resource
	private Resume_pxjlMapper resume_pxjlMapper;

	public void updateByPxjlId(Resume_pxjl resume_pxjl)
	{
		resume_pxjlMapper.updateByPxjlId(resume_pxjl);
	}

	public void PxjlinsertSelective(Resume_pxjl resume_jyjl)
	{
		resume_pxjlMapper.PxjlinsertSelective(resume_jyjl);
	}

	public List<Resume_pxjl> getlistResumePxjlByResumeId(String ResumeId)
	{
		return resume_pxjlMapper.getlistResumePxjlByResumeId(ResumeId);
	}

	public void deleteByPxjlid(String resumePxjlid)
	{
		resume_pxjlMapper.deleteByPxjlid(resumePxjlid);
	}
}

package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_xnzwMapper;
import com.ttgis.recruit.domain.Resume_xnzw;

@Repository
@Service
public class Resume_xnzwService
{
	@Resource
	private Resume_xnzwMapper resume_xnzwMapper;

	public void updateByXnzwId(Resume_xnzw resume_xnzw)
	{
		resume_xnzwMapper.updateByXnzwId(resume_xnzw);
	}

	public void XnzwinsertSelective(Resume_xnzw resume_xnzw)
	{
		resume_xnzwMapper.XnzwinsertSelective(resume_xnzw);
	}

	public List<Resume_xnzw> getlistResumeXnzwByResumeId(String ResumeId)
	{
		return resume_xnzwMapper.getlistResumeXnzwByResumeId(ResumeId);
	}

	public void deleteByXnzwid(String resumeXnzwid)
	{
		resume_xnzwMapper.deleteByXnzwid(resumeXnzwid);
	}
}

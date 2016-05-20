package com.ttgis.recruit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.ResumeMapper;
import com.ttgis.recruit.domain.Resume;


@Repository
@Service
public class ResumeService
{

	@Resource
	private ResumeMapper resumeMapper;

	public void updateByPrimaryKeySelective(Resume resume)
	{
		resumeMapper.updateByPrimaryKeySelective(resume);
	}

	public void insertSelective(Resume resume)
	{
		resumeMapper.insertSelective(resume);
	}

	public void FjinsertSelective(Resume resume)
	{
		resumeMapper.FjinsertSelective(resume);
	}

	public Resume selectByPrimaryKeySelectiveById(String resumeId)
	{
		return resumeMapper.selectByPrimaryKeySelectiveById(resumeId);
	}

}

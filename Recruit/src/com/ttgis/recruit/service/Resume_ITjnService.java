package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_ITjnMapper;
import com.ttgis.recruit.domain.Resume_ITjn;


@Repository
@Service
public class Resume_ITjnService {
	@Resource
	private Resume_ITjnMapper resume_ITjnMapper;
	
	public void updateByITjnId(Resume_ITjn resume_ITjn) 
	{
		resume_ITjnMapper.updateByITjnId(resume_ITjn);
	}
	
	public void ITjninsertSelective(Resume_ITjn resume_ITjn)
	{
		resume_ITjnMapper.ITjninsertSelective(resume_ITjn);
	}
	
	public List<Resume_ITjn> getlistResumeITjnByResumeId(String ResumeId){
		return resume_ITjnMapper.getlistResumeITjnByResumeId(ResumeId);
	}

	public void deleteByITjnid(String resumeFjid)
	{
		resume_ITjnMapper.deleteByITjnid(resumeFjid);
	}
}

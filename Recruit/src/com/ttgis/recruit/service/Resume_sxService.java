package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Resume_sxMapper;
import com.ttgis.recruit.domain.Resume_sx;

@Repository
@Service
public class Resume_sxService {
	@Resource
	private Resume_sxMapper resume_sxMapper;
	
	public void updateBySxId(Resume_sx resume_sx) 
	{
		resume_sxMapper.updateBySxId(resume_sx);
	}
	
	public void SxinsertSelective(Resume_sx resume_sx)
	{
		resume_sxMapper.SxinsertSelective(resume_sx);
	}
	
	public List<Resume_sx> getlistResumeSxByResumeId(String ResumeId){
		return resume_sxMapper.getlistResumeSxByResumeId(ResumeId);
	}
	public void deleteBySxid(String resumeSxid) 
	{
		resume_sxMapper.deleteBySxid(resumeSxid);
	}
}

package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_fj;

public interface Resume_fjMapper
{
	int updateByFjId(Resume_fj record);

	int FjinsertSelective(Resume_fj record);

	public List<Resume_fj> getlistResumeFjByResumeId(String resumeId);

	int deleteByFjid(String resumeFjid);

	Resume_fj selectByPrimaryKey(String ResumeId);
}
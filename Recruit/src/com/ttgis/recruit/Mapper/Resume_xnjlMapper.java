package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_xnjl;

public interface Resume_xnjlMapper{
	int updateByXnjlId(Resume_xnjl record);
	int XnjlinsertSelective(Resume_xnjl record);
	public List<Resume_xnjl> getlistResumeXnjlByResumeId(String resumeId);
	int deleteByXnjlid(String resumeXnjlid);
}
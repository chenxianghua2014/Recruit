package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_yynl;

public interface Resume_yynlMapper{
	int updateByYynlId(Resume_yynl record);
	int YynlinsertSelective(Resume_yynl record);
	public List<Resume_yynl> getlistResumeYynlByResumeId(String resumeId);
	int deleteByYynlid(String resumeYynlid);
}
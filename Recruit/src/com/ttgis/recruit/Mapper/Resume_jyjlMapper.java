package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_jyjl;

public interface Resume_jyjlMapper
{
	int updateByJyjlId(Resume_jyjl record);

	int JyjlinsertSelective(Resume_jyjl record);

	public List<Resume_jyjl> getlistResumeJyjlByResumeId(String resumeId);

	int deleteByJyjlid(String resumeJyjlid);
}
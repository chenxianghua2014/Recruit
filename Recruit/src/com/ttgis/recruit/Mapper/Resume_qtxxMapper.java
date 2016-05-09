package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_qtxx;

public interface Resume_qtxxMapper{
	int updateByQtxxId(Resume_qtxx record);
	int QtxxinsertSelective(Resume_qtxx record);
	public List<Resume_qtxx> getlistResumeQtxxByResumeId(String resumeId);
	int deleteByQtxxid(String resumeQtxxid);
}
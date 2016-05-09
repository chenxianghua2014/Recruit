package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_gzjl;

public interface Resume_gzjlMapper{
	int updateByGzjlId(Resume_gzjl record);
	int GzjlinsertSelective(Resume_gzjl record);
	public List<Resume_gzjl> getlistResumeGzjlByResumeId(String resumeId);
	int deleteByGzjlid(String resumeGzjlid);
}
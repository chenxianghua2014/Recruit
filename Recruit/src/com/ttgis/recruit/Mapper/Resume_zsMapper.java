package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_zs;

public interface Resume_zsMapper {
	int updateByZsId(Resume_zs record);
	int ZsinsertSelective(Resume_zs record);
	public List<Resume_zs> getlistResumeZsByResumeId(String resumeId);
	int deleteByZsid(String Resume_Zsid);
}

package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_ITjn;

public interface Resume_ITjnMapper {
	int updateByITjnId(Resume_ITjn record);
	int ITjninsertSelective(Resume_ITjn record);
	public List<Resume_ITjn> getlistResumeITjnByResumeId(String resumeId);
	int deleteByITjnid(String Resume_ITjnid);
}

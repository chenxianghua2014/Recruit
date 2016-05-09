package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_pxjl;

public interface Resume_pxjlMapper{
	int updateByPxjlId(Resume_pxjl record);
	int PxjlinsertSelective(Resume_pxjl record);
	public List<Resume_pxjl> getlistResumePxjlByResumeId(String resumeId);
	int deleteByPxjlid(String resumePxjlid);
}
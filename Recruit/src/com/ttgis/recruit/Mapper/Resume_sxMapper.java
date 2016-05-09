package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_sx;

public interface Resume_sxMapper{
	int updateBySxId(Resume_sx record);
	int SxinsertSelective(Resume_sx record);
	public List<Resume_sx> getlistResumeSxByResumeId(String resumeId);
	int deleteBySxid(String resumeSxid);
}
package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_sjjl;

public interface Resume_sjjlMapper{
	int updateBySjjlId(Resume_sjjl record);
	int SjjlinsertSelective(Resume_sjjl record);
	public List<Resume_sjjl> getlistResumeSjjlByResumeId(String resumeId);
	int deleteBySjjlid(String resumeSjjlid);
}
package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_xmjy;

public interface Resume_xmjyMapper{
	int updateByXmjyId(Resume_xmjy record);
	int XmjyinsertSelective(Resume_xmjy record);
	public List<Resume_xmjy> getlistResumeXmjyByResumeId(String resumeId);
	int deleteByXmjyid(String resumeXmjyid);
}
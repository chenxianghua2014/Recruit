package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Resume_xnzw;

public interface Resume_xnzwMapper{
	int updateByXnzwId(Resume_xnzw record);
	int XnzwinsertSelective(Resume_xnzw record);
	public List<Resume_xnzw> getlistResumeXnzwByResumeId(String resumeId);
	int deleteByXnzwid(String resumeXnzwid);
}
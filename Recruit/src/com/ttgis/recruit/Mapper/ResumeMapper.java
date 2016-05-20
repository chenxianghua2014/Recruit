package com.ttgis.recruit.Mapper;

import com.ttgis.recruit.domain.Resume;

public interface ResumeMapper {

    Resume selectByPrimaryKey(String resumeId);

    int deleteByPrimaryKey(String resumeId);

    int insert(Resume record);

    int insertSelective(Resume record);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);

    int FjinsertSelective(Resume record);

    public Resume selectByPrimaryKeySelectiveById(String resumeId);

}
package com.ttgis.recruit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.CjtsglMapper;
import com.ttgis.recruit.controller.CptsglController;
import com.ttgis.recruit.domain.Cjtsgl;

import org.apache.log4j.Logger;

@Repository
@Service
public class CjtsglService
{
	static Logger log = Logger.getLogger(CptsglController.class);
	@Resource
	private CjtsglMapper cjtsgoMapper;

	public void insertCjtsgl(Cjtsgl cjtsgl)
	{
		cjtsgoMapper.insertSelective(cjtsgl);
	}

	public Cjtsgl selectByPrimaryKey(String cjtsglId)
	{
		return cjtsgoMapper.selectByPrimaryKey(cjtsglId);
	}

	public int updateByPrimaryKeySelective(Cjtsgl cjtsgl)
	{
		return cjtsgoMapper.updateByPrimaryKeySelective(cjtsgl);
	}

}

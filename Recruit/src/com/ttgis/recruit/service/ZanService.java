package com.ttgis.recruit.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ZanMapper;
import com.ttgis.recruit.domain.Zan;

@Repository
@Service
public class ZanService
{

	@Resource
	private ZanMapper zanMapper;

	@Transactional
	public int addZan(Zan zan)
	{
		return zanMapper.addZan(zan);
	}

	public int checkZan(Zan zan)
	{
		return zanMapper.checkZan(zan);
	}

}

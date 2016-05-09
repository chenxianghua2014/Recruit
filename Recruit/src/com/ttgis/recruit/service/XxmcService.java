package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.XxmcMapper;
import com.ttgis.recruit.domain.Xxmc;

@Repository
@Service
public class XxmcService {

	@Resource
	private XxmcMapper xxmcMapper;
	
	public List<Xxmc> LoadXxmc(String type) {
		return xxmcMapper.selectByType(type);
	}
}

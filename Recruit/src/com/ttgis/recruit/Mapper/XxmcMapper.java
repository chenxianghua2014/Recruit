package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Xxmc;

public interface XxmcMapper {
	List<Xxmc> selectByType(String type);
}

package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Collect_positionMapper; 
import com.ttgis.recruit.domain.Collect_position;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class CollectionService
{
	@Resource
	private Collect_positionMapper collect_positionMapper;

	public Collect_position selectByPrimaryKey(String collect_position_id)
	{
		return collect_positionMapper.selectByPrimaryKey(collect_position_id);
	}

	public void insertSelective(Collect_position collect_position)
	{
		collect_positionMapper.insertSelective(collect_position);
	}

	public int deleteByPrimaryKey(Map<String, String> params)
	{
		return collect_positionMapper.deleteByPrimaryKey(params);
	}

	public void updateByPrimaryKeySelective(Collect_position collect_position)
	{
		collect_positionMapper.updateByPrimaryKeySelective(collect_position);
	}

	public List<Collect_position> selectByWhere(Collect_position collect_position)
	{
		return collect_positionMapper.selectByWhere(collect_position);
	}
}

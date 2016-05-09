package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.MbglMapper; 
import com.ttgis.recruit.domain.Mbgl;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class MbglService
{
	@Resource
	private MbglMapper mbglMapper;

	public Mbgl selectByPrimaryKey(String mbgl_id)
	{
		return mbglMapper.selectByPrimaryKey(mbgl_id);
	}

	public List<Mbgl> selectByMbType(Map<String, String> params)
	{
		return mbglMapper.selectByMbType(params);
	}

	public void insertSelective(Mbgl mbgl)
	{
		mbglMapper.insertSelective(mbgl);
	}

	public int deleteByPrimaryKey(String mbgl_id)
	{
		return mbglMapper.deleteByPrimaryKey(mbgl_id);
	}

	public void updateByPrimaryKeySelective(Mbgl mbgl)
	{
		mbglMapper.updateByPrimaryKeySelective(mbgl);
	}

	public List<Mbgl> selectByWhere(Map<String, Object> params)
	{
		return mbglMapper.selectByWhere(params);
	}

	public int selectCount(Map<String, Object> params)
	{
		return mbglMapper.selectCount(params);
	}
}

package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.MsqMapper;
import com.ttgis.recruit.domain.Msq;
import com.ttgis.recruit.domain.QueryMsq;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class MsqService
{
	@Resource
	private MsqMapper msqMapper;

	public Msq selectByPrimaryKey(String msq_id)
	{
		return msqMapper.selectByPrimaryKey(msq_id);
	}

	public void insertSelective(Msq msq)
	{
		msqMapper.insertSelective(msq);
	}

	public int deleteByPrimaryKey(String msq_id)
	{
		return msqMapper.deleteByPrimaryKey(msq_id);
	}

	public void updateByPrimaryKeySelective(Msq msq)
	{
		msqMapper.updateByPrimaryKeySelective(msq);
	}

	public List<Msq> selectByWhere(QueryMsq p)
	{
		return msqMapper.selectByWhere(p);
	}

	public List<Msq> selectByType(Map<String, String> map)
	{
		return msqMapper.selectByType(map);
	}

	public int selectCount(QueryMsq p)
	{
		return msqMapper.selectCount(p);
	}
}

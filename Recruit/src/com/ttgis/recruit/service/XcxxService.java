package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.XcxxMapper;
import com.ttgis.recruit.domain.QueryXcxx;
import com.ttgis.recruit.domain.Xcxx;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class XcxxService
{
	@Resource
	private XcxxMapper xcxxMapper;

	public Xcxx selectByPrimaryKey(String xcxx_id)
	{
		return xcxxMapper.selectByPrimaryKey(xcxx_id);
	}

	public void insertSelective(Xcxx xcxx)
	{
		xcxxMapper.insertSelective(xcxx);
	}

	public int deleteByPrimaryKey(String xcxx_id)
	{
		return xcxxMapper.deleteByPrimaryKey(xcxx_id);
	}

	public void updateByPrimaryKeySelective(Xcxx xcxx)
	{
		xcxxMapper.updateByPrimaryKeySelective(xcxx);
	}

	public List<Xcxx> selectByWhere(QueryXcxx p)
	{
		return xcxxMapper.selectByWhere(p);
	}

	public List<Xcxx> selectByType(Map<String, String> map)
	{
		return xcxxMapper.selectByType(map);
	}

	public int selectCount(QueryXcxx p)
	{
		return xcxxMapper.selectCount(p);
	}
	
	/**
	 * 集团用户审核查询
	 * @param p
	 * @return
	 */
	public int selectCount1(QueryXcxx p){
		return xcxxMapper.selectCount1(p);
	}
	
	/**
	 * 集团用户审核查询
	 * @param p
	 * @return
	 */
	public List<Xcxx> selectByWhere1(QueryXcxx p){
		return xcxxMapper.selectByWhere1(p);
	}
}

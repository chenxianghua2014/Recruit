package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Xcxx_attachMapper; 
import com.ttgis.recruit.domain.Xcxx_attach;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class Xcxx_attachService
{
	@Resource
	private Xcxx_attachMapper attachMapper;

	public Xcxx_attach selectByPrimaryKey(String xcxx_attach_id)
	{
		return attachMapper.selectByPrimaryKey(xcxx_attach_id);
	}

	public void insertSelective(Xcxx_attach xcxx_attach)
	{
		attachMapper.insertSelective(xcxx_attach);
	}

	public int deleteByPrimaryKey(String xcxx_id)
	{
		return attachMapper.deleteByPrimaryKey(xcxx_id);
	}

	public void updateByPrimaryKeySelective(Xcxx_attach xcxx_attach)
	{
		attachMapper.updateByPrimaryKeySelective(xcxx_attach);
	}

	public List<Xcxx_attach> selectByWhere(String strWhere)
	{
		return attachMapper.selectByWhere(strWhere);
	}
}

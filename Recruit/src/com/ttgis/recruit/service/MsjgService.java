package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.Msqgl_detailedMapper; 
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.Msqgl_detailed;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class MsjgService
{
	@Resource
	private Msqgl_detailedMapper msqgl_detailedMapperled;

	public Msqgl_detailed selectByPrimaryKey(String msjg_id)
	{
		return msqgl_detailedMapperled.selectByPrimaryKey(msjg_id);
	}

	public void insertSelective(Msqgl_detailed Msqgl_detailed)
	{
		msqgl_detailedMapperled.insertSelective(Msqgl_detailed);
	}

	public int deleteByPrimaryKey(String msjg_id)
	{
		return msqgl_detailedMapperled.deleteByPrimaryKey(msjg_id);
	}

	public int deleteByForeignKey(String jtjlkId)
	{
		return msqgl_detailedMapperled.deleteByForeignKey(jtjlkId);
	}

	public void updateByPrimaryKeySelective(Msqgl_detailed Msqgl_detailed)
	{
		msqgl_detailedMapperled.updateByPrimaryKeySelective(Msqgl_detailed);
	}

	public List<Msqgl_detailed> selectByWhere(PageCondition p)
	{
		return msqgl_detailedMapperled.selectByWhere(p);
	}

	public List<Msqgl_detailed> selectByForeignKey(String jlId)
	{
		return msqgl_detailedMapperled.selectByForeignKey(jlId);
	}

	public int selectCount(PageCondition p)
	{
		return msqgl_detailedMapperled.selectCount(p);
	}

	public List<Msqgl_detailed> selectCountByForeignKey(String jlId)
	{
		return msqgl_detailedMapperled.selectCountByForeignKey(jlId);
	}

	public List<Msqgl_detailed> selectByMsg(Map<String, String> params)
	{
		return msqgl_detailedMapperled.selectByMsg(params);
	}

	public List<Msqgl_detailed> selectByJlId(String jlId)
	{
		return msqgl_detailedMapperled.selectByJlId(jlId);
	}

	public int deleteByWhere(Map<String, String> params)
	{
		return msqgl_detailedMapperled.deleteByWhere(params);
	}

	public List<Msqgl_detailed> selectMsInfoByMsq(String msqId)
	{
		return msqgl_detailedMapperled.selectMsInfoByMsq(msqId);
	}
}

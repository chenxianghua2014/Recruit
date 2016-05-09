package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.PositionMapper;
import com.ttgis.recruit.Mapper.ZpzyMapper;
import com.ttgis.recruit.Mapper.ZylbMapper;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.Position;
import com.ttgis.recruit.domain.PositionWithBLOBs;
import com.ttgis.recruit.domain.Zpzy;
import com.ttgis.recruit.domain.Zylb;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class ZwglService
{
	@Resource
	private PositionMapper PositionMapper;
	@Resource
	private ZylbMapper zylbMapper;
	@Resource
	private ZpzyMapper zpzyMapper;

	public List<Position> selectByCompany(String zzjgId)
	{
		return PositionMapper.selectByCompany(zzjgId);
	}

	public PositionWithBLOBs selectByPrimaryKey(String position_id)
	{
		return PositionMapper.selectByPrimaryKey(position_id);
	}

	public List<PositionWithBLOBs> selectByPrimaryKeys(Map<String, Object> params)
	{
		return PositionMapper.selectByPrimaryKeys(params);
	}

	public void insertSelective(PositionWithBLOBs positionWithBLOBs)
	{
		PositionMapper.insertSelective(positionWithBLOBs);
	}

	public int deleteByPrimaryKey(String position_id)
	{
		return PositionMapper.deleteByPrimaryKey(position_id);
	}

	public void updateByPrimaryKeySelective(PositionWithBLOBs positionWithBLOBs)
	{
		PositionMapper.updateByPrimaryKeySelective(positionWithBLOBs);
	}

	public List<PositionWithBLOBs> selectByWhere1(PageCondition p)
	{
		return PositionMapper.selectByWhere1(p);
	}

	public int selectCount1(PageCondition p)
	{
		return PositionMapper.selectCount1(p);
	}
	
	public List<PositionWithBLOBs> selectByWhere(PageCondition p)
	{
		return PositionMapper.selectByWhere(p);
	}

	public int selectCount(PageCondition p)
	{
		return PositionMapper.selectCount(p);
	}

	public List<Zylb> LoadZylb()
	{
		return zylbMapper.selectAll();
	}

	public List<Zylb> LoadZylbMain()
	{
		return zylbMapper.selectAllOfMain();
	}

	public List<Zpzy> LoadZpzy(String type)
	{
		return zpzyMapper.selectByType(type);
	}

}

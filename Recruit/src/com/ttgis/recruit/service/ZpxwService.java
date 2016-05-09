package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.ZpxwMapper;
import com.ttgis.recruit.domain.Zpxw;
import com.ttgis.recruit.domain.ZzjgCondition;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class ZpxwService
{
	@Resource
	private ZpxwMapper zpxwMapper;

	public Zpxw selectByPrimaryKey(String zpxw_id)
	{
		return zpxwMapper.selectByPrimaryKey(zpxw_id);
	}

	public void insertSelective(Zpxw zpxw)
	{
		zpxwMapper.insertSelective(zpxw);
	}

	public int deleteByPrimaryKey(String zpxw_id)
	{
		return zpxwMapper.deleteByPrimaryKey(zpxw_id);
	}

	public void updateByPrimaryKeySelective(Zpxw zpxw)
	{
		zpxwMapper.updateByPrimaryKeySelective(zpxw);
	}

	public List<Zpxw> selectByWhere(ZzjgCondition p)
	{
		return zpxwMapper.selectByWhere(p);
	}

	public List<Zpxw> selectZpxwByWhere(ZzjgCondition p)
	{
		return zpxwMapper.selectZpxwByWhere(p);
	}
	
	public List<Zpxw> selectInformationCenterByWhere(ZzjgCondition p)
	{
		return zpxwMapper.selectInformationCenterByWhere(p);
	}

	public int selectCount(ZzjgCondition p)
	{
		return zpxwMapper.selectCount(p);
	}

	public Zpxw selectZprc(String strZzjgId)
	{
		return zpxwMapper.selectZprc(strZzjgId);
	}

	public List<Zpxw> select()
	{
		return zpxwMapper.select();
	}
	
	public void setTop(Zpxw zpxw){
		zpxwMapper.setTop(zpxw);
	}
	
	public void updateTopTime(Zpxw zpxw){
		zpxwMapper.updateTopTime(zpxw);
	}
	
	/**
	 * 置顶新闻不分页
	 * @param p
	 * @return
	 */
	public List<Zpxw> selectZpxwByWhereTop(ZzjgCondition p){
		return zpxwMapper.selectZpxwByWhereTop(p);
	}
	
	/**
	 * 非置顶新闻数
	 * @param p
	 * @return
	 */
	public int selectCountNoTop(ZzjgCondition p){
		return zpxwMapper.selectCountNoTop(p);
	}
	
	/**
	 * 非置顶新闻查询
	 * @param p
	 * @return
	 */
	public List<Zpxw> selectZpxwByWhereNoTop(ZzjgCondition p){
		return zpxwMapper.selectZpxwByWhereNoTop(p);
	}

}

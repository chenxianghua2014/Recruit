package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.tkglFxtMapper;
import com.ttgis.recruit.domain.QueryTkglFxt;
import com.ttgis.recruit.domain.tkglFxt;

import org.apache.log4j.Logger;

@Repository
@Service
public class TkglFxtService
{
	static Logger log = Logger.getLogger(TkglFxtService.class);

	@Resource
	private tkglFxtMapper tkglFxtMapper;

	/**
	 * 添加标签
	 * 
	 * @param ksxcgl
	 */
	@Transactional
	public void insertkgl(tkglFxt tkgl)
	{
		log.info("添加标签:" + tkgl.getId());
		tkglFxtMapper.insert(tkgl);

	}

	public List<tkglFxt> selectByWhere(QueryTkglFxt qt)
	{
		return tkglFxtMapper.selectByWhere(qt);
	}

	public int selectCount(QueryTkglFxt qt)
	{
		return tkglFxtMapper.selectCount(qt);
	}

	public tkglFxt selectByPrimaryKey(String id)
	{
		return tkglFxtMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void updateByPrimaryKeySelective(tkglFxt tkgl)
	{
		log.info("更新宣传信息ID" + tkgl.getId());
		tkglFxtMapper.updateByPrimaryKeySelective(tkgl);
	}

	@Transactional
	public int deleteByPrimaryKey(String id)
	{
		log.info("删除宣传信息ID" + id);
		return tkglFxtMapper.deleteByPrimaryKey(id);
	}

	public List<tkglFxt> queryTkglFxt(String s)
	{
		return tkglFxtMapper.queryTkglFxt(s);
	}
}

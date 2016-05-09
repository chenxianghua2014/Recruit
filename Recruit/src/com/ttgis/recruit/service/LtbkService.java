package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.LtbkMapper;
import com.ttgis.recruit.domain.Ltbk;
import com.ttgis.recruit.domain.QueryLtbk;

@Repository
@Service
public class LtbkService
{

	@Resource
	private LtbkMapper ltbkMapper;

	/**
	 * 添加标签
	 * 
	 * @param Ltbk
	 */
	@Transactional
	public void insertLtbk(Ltbk ltbk)
	{
		ltbkMapper.insert(ltbk);

	}

	/**
	 * 查询一条数据
	 * 
	 * @param LtbkId
	 * @return
	 */
	public Ltbk selectByPrimaryKey(String LtbkId)
	{
		return ltbkMapper.selectByPrimaryKey(LtbkId);
	}

	/**
	 * 修改标签信息
	 * 
	 * @param Ltbk
	 */
	@Transactional
	public void updateByPrimaryKey(Ltbk Ltbk)
	{
		ltbkMapper.updateByPrimaryKey(Ltbk);
	}

	/**
	 * 删除标签
	 * 
	 * @param LtbkId
	 */
	@Transactional
	public void delLtbk(String LtbkId)
	{
		ltbkMapper.deleteByPrimaryKey(LtbkId);
	}

	@Transactional
	public void delLtbkByTlqId(String tlqId)
	{
		ltbkMapper.delLtbkByTlqId(tlqId);
	}

	public List<Ltbk> selectByWhere(QueryLtbk ql)
	{
		return ltbkMapper.selectByWhere(ql);
	}

	public int selectCount(QueryLtbk ql)
	{
		return ltbkMapper.selectCount(ql);
	}

	public List<Ltbk> queryLtbk()
	{
		return ltbkMapper.queryLtbk();
	}

	public List<Ltbk> queryLtbkByTlqId(String tlqId)
	{
		return ltbkMapper.queryLtbkByTlqId(tlqId);
	}

	@Transactional
	public int updateLtbkSJ(String ltbkId)
	{
		return ltbkMapper.updateLtbkSJ(ltbkId);
	}
}

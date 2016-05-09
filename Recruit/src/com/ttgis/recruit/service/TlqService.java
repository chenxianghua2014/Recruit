package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.TlqMapper;
import com.ttgis.recruit.domain.QueryTlq;
import com.ttgis.recruit.domain.Tlq;

@Repository
@Service
public class TlqService
{
	@Resource
	private TlqMapper tlqMapper;

	public List<Tlq> selectByWhere(QueryTlq ql)
	{
		return tlqMapper.selectByWhere(ql);
	}

	public int selectCount(QueryTlq ql)
	{
		return tlqMapper.selectCount(ql);
	}

	/**
	 * 添加标签
	 * 
	 * @param Tlq
	 */
	@Transactional
	public void insertTlq(Tlq Tlq)
	{
		tlqMapper.insert(Tlq);

	}

	/**
	 * 查询一条数据
	 * 
	 * @param TlqId
	 * @return
	 */
	public Tlq selectByPrimaryKey(String TlqId)
	{
		return tlqMapper.selectByPrimaryKey(TlqId);
	}

	/**
	 * 修改标签信息
	 * 
	 * @param Tlq
	 */
	@Transactional
	public void updateByPrimaryKey(Tlq Tlq)
	{
		tlqMapper.updateByPrimaryKey(Tlq);
	}

	/**
	 * 删除标签
	 * 
	 * @param TlqId
	 */
	@Transactional
	public void delTlq(String TlqId)
	{
		tlqMapper.deleteByPrimaryKey(TlqId);
	}

	public List<Tlq> queryTlq()
	{
		return tlqMapper.queryTlq();
	}

}

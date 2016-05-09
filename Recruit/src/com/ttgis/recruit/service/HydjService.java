package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.HydjMapper;
import com.ttgis.recruit.domain.Hycpdj;
import com.ttgis.recruit.domain.Hydj;
import com.ttgis.recruit.domain.QueryHydj;

@Repository
@Service
public class HydjService
{

	@Resource
	private HydjMapper hydjMapper;

	public List<Hydj> selectByWhere(QueryHydj qh)
	{
		return hydjMapper.selectByWhere(qh);
	}

	public int selectCount(QueryHydj qh)
	{
		return hydjMapper.selectCount(qh);
	}

	public Hydj selectByPrimaryKey(String hydjId)
	{
		return hydjMapper.selectByPrimaryKey(hydjId);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Hydj hydj)
	{
		hydjMapper.updateByPrimaryKeySelective(hydj);
	}

	@Transactional
	public void insertSelective(Hydj hydj)
	{
		hydjMapper.insertSelective(hydj);
	}

	@Transactional
	public void delHydj(String hydjId)
	{
		hydjMapper.deleteByPrimaryKey(hydjId);
	}

	public List<Hydj> queryHydj()
	{
		return hydjMapper.queryHydj();
	}

	public Hycpdj queryHycpdj()
	{
		return hydjMapper.queryHycpdj();
	}

	@Transactional
	public void UpdHycpdj(String hycpdj)
	{
		hydjMapper.UpdHycpdj(hycpdj);
	}
}

package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.XqqzMapper;
import com.ttgis.recruit.domain.QueryXqqz;
import com.ttgis.recruit.domain.Xqqz;

@Repository
@Service
public class XqqzService
{
	@Resource
	private XqqzMapper xqqzMapper;

	public List<Xqqz> selectByWhere(QueryXqqz qx)
	{
		return xqqzMapper.selectByWhere(qx);
	}

	public int selectCount(QueryXqqz qx)
	{
		return xqqzMapper.selectCount(qx);
	}

	public Xqqz selectByPrimaryKey(String xqqzId)
	{
		return xqqzMapper.selectByPrimaryKey(xqqzId);
	}

	public void updateByPrimaryKeySelective(Xqqz xqqz)
	{
		xqqzMapper.updateByPrimaryKeySelective(xqqz);
	}

	public void insertSelective(Xqqz xqqz)
	{
		xqqzMapper.insertSelective(xqqz);
	}

	@Transactional
	public void delXqqz(String xqqzId)
	{
		xqqzMapper.deleteByPrimaryKey(xqqzId);
	}

	public List<Xqqz> queryXqqzMark(String xqqzFbr)
	{
		return xqqzMapper.queryXqqzMark(xqqzFbr);
	}

	// ------------------------------------------------------------------------------------------
	public List<Xqqz> qtselectByWhere(QueryXqqz p)
	{
		return xqqzMapper.qtselectByWhere(p);
	}

	public int qtselectCount(QueryXqqz p)
	{
		return xqqzMapper.qtselectCount(p);
	}

	public List<Xqqz> queryxqqzList()
	{
		return xqqzMapper.queryxqqzList();
	}
}

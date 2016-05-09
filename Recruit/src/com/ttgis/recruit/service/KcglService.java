package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.KcglMapper;
import com.ttgis.recruit.domain.Kcgl;
import com.ttgis.recruit.domain.QueryKcgl;

@Repository
@Service
public class KcglService
{
	@Resource
	private KcglMapper kcglMapper;

	public List<Kcgl> queryKcgl()
	{
		return kcglMapper.queryKcgl();
	}

	public List<Kcgl> selectByWhere(QueryKcgl qk)
	{
		return kcglMapper.selectByWhere(qk);
	}

	public int selectCount(QueryKcgl qk)
	{
		return kcglMapper.selectCount(qk);
	}

	public Kcgl selectByPrimaryKey(String kcglId)
	{
		return kcglMapper.selectByPrimaryKey(kcglId);
	}

	@Transactional
	public void insertSelective(Kcgl kcgl)
	{
		kcglMapper.insertSelective(kcgl);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Kcgl kcgl)
	{
		kcglMapper.updateByPrimaryKeySelective(kcgl);
	}

	@Transactional
	public void delKcgl(String kcglId)
	{
		kcglMapper.deleteByPrimaryKey(kcglId);
	}

	public int queryKcrlByKcglName(String kcglName)
	{
		return kcglMapper.queryKcrlByKcglName(kcglName);
	}
}

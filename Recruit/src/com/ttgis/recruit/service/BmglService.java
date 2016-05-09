package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.BmglMapper;
import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.QueryBmgl;

@Repository
@Service
public class BmglService
{
	@Resource
	private BmglMapper bmglMapper;

	public List<Bmgl> queryBmgl()
	{
		return bmglMapper.queryBmgl();
	}

	public List<Bmgl> selectIsExist(Bmgl b)
	{
		return bmglMapper.selectIsExist(b);
	}

	public Bmgl queryBmglById(String bmglId)
	{
		return bmglMapper.selectByPrimaryKey(bmglId);
	}

	public List<Bmgl> selectByWhere(QueryBmgl qb)
	{
		return bmglMapper.selectByWhere(qb);
	}

	public int selectCount(QueryBmgl qb)
	{
		return bmglMapper.selectCount(qb);
	}

	@Transactional
	public void insertSelective(Bmgl bmgl)
	{
		bmglMapper.insertSelective(bmgl);
	}

	@Transactional
	public void delBmgl(String bmglId)
	{
		bmglMapper.deleteByPrimaryKey(bmglId);
	}

	@Transactional
	public void deleteByWhere(Map<String, String> p)
	{
		bmglMapper.deleteByWhere(p);
	}

	public List<Bmgl> CheckCpglLogin(Bmgl bmgl)
	{
		return bmglMapper.CheckCpglLogin(bmgl);
	}

	public void updKscjById(Bmgl bmgl)
	{
		bmglMapper.updateByPrimaryKeySelective(bmgl);
	}

	@Transactional
	public void delByKcglId(String kcglId)
	{
		bmglMapper.delByKcglId(kcglId);
	}

	public void delByJtjlkId(String jtjlkId)
	{
		bmglMapper.delByJtjlkId(jtjlkId);
	}

	public List<Bmgl> queryBmglBySfzh(String bmglSfzh)
	{
		return bmglMapper.queryBmglBySfzh(bmglSfzh);
	}
	
	public List<Bmgl> queryAllBmgl(){
		return bmglMapper.queryAllBmgl();
	}

}

package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.JkMapper;
import com.ttgis.recruit.Mapper.JkglFxtMapper;
import com.ttgis.recruit.Mapper.JkglMapper;
import com.ttgis.recruit.domain.Jk;
import com.ttgis.recruit.domain.JkglFxtWithBLOBs;
import com.ttgis.recruit.domain.JkglWithBLOBs;
import com.ttgis.recruit.domain.QueryJk;

@Repository
@Service
public class JkService
{

	@Resource
	private JkMapper jkMapper;
	@Resource
	private JkglMapper jkglMapper;
	@Resource
	private JkglFxtMapper jkglFxtMapper;

	public List<Jk> selectByWhere(QueryJk qj)
	{
		return jkMapper.selectByWhere(qj);
	}

	public int selectCount(QueryJk qj)
	{
		return jkMapper.selectCount(qj);
	}

	public Jk selectByPrimaryKey(String jkId)
	{
		return jkMapper.selectByPrimaryKey(jkId);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Jk jk)
	{
		jkMapper.updateByPrimaryKeySelective(jk);
	}

	@Transactional
	public void insertSelective(Jk jk)
	{
		jkMapper.insertSelective(jk);
	}

	@Transactional
	public void deleteByPrimaryKey(String jkId)
	{
		jkMapper.deleteByPrimaryKey(jkId);
	}

	@Transactional
	public void insertSelectiveJkgl(JkglWithBLOBs jkgl)
	{
		jkglMapper.insertSelective(jkgl);
	}

	public int queryJkglSJbh(String jkglSjbh)
	{
		return jkglMapper.queryJkglSJbh(jkglSjbh);
	}

	public List<JkglWithBLOBs> queryJkglBySJbh(Map<String, String> param)
	{
		return jkglMapper.queryJkglBySJbh(param);
	}

	public List<JkglWithBLOBs> queryRandomJk(String jkglStlx)
	{
		return jkglMapper.queryRandomJk(jkglStlx);
	}

	@Transactional
	public void insertSelective(JkglFxtWithBLOBs jkglFxt)
	{
		jkglFxtMapper.insertSelective(jkglFxt);
	}

	public List<JkglFxtWithBLOBs> queryJkglFxt(Map<String, String> param)
	{
		return jkglFxtMapper.queryJkglFxt(param);
	}

	public List<JkglFxtWithBLOBs> jkqueryJkglFxt(String stlx)
	{
		return jkglFxtMapper.jkqueryJkglFxt(stlx);
	}

	@Transactional
	public void deleteByJkId(String jkglSjbh)
	{
		jkglMapper.deleteByJkId(jkglSjbh);
	}

	@Transactional
	public void deleteJkglFxtByJkId(String sjbh)
	{
		jkglFxtMapper.deleteJkglFxtByJkId(sjbh);
	}
}

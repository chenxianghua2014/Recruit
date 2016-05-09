package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.BbsMapper;
import com.ttgis.recruit.domain.Bbs;
import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.QueryBbs;

@Repository
@Service
public class BbsService
{

	@Resource
	private BbsMapper bbsMapper;

	/**
	 * 添加BBS
	 * 
	 * @param bbs
	 */
	@Transactional
	public void insertBbs(Bbs bbs)
	{
		bbsMapper.insert(bbs);
	}

	public List<Bbs> selectByWhere(QueryBbs qb)
	{
		return bbsMapper.selectByWhere(qb);
	}

	public int selectCount(QueryBbs qb)
	{
		return bbsMapper.selectCount(qb);
	}

	/**
	 * 删除标签
	 * 
	 * @param bbsId
	 */
	@Transactional
	public void delBbs(String bbsId)
	{
		bbsMapper.deleteByPrimaryKey(bbsId);
	}

	public List<Bbs> queryBbsMark(String bbsFbr)
	{
		return bbsMapper.queryBbsMark(bbsFbr);
	}

	public List<Bbs> queryBbs()
	{
		return bbsMapper.queryBbs();
	}

	public List<Bbs> queryBbsByLtbq(Ltbq ltbq)
	{
		return bbsMapper.queryBbsByLtbq(ltbq);
	}

	public Bbs getBbsById(String bbsId)
	{
		return bbsMapper.selectByPrimaryKey(bbsId);
	}

	public List<Bbs> selectqtBbsByWhere(QueryBbs p)
	{
		return bbsMapper.selectqtBbsByWhere(p);
	}

	public int selectqtBbsCount(QueryBbs p)
	{
		return bbsMapper.selectqtBbsCount(p);
	}

	public List<Bbs> selectByLtbqWhere(QueryBbs qb)
	{
		return bbsMapper.selectByLtbqWhere(qb);
	}

	public int selectByLtbqCount(QueryBbs qb)
	{
		return bbsMapper.selectByLtbqCount(qb);
	}

	public Bbs queryByBbsId(String bbsId)
	{
		return bbsMapper.selectByPrimaryKey(bbsId);
	}

	public List<Bbs> queryBbsByLtbkId(String ltbkId)
	{
		return bbsMapper.queryBbsByLtbkId(ltbkId);
	}

	public List<Bbs> BbsByLtbkIdData(QueryBbs qb)
	{
		return bbsMapper.BbsByLtbkIdData(qb);
	}

	public int BbsByLtbkIdDataCount(QueryBbs qb)
	{
		return bbsMapper.BbsByLtbkIdDataCount(qb);
	}

	public List<Bbs> queryLtbkByLtbkId(String ltbkId)
	{
		return bbsMapper.queryLtbkByLtbkId(ltbkId);
	}

	@Transactional
	public int updZan(Bbs bbs)
	{
		return bbsMapper.updZan(bbs);
	}

	public List<Bbs> htBbsData(QueryBbs qb)
	{
		return bbsMapper.htBbsData(qb);
	}

	public int htBbsCount(QueryBbs qb)
	{
		return bbsMapper.htBbsCount(qb);
	}

	public Bbs selectByPrimaryKey(String bbsId)
	{
		return bbsMapper.selectByPrimaryKey(bbsId);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Bbs bbs)
	{
		bbsMapper.updateByPrimaryKeySelective(bbs);
	}
}

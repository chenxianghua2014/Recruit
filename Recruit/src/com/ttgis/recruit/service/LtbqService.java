package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.LtbqMapper; 
import com.ttgis.recruit.domain.Ltbq;
import com.ttgis.recruit.domain.LtbqArticle;
import com.ttgis.recruit.domain.LtbqBbs;
import com.ttgis.recruit.domain.LtbqXqqz;
import com.ttgis.recruit.domain.QueryLtbq;

@Repository
@Service
public class LtbqService
{

	@Resource
	private LtbqMapper ltbqMapper;

	public List<Ltbq> queryLtbq()
	{
		return ltbqMapper.queryLtbq();
	}

	public List<Ltbq> checkLtbq()
	{
		return ltbqMapper.checkLtbq();
	}

	public List<Ltbq> checkLtbqArticle()
	{
		return ltbqMapper.checkLtbqArticle();
	}

	public List<Ltbq> checkLtbqXqqz()
	{
		return ltbqMapper.checkLtbqXqqz();
	}

	public List<Ltbq> queryLtbqByBW()
	{
		return ltbqMapper.queryLtbqByBW();
	}

	public List<Ltbq> queryLtbqByQZ()
	{
		return ltbqMapper.queryLtbqByQZ();
	}

	/**
	 * 添加标签
	 * 
	 * @param Ltbq
	 */
	@Transactional
	public void insertLtbq(Ltbq ltbq)
	{
		ltbqMapper.insert(ltbq);
	}

	public Ltbq getLtbqByltbqName(String ltbqName)
	{
		return ltbqMapper.getLtbqByltbqName(ltbqName);
	}

	/**
	 * 删除标签实体
	 * 
	 * @param account_id
	 * @return
	 */
	@Transactional
	public void updateLtbqDel(String ltbqName)
	{
		ltbqMapper.deleteByltbqName(ltbqName);
	}

	/**
	 * 查询一条数据
	 * 
	 * @param ltbqId
	 * @return
	 */
	public Ltbq selectByPrimaryKey(String ltbqId)
	{
		return ltbqMapper.selectByPrimaryKey(ltbqId);
	}

	/**
	 * 修改标签信息
	 * 
	 * @param ltbq
	 */
	@Transactional
	public void updateByPrimaryKey(Ltbq ltbq)
	{
		ltbqMapper.updateByPrimaryKey(ltbq);
	}

	/**
	 * 删除标签
	 * 
	 * @param ltbqId
	 */
	@Transactional
	public void delLtbq(String ltbqId)
	{
		ltbqMapper.deleteByPrimaryKey(ltbqId);
	}

	@Transactional
	public void delLtbqBbs(String ltbqId)
	{
		ltbqMapper.delLtbqBbs(ltbqId);
	}

	@Transactional
	public void delLtbqArticle(String ltbqId)
	{
		ltbqMapper.delLtbqArticle(ltbqId);
	}

	@Transactional
	public void delLtbqXqqz(String ltbqId)
	{
		ltbqMapper.delLtbqXqqz(ltbqId);
	}

	public List<Ltbq> selectByWhere(QueryLtbq ql)
	{
		return ltbqMapper.selectByWhere(ql);
	}

	public int selectCount(QueryLtbq ql)
	{
		return ltbqMapper.selectCount(ql);
	}

	public int addLtbqBbs(LtbqBbs lb)
	{
		return ltbqMapper.addLtbqBbs(lb);
	}

	@Transactional
	public int addLtbqArticle(LtbqArticle la)
	{
		return ltbqMapper.addLtbqArticle(la);
	}

	@Transactional
	public int addLtbqXqqz(LtbqXqqz lx)
	{
		return ltbqMapper.addLtbqXqqz(lx);
	}

}

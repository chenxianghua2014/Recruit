package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ZjgzMapper;
import com.ttgis.recruit.controller.ZjgzController;
import com.ttgis.recruit.domain.QueryZjgz;
import com.ttgis.recruit.domain.Zjgz;

import org.apache.log4j.Logger;

@Repository
@Service
public class ZjgzService
{
	static Logger log = Logger.getLogger(ZjgzController.class);

	@Resource
	private ZjgzMapper zjgzMapper;

	public List<Zjgz> queryZjgz()
	{
		log.info("查询标签数据");
		return zjgzMapper.quary();
	}

	/**
	 * 添加标签
	 * 
	 * @param
	 */
	@Transactional
	public void insertzjgz(Zjgz zjgz)
	{
		log.info("添加标签:" + zjgz.getZjgzId());
		zjgzMapper.insert(zjgz);

	}

	public Zjgz selectByPrimaryKey(String zjgz_id)
	{
		return zjgzMapper.selectByPrimaryKey(zjgz_id);
	}

	public List<Zjgz> selectByWhere(QueryZjgz qz)
	{
		return zjgzMapper.selectByWhere(qz);
	}

	public int selectCount(QueryZjgz qz)
	{
		return zjgzMapper.selectCount(qz);
	}

	public void updateByPrimaryKeySelective(Zjgz zjgz)
	{
		log.info("更新宣传信息ID" + zjgz.getZjgzId());
		zjgzMapper.updateByPrimaryKeySelective(zjgz);
	}

	public int deleteByPrimaryKey(String zjgzid)
	{
		log.info("删除宣传信息ID" + zjgzid);
		return zjgzMapper.deleteByPrimaryKey(zjgzid);
	}
}
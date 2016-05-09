package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.XqqzLxglMapper;
import com.ttgis.recruit.domain.QueryXqqzLxgl;
import com.ttgis.recruit.domain.XqqzLxgl;

/**
 * 
 * @类名： com.ttgis.recruit.controller。ArticleController
 * @创建人： 范井龙
 * @日期：
 * @描述： 兴趣圈子类型管理实现类
 * @版本号：
 */
@Repository
@Service
public class XqqzLxglService
{
	@Resource
	private XqqzLxglMapper xqqzLxglMapper;

	public List<XqqzLxgl> XqqzLxglData(QueryXqqzLxgl qxl)
	{
		return xqqzLxglMapper.XqqzLxglData(qxl);
	}

	public int XqqzLxglCount(QueryXqqzLxgl qxl)
	{
		return xqqzLxglMapper.XqqzLxglCount(qxl);
	}

	public XqqzLxgl selectByPrimaryKey(String xqqzlxglId)
	{
		return xqqzLxglMapper.selectByPrimaryKey(xqqzlxglId);
	}

	@Transactional
	public void updateByPrimaryKeySelective(XqqzLxgl XqqzLxgl)
	{
		xqqzLxglMapper.updateByPrimaryKeySelective(XqqzLxgl);
	}

	@Transactional
	public void insertSelective(XqqzLxgl XqqzLxgl)
	{
		xqqzLxglMapper.insertSelective(XqqzLxgl);
	}

	@Transactional
	public void deleteByPrimaryKey(String xqqzlxglId)
	{
		xqqzLxglMapper.deleteByPrimaryKey(xqqzlxglId);
	}

	public List<XqqzLxgl> queryXqqzLxgl()
	{
		return xqqzLxglMapper.queryXqqzLxgl();
	}

	public int XqqzCount(String xqqzLxglId)
	{
		return xqqzLxglMapper.XqqzCount(xqqzLxglId);
	}

}

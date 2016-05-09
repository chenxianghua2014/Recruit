/**
 * 
 */
package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.LinkMapper;
import com.ttgis.recruit.domain.Link;

/**
 * @author DZX
 * 
 */
@Repository
@Service
public class LinkService
{

	@Resource
	private LinkMapper linkMapper;

	/**
	 * 通过主键删除友情链接实体
	 * 
	 * @mbggenerated
	 */
	public int deleteByPrimaryKey(String linkId)
	{
		return linkMapper.deleteByPrimaryKey(linkId);
	}

	/**
	 * 插入友情链接实体
	 * 
	 * @mbggenerated
	 */
	public int insert(Link record)
	{
		return linkMapper.insert(record);
	}

	/**
	 * 
	 * @mbggenerated
	 */
	public int insertSelective(Link record)
	{
		return linkMapper.insertSelective(record);
	}

	/**
	 * 
	 * @mbggenerated
	 */
	public Link selectByPrimaryKey(String linkId)
	{
		return linkMapper.selectByPrimaryKey(linkId);
	}

	/**
	 * 更新友情链接实体
	 * 
	 * @mbggenerated
	 */
	public int updateByPrimaryKeySelective(Link record)
	{
		return linkMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 通过主键更新友情链接实体
	 * 
	 * @mbggenerated
	 */
	public int updateByPrimaryKey(Link record)
	{
		return linkMapper.updateByPrimaryKey(record);
	}

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<Link> selectAll()
	{
		return linkMapper.selectAll();
	}

}

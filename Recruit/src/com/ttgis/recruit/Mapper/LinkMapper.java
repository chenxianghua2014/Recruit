package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.Link;

public interface LinkMapper {
    /**
     * 通过主键删除友情链接实体
     * @mbggenerated
     */
    int deleteByPrimaryKey(String linkId);

    /**
     * 插入友情链接实体
     * @mbggenerated
     */
    int insert(Link record);

    /**
     * 
     * @mbggenerated
     */
    int insertSelective(Link record);

    /**
     *
     * @mbggenerated
     */
    Link selectByPrimaryKey(String linkId);

    /**
     * 更新友情链接实体
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Link record);

    /**
     * 通过主键更新友情链接实体
     * @mbggenerated
     */
    int updateByPrimaryKey(Link record);
    
    /**
     * 查询所有记录
     * @return
     */
    List<Link> selectAll();
}
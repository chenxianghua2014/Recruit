package com.ttgis.recruit.Mapper;

import com.ttgis.recruit.domain.Sysmsg;

public interface SysmsgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String sysmsgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    int insert(Sysmsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    int insertSelective(Sysmsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    Sysmsg selectByPrimaryKey(String sysmsgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Sysmsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYSMSG
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Sysmsg record);
}
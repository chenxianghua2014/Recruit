package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.QueryTlq;
import com.ttgis.recruit.domain.Tlq;

public interface TlqMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String tlqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    int insert(Tlq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    int insertSelective(Tlq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    Tlq selectByPrimaryKey(String tlqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Tlq record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TLQ
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Tlq record);
    
    List<Tlq> selectByWhere(QueryTlq ql);

  	int selectCount(QueryTlq ql);
  	
  	List<Tlq> queryTlq();
}
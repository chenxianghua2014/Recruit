package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.QueryXqqzReview;
import com.ttgis.recruit.domain.XqqzReview;

public interface XqqzReviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String xqqzReviewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    int insert(XqqzReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    int insertSelective(XqqzReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    XqqzReview selectByPrimaryKey(String xqqzReviewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(XqqzReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XQQZ_REVIEW
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(XqqzReview record);
    
    List<XqqzReview> selectByWhere(QueryXqqzReview qxr);
    int selectCount(QueryXqqzReview qxr);
}
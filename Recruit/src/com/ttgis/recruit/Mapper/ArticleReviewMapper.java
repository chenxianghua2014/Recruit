package com.ttgis.recruit.Mapper;

import java.util.List;

import com.ttgis.recruit.domain.ArticleReview;
import com.ttgis.recruit.domain.QueryArticleReview;

public interface ArticleReviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String articleReviewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int insert(ArticleReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int insertSelective(ArticleReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    ArticleReview selectByPrimaryKey(String articleReviewId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ArticleReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ArticleReview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ARTICLE_REVIEW
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ArticleReview record);
    
    
    List<ArticleReview> selectByWhere(QueryArticleReview qar);
    int selectCount(QueryArticleReview qar);
}
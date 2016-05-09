package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ArticleReviewMapper;
import com.ttgis.recruit.domain.ArticleReview;
import com.ttgis.recruit.domain.QueryArticleReview;

/**
 * 
 * @author 范井龙
 * 
 */
@Repository
@Service
public class ArticleReviewService
{
	@Resource
	private ArticleReviewMapper articleReviewMapper;

	public List<ArticleReview> selectByWhere(QueryArticleReview qar)
	{
		return articleReviewMapper.selectByWhere(qar);
	}

	public int selectCount(QueryArticleReview qar)
	{
		return articleReviewMapper.selectCount(qar);
	}

	@Transactional
	public void saveArticleReview(ArticleReview articleReview)
	{
		articleReviewMapper.insertSelective(articleReview);
	}

	@Transactional
	public void delArticleReview(String articleReviewId)
	{
		articleReviewMapper.deleteByPrimaryKey(articleReviewId);
	}

	@Transactional
	public void insertSelective(ArticleReview articleReview)
	{
		articleReviewMapper.insertSelective(articleReview);
	}

}

package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ReviewMapper;
import com.ttgis.recruit.domain.QueryReview;
import com.ttgis.recruit.domain.Review;

@Repository
@Service
public class ReviewService
{

	@Resource
	private ReviewMapper reviewMapper;

	/**
	 * 添加review
	 * 
	 * @param bbs
	 */
	@Transactional
	public void insertSelective(Review review)
	{
		reviewMapper.insertSelective(review);
	}

	/**
	 * 删除标签
	 * 
	 * @param reviewId
	 */
	@Transactional
	public void delReview(String reviewId)
	{
		reviewMapper.deleteByPrimaryKey(reviewId);
	}

	@Transactional
	public void delReviewByBbsId(String bbsId)
	{
		reviewMapper.delReviewByBbsId(bbsId);
	}

	/**
	 * 根据BBSID 查询评论
	 * 
	 * @param
	 */
	public List<Review> queryByBbsId(String bbsId)
	{
		return reviewMapper.selectByBbsId(bbsId);
	}

	public List<Review> queryReviewMark(String reviewFbr)
	{
		return reviewMapper.queryReviewMark(reviewFbr);
	}

	public List<Review> queryReview()
	{
		return reviewMapper.queryReview();
	}

	public List<Review> selectByWhere(QueryReview qr)
	{
		return reviewMapper.selectByWhere(qr);
	}

	public int selectCount(QueryReview qr)
	{
		return reviewMapper.selectCount(qr);
	}

	public List<Review> queryByZzjgDwmc(String zzjgDwmc)
	{
		return reviewMapper.queryByZzjgDwmc(zzjgDwmc);
	}

	public List<Review> queryreviewList(String bbsId)
	{
		return reviewMapper.queryreviewList(bbsId);
	}

	public List<Review> htBbsReviewData(QueryReview qr)
	{
		return reviewMapper.htBbsReviewData(qr);
	}

	public int htBbsReviewCount(QueryReview qr)
	{
		return reviewMapper.htBbsReviewCount(qr);
	}

	@Transactional
	public void delhtBbsReview(String reviewId)
	{
		reviewMapper.deleteByPrimaryKey(reviewId);
	}

	public Review selectByPrimaryKey(String reviewId)
	{
		return reviewMapper.selectByPrimaryKey(reviewId);
	}
}

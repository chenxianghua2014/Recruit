package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.XqqzReviewMapper;
import com.ttgis.recruit.domain.QueryXqqzReview;
import com.ttgis.recruit.domain.XqqzReview;

@Repository
@Service
public class XqqzReviewService
{
	@Resource
	private XqqzReviewMapper xqqzReviewMapper;

	public List<XqqzReview> selectByWhere(QueryXqqzReview qxr)
	{
		return xqqzReviewMapper.selectByWhere(qxr);
	}

	public int selectCount(QueryXqqzReview qxr)
	{
		return xqqzReviewMapper.selectCount(qxr);
	}

	@Transactional
	public void saveXqqzReview(XqqzReview xqqzReview)
	{
		xqqzReviewMapper.insertSelective(xqqzReview);
	}

	@Transactional
	public void delXqqzReview(String xqqzReviewId)
	{
		xqqzReviewMapper.deleteByPrimaryKey(xqqzReviewId);
	}

	@Transactional
	public void insertSelective(XqqzReview xqqzReview)
	{
		xqqzReviewMapper.insertSelective(xqqzReview);
	}

}

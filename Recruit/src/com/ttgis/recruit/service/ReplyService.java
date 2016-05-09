package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ReplyMapper;
import com.ttgis.recruit.domain.QueryReply;
import com.ttgis.recruit.domain.Reply;

@Repository
@Service
public class ReplyService
{
	@Resource
	private ReplyMapper replyMapper;

	public int htBbsReviewReplyCount(QueryReply qr)
	{
		return replyMapper.htBbsReviewReplyCount(qr);
	}

	public List<Reply> htBbsReviewReplyData(QueryReply qr)
	{
		return replyMapper.htBbsReviewReplyData(qr);
	}

	@Transactional
	public void delhtBbsReviewReply(String replyId)
	{
		replyMapper.deleteByPrimaryKey(replyId);
	}

	@Transactional
	public void insertSelective(Reply reply)
	{
		replyMapper.insertSelective(reply);
	}

	public List<Reply> qtGetReplyByReviewId(String reviewId)
	{
		return replyMapper.qtGetReplyByReviewId(reviewId);
	}

	@Transactional
	public void delhtBbsReviewReplyByReviewId(String reviewId)
	{
		replyMapper.delhtBbsReviewReplyByReviewId(reviewId);
	}
}

package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.MemberMapper;
import com.ttgis.recruit.domain.Member;
import com.ttgis.recruit.domain.QueryMember;

@Repository
@Service
public class MemberService
{
	@Resource
	private MemberMapper memberMapper;

	public List<Member> selectByWhere(QueryMember qm)
	{
		return memberMapper.selectByWhere(qm);
	}

	public int selectCount(QueryMember qm)
	{
		return memberMapper.selectCount(qm);
	}

	public Member selectByPrimaryKey(String memberId)
	{
		return memberMapper.selectByPrimaryKey(memberId);
	}

	public void updateByPrimaryKeySelective(Member member)
	{
		memberMapper.updateByPrimaryKeySelective(member);
	}

	public void insertSelective(Member member)
	{
		memberMapper.insertSelective(member);
	}

	@Transactional
	public void delMember(String memberId)
	{
		memberMapper.deleteByPrimaryKey(memberId);
	}

	public int hyCount()
	{
		return memberMapper.hyCount();
	}

	public List<Member> bzselectByWhere(QueryMember qm)
	{
		return memberMapper.bzselectByWhere(qm);
	}

	public int bzselectCount(QueryMember qm)
	{
		return memberMapper.bzselectCount(qm);
	}

	public List<Member> CpfyData(QueryMember qm)
	{
		return memberMapper.CpfyData(qm);
	}

	public int CpfyDataCount(QueryMember qm)
	{
		return memberMapper.CpfyDataCount(qm);
	}

	public List<Member> queryCpfygl()
	{
		return memberMapper.queryCpfygl();
	}

}

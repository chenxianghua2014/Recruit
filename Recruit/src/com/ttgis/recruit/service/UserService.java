package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.UserinfoMapper;
import com.ttgis.recruit.domain.QueryUser;
import com.ttgis.recruit.domain.Userinfo;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class UserService
{
	@Resource
	private UserinfoMapper userMapper;

	public Userinfo selectByPrimaryKey(String user_id)
	{
		return userMapper.selectByPrimaryKey(user_id);
	}

	@Transactional
	public int insertSelective(Userinfo user)
	{
		return userMapper.insertSelective(user);
	}

	@Transactional
	public int updateByPrimaryKeySelective(Userinfo user)
	{
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public List<Userinfo> queryUserFO()
	{
		return userMapper.queryUserFO();
	}

	public List<Userinfo> queryUserSun()
	{
		return userMapper.queryUserSun();
	}

	public List<Userinfo> selectLsyh(Map<String, String> params)
	{
		return userMapper.selectLsyh(params);
	}

	@Transactional
	public int deleteByPrimaryKey(String userId)
	{
		return userMapper.deleteByPrimaryKey(userId);
	}

	public Userinfo CheckLogin(Userinfo user)
	{
		return userMapper.CheckLogin(user);
	}

	public List<Userinfo> selectByWhere(QueryUser qu)
	{
		return userMapper.selectByWhere(qu);
	}

	public int selectCount(QueryUser qu)
	{
		return userMapper.selectCount(qu);
	}

	public int userHfjy(String userId)
	{
		return userMapper.userHfjy(userId);
	}

	public int checkPlrSFinUser(String reviewPlr)
	{
		return userMapper.checkPlrSFinUser(reviewPlr);
	}

	public int userJygl(String reviewPlr)
	{
		return userMapper.userJygl(reviewPlr);
	}

	public List<Userinfo> hyselectByWhere(QueryUser qu)
	{
		return userMapper.hyselectByWhere(qu);
	}

	public int hyselectCount(QueryUser qu)
	{
		return userMapper.hyselectCount(qu);
	}

	public List<Userinfo> selectWaitDelete(String currentdatetime)
	{
		return userMapper.selectWaitDelete(currentdatetime);
	}

	public List<Userinfo> selectByMsqId(String msqId)
	{
		return userMapper.selectByMsqId(msqId);
	}

	@Transactional
	public int delLsUser(Map<String, String> map)
	{
		return userMapper.delLsUser(map);
	}

	public int checkUserIdcard(String userIdcard)
	{
		return userMapper.checkUserIdcard(userIdcard);
	}

	public int checkUserName(String userEmail)
	{
		return userMapper.checkUserName(userEmail);
	}

	public int checkUserTelephone(Long userTelephone)
	{
		return userMapper.checkUserTelephone(userTelephone);
	}

	public int checkUserSfzh(String userSfzh)
	{
		return userMapper.checkUserSfzh(userSfzh);
	}

	public Userinfo checkUserIsCZ(Userinfo user)
	{
		return userMapper.checkUserIsCZ(user);
	}

}

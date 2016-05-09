package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.XxtzMapper;
import com.ttgis.recruit.controller.XxtzController;
import com.ttgis.recruit.domain.QueryXxtz;
import com.ttgis.recruit.domain.Xxtz;

import org.apache.log4j.Logger;

@Repository
@Service
public class XxtzService
{
	static Logger log = Logger.getLogger(XxtzController.class);
	@Resource
	private XxtzMapper xxtzMapper;

	/**
	 * 添加标签
	 * 
	 * @param xxtz
	 */
	@Transactional
	public void insertxxtz(Xxtz xxtz)
	{
		log.info("添加消息通知:" + xxtz.getXxtzId());
		xxtzMapper.insert(xxtz);

	}

	public Xxtz selectByPrimaryKey(String xxtzId)
	{
		return xxtzMapper.selectByPrimaryKey(xxtzId);
	}

	public List<Xxtz> selectCjtsEmail()
	{
		return xxtzMapper.selectCjtsEmail();
	}

	public List<Xxtz> selectByWhere(QueryXxtz p)
	{
		return xxtzMapper.selectByWhere(p);
	}

	public int updateByPrimaryKeySelective(Xxtz xxtz)
	{
		return xxtzMapper.updateByPrimaryKeySelective(xxtz);
	}
	
	public int updateByWhereSelective(Xxtz xxtz)
	{
		return xxtzMapper.updateByWhereSelective(xxtz);
	}
	
	public int selectCount(QueryXxtz p)
	{
		return xxtzMapper.selectCount(p);
	}

	public int selectFailedMsg(String strZzjgId)
	{
		return xxtzMapper.selectFailedMsg(strZzjgId);
	}
}

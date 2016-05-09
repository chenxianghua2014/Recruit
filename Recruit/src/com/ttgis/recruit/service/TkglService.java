package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.TkglMapper;
import com.ttgis.recruit.controller.TkglController;
import com.ttgis.recruit.domain.Gxcs;
import com.ttgis.recruit.domain.QueryTkgl;
import com.ttgis.recruit.domain.Tkgl;

import org.apache.log4j.Logger;

@Repository
@Service
public class TkglService
{
	static Logger log = Logger.getLogger(TkglController.class);

	@Resource
	private TkglMapper tkglMapper;

	public List<Tkgl> queryKsxcgl()
	{
		log.info("查询标签数据");
		return tkglMapper.quary();
	}

	/**
	 * 添加标签
	 * 
	 * @param ksxcgl
	 */
	@Transactional
	public void insertkgl(Tkgl tkgl)
	{
		log.info("添加标签:" + tkgl.getTkglId());
		tkglMapper.insert(tkgl);

	}

	public Tkgl selectByPrimaryKey(String tkgl_id)
	{
		return tkglMapper.selectByPrimaryKey(tkgl_id);
	}

	public List<Tkgl> selectByWhere(QueryTkgl qt)
	{
		return tkglMapper.selectByWhere(qt);
	}

	public List<Tkgl> selectByWherets(QueryTkgl qt)
	{
		return tkglMapper.selectByWherets(qt);
	}

	public List<Tkgl> querySj(Map<String, Object> params)
	{
		return tkglMapper.querySj(params);
	}

	public int selectCountts(QueryTkgl qt)
	{
		return tkglMapper.selectCountts(qt);
	}

	public int selectCount(QueryTkgl qt)
	{
		return tkglMapper.selectCount(qt);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Tkgl tkgl)
	{
		log.info("更新宣传信息ID" + tkgl.getTkglId());
		tkglMapper.updateByPrimaryKeySelective(tkgl);
	}

	@Transactional
	public int deleteByPrimaryKey(String tkgl_id)
	{
		log.info("删除宣传信息ID" + tkgl_id);
		return tkglMapper.deleteByPrimaryKey(tkgl_id);
	}

	@Transactional
	public void insertGxcs(Gxcs gxcs)
	{
		tkglMapper.insertGxcs(gxcs);
	}

	@Transactional
	public void delGxcsByName(String gxcsKsxm)
	{
		tkglMapper.delGxcsByName(gxcsKsxm);
	}

	public List<Gxcs> queryByGxcsId(String name)
	{
		return tkglMapper.queryByGxcsId(name);
	}

	public int checkGxcs(String gxcsKsxm)
	{
		return tkglMapper.checkGxcs(gxcsKsxm);
	}

	public int checkGxcsCount(String sfzh)
	{
		return tkglMapper.checkGxcsCount(sfzh);
	}

	public Gxcs queryBySfzh(String sfzh)
	{
		return tkglMapper.queryBySfzh(sfzh);
	}
}
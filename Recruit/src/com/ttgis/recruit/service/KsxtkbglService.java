package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.KsxtkbglMapper;
import com.ttgis.recruit.controller.KsxtkbglController;
import com.ttgis.recruit.domain.Ksxtkbgl;
import com.ttgis.recruit.domain.PageCondition;

import org.apache.log4j.Logger;

@Repository
@Service
public class KsxtkbglService
{
	static Logger log = Logger.getLogger(KsxtkbglController.class);

	@Resource
	private KsxtkbglMapper ksxtkbglMapper;

	public List<Ksxtkbgl> queryksxtkbgl()
	{
		log.info("查询标签数据");
		return ksxtkbglMapper.quary();
	}

	/**
	 * 添加标签
	 * 
	 * @param ksxtkbgl
	 */
	@Transactional
	public void insertksxtkbgl(Ksxtkbgl ksxtkbgl)
	{
		log.info("添加标签:" + ksxtkbgl.getKsxtkbglId());
		ksxtkbglMapper.insert(ksxtkbgl);

	}

	public Ksxtkbgl selectByPrimaryKey(String ksxtkbgl_id)
	{
		return ksxtkbglMapper.selectByPrimaryKey(ksxtkbgl_id);
	}

	public List<Ksxtkbgl> selectByWhere(PageCondition p)
	{
		return ksxtkbglMapper.selectByWhere(p);
	}

	public int selectCount(PageCondition p)
	{
		return ksxtkbglMapper.selectCount(p);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Ksxtkbgl ksxtkbgl)
	{
		log.info("更新宣传信息ID" + ksxtkbgl.getKsxtkbglId());
		ksxtkbglMapper.updateByPrimaryKeySelective(ksxtkbgl);
	}

	@Transactional
	public int deleteByPrimaryKey(String ksxtkbgl_Id)
	{
		log.info("删除宣传信息ID" + ksxtkbgl_Id);
		return ksxtkbglMapper.deleteByPrimaryKey(ksxtkbgl_Id);
	}

	public List<Ksxtkbgl> queryKssj()
	{
		return ksxtkbglMapper.queryKssj();
	}

}
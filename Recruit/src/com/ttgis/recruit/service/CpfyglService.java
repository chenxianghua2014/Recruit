package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.CpfyglMapper;
import com.ttgis.recruit.controller.KsxcglController;
import com.ttgis.recruit.domain.Cpfygl;
import com.ttgis.recruit.domain.PageCondition;
import org.apache.log4j.Logger;

@Repository
@Service
public class CpfyglService
{
	static Logger log = Logger.getLogger(KsxcglController.class);

	@Resource
	private CpfyglMapper cpfyglMapper;

	public List<Cpfygl> querycpfygl()
	{
		log.info("查询标签数据");
		return cpfyglMapper.quary();
	}

	/**
	 * 添加标签
	 * 
	 * @param ksxcgl
	 */
	public void insertcpfygl(Cpfygl cpfygl)
	{
		log.info("添加标签:" + cpfygl.getCpfyglId());
		cpfyglMapper.insert(cpfygl);

	}

	public Cpfygl selectByPrimaryKey(String cpfygl_id)
	{
		return cpfyglMapper.selectByPrimaryKey(cpfygl_id);
	}

	public List<Cpfygl> selectByWhere(PageCondition p)
	{
		return cpfyglMapper.selectByWhere(p);
	}

	public int selectCount(PageCondition p)
	{
		return cpfyglMapper.selectCount(p);
	}

	public void updateByPrimaryKeySelective(Cpfygl cpfygl)
	{
		log.info("更新宣传信息ID" + cpfygl.getCpfyglId());
		cpfyglMapper.updateByPrimaryKeySelective(cpfygl);
	}

}
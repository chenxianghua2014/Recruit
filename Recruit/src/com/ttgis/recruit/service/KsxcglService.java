package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.KsxcglMapper;
import com.ttgis.recruit.controller.KsxcglController;
import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.QueryKsxcgl;

import org.apache.log4j.Logger;

@Repository
@Service
public class KsxcglService
{
	static Logger log = Logger.getLogger(KsxcglController.class);

	@Resource
	private KsxcglMapper ksxcglMapper;

	public List<Ksxcgl> queryKsxcgl()
	{
		log.info("查询标签数据");
		return ksxcglMapper.queryKsxcgl();
	}

	public List<Ksxcgl> queryKsxcglByBkdw(String ksxcglBkdw)
	{
		log.info("查询标签数据");
		return ksxcglMapper.queryKsxcglByBkdw(ksxcglBkdw);
	}

	/**
	 * 添加标签
	 * 
	 * @param ksxcgl
	 */
	public void insertksxcgl(Ksxcgl ksxcgl)
	{
		log.info("添加标签:" + ksxcgl.getKsxcglId());
		ksxcglMapper.insert(ksxcgl);

	}

	public Ksxcgl selectByPrimaryKey(String ksxcgl_id)
	{
		return ksxcglMapper.selectByPrimaryKey(ksxcgl_id);
	}

	public List<Ksxcgl> selectByBmglId(Map<String, String> map)
	{
		return ksxcglMapper.selectByBmglId(map);
	}

	public List<Ksxcgl> selectByWhere(QueryKsxcgl qk)
	{
		return ksxcglMapper.selectByWhere(qk);
	}

	public int selectCount(QueryKsxcgl qk)
	{
		return ksxcglMapper.selectCount(qk);
	}

	public void updateByPrimaryKeySelective(Ksxcgl ksxcgl)
	{
		log.info("更新考试现场管理ID" + ksxcgl.getKsxcglId());
		ksxcglMapper.updateByPrimaryKeySelective(ksxcgl);
	}

	public void deleteByPrimaryKey(Map<String, String> map)
	{
		log.info("删除考试现场管理ID" + map);
		ksxcglMapper.deleteByPrimaryKey(map);
	}

	public void DelJsgcj(String ksxcgl_id)
	{
		log.info("删除技术岗成绩ID" + ksxcgl_id);
		ksxcglMapper.DelJsgcj(ksxcgl_id);
	}

	public void DelGlgcj(String ksxcgl_id)
	{
		log.info("删除管理岗成绩ID" + ksxcgl_id);
		ksxcglMapper.DelGlgcj(ksxcgl_id);
	}

	public void delByBmglId(String kcglId)
	{
		ksxcglMapper.delByBmglId(kcglId);
	}

	public void delByKcglName(String ksxcglKc)
	{
		ksxcglMapper.delByKcglName(ksxcglKc);
	}

	public int selectCountByKc(String ksxcglKc)
	{
		return ksxcglMapper.selectCountByKc(ksxcglKc);
	}

	@Transactional
	public void updKscjByName(Ksxcgl Ksxcgl)
	{
		ksxcglMapper.updKscjByName(Ksxcgl);
	}

	public List<Ksxcgl> queryByName(String ksxcglName)
	{
		return ksxcglMapper.queryByName(ksxcglName);
	}

	public List<Ksxcgl> queryByNameAndBkdw(Map<String, String> ksxcglparams)
	{
		return ksxcglMapper.queryByNameAndBkdw(ksxcglparams);
	}

	public int checkKscjGlg(String ksxcglName)
	{
		return ksxcglMapper.checkKscjGlg(ksxcglName);
	}

	public int checkKscjJsg(String ksxcglIdcard)
	{
		return ksxcglMapper.checkKscjJsg(ksxcglIdcard);
	}

	public Ksxcgl queryByKsxcglId(String ksxcglIdcard)
	{
		return ksxcglMapper.queryByKsxcglId(ksxcglIdcard);
	}

	public List<Ksxcgl> CjcxData(QueryKsxcgl qk)
	{
		return ksxcglMapper.CjcxData(qk);
	}

	public int CjcxCount(QueryKsxcgl qk)
	{
		return ksxcglMapper.CjcxCount(qk);
	}

	public List<Ksxcgl> EJCjcxData(QueryKsxcgl qk)
	{
		return ksxcglMapper.EJCjcxData(qk);
	}

	public int EJCjcxCount(QueryKsxcgl qk)
	{
		return ksxcglMapper.EJCjcxCount(qk);
	}

	public List<Ksxcgl> SJCjcxData(QueryKsxcgl qk)
	{
		return ksxcglMapper.SJCjcxData(qk);
	}

	public int SJCjcxCount(QueryKsxcgl qk)
	{
		return ksxcglMapper.SJCjcxCount(qk);
	}

	public void deleteByBmglInfo(Map<String, String> map)
	{
		ksxcglMapper.deleteByBmglInfo(map);
	}

}
package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.TxlMapper;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.Txl;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class TxlService
{
	@Resource
	private TxlMapper txlMapper;

	public Txl selectByPrimaryKey(String txl_id)
	{
		return txlMapper.selectByPrimaryKey(txl_id);
	}

	public void insertSelective(Txl txl)
	{
		txlMapper.insertSelective(txl);
	}

	public int deleteByPrimaryKey(String txl_id)
	{
		return txlMapper.deleteByPrimaryKey(txl_id);
	}

	public void updateByPrimaryKeySelective(Txl txl)
	{
		txlMapper.updateByPrimaryKeySelective(txl);
	}

	public Txl selectByName(Map<String, String> params)
	{
		return txlMapper.selectByName(params);
	}

	public List<Txl> selectByWhere(PageCondition p)
	{
		return txlMapper.selectByWhere(p);
	}

	public int selectCount(PageCondition p)
	{
		return txlMapper.selectCount(p);
	}

	public List<Txl> loadMsg(Map<String, String> params)
	{
		return txlMapper.loadMsg(params);
	}

	public List<Txl> loadZw(String zzjg)
	{
		return txlMapper.loadZw(zzjg);
	}

	public List<Txl> loadBm(String zzjg)
	{
		return txlMapper.loadBm(zzjg);
	}
}

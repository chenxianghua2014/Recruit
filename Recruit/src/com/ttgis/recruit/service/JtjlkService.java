package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.recruit.Mapper.JtjlkMapper;
import com.ttgis.recruit.domain.Jtjlk;
import com.ttgis.recruit.domain.Msg;
import com.ttgis.recruit.domain.MyApplication;
import com.ttgis.recruit.domain.QueryJtjlk;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class JtjlkService {
	@Resource
	private JtjlkMapper JtjlkMapper;

	public Jtjlk selectByPrimaryKey(String Jtjlk_id) {
		return JtjlkMapper.selectByPrimaryKey(Jtjlk_id);
	}

	public void insertSelective(Jtjlk jtjlk) {
		JtjlkMapper.insertSelective(jtjlk);
	}

	public int deleteByPrimaryKey(String Jtjlk_id) {
		return JtjlkMapper.deleteByPrimaryKey(Jtjlk_id);
	}

	public void updateByPrimaryKeySelective(Jtjlk jtjlk) {
		JtjlkMapper.updateByPrimaryKeySelective(jtjlk);
	}

	public void updateCpcj(Jtjlk jtjlk) {
		JtjlkMapper.updateCpcj(jtjlk);
	}

	public List<Jtjlk> selectSameGw(Jtjlk jtjlk) {
		return JtjlkMapper.selectSameGw(jtjlk);
	}

	public List<Jtjlk> selectByJlzt(Map<String, String> s) {
		return JtjlkMapper.selectByJlzt(s);
	}

	public List<Jtjlk> selectByWhere(QueryJtjlk q) {
		return JtjlkMapper.selectByWhere(q);
	}

	public List<Jtjlk> selectByMsqId(String msqId) {
		return JtjlkMapper.selectByMsqId(msqId);
	}

	public int selectCount(QueryJtjlk q) {
		return JtjlkMapper.selectCount(q);
	}

	public List<Jtjlk> selectIsExist(Map<String, String> s) {
		return JtjlkMapper.selectIsExist(s);
	}

	public List<Jtjlk> selectCanCollection(Map<String, String> s) {
		return JtjlkMapper.selectCanCollection(s);
	}

	public List<Jtjlk> selectStatusByUserId(String strUserId) {
		return JtjlkMapper.selectStatusByUserId(strUserId);
	}

	public Jtjlk queryKsByName(String JtjlkName) {
		return JtjlkMapper.queryKsByName(JtjlkName);
	}

	public List<MyApplication> selectMyApplication(String userId) {
		return JtjlkMapper.selectMyApplication(userId);
	}

	public Jtjlk CheckCpResult(Map<String, String> map) {
		return JtjlkMapper.CheckCpResult(map);
	}

	public List<Jtjlk> selectBySfzh(String sfzh) {
		return JtjlkMapper.selectBySfzh(sfzh);
	}

	public List<Jtjlk> selectJtjlkByWhere(QueryJtjlk q) {
		return JtjlkMapper.selectJtjlkByWhere(q);
	}

	public List<Jtjlk> selectBdwjlkByWhere(QueryJtjlk q) {
		return JtjlkMapper.selectBdwjlkByWhere(q);
	}

	public int selectJtjlkCount(QueryJtjlk q) {
		return JtjlkMapper.selectJtjlkCount(q);
	}

	public int selectBdwJtjlkCount(QueryJtjlk q) {
		return JtjlkMapper.selectBdwJtjlkCount(q);
	}

	public List<Jtjlk> selectJtjlkByWhere3Level(QueryJtjlk q) {
		return JtjlkMapper.selectJtjlkByWhere3Level(q);
	}

	public List<Jtjlk> selectJtjlkByWhere2Level(QueryJtjlk q) {
		return JtjlkMapper.selectJtjlkByWhere2Level(q);
	}

	public int selectBdwAndXsdwJtjlkCount(QueryJtjlk q) {
		return JtjlkMapper.selectBdwAndXsdwJtjlkCount(q);
	}

	public int LoadBdwAndXsdwJtjlkDataLevel3Count(QueryJtjlk q) {
		return JtjlkMapper.selectBdwAndXsdwJtjlkLevel3Count(q);
	}
	
	public int updateStar(QueryJtjlk q){
		return JtjlkMapper.updateStar(q);
	}

	public Jtjlk selectJtjlkById(Msg msg){
		return JtjlkMapper.selectJtjlkById(msg);
	}
	
}

package com.ttgis.recruit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ZzjgMapper;
import com.ttgis.recruit.domain.QueryJygl;
import com.ttgis.recruit.domain.Zzjg;

@Repository
@Service
public class ZzjgService {

	@Resource
	private ZzjgMapper zzjgMapper;

	public Zzjg CheckLogin(Zzjg zzjg) {
		return zzjgMapper.CheckLogin(zzjg);
	}

	public Zzjg selectByPrimaryKey(String zzjgId) {
		return zzjgMapper.selectByPrimaryKey(zzjgId);
	}

	public Zzjg selectByBmglId(String bmglId) {
		return zzjgMapper.selectByBmglId(bmglId);
	}

	@Transactional
	public int insertSelective(Zzjg zzjg) {
		return zzjgMapper.insertSelective(zzjg);
	}

	@Transactional
	public int updateByPrimaryKeySelective(Zzjg zzjg) {
		return zzjgMapper.updateByPrimaryKeySelective(zzjg);
	}

	public List<Zzjg> queryUserFO() {
		return zzjgMapper.queryUserFO();
	}

	public List<Zzjg> queryUserSun() {
		return zzjgMapper.queryUserSun();
	}

	public List<Zzjg> selectLsyh(Map<String, String> params) {
		return zzjgMapper.selectLsyh(params);
	}

	@Transactional
	public int deleteByPrimaryKey(String zzjgId) {
		return zzjgMapper.deleteByPrimaryKey(zzjgId);
	}

	public List<Zzjg> selectByWhere(QueryJygl qj) {
		return zzjgMapper.selectByWhere(qj);
	}

	public List<Zzjg> selectAllBkdw(Map<String, String> params) {
		return zzjgMapper.selectAllBkdw(params);
	}

	public int selectCount(QueryJygl qj) {
		return zzjgMapper.selectCount(qj);
	}

	public int hfjy(String zzjgId) {
		return zzjgMapper.hfjy(zzjgId);
	}

	public List<Zzjg> selectBySjdw(String sjdw) {
		return zzjgMapper.selectBySjdw(sjdw);
	}

	public List<Zzjg> selectSanjidw(String sjdw) {
		return zzjgMapper.selectSanjidw(sjdw);
	}

	public List<Zzjg> selectAll() {
		return zzjgMapper.selectAll();
	}

	public List<Zzjg> fhyfySelectByWhere(QueryJygl qj) {
		return zzjgMapper.fhyfySelectByWhere(qj);
	}

	public int fhyfySelectCount(QueryJygl qj) {
		return zzjgMapper.fhyfySelectCount(qj);
	}

	public List<Zzjg> queryFhyfy() {
		return zzjgMapper.queryFhyfy();
	}

	public List<Zzjg> EJqueryUserFO(String zzjgId) {
		return zzjgMapper.EJqueryUserFO(zzjgId);
	}

	public List<Zzjg> EJqueryUserSun(String zzjgId) {
		return zzjgMapper.EJqueryUserSun(zzjgId);
	}

	public Zzjg selectZzjgIdByUserId(String userId) {
		return zzjgMapper.selectZzjgIdByUserId(userId);

	}

	public List<Zzjg> EJqueryUserSun2Q(String zzjgId) {
		return zzjgMapper.EJqueryUserSun2Q(zzjgId);
	}

	public List<Zzjg> queryUserSunLevel2() {
		return zzjgMapper.queryUserSunLevel2();
	}
}

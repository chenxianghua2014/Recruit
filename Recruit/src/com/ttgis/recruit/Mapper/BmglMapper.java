package com.ttgis.recruit.Mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.recruit.domain.Bmgl;
import com.ttgis.recruit.domain.QueryBmgl;

public interface BmglMapper
{
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String bmglId);

	int deleteByWhere(Map<String, String> p);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	int insert(Bmgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	int insertSelective(Bmgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	Bmgl selectByPrimaryKey(String bmglId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Bmgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table BMGL
	 * 
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Bmgl record);

	List<Bmgl> selectByWhere(QueryBmgl qb);

	int selectCount(QueryBmgl qb);

	List<Bmgl> queryBmgl();

	List<Bmgl> selectIsExist(Bmgl b);

	List<Bmgl> CheckCpglLogin(Bmgl bmgl);

	void delByKcglId(String kcglId);

	void delByJtjlkId(String jtjlkId);

	List<Bmgl> queryBmglBySfzh(String bmglSfzh);
	
	List<Bmgl> queryAllBmgl();
}
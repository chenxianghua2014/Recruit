package com.ttgis.recruit.Mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.recruit.domain.Ksxcgl;
import com.ttgis.recruit.domain.QueryKsxcgl;

public interface KsxcglMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Map<String, String> map);

	int DelJsgcj(String ksxcglId);

	int DelGlgcj(String ksxcglId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int insert(Ksxcgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int insertSelective(Ksxcgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	Ksxcgl selectByPrimaryKey(String ksxcglId);

	List<Ksxcgl> selectByBmglId(Map<String, String> map);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Ksxcgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int updateByPrimaryKeyWithBLOBs(Ksxcgl record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table KSXCGL
	 * 
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Ksxcgl record);

	/**
	 * 查询
	 * 
	 * @return
	 */
	List<Ksxcgl> quary();

	List<Ksxcgl> selectByWhere(QueryKsxcgl qk);

	int selectCount(QueryKsxcgl qk);

	void delByBmglId(String kcglId);

	void delByKcglName(String ksxcglKc);

	int selectCountByKc(String ksxcglKc);

	void updKscjByName(Ksxcgl Ksxcgl);

	List<Ksxcgl> queryByName(String ksxcglName);

	List<Ksxcgl> queryByNameAndBkdw(Map<String, String> ksxcglparams);

	List<Ksxcgl> queryKsxcgl();

	int checkKscjGlg(String ksxcglIdcard);

	int checkKscjJsg(String ksxcglIdcard);

	Ksxcgl queryByKsxcglId(String ksxcglId);

	List<Ksxcgl> CjcxData(QueryKsxcgl qk);

	int CjcxCount(QueryKsxcgl qk);

	List<Ksxcgl> EJCjcxData(QueryKsxcgl qk);

	int EJCjcxCount(QueryKsxcgl qk);

	List<Ksxcgl> SJCjcxData(QueryKsxcgl qk);

	int SJCjcxCount(QueryKsxcgl qk);

	List<Ksxcgl> queryKsxcglByBkdw(String ksxcglBkdw);

	int deleteByBmglInfo(Map<String, String> map);

}
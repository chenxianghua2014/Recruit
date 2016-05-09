/**
 * 题库管理 Mapper实现 2014年05月19日
 * 董再兴
 */
package com.ttgis.recruit.Mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.recruit.domain.Gxcs;
import com.ttgis.recruit.domain.PageCondition;
import com.ttgis.recruit.domain.QueryTkgl;
import com.ttgis.recruit.domain.Tkgl;

public interface TkglMapper
{
	/**
	 * 根据主键删除实体对象
	 */
	int deleteByPrimaryKey(String tkglId);

	/**
	 * 插入实体对象
	 */
	int insert(Tkgl record);

	/**
	 * 选择性插入
	 */
	int insertSelective(Tkgl record);

	/**
	 * 根据主键查询实体对象
	 */
	Tkgl selectByPrimaryKey(String tkglId);

	/**
	 * 选择性根据主键更新实体
	 */
	int updateByPrimaryKeySelective(Tkgl record);

	/**
	 * 更新实体信息
	 */
	int updateByPrimaryKey(Tkgl record);

	/**
	 * 分页查询
	 * 
	 * @param pageCondition
	 * @return
	 */
	List<Tkgl> selectByPages(PageCondition pageCondition);

	/**
	 * 查询记录总数
	 * 
	 * @return
	 */
	int selectCount();

	List<Tkgl> quary();

	List<Tkgl> selectByWhere(QueryTkgl qt);

	List<Tkgl> selectByWherets(QueryTkgl qt);

	int selectCount(QueryTkgl qt);

	int selectCountts(QueryTkgl qt);

	List<Tkgl> querySj(Map<String, Object> params);

	void insertGxcs(Gxcs gxcs);

	List<Gxcs> queryByGxcsId(String gxcsKsxm);

	int checkGxcs(String gxcsKsxm);

	void delGxcsByName(String gxcsKsxm);

	int checkGxcsCount(String sfzh);

	Gxcs queryBySfzh(String sfzh);

}
/**
 * 董再兴 H2Dialect.java 2013年7月3日
 */
package com.ttgis.recruit.mybatis.dialect;


/**
 * 自定义H2数据库方言实现
 * @author 董再兴
 */
public class H2Dialect extends Dialect {

    public boolean supportsLimit() {
        return true;
    }

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		return new StringBuffer(sql.length() + 40).
			append(sql).
			append((offset > 0) ? " limit "+limitPlaceholder+" offset "+offsetPlaceholder : " limit "+limitPlaceholder).
			toString();
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}
    
//    public boolean bindLimitParametersInReverseOrder() {
//        return true;
//    }    
//
//    public boolean bindLimitParametersFirst() {
//        return false;
//    }

    

}
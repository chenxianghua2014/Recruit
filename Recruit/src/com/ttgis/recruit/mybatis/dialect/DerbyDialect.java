/**
 * 董再兴 DerbyDialect.java 2013年7月3日
 */
package com.ttgis.recruit.mybatis.dialect;

/**
 * 自定义Derby数据库方言实现
 * @author 董再兴
 */
public class DerbyDialect extends Dialect{

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException( "paged queries not supported" );
	}

}

/**
 * 董再兴 SybaseDialect.java 2013年7月3日
 */
package com.ttgis.recruit.mybatis.dialect;

/**
 * @author 董再兴
 * 自定义Sybase数据库方言实现
 */
public class SybaseDialect extends Dialect{

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

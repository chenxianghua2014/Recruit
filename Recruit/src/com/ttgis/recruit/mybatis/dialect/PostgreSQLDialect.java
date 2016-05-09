/**
 * 董再兴 PostgreSQLDialect.java 2013年7月3日
 */
package com.ttgis.recruit.mybatis.dialect;
/**
 * @author 董再兴
 * 自定义PostgreSQL数据库方言实现
 */
public class PostgreSQLDialect extends Dialect{
	
	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset(){
		return true;
	}
	
	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		return new StringBuffer( sql.length()+20 )
		.append(sql)
		.append(offset > 0 ? " limit "+limitPlaceholder+" offset "+offsetPlaceholder : " limit "+limitPlaceholder)
		.toString();
	}
}

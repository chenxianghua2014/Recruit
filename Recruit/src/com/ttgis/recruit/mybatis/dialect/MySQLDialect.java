/**
 * 董再兴 MySQLDialect.java 2013年7月3日
 */
package com.ttgis.recruit.mybatis.dialect;
/**
 * @author 董再兴
 * 自定义MySQL数据库方言实现
 */
public class MySQLDialect extends Dialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}   
  
}

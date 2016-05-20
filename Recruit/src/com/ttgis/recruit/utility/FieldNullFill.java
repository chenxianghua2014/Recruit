/**   
* @Title: FieldNullFill.java 
* @Package: com.ttgis.recruit.utility 
* @Description: TODO 
* @author: hua
* @date: 2016年5月20日 上午9:30:44 
* @version V1.0   
*/
package com.ttgis.recruit.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
* @ClassName: FieldNullFill 
* @Description: 遍历对象的属性值，若为null或"",选择值填充，由于Itxt Cell类addCell()方法中若输入参数为空，则会异常 
* @author: cxh 
* @date: 2016年5月20日 上午9:30:44 
*  
*/
public class FieldNullFill {

    /** 
    * @Title: main 
    * @Description: TODO 
    * @param @param args  参数说明 
    * @return void    返回类型 
    * @throws 
    */
    
    public static void checkFieldValueNull(Object bean,String fillContent) {
        
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                String fieldSetName = parSetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Method fieldSetMet = cls.getMethod(fieldSetName, new Class[]{String.class});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                
                if (fieldVal == null || "".equals(fieldVal)) {
                    fieldSetMet.invoke(bean, new Object[]{fillContent});
                }
            } catch (Exception e) {
                continue;
            }
        }
       // return result;
    }


    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }
    /**
     * 拼接某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
	if (null == fieldName || "".equals(fieldName)) {
	    return null;
	}
	int startIndex = 0;
	if (fieldName.charAt(0) == '_')
	    startIndex = 1;
	return "set"
	+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
	+ fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }
   

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	
	Person bean = new Person();
	bean.setName("chen");
	checkFieldValueNull(bean,"kong");
	System.out.println(bean.toString());
	System.out.println(bean.getSexxx());
    }

}

class Person{
    private String name ;
   
    private String sex ;
    
    private String sexxx ;
    
    private int age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getSexxx() {
        return sexxx;
    }

    public void setSexxx(String sexxx) {
        this.sexxx = sexxx;
    }

    public String toString(){
	return "xingming:" + this.getName() + "age" + getAge() + "sex" + getSex();
	
    } 
}

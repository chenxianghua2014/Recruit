/**   
* @Title: ResumeMapperTest.java 
* @Package: com.ttgis.recruit.Mapper 
* @Description: TODO 
* @author: hua
* @date: 2016年5月18日 上午9:42:12 
* @version V1.0   
*/
package com.ttgis.recruit.Mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ttgis.recruit.domain.Resume;
import com.ttgis.recruit.service.ResumeService;

/** 
* @ClassName: ResumeMapperTest 
* @Description: TODO 
* @author: cxh 
* @date: 2016年5月18日 上午9:42:12 
*  
*/
public class ResumeMapperTest {
    @Autowired
    private ResumeService resumeService;

    /**
     * Test method for {@link com.ttgis.recruit.Mapper.ResumeMapper#selectByPrimaryKeySelectiveById(java.lang.String)}.
     */
    @Test
    public void testSelectByPrimaryKeySelectiveById() {
	resumeService.selectByPrimaryKeySelectiveById("60030F27-A73C-5E2D-4DE5-A5BBF6537D7E");
	
    }

}

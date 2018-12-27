package com.source.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.source.model.User;
import com.source.services.UserServicesImpl;

/**
 * 单元测试
 * @author Administrator
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)   //让测试运行于Spring测试环境
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc-servlet.xml"})
public class DaoTest {
	
    @Resource
    private UserServicesImpl userServicesImpl;
	
    @Test
	public void findUser() throws Exception {
    	User user = userServicesImpl.FindByAccout("admin");
    	System.out.println("我要的输出的结果="+user.getId());
	}
}

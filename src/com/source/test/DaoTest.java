package com.source.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.source.model.User;
import com.source.services.UserServicesImpl;
import com.source.test_model.P1;
import com.source.test_model.P2;
import com.source.test_model.P3;
import com.source.test_services.P2Service;
import com.source.test_services.P3Service;

/**
 * 单元测试
 * @author Administrator
 *测试 p1 p2 p2的属相为p1类类型，并且为p1的的普通属相，在这个测试类中进行p2 增删改查
 *测试 p1 p3 p3的属性为p1类类型，并且为p1的主键，在这个测试类中进行p3 增删改
 */

@RunWith(SpringJUnit4ClassRunner.class)   //让测试运行于Spring测试环境
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc-servlet.xml"})
public class DaoTest {
	
    @Resource
    private UserServicesImpl userServicesImpl;
    
    @Resource
    private P2Service p2Service;
    
    @Resource
    private P3Service p3Service;
	
    @Test
	public void findUser() throws Exception {
    	User user = userServicesImpl.FindByAccout("admin");
    	System.out.println("我要的输出的结果="+user.getId());
	}
    
    @Test
    public void addP2() {
    	P1 p1 = new P1();
    	p1.setName("哈哈");
    	String tel = "111112";
    	int id = p2Service.add(p1,tel);
    
    	
    }
    
    @Test
    public void updateP2() {
    	P1 p1 = new P1();
    	p1.setName("修改ddd");
    	String tel = "000000";
    	int id = p2Service.update(p1, tel, 1);
        System.out.println(id);
    }
    
    @Test
    public void findByIdP2() {
		P2 p2 = p2Service.findById(1);
		System.out.println(p2);
    	String name  = p2.getP1().getName();
    	System.out.println(name);
    }
    
    @Test
    public void findAllP2() {
    	List<P2> listP2 = p2Service.fingAll();
    	System.out.println(listP2.size());
    	for (P2 p2 : listP2) {
			System.out.println("姓名："+p2.getP1().getName()+"----"+"电话："+p2.getTel());
		}
    }
    
    @Test
    public void add3() {
    	P1 p1 = new P1();
    	p1.setId(2);
    	int result =p3Service.add("陈仓区", p1);
    	System.out.println(result);
    }
    
    
    @Test
    public void updateP3() {
    	P1 p1 = new P1();
    	p1.setId(2);
    	int result = p3Service.update("宝鸡虢镇", p1, 1);
    	System.out.println(result);
    }
    
    //根据嵌套的对象获取ID
    @Test
    public void findByIdP3() {
    	P3 p3 = p3Service.findById(1);
    	P1 p1 = p3.getP1_id();
    	System.out.println(p1.getName());
    }
    
    @Test
    public void findAllP3() {
    	List<P3> listP3 = p3Service.findAll();
    	for (P3 p3 : listP3) {
			System.out.println(p3.getP1_id().getName());
		}
    }
    
    //测试事务
    @Test
   public void tans() {
    	p3Service.trans();
    }
    
}

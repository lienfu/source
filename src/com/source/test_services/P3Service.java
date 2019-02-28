package com.source.test_services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.source.test_dao.P3Mapper;
import com.source.test_model.P1;
import com.source.test_model.P3;


@Service
public class P3Service {

	@Resource
	private P3Mapper p3Mapper;
	
	@Resource
	private P3Service p3Service;
	
	@Resource
	private P2Service p2Service;
	
	
	public int add(String address,P1 p1) {
		return p3Mapper.add(address, p1);
	}
	
	public int update(String address,P1 p1,int id) {
		return p3Mapper.update(address, p1, id);
	}
	
	public P3 findById(int id) {
		return p3Mapper.findById(id);
	}
	
	public List<P3> findAll(){
		return p3Mapper.findAll();
	}
	
	 @Transactional
	    public void trans() {
	    	P1 p1 = new P1();
	    //	p1.setName("bbbbbbb");
	    	p1.setId(9);
	    	try {
	    		int result_p3 = p3Service.add("事务测试", p1);
	    		System.out.println("------------------P3的添加结果"+result_p3);
	    		int result_p2 = p2Service.add(p1, "jjjjjjj");
	    		System.out.println("=================P2的添加结果"+result_p2);
	    	}catch(Exception e) {
	    		System.out.println("进入异常");
	    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
	    	}
	    	
	    }
}

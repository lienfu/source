package com.source.test_services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.test_dao.P1Mapper;
import com.source.test_model.P1;

@Service
public class P1Service {
	
	@Resource
	private P1Mapper p1Mapper;
	
	public int bacthAdd(List<P1> p1) throws Exception{
	
		return	p1Mapper.batchAdd(p1);
		
	}
}

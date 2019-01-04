package com.source.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.P3Mapper;
import com.source.model.P1;
import com.source.model.P3;

@Service
public class P3Service {

	@Resource
	private P3Mapper p3Mapper;
	
	
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
}

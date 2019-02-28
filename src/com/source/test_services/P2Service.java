package com.source.test_services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.test_dao.P2Mapper;
import com.source.test_model.P1;
import com.source.test_model.P2;

@Service
public class P2Service {

	@Resource
	private P2Mapper p2Mapper;

	public int add(P1 p1, String tel) {
		return p2Mapper.addP2(p1, tel);
	}

	public int update(P1 p1, String tel, int id) {
		return p2Mapper.updateP2(p1, tel, id);
	}

	public P2 findById(int id) {
		return p2Mapper.findById(id);
	}
	
	public List<P2> fingAll(){
		return p2Mapper.findAll();
	}

}

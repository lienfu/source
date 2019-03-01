package com.source.test_dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.test_model.P1;

@Repository
public interface P1Mapper {
	
	public int batchAdd(List<P1> p1);

}

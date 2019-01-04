package com.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.P1;
import com.source.model.P3;

@Repository
public interface P3Mapper {
	
	public int add(@Param("address") String address,@Param("p1") P1 p1);
	
	public int update(@Param("address") String adress,@Param("p1") P1 p1,@Param("id") int id);
	
	public P3 findById(@Param("id") int id);
	
	public List<P3> findAll();
}

package com.source.test_dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.test_model.P1;
import com.source.test_model.P2;



@Repository
public interface P2Mapper {
	public int addP2(@Param("p1") P1 p1,@Param("tel") String tel);
	
	public int updateP2(@Param("p1") P1 p1,@Param("tel") String tel,@Param("id") int id);
	public P2 findById(@Param("id") int id);
	
	public List<P2> findAll();
}

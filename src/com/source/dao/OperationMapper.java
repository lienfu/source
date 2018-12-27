package com.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.Operation;

@Repository
public interface OperationMapper {

	public List<Operation> findAll();
	
	public Operation findById(@Param("id") int id);
	
	public int addOperation(Operation operation);
	
	public int updateOperation(Operation operation);
	
	public int deleteById(@Param("id") int id);
	
	public int pageCount();
	
	public List<Operation> pageOperation(@Param("pageIndex") int pageIndex, @Param("pageNumber") int pageNumber);
	
}

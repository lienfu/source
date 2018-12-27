package com.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.Log;

@Repository
public interface LogMapper {

	public int addLog(Log log);
	
	public int pageCount();
	
	public List<Log> pageLog(@Param("pageIndex") int pageIndex, @Param("pageNumber") int pageNumber);
}

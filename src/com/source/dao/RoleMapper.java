package com.source.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.Role;


@Repository
public interface RoleMapper {

	public int addRole(Role role);

	public List<Role> pageRole(@Param("pageIndex") int pageIndex, @Param("pageNumber") int pageNumber);

	public int pageCount();
	
	public int deleteById(@Param("id") int id);
	
	public Role findById(@Param("id") int id);
	
	public int updateRole(Role role);
	
	public List<Role> findAll();
}

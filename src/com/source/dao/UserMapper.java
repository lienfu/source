package com.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.User;

@Repository
public interface UserMapper {
	
	public int addUser(User user);
	
	public List<User> pageUser(@Param("pageIndex") int pageIndex, @Param("pageNumber") int pageNumber);
	
	public int pageCount();
	
	public int deleteById(@Param("id") int id);
	
	public User findById(@Param("id") int id);
	
	public User FindByAccout(@Param("account") String account);
	
	public List<User> FindByAreaId(@Param("area_id") String area_id);
	
	public int updateUser(User user);
	
	public User userLogin(@Param("account") String name, @Param("password") String pwd);
	
	
	public User IfUserLogin(@Param("id") int id, @Param("account") String account);
	
	public int updateUserPw(@Param("password") String pwd, @Param("update_time") String update_time,@Param("id") int id);
	
	
}
	


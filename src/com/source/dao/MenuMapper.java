package com.source.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.Menu;

@Repository
public interface MenuMapper {

	public int addMenu(Menu menu);
	
	public List<Map> pageMenu(@Param("pageIndex") int pageIndex,@Param("pageNumber") int pageNumber);
	
	public int pageCount();
	
	public int deleteById(@Param("id") int id);
	
	public int updateMenu(Menu menu);
	
	public Menu findById(@Param("id") int id);
	
	public List<Menu> findByLevel();
	
	public int findUpMenuId(@Param("up_menu_id") String up_menu_id );
	
	public List<Menu> findAll();
	

}

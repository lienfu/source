package com.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.source.model.Area;

@Repository
public interface AreaMapper {

	public int addArea(Area area);
	
	public Area findById(@Param("id") int id);
	
	public List<Area> pageArea(@Param("pageIndex") int pageIndex, @Param("pageNumber") int pageNumber);
	
	public int pageCount();
	
	public int deleteById(@Param("id") int id);
	
	public int updateArea(Area area);
	
	public List<Area> findAll();
	
	public List<Area> findLevelOne();
	
	public List<Area> findUpAreaId(@Param("up_area_id") String up_area_id);
	
	public List<Area> findAreaId(@Param("up_area_id") String up_area_id);
	
	public List<Area> findAreaIds(@Param("up_area_id") String up_area_id);
	
	public int ifDeleteById(@Param("up_area_id") String up_area_id);
	
    public List<Area> findlevelArea(@Param("area_id") String area_id,@Param("level") int level);
    
    public Area findByUpArea(@Param("up_area_id") String up_area_id,@Param("area_id") String area_id);
    
	

}

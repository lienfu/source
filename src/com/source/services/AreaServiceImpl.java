package com.source.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.AreaMapper;
import com.source.interfaces.IService;
import com.source.model.Area;
import com.source.tools.PageBean;

@Service
public class AreaServiceImpl implements IService {
	
	@Resource
	private AreaMapper areaMapper;

	@Override
	public String deleteById(int id) throws Exception {
		String result = "";
		int deleteAreaCount = areaMapper.ifDeleteById(String.valueOf(id));
		if(deleteAreaCount>0) {
			result = "请先删除下级区域后，在尝试删除";
		}else {
			areaMapper.deleteById(id);
			result = "删除成功" ;
		}
		return result;
	}

	@Override
	public int update(Object object) throws Exception {
		Area area = (Area)object;
		area.setUp_area_id(areaMapper.findById(area.getId()).getUp_area_id());
		return areaMapper.updateArea(area);
	}

	@Override
	public int add(Object object) throws Exception {
		return areaMapper.addArea((Area)object);
	}

	@Override
	public int delById(int id) throws Exception {
		return areaMapper.deleteById(id);
	}
	
	public int pageCount() {
		return areaMapper.pageCount();
	}
	
	public PageBean<Area> pageArea(int pageIndex,int pageNumber) throws Exception{
		List<Area> areaList = areaMapper.pageArea((pageIndex-1)*pageNumber,pageNumber);
		List<Area> pageAreaList = new ArrayList<Area>();
		for (Area area : areaList) {
			if(area.getUp_area_id().equals("0")) {
				area.setUp_area_id("根区域");
			}else {
				area.setUp_area_id(areaMapper.findById(Integer.parseInt(area.getUp_area_id())).getArea_name());;
			}
			if(area.getArea_id().equals("0")) {
				area.setArea_id("根区域");
			}else {
				area.setArea_id(areaMapper.findById(Integer.parseInt(area.getArea_id())).getArea_name());
			}
			pageAreaList.add(area);
		}
		
		Area area = new Area();
		PageBean<Area> pageArea = new PageBean<Area>();
		int count = pageCount();
		pageArea.setCount(count);
		int pageCountNumber ;
		if (count % pageNumber == 0) {
			pageCountNumber = count / pageNumber;
		} else {
			pageCountNumber = count / pageNumber + 1;
		}
		pageArea.setPageCountNumber(pageCountNumber);
		pageArea.setPageData(pageAreaList);
		pageArea.setPageIndex(pageIndex);
		pageArea.setPageNumber(pageNumber);
		return pageArea;
	}
	
	public List<Area> findAll() throws Exception{
		return areaMapper.findAll();
	}
	
	public Area findById(int id) throws Exception{
		Area area = areaMapper.findById(id);
		
		if(area.getUp_area_id().equals("0")) {
			area.setUp_area_id("根区域");
		}else {
			area.setUp_area_id(areaMapper.findById(Integer.parseInt(area.getUp_area_id())).getArea_name());;
		}
		if(area.getArea_id().equals("0")) {
			area.setArea_id("根区域");
		}else {
			area.setArea_id(areaMapper.findById(Integer.parseInt(area.getArea_id())).getArea_name());
		}
		
		return area;
	}
	
	public List<Area> findLevelOne() throws Exception{
		return areaMapper.findLevelOne();
	}
	
	public List<Area> findUpAreaId(String up_area_id) throws Exception{
		
		return areaMapper.findUpAreaId(up_area_id);
	}
	
	public List<Area> findAreaId(String up_area_id) throws Exception{
		
		List<Area> areaList = areaMapper.findAreaId(up_area_id);
			
		return areaList;
	}
	
	public List<Area> findAreaIds(String up_area_id) throws Exception{
			
		List<Area> areaList = areaMapper.findAreaIds(up_area_id);
			
		return areaList;
	}
	
	public List<Area> findlevelArea(String area_id,int level) throws Exception{
		List<Area> areaList = areaMapper.findlevelArea(area_id, level);
		return areaList;
		
	}
	
	public Area findAreaId(int id) throws Exception{
		Area area = areaMapper.findById(id);
		return area;
	}
	
	
	 public Area findByUpArea(String up_area_id,String area_id) throws Exception {
		 Area area = areaMapper.findByUpArea(up_area_id, area_id);
		 return area;
	 }
	

}

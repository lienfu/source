package com.source.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.components.Info;
import com.source.model.Area;
import com.source.services.AreaServiceImpl;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.PageBean;

@RequestMapping("/area")
@Controller
public class AreaController {

	@Autowired
	private AreaServiceImpl areaServiceImpl;

	// 分页 获取前台传过来的页码数
	private int getPageIndex(HttpServletRequest request) {
		String pageIndex = request.getParameter("pageIndex");
		if (pageIndex != null && !pageIndex.trim().isEmpty()) {
			return Integer.parseInt(pageIndex);
		}
		return 1;
	}

	// 获取前台传过来的链接
	private String getUrl(HttpServletRequest request) {
		String url = request.getServletPath(); // 获取查询字符串 url 问号后面的
		int pageIndex = url.lastIndexOf("&pageIndex=");
		if (pageIndex == -1) {
			return url;
		}
		return url.substring(0, pageIndex);
	}
	
	@RequestMapping("/pageArea")
	@ResponseBody
	public PageBean<Area> findPageArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		PageBean<Area> pageArea = null;
		try {
			pageArea = areaServiceImpl.pageArea(pageIndex, pageNumber);
			pageArea.setUrl(url);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return pageArea;
	}
	
	@RequestMapping("/addArea")
	@ResponseBody
	public String addArea(@RequestBody Area area) throws Exception {
		String result = "";
		try {
			if(area.getArea_id().equals("0")) {
				area.setLevel(0);
				area.setUp_area_id("0");
			}else {
				Area up_Area = areaServiceImpl.findById(Integer.parseInt(area.getUp_area_id()));
				area.setLevel(up_Area.getLevel()+1);
			}
			areaServiceImpl.add(area);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/updateArea")
	@ResponseBody
	public String updateArea(@RequestBody Area area) throws Exception {
		String result = "";
		try {
			areaServiceImpl.update(area);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public Area findById(@RequestParam("id") int id,HttpServletResponse response) throws IOException {
		Area area = null;
		try {
			 area = areaServiceImpl.findById(id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		
		return area;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Area> findAll(HttpServletResponse response) throws IOException{
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findAll();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList;
	}
	
	@RequestMapping("/findLevelOne")
	@ResponseBody
	public List<Area> findLevelOne(HttpServletResponse response) throws IOException{
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findLevelOne();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList;
	}
	
	@RequestMapping("/findUpAreaId")
	@ResponseBody
	public List<Area> findUpAreaId(@RequestParam("up_area_id") String up_area_id,
			HttpServletResponse response) throws IOException{
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findUpAreaId(up_area_id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList;
	}
	
	@RequestMapping("/findAreaId")
	@ResponseBody
	public List<Area> findAreaId(@RequestParam("up_area_id") String up_area_id,
			HttpServletResponse response) throws IOException {
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findAreaId(up_area_id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList; 
	}
	
	@RequestMapping("/findAreaIds")
	@ResponseBody
	public List<Area> findAreaIds(@RequestParam("up_area_id") String up_area_id,
			HttpServletResponse response) throws IOException {
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findAreaIds(up_area_id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int id) throws Exception {
		String result = areaServiceImpl.deleteById(id);
		return JacksonJsonUtil.beanToJson(result);
	}
	
	
	@RequestMapping("/findlevelArea")
	@ResponseBody
	public List<Area> findlevelArea(@RequestParam("area_id") String area_id,@RequestParam("level") int level,
			HttpServletResponse response
			) throws IOException{
		List<Area> areaList = null;
		try {
			areaList = areaServiceImpl.findlevelArea(area_id, level);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return areaList;
	}
	
}

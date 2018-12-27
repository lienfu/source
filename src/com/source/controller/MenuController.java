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
import com.source.components.SessionUser;
import com.source.model.Menu;
import com.source.model.User;
import com.source.services.MenuServiceImpl;
import com.source.services.RoleServiceImpI;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.PageBean;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@Autowired
	private RoleServiceImpI roleServiceImpI;
	
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
	
	@RequestMapping("findById")
	@ResponseBody
	public Menu findById(@RequestParam("id") int id,HttpServletResponse response) throws IOException {
		Menu menu =null;
		try {
			menu = menuServiceImpl.findById(id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return menu;
	}
	
	@RequestMapping("/pageMenu")
	@ResponseBody
	public PageBean<Menu> findPageMenu(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		PageBean<Menu> pageMenu = null;
		try {
			pageMenu = menuServiceImpl.pageMenu(pageIndex, pageNumber);
			pageMenu.setUrl(url);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
			e.printStackTrace();
		}
		return pageMenu;
	}
	
	@RequestMapping("/updateMenu")
	@ResponseBody
	public String updateMenu(@RequestBody Menu menu) throws Exception {
		String result = "";
		try {
			menuServiceImpl.update(menu);
			result = Info.RESULT;
		} catch (Exception e) {
			
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int id) throws Exception {
		String result = "";
		try {
			result = menuServiceImpl.deleteById(id);
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/addMenu")
	@ResponseBody
	public String addMune(@RequestBody Menu menu) throws Exception {
		String result = "";
		try {
			menuServiceImpl.add(menu);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/findByLevel")
	@ResponseBody
	public List<Menu> findByLevel(HttpServletResponse response) throws IOException {
		List<Menu> menuList = null;
		try {
			 menuList = menuServiceImpl.findByLevel();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return menuList;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Menu> findAll(HttpServletResponse response) throws IOException{
		List<Menu> menuList = null;
		
		try {
			 menuList = menuServiceImpl.findAll();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return menuList;
	}
	
	@RequestMapping("/findByUser")
	@ResponseBody
	public List<Menu> findByUser(HttpServletResponse response) throws IOException{
		User user = SessionUser.getUser();
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menu = null;
		try {
			String Menus = roleServiceImpI.findById(Integer.parseInt(user.getRole_id())).getMenus();
			String[] MenusArgs = Menus.split(","); 
			for(int i=0;i<MenusArgs.length;i++) {
				int muenu_id = Integer.parseInt(MenusArgs[i]);
				menu =menuServiceImpl.findByUser(muenu_id);
				menuList.add(menu);
				
			}
			
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		} 
		
		return menuList;
		
	}
	
	
}

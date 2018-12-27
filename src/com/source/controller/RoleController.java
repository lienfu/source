package com.source.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.components.Info;
import com.source.model.Menu;
import com.source.model.Operation;
import com.source.model.Role;
import com.source.services.MenuServiceImpl;
import com.source.services.OperationServiceImpl;
import com.source.services.RoleServiceImpI;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.PageBean;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleServiceImpI roleServiceImpI;
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Autowired
	private OperationServiceImpl operationServiceImpl;

	@SuppressWarnings("null")
	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(@RequestParam("role_name") String role_name, @RequestParam("status") int status,
			@RequestParam("menus[]") String[] menus, @RequestParam("operations[]") String[] operations)
			throws Exception {

		Role role = new Role();
		role.setRole_name(role_name);
		role.setStatus(status);
		StringBuffer sb_menus = new StringBuffer();
		StringBuffer sb_operations = new StringBuffer();

		if (menus[0].equals("0")) {
			role.setMenus("");
		} else {
			for (int i = 0; i < menus.length; i++) {
				sb_menus.append(menus[i] + ",");
			}
			role.setMenus(sb_menus.toString().substring(0, sb_menus.toString().length() - 1));
		}

		if (operations[0].equals("0")) {
			role.setOperations("");
		} else {
			for (int i = 0; i < operations.length; i++) {
				sb_operations.append(operations[i] + ",");
			}
			role.setOperations(sb_operations.toString().substring(0, sb_operations.toString().length() - 1));
		}

		String result = "";
		try {
			roleServiceImpI.add(role);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}

		return JacksonJsonUtil.beanToJson(result);
	}

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
	
	@RequestMapping("/pageRole")
	@ResponseBody
	public PageBean<Role> findPageRole(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		PageBean<Role> pageRole = null;
		try {
			pageRole = roleServiceImpI.pageRole(pageIndex, pageNumber);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return pageRole;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteByid(@RequestParam("id") int id) throws Exception {
		String result = "";
		try {
			roleServiceImpI.delById(id);
			result=Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
		
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public List findById(@RequestParam("id") int id,HttpServletResponse response) throws IOException {
		List list = new ArrayList();
		Role role =null;
		try {
			role = roleServiceImpI.findById(id);
			List<Menu> menuList = menuServiceImpl.findAll();
			List<Operation> operationList = operationServiceImpl.findAll();
			list.add(role);
			list.add(menuList);
			list.add(operationList);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return list;
	}
	
	@RequestMapping("/updateRole")
	@ResponseBody
	public String updateRole(@RequestParam("id") int id,@RequestParam("role_name") String role_name, @RequestParam("status") int status,
			@RequestParam("menus[]") String[] menus, @RequestParam("operations[]") String[] operations) throws Exception {
		
		String result="";
		Role role = new Role();
		role.setId(id);
		role.setRole_name(role_name);
		role.setStatus(status);
		StringBuffer sb_menus = new StringBuffer();
		StringBuffer sb_operations = new StringBuffer();

		if (menus[0].equals("0")) {
			role.setMenus("");
		} else {
			for (int i = 0; i < menus.length; i++) {
				sb_menus.append(menus[i] + ",");
			}
			role.setMenus(sb_menus.toString().substring(0, sb_menus.toString().length() - 1));
		}

		if (operations[0].equals("0")) {
			role.setOperations("");
		} else {
			for (int i = 0; i < operations.length; i++) {
				sb_operations.append(operations[i] + ",");
			}
			role.setOperations(sb_operations.toString().substring(0, sb_operations.toString().length() - 1));
		}
		try {
			roleServiceImpI.update(role);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Role> findAll(HttpServletResponse response) throws IOException{
		List<Role> roleList = null;
		try {
			 roleList = roleServiceImpI.findAll();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		
		return roleList;
	}
	

}

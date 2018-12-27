package com.source.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.source.model.User;
import com.source.services.UserServicesImpl;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.MD5Util;
import com.source.tools.PageBean;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServicesImpl userServices;
	
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
	
	
	@RequestMapping("/pageUser")
	@ResponseBody
	public PageBean<User> findPageUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		
		PageBean<User> pageUser = null;
		try {
			pageUser = userServices.pageUser(pageIndex, pageNumber);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return pageUser;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(@RequestBody User user) throws Exception {
		String result = "";
		try {
			user.setPassword(MD5Util.MD5(user.getPassword()));
			userServices.add(user);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int id) throws Exception{
		String result="";
		try {
			int res = userServices.delById(id);
			if(res>0) {
				result=Info.RESULT;
			}
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public List findById(@RequestParam("id") int id,HttpServletResponse response) throws IOException {
		List list = new ArrayList();
		 try {
			 list = userServices.findById(id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		 
		return list;
				
	}
	
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(@RequestBody User user) throws Exception {
		String result="";
		try {
			userServices.update(user);
			result=Info.RESULT;
		} catch (Exception e) {
			result=Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	
	@RequestMapping("/updateUserPw")
	@ResponseBody
	public String updateUserPw(@RequestBody User user) throws Exception  {
		String result="";
		
		String pwd = MD5Util.MD5(user.getPassword()) ;
		int id = user.getId();
		try {
			userServices.updateUserPw(pwd, id);
			result=Info.RESULT;
		} catch (Exception e) {
			result=Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
		
		
		
	}
	
	
	
}

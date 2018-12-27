package com.source.controller;

import java.io.IOException;
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
import com.source.model.Menu;
import com.source.model.Operation;
import com.source.services.OperationServiceImpl;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.PageBean;

@Controller
@RequestMapping("/operation")
public class OperationController {
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Operation> findAll(HttpServletResponse response) throws IOException{
		List<Operation> operationList =null;
		try {
			operationList = operationServiceImpl.findAll();
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return operationList;
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
	
	@RequestMapping("/pageOperation")
	@ResponseBody
	public PageBean<Operation> pageOperation(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		PageBean<Operation> pageOperation = null;
		
		try {
			pageOperation = operationServiceImpl.pageOperation(pageIndex, pageNumber);
			pageOperation.setUrl(url);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
			e.printStackTrace();
		}
		return pageOperation;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int id) throws Exception {
		String result = "";
		try {
			operationServiceImpl.delById(id);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public Operation findById(@RequestParam("id") int id,HttpServletResponse response) throws IOException {
		Operation operation = null;
		try {
			operation = operationServiceImpl.findById(id);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return operation;
	}
	
	@RequestMapping("/addOperation")
	@ResponseBody
	public String addOperation(@RequestBody Operation operation) throws Exception {
		String result = "";
		try {
			operationServiceImpl.add(operation);
			result=Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
	
	@RequestMapping("/updateOperation")
	@ResponseBody
	public String updateOperation(@RequestBody Operation operation) throws Exception {
		String result = "";
		try {
			operationServiceImpl.update(operation);
			result = Info.RESULT;
		} catch (Exception e) {
			result = Info.MESSAGE;
		}
		return JacksonJsonUtil.beanToJson(result);
	}
}

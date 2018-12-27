package com.source.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.components.Info;
import com.source.model.Log;
import com.source.services.LogServiceImpl;
import com.source.tools.PageBean;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	private LogServiceImpl logServiceImpl;
	

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
	
	@ResponseBody
	@RequestMapping("/pageLog")
	public PageBean<Log> findPageLog(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNumber = Info.PAGENUMBWE;
		String url = getUrl(request);
		int pageIndex = getPageIndex(request);
		PageBean<Log> pagelog = null;
		try {
			 pagelog = logServiceImpl.pageLog(pageIndex, pageNumber);
			 pagelog.setUrl(url);
		} catch (Exception e) {
			response.sendRedirect(Info.PATH);
		}
		return pagelog;
	}
}

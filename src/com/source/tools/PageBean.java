package com.source.tools;

import java.util.List;

public class PageBean<T> {
	private int pageIndex;  //当前页起始位置
	private int pageNumber;   //每页记录数（当前页面数）
	private int count;    // 总记录数
	private List<T> pageData;   //每页保存记录数
	private String url;   //保存查询条件
	
	private int pageCountNumber;

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageCount() {
		int pageCount=count / pageNumber;
		return count%pageNumber == 0? pageCount:pageCount+1;
	}
	public int getPageCountNumber() {
		return pageCountNumber;
	}
	public void setPageCountNumber(int pageCountNumber) {
		this.pageCountNumber = pageCountNumber;
	}

}

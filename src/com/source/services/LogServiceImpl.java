package com.source.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.LogMapper;
import com.source.interfaces.IService;
import com.source.model.Log;
import com.source.tools.PageBean;

@Service
public class LogServiceImpl implements IService {
	
	@Resource
	private LogMapper logMapper;
	

	@Override
	public String deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Object object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Object object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public PageBean<Log> pageLog(int pageIndex,int pageNumber) throws Exception{
		List<Log> pageLogList = logMapper.pageLog((pageIndex-1)*pageNumber,pageNumber);
		PageBean<Log> pageLog = new PageBean<Log>();
		pageLog.setPageData(pageLogList);
		int count = logMapper.pageCount();
		pageLog.setCount(count);
		int pageCountNumber ;
		if (count % pageNumber == 0) {
			pageCountNumber = count / pageNumber;
		} else {
			pageCountNumber = count / pageNumber + 1;
		}
		pageLog.setPageCountNumber(pageCountNumber);
		pageLog.setPageIndex(pageIndex);
		pageLog.setPageNumber(pageNumber);
		return pageLog;
		
	}

}

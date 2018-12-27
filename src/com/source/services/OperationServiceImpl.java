package com.source.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.OperationMapper;
import com.source.interfaces.IService;
import com.source.model.Operation;
import com.source.model.Role;
import com.source.tools.PageBean;

@Service
public class OperationServiceImpl implements IService {
	
	@Resource
	private OperationMapper operationMapper;

	@Override
	public String deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Object object) throws Exception {
		return operationMapper.updateOperation((Operation)object);
	}

	@Override
	public int add(Object object) throws Exception {
		// TODO Auto-generated method stub
		return operationMapper.addOperation((Operation)object);
	}
	
	public List<Operation> findAll() throws Exception{
		return operationMapper.findAll();
	}
	
	public Operation findById(int id) throws Exception {
		return operationMapper.findById(id);
	}

	@Override
	public int delById(int id) throws Exception {
		return operationMapper.deleteById(id);
	}
	
	public int pageCount() {
		return operationMapper.pageCount();
	}
	
	public PageBean<Operation> pageOperation (int pageIndex,int pageNumber) throws Exception {
		PageBean<Operation> pageListOperation = new PageBean<Operation>();
		List<Operation> operationList = operationMapper.pageOperation((pageIndex-1)*pageNumber,pageNumber);
		pageListOperation.setPageData(operationList);
		int count = pageCount();
		pageListOperation.setCount(count);
		int pageCountNumber ;
		if (count % pageNumber == 0) {
			pageCountNumber = count / pageNumber;
		} else {
			pageCountNumber = count / pageNumber + 1;
		}
		pageListOperation.setPageIndex(pageIndex);
		pageListOperation.setPageNumber(pageNumber);
		pageListOperation.setPageCountNumber(pageCountNumber);
		return pageListOperation;
		
	}

}

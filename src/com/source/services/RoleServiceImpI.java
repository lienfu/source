package com.source.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.MenuMapper;
import com.source.dao.OperationMapper;
import com.source.dao.RoleMapper;
import com.source.interfaces.IService;
import com.source.model.Role;
import com.source.tools.PageBean;
 

@Service
public class RoleServiceImpI implements IService {
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private MenuMapper menuMapper;
	
	@Resource 
	private OperationMapper operationMapper;
	 

	@Override
	public String deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Object object) throws Exception {
		return roleMapper.updateRole((Role)object);
	}

	@Override
	public int add(Object object) throws Exception {
		return roleMapper.addRole((Role)object);
	}
	
	public int pageCount() {
		return roleMapper.pageCount();
	}
	
	public PageBean<Role> pageRole (int pageIndex,int pageNumber) throws Exception {
			
			PageBean<Role> pageListRole = new PageBean<Role>();
			List<Role> roleList= roleMapper.pageRole((pageIndex-1)*pageNumber,pageNumber);
			List<Role> pageRole = new ArrayList<Role>();
			
			StringBuffer sb_menus = new StringBuffer();
			StringBuffer sb_operations = new StringBuffer();
			for (Role role : roleList) {
				if(role.getMenus().equals("")|| role.getMenus()==null) {
					role.setMenus("无");
				}else {
					String[] menu_ids = role.getMenus().split(",");
					for (String id : menu_ids) {
						String menu_name = menuMapper.findById(Integer.parseInt(id)).getMenu_name();
						sb_menus.append(menu_name+",");
					}
					role.setMenus(sb_menus.toString().substring(0,sb_menus.toString().length() - 1));
					sb_menus.setLength(0);
				}
				
				if(role.getOperations().equals("")||role.getOperations()==null) {
					role.setOperations("无");
				}else {
					String[] operations_ids = role.getOperations().split(",");
					for (String id : operations_ids) {
						String operation_name = operationMapper.findById(Integer.parseInt(id)).getOperation_name();
						sb_operations.append(operation_name+",");
					}
					role.setOperations(sb_operations.toString().substring(0,sb_operations.toString().length()-1));
					sb_operations.setLength(0);
				}
				pageRole.add(role);
				
			}
			
			pageListRole.setPageData(pageRole);
			
			int count = pageCount();
			pageListRole.setCount(count);
			int pageCountNumber ;
			if (count % pageNumber == 0) {
				pageCountNumber = count / pageNumber;
			} else {
				pageCountNumber = count / pageNumber + 1;
			}
			pageListRole.setPageCountNumber(pageCountNumber);
			pageListRole.setPageIndex(pageIndex);
			pageListRole.setPageNumber(pageNumber);
			return pageListRole;
		}

	@Override
	public int delById(int id) throws Exception {
		return roleMapper.deleteById(id);
	}
	
	
	public Role findById(int id) throws Exception {
		Role role = roleMapper.findById(id);
		return role;
	}
	
	
	public List<Role> findAll()throws Exception {
		return roleMapper.findAll();
	}
	
	
	
	
	
		
	

	
}

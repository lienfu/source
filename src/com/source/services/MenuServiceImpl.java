package com.source.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.components.Info;
import com.source.components.SessionUser;
import com.source.dao.MenuMapper;
import com.source.dao.RoleMapper;
import com.source.interfaces.IService;
import com.source.model.Menu;
import com.source.model.Role;
import com.source.model.User;
import com.source.tools.PageBean;

@Service("IService")
public class MenuServiceImpl implements IService {
	
	@Resource
	private MenuMapper menuMapper;
	
	@Resource 
	private RoleMapper roleMapper;

	@Override
	public String deleteById(int id) throws Exception {
		int count = menuMapper.findUpMenuId(String.valueOf(id));
		User user = SessionUser.getUser();
		Role role = roleMapper.findById(Integer.parseInt(user.getRole_id()));
		String role_menus = role.getMenus();
		int contains = role_menus.indexOf(String.valueOf(id));
		if(count>0 ) {
			return Info.DEl;
		}
		if(contains!=-1) {
			return Info.delMenuRole;
		}
		 menuMapper.deleteById(id);
		 return Info.RESULT;
	}

	@Override
	public int update(Object object) throws Exception {
		// TODO Auto-generated method stub
		Menu menu = (Menu)object;
		menu.setUp_menu_id(menuMapper.findById(menu.getId()).getUp_menu_id());
		return menuMapper.updateMenu((Menu)object);
	}

	@Override
	public int add(Object object) throws Exception {
		Menu menu = (Menu)object;
		if(menu.getUp_menu_id().equals("0")) {
			menu.setLevel(1);
		}else {
			int level = menuMapper.findById(Integer.parseInt(menu.getUp_menu_id())).getLevel()+1;
			menu.setLevel(level);
		}
		return menuMapper.addMenu((Menu)object);
	}
	
	public int pageCount() {
		return menuMapper.pageCount();
	}
	
	public PageBean<Menu> pageMenu (int pageIndex,int pageNumber) throws Exception {
		
		PageBean<Menu> pageListMenu = new PageBean<Menu>();
		List<Map> pageMenu= menuMapper.pageMenu((pageIndex-1)*pageNumber,pageNumber);
		List<Menu> menuList = new ArrayList<Menu>();
				
		for (int i=0;i<pageMenu.size();i++) {
			Menu menu = new Menu();
			String up_menu_id = (String) pageMenu.get(i).get("up_menu_id");
			if(up_menu_id.equals("0")) {
				menu.setUp_menu_id("根目录");
			}else {
				menu.setUp_menu_id(menuMapper.findById(Integer.parseInt(up_menu_id)).getMenu_name());
			}
			menu.setId((int)pageMenu.get(i).get("id"));
			menu.setMenu_name((String) pageMenu.get(i).get("menu_name"));
			menu.setLevel((int)pageMenu.get(i).get("level"));
			menu.setPath((String) pageMenu.get(i).get("path"));
			menuList.add(menu);
		}
		
		pageListMenu.setPageData(menuList);
		
		int count = pageCount();
		pageListMenu.setCount(count);
		int pageCountNumber ;
		if (count % pageNumber == 0) {
			pageCountNumber = count / pageNumber;
		} else {
			pageCountNumber = count / pageNumber + 1;
		}
		pageListMenu.setPageCountNumber(pageCountNumber);
		pageListMenu.setPageIndex(pageIndex);
		pageListMenu.setPageNumber(pageNumber);
		return pageListMenu;
	}
	
	public Menu findById(int id) throws Exception{
		Menu menu = menuMapper.findById(id);
		if(menu.getUp_menu_id().equals("0")) {
			menu.setUp_menu_id("根目录");
		}else {
			Menu upMenu = menuMapper.findById(Integer.parseInt(menuMapper.findById(id).getUp_menu_id()));
			menu.setUp_menu_id(upMenu.getMenu_name());
		}
		return menu;
	}
	
	public List<Menu> findByLevel() throws Exception{
		return menuMapper.findByLevel();
	}
	
	
	public List<Menu> findAll() throws Exception{
		
		return menuMapper.findAll();
	}

	@Override
	public int delById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Menu findByUser(int id) throws Exception{
		Menu menu = menuMapper.findById(id);
		return menu;
	}
	
	

}

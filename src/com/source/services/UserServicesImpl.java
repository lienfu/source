package com.source.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.source.dao.AreaMapper;
import com.source.dao.RoleMapper;
import com.source.dao.UserMapper;
import com.source.interfaces.IService;
import com.source.model.Role;
import com.source.model.User;
import com.source.tools.PageBean;

@Service
public class UserServicesImpl implements IService{
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private AreaMapper areaMapper;
	@Override
	public String deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Object object) throws Exception {
		// TODO Auto-generated method stub
		User user = (User)object;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		user.setUpdate_time(df.format(new Date()));
		return userMapper.updateUser(user);
	}

	@Override
	public int add(Object object) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式 yyyy-MM-dd HH:mm:ss
	//	System.out.println("new Date()=" + new Date());  //new Date()=Fri Apr 20 16:23:47 CST 201
	//	System.out.println(df.format(new Date()));   //2018-04-20 16:23:47
		User user = (User)object;
		user.setCreate_time(df.format(new Date()));
		return userMapper.addUser(user);
	}

	@Override
	public int delById(int id) throws Exception {
		return userMapper.deleteById(id);
	}
	
	public PageBean<User> pageUser(int pageIndex,int pageNumber) throws Exception{
		PageBean<User> pageUser = new PageBean<User>();
		List<User> userList = userMapper.pageUser((pageIndex-1)*pageNumber, pageNumber);
		List<User> pageUserList = new ArrayList<User>();
		for (User user : userList) {
			if(user.getRole_id()==null || user.getRole_id().equals(" ") ||user.getRole_id().equals("null") ||user.getRole_id().equals("0")) {
				user.setRole_id("无");
			}else {
				user.setRole_id(roleMapper.findById(Integer.parseInt(user.getRole_id())).getRole_name());
			}
			
			if(user.getArea_id()==null ||user.getArea_id().equals(" ") || user.getArea_id().equals("null") || user.getArea_id().equals("0")) {
				user.setArea_id("无");
			}else {
				user.setArea_id(areaMapper.findById(Integer.parseInt(user.getArea_id())).getArea_name());
			}
			if(user.getStatus().equals("0")) {
				user.setStatus("暂停中");
			}else {
				user.setStatus("启用中");
			}
			
			if(user.getUpdate_time()==null || user.getUpdate_time().equals("") || user.getUpdate_time().equals("null")) {
				user.setUpdate_time("无");
			}
			pageUserList.add(user);
	
		}
		
		int count = pageCount();
		pageUser.setCount(count);
		pageUser.setPageData(pageUserList);
		int pageCountNumber ;
		if (count % pageNumber == 0) {
			pageCountNumber = count / pageNumber;
		} else {
			pageCountNumber = count / pageNumber + 1;
		}
		pageUser.setPageIndex(pageIndex);
		pageUser.setPageNumber(pageNumber);
		pageUser.setPageCountNumber(pageCountNumber);
		
		return pageUser;
		
	}

	private int pageCount() {
		// TODO Auto-generated method stub
		return userMapper.pageCount();
	}
	
	
	public List findById(int id) {
		User user = new User();
		user = userMapper.findById(id);
	//	user.setRole_id(roleMapper.findById(Integer.parseInt(user.getRole_id())).getRole_name());
		user.setArea_id(areaMapper.findById(Integer.parseInt(user.getArea_id())).getArea_name());
		if(user.getUpdate_time()==null) {
			user.setUpdate_time("无");
		}
		
		List list = new ArrayList();
		list.add(user);
		
		List<Role> roleList = roleMapper.findAll();
		
		if(Integer.parseInt(user.getRole_id())==0) {
			
		}else {
			
			int role_id = Integer.parseInt(user.getRole_id());
			int index=0;
			for(int i=0;i<roleList.size();i++) {
				if(roleList.get(i).getId()==role_id) {
					index=i;
					break;
				}
			}
			roleList.remove(index);
			
			Role role = roleMapper.findById(role_id);
			
			roleList.add(role);
		}
		list.add(roleList);
		return list;
	}
	
	public User userLogin(String account, String pwd) throws Exception{
		return userMapper.userLogin(account, pwd);
	}
	
	public User IfUserLogin(int id,String account) {
		return userMapper.IfUserLogin( id,account);
	}
	
	public int updateUserPw(String pwd,int id) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String updateTime = df.format(new Date());
		return userMapper.updateUserPw(pwd,updateTime, id);
	}
	
	
	public List<User> FindByAreaId(String id) throws Exception{
		return userMapper.FindByAreaId(id);
	}
	
	public User FindByAccout(String account) throws Exception{
		return userMapper.FindByAccout(account);
	}

}

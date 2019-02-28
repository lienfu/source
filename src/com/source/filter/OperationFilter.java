package com.source.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.source.components.SessionUser;
import com.source.model.Operation;
import com.source.model.Role;
import com.source.model.User;
import com.source.services.OperationServiceImpl;
import com.source.services.RoleServiceImpI;



/**
 * 
 *如果role表中有OperationId，则前台请求的url
 *和Operation path 做比较，若是前台的url和path一样，则提示没有权限
 *若是前台的url和path不一样则放行
 */
public class OperationFilter implements Filter {
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	@Autowired
	private RoleServiceImpI roleServiceImpI;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setContentType("text/html;charset=UTF-8");
		
		
		//获取前台传过来的url，并获取含参数的url ？前面的字符串
		//	System.out.println(request.getServletPath()); /admin/index.jsp
		//	System.out.println(request.getRequestURI());  /web_frame/admin/index.jsp
		String requestUrl = null;
		if(request.getServletPath().indexOf("?")!=-1) {
			requestUrl = request.getServletPath().substring(0, request.getServletPath().indexOf("?"));
		}else {
			requestUrl = request.getServletPath();
		}
		
		//定义与role关联的operation的path的集合
		List<String> operationPathList = new ArrayList<String>();
		
		//获取tb_operation表中通过id与角色表关联的path
		User user = SessionUser.getUser();
		String role_id = user.getRole_id();
		//获取用户user关联的roleId的role对象
		try {
			Role role = roleServiceImpI.findById(Integer.parseInt(role_id));
			//获取与role 关联的OperationId
			String userOperationIdSb = role.getOperations();
			if(userOperationIdSb==null || userOperationIdSb.equals("") ) {
				arg2.doFilter(arg0, arg1);
			}else {
				//获取关联的OperationId path的集合
				if(userOperationIdSb.toString().indexOf(",")!=-1) {
					String[] userOperationIdArgs = userOperationIdSb.toString().split(",");
					for(int i=0;i<userOperationIdArgs.length;i++) {
						Operation operation = operationServiceImpl.findById(Integer.parseInt(userOperationIdArgs[i]));
						operationPathList.add(operation.getPath());
					}
				}else {
					Operation operation = operationServiceImpl.findById(Integer.parseInt(userOperationIdSb.toString()));
					operationPathList.add(operation.getPath());
				}
				
				System.out.println("===="+operationPathList.indexOf(requestUrl));
				if(operationPathList.indexOf(requestUrl)!=-1) {
					request.setAttribute("msg", "您没有权限操作，请联系管理员");
					request.getRequestDispatcher("/admin/403.html").forward(request, response);
				}else {
					arg2.doFilter(arg0, arg1);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// 自动加载spring 标签
	   SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, arg0.getServletContext());

	}

}

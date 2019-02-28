package com.source.filter;

import java.io.IOException;

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
import com.source.model.User;
import com.source.services.OperationServiceImpl;
import com.source.services.RoleServiceImpI;

public class RoleFilter implements Filter {
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	@Autowired
	private RoleServiceImpI roleServiceImpI;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setContentType("text/html;charset=UTF-8");
		
		User user = SessionUser.getUser();
		String role_id = user.getRole_id();
		if(role_id == null ||role_id.isEmpty() || role_id=="") {
			request.setAttribute("msg", "系统无法确定您的角色，请联系管理员");
			request.getRequestDispatcher("/admin/403.html").forward(request, response);
		}
		
		
		chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 自动加载spring 标签
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

}

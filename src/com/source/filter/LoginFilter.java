package com.source.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.source.components.JwtUtil;
import com.source.components.ReadCookies;
import com.source.components.SessionUser;
import com.source.model.User;
import com.source.services.UserServicesImpl;

import io.jsonwebtoken.Claims;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	
	private UserServicesImpl userServices;

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Cookie> cookieMap = ReadCookies.ReadCookie(request);
		String tokens = "";
		 if(cookieMap.containsKey("tokens")){
			//cookieName 为cookie名称
			Cookie cookie = (Cookie)cookieMap.get("tokens");
			 tokens = cookie.getValue();
			}
		 String userAccount = "";
		 int userId =0;
		 try {
			Claims claims = new JwtUtil().parseJWT(tokens);
			 userAccount = (String) claims.get("sub");
			 //获取标识
			 userId = Integer.parseInt((String)claims.get("jti"));
			User user =userServices.IfUserLogin(userId, userAccount);
			if(user == null){
				response.sendRedirect(request.getContextPath()+"/login.html");
			} else {
				SessionUser.setUser(user);
				chain.doFilter(arg0, arg1);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/login.html");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		 ServletContext sc = fConfig.getServletContext();
		 XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
		 userServices =  cxt.getBean(UserServicesImpl.class);
	}

}

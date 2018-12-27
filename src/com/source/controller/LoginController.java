package com.source.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.source.components.IoInfo;
import com.source.components.JwtUtil;
import com.source.components.ReadCookies;
import com.source.model.User;
import com.source.services.UserServicesImpl;
import com.source.tools.JacksonJsonUtil;
import com.source.tools.MD5Util;
import com.source.tools.VerifyCode;

import io.jsonwebtoken.Claims;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserServicesImpl userServices;
	
	@RequestMapping("/getSysInfo")
	@ResponseBody  
	public IoInfo getSysInfo() {
		return new IoInfo();
	}
	
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//将一副图片加载到内存中
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage bufferedImage = verifyCode.getImage();
		request.getSession().setAttribute("verifyCode", verifyCode.getText());
		VerifyCode.output(bufferedImage, response.getOutputStream());
	}
	/*
	 * 给客户段传过去的为COOIKE 客户端存储COOIKE，用cookie和服务器服务器的session 做判断
	@RequestMapping("/doLogin")
	public void doLogin(
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws ServletException, IOException {
		String pwd = MD5Util.MD5(password);
		try {
			if(!request.getSession().getAttribute("verifyCode").toString().equalsIgnoreCase(verifyCode)) {
		//		E10ADC3949BA59ABBE56E057F20F883E
				request.getSession().setAttribute("msg", "验证码错误");
	//			Cookie cookie = new Cookie("msg", String.valueOf("验证码错误"));
				Cookie cookie = new Cookie("msg", URLEncoder.encode("验证码错误", "UTF-8"));
				cookie.setPath("/");
				cookie.setMaxAge(3); 
				response.addCookie(cookie); 

				response.sendRedirect(request.getContextPath()+"/login.html");
				return;	
			}
			User user = userServices.userLogin(account, pwd);
			//model.addAttribute("user",user);
			
			String s = JacksonJsonUtil.beanToJson(user);
			Cookie cookies = new Cookie("user",URLEncoder.encode(s, "UTF-8"));
			cookies.setPath("/");
			response.addCookie(cookies); 
		} catch (Exception e) {
			request.setAttribute("msg","用户名或密码错误");
			request.getRequestDispatcher("/login.html").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/index.html");
		return;
	}
	*/
	
	@RequestMapping("/userlogin")
	@ResponseBody
	public String userlogin(HttpServletRequest request) throws Exception {
		Map<String,Cookie> cookieMap = ReadCookies.ReadCookie(request);
		String tokens = "";
		 if(cookieMap.containsKey("tokens")){
			//cookieName 为cookie名称
			Cookie cookie = (Cookie)cookieMap.get("tokens");
			 tokens = cookie.getValue();
			}
		 String userAccount = "";
		 int userId = 0;
		 try {
			Claims claims = new JwtUtil().parseJWT(tokens);
			 userAccount = (String) claims.get("sub");
			 //获取标识
			 userId = Integer.parseInt((String)claims.get("jti"));
		} catch (Exception e) {
			
		}
		 
		String nick_name = userServices.IfUserLogin(userId, userAccount).getNick_name();
		return JacksonJsonUtil.beanToJson(nick_name);
	}
	
	@RequestMapping("/findUser")
	@ResponseBody
	public User findUser(HttpServletRequest request) throws Exception {
		Map<String,Cookie> cookieMap = ReadCookies.ReadCookie(request);
		String tokens = "";
		 if(cookieMap.containsKey("tokens")){
			//cookieName 为cookie名称
			Cookie cookie = (Cookie)cookieMap.get("tokens");
			 tokens = cookie.getValue();
			}
		 Claims claims = new JwtUtil().parseJWT(tokens);
		 String  userAccount = (String) claims.get("sub");
		 int userId =  userId = Integer.parseInt((String)claims.get("jti"));
		 User user = userServices.IfUserLogin(userId, userAccount);
		 return user;
	}
	
	@RequestMapping("/doLogin")
	public void doLogin(
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws ServletException, IOException {
		String pwd = MD5Util.MD5(password);
		try {
			if(!request.getSession().getAttribute("verifyCode").toString().equalsIgnoreCase(verifyCode)) {
				Cookie cookie = new Cookie("msg", URLEncoder.encode("验证码错误", "UTF-8"));
				cookie.setPath("/");
				cookie.setMaxAge(3); 
				response.addCookie(cookie); 
				response.sendRedirect(request.getContextPath()+"/login.html");
				return;	
			}
			User user = userServices.userLogin(account, pwd);
			long ttlMillis= 1000*60*30;
			
			String token  = new JwtUtil().createJWT(String.valueOf(user.getId()), user.getAccount(), ttlMillis);
			Cookie cookies = new Cookie("tokens",token);
			cookies.setPath("/");
			response.addCookie(cookies); 
			
		} catch (Exception e) {
			Cookie cookie = new Cookie("msg", URLEncoder.encode("账号或密码错误", "UTF-8"));
			cookie.setPath("/");
			cookie.setMaxAge(3); 
			response.addCookie(cookie); 
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/index.html");
		return;
	}
	
	@RequestMapping("/signOut")
	@ResponseBody
	public String signOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		long ttlMillis= 1000*3*1;
		String token  = new JwtUtil().createJWT(String.valueOf(0), "0", ttlMillis);
		Cookie cookies = new Cookie("tokens",token);
		cookies.setPath("/");
		response.addCookie(cookies); 
//		response.sendRedirect(request.getContextPath()+"/login.html");
		return JacksonJsonUtil.beanToJson("");
		
	}
}

package com.source.components;

import org.springframework.stereotype.Component;

import com.source.model.User;


@Component
public class SessionUser {

	//用户存储session中的user
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SessionUser.user = user;
	}
	
}

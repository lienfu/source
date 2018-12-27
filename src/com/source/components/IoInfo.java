package com.source.components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;



public class IoInfo {
	Properties props = System.getProperties();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 操作系统的名称
	private String os_name;
	// 操作系统的架构
	private String os_arch;
	// 操作系统版本
	private String os_version;
	// java版本
	private String java_version;
	// 登陆时间
	private String login_time;

	public String getOs_name() {
		return os_name;
	}

	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}

	public String getOs_arch() {
		return os_arch;
	}

	public void setOs_arch(String os_arch) {
		this.os_arch = os_arch;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getJava_version() {
		return java_version;
	}

	public void setJava_version(String java_version) {
		this.java_version = java_version;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public IoInfo() {
		this.os_name = props.getProperty("os.name");
		this.os_arch = props.getProperty("os.arch");
		this.os_version = props.getProperty("os.version");
		this.java_version = props.getProperty("java.version");
		this.login_time = df.format(new Date());
	}
}

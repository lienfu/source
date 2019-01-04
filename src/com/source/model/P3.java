package com.source.model;

//p3的属性为p1的主键
public class P3 {

	private Integer id;
	private String address;
	private P1 p1_id;
	
	public P1 getP1_id() {
		return p1_id;
	}
	public void setP1_id(P1 p1_id) {
		this.p1_id = p1_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}

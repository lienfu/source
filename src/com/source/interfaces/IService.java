package com.source.interfaces;



public interface IService {
	
	public String deleteById(int id) throws Exception;
	public int update(Object object) throws Exception;
	public int add(Object object) throws Exception;
	
	public int delById(int id) throws Exception;
}

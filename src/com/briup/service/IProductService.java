package com.briup.service;

import com.briup.bean.Product;

public interface IProductService {
	
	public Product add(Product product);
	
	public void remove(long id);
	
	public Product modify(Product product, long id);
	
	public Product query(String name);
	
}

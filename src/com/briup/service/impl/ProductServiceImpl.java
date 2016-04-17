package com.briup.service.impl;

import com.briup.bean.Product;
import com.briup.dao.ProductDao;
import com.briup.service.IProductService;

public class ProductServiceImpl implements IProductService{
	private ProductDao productDao = null;

	public ProductServiceImpl() {
		productDao = new ProductDao();
	}
	
	@Override
	public Product add(Product product) {
		productDao.save(product);
		
		return product;
	}

	@Override
	public void remove(long id) {
		productDao.deleteById(id);
	}

	@Override
	public Product modify(Product product, long id) {
		productDao.update(product, id);
		
		return product;
	}

	@Override
	public Product query(String name) {
		Product product = null;
		product = productDao.findByName(name);
		
		return product;
	}
	
}

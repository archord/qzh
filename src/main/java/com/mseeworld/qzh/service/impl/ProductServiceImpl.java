package com.mseeworld.qzh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mseeworld.qzh.dao.ProductDAO;
import com.mseeworld.qzh.model.Product;
import com.mseeworld.qzh.model.AUser;
import com.mseeworld.qzh.service.ProductService;

public class ProductServiceImpl implements ProductService {
	ProductDAO productDao;
	
	public ProductDAO getProductDao() {
		return productDao;
	}
	@Resource
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}

	public Product add(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	public List<Product> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return productDao.findByKeyword(keyword);
	}

	public boolean modify(Product product) {
		// TODO Auto-generated method stub
		return productDao.update(product);
	}

	public boolean remove(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		for(int i=0;i<ids.length;i++){
			flag = productDao.delete(Integer.parseInt(ids[i]));
		}
		return flag;
	}
	public List<Product> findAll(int start, int limit) {
		// TODO Auto-generated method stub
		return productDao.findAll(start, limit);
	}
	public long count() {
		// TODO Auto-generated method stub
		return findAll().size();
	}
	

	

}

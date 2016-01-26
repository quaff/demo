package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.ironrhino.core.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Product;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductManager productManager;

	@Override
	@Transactional(readOnly = true)
	public List<Product> list() {
		List<com.demo.model.Product> list = productManager.findAll(Order.desc("quantity"), Order.asc("name"));
		List<Product> result = new ArrayList<>(list.size());
		for (com.demo.model.Product p : list) {
			Product product = new Product();
			BeanUtils.copyProperties(p, product);
			result.add(product);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Product getById(String id) {
		com.demo.model.Product p = productManager.get(id);
		if (p == null)
			return null;
		Product product = new Product();
		BeanUtils.copyProperties(p, product);
		return product;
	}

}

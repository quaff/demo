package com.demo.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.ironrhino.core.util.BeanUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.ProductService;
import com.demo.domain.Product;
import com.demo.service.ProductManager;

@RestController
public class ProductController implements ProductService {

	@Autowired
	Logger logger;

	@Autowired
	ProductManager productManager;

	@Override
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
	public Product getById(@PathVariable String id) {
		logger.info("try find product {}", id);
		com.demo.model.Product p = productManager.get(id);
		if (p == null)
			return null;
		Product product = new Product();
		BeanUtils.copyProperties(p, product);
		return product;
	}

}

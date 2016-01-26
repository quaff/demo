package com.demo.service;

import java.util.List;

import org.ironrhino.core.remoting.Remoting;

import com.demo.domain.Product;

@Remoting
public interface ProductService {

	public List<Product> list();

	public Product getById(String id);

}

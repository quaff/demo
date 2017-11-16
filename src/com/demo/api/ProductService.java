package com.demo.api;

import java.util.List;

import org.ironrhino.rest.client.RestApi;
import org.ironrhino.rest.doc.annotation.Api;
import org.ironrhino.rest.doc.annotation.ApiModule;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.domain.Product;

@RestApi
@RequestMapping("/product")
@ApiModule("产品服务")
public interface ProductService {

	@Api("获取所有产品")
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> list();

	@Api("根据ID获取产品")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getById(@PathVariable String id);

}

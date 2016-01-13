package com.demo.service;

import org.ironrhino.core.service.BaseManagerImpl;
import org.ironrhino.core.struts.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Product;

@Component
public class ProductManagerImpl extends BaseManagerImpl<Product> implements ProductManager {

	@Override
	@Transactional
	public void save(Product product) {
		if (product.isNew() && product.isFeatured()) { // 纯演示用,这个规则本应该放在实体类里面
			ValidationException ve = new ValidationException();
			ve.addFieldError("product.featured", "新品不能设置为精选");
			throw ve;
		}
		super.save(product);
	}

}

package com.demo.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.ironrhino.core.hibernate.CriteriaState;
import org.ironrhino.core.struts.EntityAction;

import com.demo.model.Product;

public class ProductAction extends EntityAction<Product> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void prepare(DetachedCriteria dc, CriteriaState criteriaState) {
		String maxQuantity = ServletActionContext.getRequest().getParameter("maxQuantity");
		if (StringUtils.isNumeric(maxQuantity))
			dc.add(Restrictions.le("quantity", Integer.valueOf(maxQuantity)));
	}

}

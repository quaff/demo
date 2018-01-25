package com.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import org.ironrhino.core.hibernate.CreationUser;
import org.ironrhino.core.hibernate.UpdateUser;
import org.ironrhino.core.hibernate.convert.StringSetConverter;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.metadata.Owner;
import org.ironrhino.core.metadata.Readonly;
import org.ironrhino.core.metadata.Richtable;
import org.ironrhino.core.metadata.UiConfig;
import org.ironrhino.core.model.BaseEntity;
import org.ironrhino.core.search.elasticsearch.annotations.Searchable;
import org.ironrhino.core.search.elasticsearch.annotations.SearchableProperty;
import org.ironrhino.core.struts.ValidationException;

import com.demo.enums.Status;

import lombok.Getter;
import lombok.Setter;

@AutoConfig
@Entity
@Richtable(order = "quantity desc,name asc")
@Owner(propertyName = "createUser", isolate = true)
@Searchable
@Getter
@Setter
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NaturalId(mutable = true)
	@UiConfig(width = "150px", searchable = true)
	@SearchableProperty
	private String name;

	@UiConfig(width = "150px")
	@ManyToOne
	private Category category;

	@UiConfig(width = "80px", template = "${value}个")
	@Max(100)
	@Min(0)
	private int quantity;

	@UiConfig(width = "100px", inputTemplate = "<span class='input-append'><@s.textfield theme='simple' type='number' name='product.price'/><span class='add-on'>元</span></span>")
	private BigDecimal price;

	@UiConfig(width = "80px", readonly = @Readonly(expression = "value"))
	private boolean featured;

	@UiConfig(width = "80px")
	private Status status;

	@UiConfig(width = "80px", type = "dictionary", templateName = "product_type")
	private String type;

	@Convert(converter = StringSetConverter.class)
	@UiConfig(width = "80px", type = "dictionary", templateName = "product_tag")
	private Set<String> tags = new LinkedHashSet<>();

	@UiConfig(type = "textarea", viewTemplate = "<#if entity.quantity==0>缺货<#else>${value}</#if>")
	private String description;

	@ElementCollection
	@OrderColumn
	private List<Photo> photos;

	@UiConfig(hidden = true)
	@Column(updatable = false)
	@CreationTimestamp
	private Date createDate;

	@UiConfig(hidden = true)
	@Column(insertable = false)
	@UpdateTimestamp
	private Date modifyDate;

	@UiConfig(hidden = true)
	@Column(updatable = false)
	@CreationUser
	private String createUser;

	@UiConfig(hidden = true)
	@Column(insertable = false)
	@UpdateUser
	private String modifyUser;

	@Version
	private int verison = -1;

	@PreUpdate
	@PrePersist
	public void validate() {
		if (price == null) {
			ValidationException ve = new ValidationException();
			ve.addActionError("请输入价格");
			throw ve;
		}
		if (price.doubleValue() <= 0) {
			ValidationException ve = new ValidationException();
			ve.addFieldError("product.price", "价格必须大于0");
			throw ve;
		}
		if (price.doubleValue() > 1000) {
			ValidationException ve = new ValidationException();
			ve.addFieldError("product.price", "价格最高不能超过1000");
			throw ve;
		}
	}

}

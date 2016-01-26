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

import org.hibernate.annotations.NaturalId;
import org.ironrhino.core.hibernate.convert.StringSetConverter;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.metadata.Owner;
import org.ironrhino.core.metadata.Readonly;
import org.ironrhino.core.metadata.Richtable;
import org.ironrhino.core.metadata.UiConfig;
import org.ironrhino.core.model.BaseEntity;
import org.ironrhino.core.model.Recordable;
import org.ironrhino.core.search.elasticsearch.annotations.Searchable;
import org.ironrhino.core.search.elasticsearch.annotations.SearchableProperty;
import org.ironrhino.core.struts.ValidationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.enums.Status;

@AutoConfig
@Entity
@Richtable(order = "quantity desc,name asc")
@Owner(propertyName = "createUser", isolate = true)
@Searchable
public class Product extends BaseEntity implements Recordable<UserDetails> {

	private static final long serialVersionUID = 1L;

	@NaturalId(mutable = true)
	@UiConfig(width = "150px", searchable = true)
	@SearchableProperty
	private String name;

	@UiConfig(width = "150px")
	@ManyToOne(optional = false)
	private Category category;

	@UiConfig(width = "80px", template = "${value}个")
	@Max(100)
	@Min(0)
	private int quantity;

	@UiConfig(width = "100px", inputTemplate = "<span class='input-append'><@s.textfield theme='simple' type='number' name='product.price'/><span class='add-on'>元</span></span>")
	private BigDecimal price;

	@UiConfig(width = "80px", readonly = @Readonly(expression = "value") )
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
	private Date createDate = new Date();

	@UiConfig(hidden = true)
	@Column(insertable = false)
	private Date modifyDate;

	@UiConfig(hidden = true)
	@Column(updatable = false)
	private String createUser;

	@UiConfig(hidden = true)
	@Column(insertable = false)
	private String modifyUser;

	@Version
	private int verison = -1;

	public int getVerison() {
		return verison;
	}

	public void setVerison(int verison) {
		this.verison = verison;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public Date getModifyDate() {
		return modifyDate;
	}

	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public void setCreateUserDetails(UserDetails user) {
		if (user != null)
			createUser = user.getUsername();
	}

	@Override
	public void setModifyUserDetails(UserDetails user) {
		if (user != null)
			modifyUser = user.getUsername();
	}

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

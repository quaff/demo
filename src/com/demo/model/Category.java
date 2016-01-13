package com.demo.model;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.metadata.UiConfig;
import org.ironrhino.core.model.BaseEntity;
import org.ironrhino.core.model.Ordered;
import org.ironrhino.core.search.elasticsearch.annotations.Searchable;
import org.ironrhino.core.search.elasticsearch.annotations.SearchableProperty;

@AutoConfig
@Entity
@Searchable
public class Category extends BaseEntity implements Ordered<Category> {

	private static final long serialVersionUID = 1L;

	@NaturalId(mutable = true)
	@UiConfig(width = "150px", searchable = true)
	@SearchableProperty
	private String name;

	@UiConfig(width = "80px")
	private int displayOrder;

	@UiConfig(type = "textarea")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Category other) {
		int i = this.displayOrder - other.displayOrder;
		return i != 0 ? i : this.name.compareTo(other.name);
	}

}

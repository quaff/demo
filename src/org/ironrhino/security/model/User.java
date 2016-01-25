package org.ironrhino.security.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ironrhino.core.aop.PublishAware;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.metadata.Richtable;
import org.ironrhino.core.search.elasticsearch.annotations.Searchable;

import com.demo.model.Department;

@PublishAware
@AutoConfig
@Searchable
@Entity
@Table(name = "user")
@Richtable(order = "username asc")
public class User extends BaseUser {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}

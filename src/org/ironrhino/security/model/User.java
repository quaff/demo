package org.ironrhino.security.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demo.model.Department;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseUser {

	private static final long serialVersionUID = 7307419528067871480L;

	@ManyToOne
	private Department department;

}

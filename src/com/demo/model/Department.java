package com.demo.model;

import javax.persistence.Entity;

import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.model.BaseTreeableEntity;

@AutoConfig
@Entity
public class Department extends BaseTreeableEntity<Department> {

	private static final long serialVersionUID = 1L;

}

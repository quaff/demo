package com.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import com.demo.enums.Status;

import lombok.Data;

@Data
public final class Product implements Serializable {

	private static final long serialVersionUID = 1281411226332347647L;

	private String id;

	private String name;

	private int quantity;

	private BigDecimal price;

	private boolean featured;

	private Status status;

	private Set<String> tags = new LinkedHashSet<>();

	private String description;

}

package com.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.ironrhino.core.metadata.UiConfig;

import lombok.Data;

@Embeddable
@Data
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;

	@UiConfig(width = "200px", cssClass = "input-medium")
	private String title;

	@UiConfig(cssClass = "input-medium")
	private String url;

}

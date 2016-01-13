package com.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.ironrhino.core.metadata.UiConfig;

@Embeddable
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;

	@UiConfig(width = "200px", cssClass = "input-medium")
	private String title;

	@UiConfig(cssClass = "input-medium")
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

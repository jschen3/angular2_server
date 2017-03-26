package com.jimmy.angular2.web.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PageObject {

	private String componentType;
	private String baseUrl;
	private List<PageObject> childPageObjects;

	private String content;
	private String style;
	private String linkUrl;

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<PageObject> getChildPageObjects() {
		return childPageObjects;
	}

	public void setChildPageObjects(List<PageObject> childPageObjects) {
		this.childPageObjects = childPageObjects;
	}

}
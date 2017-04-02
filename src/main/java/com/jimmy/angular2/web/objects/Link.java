package com.jimmy.angular2.web.objects;

public class Link {
	private String content;
	private String linkUrl;
	private String style;
	public Link(){};
	public Link(String text, String url, String style){
		this.content=text;
		this.linkUrl=url;
		this.style=style;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	
}

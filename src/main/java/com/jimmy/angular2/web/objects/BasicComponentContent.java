package com.jimmy.angular2.web.objects;

public class BasicComponentContent {
	private String content;
	private String style;
	public BasicComponentContent(){
		
	}
	public BasicComponentContent(String content, String style){
		this.content=content;
		this.style=style;
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
	
}

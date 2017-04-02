package com.jimmy.angular2.web.objects;

public class BasicComponent {
	private String componentType;
	private String sourceUrl;
	private String style;
	public BasicComponent(){
		
	}
	public BasicComponent(String componentType, String sourceUrl, String style){
		this.componentType=componentType;
		this.sourceUrl=sourceUrl;
		this.style=style;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}

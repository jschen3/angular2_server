package com.jimmy.angular2.web.objects;

public enum BaseComponentTypeEnum {
	TEXT("text"),
	IMAGE("image"),
	LINK("link"),
	CODE("code");
	
	private final String type;
	BaseComponentTypeEnum(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
}

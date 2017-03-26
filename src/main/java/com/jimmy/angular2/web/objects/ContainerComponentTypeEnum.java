package com.jimmy.angular2.web.objects;

public enum ContainerComponentTypeEnum {
	COMPONENT_CONTAINER("componentcontainer"),
	CARD("card");
	private final String type;
	ContainerComponentTypeEnum(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
}

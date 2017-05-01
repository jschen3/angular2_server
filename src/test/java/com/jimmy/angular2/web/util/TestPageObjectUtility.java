package com.jimmy.angular2.web.util;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jimmy.angular2.web.objects.BaseComponentTypeEnum;
import com.jimmy.angular2.web.objects.PageObject;

public class TestPageObjectUtility {
	@Test
	public void testEditPageObject() throws JsonParseException, JsonMappingException, IOException{
		PageObject indexPageObject = PageObjectUtility.getBasePageObject("index");
		ArrayList<String> componentPath = new ArrayList<String>();
		componentPath.add("aboutMeCard");
		componentPath.add("aboutMeText");
		PageObject editPageObject =new PageObject();
		editPageObject.setBaseUrl("http://localhost:8080/pages/index/aboutMeCard/aboutMeText");
		editPageObject.setComponentType("text");
		editPageObject.setContent("new content");
		editPageObject.setComponentType(BaseComponentTypeEnum.TEXT.getType());
		editPageObject.setElementUrl("aboutMeText");
		System.out.println(PageObjectUtility.editPageObject(indexPageObject, componentPath, editPageObject));
	}
}

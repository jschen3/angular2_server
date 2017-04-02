package com.jimmy.angular2.web.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.web.objects.BaseComponentTypeEnum;
import com.jimmy.angular2.web.objects.BasicComponent;
import com.jimmy.angular2.web.objects.BasicComponentContent;
import com.jimmy.angular2.web.objects.Link;
import com.jimmy.angular2.web.objects.PageObject;

public class PageObjectUtility {
	public static List<String> toArrayList(String[] array){
		ArrayList<String> list;
		if (array==null)
			list=new ArrayList<String>();
		else{
			list=new ArrayList<String>(Arrays.asList(array));
		}
		return list;
	}
	public static PageObject getBasePageObject(String page) throws JsonParseException, JsonMappingException, IOException{
		String pagePath = Constants.PAGES_FOLDER_LOCATION+"/"+page+".json";
		File pageFile = new File(pagePath);
		if (!pageFile.exists())
			return null;
		else{
			ObjectMapper mapper =new ObjectMapper();
			return mapper.readValue(pageFile, PageObject.class);
		}
		
	}
	public static PageObject getChildPageObject(PageObject basePageObject, List<String> componentPath) {
		if (componentPath ==null || componentPath.size()==0)
			return basePageObject;
		else{
			List<PageObject> childrenPageObjects = basePageObject.getChildPageObjects();
			String findUrl = componentPath.get(0);
			for(PageObject childPageObject:childrenPageObjects){
				if (childPageObject.getElementUrl().equals(findUrl)){
					componentPath.remove(0);
					return getChildPageObject(childPageObject, componentPath);
				}
			}
			return null;
		}
	}
	public static PageObject getChildPageObject(String page, List<String> componentPath) throws IOException{
		PageObject pageObject = getBasePageObject(page);
		return getChildPageObject(pageObject, componentPath);
	}
	
	public static PageObject editPageObject(String page, List<String> componentPath, PageObject edit) throws IOException{
		PageObject pageObject = getBasePageObject(page);
		return editPageObject(pageObject, componentPath, edit);
		
	}
	public static PageObject editPageObject(PageObject startingPageObject, List<String> componentPath, PageObject edit){
		if (componentPath.size()==0){
			return edit;
		}
		else{
			String editObjectName = componentPath.remove(componentPath.size()-1);
			PageObject editParentObject = getChildPageObject(startingPageObject, componentPath);
			List<PageObject> parentObjectChildren =editParentObject.getChildPageObjects();
			int index=0;
			for(PageObject child:parentObjectChildren){
				if (child.getElementUrl().equals(editObjectName))
					break;
				index++;
			}
			parentObjectChildren.set(index, edit);
			editParentObject.setChildPageObjects(parentObjectChildren);
			return editPageObject(startingPageObject, componentPath, editParentObject);
		}
	}
	public static PageObject addPageObject(String page, List<String> componentPath, PageObject add) throws Exception{
		PageObject pageObject = getBasePageObject(page);
		return addPageObject(pageObject, componentPath, add);
	}
	public static PageObject addPageObject(PageObject startingPageObject, List<String> componentPath, PageObject add) throws Exception{
		if (componentPath.size()==0){
			throw new Exception("Another root page object cannot be added");
		}
		else{
			PageObject parentObject = getChildPageObject(startingPageObject, componentPath);
			List<PageObject> parentObjectChildren = parentObject.getChildPageObjects();
			parentObjectChildren.add(add);
			parentObject.setChildPageObjects(parentObjectChildren);
			return editPageObject(startingPageObject, componentPath, parentObject);
		}
	}
	public static void savePageObject(PageObject newPageObject) throws Exception {
		String newFilePath = Constants.PAGES_FOLDER_LOCATION+"/"+newPageObject.getElementUrl()+".json";
		ObjectMapper mapper = new ObjectMapper();
		File pageFile = new File(newFilePath);
		mapper.writeValue(pageFile, newPageObject);
	}
	
	public static String pageObjectToJsonComponentString(String pageName, PageObject pageObject) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		if (pageObject.getChildPageObjects()==null){
			if (pageObject.getComponentType().equals(BaseComponentTypeEnum.LINK.getType())){
				Link link = new Link(pageObject.getContent(), pageObject.getLinkUrl(), pageObject.getStyle());
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(link);
			}
			else{
				BasicComponentContent basicComponentContent = new BasicComponentContent(pageObject.getContent(),  pageObject.getStyle());
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(basicComponentContent);
			}
		}
		else{
			List<BasicComponent> basicComponents = new ArrayList<BasicComponent>();
			List<PageObject> children = pageObject.getChildPageObjects();
			for(PageObject child:children){
				BasicComponent basicComponent = new BasicComponent(child.getComponentType(), child.getBaseUrl(), child.getStyle());
				basicComponents.add(basicComponent);
			}
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(basicComponents);
		}
	}
	public static PageObject jsonComponentStringToPageObject(String elementUrl, String jsonString) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map basicComponent =mapper.readValue(jsonString, Map.class);
			PageObject newPageObject = new PageObject();
			newPageObject.setBaseUrl( (String) basicComponent.get("sourceUrl"));
			newPageObject.setComponentType((String) basicComponent.get("componentType"));
			newPageObject.setContent((String) basicComponent.get("content"));
			newPageObject.setElementUrl(elementUrl);
			newPageObject.setStyle((String) basicComponent.get("style"));
			newPageObject.setLinkUrl((String) basicComponent.get("linkUrl"));
			return newPageObject;
		} catch (IOException e) {
			throw new Exception("Cannot convert json component: " + e.toString());
		}
		
	}
	public static String createPageUrl(String baseUrl, String page) throws Exception{
		String expectedUrl=Constants.BASE_PAGE_URL+"/"+page;
		String pageUrl=expectedUrl+"?";
		if (baseUrl.startsWith(expectedUrl)){
			String ending=baseUrl.substring(expectedUrl.length()+1);
			String[] sections=ending.split("/");
			for(int i=0;i<sections.length;i++){
				if (i==sections.length-1){
					pageUrl+="component="+sections[i];
				}
				else{
					pageUrl+="component="+sections[i]+"&";
				}
			}
			return pageUrl;
		}
		else{
			throw new Exception("Cannot create page url for baseUrl:" + baseUrl + "page: "+ page);
		}	
	}
}

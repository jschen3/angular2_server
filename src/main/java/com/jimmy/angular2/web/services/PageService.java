package com.jimmy.angular2.web.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.objects.BasicComponent;
import com.jimmy.angular2.objects.BasicComponentContent;
import com.jimmy.angular2.objects.Link;
import com.jimmy.angular2.web.objects.BaseComponentTypeEnum;
import com.jimmy.angular2.web.objects.PageObject;

public class PageService {
	
	@RequestMapping(value="/{page}", method=RequestMethod.GET, produces="application/json")
	public String getContainer(@PathVariable("page") String page, @RequestParam(value="component", required=false) String[] componentArray) throws Exception{
		PageObject basePageObject = getBasePageObject(page);
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<String> componentPath;
		if (componentArray==null)
			componentPath=null;
		else{
			componentPath=(ArrayList<String>) Arrays.asList(componentArray);
		}
		PageObject finalPageObject=findEndPageObject(basePageObject, componentPath);
		if (finalPageObject.getChildPageObjects()==null){
			if (finalPageObject.getComponentType().equals(BaseComponentTypeEnum.LINK.getType())){
				Link link = new Link();
				link.setStyle(finalPageObject.getStyle());
				link.setUrl(finalPageObject.getLinkUrl());
				link.setText(finalPageObject.getContent());
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(link);
			}
			else{
				BasicComponentContent basicComponentContent = new BasicComponentContent();
				basicComponentContent.setStyle(finalPageObject.getStyle());
				basicComponentContent.setContent(finalPageObject.getContent());
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(basicComponentContent);
			}
		}
		else{
			List<BasicComponent> basicComponents = new ArrayList<BasicComponent>();
			List<PageObject> children = finalPageObject.getChildPageObjects();
			for(PageObject child:children){
				BasicComponent basicComponent = new BasicComponent();
				String baseUrl=child.getBaseUrl();
				String pageUrl=createPageUrl(baseUrl, page);
				basicComponent.setSourceUrl(pageUrl);
				basicComponent.setComponentType(child.getComponentType());
				basicComponent.setStyle(child.getStyle());
				basicComponents.add(basicComponent);
			}
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(basicComponents);
		}
		
	}
	private PageObject findEndPageObject(PageObject basePageObject, ArrayList<String> componentPath) {
		if (componentPath ==null || componentPath.size()==0)
			return basePageObject;
		else{
			List<PageObject> childrenPageObjects = basePageObject.getChildPageObjects();
			String findUrl = componentPath.get(0);
			for(PageObject childPageObject:childrenPageObjects){
				if (childPageObject.getBaseUrl().equals(findUrl)){
					componentPath.remove(0);
					return findEndPageObject(childPageObject, componentPath);
				}
			}
			return null;
		}
		
	}
	private PageObject getBasePageObject(String page) throws JsonParseException, JsonMappingException, IOException{
		String pagePath = "pages/"+page+".json";
		File pageFile = new File(pagePath);
		if (!pageFile.exists())
			return null;
		else{
			ObjectMapper mapper =new ObjectMapper();
			return mapper.readValue(pageFile, PageObject.class);
		}
		
	}
	private String createPageUrl(String baseUrl, String page) throws Exception{
		String homeUrl="http://localhost:8080";
		String pageUrl=homeUrl+"/"+page;
		if (baseUrl.startsWith(homeUrl+"/"+page+"?")){
			String ending=baseUrl.substring(homeUrl.length()+page.length()+1);
			String[] sections=ending.split("/");
			for(int i=0;i<sections.length;i++){
				pageUrl+="component="+sections[i]+"&";
				if (i==sections.length-1){
					pageUrl+="component="+sections[i];
				}
			}
			return pageUrl;
		}
		else{
			throw new Exception("Cannot create page url for baseUrl:" + baseUrl + "page: "+ page);
		}	
	}
}


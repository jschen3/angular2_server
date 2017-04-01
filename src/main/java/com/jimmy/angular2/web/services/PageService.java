package com.jimmy.angular2.web.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.web.objects.BaseComponentTypeEnum;
import com.jimmy.angular2.web.objects.BasicComponent;
import com.jimmy.angular2.web.objects.BasicComponentContent;
import com.jimmy.angular2.web.objects.Link;
import com.jimmy.angular2.web.objects.PageObject;
@RestController
@CrossOrigin
public class PageService {
	@RequestMapping(value="pages/{page}", method=RequestMethod.GET, produces="application/json")
	public String getContainer(@PathVariable("page") String page, @RequestParam(value="component", required=false) String[] componentArray) throws Exception{
		PageObject basePageObject = getBasePageObject(page);
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<String> componentPath;
		if (componentArray==null)
			componentPath=null;
		else{
			componentPath=new ArrayList<String>(Arrays.asList(componentArray));
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
				if (childPageObject.getElementUrl().equals(findUrl)){
					componentPath.remove(0);
					return findEndPageObject(childPageObject, componentPath);
				}
			}
			return null;
		}
		
	}
	private PageObject getBasePageObject(String page) throws JsonParseException, JsonMappingException, IOException{
		String pagePath = Constants.PAGE_ROOT_PATH+"/"+page+".json";
		File pageFile = new File(pagePath);
		if (!pageFile.exists())
			return null;
		else{
			ObjectMapper mapper =new ObjectMapper();
			return mapper.readValue(pageFile, PageObject.class);
		}
		
	}
	private String createPageUrl(String baseUrl, String page) throws Exception{
		String expectedUrl=Constants.BASE_URL+"/"+page;
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


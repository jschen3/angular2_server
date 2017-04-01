package com.jimmy.angular2.web.services;

import java.io.File;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.web.objects.PageObject;

@RestController
@CrossOrigin
public class EditPageService {
	
	@RequestMapping(value="edit/newpage", method=RequestMethod.POST, produces="application/json")
	public String createNewPage(@RequestParam(value="pageName", required=true) String pageName) throws Exception{
		String baseUrl = Constants.BASE_URL + "/" + pageName;
		PageObject newPageObject = new PageObject();
		newPageObject.setBaseUrl(baseUrl);
		newPageObject.setElementUrl(pageName);
		ArrayList<PageObject> childPageObjects = new ArrayList<PageObject>();
		newPageObject.setChildPageObjects(childPageObjects);
		savePageObject(newPageObject);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newPageObject);
	}

	private void savePageObject(PageObject newPageObject) throws Exception {
		String newFilePath = Constants.PAGE_ROOT_PATH+"/"+newPageObject.getElementUrl()+".json";
		ObjectMapper mapper = new ObjectMapper();
		File pageFile = new File(newFilePath);
		if (pageFile.exists())
			throw new Exception("File already exists");
		else{
			mapper.writeValue(pageFile, newPageObject);
		}	
	}
}

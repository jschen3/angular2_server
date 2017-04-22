package com.jimmy.angular2.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.web.objects.PageObject;
import com.jimmy.angular2.web.util.PageObjectUtility;

@RestController
@CrossOrigin
public class EditPage {
	
	@RequestMapping(value="edit/newPage", method=RequestMethod.POST, produces="application/json")
	public void createNewPage(@RequestParam(value="pageName", required=true) String pageName) throws Exception{
		String baseUrl = Constants.BASE_PAGE_URL + "/" + pageName;
		PageObject newPageObject = new PageObject();
		newPageObject.setBaseUrl(baseUrl);
		newPageObject.setElementUrl(pageName);
		ArrayList<PageObject> childPageObjects = new ArrayList<PageObject>();
		newPageObject.setChildPageObjects(childPageObjects);
		PageObjectUtility.savePageObject(newPageObject);
	}
	
	@RequestMapping(value="edit/editPage/{page}", method=RequestMethod.POST, produces="application/json")
	public void editPage(@PathVariable("page") String page, 
				@RequestParam(value="component", required=false) String[] componentArray,
				@RequestBody PageObject editedComponent) throws Exception{
		List<String> componentPath = PageObjectUtility.toArrayList(componentArray);
		PageObject editedPageObject=PageObjectUtility.editPageObject(page, componentPath, editedComponent);
		PageObjectUtility.savePageObject(editedPageObject);
	}
	
	@RequestMapping(value="edit/addPageObject/{page}", method=RequestMethod.POST, produces="application/json")
	public void addPageObject(@PathVariable("page") String page, 
			@RequestParam(value="component", required=false) String[] componentArray,
			@RequestBody PageObject componentToAdd) throws Exception{
		List<String> componentPath = PageObjectUtility.toArrayList(componentArray);
		PageObject pageObjectWithAddition=PageObjectUtility.addPageObject(page, componentPath, componentToAdd);
		PageObjectUtility.savePageObject(pageObjectWithAddition);
	}
	
	@RequestMapping(value="edit/deltePageObject/{page}", method=RequestMethod.POST, produces="application/json")
	public void deletePageObject(@RequestParam(value="page", required=true) String page, @RequestParam(value="component", required=false) String[] componentArray) throws Exception{
		List<String> componentPath = PageObjectUtility.toArrayList(componentArray);
		PageObject pageObjectPageDeleted = PageObjectUtility.deletePageObject(page, componentPath);
		PageObjectUtility.savePageObject(pageObjectPageDeleted);
	}
	
}

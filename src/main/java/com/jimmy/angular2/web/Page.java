package com.jimmy.angular2.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimmy.angular2.web.objects.PageObject;
import com.jimmy.angular2.web.util.PageObjectUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@CrossOrigin
@Api(value="page", description="Pages api")
public class Page {
	@ApiOperation(value="Gets the specified page")
	@RequestMapping(value="pages/{page}", method=RequestMethod.GET, produces="application/json")
	public String getContainer(@PathVariable("page") String page, @RequestParam(value="component", required=false) String[] componentArray) throws Exception{
		List<String> componentPath = PageObjectUtility.toArrayList(componentArray);
		PageObject pageObject=PageObjectUtility.getChildPageObject(page, componentPath);
		String pageObjectString = PageObjectUtility.pageObjectToJsonComponentString(page, pageObject);
		return pageObjectString;
	}
}


package com.jimmy.angular2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.storage.StorageService;
import com.jimmy.angular2.web.objects.PageObject;
import com.jimmy.angular2.web.util.PageObjectUtility;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class Image {
	private final StorageService storageService;
	
	@Autowired
    public Image(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@RequestMapping(value="images/getImage/{filePath:.+}", method=RequestMethod.GET)
	public ResponseEntity<Resource> returnImage(@PathVariable("filePath") String filePath) throws Exception{
		Resource file = storageService.loadAsResource(filePath);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+file.getFilename()+"\"")
                .body(file);
	}
	@RequestMapping(value="images", method=RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
		 	storageService.store(file);
	}
	
	@RequestMapping(value="images/{page}", method=RequestMethod.POST)
	public void uploadImageIntoAPage(@PathVariable("page") String page, @RequestParam(value="component", required=false) String[] componentArray, @RequestParam("file") MultipartFile file) throws Exception{
		storageService.store(file);
		PageObject imagePageObject = new PageObject();
		String fileName = file.getName();
		List<String> componentPath=PageObjectUtility.toArrayList(componentArray);
		imagePageObject.setContent(Constants.BASE_IMAGE_URL+"/"+fileName);
		imagePageObject.setElementUrl(componentPath.remove(componentPath.size()-1));
		PageObject pageObjectWithAddition=PageObjectUtility.addPageObject(page, componentPath, imagePageObject);
		PageObjectUtility.savePageObject(pageObjectWithAddition);
	}
	
	
}

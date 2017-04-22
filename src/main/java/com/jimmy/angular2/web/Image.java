package com.jimmy.angular2.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.storage.StorageService;

@RestController
@CrossOrigin
public class Image {
	private final StorageService storageService;
	
	@Autowired
    public Image(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@RequestMapping(value="images", method=RequestMethod.GET, produces="application/json")
	public String listUploadedImages(Model model) throws Exception{
		Model images = model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(Image.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
		ObjectMapper mapper = new ObjectMapper();
		return images.toString();
	}
	@RequestMapping(value="images/{filePath}", method=RequestMethod.GET)
	public ResponseEntity<Resource> returnImage(@PathVariable("fileName") String fileName) throws Exception{
		Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+file.getFilename()+"\"")
                .body(file);
	}
	@RequestMapping(value="images", method=RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception{
		 	storageService.store(file);
	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded " + file.getOriginalFilename() + "!");
	        return "redirect:/";
	}
	
}

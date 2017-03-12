package com.jimmy.angular2.pages;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jimmy.angular2.objects.BasicComponent;
import com.jimmy.angular2.objects.BasicComponentContent;
@RestController
@RequestMapping(value="/index")
public class Index {
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value="/rootContainer", method = RequestMethod.GET)
    public String rootContainer() throws JsonProcessingException {
		List<BasicComponent> rootComponents = new ArrayList<BasicComponent>();
		
        BasicComponent aboutMeComponent = new BasicComponent();
        aboutMeComponent.setComponentType("card");
        aboutMeComponent.setSourceUrl("/index/aboutMeCard");
        rootComponents.add(aboutMeComponent);
        
        BasicComponent projectBlogContainer =  new BasicComponent();
        projectBlogContainer.setComponentType("componentcontainer");
        projectBlogContainer.setSourceUrl("/index/projectBlogContainer");
        rootComponents.add(projectBlogContainer);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootComponents);
    }
    @RequestMapping(value="aboutMeCard", method=RequestMethod.GET)
    public String aboutMeCard() throws JsonProcessingException{
    	List<BasicComponent> aboutMeCardComponents = new ArrayList<BasicComponent>();
    	
    	BasicComponent aboutMeImage = new BasicComponent();
    	aboutMeImage.setComponentType("image");
    	aboutMeImage.setSourceUrl("/aboutMeCard/aboutMeImage");
    	aboutMeCardComponents.add(aboutMeImage);
    	
    	BasicComponent aboutMeText = new BasicComponent();
    	aboutMeText.setComponentType("text");
    	aboutMeText.setSourceUrl("/aboutMeCard/aboutMeText");
    	aboutMeCardComponents.add(aboutMeText);
    	
    	BasicComponent aboutMeAboutMeLink = new BasicComponent();
    	aboutMeAboutMeLink.setComponentType("link");
    	aboutMeAboutMeLink.setSourceUrl("/aboutMeCard/aboutMe");
    	aboutMeCardComponents.add(aboutMeAboutMeLink);
    	
    	BasicComponent aboutMeResumeLink = new BasicComponent();
    	aboutMeResumeLink.setComponentType("link");
    	aboutMeResumeLink.setSourceUrl("/aboutMeCard/aboutMeResume");
    	aboutMeCardComponents.add(aboutMeResumeLink);
    	
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeCardComponents);
    	
    }
}

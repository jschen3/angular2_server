package com.jimmy.angular2.pages;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jimmy.angular2.objects.BasicComponent;
import com.jimmy.angular2.objects.BasicComponentContent;
import com.jimmy.angular2.objects.Link;
@RestController
@CrossOrigin
@RequestMapping(value="/index")
public class Index {
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value="/rootContainer", method = RequestMethod.GET)
    public String rootContainer() throws JsonProcessingException {
		List<BasicComponent> rootComponents = new ArrayList<BasicComponent>();
		
        BasicComponent aboutMeComponent = new BasicComponent();
        aboutMeComponent.setComponentType("card");
        aboutMeComponent.setSourceUrl("http://localhost:8080/index/aboutMeCard");
        rootComponents.add(aboutMeComponent);
        
        BasicComponent projectBlogContainer =  new BasicComponent();
        projectBlogContainer.setComponentType("componentcontainer");
        projectBlogContainer.setSourceUrl("http://localhost:8080/index/projectBlogContainer");
        rootComponents.add(projectBlogContainer);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootComponents);
    }
    @RequestMapping(value="aboutMeCard", method=RequestMethod.GET)
    public String aboutMeCard() throws JsonProcessingException{
    	List<BasicComponent> aboutMeCardComponents = new ArrayList<BasicComponent>();
    	
    	BasicComponent aboutMeImage = new BasicComponent();
    	aboutMeImage.setComponentType("image");
    	aboutMeImage.setSourceUrl("http://localhost:8080/aboutMeCard/aboutMeImage");
    	aboutMeCardComponents.add(aboutMeImage);
    	
    	BasicComponent aboutMeText = new BasicComponent();
    	aboutMeText.setComponentType("text");
    	aboutMeText.setSourceUrl("http://localhost:8080/aboutMeCard/aboutMeText");
    	aboutMeCardComponents.add(aboutMeText);
    	
    	BasicComponent aboutMeAboutMeLink = new BasicComponent();
    	aboutMeAboutMeLink.setComponentType("link");
    	aboutMeAboutMeLink.setSourceUrl("http://localhost:8080/aboutMeCard/aboutMe");
    	aboutMeCardComponents.add(aboutMeAboutMeLink);
    	
    	BasicComponent aboutMeResumeLink = new BasicComponent();
    	aboutMeResumeLink.setComponentType("link");
    	aboutMeResumeLink.setSourceUrl("http://localhost:8080/aboutMeCard/aboutMeResume");
    	aboutMeCardComponents.add(aboutMeResumeLink);
    	
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeCardComponents);
    	
    }
    @RequestMapping(value="aboutMeCard/aboutMeImage", method=RequestMethod.GET)
    public String aboutMeImage() throws JsonProcessingException{
    	BasicComponentContent aboutMeImage = new BasicComponentContent();
    	aboutMeImage.setContent("images/jimmy.jpg");
    	aboutMeImage.setStyle("card-img-top");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeImage);
    }
    @RequestMapping(value="aboutMeCard/aboutMeText", method=RequestMethod.GET)
    public String aboutMeText() throws JsonProcessingException{
    	BasicComponentContent aboutMeImage = new BasicComponentContent();
    	aboutMeImage.setContent("Hi, my name is obviously Jimmy Chen. I am an enterprising full stack software developing, most messing around but occaisonally building cool stuff. Thank you for visiting my website.");
    	aboutMeImage.setStyle("card-text");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeImage);
    }
    @RequestMapping(value="aboutMeCard/aboutMe" , method=RequestMethod.GET)
    public String aboutMeAboutMeLink() throws JsonProcessingException{
    	Link aboutMeAboutMeLink = new Link();
    	aboutMeAboutMeLink.setText("About me");
    	aboutMeAboutMeLink.setStyle("card-link");
    	aboutMeAboutMeLink.setUrl("/aboutme");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeAboutMeLink);
    	
    }
    @RequestMapping(value="aboutMeCard/aboutMeResume", method=RequestMethod.GET)
    public String aboutMeAboutMeResume() throws JsonProcessingException{
    	Link aboutMeResume = new Link();
    	aboutMeResume.setText("Resume");
    	aboutMeResume.setStyle("card-link");
    	aboutMeResume.setUrl("/resume");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeResume);
    }
}

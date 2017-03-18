package com.jimmy.angular2.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.objects.BasicComponent;
import com.jimmy.angular2.objects.BasicComponentContent;
import com.jimmy.angular2.objects.Link;
@RestController
@CrossOrigin
@RequestMapping(value="/index")
public class Index {
    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value="/rootContainer", method = RequestMethod.GET, produces="application/json")
    public String rootContainer() throws JsonProcessingException {
		List<BasicComponent> rootComponents = new ArrayList<BasicComponent>();
		
        BasicComponent aboutMeComponent = new BasicComponent();
        aboutMeComponent.setComponentType("card");
        aboutMeComponent.setSourceUrl("http://localhost:8080/index/aboutMeCard" );
        aboutMeComponent.setStyle("aboutMeCard");
        rootComponents.add(aboutMeComponent);
        
        BasicComponent projectBlogContainer =  new BasicComponent();
        projectBlogContainer.setComponentType("componentcontainer");
        projectBlogContainer.setSourceUrl("http://localhost:8080/index/projectBlogContainer");
        projectBlogContainer.setStyle("projectBlogContainer");
        rootComponents.add(projectBlogContainer);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootComponents);
    }
    @RequestMapping(value="/aboutMeCard", method=RequestMethod.GET, produces="application/json")
    public String aboutMeCard() throws JsonProcessingException{
    	List<BasicComponent> aboutMeCardComponents = new ArrayList<BasicComponent>();
    	
    	BasicComponent aboutMeImage = new BasicComponent();
    	aboutMeImage.setComponentType("image");
    	aboutMeImage.setSourceUrl("http://localhost:8080/index/aboutMeCard/aboutMeImage");
    	aboutMeCardComponents.add(aboutMeImage);
    	
    	BasicComponent aboutMeText = new BasicComponent();
    	aboutMeText.setComponentType("text");
    	aboutMeText.setSourceUrl("http://localhost:8080/index/aboutMeCard/aboutMeText");
    	aboutMeCardComponents.add(aboutMeText);
    	
    	BasicComponent aboutMeAboutMeLink = new BasicComponent();
    	aboutMeAboutMeLink.setComponentType("link");
    	aboutMeAboutMeLink.setSourceUrl("http://localhost:8080/index/aboutMeCard/aboutMe");
    	aboutMeCardComponents.add(aboutMeAboutMeLink);
    	
    	BasicComponent aboutMeResumeLink = new BasicComponent();
    	aboutMeResumeLink.setComponentType("link");
    	aboutMeResumeLink.setSourceUrl("http://localhost:8080/index/aboutMeCard/aboutMeResume");
    	aboutMeCardComponents.add(aboutMeResumeLink);
    	
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeCardComponents);
    	
    }
    @RequestMapping(value="/aboutMeCard/aboutMeImage", method=RequestMethod.GET, produces="application/json")
    public String aboutMeImage() throws JsonProcessingException{
    	BasicComponentContent aboutMeImage = new BasicComponentContent();
    	aboutMeImage.setContent("images/jimmy.jpg");
    	aboutMeImage.setStyle("card-img-top");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeImage);
    }
    @RequestMapping(value="/aboutMeCard/aboutMeText", method=RequestMethod.GET, produces="application/json")
    public String aboutMeText() throws JsonProcessingException{
    	BasicComponentContent aboutMeImage = new BasicComponentContent();
    	aboutMeImage.setContent("Hi, my name is obviously Jimmy Chen. I am an enterprising full stack software developer, mostly messing around but occaisonally building cool stuff. Thank you for visiting my website.");
    	aboutMeImage.setStyle("card-text");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeImage);
    }
    @RequestMapping(value="/aboutMeCard/aboutMe" , method=RequestMethod.GET, produces="application/json")
    public String aboutMeAboutMeLink() throws JsonProcessingException{
    	Link aboutMeAboutMeLink = new Link();
    	aboutMeAboutMeLink.setText("About me");
    	aboutMeAboutMeLink.setStyle("");
    	aboutMeAboutMeLink.setUrl("/aboutme");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeAboutMeLink);
    	
    }
    @RequestMapping(value="/aboutMeCard/aboutMeResume", method=RequestMethod.GET, produces="application/json")
    public String aboutMeAboutMeResume() throws JsonProcessingException{
    	Link aboutMeResume = new Link();
    	aboutMeResume.setText("Resume");
    	aboutMeResume.setStyle("");
    	aboutMeResume.setUrl("/resume");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aboutMeResume);
    }
    @RequestMapping(value="/projectBlogContainer", method=RequestMethod.GET, produces="application/json")
    public String projectBlogContainer() throws JsonProcessingException{
    	List<BasicComponent> projectBlogComponents = new ArrayList<BasicComponent>();
    	
    	BasicComponent projectCard = new BasicComponent();
    	projectCard.setComponentType("card");
    	projectCard.setSourceUrl("http://localhost:8080/index/projectBlogContainer/projectCard");
    	projectBlogComponents.add(projectCard);
    	
    	BasicComponent blogCard = new BasicComponent();
    	blogCard.setComponentType("card");
    	blogCard.setSourceUrl("http://localhost:8080/index/projectBlogContainer/blogCard");
    	projectBlogComponents.add(blogCard);
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectBlogComponents);
    }
    @RequestMapping(value="/projectBlogContainer/projectCard", method=RequestMethod.GET, produces="application/json")
    public String projectCardContainer() throws JsonProcessingException{
    	List<BasicComponent> projectCardComponents  = new ArrayList<BasicComponent>();
    	
    	BasicComponent projectImage = new BasicComponent();
    	projectImage.setComponentType("image");
    	projectImage.setSourceUrl("http://localhost:8080/index/projectBlogContainer/projectCard/projectCardImage");
    	projectCardComponents.add(projectImage);
    	
    	BasicComponent projectText = new BasicComponent();
    	projectText.setComponentType("text");
    	projectText.setSourceUrl("http://localhost:8080/index/projectBlogContainer/projectCard/projectCardText");
    	projectCardComponents.add(projectText);
    	
    	BasicComponent singleProjectLink = new BasicComponent();
    	singleProjectLink.setComponentType("link");
    	singleProjectLink.setSourceUrl("http://localhost:8080/index/projectBlogContainer/projectCard/singleProjectLink");
    	projectCardComponents.add(singleProjectLink);
    	
    	BasicComponent projectPortfolioLink = new BasicComponent();
    	projectPortfolioLink.setComponentType("link");
    	projectPortfolioLink.setSourceUrl("http://localhost:8080/index/projectBlogContainer/projectCard/projectPortfolioLink");
    	projectCardComponents.add(projectPortfolioLink);
    	
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectCardComponents);
    }
    @RequestMapping(value="/projectBlogContainer/projectCard/projectCardImage")
    public String projectCardImage() throws JsonProcessingException{
    	BasicComponentContent projectImage = new BasicComponentContent();
    	projectImage.setContent("images/puzzle.png");
    	projectImage.setStyle("card-img-top");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectImage);
    }
    @RequestMapping(value="/projectBlogContainer/projectCard/projectCardText", method=RequestMethod.GET, produces="application/json")
    public String projectCardText() throws JsonProcessingException{
    	BasicComponentContent projectText = new BasicComponentContent();
    	projectText.setContent("I find programming problems fun, so one of my projects is my portfolio of problems and solutions");
    	projectText.setStyle("card-text");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectText);
    }
    @RequestMapping(value="/projectBlogContainer/projectCard/singleProjectLink", method=RequestMethod.GET, produces="application/json")
    public String singleProjectLink() throws JsonProcessingException{
    	Link singleProjectLink = new Link();
    	singleProjectLink.setStyle("");
    	singleProjectLink.setText("Problem Repository");
    	singleProjectLink.setUrl("/problemRepository");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(singleProjectLink);
    }
    @RequestMapping(value="/projectBlogContainer/projectCard/projectPortfolioLink", method=RequestMethod.GET, produces="application/json")
    public String portfolioLink() throws JsonProcessingException{
    	Link portfolioLink = new Link();
    	portfolioLink.setStyle("");
    	portfolioLink.setUrl("/projectPortfolio");
    	portfolioLink.setText("/projectPortFolio");
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(portfolioLink);
    }
    
    @RequestMapping(value="/projectBlogContainer/blogCard", method=RequestMethod.GET, produces="application/json")
    public String blogCardContainer() throws JsonProcessingException{
    	List<BasicComponent> blogCardComponents = new ArrayList<BasicComponent>();
    	
    	BasicComponent blogImage = new BasicComponent();
    	blogImage.setComponentType("image");
    	blogImage.setSourceUrl("http://localhost:8080/index/projectBlogContainer/blogCard/blogImage");
    	blogCardComponents.add(blogImage);
    	
    	BasicComponent blogText = new BasicComponent();
    	blogText.setComponentType("text");
    	blogText.setSourceUrl("http://localhost:8080/index/projectBlogContainer/blogCard/blogText");
    	blogCardComponents.add(blogText);
    	
    	BasicComponent blogLink = new BasicComponent();
    	blogLink.setComponentType("link");
    	blogLink.setSourceUrl("http://localhost:8080/index/projectBlogContainer/blogCard/blogLink");
    	blogCardComponents.add(blogLink);
    	
    	BasicComponent archiveLink= new BasicComponent();
    	archiveLink.setComponentType("link");
    	archiveLink.setSourceUrl("http://localhost:8080/index/projectBlogContainer/blogCard/archiveLink");
    	blogCardComponents.add(archiveLink);
    	
    	return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogCardComponents);
    }
    
}

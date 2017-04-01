package com.jimmy.angular2.web.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.angular2.constants.Constants;
import com.jimmy.angular2.web.objects.BaseComponentTypeEnum;
import com.jimmy.angular2.web.objects.ContainerComponentTypeEnum;
import com.jimmy.angular2.web.objects.PageObject;

public class IndexPage {
	public static PageObject initIndexPage(){
		String baseUrl = Constants.BASE_URL+"/index";
		PageObject indexPageObject = new PageObject();
		indexPageObject.setBaseUrl(baseUrl);
		indexPageObject.setComponentType(null);
		indexPageObject.setElementUrl("index");
		List<PageObject> indexContainerChildren = new ArrayList<PageObject>();
		PageObject aboutMeCard = new PageObject();
		aboutMeCard.setComponentType(ContainerComponentTypeEnum.CARD.getType());
		aboutMeCard.setBaseUrl(baseUrl+"/aboutMeCard");
		aboutMeCard.setElementUrl("aboutMeCard");
		aboutMeCard.setStyle("card aboutMeCard");
		
		List<PageObject> aboutMeCardChildren = new ArrayList<PageObject>();
		PageObject aboutMeImage = new PageObject();
		aboutMeImage.setBaseUrl(baseUrl+"/aboutMeCard/aboutMeImage");
		aboutMeImage.setElementUrl("aboutMeImage");
		aboutMeImage.setStyle("card-image");
		aboutMeImage.setContent("images/jimmy.jpg");
		aboutMeImage.setComponentType(BaseComponentTypeEnum.IMAGE.getType());
		aboutMeCardChildren.add(aboutMeImage);
		
		PageObject aboutMeText = new PageObject();
		aboutMeText.setBaseUrl(baseUrl+"/aboutMeCard/aboutMeText");
		aboutMeText.setElementUrl("aboutMeText");
		aboutMeText.setComponentType(BaseComponentTypeEnum.TEXT.getType());
		aboutMeText.setContent("Hi, my name is obviously Jimmy Chen. I am an enterprising full stack software developer, mostly messing around but occaisonally building cool stuff. Thank you for visiting my website.");
		aboutMeCardChildren.add(aboutMeText);
		
		PageObject aboutMeAboutMeLink = new PageObject();
		aboutMeAboutMeLink.setBaseUrl(baseUrl+"/aboutMeCard/aboutMe");
		aboutMeAboutMeLink.setElementUrl("aboutMe");
		aboutMeAboutMeLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		aboutMeAboutMeLink.setContent("About me");
		aboutMeAboutMeLink.setLinkUrl("#");
		aboutMeAboutMeLink.setStyle("card-action");
		aboutMeCardChildren.add(aboutMeAboutMeLink);
		
		PageObject aboutMeResumeLink = new PageObject();
		aboutMeResumeLink.setBaseUrl(baseUrl+"/aboutMeCard/resume");
		aboutMeResumeLink.setElementUrl("resume");
		aboutMeResumeLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		aboutMeResumeLink.setContent("Resume");
		aboutMeResumeLink.setStyle("card-action");
		aboutMeResumeLink.setLinkUrl("#");
		aboutMeCardChildren.add(aboutMeResumeLink);
		
		aboutMeCard.setChildPageObjects(aboutMeCardChildren);
		indexContainerChildren.add(aboutMeCard);
		
		PageObject projectBlogContainer = new PageObject();
		projectBlogContainer.setBaseUrl(baseUrl+"/projectBlogContainer");
		projectBlogContainer.setElementUrl("projectBlogContainer");
		projectBlogContainer.setComponentType(ContainerComponentTypeEnum.COMPONENT_CONTAINER.getType());
		projectBlogContainer.setStyle("projectBlogContainer");
		
		List<PageObject> projectBlogContainerChildren = new ArrayList<PageObject>();
		
		PageObject projectCard = new PageObject();
		projectCard.setBaseUrl(baseUrl+"/projectBlogContainer/projectCard");
		projectCard.setElementUrl("projectCard");
		projectCard.setComponentType(ContainerComponentTypeEnum.CARD.getType());
		projectCard.setStyle("card projectCard");
		
		List<PageObject> projectCardChildren = new ArrayList<PageObject>();
		
		PageObject projectCardImage = new PageObject();
		projectCardImage.setBaseUrl(baseUrl+"/projectBlogContainer/projectCard/projectCardImage");
		projectCardImage.setElementUrl("projectCardImage");
		projectCardImage.setComponentType(BaseComponentTypeEnum.IMAGE.getType());
		projectCardImage.setContent("images/puzzle.png");
		projectCard.setStyle("card-image");
		projectCardChildren.add(projectCardImage);
		
		PageObject projectCardText = new PageObject();
		projectCardText.setBaseUrl(baseUrl+"/projectBlogContainer/projectCard/projectCardText");
		projectCardText.setElementUrl("projectCardText");
		projectCardText.setComponentType(BaseComponentTypeEnum.TEXT.getType());
		projectCardText.setStyle("cart-content");
		projectCardText.setContent("I find programming problems fun, so one of my projects is my portfolio of problems and solutions");
		projectCardChildren.add(projectCardText);
		
		PageObject projectCardLink = new PageObject();
		projectCardLink.setBaseUrl(baseUrl+"/projectBlogContainer/projectCard/singleProjectLink");
		projectCardLink.setElementUrl("singleProjectLink");
		projectCardLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		projectCardLink.setStyle("card-action");
		projectCardLink.setContent("Problem Repository");
		projectCardLink.setLinkUrl("#");
		projectCardChildren.add(projectCardLink);
	
		PageObject projectCardPortfolioLink = new PageObject();
		projectCardPortfolioLink.setBaseUrl(baseUrl+"/projectBlogContainer/projectCard/projectPortfolioLink");
		projectCardPortfolioLink.setElementUrl("projectPortfolioLink");
		projectCardPortfolioLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		projectCardPortfolioLink.setStyle("card-action");
		projectCardPortfolioLink.setContent("Project Portfolio");
		projectCardPortfolioLink.setLinkUrl("#");
		projectCardChildren.add(projectCardPortfolioLink);
		
		projectCard.setChildPageObjects(projectCardChildren);
		projectBlogContainerChildren.add(projectCard);
		
		PageObject blogCard = new PageObject();
		blogCard.setComponentType(ContainerComponentTypeEnum.CARD.getType());
		blogCard.setBaseUrl(baseUrl+"/projectBlogContainer/blogCard");
		blogCard.setElementUrl("blogCard");
		blogCard.setStyle("card");
		projectCard.setComponentType(ContainerComponentTypeEnum.CARD.getType());
		projectCard.setStyle("card blogCard");
		
		List<PageObject> blogCardChildren = new ArrayList<PageObject>();
		
		PageObject blogCardImage = new PageObject();
		blogCardImage.setBaseUrl(baseUrl+"/projectBlogContainer/blogCard/blogImage");
		blogCardImage.setElementUrl("blogImage");
		blogCardImage.setComponentType(BaseComponentTypeEnum.IMAGE.getType());
		blogCardImage.setContent("images/mountain.jpg");
		blogCardImage.setStyle("card-image");
		blogCardChildren.add(blogCardImage);
		
		PageObject blogCardText = new PageObject();
		blogCardText.setBaseUrl(baseUrl+"/projectBlogContainer/blogCard/blogText");
		blogCardText.setElementUrl("blogText");
		blogCardText.setComponentType(BaseComponentTypeEnum.TEXT.getType());
		blogCardText.setStyle("card-content");
		blogCardText.setContent("Sample Text blurb Sample Text blurb Sample Text blurb Sample Text blurb ");
		blogCardChildren.add(blogCardText);
		
		PageObject blogCardLink = new PageObject();
		blogCardLink.setBaseUrl(baseUrl+"/projectBlogContainer/blogCard/blogLink");
		blogCardLink.setElementUrl("blogLink");
		blogCardLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		blogCardLink.setStyle("card-action");
		blogCardLink.setContent("Read More");
		blogCardLink.setLinkUrl("#");
		blogCardChildren.add(blogCardLink);
	
		PageObject blogCardArchiveLink = new PageObject();
		blogCardArchiveLink.setBaseUrl(baseUrl+"/projectBlogContainer/blogCard/archiveLink");
		blogCardArchiveLink.setElementUrl("archiveLink");
		blogCardArchiveLink.setComponentType(BaseComponentTypeEnum.LINK.getType());
		blogCardArchiveLink.setStyle("card-action");
		blogCardArchiveLink.setContent("Blog Archive");
		blogCardArchiveLink.setLinkUrl("#");
		blogCardChildren.add(blogCardArchiveLink);
		
		blogCard.setChildPageObjects(blogCardChildren);
		projectBlogContainerChildren.add(blogCard);
		
		projectBlogContainer.setChildPageObjects(projectBlogContainerChildren);
		indexContainerChildren.add(projectBlogContainer);
		indexPageObject.setChildPageObjects(indexContainerChildren);
		
		return indexPageObject;	
	}
	public static void main(String args[]) throws IOException{
		File f = new File("/Users/jimmy/Desktop/index.json");
		ObjectMapper mapper = new ObjectMapper();
		PageObject indexPageObject = initIndexPage();
		mapper.writerWithDefaultPrettyPrinter().writeValue(f, indexPageObject);
	}
}

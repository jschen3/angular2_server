# Angular2 Server

Spring boot rest server for the angular2 website project. Provides a rest server back end to create page objects and page manipulations that are displayed in the angular2 website. 

# How to run
mvn spring-boot:run

# Significant Features
Swagger documentation: localhost:8080/swagger-ui.html

PageObject controls each pages individual view. 

A PageObject consists of the following attributes
baseUrl: url to find the page <br />
elementUrl: name of the element<br />
childPageObjects: array of child objects allows embedding of any amount of objects in page<br />
componentType : every type of page object available currently image, text, link, etc<br />
content: the content of the pageobject
style: css style of the page object

All page objects are served with the following url:
localhost:8080/pages/{name of page}

Page objects are placed in the pages directory

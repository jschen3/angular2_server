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

# Swagger Documentation Image
![alt text](https://lh3.googleusercontent.com/qgleLfIRY4qPTEqMtONG92HE3PM3FUCCU9gfk874mvuCaIV35a_qMEgRRWYKDUXoymNG-WnhTw490JfA_Dqw4ewg6IgLtKfqaVSusA7-_FXoDTOZpZXMSI_nj7mTQY4bWXwRyzjJOKFAgEb9PVb01i8_644Ur4w42TcRvSHIMpHi-YQseoH08NeAWkDN8u31abu48XpQRnRC1VPGwpVV1AJXnjbhjaIP0zJpT62m_nnrAAlriXrh1DNA_Qr4a3OWlsXLRVf28NYpz8271Fgwgoa0uUXlzUL3_QuCYJKZBgPFFXAbdtitMoKDMf9s3wtumtn-FoxwkHRbf1NRACF2B5T66rppN7JJRwZ6cZipSEkT2_QKNTlIr6pt3ZBYdIRECK-Q6mx1X3M3Evez8QsXZGFtoeWRjEXrXw0SBYN02wKNfg_hpsk9-gUrNYF8G4xBQy3xfEplse9tWqXfGOXm-XMU9jZ8OMWEgPzEToC1RRaBghSodFB932Ds23Wim4vunfxab2go40Z4POBexxCEn40ty5pzM01q2gBhKfR3KzrJyR1OculrpQhK0rqNOLJwq7aXlB7nih_SE5qVfgkpsM5sBpDwrpcYw8AxY6JuCJsco2RltoQUOTxmW3Buk_e4ZHknuUwYETSoBJHE135JRpxUI-knMyWT3lxpWRwrOg=w600-h385-no)

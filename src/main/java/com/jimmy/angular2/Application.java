package com.jimmy.angular2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jimmy.angular2.storage.StorageService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.jimmy.angular2.web", "com.jimmy.angular2.storage"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jimmy.angular2.web"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
    @Bean
	CommandLineRunner init(StorageService storageService) {
    	return (args) -> {
    		storageService.init();
    	};
    }
    
    
}

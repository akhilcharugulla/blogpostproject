package udemy.blogpost.demo.configurations;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private ApiInfo apidetails() {
		 return new ApiInfo(
                "Spring Boot Blog REST APIs",
                "Spring Boot Blog REST API Documentation",
                "1.0",
                "Terms of service",
                new Contact("Akhil", "www.akhilcharugulla.com", "akhilcharugulla13@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
	}
	
	@Bean
	public Docket akhil() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apidetails())
				.select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
				.build();

// select() method initiates builder pattern to Apiselectionbuilder. 		
//The apiInfo() method cannot be called  once select() is called on Docket object
//after select() or before build() causes an error as it is not part of builder 
//you can't call apiInfo like below mentioned:  
		
//		return new Docket(DocumentationType.SWAGGER_2)
//		.select()
//		.apiInfo(apidetails())
//        .apis(RequestHandlerSelectors.any())
//        .paths(PathSelectors.any())
//		.build();
//or
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//              .apis(RequestHandlerSelectors.any())
//              .paths(PathSelectors.any())
//				.apiInfo(apidetails()) 
//				.build();		

		
//	apiInfo must be called before select() method 
//	if select() method is called before apiInfo , we need to place apiInfo after the build() method 
		
		
//for paths method we can use PathSelectors.ant("/postapi/*")
//for apis method we can tell it what are the packages it look at. By giving RequestHandlerSelectors.basePackage("udemy.logpost.demo.controller")
	}
}

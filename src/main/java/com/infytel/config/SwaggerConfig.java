package com.infytel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@ComponentScan
@EnableSwagger2
public class SwaggerConfig 
{
	private  Contact contact =new Contact("admin@infytel.com", "", "admin@infytel.com");//contact details of Infytel
	private ApiInfo apiInfo = new ApiInfo("InfyTel REST API","This page documents Infytel endpoints","","1.0","","",""); 

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("(?!/error.*).*"))//removes error controller being projected in the documentation site
				.build().useDefaultResponseMessages(false).apiInfo(apiInfo); //setting apiInfo here
	}
	
}
 
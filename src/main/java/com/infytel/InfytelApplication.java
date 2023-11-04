package com.infytel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@SpringBootApplication
public class InfytelApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(InfytelApplication.class, args);
	}
	
	 //Enable CORS globally
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("http://localhost:9000");
	            }
	    
	 
	//Support matrix parameters
		 @Override
		    public void configurePathMatch(PathMatchConfigurer configurer) {
		        UrlPathHelper urlPathHelper = new UrlPathHelper();
		        urlPathHelper.setRemoveSemicolonContent(false);
		        configurer.setUrlPathHelper(urlPathHelper);
		   }
		  @Bean
		    public PasswordEncoder passwordEncoder() {
		        return new BCryptPasswordEncoder();
		    }

//http://localhost:8085/infytel/swagger-ui/	  
		  
}

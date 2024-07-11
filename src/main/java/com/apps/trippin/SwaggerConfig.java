package com.apps.trippin;


import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
				.paths(regex("/trippin.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Trippin API")
				.description("Trippin API reference for developers")
				.termsOfServiceUrl("http://trippin.com")
				.contact(new Contact("Replica", "Replica", "ravindrakv1305@gmail.com")).license("Trippin License")
				.licenseUrl("ravindrakv1305@gmail.com").version("1.0").build();
	}
}*/

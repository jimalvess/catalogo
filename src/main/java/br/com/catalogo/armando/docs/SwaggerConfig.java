package br.com.catalogo.armando.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2).select() 
			.apis(RequestHandlerSelectors.basePackage("br.com.catalogo.armando.endpoint.controller")) 
			.paths(PathSelectors.any()) 
			.build() 
			.apiInfo(metaData()); 
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Catalogo de Produtos") 
			.description("Teste para Compasso") 
			.version("1.0") 
			.contact(new Contact("Armando Alves", null, "jimalvess@gmail.com")) 
			.license("Apache License Version 2.0") 
			.licenseUrl("https://www.apache.org/license/LICENSE-2.0") 
			.build();
	}
}

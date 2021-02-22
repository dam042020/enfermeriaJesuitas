package org.jesuitasrioja.proyectoFinalEval.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurationBasic {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.jesuitasrioja.proyectoFinalEval"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API rest Enfermeria")
				.description("Proyecto para la gestion de la enfermeria de Jesuitas").version("0.1")
				.contact(new Contact("Sergio", "url", "dam04.2020.jesuitas@gmail.com")).build();
	}
}

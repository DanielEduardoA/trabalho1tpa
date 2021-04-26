package br.edu.univas.api.marvel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket documentationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.edu.univas.api.marvel.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		Contact contact = new Contact("Daniel Eduardo", "https://marvel-pipeline-heroku.herokuapp.com/", "danieleduardoalm@gmail.com");
		return new ApiInfoBuilder().title("Marvel API")
				.description("Marvel API reference for developers")
				.contact(contact)
				.version("1.0")
				.build();
	}

}
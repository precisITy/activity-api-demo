package com.precisity.demo.activity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-05T14:21:30.374Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

  ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Activity API")
                               .description("REST-API")
                               .license("")
                               .licenseUrl("http://unlicense.org")
                               .termsOfServiceUrl("")
                               .version("1.0")
                               .contact(new Contact("", "", ""))
                               .build();
  }

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2).select()
                                                  .apis(RequestHandlerSelectors.basePackage("com.precisity.demo.activity.api"))
                                                  .build()
                                                  .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                                                  .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                                                  .apiInfo(apiInfo());
  }

}

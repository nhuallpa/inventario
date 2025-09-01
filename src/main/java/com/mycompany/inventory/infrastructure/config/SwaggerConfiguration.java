package com.mycompany.inventory.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
            .info(
                    new Info()
                            .title("Inventario API")
                            .description("Inventario")
                            .version("1.0")
                            .contact(
                                    new Contact()
                                            .name("Nestor Huallpa")));
  }
}
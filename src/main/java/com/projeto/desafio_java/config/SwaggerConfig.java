package com.projeto.desafio_java.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Java API")
                        .description("API para o projeto Desafio Java de nível III")
                        .contact(new Contact()
                                .name("Pedro Freitag")
                                .email("pedrobosini9@gmail.com")));
    }
}

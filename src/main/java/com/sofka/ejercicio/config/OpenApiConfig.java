package com.sofka.ejercicio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("💻 Ejercicio Java Back-end | Zona Talentos 💡  ")
                        .description("API - Tour de Francia")
                        .contact(new Contact()
                                .name("María Camila Morales")
                                .email("maria.morales@sofka.com.co"))
                        .version("v1.0")
                );

    }
}

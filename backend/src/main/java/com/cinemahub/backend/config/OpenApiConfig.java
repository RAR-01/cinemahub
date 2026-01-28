package com.cinemahub.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cinemaHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CinemaHub API")
                        .description("API documentation for CinemaHub backend")
                        .version("1.0"));
    }
}

package com.channa.jobx_portal.config;

import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Portal REST API")
                        .version("1.0")
                        .description("Spring Boot Job Portal Backend APIs for Companies, Jobs, Candidates, Applications")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Yallaling Channa")
                                .email("your-email@gmail.com")));
    }
}
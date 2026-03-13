package com.themaj.smart_books;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartBooksAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SmartBooks API")
                        .description("Finance tracking backend")
                                .version("1.0"));
    }
}

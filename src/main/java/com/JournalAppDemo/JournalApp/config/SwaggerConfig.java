package com.JournalAppDemo.JournalApp.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Journal App API Documentation")
                                .description("This documentation provides all the REST API endpoints for the Journal Application. " +
                                        "\nThe Journal App allows users to register, login, create personal journals, manage entries, and provides admin-level controls. " +
                                        "\nAll secured APIs require a valid JWT token.")
                                .version("1.0.0")
                )
                .servers(Arrays.asList(new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server")))
                .tags(Arrays.asList(
                        new Tag().name("Public APIs").description("Endpoints accessible without authentication, like registration and login."),
                        new Tag().name("User APIs").description("APIs for authenticated users to manage their profiles and personal data."),
                        new Tag().name("Journal APIs").description("CRUD operations for user journals and journal entries."),
                        new Tag().name("Admin APIs").description("Administrative operations like user management.")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .description("Enter your JWT token in the format: Bearer <token>")
                ));
    }
}

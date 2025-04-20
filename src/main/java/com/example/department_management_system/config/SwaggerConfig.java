package com.example.department_management_system.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // general info
        Info info = new Info()
                .title("Ovoz berish tizimi API-lar")
                .version("1.0.0")
                .description("Quyida Ovoz berish tizimi loyihasi uchun API hujjatlar tagdim qilingan.")
                .contact(new Contact()
                        .name("Avazbek")
                        .email("bigmangcom@gmail.com")
                        .url("https://t.me/Greed_Coder")
                )
                .license(new License()
                        .name("Avazbek")
                        .url("https://t.me/Greed_Coder")
                )
                .termsOfService("Savol javob uchun: https://t.me/Greed_Coder");

        // servers (ishlatiladigan serverlar)
        Server server1 = new Server()
                .description("Local")
                .url("http://localhost:8080");


        // security type (bizning holatda JWT)
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("bearerAuth");

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("bearerAuth");
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.bearerFormat("JWT");
        securityScheme.setIn(SecurityScheme.In.HEADER);
        securityScheme.setScheme("bearer");

        Components components = new Components();
        components.addSecuritySchemes("bearerAuth", securityScheme);

        // collect all together
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setServers(List.of(server1));
        openAPI.setSecurity(List.of(securityRequirement));
        openAPI.components(components);

        // return-xe
        return openAPI;
    }
}

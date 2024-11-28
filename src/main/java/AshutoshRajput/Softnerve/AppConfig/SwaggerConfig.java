package AshutoshRajput.Softnerve.AppConfig;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder()
                .group("Student API")
                .pathsToMatch("/student/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginApi() {
        return GroupedOpenApi.builder()
                .group("Login API")
                .pathsToMatch("/login/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management Application")
                        .description("This project is designed to manage information and offers functions for creating, updating, deleting, and retrieving student records. Additionally, it includes a login API for authentication using JWT tokens. The `create student` API is an open endpoint. To use it, you will need to first log in, then copy the JWT token and paste it into the header for authentication.\n\n" +
                                "### API Summary\n" +
                                "**Base URL:** `http://localhost:8090`\n\n" +
                                "1. **Create Student**\n" +
                                "   - **POST** `/student/create`\n" +
                                "   - Requires valid student data.\n\n" +
                                "2. **Get Student by ID**\n" +
                                "   - **GET** `/student/get/{id}`\n" +
                                "   - Retrieves a student by their unique ID.\n\n" +
                                "3. **Update Student**\n" +
                                "   - **PUT** `/student/update/{id}`\n" +
                                "   - Updates a student record based on ID and request body.\n\n" +
                                "4. **Delete Student**\n" +
                                "   - **DELETE** `/student/delete/{id}`\n" +
                                "   - Deletes a student record by their unique ID.\n\n" +
                                "5. **Get List of Students**\n" +
                                "   - **GET** `/student/students/{page}/{size}`\n" +
                                "   - Pagination-based student retrieval.\n\n" +
                                "6. **Login**\n" +
                                "   - **POST** `/login/autologin`\n" +
                                "   - Authenticates user and returns JWT token.\n\n" +
                                "**Database:** `MongoDB`\n")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Ashutosh Rajput")
                                .email("rajputashutosh81@gmail.com"))
                )

                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")));

    }
}

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
                .pathsToMatch("**")
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management Application")
                        .description("This project is designed student to manage information and offers functions for creating, updating, deleting, and retrieving student records. Additionally, it includes a login API for authentication using JWT tokens. The `create student` API is an open endpoint. To use it, you will need to first log in, then copy the JWT token and paste it into the header for authentication.\n\n" +
                                "### API Summary\n" +
                                "   - **POST:** `Create Student`\n" +
                                "   - **GET:** `Get Student by ID`\n" +
                                "   - **PUT:** `Update Student`\n" +
                                "   - **DELETE:** `Delete Student`\n" +
                                "   - **GET:** `Get List of Students`\n" +
                                "   - **POST:** `Login`\n" +
                                "**Database:** `MongoDB`\n")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Admin")
                                .email("admin@gmail.com"))
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

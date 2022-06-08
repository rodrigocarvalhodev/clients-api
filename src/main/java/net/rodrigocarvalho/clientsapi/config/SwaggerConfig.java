package net.rodrigocarvalho.clientsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Clients API",
                "Documentação da API restful Clients-API",
                "1.0.0-SNAPSHOT",
                "",
                new Contact("Rodrigo Carvalho", "https://rodrigocarvalho.net", "eu@rodrigocarvalho.net"),
                "",
                "",
                new ArrayList<>()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.rodrigocarvalho"))
                .paths(PathSelectors.any())
                .build();
    }
}

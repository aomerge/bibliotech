package com.aomerge.apiGateway.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi gatewayApi(RestTemplate restTemplate) {
        return GroupedOpenApi.builder()
                .group("gateway")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApi -> {
                    String[] apiDocsUrls = {
                            "http://localhost:8081/v3/api-docs",
                            "http://localhost:8082/v3/api-docs"
                    };

                    for (String apiDocsUrl : apiDocsUrls) {
                        OpenAPI microserviceOpenApi = restTemplate.getForObject(apiDocsUrl, OpenAPI.class);

                        if (microserviceOpenApi != null) {
                            Paths paths = microserviceOpenApi.getPaths();
                            if (paths != null) {
                                openApi.getPaths().putAll(paths);
                            }
                        }
                    }
                })
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

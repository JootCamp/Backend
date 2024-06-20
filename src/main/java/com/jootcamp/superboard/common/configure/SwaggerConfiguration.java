package com.jootcamp.superboard.common.configure;

import com.jootcamp.superboard.common.exception.BadRequestException;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();

        Info info = new Info()
                .title("JootCamp에서 사용할 API 명세서")
                .version("1.0")
                .description("JootCamp에서 사용할 API 명세서 화면입니다");

        openAPI.info(info);

        return openAPI;
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.jootcamp.superboard"};

        return GroupedOpenApi.builder()
                .group("JootCamp")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
}

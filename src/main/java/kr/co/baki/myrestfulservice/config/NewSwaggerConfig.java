package kr.co.baki.myrestfulservice.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "My RESTful Service API 명세서",
                version = "v1.0.0",
                description = "Spring Boot를 이용한 RESTful 서비스 API 명세서입니다.")
)

@Configuration
@RequiredArgsConstructor
public class NewSwaggerConfig {
    @Bean
    public GroupedOpenApi customOpenAPI() {
        String[] paths = {"/users/**", "/admin/**"};

        return GroupedOpenApi.builder()
                .group("일반 사용자와 관리자를 위한 User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}

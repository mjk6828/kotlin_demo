package com.example.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableSwagger2
class SwaggerConfig {
    /**
     * swagger 기본 메인 설정
     * @return
     */
    @Bean
    fun restAPI(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.kotlinserver"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
    }
    /**
     * swagger 기초 정보 설정
     * @return
     */
    fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
            .title("Spring Boot REST API")
            .version("1.0.0")
            .description("swagger api 입니다.")
            .build()
    }

    /**
     * header 사용을 위한 jwt 설정 자물쇠 노출 설정
     */
    fun apiKey(): ApiKey? {
        return ApiKey("JWT", "token", "header")
    }

    /**
     * header 사용을 위한 jwt 설정 자물쇠 노출 설정
     */
    fun securityContext(): SecurityContext? {
        return springfox.documentation.spi.service.contexts.SecurityContext
            .builder()
            .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build()
    }

    /**
     *  header 사용을 위한 jwt 설정 자물쇠 노출 설정
     */
    fun defaultAuth(): List<SecurityManager>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }
}
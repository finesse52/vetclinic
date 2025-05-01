package project.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Pet and Owners API")
                    .version("1.0.0")
                    .description("Документация API для системы управления владельцами и питомцами")
            )
            .servers(
                listOf(
                    Server().url("http://localhost:8080").description("Локальный сервер")
                )
            )
    }
}
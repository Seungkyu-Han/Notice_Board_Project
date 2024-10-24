package seungkyu.board.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import seungkyu.board.service.UserService

@Configuration
class UserRouter(
) {

    @Bean
    fun router(
        userService: UserService
    ) = coRouter {
        "/users".nest {

        }
    }
}
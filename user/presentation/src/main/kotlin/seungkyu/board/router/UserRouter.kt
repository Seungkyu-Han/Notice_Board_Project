package seungkyu.board.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import seungkyu.board.service.UserService

@Configuration
class UserRouter {

    @Bean
    fun router(
        userService: UserService
    ) = coRouter {

        "/users".nest {
            POST("/signup", userService::register)
            POST("/login", userService::login)
            GET("/my-info", userService::getUserInfo)
            PUT("/logout", userService::logout)
            PATCH("/password", userService::updatePassword)
            DELETE("/withdraw", userService::deleteId)
        }
    }
}
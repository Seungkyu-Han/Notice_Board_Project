package seungkyu.board.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import seungkyu.board.service.PostService

@Configuration
class PostRouter {

    @Bean
    fun router(
        postService: PostService
    ) = coRouter {
        "/posts".nest {

        }
    }
}
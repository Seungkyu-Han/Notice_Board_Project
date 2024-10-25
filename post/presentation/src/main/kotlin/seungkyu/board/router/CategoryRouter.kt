package seungkyu.board.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import seungkyu.board.service.impl.CategoryServiceImpl

@Configuration
class CategoryRouter {

    @Bean
    fun router(
        categoryService: CategoryServiceImpl
    ) = coRouter {
        "/categories".nest {
            POST("", categoryService::create)
            PATCH("", categoryService::update)
            DELETE("", categoryService::delete)
        }
    }
}
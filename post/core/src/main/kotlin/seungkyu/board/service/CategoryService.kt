package seungkyu.board.service

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse


interface CategoryService {

    suspend fun create(serverRequest: ServerRequest): ServerResponse
    suspend fun update(serverRequest: ServerRequest): ServerResponse
    suspend fun delete(serverRequest: ServerRequest): ServerResponse
}
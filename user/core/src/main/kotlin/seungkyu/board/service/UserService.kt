package seungkyu.board.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

interface UserService {

    fun register(serverRequest: ServerRequest): ServerResponse
    fun login(request: ServerRequest): ServerResponse
    fun isDuplicateId(request: ServerRequest): ServerResponse
    fun getUserInfo(request: ServerRequest): ServerResponse
    fun updatePassword(request: ServerRequest): ServerResponse
    fun deleteId(request: ServerRequest): ServerResponse
}
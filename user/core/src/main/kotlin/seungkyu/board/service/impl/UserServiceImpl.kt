package seungkyu.board.service.impl

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import seungkyu.board.service.UserService

@Service
class UserServiceImpl : UserService {
    override fun register(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override fun login(request: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override fun isDuplicateId(request: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(request: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override fun updatePassword(request: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override fun deleteId(request: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }
}
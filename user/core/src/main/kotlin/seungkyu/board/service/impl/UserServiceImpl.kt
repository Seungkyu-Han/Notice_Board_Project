package seungkyu.board.service.impl

import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import seungkyu.board.aop.LoginCheck
import seungkyu.board.data.enums.Status
import seungkyu.board.dto.req.LoginReq
import seungkyu.board.dto.req.RegisterReq
import seungkyu.board.dto.req.UpdatePasswordReq
import seungkyu.board.entity.UserDocument
import seungkyu.board.exception.DuplicatedIdException
import seungkyu.board.repository.UserMongoRepository
import seungkyu.board.service.UserService

@Service
class UserServiceImpl(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val userMongoRepository: UserMongoRepository
) : UserService {

    override suspend fun register(serverRequest: ServerRequest): ServerResponse {

        val registerReq = serverRequest.bodyToMono(RegisterReq::class.java).awaitSingle()

        val isDuplicate = isDuplicateId(registerReq.userId)

        if(isDuplicate)
            throw DuplicatedIdException("중복된 아이디 입니다.")

        val userDocument = UserDocument(
            id = null,
            userId = registerReq.userId,
            password = bCryptPasswordEncoder.encode(registerReq.password),
            nickName = registerReq.nickname,
            isAdmin = false,
            isWithDraw = false,
            status = Status.DEFAULT,
            createdAt = null,
            updatedAt = null
        )

        val userResult = userMongoRepository.save(userDocument).awaitSingle()

        return ServerResponse.status(201).bodyValueAndAwait(userResult)
    }

    override suspend fun login(request: ServerRequest): ServerResponse {
        val loginReq = request.bodyToMono(LoginReq::class.java).awaitSingle()

        val user = userMongoRepository.findByUserId(loginReq.id).awaitSingleOrNull()

        return if(user != null && bCryptPasswordEncoder.matches(loginReq.password, user.password)){
            request.session()
                .doOnNext{
                    it.attributes["status"] = user.status
                    it.attributes["id"] = loginReq.id
                }
                .awaitFirst()

            ServerResponse.ok().bodyValueAndAwait(user)
        }else{
            ServerResponse.status(403).buildAndAwait()
        }
    }

    override suspend fun isDuplicateId(request: ServerRequest): ServerResponse {
        val userId = request.queryParam("id").orElse("")
        return ServerResponse.ok().bodyValueAndAwait(this.isDuplicateId(userId))
    }

    private suspend fun isDuplicateId(userId: String): Boolean {
        return userMongoRepository.existsByUserId(userId).awaitSingle()
    }

    @LoginCheck(status = Status.DEFAULT)
    override suspend fun getUserInfo(request: ServerRequest): ServerResponse {

        val userId = request.session()
            .map {
                it.attributes.getOrDefault("id", "") as String
            }.awaitSingle()

        if(userId.isNullOrBlank())
            return ServerResponse.status(403).bodyValueAndAwait(userId)

        val user = userMongoRepository.findByUserId(userId).awaitSingle()

        return ServerResponse.ok().bodyValueAndAwait(user)
    }

    @LoginCheck(status = Status.DEFAULT)
    override suspend fun updatePassword(request: ServerRequest): ServerResponse {
        val updatePasswordReq = request.bodyToMono(UpdatePasswordReq::class.java).awaitSingle()

        val userId = request.session()
            .map {
                it.attributes.getOrDefault("id", "") as String
            }.awaitSingle()

        val user = userMongoRepository.findByUserId(userId).awaitSingle()

        if(bCryptPasswordEncoder.matches(updatePasswordReq.beforePassword, user.password)){
            user.password = bCryptPasswordEncoder.encode(updatePasswordReq.afterPassword)
            userMongoRepository.save(user).awaitSingle()
            return ServerResponse.ok().buildAndAwait()
        }else{
            return ServerResponse.status(403).buildAndAwait()
        }
    }

    @LoginCheck(status = Status.DEFAULT)
    override suspend fun deleteId(request: ServerRequest): ServerResponse {

        val userId = request.session()
            .map {
                it.attributes.getOrDefault("id", "") as String
            }.awaitSingle()

        val user = userMongoRepository.findByUserId(userId).awaitSingleOrNull()
            ?: return ServerResponse.status(403).buildAndAwait()

        userMongoRepository.delete(user).awaitSingleOrNull()

        return ServerResponse.ok().buildAndAwait()
    }

    @LoginCheck(status = Status.DEFAULT)
    override suspend fun logout(request: ServerRequest): ServerResponse {

        request.session().map{
            it.invalidate()
        }.awaitSingle()

        return ServerResponse.ok().buildAndAwait()
    }
}
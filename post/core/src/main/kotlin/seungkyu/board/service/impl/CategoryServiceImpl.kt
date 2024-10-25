package seungkyu.board.service.impl

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import seungkyu.board.dto.req.CreateReq
import seungkyu.board.dto.req.UpdateReq
import seungkyu.board.entity.CategoryDocument
import seungkyu.board.repository.CategoryMongoRepository
import seungkyu.board.service.CategoryService

@Service
class CategoryServiceImpl(
    private val categoryMongoRepository: CategoryMongoRepository
): CategoryService {

    override suspend fun create(serverRequest: ServerRequest): ServerResponse {

        val createReq = serverRequest.bodyToMono(CreateReq::class.java).awaitSingle()

        val category = CategoryDocument(name = createReq.name)

        categoryMongoRepository.save(category).awaitSingle()

        return ServerResponse.status(201).buildAndAwait()
    }

    override suspend fun update(serverRequest: ServerRequest): ServerResponse {
        val updateReq = serverRequest.bodyToMono(UpdateReq::class.java).awaitSingle()

        val category = categoryMongoRepository.findByName(updateReq.beforeName).awaitSingle()

        category.name = updateReq.afterName

        categoryMongoRepository.save(category).awaitSingle()

        return ServerResponse.status(201).buildAndAwait()
    }

    override suspend fun delete(serverRequest: ServerRequest): ServerResponse {
        val categoryName = serverRequest.queryParam("name")

        if(categoryName.isEmpty)
            return ServerResponse.badRequest().buildAndAwait()

        categoryMongoRepository.deleteByName(categoryName.get()).awaitSingleOrNull()
        return ServerResponse.ok().buildAndAwait()
    }
}
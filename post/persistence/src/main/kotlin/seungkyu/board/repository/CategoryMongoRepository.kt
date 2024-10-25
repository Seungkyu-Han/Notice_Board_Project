package seungkyu.board.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import seungkyu.board.entity.CategoryDocument

interface CategoryMongoRepository: ReactiveMongoRepository<CategoryDocument, ObjectId> {
    fun findByName(name: String): Mono<CategoryDocument>
    fun deleteByName(name: String): Mono<Void>
}
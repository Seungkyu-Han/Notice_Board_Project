package seungkyu.board.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Category")
data class CategoryDocument(
    @Id
    val id: ObjectId? = null,
    @Indexed(unique = true, name = "category_name")
    var name: String,

)

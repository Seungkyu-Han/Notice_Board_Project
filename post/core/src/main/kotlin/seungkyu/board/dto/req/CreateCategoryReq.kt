package seungkyu.board.dto.req

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCategoryReq(
    @JsonProperty("name")
    val name: String
)

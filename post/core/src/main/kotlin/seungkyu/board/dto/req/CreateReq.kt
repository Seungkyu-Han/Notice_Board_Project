package seungkyu.board.dto.req

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateReq(
    @JsonProperty("name")
    val name: String
)

package seungkyu.board.dto

import seungkyu.board.data.Status
import java.time.LocalDate

data class UserReq (
    val id: Int,
    val userId: String,
    val password: String,
    val nickName: String,
    val isAdmin: Boolean,
    val createdAt: LocalDate,
    val isWithDraw: Boolean,
    val status: Status,
    val updatedAt: LocalDate,
)
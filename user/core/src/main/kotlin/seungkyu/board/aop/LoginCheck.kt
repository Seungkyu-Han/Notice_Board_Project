package seungkyu.board.aop

import seungkyu.board.data.enums.Status


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LoginCheck(
     val status: Status
)
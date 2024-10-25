package seungkyu.board.aop

import lombok.SneakyThrows
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import seungkyu.board.data.enums.Status

@Aspect
@Component
class LoginCheckAspect {

    companion object {
        private val log = LoggerFactory.getLogger(LoginCheckAspect::class.java)
    }

    @SneakyThrows
    @Around("@annotation(seungkyu.board.aop.LoginCheck) && @annotation(loginCheck)")
    fun defaultLoginCheck(joinPoint: ProceedingJoinPoint, loginCheck: LoginCheck):Any? {

        (joinPoint.args[0] as ServerRequest)
            .session()
            .doOnNext {
                if(it.attributes["status"] as Status == loginCheck.status)
                    log.info("request method: {},  id: {}", joinPoint.signature, it.attributes["id"])
            }
            .subscribe()


        return joinPoint.proceed()
    }
}
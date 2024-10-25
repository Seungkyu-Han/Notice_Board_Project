package seungkyu.board.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import seungkyu.board.service.CategoryService

@Service
class CategoryServiceImpl: CategoryService {

    companion object {
        private val log = LoggerFactory.getLogger(CategoryServiceImpl::class.java)
    }
}
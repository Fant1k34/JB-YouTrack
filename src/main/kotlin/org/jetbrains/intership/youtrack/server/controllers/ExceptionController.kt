package org.jetbrains.intership.youtrack.server.controllers

import org.jetbrains.intership.youtrack.server.dto.ErrorResultDto
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody


@ControllerAdvice
class ExceptionController {
    private var logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @ResponseBody
    @ExceptionHandler
    fun handleException(ex: RuntimeException): ErrorResultDto {
        logger.error(ex.message)
        return ErrorResultDto(message = ex.message)
    }
}

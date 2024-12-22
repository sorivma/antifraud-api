package org.example.antifraudapi.rest.exceiption

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.example.antifraudapi.rest.models.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = [Content(schema = Schema(implementation = ExceptionResponse::class))]
    )
    fun handleGeneralException(ex: Exception): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(
            errorCode = "INTERNAL_SERVER_ERROR",
            message = "An unexpected error occurred: ${ex.localizedMessage}",
            details = ex.stackTraceToString()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(
        responseCode = "404",
        description = "Resource Not Found",
        content = [Content(schema = Schema(implementation = ExceptionResponse::class))]
    )
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(
            errorCode = "RESOURCE_NOT_FOUND",
            message = "Resource not found: ${ex.message}",
            details = "The resource with the given ID was not found in the system."
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(
        responseCode = "400",
        description = "Bad Request - Invalid input",
        content = [Content(schema = Schema(implementation = ExceptionResponse::class))]
    )
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(
            errorCode = "BAD_REQUEST",
            message = "Invalid input: ${ex.localizedMessage}",
            details = "The request data failed validation due to incorrect format or missing fields."
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }
}
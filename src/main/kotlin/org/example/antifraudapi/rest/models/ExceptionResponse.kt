package org.example.antifraudapi.rest.models

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

open class ExceptionResponse(
    @Schema(
        description = "A unique error code representing the type of error.",
        example = "USER_NOT_FOUND",
        required = true
    )
    val errorCode: String,

    @Schema(
        description = "A human-readable message explaining the error.",
        example = "User with ID 123 was not found.",
        required = true
    )
    val message: String,

    @Schema(
        description = "Additional details about the error. May provide further information or context for debugging.",
        example = "The user with ID 123 does not exist in the database.",
        nullable = true
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val details: String? = null,

    @Schema(
        description = "The timestamp when the error occurred.",
        example = "2024-11-29T12:34:56.789Z",
        required = true
    )
    val timestamp: LocalDateTime = LocalDateTime.now(),

    @Schema(
        description = "The name of the entity that caused the error, if applicable (e.g., 'User', 'Transaction').",
        example = "User",
        nullable = true
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val entity: String? = null // Optional entity field for specific cases
)

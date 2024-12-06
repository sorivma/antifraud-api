package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.springframework.hateoas.RepresentationModel

@Schema(description = "Representation model for a user.")
open class UserRepresentation(

    @Schema(
        description = "The unique identifier of the user. If not provided, it will be automatically generated.",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private val id: String? = null,

    @field:NotBlank(message = "User name cannot be blank")
    @Schema(
        description = "The name of the user. This is required and must be a non-blank string.",
        example = "John Doe"
    )
    val name: String
) : RepresentationModel<UserRepresentation>()

package org.example.antifraudapi.rest.controllers

import NoEntityResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.example.antifraudapi.rest.models.AccountRepresentation
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.RegistrationRequest
import org.example.antifraudapi.rest.models.UserRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import java.util.*

interface UserController {
    @Operation(
        summary = "Get all users paged",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "All users paged",
                content = [Content(schema = Schema(implementation = PagedModel::class))]
                )
        ],
        parameters = [
            Parameter(
            description = "Configuration of page",
            schema = Schema(implementation = Pageable::class)
        )
        ]
    )
    fun getUserPages(
        pageable: Pageable
    ): PagedModel<UserRepresentation>

    @Operation(
        summary = "Get user by id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User with id specified",
                content = [Content(schema = Schema(implementation = UserRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found",
                content = [Content(schema = Schema(implementation = NoEntityResponse::class))]
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of the user",
            )
        ]
    )
    fun getUser(userId: UUID): UserRepresentation

    @Operation(
        summary = "Get user account by user id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User account by user id",
                content = [Content(schema = Schema(implementation = AccountRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of the user",
            )
        ]
    )
    fun getAccount(userId: UUID): AccountRepresentation

    @Operation(
        summary = "Register new user",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User registered",
                content = [Content(schema = Schema(implementation = UserRepresentation::class))]
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "Registration request body",
                schema = Schema(implementation = RegistrationRequest::class)
            )
        ]
    )
    fun registerUser(@Valid registrationRequest: RegistrationRequest): UserRepresentation

    @Operation(
        summary = "Updates status of user account by id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Verification that all is fine",
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of the user"
            )
        ]
    )
    fun updateStatus(userId: UUID, status: AccountStatus)
}
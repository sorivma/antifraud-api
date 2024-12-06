package org.example.antifraudapi.rest.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.example.antifraudapi.rest.models.AccountRepresentation
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.ExceptionResponse
import org.example.antifraudapi.rest.models.RegistrationRequest
import org.example.antifraudapi.rest.models.UserRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import java.util.*

interface UserController {

    @Operation(
        summary = "Get paged list of all users",
        description = "Retrieves a paginated list of all users in the system. Pagination settings like page size and number can be configured.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Paged list of users successfully retrieved",
                content = [Content(schema = Schema(implementation = PagedModel::class))]
            )
        ]
    )
    fun getUserPages(
        @Parameter(
            description = "Pagination configuration, including page size and number",
            schema = Schema(implementation = Pageable::class)
        )
        pageable: Pageable
    ): PagedModel<UserRepresentation>

    @Operation(
        summary = "Get user details by ID",
        description = "Fetches details of a user by their unique ID. If no user exists with the provided ID, a 404 response is returned.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User successfully retrieved",
                content = [Content(schema = Schema(implementation = UserRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found",
                content = [Content(schema = Schema(implementation = ExceptionResponse::class))]
            )
        ]
    )
    fun getUser(
        @Parameter(
            description = "Unique identifier of the user",
            required = true,
            schema = Schema(type = "string", format = "uuid")
        )
        userId: UUID
    ): UserRepresentation

    @Operation(
        summary = "Get user account details by user ID",
        description = "Fetches account details for a given user by their unique ID. Returns 404 if the user is not found.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User account successfully retrieved",
                content = [Content(schema = Schema(implementation = AccountRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun getAccount(
        @Parameter(
            description = "Unique identifier of the user",
            required = true,
            schema = Schema(type = "string", format = "uuid")
        )
        userId: UUID
    ): AccountRepresentation

    @Operation(
        summary = "Register a new user",
        description = "Creates a new user in the system with the details provided in the registration request body.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User successfully registered",
                content = [Content(schema = Schema(implementation = UserRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation error in the registration data"
            )
        ]
    )
    fun registerUser(
        @Valid
        @Parameter(
            description = "User registration details",
            required = true,
            schema = Schema(implementation = RegistrationRequest::class)
        )
        registrationRequest: RegistrationRequest
    ): UserRepresentation

    @Operation(
        summary = "Update user account status",
        description = "Updates the status of a user's account by their ID. The status must be a valid value from the `AccountStatus` enum.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "User account status successfully updated"
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun updateStatus(
        @Parameter(
            description = "Unique identifier of the user",
            required = true,
            schema = Schema(type = "string", format = "uuid")
        )
        userId: UUID,

        @Parameter(
            description = "New account status for the user",
            required = true,
            schema = Schema(implementation = AccountStatus::class)
        )
        status: AccountStatus
    )
}

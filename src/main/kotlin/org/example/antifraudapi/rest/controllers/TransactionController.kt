package org.example.antifraudapi.rest.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.example.antifraudapi.rest.models.AccountRepresentation
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.TransactionCreationRequest
import org.example.antifraudapi.rest.models.TransactionRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import java.util.*

interface TransactionController {
    @Operation(
        summary = "Get user account by user id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Paged model of transactions",
                content = [Content(schema = Schema(implementation = AccountRepresentation::class))]
            )
        ],
        parameters = [
            Parameter(
                description = "Pageable config of transactions",
                schema = Schema(
                    implementation = Pageable::class
                )
            )
        ]
    )
    fun getTransactions(pageable: Pageable): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Get transaction by its id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Model of transactions",
                content = [Content(schema = Schema(implementation = TransactionRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Transaction not found",
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of transaction",
            )
        ]
    )
    fun getTransaction(transactionId: UUID): TransactionRepresentation

    @Operation(
        summary = "Get page model of transactions addressed to user with specified id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Model of transactions",
                content = [Content(schema = Schema(implementation = Pageable::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found",
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of User",
            ),
            Parameter(
                description = "Pageable config of transactions",
                schema = Schema(implementation = Pageable::class)
            )
        ]
    )
    fun getIncomeTransactions(
        userId: UUID,
        pageable: Pageable
    ): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Get page model of transactions addressed from user with specified id",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Model of transactions",
                content = [Content(schema = Schema(implementation = Pageable::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found",
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "ID of User",
            ),
            Parameter(
                required = true,
                description = "Pageable config of transactions",
                schema = Schema(implementation = Pageable::class)
            )
        ]
    )
    fun getOutcomeTransactions(
        userId: UUID,
        pageable: Pageable
    ): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Create transaction",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Transaction created",
                content = [Content(schema = Schema(implementation = TransactionRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User (payee or payer) not found",
            )
        ],
        parameters = [
            Parameter(
                required = true,
                description = "Transaction info",
            )
        ]
    )
    fun createTransaction(@Valid transaction: TransactionCreationRequest): TransactionRepresentation
}
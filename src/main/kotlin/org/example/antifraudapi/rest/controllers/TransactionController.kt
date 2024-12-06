package org.example.antifraudapi.rest.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.example.antifraudapi.rest.models.TransactionCreationRequest
import org.example.antifraudapi.rest.models.TransactionRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import java.util.*

interface TransactionController {

    @Operation(
        summary = "Get transactions of a user by user ID",
        description = "Fetches a paginated list of transactions for the specified user ID.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved a paginated list of transactions",
                content = [Content(schema = Schema(implementation = PagedModel::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun getTransactions(
        pageable: Pageable
    ): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Get a specific transaction by its ID",
        description = "Retrieves a transaction using its unique ID.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved the transaction details",
                content = [Content(schema = Schema(implementation = TransactionRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Transaction not found"
            )
        ]
    )
    fun getTransaction(
        transactionId: UUID
    ): TransactionRepresentation

    @Operation(
        summary = "Get incoming transactions for a user by user ID",
        description = "Fetches a paginated list of transactions addressed to a specific user.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved a paginated list of incoming transactions",
                content = [Content(schema = Schema(implementation = PagedModel::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun getIncomeTransactions(
        userId: UUID,
        pageable: Pageable
    ): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Get outgoing transactions for a user by user ID",
        description = "Fetches a paginated list of transactions originating from a specific user.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved a paginated list of outgoing transactions",
                content = [Content(schema = Schema(implementation = PagedModel::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    fun getOutcomeTransactions(
        userId: UUID,
        pageable: Pageable
    ): PagedModel<TransactionRepresentation>

    @Operation(
        summary = "Create a new transaction",
        description = "Initiates a new transaction between users. Both the payee and payer must exist.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Transaction successfully created",
                content = [Content(schema = Schema(implementation = TransactionRepresentation::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Payee or Payer not found"
            )
        ],
        requestBody = RequestBody(
            description = "Transaction details to be created",
            required = true,
            content = [Content(schema = Schema(implementation = TransactionCreationRequest::class))]
        )
    )
    fun createTransaction(
        @Valid transaction: TransactionCreationRequest
    ): TransactionRepresentation
}

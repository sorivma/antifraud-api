package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Positive
import org.example.antifraudapi.rest.models.TransactionStatus

@Schema(description = "Request model for creating a new transaction.")
open class TransactionCreationRequest(
    @Positive(message = "Amount must be a positive value")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than or equal to 0.01")
    @Schema(
        description = "The amount of money involved in the transaction. It must be a positive value and not less than 0.01.",
        example = "100.50"
    )
    val amount: Double,

    @Schema(
        description = "The unique identifier of the user. If not provided, it will be automatically generated.",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val payerId: String,

    @Schema(
        description = "The unique identifier of the user. If not provided, it will be automatically generated.",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val payeeId: String,
    @Schema(
        description = "Payment method of transaction",
        example = "CREDIT_CARD"
    )
    val paymentMethod: PaymentMethod,
)

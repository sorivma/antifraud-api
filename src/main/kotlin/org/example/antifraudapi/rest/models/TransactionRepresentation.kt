package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel

@Schema(description = "Representation model for a transaction.")
open class TransactionRepresentation(

    @Schema(
        description = "The unique identifier for the transaction. If not specified, it will be generated automatically.",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val id: String? = null,

    @Schema(
        description = "The amount of money involved in the transaction. It must be a positive value.",
        example = "100.50"
    )
    val amount: Double,

    @Schema(
        description = "The status of the transaction. Possible values: 'PENDING', 'COMPLETED', 'FAILED'.",
        example = "PENDING"
    )
    val status: TransactionStatus
) : RepresentationModel<TransactionRepresentation>()

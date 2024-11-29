package org.example.antifraudapi.rest.models

import jakarta.validation.constraints.Positive
import org.springframework.hateoas.RepresentationModel

open class TransactionRepresentation(
    val id: String? = null,
    @Positive
    val amount: Double,
    val status: TransactionStatus,
): RepresentationModel<TransactionRepresentation>()


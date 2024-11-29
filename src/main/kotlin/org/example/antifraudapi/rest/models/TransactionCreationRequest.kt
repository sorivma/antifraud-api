package org.example.antifraudapi.rest.models

open class TransactionCreationRequest(
    val id: String? = null,
    val amount: Double,
    val status: TransactionStatus,
)
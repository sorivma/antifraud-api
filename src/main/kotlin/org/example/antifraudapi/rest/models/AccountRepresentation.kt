package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel

@Schema(description = "Represents the account details for a user, including balance, status, type, and currency information.")
open class AccountRepresentation(

    @Schema(
        description = "Unique identifier for the account.",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val id: String? = null,

    @Schema(
        description = "The balance of the account. This value represents the current available balance in the user's account.",
        example = "2500.75"
    )
    val balance: Double,

    @Schema(
        description = "The current status of the account. Possible values could be 'ACTIVE', 'SUSPENDED', etc.",
        implementation = AccountStatus::class
    )
    val status: AccountStatus,

    @Schema(
        description = "The type of the account. This could represent different account categories like 'SAVINGS', 'CHECKING', etc.",
        implementation = AccountType::class
    )
    val accountType: AccountType,

    @Schema(
        description = "The currency in which the account balance is held, e.g., 'USD', 'EUR', etc.",
        example = "USD"
    )
    val currency: String

): RepresentationModel<AccountRepresentation>()

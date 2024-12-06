package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.example.antifraudapi.rest.models.AccountType
import org.hibernate.validator.constraints.Currency

@Schema(description = "Request model for user registration.")
open class RegistrationRequest(

    @Schema(
        description = "The name of the user. It must not be blank.",
        example = "John Doe"
    )
    @NotBlank(message = "Name required non-blank")
    val name: String,

    @Schema(
        description = "The email of the user. It must not be blank.",
        example = "johndoe@example.com"
    )
    @NotBlank(message = "Email required non-blank")
    val email: String,

    @Schema(
        description = "The currency code for the user account. Accepted values: EUR, RUB, USD.",
        example = "USD"
    )
    @Currency(value = ["EUR", "RUB", "USD"], message = "Invalid currency code. Accepted values: EUR, RUB, USD.")
    val currencyCode: String,

    @Schema(
        description = "The account type of the user. Must be one of the following values: CHECKING, SAVINGS, BUSINESS.",
        example = "CHECKING"
    )
    @NotBlank(message = "Password required non-blank")
    @Size(min = 5, message = "Password required to be at least 5 characters")
    val accountType: AccountType
)

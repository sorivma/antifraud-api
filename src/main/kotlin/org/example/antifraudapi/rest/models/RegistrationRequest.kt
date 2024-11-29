package org.example.antifraudapi.rest.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Currency

open class RegistrationRequest(
    @NotBlank(message = "Name required non-blank")
    val name: String,
    @NotBlank(message = "Email required non-blank")
    val email: String,
    @Currency(value = ["EUR", "RUB", "USD"])
    val currencyCode: String,
    @NotBlank(message = "Password required non-blank")
    @Size(min = 5, message = "Password required to be at least 5 characters")
    val accountType: AccountType
)

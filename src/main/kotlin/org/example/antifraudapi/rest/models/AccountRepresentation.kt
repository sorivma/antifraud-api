package org.example.antifraudapi.rest.models

import org.springframework.hateoas.RepresentationModel

open class AccountRepresentation(
    val id: String? = null,
    val balance: Double,
    val status: AccountStatus,
    val accountType: AccountType,
    val currency: String
): RepresentationModel<AccountRepresentation>()

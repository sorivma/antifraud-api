package org.example.antifraudapi.rest.models

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "The status of the account, indicating whether it is active, suspended, or closed.")
enum class AccountStatus {

    @Schema(description = "The account is active and can perform transactions.")
    ACTIVE,

    @Schema(description = "The account is suspended and cannot perform transactions.")
    SUSPENDED,

    @Schema(description = "The account is closed and cannot perform any further transactions.")
    CLOSED
}

@Schema(description = "The type of the account, indicating whether it is checking, savings, or business.")
enum class AccountType {

    @Schema(description = "A checking account typically used for day-to-day transactions.")
    CHECKING,

    @Schema(description = "A savings account intended for saving money, often earning interest.")
    SAVINGS,

    @Schema(description = "A business account used by businesses for financial operations.")
    BUSINESS
}

@Schema(description = "The status of a transaction, indicating its current state in the payment process.")
enum class TransactionStatus {

    @Schema(description = "The transaction is pending and awaiting further processing or completion.")
    PENDING,

    @Schema(description = "The transaction has been completed successfully.")
    COMPLETED,

    @Schema(description = "The transaction has failed and was not processed successfully.")
    FAILED
}

@Schema(description = "The method of payment used for the transaction, indicating how funds are transferred.")
enum class PaymentMethod {

    @Schema(description = "Payment made using a credit card.")
    CREDIT_CARD,

    @Schema(description = "Payment made via a bank transfer.")
    BANK_TRANSFER,

    @Schema(description = "Payment made using Qiwi. (DEPRECATED)")
    @Deprecated("This payment method is no longer supported.")
    QIWI
}

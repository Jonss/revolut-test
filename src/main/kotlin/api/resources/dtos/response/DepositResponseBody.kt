package api.resources.dtos.response

import java.time.LocalDateTime

data class DepositResponseBody(
    val value: DepositValueResponse,
    val origin: SimpleAccountResponse,
    val destiny: SimpleAccountResponse,
    val createdAt: LocalDateTime
)

data class DepositValueResponse(val amount: Long, val currency: CurrencyDTO)

data class SimpleAccountResponse(val email: String, val name: String)

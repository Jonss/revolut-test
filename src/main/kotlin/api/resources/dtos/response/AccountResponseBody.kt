package api.resources.dtos.response

import java.util.*


data class AccountResponseBody(
    val email: String,
    val fullName: String,
    val externalId: UUID
)
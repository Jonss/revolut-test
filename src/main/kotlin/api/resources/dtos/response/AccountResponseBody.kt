package api.resources.dtos.response

import java.util.UUID

data class AccountResponseBody(
    val email: String,
    val fullName: String,
    val externalId: UUID
)

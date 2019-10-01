package api.resources.dtos.request

data class DepositRequestBody(val value: DepositValueRequest, val account: SimpleAccountRequest)

data class DepositValueRequest(val amount: Long, val currency: String)

data class SimpleAccountRequest(val email: String)

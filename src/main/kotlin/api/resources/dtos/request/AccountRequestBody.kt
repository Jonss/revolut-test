package api.resources.dtos.request

import domain.models.Account

data class AccountRequestBody(
    val email: String,
    val fullName: String,
    val nickName: String,
    val phoneNumber: String
) {

    fun toAccount()  = Account(
        this.email,
        this.fullName,
        this.nickName,
        this.phoneNumber
    )

}

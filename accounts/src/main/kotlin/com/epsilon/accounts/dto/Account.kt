package com.epsilon.accounts.dto

import java.util.*

data class Account(
    val id: UUID,
    val owner: String
) {
    fun toAvro() = com.epsilon.accounts.avro.Account(id.toString(), owner)
}

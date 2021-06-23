package com.epsilon.accounts.controller

import com.epsilon.accounts.dto.Account
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api/accounts")
class AccountController {
    @Get
    fun getAccounts(): List<Account> {
        return emptyList()
    }
}

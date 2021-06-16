package com.epsilon.accounts.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api/accounts")
class AccountController {
    @Get
    fun getAccounts(): List<Any> {
        return emptyList()
    }
}

package com.epsilon.accounts.faker

import com.epsilon.accounts.dto.Account
import com.epsilon.accounts.kafka.AccountProducer
import com.github.javafaker.Faker
import io.micronaut.scheduling.annotation.Scheduled
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Singleton

@Singleton
class AccountFaker(
    private val accountProducer: AccountProducer
) {

    private val faker = Faker()

    @Scheduled(fixedDelay = "2s")
    fun sendAccount() {
        val account = Account(
            id = UUID.randomUUID(),
            owner = faker.name().fullName()
        )

        runBlocking {
            accountProducer.send(account)
            println("Sent $account")
        }
    }
}

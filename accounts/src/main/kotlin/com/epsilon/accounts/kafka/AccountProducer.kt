package com.epsilon.accounts.kafka

import com.github.javafaker.Faker
import io.micronaut.scheduling.annotation.Scheduled
import org.apache.kafka.clients.producer.ProducerRecord
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import java.util.*
import javax.inject.Singleton

@Singleton
class AccountProducer {

    private val faker = Faker()

    private val sender = KafkaSender.create<String, com.epsilon.accounts.Account>(
        SenderOptions.create(
            mapOf(
                "bootstrap.servers" to "localhost:29092",
                "schema.registry.url" to "http://localhost:8081",
                "security-protocol" to "PLAINTEXT",
                "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
                "value.serializer" to "io.confluent.kafka.serializers.KafkaAvroSerializer"
            )
        )
    )

    @Scheduled(fixedDelay = "2s")
    fun sendAccount() {
        val account = com.epsilon.accounts.Account(
            UUID.randomUUID().toString(),
            faker.name().fullName()
        )

        ProducerRecord("accounts", account.id.toString(), account)
            .let { SenderRecord.create(it, it.key()) }
            .let { sender.send(Mono.just(it)) }
            .map {
                println("Sent $account")
                it
            }
            .subscribe()
    }
}

package com.epsilon.accounts.kafka

import com.epsilon.accounts.dto.Account
import kotlinx.coroutines.reactive.awaitSingle
import org.apache.kafka.clients.producer.ProducerRecord
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import reactor.kotlin.core.publisher.toMono
import javax.inject.Singleton

@Singleton
class AccountProducer {

    private val sender = KafkaSender.create<String, com.epsilon.accounts.avro.Account>(
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

    private val topic = "accounts"

    suspend fun send(account: Account): SenderResult<String> {
        return account.toAvro()
            .let { ProducerRecord(topic, it.id, it) }
            .let { SenderRecord.create(it, it.key()) }
            .let { sender.send(it.toMono()) }
            .awaitSingle()
    }
}

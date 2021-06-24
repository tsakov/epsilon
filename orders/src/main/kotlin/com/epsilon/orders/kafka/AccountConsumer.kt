package com.epsilon.orders.kafka

import com.epsilon.accounts.avro.Account
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset.EARLIEST
import io.micronaut.configuration.kafka.annotation.OffsetStrategy
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.core.annotation.Blocking
import io.reactivex.Single
import org.apache.logging.log4j.kotlin.logger


@KafkaListener(groupId = "epsilon-orders", offsetReset = EARLIEST, offsetStrategy = OffsetStrategy.ASYNC)
class AccountConsumer {

    private val log = logger()

    // TODO: auto commit
    @Blocking
    @Topic("accounts")
    fun receive(flowable: Single<Account>): Single<Account> {
        return flowable.doOnSuccess {
            log.info("Received account: $it")
        }
    }
}

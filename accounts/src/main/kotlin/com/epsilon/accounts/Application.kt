package com.epsilon.accounts

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.epsilon.accounts")
        .start()
}

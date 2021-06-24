package com.epsilon.orders

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.epsilon.orders")
        .start()
}

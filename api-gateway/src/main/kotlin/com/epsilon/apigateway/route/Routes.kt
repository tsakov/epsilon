package com.epsilon.apigateway.route

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Routes {

    @Bean
    fun myRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            // TODO: add routes
            .build()
    }
}

package com.loreans.restcontroller

import org.axonframework.commandhandling.CommandBus
import org.mockito.Mockito
import org.springframework.context.annotation.Bean

class Configurations {
    @Bean
    fun commandBus(): CommandBus {
        return Mockito.mock(CommandBus::class.java)
    }
}
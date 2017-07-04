package com.loreans

import com.loreans.aggregates.Player
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.model.GenericJpaRepository
import org.axonframework.commandhandling.model.Repository
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider
import org.axonframework.common.jpa.EntityManagerProvider
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.axonframework.spring.config.EnableAxonAutoConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean

import javax.persistence.Basic

import org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage

@EnableAxonAutoConfiguration
@SpringBootApplication
class PlayersDomain {

    @Bean
     fun eventStorageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

    @Bean
    fun jpaPlayerRepository(eventBus: EventBus): Repository<Player> {
        return GenericJpaRepository(enityManagerProvider(), Player::class.java, eventBus)
    }

    private fun enityManagerProvider(): EntityManagerProvider {
        return ContainerManagedEntityManagerProvider()
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val applicationContext = SpringApplication.run(PlayersDomain::class.java, *args)
            val commandBus = applicationContext.getBean(CommandBus::class.java)
            commandBus.dispatch(asCommandMessage<Any>(RegisterPlayerCommand("player1", "batman")))
            commandBus.dispatch(asCommandMessage<Any>(RegisterPlayerCommand("player1", "superman")))
            commandBus.dispatch(asCommandMessage<Any>(RegisterPlayerCommand("player1", "wonderwoman")))
            val bean = applicationContext.getBean(EventStorageEngine::class.java)
        }
    }
}

package com.loreans

import com.loreans.aggregates.Player
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.CommandCallback
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage
import org.axonframework.commandhandling.model.GenericJpaRepository
import org.axonframework.commandhandling.model.Repository
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider
import org.axonframework.common.jpa.EntityManagerProvider
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.axonframework.spring.config.EnableAxonAutoConfiguration
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManager

@EnableAxonAutoConfiguration
@SpringBootApplication
@ImportAutoConfiguration
open class PlayersDomain {

    @Bean
    open fun eventStorageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

    @Bean
    open fun jpaPlayerRepository(eventBus: EventBus, entityManager: EntityManager): Repository<Player> {
        return GenericJpaRepository(enityManagerProvider(entityManager), Player::class.java, eventBus)
    }

    private fun enityManagerProvider(entityManager: EntityManager): EntityManagerProvider {
        val containerManagedEntityManagerProvider = ContainerManagedEntityManagerProvider()
        containerManagedEntityManagerProvider.entityManager = entityManager
        return containerManagedEntityManagerProvider
    }

    @Bean
    open fun axonTransactionManager(platformTransactionManager: PlatformTransactionManager): TransactionManager {
        return SpringTransactionManager(platformTransactionManager)
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val applicationContext = SpringApplication.run(PlayersDomain::class.java, *args)
            val bean = applicationContext.getBean(EntityManager::class.java)
            val commandBus = applicationContext.getBean(CommandBus::class.java)
            val callback: CommandCallback<Any, Any> = object : CommandCallback<Any, Any> {
                override fun onSuccess(commandMessage: CommandMessage<out Any>?, result: Any?) = println("Yeah i have a success")

                override fun onFailure(commandMessage: CommandMessage<out Any>?, cause: Throwable?) {
                    cause?.printStackTrace()
                }

            }
            print(commandBus)
            commandBus.dispatch(asCommandMessage<Any>(RegisterPlayerCommand("player1", "batman")),
                    callback)
        }
    }
}

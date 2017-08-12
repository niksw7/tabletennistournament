/*
package com.loreans.config

import com.loreans.aggregates.Player
import org.axonframework.commandhandling.model.GenericJpaRepository
import org.axonframework.commandhandling.model.Repository
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider
import org.axonframework.common.jpa.EntityManagerProvider
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManager

*/
/**
 * Created by nikeshshetty on 7/6/17.
 *//*

@Configuration
class Configuration {
    @Bean
    open fun localEntityManagerFactoryBean(): LocalEntityManagerFactoryBean {
        return LocalEntityManagerFactoryBean()
    }


    @Bean
    open fun eventStorageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

    @Bean
    open fun jpaPlayerRepository(eventBus: EventBus, entityManager: EntityManager): Repository<Player> {
        print("someone called me")
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

}*/

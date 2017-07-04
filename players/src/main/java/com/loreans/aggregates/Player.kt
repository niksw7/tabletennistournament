package com.loreans.aggregates

import com.loreans.coreapi.PlayerRegisteredEvent
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.eventsourcing.EventSourcingHandler

import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by nikeshshetty on 3/5/17.
 */
@Aggregate(repository = "jpaPlayerRepository")
@Entity
class Player {
    @Id
    @AggregateIdentifier
    private var playerId: String? = null

    private constructor() {}

    @CommandHandler
    constructor(command: RegisterPlayerCommand) {
        apply(PlayerRegisteredEvent(command.playerId, command.playerName))
    }

    @EventSourcingHandler
    fun on(event: PlayerRegisteredEvent) {
        playerId = event.playerId
    }

}

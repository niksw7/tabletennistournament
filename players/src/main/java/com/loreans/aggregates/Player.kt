package com.loreans.aggregates

import com.loreans.coreapi.PlayerRegisteredEvent
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import javax.persistence.Basic
import javax.persistence.Entity
import javax.persistence.Id


/**
 * Created by nikeshshetty on 3/5/17.
 */
@Aggregate
@Entity
class Player {
    @Id
    @AggregateIdentifier
    private lateinit var playerId: String

    @Basic
    private lateinit var playerName: String

    @Basic
    private var selfRating: Int = 0

    private constructor() {}

    @CommandHandler
    constructor(command: RegisterPlayerCommand) {
        AggregateLifecycle.apply(PlayerRegisteredEvent(command.playerId, command.playerName, command.selfRating))
    }

    @EventSourcingHandler
    fun on(event: PlayerRegisteredEvent) {
        playerId = event.playerId
        playerName = event.playerName
        selfRating = event.selfRating
    }

}

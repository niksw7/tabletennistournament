package com.loreans.coreapi

import com.loreans.aggregates.Player
import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created by nikeshshetty on 3/5/17.
 */

class RegisterPlayerCommand(val playerId: String, val playerName: String, val selfRating: Int)

data class PlayerRegisteredEvent(val playerId: String, val playerName: String, val selfRating: Int)


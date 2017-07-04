package com.loreans.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created by nikeshshetty on 3/5/17.
 */

class RegisterPlayerCommand(val playerId: String, val playerName:String)

class PlayerRegisteredEvent(val playerId: String, val playerName: String)
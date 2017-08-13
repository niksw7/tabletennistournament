package com.loreans.aggregates

import com.loreans.coreapi.PlayerRegisteredEvent
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.test.aggregate.AggregateTestFixture
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object PlayerSpec : Spek({
    describe("register a player") {
        beforeGroup {
            var fixture = AggregateTestFixture(Player::class.java)
            it("should register a player successfully") {
                fixture.givenNoPriorActivity()
                        .`when`(RegisterPlayerCommand("somePlayerId", "batman",1))
                        .expectEvents(PlayerRegisteredEvent("somePlayerId", "batman",1))
            }

        }
    }
})

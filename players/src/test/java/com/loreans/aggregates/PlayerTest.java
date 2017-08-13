package com.loreans.aggregates;

import com.loreans.coreapi.PlayerRegisteredEvent;
import com.loreans.coreapi.RegisterPlayerCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by nikeshshetty on 3/5/17.
 */
public class PlayerTest {
    private AggregateTestFixture<Player> fixture;

    @BeforeEach
    public void setup() {
        fixture = new AggregateTestFixture(Player.class);
    }

    @Test
    public void registerPlayer() {
        String playerId = "somePlayerId";
        String playerName = "batman";
        fixture.givenNoPriorActivity()
                .when(new RegisterPlayerCommand(playerId, playerName, 3))
                .expectEvents(new PlayerRegisteredEvent(playerId, playerName, 3));


    }
}
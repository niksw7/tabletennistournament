package com.loreans.aggregates;

import com.loreans.coreapi.PlayerRegisteredEvent;
import com.loreans.coreapi.RegisterPlayerCommand;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nikeshshetty on 3/5/17.
 */
public class PlayerTest {
    private FixtureConfiguration<Player> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(Player.class);
    }

    @Test
    public void registerPlayer() {
        String playerId = "somePlayerId";
        String playerName = "batman";
        fixture.givenNoPriorActivity()
                .when(new RegisterPlayerCommand(playerId, playerName))
                .expectEvents(new PlayerRegisteredEvent(playerId, playerName));


    }
}
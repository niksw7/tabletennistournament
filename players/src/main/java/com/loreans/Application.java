package com.loreans;

import com.loreans.aggregates.Player;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

/**
 * Created by nikeshshetty on 7/3/17.
 */
@Deprecated//We have better ways to do it using annotations in spring.
public class Application {
    public static void main(String[] args) {
        Configuration configuration = DefaultConfigurer.defaultConfiguration().
                configureAggregate(Player.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine()).
                        buildConfiguration();
        configuration.start();


    }
}

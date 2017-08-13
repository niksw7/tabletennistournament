package com.loreans

import com.loreans.coreapi.PlayerRegisteredEvent
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.GenericCommandMessage
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = arrayOf(PlayersDomainAppStarter::class))
@TestPropertySource("/test.properties")
class PlayersDomainAppStarterTest {

    @Autowired
    lateinit var commandBus: CommandBus

    @Autowired
    lateinit var embeddedEventStore: EmbeddedEventStore

    @Test
    fun testSampleStartupOfApplication() {
        commandBus.dispatch(GenericCommandMessage.asCommandMessage<Any>(RegisterPlayerCommand("1", "batman", 9)))
        commandBus.dispatch(GenericCommandMessage.asCommandMessage<Any>(RegisterPlayerCommand("2", "superman", 8)))

        val readEventsForBatman = embeddedEventStore.readEvents("1")
        val readEventsForSuperman = embeddedEventStore.readEvents("2")

        val message1 = readEventsForBatman.next()
        assert(message1.payload as PlayerRegisteredEvent == PlayerRegisteredEvent("1", "batman", 9))

        val message2 = readEventsForSuperman.next()
        assert(message2.payload as PlayerRegisteredEvent == PlayerRegisteredEvent("2", "superman", 8))


    }
}
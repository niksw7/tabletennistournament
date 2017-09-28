package com.loreans

import com.loreans.coreapi.PlayerRegisteredEvent
import com.loreans.coreapi.RegisterPlayerCommand
import com.loreans.repository.PlayerViewRepository
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.GenericCommandMessage
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = arrayOf(PlayersDomainAppStarter::class))
@TestPropertySource(properties = arrayOf("spring.datasource.url=jdbc:h2:mem:test_mem"))
class PlayersDomainAppStarterTest {

    @Autowired
    lateinit var commandBus: CommandBus

    @Autowired
    lateinit var embeddedEventStore: EmbeddedEventStore

    @Autowired
    lateinit var playerViewRepository: PlayerViewRepository

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

        assertEquals(2, playerViewRepository.count())


    }
}
package com.loreans.restcontroller

import com.loreans.aggregates.Player
import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage
import org.axonframework.commandhandling.model.Repository
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicInteger


@RestController
class PlayerApiController {
    @Autowired
    lateinit var commandBus: CommandBus

    @Autowired
    lateinit var eventStorageEngine: EventStorageEngine
    val atomicInteger: AtomicInteger = AtomicInteger()

    @PostMapping("register-player")
    fun registerPlayer(@RequestBody registerPlayerRequest: RegisterPlayerRequest) {
        val commandMessage = asCommandMessage<RegisterPlayerCommand>(RegisterPlayerCommand(atomicInteger.getAndIncrement().toString(), registerPlayerRequest.name, registerPlayerRequest.skillRating))
        commandBus.dispatch(commandMessage)
    }

    /*@GetMapping("welcome-back")
    fun welcomeback(@RequestParam("id") id: String): String {
        val readEvents = eventStorageEngine.readEvents(id)
        readEvents.asStream().forEach { msg -> print(msg) }
        val load = players.load(id)
        println(load!!.identifier())
        return load.identifier()
    }*/
}

data class RegisterPlayerRequest(val name: String, val age: Int, val skillRating: Int)

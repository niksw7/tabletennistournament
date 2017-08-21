package com.loreans.restcontroller

import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger


@RestController
class PlayerApiController {
    @Autowired
    lateinit var commandBus: CommandBus

    val atomicInteger: AtomicInteger = AtomicInteger()

    @PostMapping("register-player")
    fun registerPlayer(@RequestBody registerPlayerRequest: RegisterPlayerRequest) {
        val commandMessage = asCommandMessage<RegisterPlayerCommand>(RegisterPlayerCommand(atomicInteger.getAndIncrement().toString(), registerPlayerRequest.name, registerPlayerRequest.skillRating))
        commandBus.dispatch(commandMessage)
    }
}

data class RegisterPlayerRequest(val name: String, val age: Int, val skillRating: Int)

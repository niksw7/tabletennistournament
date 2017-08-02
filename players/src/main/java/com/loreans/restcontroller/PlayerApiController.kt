package com.loreans.restcontroller

import com.loreans.coreapi.RegisterPlayerCommand
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.GenericCommandMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by nikeshshetty on 8/1/17.
 */

@RestController
class playerapis {
    @Autowired
    lateinit var commandBus: CommandBus
    val atomicInteger: AtomicInteger = AtomicInteger()

    @PostMapping("register-player")
    fun registerPlayer(@RequestBody registerPlayerRequest: RegisterPlayerRequest) {
        print("yeah you are finally hitting on me!")
        commandBus.dispatch(GenericCommandMessage.asCommandMessage<Any>(RegisterPlayerCommand(atomicInteger.getAndIncrement().toString(), registerPlayerRequest.name)))
    }

    @GetMapping("welcome-back")
    fun welcomeback(): String {
        print("yeah you are finally hitting on me!")
        return "Great to see you"
    }
}

data class RegisterPlayerRequest(val name: String, val age: Int, val skillRating: Int)

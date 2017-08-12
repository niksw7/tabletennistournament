package com.loreans

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PlayersDomainAppStarter {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val applicationContext = SpringApplication.run(PlayersDomainAppStarter::class.java, *args)
/*            val bean = applicationContext.getBean(EntityManager::class.java)
            val commandBus = applicationContext.getBean(CommandBus::class.java)
            val callback: CommandCallback<Any, Any> = object : CommandCallback<Any, Any> {
                override fun onSuccess(commandMessage: CommandMessage<out Any>?, result: Any?) = println("Yeah i have a success")

                override fun onFailure(commandMessage: CommandMessage<out Any>?, cause: Throwable?) {
                    cause?.printStackTrace()
                }

            }
            val registerPlayerCommand = RegisterPlayerCommand("player1", "batman", 1)
            commandBus.dispatch(GenericCommandMessage.asCommandMessage(registerPlayerCommand), callback)
            commandBus.dispatch(GenericCommandMessage.asCommandMessage(registerPlayerCommand), callback)*/
        }
    }
}

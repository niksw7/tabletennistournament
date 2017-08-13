package com.loreans

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PlayersDomainAppStarter {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(PlayersDomainAppStarter::class.java, *args)
        }
    }
}

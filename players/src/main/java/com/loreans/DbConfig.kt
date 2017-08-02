package com.loreans

import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.context.annotation.Bean
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean

/**
 * Created by nikeshshetty on 7/6/17.
 */
@AutoConfigurationPackage
class DbConfig {
    @Bean
    open fun localEntityManagerFactoryBean(): LocalEntityManagerFactoryBean {
        return LocalEntityManagerFactoryBean()
    }

}
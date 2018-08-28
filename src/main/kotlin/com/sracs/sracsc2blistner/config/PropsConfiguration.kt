package com.sracs.sracsc2blistner.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@ComponentScan(basePackages = arrayOf("com.sracs.sracsc2blistner"))
@PropertySource(value = *arrayOf("classpath:props.properties"))
class PropsConfiguration {

    @Bean
    fun propertyConfigInDev(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}
package com.sracs.sracsc2blistner.config

import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateConfig {
    private val LOGGER = LoggerFactory.getLogger(RestTemplateConfig::class.java)

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        val timeout = 5000
        return restTemplateBuilder
                .setConnectTimeout(timeout)
                .setReadTimeout(timeout)
                .build()
    }

}
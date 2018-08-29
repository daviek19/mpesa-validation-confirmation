package com.sracs.sracsc2blistner.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.util.ResourceUtils
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    private val LOGGER = LoggerFactory.getLogger(FirebaseConfig::class.java)

    @Bean
    fun firebaseApp(): FirebaseApp? {
        var firebaseApp: FirebaseApp? = null

        try {
            val options = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(ClassPathResource("classpath:firebase-admin.json").getInputStream()))
                    .setDatabaseUrl("https://sracs-3e457.firebaseio.com")
                    .build()
            firebaseApp = FirebaseApp.initializeApp(options)
        } catch (ex: Exception) {
            LOGGER.error("Error initializing firebase app {} ", ex.message)
        }

        return firebaseApp
    }
}
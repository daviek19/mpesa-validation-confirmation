package com.sracs.sracsc2blistner.mpesastkpush;

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = arrayOf("v1/stk-push"),
        headers = arrayOf("Accept=application/json"),
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
public class StkPushController {

    private val LOGGER = LoggerFactory.getLogger(StkPushController::class.java)

    @PostMapping("/call-back")
    fun c2bConfirmation(@RequestBody postData: Any): ResponseEntity<*> {

        LOGGER.info("stk push results {}", postData.toString());

        return ResponseEntity<Any>(null, HttpStatus.ACCEPTED);
    }
}

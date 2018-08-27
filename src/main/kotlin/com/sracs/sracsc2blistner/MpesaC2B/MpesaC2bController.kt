package com.sracs.sracsc2blistner.MpesaC2B;

import com.google.gson.Gson
import com.sracs.sracsc2blistner.MpesaC2B.support.MpesaResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This API enables Paybill and Buy Goods merchants to integrate to M-Pesa and receive real time payments notifications.
 */
@RestController
@RequestMapping(path = arrayOf("v1/c2b-api"),
        headers = arrayOf("Accept=application/json"),
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class MpesaC2bController {

    private val LOGGER = LoggerFactory.getLogger(MpesaC2bController::class.java)

    /**
     * Whenever M-Pesa receives a transaction on your paybill, M-Pesa triggers a confirmation request of the transaction
     * on this endpoint which then should respond with a success acknowledging the confirmation.
     */
    @PostMapping("/confirm")
    fun c2bConfirmation(@RequestBody postData: Any): ResponseEntity<*> {

        LOGGER.info("confirmation request for {}", Gson().toJson(postData));

        return ResponseEntity<MpesaResponse>(
                MpesaResponse("0", "Confirmation received successfully."),
                HttpStatus.OK)
    }

    /**
     * Validation is an optional feature.</p>
     *
     * Whenever M-Pesa receives a transaction on your paybill, M-Pesa triggers a validation request against
     * this endpoint.</p>
     * M-Pesa completes or cancels the transaction depending on this validation response.
     */
    @PostMapping("/validate")
    private fun c2bValidation(@RequestBody postData: Any): ResponseEntity<*> {

        LOGGER.info("validation request for {}", Gson().toJson(postData));

        return ResponseEntity<MpesaResponse>(
                MpesaResponse("0", "Validation passed successfully."),
                HttpStatus.OK)
    }

}



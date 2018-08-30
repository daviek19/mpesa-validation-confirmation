package com.sracs.sracsc2blistner.mpesaC2b;

import com.google.firebase.messaging.FirebaseMessagingException
import com.google.gson.Gson
import com.sracs.sracsc2blistner.fcm.support.FirebaseAdminSdkService
import com.sracs.sracsc2blistner.fcm.support.SearchUserResponse
import com.sracs.sracsc2blistner.mpesaC2b.support.ConfirmationResponse
import com.sracs.sracsc2blistner.mpesaC2b.support.MpesaResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.client.support.BasicAuthorizationInterceptor
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset
import javax.annotation.PostConstruct

/**
 * This API enables Paybill and Buy Goods merchants to integrate to M-Pesa and receive real time payments notifications.
 */
@RestController
@RequestMapping(path = arrayOf("v1/c2b-api"),
        headers = arrayOf("Accept=application/json"),
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class MpesaC2bController {

    private val LOGGER = LoggerFactory.getLogger(MpesaC2bController::class.java)

    lateinit var restTemplate: RestTemplate;

    @Autowired
    lateinit var firebaseAdminSdkService: FirebaseAdminSdkService

    @Value("\${sracs-api-user}")
    lateinit var sracsApiUsername: String

    @Value("\${sracs-api-password}")
    lateinit var sracsApiPassword: String

    @Value("\${sracs-search-user-url}")
    lateinit var sracsSearchUserEndpoint: String


    /**
     * run once immediately after the bean’s initialization to set the restTemplate
     * with authorization details
     */
    @PostConstruct
    fun init() {
        restTemplate = RestTemplate()
        restTemplate.getMessageConverters()
                .add(0, StringHttpMessageConverter(Charset.forName("UTF-8")))
        restTemplate.getInterceptors()
                .add(BasicAuthorizationInterceptor(sracsApiUsername, sracsApiPassword))
    }

    /**
     * Whenever M-Pesa receives a transaction on your paybill, M-Pesa triggers a confirmation request of the transaction
     * on this endpoint which then should respond with a success acknowledging the confirmation.
     */
    @PostMapping("/confirm")
    fun c2bConfirmation(@RequestBody postData: Any): ResponseEntity<*> {

        LOGGER.info("confirmation request for {}", postData.toString())

        //The mpesaResponse from mpesa
        val mpesaResponse: ConfirmationResponse = Gson().fromJson(Gson().toJson(postData), ConfirmationResponse::class.java);

        //Todo send the response to sracs DB

        //Query for fcm-token from sracs api with the returned msisdn
        val fcmQueryUrl: String = sracsSearchUserEndpoint + mpesaResponse.msisdn;

        try {
            val fcmQueryResponse = restTemplate.getForEntity(fcmQueryUrl, Any::class.java);
            val searchUserResponse: SearchUserResponse = Gson().fromJson(Gson().toJson(fcmQueryResponse.body), SearchUserResponse::class.java);

            LOGGER.info("found search details {}", searchUserResponse.toString())
            if (searchUserResponse.data.user !== null && mpesaResponse != null) {
                firebaseAdminSdkService.sendFcmMessage(searchUserResponse.data.user,mpesaResponse)
            }

        } catch (exception: FirebaseMessagingException) {
            LOGGER.error("a firebase error occurred when sending fcm-token: {}", exception.toString());
        } catch (exception: RestClientException) {
            LOGGER.error("an error occurred when fetching details from sracs API: {}", exception.toString());
        } catch (exception: Exception) {
            LOGGER.error("a general error occurred when sending fcm-token: {}", exception.toString());
        }

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

        LOGGER.info("validation request for {}", Gson().toJson(postData))

        return ResponseEntity<MpesaResponse>(
                MpesaResponse("0", "Validation passed successfully."),
                HttpStatus.OK)
    }

}



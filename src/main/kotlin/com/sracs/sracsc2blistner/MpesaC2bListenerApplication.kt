package com.sracs.sracsc2blistner

import com.sracs.sracsc2blistner.fcm.support.MpesaTransactionsDb
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.math.log

@SpringBootApplication
class MpesaC2bListenerApplication

fun main(args: Array<String>) {
    runApplication<MpesaC2bListenerApplication>(*args)
}

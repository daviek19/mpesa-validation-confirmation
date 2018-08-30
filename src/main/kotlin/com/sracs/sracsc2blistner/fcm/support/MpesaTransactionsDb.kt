package com.sracs.sracsc2blistner.fcm.support

import com.sracs.sracsc2blistner.mpesaC2b.MpesaC2bController
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Temporary in memory mpesa transactions database
 */
class MpesaTransactionsDb {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(MpesaC2bController::class.java)

        private var mpesaTransactionsDb: LinkedList<String>

        init {
            LOGGER.info("we have initialized the MpesaTransactionsDb");
            mpesaTransactionsDb = LinkedList<String>()
        }

        @JvmStatic
        fun addTransaction(transactionId: String): Unit {
            LOGGER.info("{} was added to MpesaTransactionsDb", transactionId);
            mpesaTransactionsDb.add(transactionId);
        }

        @JvmStatic
        fun getDatabase(): LinkedList<String> {
            return mpesaTransactionsDb;
        }
    }
}
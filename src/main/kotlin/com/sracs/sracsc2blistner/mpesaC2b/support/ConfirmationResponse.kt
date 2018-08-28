package com.sracs.sracsc2blistner.mpesaC2b.support

import com.google.gson.annotations.SerializedName

data class ConfirmationResponse(
        @SerializedName("TransID") val transID: String,
        @SerializedName("TransAmount") val transAmount: String,
        @SerializedName("MiddleName") val middleName: String,
        @SerializedName("BillRefNumber") val billRefNumber: String,
        @SerializedName("TransTime") val transTime: String,
        @SerializedName("BusinessShortCode") val businessShortCode: String,
        @SerializedName("FirstName") val firstName: String,
        @SerializedName("TransactionType") val transactionType: String,
        @SerializedName("LastName") val lastName: String,
        @SerializedName("MSISDN") val msisdn: String,
        @SerializedName("OrgAccountBalance") val orgAccountBalance: String,
        @SerializedName("InvoiceNumber") val invoiceNumber: String,
        @SerializedName("ThirdPartyTransID") val thirdPartyTransID: String
)

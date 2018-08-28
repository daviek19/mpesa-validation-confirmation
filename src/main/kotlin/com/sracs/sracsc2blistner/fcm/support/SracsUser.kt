package com.sracs.sracsc2blistner.fcm.support

import com.google.gson.annotations.SerializedName

data class SracsUser(
        @SerializedName("FcmToken") val fcmToken: String,
        @SerializedName("MobileNumber") val mobileNumber: String,
        @SerializedName("FullName") val fullName: String,
        @SerializedName("ConversationId") val conversationId: String
)
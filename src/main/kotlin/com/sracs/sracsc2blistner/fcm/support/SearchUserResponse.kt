package com.sracs.sracsc2blistner.fcm.support

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(@SerializedName("status") val status: String,
                              @SerializedName("data") val data: Data)

data class Data(@SerializedName("User") val user: SracsUser)


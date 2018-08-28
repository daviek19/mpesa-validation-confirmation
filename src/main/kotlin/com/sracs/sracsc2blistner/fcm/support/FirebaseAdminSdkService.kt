package com.sracs.sracsc2blistner.fcm.support

import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FirebaseAdminSdkService {

    private val LOGGER = LoggerFactory.getLogger(FirebaseAdminSdkService::class.java)

    @Autowired
    lateinit var firebaseApp: FirebaseApp

    @Throws(FirebaseMessagingException::class, FcmTokenException::class)
    fun sendFcmMessage(user: SracsUser): String {

        val response: String;

        if (!user.fcmToken.isEmpty()) {
            LOGGER.info("sending message to {} of conversation id {} ", user.fcmToken, user.conversationId);

            val ANDROID_NEWS_ICON_RESOURCE = "news_alert_icon"
            val APNS_NEWS_BADGE_RESOURCE = 42
            val WEBPUSH_NEWS_ICON_URL = "https://png.icons8.com/ios/150/3584fc/android-os-filled.png"

            val title = "Payment Confirmation"
            val body = "Dear " + user.fullName + ". We received your payment."

            val message = Message.builder()
                    .setNotification(Notification(title, body))
                    .setAndroidConfig(AndroidConfig.builder()
                            .setNotification(AndroidNotification.builder()
                                    .setIcon(ANDROID_NEWS_ICON_RESOURCE)
                                    .build())
                            .build())
                    .setApnsConfig(ApnsConfig.builder()
                            .setAps(Aps.builder()
                                    .setBadge(APNS_NEWS_BADGE_RESOURCE)
                                    .build())
                            .build())
                    .setWebpushConfig(WebpushConfig.builder()
                            .setNotification(WebpushNotification(null, null, WEBPUSH_NEWS_ICON_URL))
                            .build())
                    .putData("data", "just some json object")
                    .setToken(user.fcmToken)
                    .build()

            response = FirebaseMessaging.getInstance(firebaseApp).send(message)

            LOGGER.info("the response for send message {}", response);
        } else {
            throw FcmTokenException()
        }
        return response
    }

}

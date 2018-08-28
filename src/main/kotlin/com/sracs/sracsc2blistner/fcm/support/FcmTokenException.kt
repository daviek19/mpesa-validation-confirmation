package com.sracs.sracsc2blistner.fcm.support

class FcmTokenException : RuntimeException {
    constructor() : super("FcmToken error...null") {}

    constructor(message: String) : super(message) {}
}
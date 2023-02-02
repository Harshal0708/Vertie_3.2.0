package com.vertie.modules.base

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService

abstract class BaseCustomFirebaseMessagingService: FirebaseMessagingService() {
    abstract fun getDashboardIntent(data: MutableMap<String, String>): Intent
}
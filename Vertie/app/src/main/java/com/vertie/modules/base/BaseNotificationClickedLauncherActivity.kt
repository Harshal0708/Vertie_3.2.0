package com.vertie.modules.base

import android.content.Intent
import android.os.Bundle
import com.vertie.utils.services.CustomFirebaseMessagingService
import java.util.HashMap

abstract class BaseNotificationClickedLauncherActivity : BaseActivity() {

    private val GOOGLE_MESSAGE_ID = "google.message_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForegroundNotificationClick()
        checkBackgroundNotificationClick()
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        checkForegroundNotificationClick()
    }
    @SuppressWarnings("unchecked")
    private fun checkForegroundNotificationClick() {
        val foregroundNotificationData = intent.extras?.getSerializable(CustomFirebaseMessagingService.EXTRA_NOTIFICATION_DATA) as? HashMap<String, String>
        if (foregroundNotificationData != null) {
            pushNotificationClicked(foregroundNotificationData)
        }
    }
    private fun checkBackgroundNotificationClick() {
        val backgroundNotificationID = intent?.extras?.getString(GOOGLE_MESSAGE_ID)
        if (backgroundNotificationID != null) {
            pushNotificationClicked(bundleToHashMap(intent?.extras!!))
        }
    }

    abstract fun pushNotificationClicked(data: HashMap<String, String>)
}
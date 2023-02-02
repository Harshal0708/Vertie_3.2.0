package com.vertie.utils.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.vertie.Constants
import com.vertie.R
import com.vertie.modules.base.BaseCustomFirebaseMessagingService
import com.vertie.modules.dashboard.DashboardActivity
import com.google.firebase.messaging.RemoteMessage
import java.io.Serializable

class CustomFirebaseMessagingService: BaseCustomFirebaseMessagingService() {
    private val TAG = this.javaClass.simpleName
    //TODO: add  repository with the one used to update the token
    companion object {
        private const val CHANNEL_ID = "channel_id"
        const val EXTRA_NOTIFICATION_DATA = "notification_data"
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        // update the api with the new firebase token is user is logged in
        //TODO: use the desired repository to update the firebase token
//        val token: String = repository.getUserToken()
//        if (token.isNotEmpty()) {
//            repository.updateFirebaseToken(token = p0)
//        }
    }

    // Listen on the notification only if the app is in foreground while background notifications couldn't be listened as it's handled automatic by firebase
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: ${remoteMessage.notification}")
        // build the notification and display it in foreground
        createNotificationChannel()
        val notification = remoteMessage.notification
        if (notification != null) {
            buildNotification(notification.title, notification.body, remoteMessage.data)
        }
        val intent = Intent()
        intent.action = Constants.BROADCAST_REFRESH
        sendBroadcast(intent)
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(title: String?, body: String?, data: MutableMap<String, String>) {
        val intent = getDashboardIntent(data)
        val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setStyle(NotificationCompat.BigTextStyle())
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(title.hashCode(), builder.build())
        }
    }
    // HANDLE FOREGROUND NOTIFICATION CLICK
    override fun getDashboardIntent(data: MutableMap<String, String>): Intent {
        return Intent(this, DashboardActivity::class.java).also {
            it.putExtra(EXTRA_NOTIFICATION_DATA, HashMap<String, String>(data) as Serializable)
        }
    }
}

package com.example.practicaltest01

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.Objects

class Practical_Service : Service() {
    var processingThread: ProcessingThread? = null

    override fun onCreate() {
        super.onCreate()

        val CHANNEL_ID = "Colocviu"
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            //val notification: Notification? = Builder(this, CHANNEL_ID)
            .setContentTitle("Colocviu EIM")
            .setContentText("")
            .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        processingThread = ProcessingThread(this)
        processingThread!!.start()
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        processingThread!!.stopThread()
    }
}

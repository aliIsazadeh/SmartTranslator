package com.esum.core.web_socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class LogService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> {
                start()
            }

            Actions.STOP.toString() -> {

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

        val notification = NotificationCompat.Builder(
            this, "running_channel"
        ).setSmallIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
            .setContentTitle("run is actiive")
            .setContentText("timer").build()
        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP
    }

}
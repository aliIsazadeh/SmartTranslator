package com.esum.core.web_socket

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.esum.common.constraints.ResultConstraints
import kotlinx.coroutines.flow.MutableSharedFlow
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketListener() : Service() {

    private lateinit var client: OkHttpClient

    private val CHANNEL_ID = "WebSocketChannelID"


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    val message: MutableSharedFlow<ResultConstraints<String>> = MutableSharedFlow()

    private inner class MessageWebSocketListener : WebSocketListener() {
        private val NORMAL_CLOSURE_STATUS = 1000
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            val subscribe = """
            {
                "eventName": "subscribe",
                "authorization": "a0fbf7bd530960365bd5c1ca0caf5b738ba95c5e",
                "eventData": {
                    "thresholdLevel": 5,
                    "tickers": ["xauusd"]
                }
            }
        """.trimIndent()

            webSocket.send(subscribe)
        }


        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            message.tryEmit(ResultConstraints.Success(bytes.utf8()))
//            output("Receiving " + bytes.toString())
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            message.tryEmit(ResultConstraints.Success("socket closed"))

//            output("Closing: $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            message.tryEmit(ResultConstraints.Error("error is ${t.message}"))
//            output("Error: ${t.message}")
        }


    }

    override fun onCreate() {
        super.onCreate()
        client = OkHttpClient()
//        start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start WebSocket connection here
        val request: Request = Request.Builder().url("wss://api.tiingo.com/fx").headers(
            headers = Headers.headersOf(
                "Authorization",
                "Token a0fbf7bd530960365bd5c1ca0caf5b738ba95c5e"
            )
        ).build()
        val listener: MessageWebSocketListener = MessageWebSocketListener()
        val ws: WebSocket = client.newWebSocket(request = request, listener = listener)

        // Clean up resources when done
        client.dispatcher.executorService.shutdown()

        // Create a notification for the foreground service
        val notification: Notification = createNotification()

        // Start the service as a foreground service
        startForeground(1, notification)

        return START_STICKY
    }

    private fun createNotification(): Notification {

        val notificationIntent = Intent(this, null)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("WebSocket Service")
            .setContentText("WebSocket connection active")
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()

    }

    override fun onDestroy() {
        super.onDestroy()
        client.dispatcher.executorService.shutdown()
    }

    private fun start() {
        val request: Request = Request.Builder().url("ws://echo.websocket.org").headers(
            headers = Headers.headersOf(
                "Authorization",
                "Token a0fbf7bd530960365bd5c1ca0caf5b738ba95c5e"
            )
        ).build()
        val listener: MessageWebSocketListener = MessageWebSocketListener()
        val ws: WebSocket = client.newWebSocket(request = request, listener = listener)

        // Clean up resources when done
        client.dispatcher.executorService.shutdown()
    }


}
package com.esum.core.web_socket


import android.util.Log
import com.esum.common.constraints.ResultConstraints
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MessageListener(private val url : String ,private val dispatcher: CoroutineDispatcher) {

    operator fun invoke() = flow<ResultConstraints<String>>  {

            val client = HttpClient {
                install(WebSockets) {

                }
            }

            client.webSocket(
                method = HttpMethod.Get,
                host = url ,
                path = "/fx",
                request = {
                    header("Authorization", "Token a0fbf7bd530960365bd5c1ca0caf5b738ba95c5e")
                }
            ) {
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

                send(subscribe)

                while (true) {
                    val message = incoming.receive() as? Frame.Text ?: continue
                    Log.d("MessageListener" , message.readText() )

                    emit(ResultConstraints.Success(message.readText()))
                }
            }
        }.flowOn(dispatcher).catch {
            emit(ResultConstraints.Error(it.message.toString()))
    }



}
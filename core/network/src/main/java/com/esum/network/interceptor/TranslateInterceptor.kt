package com.esum.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class TranslateInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "a8c484a0b1mshd2eb9728993d4c4p13b074jsne7ae553be513")
            .addHeader("X-RapidAPI-Host", "translated-mymemory---translation-memory.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}
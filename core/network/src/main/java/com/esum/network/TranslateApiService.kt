package com.esum.network

import com.esum.network.model.TranslateResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApiService {


    @GET("get")
    suspend fun translate(
        @Query("langpair") langpair: String,
        @Query("q") q: String,
        @Query("mt") mt: String = "1",
        @Query("onlyprivate") onlyprivate: String = "0",
        @Query("de") de: String = "a@b.c"
    ) : TranslateResult


}
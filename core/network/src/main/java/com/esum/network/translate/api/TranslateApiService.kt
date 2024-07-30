package com.esum.network.translate.api

import com.esum.network.translate.model.TranslateResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslateApiService {

    @FormUrlEncoded
    @POST("/translate")
    suspend fun translate(
        @Field("source") sourceLanguage: String,
        @Field("target") targetLanguage: String,
        @Field("q") text : String
    ): Response<TranslateResult>


}
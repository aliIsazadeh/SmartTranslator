package com.esum.network.translate.api

import com.esum.network.translate.model.TranslateResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TranslateApiService {


    @FormUrlEncoded
    @POST("/translate")
    suspend fun translate(
        @Field("source_language") sourceLanguage: String,
        @Field("target_language") targetLanguage: String,
        @Field("text") text: String
    ): Response<TranslateResult>


}
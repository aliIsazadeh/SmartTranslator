package com.esum.network

import com.esum.common.lagnuage.Languages
import com.esum.network.model.TranslateResult
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslateApiService {


    @FormUrlEncoded
    @POST("/translate")
    suspend fun translate(
        @Field("source_language") sourceLanguage: String,
        @Field("target_language") targetLanguage: String,
        @Field("text") text: String
    ): TranslateResult


}
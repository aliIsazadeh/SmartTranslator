package com.esum.network.translate.model

import com.esum.network.translate.model.TranslateData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslateResult(
    @Json(name ="data")
    val data: TranslateData,
    @Json(name ="status")
    val status: String
)
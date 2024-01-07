package com.esum.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslateResult(
    @Json(name ="data")
    val data: TranslateData,
    @Json(name ="status")
    val status: String
)
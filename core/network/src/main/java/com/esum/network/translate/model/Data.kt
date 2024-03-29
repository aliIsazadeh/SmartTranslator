package com.esum.network.translate.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslateData(
    @Json(name = "translatedText")
    val translatedText: String
)
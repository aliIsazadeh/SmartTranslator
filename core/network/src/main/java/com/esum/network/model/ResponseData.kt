package com.esum.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseData(
    @Json(name = "match") val match: Int,
    @Json(name = "translatedText") val translatedText: String
)
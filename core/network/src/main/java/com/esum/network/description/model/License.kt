package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class License(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)
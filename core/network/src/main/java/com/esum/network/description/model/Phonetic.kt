package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Phonetic(
    @Json(name = "audio")
    val audio: String?,
    @Json(name = "license")
    val license: License?,
    @Json(name = "sourceUrl")
    val sourceUrl: String?,
    @Json(name = "text")
    val text: String?
)
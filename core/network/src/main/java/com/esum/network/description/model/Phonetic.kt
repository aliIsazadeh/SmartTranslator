package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Phonetic(
    @field:Json(name = "audio")
    val audio: String,
    @field:Json(name = "license")
    val license: License,
    @field:Json(name = "sourceUrl")
    val sourceUrl: String,
    @field:Json(name = "text")
    val text: String
)
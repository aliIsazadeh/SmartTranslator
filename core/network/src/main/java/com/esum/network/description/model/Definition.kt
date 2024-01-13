package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Definition(
    @Json(name = "antonyms")
    val antonyms: List<String?>,
    @Json(name = "definition")
    val definition: String?,
    @Json(name = "example")
    val example: String?,
    @Json(name = "synonyms")
    val synonyms: List<String?>
)
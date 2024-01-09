package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Definition(
    @field:Json(name = "antonyms")
    val antonyms: List<String?>,
    @field:Json(name = "definition")
    val definition: String,
    @field:Json(name = "example")
    val example: String,
    @field:Json(name = "synonyms")
    val synonyms: List<String?>
)
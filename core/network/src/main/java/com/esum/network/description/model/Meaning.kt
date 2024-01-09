package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meaning(
    @field:Json(name = "antonyms")
    val antonyms: List<Any>,
    @field:Json(name = "definitions")
    val definitions: List<Definition>,
    @field:Json(name = "partOfSpeech")
    val partOfSpeech: String,
    @field:Json(name = "synonyms")
    val synonyms: List<String>
)
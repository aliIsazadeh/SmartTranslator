package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meaning(
    @Json(name = "antonyms")
    val antonyms: List<Any>,
    @Json(name = "definitions")
    val definitions: List<Definition>,
    @Json(name = "partOfSpeech")
    val partOfSpeech: String,
    @Json(name = "synonyms")
    val synonyms: List<String>
)
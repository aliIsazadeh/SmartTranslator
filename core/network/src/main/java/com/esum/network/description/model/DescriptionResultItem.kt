package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DescriptionResultItem(
    @field:Json(name = "license")
    val license: License,
    @field:Json(name = "meanings")
    val meanings: List<Meaning>,
    @field:Json(name = "phonetic")
    val phonetic: String,
    @field:Json(name = "phonetics")
    val phonetics: List<Phonetic>,
    @field:Json(name = "sourceUrls")
    val sourceUrls: List<String>,
    @field:Json(name = "word")
    val word: String
)
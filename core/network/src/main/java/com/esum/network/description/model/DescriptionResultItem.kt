package com.esum.network.description.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DescriptionResultItem(
    @Json(name = "license")
    val license: License?,
    @Json(name = "meanings")
    val meanings: List<Meaning>?,
    @Json(name = "phonetic")
    val phonetic: String?,
    @Json(name = "phonetics")
    val phonetics: List<Phonetic>,
    @Json(name = "sourceUrls")
    val sourceUrls: List<String?>,
    @Json(name = "word")
    val word: String
)
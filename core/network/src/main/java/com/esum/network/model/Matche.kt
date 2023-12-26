package com.esum.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Matche(
    @Json(name = "create-date") val createDate: String,
    @Json(name = "created-by") val createdBy: String,
    @Json(name = "id") val id: String,
    @Json(name = "last-update-date") val lastUpdateDate: String,
    @Json(name = "last-updated-by") val lastUpdatedBy: String,
    @Json(name = "match") val match: Double,
    @Json(name = "quality") val quality: String,
    @Json(name = "reference") val reference: Any,
    @Json(name = "segment") val segment: String,
    @Json(name = "source") val source: String,
    @Json(name = "subject") val subject: String,
    @Json(name = "target") val target: String,
    @Json(name = "translation") val translation: String,
    @Json(name = "usage-count") val usageCount: Int
)
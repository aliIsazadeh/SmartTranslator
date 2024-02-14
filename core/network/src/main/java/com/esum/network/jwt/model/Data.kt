package com.esum.testosm.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val avatar: String?,
    val bio: String?,
    val followersCount: Int?,
    val followingCount: Int?,
    val id: Int?,
    val name: String?,
    val token: String
)
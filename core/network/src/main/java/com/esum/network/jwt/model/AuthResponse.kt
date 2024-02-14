package com.esum.testosm.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    val data: Data?,
    val errorMessage: String?
)
package com.esum.testosm.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequestParams(
    @Json(name = "name") val name : String ,
    @Json(name = "email") val email : String ,
    @Json(name = "password") val password : String
)
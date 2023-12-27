package com.esum.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslateResult(
    @Json(name = "exception_code") val exceptionCode: Int?,
    @Json(name = "matches") val matches: List<Matche>,
    @Json(name = "mtLangSupported") val mtLangSupported: String?,
    @Json(name = "quotaFinished") val quotaFinished: Boolean,
    @Json(name = "responderId") val responderId: Int?,
    @Json(name = "responseData") val responseData: ResponseData,
    @Json(name = "responseDetails") val responseDetails: String,
    @Json(name = "responseStatus") val responseStatus: Int
)
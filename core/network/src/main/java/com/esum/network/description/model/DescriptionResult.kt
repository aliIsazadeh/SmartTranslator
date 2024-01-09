package com.esum.network.description.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DescriptionResult : ArrayList<DescriptionResultItem>()
package com.esum.feature.card.domain.model

import com.esum.common.lagnuage.Languages

data class Card(
    val original: String = "",
    val originalLanguage: Languages = Languages.Farsi,
    val translate: String = "",
    val translateLanguage: Languages = Languages.English,
    val correctAnswerCount: Int = 0,
    val sentence: String? = null,
    val updateDate: String = "",
    val createDate: String = "",
)
package com.esum.feature.card.domain.model

import com.esum.common.lagnuage.Languages

data class Card(
    val original : Pair<String , Languages> = Pair("" , Languages.Farsi),
    val translate : Pair<String , Languages> = Pair("" , Languages.English),
    val correctAnswerCount : Int = 0,
    val sentence : String ? = null
)
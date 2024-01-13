package com.esum.feature.card.presentation.state

import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import java.util.UUID

data class AddCardScreenState(
    val id : UUID ? = null,
    val originalText: String = "",
    val originalLanguages: Languages = Languages.Farsi,
    val translateText: String = "",
    val translateLanguages: Languages = Languages.English,
    val sentence : String  = "",
    val audio : String ="",
    val description: DescriptionModel ? = null,
    val correctAnswer : Int  = 0,
)
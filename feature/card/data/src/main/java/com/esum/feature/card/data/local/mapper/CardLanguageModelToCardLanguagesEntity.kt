package com.esum.feature.card.data.local.mapper

import com.esum.database.entity.model.CardWithLanguageModel
import com.esum.database.entity.relations.CardWithLanguages

fun CardWithLanguageModel.mapToCardWithLanguages(): CardWithLanguages {



    return CardWithLanguages(
        cardEntity = this.card, language = listOf(this.language)
    )


}
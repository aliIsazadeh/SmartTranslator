package com.esum.feature.card.data.local.mapper

import com.esum.common.lagnuage.Languages
import com.esum.common.lagnuage.getLanguagesByKey
import com.esum.database.entity.CardEntity
import com.esum.database.entity.relations.CardWithLanguages
import com.esum.feature.card.domain.local.model.CardDetails
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.remote.description.model.DescriptionDefinition
import com.esum.feature.card.domain.remote.description.model.DescriptionMeanings
import com.esum.feature.card.domain.remote.description.model.DescriptionModel

fun CardWithLanguages.mapToCardWithLanguage(): CardWithLanguage {


    return CardWithLanguage(
        id = this.cardEntity.id,
        image = this.cardEntity.image,
        createDate = this.cardEntity.createDate,
        active = this.cardEntity.active,
        updateDate = this.cardEntity.updateDate ?: "",
        original = this.cardEntity.defineText,
        originalLanguage = getLanguagesByKey(this.cardEntity.defineLanguage),
        descriptionModel = this.language?.let {

            Pair(
                CardDetails(
                    id = null,
                    sentence = it.language.sentence,
                    description = DescriptionModel(
                        id = it.description?.description?.id,
                        audio = it.description?.description?.audio,
                        phonetic = it.description?.description?.phonetic ?: "",
                        licence = it.description?.description?.licence ?: "",
                        meanings = it.description?.meanings?.map { meanings ->
                            DescriptionMeanings(
                                id = meanings.meanings.id,
                                partOfSpeech = meanings.meanings.partOfSpeech,
                                definitions = meanings.definitions.map { descDif ->
                                    DescriptionDefinition(
                                        id = descDif.id,
                                        example = descDif.example,
                                        definition = descDif.example,
                                        synonyms = descDif.synonyms.synonym.map { it },
                                        antonyms = descDif.antonym.value.map { it }
                                    )
                                },
                            )
                        }
                    ),
                    translated = it.language.value,
                    correctAnswerCount = it.language.correctAnswerCount
                ), getLanguagesByKey(it.language.region)
            )
        }

    )

}
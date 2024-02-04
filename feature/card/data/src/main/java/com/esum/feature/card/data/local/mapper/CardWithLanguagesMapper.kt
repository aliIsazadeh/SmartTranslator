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
        descriptionModel = this.language?.let { languageWithDescriptions ->

            Pair(
                CardDetails(
                    id = languageWithDescriptions.language.id,
                    sentence = languageWithDescriptions.language.sentence,
                    description = DescriptionModel(
                        id = languageWithDescriptions.description?.description?.id,
                        audio = languageWithDescriptions.description?.description?.audio,
                        phonetic = languageWithDescriptions.description?.description?.phonetic ?: "",
                        licence = languageWithDescriptions.description?.description?.licence ?: "",
                        meanings = languageWithDescriptions.description?.meanings?.map { meanings ->
                            DescriptionMeanings(
                                id = meanings.meanings.id,
                                partOfSpeech = meanings.meanings.partOfSpeech,
                                definitions = meanings.definitions.map { descDif ->
                                    DescriptionDefinition(
                                        id = descDif.id,
                                        example = descDif.example,
                                        definition = descDif.definition,
                                        synonyms = descDif.synonyms.synonym.map { it },
                                        antonyms = descDif.antonym.value.map { it }
                                    )
                                },
                            )
                        }
                    ),
                    translated = languageWithDescriptions.language.value,
                    correctAnswerCount = languageWithDescriptions.language.correctAnswerCount
                ), getLanguagesByKey(languageWithDescriptions.language.region)
            )
        }

    )

}
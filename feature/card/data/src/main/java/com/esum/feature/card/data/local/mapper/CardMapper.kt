package com.esum.feature.card.data.local.mapper

import com.esum.database.entity.CardEntity
import com.esum.database.entity.Description
import com.esum.database.entity.DescriptionDefinition
import com.esum.database.entity.DescriptionMeanings
import com.esum.database.entity.Language
import com.esum.database.entity.model.Antonym
import com.esum.database.entity.model.CardWithLanguage
import com.esum.database.entity.model.Synonym
import com.esum.database.entity.relations.CardWithLanguages
import com.esum.database.entity.relations.DescriptionMeaningsWithDefinitions
import com.esum.database.entity.relations.DescriptionWithMeanings
import com.esum.database.entity.relations.LanguageWithDescriptions
import com.esum.feature.card.domain.local.model.Card
import java.util.UUID

fun Card.mapToCardEntity(): CardWithLanguage {

    val cardId: UUID = this.id ?: UUID.randomUUID()

    return CardWithLanguage(
       card  = CardEntity(
            id = cardId,
            createDate = this.createDate,
            updateDate = this.updateDate,
            image = this.image,
            defineLanguage = this.originalLanguage.key,
            active = this.active,
            defineText = this.original
        ),
        language = this.descriptionModel?.let { (details, language) ->
            val languageId: UUID = details.id ?: UUID.randomUUID()
            val descriptionId: UUID = details.description?.id ?: UUID.randomUUID()
            LanguageWithDescriptions(
                language = Language(
                    id = languageId,
                    cardId = cardId,
                    region = language.key,
                    value = details.translated,
                    correctAnswerCount = details.correctAnswerCount,
                    sentence = details.sentence,
                ),
                description = if (details.description != null) {
                    DescriptionWithMeanings(
                        description = Description(
                            languageId = languageId,
                            id = descriptionId,
                            licence = details.description!!.licence,
                            audio = details.description!!.audio ?:"",
                            phonetic = details.description!!.phonetic
                        ),
                        meanings = if (details.description!!.meanings != null) {
                            details.description!!.meanings?.map { descriptionMeanings ->

                                val descriptionMeaningsId: UUID =
                                    descriptionMeanings.id ?: UUID.randomUUID()

                                DescriptionMeaningsWithDefinitions(
                                    meanings = DescriptionMeanings(
                                        id = descriptionMeaningsId,
                                        descriptionId = descriptionId,
                                        partOfSpeech = descriptionMeanings.partOfSpeech
                                    ),
                                    definitions = descriptionMeanings.definitions.map { definition ->

                                        val definitionId: UUID =
                                            definition.id ?: UUID.randomUUID()

                                        DescriptionDefinition(
                                            id = definitionId,
                                            definition = definition.definition,
                                            example = definition.example,
                                            descriptionMeaningId = descriptionMeaningsId,
                                            synonyms = Synonym(definition.synonyms.map { synonym -> synonym }),
                                            antonym = Antonym(definition.antonyms.map { antonym -> antonym }),
                                        )
                                    }
                                )
                            }
                        } else {
                            null
                        }
                    )
                } else {
                    null
                }
            )
        }
    )
}
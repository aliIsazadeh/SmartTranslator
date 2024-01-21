package com.esum.feature.card.data.local.mapper

import com.esum.database.entity.CardEntity
import com.esum.database.entity.Description
import com.esum.database.entity.DescriptionDefinition
import com.esum.database.entity.DescriptionMeanings
import com.esum.database.entity.Language
import com.esum.database.entity.model.Antonym
import com.esum.database.entity.model.Synonym
import com.esum.database.entity.relations.CardWithLanguages
import com.esum.database.entity.relations.DescriptionMeaningsWithDefinitions
import com.esum.database.entity.relations.DescriptionWithMeanings
import com.esum.database.entity.relations.LanguageWithDescriptions
import com.esum.feature.card.domain.local.model.CardWithLanguage
import java.util.UUID

fun CardWithLanguage.mapToCardWithLanguages(): CardWithLanguages {


    val cardId: UUID = this.id ?: UUID.randomUUID()


    return CardWithLanguages(
        cardEntity = CardEntity(
            id = cardId,
            defineText = this.original,
            image = this.image,
            createDate = this.createDate,
            defineLanguage = this.originalLanguage.key,
            active = this.active,
            updateDate = this.updateDate
        ),
        language = this.descriptionModel.map { details ->
            details?.let {
                val languageId: UUID = details.first.id ?: UUID.randomUUID()
                val descriptionId: UUID = details.first.description?.id ?: UUID.randomUUID()
                LanguageWithDescriptions(
                    language = Language(
                        id = languageId,
                        sentence = details.first.sentence,
                        cardId = cardId,
                        value = details.first.translated,
                        region = details.second.key,
                        correctAnswerCount = details.first.correctAnswerCount
                    ),
                    description = details.first.description?.let { description ->
                        DescriptionWithMeanings(
                            description = Description(
                                languageId = languageId,
                                id = descriptionId,
                                licence = description.licence,
                                phonetic = description.phonetic,
                                audio = description.audio ?: ""
                            ), meanings = description.meanings?.map { meaning ->
                                val meaningId: UUID =
                                    meaning.id ?: UUID.randomUUID()
                                DescriptionMeaningsWithDefinitions(
                                    meanings = DescriptionMeanings(
                                        id = meaningId,
                                        descriptionId = descriptionId,
                                        partOfSpeech = meaning.partOfSpeech
                                    ), definitions = meaning.definitions.map { definition ->
                                        val definitionId: UUID =
                                            definition.id ?: UUID.randomUUID()
                                        DescriptionDefinition(
                                            id = definitionId,
                                            definition = definition.definition,
                                            antonym = Antonym(value = definition.antonyms),
                                            synonyms = Synonym(synonym = definition.synonyms),
                                            example = definition.example,
                                            descriptionMeaningId = meaningId
                                        )
                                    }
                                )
                            }
                        )
                    })
            }
        },
    )

}
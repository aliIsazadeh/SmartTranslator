package com.esum.feature.card.data.remote.description.mapper

import com.esum.feature.card.domain.remote.description.model.DescriptionDefinition
import com.esum.feature.card.domain.remote.description.model.DescriptionMeanings
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.network.description.model.DescriptionResult
import com.esum.network.description.model.Meaning

fun DescriptionResult.mapToDescriptionModel(): DescriptionModel {

    return this.map { result ->
        DescriptionModel(
            phonetic = result.phonetic,
            audio = result.phonetics.firstOrNull { it.audio.isNotBlank() }?.audio ?: "",
            meanings = result.meanings.map { meaning: Meaning ->
                DescriptionMeanings(
                    partOfSpeech = meaning.partOfSpeech,
                    definitions = meaning.definitions.map {
                        DescriptionDefinition(
                            definition = it.definition,
                            antonyms = it.antonyms,
                            synonyms = it.synonyms,
                            example = it.example
                        )
                    })
            },
            licence = result.license.name
        )
    }.first()


}
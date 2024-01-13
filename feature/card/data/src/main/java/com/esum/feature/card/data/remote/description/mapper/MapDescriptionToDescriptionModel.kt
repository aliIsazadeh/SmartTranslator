package com.esum.feature.card.data.remote.description.mapper

import com.esum.feature.card.domain.remote.description.model.DescriptionDefinition
import com.esum.feature.card.domain.remote.description.model.DescriptionMeanings
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.network.description.model.DescriptionResultItem
import com.esum.network.description.model.Meaning

fun DescriptionResultItem.mapToDescriptionModel(): DescriptionModel {

    return this.let { result ->
        DescriptionModel(
            phonetic = result.phonetic
                ?: this.phonetics.firstOrNull { phonetic -> phonetic.text != null && phonetic.audio != null }?.text
                ?: this.phonetics.firstOrNull { phonetic -> phonetic.text != null }?.text ?: "",
            audio = result.phonetics.firstOrNull { it.audio?.isNotBlank() == true }?.audio ?: "",
            meanings = result.meanings?.map { meaning: Meaning ->
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
            licence = result.license?.name ?: ""
        )
    }


}
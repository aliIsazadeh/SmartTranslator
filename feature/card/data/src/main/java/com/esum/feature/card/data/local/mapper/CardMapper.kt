package com.esum.feature.card.data.local.mapper

import com.esum.common.lagnuage.Languages
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card

fun Card.mapToCardEntity(): CardEntity {

    var english: String? = null
    var farsi: String? = null
    var french: String? = null
    var germany: String? = null
    var sentence: String? = null
    var spaniel: String? = null
    var turkish: String? = null

    var defineLanguage: String


    when (this.originalLanguage) {
        Languages.English -> {
            english = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Farsi -> {
            farsi = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Italian -> {
            english = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.French -> {
            french = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Arabic -> {
            english = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Japans -> {
            english = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Germany -> {
            germany = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Turkish -> {
            turkish = this.original
            defineLanguage = this.originalLanguage.key
        }

        Languages.Spanish -> {
            spaniel = this.original
            defineLanguage = this.originalLanguage.key
        }
    }

    when (this.translateLanguage) {
        Languages.English -> {
            english = this.translate
        }

        Languages.Farsi -> {
            farsi = this.translate
        }

        Languages.Italian -> {
            english = this.translate
        }

        Languages.French -> {
            french = this.translate
        }

        Languages.Arabic -> {
            english = this.translate
        }

        Languages.Japans -> {
            english = this.translate
        }

        Languages.Germany -> {
            germany = this.translate
        }

        Languages.Turkish -> {
            turkish = this.translate
        }

        Languages.Spanish -> {
            spaniel = this.translate
        }
    }

    return CardEntity(
        active = true,
        correctAnswerCount = this.correctAnswerCount,
        defineLanguage = defineLanguage,
        english = english,
        farsi = farsi,
        french = french,
        germany = germany,
        image = null,
        sentence = this.sentence,
        spaniel = spaniel,
        turkish = turkish,
        createDate = this.createDate, updateDate = this.updateDate
    )

}
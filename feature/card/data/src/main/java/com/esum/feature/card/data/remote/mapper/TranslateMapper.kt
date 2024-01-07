package com.esum.feature.card.data.remote.mapper

import com.esum.network.model.TranslateResult

fun TranslateResult.mapToTranslateModel(): com.esum.feature.card.domain.local.model.TranslateResult {
    return com.esum.feature.card.domain.local.model.TranslateResult(translated = this.data.translatedText)

}
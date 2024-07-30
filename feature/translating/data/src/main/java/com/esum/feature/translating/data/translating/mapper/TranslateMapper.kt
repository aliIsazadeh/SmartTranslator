package com.esum.feature.translating.data.translating.mapper

import com.esum.network.translate.model.TranslateResult


fun TranslateResult.mapToTranslateModel(): com.esum.feature.translating.domain.translating.model.TranslateResult {
    return com.esum.feature.translating.domain.translating.model.TranslateResult(translated = this.translatedText)

}
package com.esum.feature.card.data.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.constraints.TranslateErrors
import com.esum.common.constraints.TranslatedStatus
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.data.mapper.mapToTranslateModel
import com.esum.feature.card.domain.model.TranslateResult
import com.esum.feature.card.domain.repository.RemoteRepository
import com.esum.network.dataproviders.TranslateDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val translateDataProvider: TranslateDataProvider) :
    RemoteRepository {

    override suspend fun translateOnline(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>> = flow {
        emit(ResultConstraints.Loading())
        val translated = translateDataProvider.translate(
            fromLanguage = fromLanguages.key,
            toLanguages = toLanguages.key,
            text
        )
        if (translated.status == TranslatedStatus.Success.status) {
            if (translated.data.translatedText.isNotBlank()) {
                emit(ResultConstraints.Success(translated.mapToTranslateModel()))
            }else {
                emit(ResultConstraints.Error<TranslateResult>(TranslateErrors.ResponseIsEmpty.message))
            }
        }
    }.catch {

    }
}
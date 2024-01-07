package com.esum.feature.card.data.remote.repository

import android.util.Log
import com.esum.common.constraints.ResultConstraints
import com.esum.common.constraints.TranslateErrors
import com.esum.common.constraints.TranslatedStatus
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.data.remote.mapper.mapToTranslateModel
import com.esum.feature.card.domain.local.model.TranslateResult
import com.esum.feature.card.domain.remote.repository.RemoteRepository
import com.esum.network.dataproviders.TranslateDataProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val translateDataProvider: TranslateDataProvider,
    private val dispatcher: CoroutineDispatcher
) :
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
            } else {
                emit(ResultConstraints.Error<TranslateResult>(TranslateErrors.ResponseIsEmpty.message))
            }
        }
    }.catch {
        Log.e("RemoteRepositoryImpl", "translateOnline: ${it.message} ")
        emit(ResultConstraints.Error<TranslateResult>(message = it.message.toString()))
    }.flowOn(dispatcher)
}
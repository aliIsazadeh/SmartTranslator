package com.esum.feature.translating.data.translating.repository

import android.util.Log
import com.esum.common.constraints.ResultConstraints
import com.esum.common.constraints.TranslateErrors
import com.esum.common.lagnuage.Languages
import com.esum.feature.translating.data.translating.mapper.mapToTranslateModel
import com.esum.feature.translating.domain.translating.model.TranslateResult
import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import com.esum.network.translate.dataprovider.TranslateDataProvider
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

    override  fun translateOnline(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>> = flow<ResultConstraints<TranslateResult>> {
        emit(ResultConstraints.Loading<TranslateResult>())
        val translated = translateDataProvider.translate(
            fromLanguage = fromLanguages.key,
            toLanguages = toLanguages.key,
            text
        )
        if (translated.isSuccessful) {
            if (translated.body() != null) {
                emit(ResultConstraints.Success(translated.body()!!.mapToTranslateModel()))
            } else {
                emit(ResultConstraints.Error<TranslateResult>(TranslateErrors.ResponseIsEmpty.message))
            }
        }
    }.catch {
        Log.e("RemoteRepositoryImpl", "translateOnline: ${it.message}")
        emit(ResultConstraints.Error<TranslateResult>(message = it.message.toString()))
    }.flowOn(dispatcher)
}
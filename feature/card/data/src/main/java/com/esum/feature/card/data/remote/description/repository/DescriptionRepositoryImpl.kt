package com.esum.feature.card.data.remote.description.repository

import android.util.Log
import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.data.remote.description.mapper.mapToDescriptionModel
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.feature.card.domain.remote.description.repository.DescriptionRepository
import com.esum.network.description.dataproviders.DescriptionDataProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DescriptionRepositoryImpl @Inject constructor(
    private val dataProvider: DescriptionDataProvider,
    private val dispatcher: CoroutineDispatcher
) :
    DescriptionRepository {
    override suspend fun getDescription(
        word: String,
        languages: Languages
    ): Flow<ResultConstraints<DescriptionModel>> = flow {

        emit(ResultConstraints.Loading())
        val response = dataProvider.getDescription(languages, word)
        if (response.isSuccessful) {
            if (response.body() != null) {
                if (response.body()?.first() != null) {
                    emit(
                        ResultConstraints.Success(
                            data = response.body()!!.first().mapToDescriptionModel()
                        )
                    )
                }
            } else {
                emit(ResultConstraints.Error(response.message()))
            }
        } else {
            emit(ResultConstraints.Error(response.message()))
        }
    }.catch {
        Log.e("DescriptionRepositoryImpl", "getDescription: ${it.message}")
        emit(ResultConstraints.Error(it.message.toString()))

    }.flowOn(dispatcher)
}
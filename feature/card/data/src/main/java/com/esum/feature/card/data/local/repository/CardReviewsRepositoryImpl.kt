package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.dataProvider.card.CardGetReviewsDataProvider
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguage
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CardReviewsRepositoryImpl @Inject constructor(
    private val dataProvider: CardGetReviewsDataProvider,
    private val dispatcher: CoroutineDispatcher
) : CardGetReviewsRepository {
    override fun getCardReviews(): Flow<ResultConstraints<List<CardWithLanguage>>> = flow {
        emit(ResultConstraints.Loading<List<CardWithLanguage>>())

        val list = dataProvider.getReviewCards()

        emit(ResultConstraints.Success(list.map { it.mapToCardWithLanguage() }))
    }.catch {
        ResultConstraints.Error<List<CardWithLanguage>>(message = "error in CardReviewsRepositoryImpl is : ${it.message}")
    }.distinctUntilChanged().flowOn(dispatcher)
}
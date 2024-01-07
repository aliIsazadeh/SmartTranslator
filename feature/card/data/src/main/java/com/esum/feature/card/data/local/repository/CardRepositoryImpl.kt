package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.database.dataProvider.CardDataProvider
import com.esum.database.entity.CardEntity
import com.esum.feature.card.data.local.mapper.mapToCardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardDataProvider: CardDataProvider,
    private val dispatcher: CoroutineDispatcher
) : CardRepository {
    override fun getAllCards(): Flow<ResultConstraints<List<CardEntity>>> =
        cardDataProvider.getAllCards()
            .onStart {
                ResultConstraints.Loading<ResultConstraints<List<CardEntity>>>()
            }.map {
                ResultConstraints.Success(it)
            }.catch {
                ResultConstraints.Error<ResultConstraints<List<CardEntity>>>(it.message.toString())
            }.distinctUntilChanged()
            .flowOn(dispatcher)

    override fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>> =
        cardDataProvider.getCardById(id).onStart {
            ResultConstraints.Loading<ResultConstraints<CardEntity>>()
        }.map {
            ResultConstraints.Success(it)
        }.catch {
            ResultConstraints.Error<ResultConstraints<CardEntity>>(it.message.toString())
        }.distinctUntilChanged().flowOn(dispatcher)

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cardDataProvider.insertCard(card.mapToCardEntity().copy(createDate = getCurrentDate()))))
    }.catch {
        emit(ResultConstraints.Error(message = it.message.toString()))
    }.flowOn(dispatcher)

    override suspend fun updateCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            cardDataProvider.updateCard(cardEntity)
            emit(ResultConstraints.Success("card updated successfully"))
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }.flowOn(dispatcher)

    override suspend fun deleteCardById(id: Long): Flow<ResultConstraints<String>> = flow {
        emit(ResultConstraints.Loading())
        cardDataProvider.deleteCardById(id)
        emit(ResultConstraints.Success("card deleted successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }.flowOn(dispatcher)

    override suspend fun deleteCard(cardEntity: CardEntity) = flow {
        emit(ResultConstraints.Loading())
        cardDataProvider.deleteCard(cardEntity)
        emit(ResultConstraints.Success("card deleted successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }.flowOn(dispatcher)


}
package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.definition.DescriptionDefinitionProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsProvider
import com.esum.database.dataProvider.description.DescriptionProvider
import com.esum.database.dataProvider.languag.LanguageProvider
import com.esum.database.entity.CardEntity
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguage
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguages
import com.esum.feature.card.domain.local.model.ActiveCardsCount
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.UUID
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardDataProvider: CardDataProvider,
    private val languageProvider: LanguageProvider,
    private val dispatcher: CoroutineDispatcher,
) : CardRepository {
    override fun getAllCards(): Flow<ResultConstraints<List<CardWithLanguage>>> =
        cardDataProvider.getAllCards()
            .onStart {
                ResultConstraints.Loading<ResultConstraints<List<CardWithLanguage>>>()
            }.map {
                ResultConstraints.Success(it.map { card -> card.mapToCardWithLanguage() })
            }.catch {
                ResultConstraints.Error<ResultConstraints<List<CardEntity>>>(it.message.toString())
            }.distinctUntilChanged()
            .flowOn(dispatcher)

    override fun getCardById(id: UUID): Flow<ResultConstraints<CardWithLanguage?>> =
        cardDataProvider.getCardById(id).onStart {
            ResultConstraints.Loading<CardWithLanguage?>()
        }.map {
            ResultConstraints.Success(it?.mapToCardWithLanguage())
        }.catch {
            ResultConstraints.Error<CardWithLanguage?>(message = it.message.toString())
        }.distinctUntilChanged().flowOn(dispatcher)


    override suspend fun updateCard(cardEntity: CardWithLanguage): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            cardDataProvider.updateCard(cardEntity.mapToCardWithLanguages().cardEntity)
            emit(ResultConstraints.Success("card updated successfully"))
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }.flowOn(dispatcher)

    override suspend fun deleteCardById(id: UUID): Flow<ResultConstraints<String>> = flow {
        emit(ResultConstraints.Loading())
        cardDataProvider.deleteCardById(id)
        emit(ResultConstraints.Success("card deleted successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }.flowOn(dispatcher)

    override suspend fun deleteCard(cardEntity: CardWithLanguage) = flow {
        emit(ResultConstraints.Loading())
        cardDataProvider.deleteCard(cardEntity.mapToCardWithLanguages().cardEntity)
        emit(ResultConstraints.Success("card deleted successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }.flowOn(dispatcher)

    override fun getActiveCardsCount(): Flow<ResultConstraints<Pair<List<ActiveCardsCount>, Int>>> {
        val cardsStatusFlow = combine(
            cardDataProvider.getActiveCardsCount(),
            languageProvider.getNeedToLearnCount()
        ) { a, b ->
            a to b
        }
        return cardsStatusFlow.onStart {
            ResultConstraints.Loading<List<ActiveCardsCount>>()
        }.map { pair ->
            ResultConstraints.Success(Pair(pair.first.map { ActiveCardsCount(active = it.active, count = it.count) } , pair.second))
        }.catch {
            ResultConstraints.Error<List<ActiveCardsCount>>(message = "error while getActiveCardsCount ")
        }.distinctUntilChanged().flowOn(dispatcher)

    }
}
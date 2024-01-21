package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.database.dataProvider.CardDataProvider
import com.esum.database.dataProvider.DescriptionDefinitionProvider
import com.esum.database.dataProvider.DescriptionMeaningsProvider
import com.esum.database.dataProvider.DescriptionProvider
import com.esum.database.dataProvider.LanguageProvider
import com.esum.database.entity.CardEntity
import com.esum.database.entity.DescriptionMeanings
import com.esum.database.entity.relations.CardWithLanguages
import com.esum.feature.card.data.local.mapper.mapToCardEntity
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguage
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguages
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.UUID
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardDataProvider: CardDataProvider,
    private val descriptionDefinitionProvider: DescriptionDefinitionProvider,
    private val descriptionMeaningsProvider: DescriptionMeaningsProvider,
    private val descriptionProvider: DescriptionProvider,
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

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow {
        emit(ResultConstraints.Loading())
        val dataObject = card.mapToCardEntity()
        val cardId =
            cardDataProvider.insertCard(
                cardEntity = dataObject.copy(
                    card = dataObject.card.copy(
                        createDate = getCurrentDate()
                    )
                ).mapToCardWithLanguages().cardEntity
            )
        dataObject.language?.let { languageWithDescription ->
            languageProvider.insertLanguage(languageWithDescription.language)
            if (languageWithDescription.description != null) {
                descriptionProvider.insertDescription(description = languageWithDescription.description!!.description)
                languageWithDescription.description!!.meanings?.forEach { meaningsWithDefinitions ->
                    descriptionMeaningsProvider.insertDescriptionMeaning(meaningsWithDefinitions.meanings)
                    meaningsWithDefinitions.definitions.forEach { definitions ->
                        descriptionDefinitionProvider.insertDescriptionDefinition(definitions)
                    }
                }
            }
        }
        emit(
            ResultConstraints.Success(
                cardId
            )
        )
    }.catch {
        emit(ResultConstraints.Error(message = it.message.toString()))
    }.flowOn(dispatcher)

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


}
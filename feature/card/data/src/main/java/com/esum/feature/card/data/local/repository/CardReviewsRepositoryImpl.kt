package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.addDays
import com.esum.common.date.getCurrentDate
import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.definition.DescriptionDefinitionProvider
import com.esum.database.dataProvider.description.DescriptionProvider
import com.esum.database.dataProvider.languag.LanguageProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsProvider
import com.esum.feature.card.data.local.mapper.mapToCardEntity
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguage
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguages
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardReviewsRepositoryImpl @Inject constructor(
    private val dataProvider: CardDataProvider,
    private val languageProvider: LanguageProvider,
    private val descriptionDefinitionInsertProvider: DescriptionDefinitionProvider,
    private val descriptionInsertProvider: DescriptionProvider,
    private val descriptionMeaningsInsertProvider: DescriptionMeaningsProvider,
    private val dispatcher: CoroutineDispatcher
) : CardGetReviewsRepository {
    override fun getCardReviews(): Flow<ResultConstraints<List<CardWithLanguage>>> = flow {
        emit(ResultConstraints.Loading<List<CardWithLanguage>>())

        val list = dataProvider.getReviewCards()

        emit(ResultConstraints.Success(list.map { it.mapToCardWithLanguage() }))
    }.catch {
        ResultConstraints.Error<List<CardWithLanguage>>(message = "error in CardReviewsRepositoryImpl is : ${it.message}")
    }.distinctUntilChanged().flowOn(dispatcher)

    override suspend fun updateCard(card: CardWithLanguage): Flow<ResultConstraints<Long>> = flow {
        emit(ResultConstraints.Loading())
        val dataObject = card.mapToCardWithLanguages()
        dataProvider.updateCard(
            cardEntity = dataObject.copy(
                cardEntity = dataObject.cardEntity.copy(
                    updateDate = addDays(
                        when (dataObject.language?.language?.correctAnswerCount) {
                            0 -> {
                                1
                            }
                            1 -> {
                                3
                            }
                            2 -> {
                                7
                            }
                            3 -> {
                                30
                            }
                            4 -> {
                                90
                            }
                            5 -> {
                                180
                            }
                            6 -> {
                                365
                            }
                            else -> {
                                365
                            }
                        },
                    ),

                ),
            ).cardEntity,
        )
        dataObject.language?.let { languageWithDescription ->
            languageProvider.updateLanguage(languageWithDescription.language.copy(correctAnswerCount = languageWithDescription.language.correctAnswerCount))
            if (languageWithDescription.description != null) {
                descriptionInsertProvider.updateDescription(description = languageWithDescription.description!!.description)
                languageWithDescription.description!!.meanings?.forEach { meaningsWithDefinitions ->
                    descriptionMeaningsInsertProvider.updateDescriptionMeaning(
                        meaningsWithDefinitions.meanings
                    )
                    meaningsWithDefinitions.definitions.forEach { definitions ->
                        descriptionDefinitionInsertProvider.updateDescriptionDefinition(definitions)
                    }
                }
            }
        }
        emit(
            ResultConstraints.Success(
                1L
            )
        )
    }.catch {
        emit(ResultConstraints.Error(message = it.message.toString()))
    }.flowOn(dispatcher)
}
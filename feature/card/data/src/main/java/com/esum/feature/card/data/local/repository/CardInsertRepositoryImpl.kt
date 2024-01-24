package com.esum.feature.card.data.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.database.dataProvider.card.CardInsertDataProvider
import com.esum.database.dataProvider.definition.DescriptionDefinitionInsertProvider
import com.esum.database.dataProvider.description.DescriptionInsertProvider
import com.esum.database.dataProvider.languag.LanguageInsertDataProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsInsertProvider
import com.esum.feature.card.data.local.mapper.mapToCardEntity
import com.esum.feature.card.data.local.mapper.mapToCardWithLanguages
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CardInsertRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val cardDataProvider: CardInsertDataProvider,
    private val languageProvider: LanguageInsertDataProvider,
    private val descriptionDefinitionInsertProvider: DescriptionDefinitionInsertProvider,
    private val descriptionInsertProvider: DescriptionInsertProvider,
    private val descriptionMeaningsInsertProvider: DescriptionMeaningsInsertProvider
) : CardInsertRepository {
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
                descriptionInsertProvider.insertDescription(description = languageWithDescription.description!!.description)
                languageWithDescription.description!!.meanings?.forEach { meaningsWithDefinitions ->
                    descriptionMeaningsInsertProvider.insertDescriptionMeaning(meaningsWithDefinitions.meanings)
                    meaningsWithDefinitions.definitions.forEach { definitions ->
                        descriptionDefinitionInsertProvider.insertDescriptionDefinition(definitions)
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

}
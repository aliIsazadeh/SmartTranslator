package com.esum.feature.card.domain.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.UUID

class CardInsertFakeRepository : CardInsertRepository {

    val cardEntities = mutableListOf<CardWithLanguage>(
        CardWithLanguage(
            id = UUID.randomUUID(),
            originalLanguage = Languages.English,
            original ="hello",
            updateDate = getCurrentDate(),
            active = true,
            createDate = getCurrentDate(),
            image =null,
            descriptionModel = null
        )
    )
    val cards = mutableListOf<Card>(
        Card(
            createDate = "",
            updateDate = "",
            originalLanguage = Languages.Farsi,
            original = "سلام"
        )
    )
    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> =
        flow<ResultConstraints<Long>> {
            emit(ResultConstraints.Loading())
            cards.add(card)
            emit(ResultConstraints.Success(0))
        }.catch {
            emit(ResultConstraints.Error<Long>(message = it.message.toString()))
        }
}
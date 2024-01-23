package com.esum.feature.card.domain.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.common.lagnuage.Languages
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.ActiveCardsCount
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.UUID

class FakeCardRepository : CardRepository {

    val cardEntities = mutableListOf<CardWithLanguage>(
        CardWithLanguage(
            id = UUID.randomUUID(),
            originalLanguage = Languages.English,
            original ="hello",
            updateDate = getCurrentDate(),
            active = true,
            createDate = getCurrentDate(),
            image =null,
            descriptionModel = listOf()
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

    override fun getAllCards(): Flow<ResultConstraints<List<CardWithLanguage>>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success<List<CardWithLanguage>>(listOf()))
    }.catch {
        emit(ResultConstraints.Error<List<CardWithLanguage>>(message = it.message.toString()))
    }

    override fun getCardById(id: UUID): Flow<ResultConstraints<CardWithLanguage?>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cardEntities.firstOrNull { it.id == id }))
    }.catch {
        emit(ResultConstraints.Error<CardWithLanguage?>(message = it.message.toString()))
    }

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> =
        flow<ResultConstraints<Long>> {
            emit(ResultConstraints.Loading())
            cards.add(card)
            emit(ResultConstraints.Success(0))
        }.catch {
            emit(ResultConstraints.Error<Long>(message = it.message.toString()))
        }

    override suspend fun updateCard(cardEntity: CardWithLanguage): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            val card = cardEntities.firstOrNull() {
                it.id == cardEntity.id
            }
            if (card != null) {
                cardEntities.remove(cardEntity)
                cardEntities.add(card)
                emit(ResultConstraints.Success("update successfully"))
            } else {
                emit(ResultConstraints.Error<String>(message = "there is no such card"))
            }
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }

    override suspend fun deleteCardById(id: UUID): Flow<ResultConstraints<String>> = flow {
        emit(ResultConstraints.Loading())
        cardEntities.removeIf {
            it.id == id
        }
        emit(ResultConstraints.Success("removed successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }

    override suspend fun deleteCard(cardEntity: CardWithLanguage): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            cardEntities.removeIf {
                it == cardEntity
            }
            emit(ResultConstraints.Success("removed successfully"))
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }

    override fun getActiveCardsCount(): Flow<ResultConstraints<Pair<List<ActiveCardsCount>, Int>>> {
        TODO("Not yet implemented")
    }

}
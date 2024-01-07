package com.esum.feature.card.domain.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.common.lagnuage.Languages
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FakeCardRepository : CardRepository {

    val cardEntities = mutableListOf<CardEntity>(
        CardEntity(
            id = 1,
            correctAnswerCount = 0,
            createDate = getCurrentDate(),
            farsi = "سلام",
            english = "hello"
        ),
    )
    val  cards = mutableListOf<Card>(
        Card(
            createDate = "",
            updateDate = "",
            sentence = "",
            correctAnswerCount = 0,
            translateLanguage = Languages.English,
            translate = "hello",
            originalLanguage = Languages.Farsi,
            original = "سلام"
        )
    )

    override fun getAllCards(): Flow<ResultConstraints<List<CardEntity>>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cardEntities.toList()))
    }.catch {
        emit(ResultConstraints.Error<List<CardEntity>>(message = it.message.toString()))
    }

    override fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cardEntities.firstOrNull { it.id == id }))
    }.catch {
        emit(ResultConstraints.Error<CardEntity?>(message = it.message.toString()))
    }

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow<ResultConstraints<Long>> {
        emit(ResultConstraints.Loading())
        cards.add(card)
        emit(ResultConstraints.Success(0))
    }.catch {
        emit(ResultConstraints.Error<Long>(message = it.message.toString()))
    }

    override suspend fun updateCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> =
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

    override suspend fun deleteCardById(id: Long): Flow<ResultConstraints<String>> = flow {
        emit(ResultConstraints.Loading())
        cardEntities.removeIf {
            it.id == id
        }
        emit(ResultConstraints.Success("removed successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }

    override suspend fun deleteCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            cardEntities.removeIf {
                it == cardEntity
            }
            emit(ResultConstraints.Success("removed successfully"))
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }

}
package com.esum.feature.card.domain.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.date.getCurrentDate
import com.esum.database.entity.CardEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FakeCardRepository : CardRepository {

    val cards = mutableListOf<CardEntity>(
        CardEntity(
            id = 1,
            correctAnswerCount = 0,
            createDate = getCurrentDate(),
            farsi = "سلام",
            english = "hello"
        ),
    )

    override fun getAllCards(): Flow<ResultConstraints<List<CardEntity>>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cards.toList()))
    }.catch {
        emit(ResultConstraints.Error<List<CardEntity>>(message = it.message.toString()))
    }

    override fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>> = flow {
        emit(ResultConstraints.Loading())
        emit(ResultConstraints.Success(cards.firstOrNull { it.id == id }))
    }.catch {
        emit(ResultConstraints.Error<CardEntity?>(message = it.message.toString()))
    }

    override suspend fun insertCard(cardEntity: CardEntity): Flow<ResultConstraints<Long>> = flow {
        emit(ResultConstraints.Loading())
        cards.add(cardEntity)
        emit(ResultConstraints.Success(cardEntity.id))
    }.catch {
        emit(ResultConstraints.Error<Long>(message = it.message.toString()))
    }

    override suspend fun updateCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            val card = cards.firstOrNull() {
                it.id == cardEntity.id
            }
            if (card != null) {
                cards.remove(cardEntity)
                cards.add(card)
                emit(ResultConstraints.Success("update successfully"))
            } else {
                emit(ResultConstraints.Error<String>(message = "there is no such card"))
            }
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }

    override suspend fun deleteCardById(id: Long): Flow<ResultConstraints<String>> = flow {
        emit(ResultConstraints.Loading())
        cards.removeIf {
            it.id == id
        }
        emit(ResultConstraints.Success("removed successfully"))
    }.catch {
        emit(ResultConstraints.Error<String>(message = it.message.toString()))
    }

    override suspend fun deleteCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> =
        flow {
            emit(ResultConstraints.Loading())
            cards.removeIf {
                it == cardEntity
            }
            emit(ResultConstraints.Success("removed successfully"))
        }.catch {
            emit(ResultConstraints.Error<String>(message = it.message.toString()))
        }

}
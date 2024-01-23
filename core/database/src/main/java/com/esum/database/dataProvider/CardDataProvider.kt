package com.esum.database.dataProvider

import com.esum.database.dao.CardDao
import com.esum.database.entity.CardEntity
import com.esum.database.entity.relations.CardWithLanguages
import java.util.UUID
import javax.inject.Inject

class CardDataProvider @Inject constructor(private val cardDao: CardDao) {

    fun getAllCards() = cardDao.getAllCards()
    fun getCardById(id : UUID) = cardDao.getCardById(id)
    fun getCardWithDescriptionById(id : UUID) = cardDao.getCardWithDescriptionById(id)
    suspend fun insertCard(cardEntity: CardEntity) : Long = cardDao.insertCard(cardEntity)
    suspend fun updateCard(cardEntity: CardEntity)  = cardDao.updateCard(cardEntity)
    suspend fun deleteCardById(id : UUID) = cardDao.deleteCardById(id)
    suspend fun deleteCard(cardEntity: CardEntity) = cardDao.deleteCard(cardEntity)
    fun getActiveCardsCount() = cardDao.getActivesCount()

}
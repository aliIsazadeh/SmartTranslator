package com.esum.database.dataProvider

import com.esum.database.dao.CardDao
import com.esum.database.entity.CardEntity
import javax.inject.Inject

class CardDataProvider @Inject constructor(private val cardDao: CardDao) {

    fun getAllCards() = cardDao.getAllCards()
    fun getCardById(id : Long) = cardDao.getCardById(id)
    suspend fun insertCard(cardEntity: CardEntity) : Long = cardDao.insertCard(cardEntity)
    suspend fun updateCard(cardEntity: CardEntity)  = cardDao.updateCard(cardEntity)
    suspend fun deleteCardById(id : Long) = cardDao.deleteCardById(id)
    suspend fun deleteCard(cardEntity: CardEntity) = cardDao.deleteCard(cardEntity)

}
package com.esum.database.dataProvider.card

import com.esum.database.dao.CardDao
import com.esum.database.entity.CardEntity
import javax.inject.Inject

class CardInsertDataProvider @Inject constructor(private val cardDao: CardDao)  {
    suspend fun insertCard(cardEntity: CardEntity) : Long = cardDao.insertCard(cardEntity)

}
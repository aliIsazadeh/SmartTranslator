package com.esum.database.dataProvider.card

import com.esum.database.dao.CardDao
import javax.inject.Inject

class CardGetReviewsDataProvider @Inject constructor(private val cardDao: CardDao)  {
    fun getReviewCards() = cardDao.getReviewCards()
}
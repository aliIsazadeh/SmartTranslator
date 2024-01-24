package com.esum.database.dataProvider.meaning


import com.esum.database.dao.DescriptionMeaningsDao
import com.esum.database.entity.DescriptionMeanings
import javax.inject.Inject

class DescriptionMeaningsInsertProvider @Inject constructor(private val dao: DescriptionMeaningsDao) {

    suspend fun insertDescriptionMeaning(descriptionMeaning: DescriptionMeanings) : Long = dao.insertDescriptionMeaning(descriptionMeaning)

}
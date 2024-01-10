package com.esum.database.dataProvider


import com.esum.database.dao.DescriptionMeaningsDao
import com.esum.database.entity.DescriptionMeanings
import javax.inject.Inject

class DescriptionMeaningsProvider @Inject constructor(private val dao: DescriptionMeaningsDao) {

    fun getAllDescriptionMeaning() = dao.getAllDescriptionMeanings()
    fun getDescriptionMeaningById(id : Long) = dao.getDescriptionMeaningById(id)
    suspend fun insertDescriptionMeaning(descriptionMeaning: DescriptionMeanings) : Long = dao.insertDescriptionMeaning(descriptionMeaning)
    suspend fun updateDescriptionMeaning(descriptionMeaning: DescriptionMeanings)  = dao.updateDescriptionMeaning(descriptionMeaning)
    suspend fun deleteDescriptionMeaningById(id : Long) = dao.deleteDescriptionMeaningById(id)
    suspend fun deleteDescriptionMeaning(descriptionMeaning: DescriptionMeanings) = dao.deleteDescriptionMeaning(descriptionMeaning)

}
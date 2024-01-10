package com.esum.database.dataProvider

import com.esum.database.dao.DescriptionDao
import com.esum.database.entity.Description
import javax.inject.Inject

class DescriptionProvider @Inject constructor(private val dao: DescriptionDao) {

    fun getAllDescription() = dao.getAllDescription()
    fun getDescriptionById(id : Long) = dao.getDescriptionById(id)
    suspend fun insertDescription(description: Description) : Long = dao.insertDescription(description)
    suspend fun updateDescription(description: Description)  = dao.updateDescription(description)
    suspend fun deleteDescriptionById(id : Long) = dao.deleteDescriptionById(id)
    suspend fun deleteDescription(description: Description) = dao.deleteDescription(description)

}
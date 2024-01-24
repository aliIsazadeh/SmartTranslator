package com.esum.database.dataProvider.definition

import com.esum.database.dao.DescriptionDefinitionDao
import com.esum.database.entity.DescriptionDefinition
import javax.inject.Inject

class DescriptionDefinitionProvider @Inject constructor(private val dao: DescriptionDefinitionDao) {

    fun getAllDescriptionDefinition() = dao.getAllDescriptionDefinition()
    fun getDescriptionDefinitionById(id: Long) = dao.getDescriptionDefinitionById(id)
    suspend fun insertDescriptionDefinition(descriptionDefinition: DescriptionDefinition): Long =
        dao.insertDescriptionDefinition(descriptionDefinition)

    suspend fun updateDescriptionDefinition(descriptionDefinition: DescriptionDefinition) =
        dao.updateDescriptionDefinition(descriptionDefinition)
    suspend fun deleteDescriptionDefinitionById(id: Long) = dao.deleteDescriptionDefinitionById(id)
    suspend fun deleteDescriptionDefinition(descriptionDefinition: DescriptionDefinition) =
        dao.deleteDescriptionDefinition(descriptionDefinition)

}
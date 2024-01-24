package com.esum.database.dataProvider.definition

import com.esum.database.dao.DescriptionDefinitionDao
import com.esum.database.entity.DescriptionDefinition
import javax.inject.Inject

class DescriptionDefinitionInsertProvider @Inject constructor(private val dao: DescriptionDefinitionDao) {

    suspend fun insertDescriptionDefinition(descriptionDefinition: DescriptionDefinition): Long =
        dao.insertDescriptionDefinition(descriptionDefinition)


}
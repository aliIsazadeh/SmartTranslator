package com.esum.database.dataProvider.description

import com.esum.database.dao.DescriptionDao
import com.esum.database.entity.Description
import javax.inject.Inject

class DescriptionInsertProvider @Inject constructor(private val dao: DescriptionDao) {

    suspend fun insertDescription(description: Description) : Long = dao.insertDescription(description)

}
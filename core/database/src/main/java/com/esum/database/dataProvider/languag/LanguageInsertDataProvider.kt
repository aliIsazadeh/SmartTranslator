package com.esum.database.dataProvider.languag

import com.esum.database.dao.LanguageDao
import com.esum.database.entity.Language
import javax.inject.Inject

class LanguageInsertDataProvider  @Inject constructor(private val dao: LanguageDao)  {
    suspend fun insertLanguage(language: Language) : Long = dao.insertLanguage(language)

}
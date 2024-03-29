package com.esum.database.dataProvider.languag

import com.esum.database.dao.LanguageDao
import com.esum.database.entity.Language
import javax.inject.Inject

class LanguageProvider @Inject constructor(private val dao: LanguageDao) {

    fun getAllLanguage() = dao.getAllLanguages()
    fun getLanguageById(id : Long) = dao.getLanguageById(id)

    fun getNeedToLearnCount() = dao.getNeedToLearn()
    suspend fun updateLanguage(language: Language)  = dao.updateLanguage(language)
    suspend fun deleteLanguageById(id : Long) = dao.deleteLanguageById(id)
    suspend fun deleteLanguage(language: Language) = dao.deleteLanguage(language)

}
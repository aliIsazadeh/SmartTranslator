package com.esum.network.translate.dataprovider

import com.esum.network.translate.api.TranslateApiService
import javax.inject.Inject

class TranslateDataProvider @Inject constructor(private val apiService: TranslateApiService) {

    suspend fun translate(fromLanguage: String, toLanguages: String, query: String) =
        apiService.translate(fromLanguage,toLanguages,query)

}
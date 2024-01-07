package com.esum.network.dataproviders

import com.esum.common.lagnuage.Languages
import com.esum.network.TranslateApiService
import javax.inject.Inject

class TranslateDataProvider @Inject constructor(private val apiService: TranslateApiService) {

    suspend fun translate(fromLanguage: String, toLanguages: String, query: String) =
        apiService.translate(fromLanguage,toLanguages,query)

}
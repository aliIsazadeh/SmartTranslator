package com.esum.network.dataproviders

import com.esum.common.lagnuage.Languages
import com.esum.network.TranslateApiService
import javax.inject.Inject

class TranslateDataProvider @Inject constructor(private val apiService: TranslateApiService) {

    suspend fun translate(fromLanguage: Languages, toLanguages: Languages, query: String) =
        apiService.translate(langpair = "${fromLanguage.key}%7C${toLanguages}", query)

}
package com.esum.network.description.dataproviders

import com.esum.common.lagnuage.Languages
import com.esum.network.description.api.DescriptionApiService
import javax.inject.Inject

class DescriptionDataProvider @Inject constructor(private val api: DescriptionApiService) {

    suspend fun getDescription(languages: Languages, word: String) =
        api.getDescription(language = languages.key, word = word)

}
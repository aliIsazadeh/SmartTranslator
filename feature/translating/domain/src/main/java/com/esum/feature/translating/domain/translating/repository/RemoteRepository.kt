package com.esum.feature.translating.domain.translating.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.translating.domain.translating.model.TranslateResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

     fun translateOnline(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>>

}
package com.esum.feature.card.domain.remote.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.local.model.TranslateResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun translateOnline(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>>

}
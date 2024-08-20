package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.translating.domain.translating.model.TranslateResult
import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryFakeImpl() : RemoteRepository {
    override  fun translateOnline(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>> = flow {
        emit(ResultConstraints.Success(TranslateResult("سلام")))
    }
}
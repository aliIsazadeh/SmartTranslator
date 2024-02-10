package com.esum.feature.translating.domain.translating.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.translating.domain.translating.model.TranslateResult
import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TranslateCardUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>> = remoteRepository.translateOnline(fromLanguages, toLanguages, text)

}
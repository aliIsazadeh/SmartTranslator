package com.esum.feature.card.domain.remote.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.local.model.TranslateResult
import com.esum.feature.card.domain.remote.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TranslateCardUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(
        fromLanguages: Languages,
        toLanguages: Languages,
        text: String
    ): Flow<ResultConstraints<TranslateResult>> = remoteRepository.translateOnline(fromLanguages, toLanguages, text)

}
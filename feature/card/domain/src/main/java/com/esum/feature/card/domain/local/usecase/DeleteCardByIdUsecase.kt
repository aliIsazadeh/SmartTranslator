package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCardByIdUsecase @Inject constructor(private val cardRepository: CardRepository) {

    suspend operator fun invoke(id: Long): Flow<ResultConstraints<String>> =
        cardRepository.deleteCardById(id)

}
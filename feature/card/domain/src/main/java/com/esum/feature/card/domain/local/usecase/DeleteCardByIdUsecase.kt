package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class DeleteCardByIdUsecase @Inject constructor(private val cardRepository: CardRepository) {

    suspend operator fun invoke(id: UUID): Flow<ResultConstraints<String>> =
        cardRepository.deleteCardById(id)

}
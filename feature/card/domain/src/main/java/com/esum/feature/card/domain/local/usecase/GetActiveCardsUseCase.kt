package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.CardStatusStates
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActiveCardsUseCase @Inject constructor(private val repository: CardRepository) {
    operator fun invoke(): Flow<ResultConstraints<CardStatusStates>> =
        repository.getActiveCardsCount().map { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    ResultConstraints.Error(result.message.toString())
                }

                is ResultConstraints.Loading -> {
                    ResultConstraints.Loading()
                }

                is ResultConstraints.Success -> {
                    ResultConstraints.Success(
                        CardStatusStates(
                            needToLearnCardsPercentage = (result.data?.second ?: 0).div(
                                if (result.data?.first.isNullOrEmpty().not()) result.data?.first?.size
                                    ?: 1 else 1
                            ).toDouble(),
                            needToLearnCardsCount = (result.data?.second ?: 0).toString(),
                            activeCardsCount = result.data?.first?.firstOrNull() { it.active }?.count?.toString()
                                ?: "0",
                            activeCardsPercentage = (result.data?.first?.filter { it.active }?.size?.toDouble()
                                ?: 0.0).div(
                                if (result.data?.first.isNullOrEmpty().not()) result.data?.first?.size
                                    ?: 1 else 1
                            ),
                            allCardsCount = ((result.data?.first?.firstOrNull() { activeCardsCount -> activeCardsCount.active }?.count
                                ?: 0) + (result.data?.first?.firstOrNull() { activeCardsCount -> !activeCardsCount.active }?.count
                                ?: 0)).toString(),
                            allCardsPercentage = 100.0,
                            completeCardsCount = result.data?.first?.filter { !it.active }?.size.toString(),
                            completeCardsPercentage = (result.data?.first?.filter { !it.active }?.size?.toDouble()
                                ?: 0.0).div(
                                if (result.data?.first.isNullOrEmpty().not()) result.data?.first?.size!!
                                     else 1
                            )
                        )
                    )
                }
            }
        }.flowOn(Dispatchers.IO)
}
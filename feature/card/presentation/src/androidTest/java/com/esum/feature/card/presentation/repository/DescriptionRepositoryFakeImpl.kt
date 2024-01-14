package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.feature.card.domain.remote.description.repository.DescriptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DescriptionRepositoryFakeImpl : DescriptionRepository {
    override suspend fun getDescription(
        word: String,
        languages: Languages
    ): Flow<ResultConstraints<DescriptionModel>> = flow {


    }
}
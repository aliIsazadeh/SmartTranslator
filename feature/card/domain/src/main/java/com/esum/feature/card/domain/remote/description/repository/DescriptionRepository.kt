package com.esum.feature.card.domain.remote.description.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import kotlinx.coroutines.flow.Flow

interface DescriptionRepository {

    suspend fun getDescription(
        word: String,
        languages: Languages
    ): Flow<ResultConstraints<DescriptionModel>>

}
package com.esum.feature.card.domain.remote.description.usecase

import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.remote.description.repository.DescriptionRepository
import javax.inject.Inject

class GetDescriptionUsecase @Inject constructor(private val repository: DescriptionRepository) {


    suspend operator fun invoke(word: String, languages: Languages) =
        repository.getDescription(word, languages)

}
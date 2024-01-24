package com.esum.feature.card.presentation.reviewCards.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.constraints.ResultConstraints
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.core.ui.component.PositiveAction
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.usecase.GetCardReviewsUsecase
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.state.CardFrontState
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ReviewCardsViewModel @Inject constructor(private val getReviewUsecase: Provider<GetCardReviewsUsecase>) :
    ViewModel(), ReviewCardsContract {

    private val _mutableState: MutableStateFlow<ReviewCardsContract.State> =
        MutableStateFlow(ReviewCardsContract.State())

    val cardReviews: StateFlow<List<CardWithLanguage>> =
        getReviewUsecase.get().invoke().map { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    _mutableState.update {
                        it.copy(loading = false)
                    }
                    addError(
                        title = R.string.an_error_accoured,
                        description = R.string.something_went_wrong,
                        sticker = R.drawable.lagging
                    )
                    emptyList<CardWithLanguage>()
                }

                is ResultConstraints.Loading -> {
                    _mutableState.update {
                        it.copy(loading = true)
                    }
                    emptyList<CardWithLanguage>()
                }

                is ResultConstraints.Success -> {
                    result.data?.let { list ->
                        _mutableState.update { state ->
                            state.copy(
                                reviewCardState = ArrayDeque(list.map { cardWithLanguage ->
                                    ReviewCardState(
                                        CardFrontState(
                                            pronunciation = cardWithLanguage.descriptionModel?.first?.description?.phonetic,
                                            audio = cardWithLanguage.descriptionModel?.first?.description?.audio,
                                            example = cardWithLanguage.descriptionModel?.first?.description?.meanings?.map
                                            { meanings -> meanings.definitions.firstOrNull { it.example?.isNotBlank() == true }?.example }
                                                ?.firstOrNull() ?: "",
                                            original = cardWithLanguage.original,
                                            originalLanguages = cardWithLanguage.originalLanguage,
                                            click = {}
                                        ),
                                        cardWithLanguage
                                    )
                                })
                            )
                        }
                    }
                    emptyList()
                }
            }

        }.stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(8000)
        )


    override val state: StateFlow<ReviewCardsContract.State> = _mutableState

    private val channelEffects = Channel<ReviewCardsContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<ReviewCardsContract.Effect> = channelEffects.receiveAsFlow()
    override fun event(event: ReviewCardsContract.Event) {
        TODO("Not yet implemented")
    }


    private fun addError(title: Int, description: Int, sticker: Int) {
        _mutableState.update {
            it.copy(
                errors = GenericDialogInfo.Builder().title(title)
                    .sticker(sticker = sticker)
                    .description(description = description)
                    .positive(
                        PositiveAction(
                            positiveBtnTxt = R.string.ok,
                            onPositiveAction = { _mutableState.update { it.copy(errors = null) } })
                    ).onDismiss {
                        _mutableState.update { it.copy(errors = null) }
                    }.build()
            )
        }
    }

}
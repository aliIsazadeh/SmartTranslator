package com.esum.feature.card.presentation.reviewCards.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.constraints.ResultConstraints
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.core.ui.component.PositiveAction
import com.esum.feature.card.domain.local.usecase.GetCardReviewsUsecase
import com.esum.feature.card.domain.local.usecase.UpdateCardUsecase
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.state.CardFrontState
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ReviewCardsViewModel @Inject constructor(
    private val getReviewUsecase: Provider<GetCardReviewsUsecase>,
    private val updateCardUsecase: Provider<UpdateCardUsecase>
) :
    ViewModel(), ReviewCardsContract {

    private val TAG = "ReviewCardsViewModel"

    private val _mutableState: MutableStateFlow<ReviewCardsContract.State> =
        MutableStateFlow(ReviewCardsContract.State())

    init {
        getReviewUsecase.get().invoke().onEach { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    _mutableState.update {
                        it.copy(loading = false)
                    }
                    addError(
                        title = R.string.an_error_accoured,
                        description = R.string.something_went_wrong,
                        sticker = R.raw.lagging
                    )
                }

                is ResultConstraints.Loading -> {
                    _mutableState.update {
                        it.copy(loading = true)
                    }
                }
                is ResultConstraints.Success -> {
                    result.data?.let { list ->
                        if (list.isEmpty()) {
                            _mutableState.update {
                                it.copy(
                                    cardState = null,
                                    listSize = list.size,
                                    currentCards = list.size,
                                    loading = false
                                )
                            }
                            return@onEach
                        }
                        list.map { cardWithLanguage ->
                            ReviewCardState(
                                CardFrontState(
                                    pronunciation = cardWithLanguage.descriptionModel?.first?.description?.phonetic,
                                    audio = cardWithLanguage.descriptionModel?.first?.description?.audio,
                                    correctAnswerCount = cardWithLanguage.descriptionModel?.first?.correctAnswerCount
                                        ?: 0,
                                    example = cardWithLanguage.descriptionModel?.first?.description?.meanings?.find
                                    { descriptionMeanings ->
                                        descriptionMeanings.definitions.any { descriptionDefinition
                                            ->
                                            descriptionDefinition.definition != null
                                        }
                                    }
                                        ?.definitions?.firstOrNull { it.definition?.isNotBlank() == true }?.definition,
                                    original = cardWithLanguage.original,
                                    originalLanguages = cardWithLanguage.originalLanguage,
                                ),
                                cardWithLanguage,
                            )
                        }
                    }?.toList()?.let { list ->

                        _mutableState.update {
                            it.copy(
                                cardState = list.first(),
                                listSize = list.size,
                                currentCards = list.size,
                                loading = false,
                                nexReviewDays = getNextDaysOnReview(list.first().cardBackState.descriptionModel?.first?.correctAnswerCount),
                                reviewCards = ArrayDeque(list)
                            )
                        }
                        ArrayDeque(list).apply {
                            this.removeFirstOrNull()
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

    }
    private fun nextCard() {
        _mutableState.update {
            val card = _mutableState.value.reviewCards.removeFirstOrNull()
            it.copy(
                cardState = card,
                currentCards = if (it.currentCards > 0) {
                    it.currentCards - 1
                } else 0,
                nexReviewDays = getNextDaysOnReview(card?.cardBackState?.descriptionModel?.first?.correctAnswerCount)
            )
        }
    }


    override val state: StateFlow<ReviewCardsContract.State> = _mutableState

    private val channelEffects = Channel<ReviewCardsContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<ReviewCardsContract.Effect> = channelEffects.receiveAsFlow()
    override fun event(event: ReviewCardsContract.Event) {
        when (event) {
            is ReviewCardsContract.Event.OnKnowClick -> {

                knowThisCard(event.count)

                rotate(true)

            }
            is ReviewCardsContract.Event.OnLearnClick -> {
                needToLearn()
                knowThisCard(1)
                rotate(true)

            }

            is ReviewCardsContract.Event.OnRotate -> {
                rotate(event.bol)
            }
            is ReviewCardsContract.Event.NeedToLearn -> {
                needToLearn()
            }
        }
    }


    private fun needToLearn(){
        rotate(false)
        _mutableState.update {
            it.copy(needToLearn = !it.needToLearn)
        }
    }

    private fun rotate(bol: Boolean?) {
        Log.d(TAG, "rotate:")
        channelEffects.trySend(ReviewCardsContract.Effect.RotateCard(bol))
    }

    private fun knowThisCard(correctAnswerCount: Int) {
        viewModelScope.launch {
            state.value.cardState?.cardBackState?.let { cardWithLanguage ->
                val card = cardWithLanguage.apply {
                    descriptionModel?.first?.correctAnswerCount = correctAnswerCount
                }
                updateCardUsecase.get().invoke(
                    card
                ).onEach { result ->

                    when (result) {
                        is ResultConstraints.Error -> {
                            _mutableState.update { it.copy(loading = false) }
                            addError(
                                R.string.an_error_accoured,
                                description = R.string.something_went_wrong,
                                sticker = R.raw.dont_know
                            )
                        }

                        is ResultConstraints.Loading -> _mutableState.update { it.copy(loading = true) }
                        is ResultConstraints.Success -> {
                            _mutableState.update { it.copy(loading = false) }
                            nextCard()
                        }
                    }

                }.launchIn(viewModelScope)
            }
        }
    }


    private fun addError(title: Int, description: Int, sticker: Int) {
        _mutableState.update { it ->
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

fun getNextDaysOnReview(value: Int?): Int {
    return when (value
        ?: 0) {
        0 -> {
            1
        }

        1 -> {
            3
        }

        2 -> {
            7
        }

        3 -> {
            30
        }

        4 -> {
            90
        }

        5 -> {
            180
        }

        else -> {
            360
        }
    }
}

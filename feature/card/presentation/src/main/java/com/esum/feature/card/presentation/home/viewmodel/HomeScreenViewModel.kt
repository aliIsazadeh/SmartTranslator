package com.esum.feature.card.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.ActiveCardsCount
import com.esum.feature.card.domain.local.model.CardStatusStates
import com.esum.feature.card.domain.local.usecase.GetActiveCardsUseCase
import com.esum.feature.card.presentation.component.LineBarState
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
class HomeScreenViewModel @Inject constructor(private val getActiveCardsUsecase: Provider<GetActiveCardsUseCase>) :
    ViewModel(), CardHomeContract {
    private val _mutableState = MutableStateFlow(
        CardHomeContract.State()
    )
    override val state: StateFlow<CardHomeContract.State> = _mutableState
    private val effectChannel = Channel<CardHomeContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<CardHomeContract.Effect> = effectChannel.receiveAsFlow()

    val activeCardsState: StateFlow<List<CardStatusStates>> =
        getActiveCardsUsecase.get().invoke().map { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    effectChannel.trySend(CardHomeContract.Effect.ShowSnackBar(message = 0))
                    emptyList<CardStatusStates>()
                }

                is ResultConstraints.Loading -> {
                    emptyList<CardStatusStates>()
                }

                is ResultConstraints.Success -> {
                    _mutableState.update { state ->
                        state.copy(
                            allCards = LineBarState(
                                percentage = 100.0,
                                count = result.data?.allCardsCount ?: "0"
                            ),
                            activeCards = LineBarState(
                                percentage = result.data?.activeCardsPercentage ?: 0.0,
                                count = result.data?.activeCardsCount ?: "0"
                            ),
                            completedCards = LineBarState(
                                percentage = result.data?.completeCardsPercentage ?: 0.0,
                                count = result.data?.completeCardsCount ?: "0"
                            ),
                            needToLearnCards = LineBarState(
                                percentage = result.data?.needToLearnCardsPercentage ?: 0.0,
                                count = result.data?.needToLearnCardsCount ?: "0"
                            ),
                        )
                    }
                    emptyList<CardStatusStates>()
                }
            }
        }.stateIn(
            started = SharingStarted.WhileSubscribed(5000L),
            scope = viewModelScope,
            initialValue = emptyList()
        )


    override fun event(event: CardHomeContract.Event) {
        TODO("Not yet implemented")
    }


}
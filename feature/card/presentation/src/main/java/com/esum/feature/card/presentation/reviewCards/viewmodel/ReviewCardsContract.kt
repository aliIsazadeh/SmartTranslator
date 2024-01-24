package com.esum.feature.card.presentation.reviewCards.viewmodel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import java.util.Queue

interface ReviewCardsContract :
    UnidirectionalViewModel<ReviewCardsContract.State, ReviewCardsContract.Effect, ReviewCardsContract.Event> {


    @Stable
    data class State(
        val reviewCardState: ArrayDeque<ReviewCardState> = ArrayDeque(listOf()),
        val numberOfReviews: String = "",
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false
    )

    sealed interface Event {

        data object OnKnowClick : Event
        data object OnLearnClick : Event

    }

    sealed interface Effect {
        data class ShowSnackBar(val message: String) : Effect
    }


}
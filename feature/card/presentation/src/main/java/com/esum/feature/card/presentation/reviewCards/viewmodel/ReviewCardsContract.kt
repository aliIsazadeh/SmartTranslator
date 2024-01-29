package com.esum.feature.card.presentation.reviewCards.viewmodel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import java.util.Queue

interface ReviewCardsContract :
    UnidirectionalViewModel<ReviewCardsContract.State, ReviewCardsContract.Effect, ReviewCardsContract.Event> {


    @Stable
    data class State(
        val reviewCards : ArrayDeque<ReviewCardState> = ArrayDeque(emptyList()),
        val cardState: ReviewCardState ? = null,
        val listSize : Int = 0,
        val numberOfReviews: String = "",
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false,
    )

    sealed interface Event {

        data object OnKnowClick : Event
        data object OnLearnClick : Event

        data class OnRotate(val bol: Boolean?) : Event

    }

    sealed interface Effect {
        data class ShowSnackBar(val message: String) : Effect
        data class RotateCard(val boolean: Boolean?) : Effect
    }


}
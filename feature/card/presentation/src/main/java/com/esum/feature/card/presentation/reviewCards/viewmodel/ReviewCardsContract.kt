package com.esum.feature.card.presentation.reviewCards.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import java.util.Queue

interface ReviewCardsContract :
    UnidirectionalViewModel<ReviewCardsContract.State, ReviewCardsContract.Effect, ReviewCardsContract.Event> {


    @Stable
    data class State(
        val reviewCards: ArrayDeque<ReviewCardState> = ArrayDeque(emptyList()),
        val cardState: ReviewCardState? = null,
        val listSize: Int = 0,
        val currentCards: Int = 0,
        val nexReviewDays: Int = 0,
        val numberOfReviews: Int = 1,
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false,
        val needToLearn : Boolean = false,
    )

    sealed interface Event {

        data class OnKnowClick(val count: Int) : Event
        data object OnLearnClick : Event
        data object NeedToLearn : Event
        data class OnRotate(val bol: Boolean?) : Event

    }

    sealed interface Effect {
        data class ShowSnackBar(val message: String) : Effect
        data class RotateCard(val boolean: Boolean?) : Effect
    }


}
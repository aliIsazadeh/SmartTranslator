package com.esum.feature.card.presentation.home.viewmodel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.presentation.addingCard.viewmodel.CardAddingContract
import com.esum.feature.card.presentation.component.LineBarState

interface CardHomeContract :
    UnidirectionalViewModel<CardHomeContract.State, CardHomeContract.Effect, CardHomeContract.Event> {


    sealed interface Event {
        data object OpenCameraEvent : Event
        data object AddCardEvent : Event
        data object ReviewCardsEvent : Event
        data object OpenProfile : Event
        data object OpenSetting : Event

    }

    sealed interface Effect {
        data class ShowSnackBar(val message : Int, val success : Boolean ? = null) : Effect
    }


    @Stable
    data class State(
        val completedCards : LineBarState = LineBarState(),
        val activeCards : LineBarState = LineBarState(),
        val needToLearnCards : LineBarState = LineBarState(),
        val allCards : LineBarState = LineBarState(),
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false
        //profile
    )


}
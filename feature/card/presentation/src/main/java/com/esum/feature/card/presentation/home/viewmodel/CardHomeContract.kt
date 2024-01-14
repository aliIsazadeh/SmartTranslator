package com.esum.feature.card.presentation.home.viewmodel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.feature.card.presentation.addingCard.viewmodel.CardAddingContract

interface CardHomeContract :
    UnidirectionalViewModel<CardAddingContract.State, CardAddingContract.Effect, CardAddingContract.Event> {


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


//    @Stable
//    data class State(
//        val
//    )


}
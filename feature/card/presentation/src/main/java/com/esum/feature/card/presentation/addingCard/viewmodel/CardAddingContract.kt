package com.esum.feature.card.presentation.addingCard.viewmodel

import androidx.compose.runtime.Stable
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.presentation.addingCard.state.AddCardScreenState

interface CardAddingContract :
    UnidirectionalViewModel<CardAddingContract.State, CardAddingContract.Effect, CardAddingContract.Event> {


    sealed interface Event {
        data object OnlineTranslateEvent : Event
        data object GenerateSentenceEvent : Event
        data object SelectOriginalOriginEvent : Event
        data object SelectTranslateOriginEvent : Event
        data object SaveCardEvent : Event
        data object CancelEvent : Event
        data object backEvent : Event
    }

    sealed interface Effect {
        data class ShowSnackBar(val message: Int, val success: Boolean) : Effect
        object SetFocusOnTranslateTextFiled : Effect

        object SetFocusOnOriginalTextFiled : Effect


    }

    @Stable
    data class State(
        val card: AddCardScreenState = AddCardScreenState(),
        val availableLanguage : List<Pair<Languages, Int>> = listOf(),
        val sentence: String? = null,
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false

    )


}
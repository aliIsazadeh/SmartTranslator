package com.esum.feature.card.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.UnidirectionalViewModel
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.feature.card.domain.local.model.Card

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

    }

    @Stable
    data class State(
        val card: Card = Card(),
        val availableLanguage : List<Pair<Languages, Int>> = listOf(),
        val sentence: String? = null,
        val errors: GenericDialogInfo? = null,
        val loading: Boolean = false

    )


}
package com.esum.feature.card.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel(), CardHomeContract {

    private val _mutableState = MutableStateFlow(
        CardHomeContract.State()
    )
    override val state: StateFlow<CardHomeContract.State> = _mutableState
    private val eventChannel = Channel<CardHomeContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<CardHomeContract.Effect> = eventChannel.receiveAsFlow()
    override fun event(event: CardHomeContract.Event) {
        TODO("Not yet implemented")
    }


}
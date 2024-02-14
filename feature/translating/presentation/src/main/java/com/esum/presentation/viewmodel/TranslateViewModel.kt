package com.esum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() , TranslateContract {

    private val _mutableState : MutableStateFlow<TranslateContract.STATE> = MutableStateFlow(
        TranslateContract.STATE())

    private val effectChannel : Channel<TranslateContract.EFFECT> = Channel<TranslateContract.EFFECT>(Channel.UNLIMITED)

    override val effect: Flow<TranslateContract.EFFECT> = effectChannel.receiveAsFlow()

    override val state: StateFlow<TranslateContract.STATE> = _mutableState.asStateFlow()

    override fun event(event: TranslateContract.EVENT) {
        when (event) {
            TranslateContract.EVENT.Translate -> TODO()
        }
    }

}
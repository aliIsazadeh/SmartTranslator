package com.esum.feature.card.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.core.ui.component.PositiveAction
import com.esum.feature.card.domain.usecase.InsertCardUsecase
import com.esum.feature.card.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingCardViewModel @Inject constructor(
    private val insertCardUsecase : Lazy<InsertCardUsecase>

) : ViewModel(), CardAddingContract {

    private val TAG = "CardAddingContract"

    private val _mutableState = MutableStateFlow(CardAddingContract.State())
    override val state: StateFlow<CardAddingContract.State> = _mutableState

    private val effectChannel = Channel<CardAddingContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<CardAddingContract.Effect> = effectChannel.receiveAsFlow()




    override fun event(event: CardAddingContract.Event) {
        viewModelScope.launch {
            try {
                when (event) {
                    CardAddingContract.Event.CancelEvent -> TODO()
                    CardAddingContract.Event.GenerateSentenceEvent -> TODO()
                    CardAddingContract.Event.OnlineTranslateEvent -> TODO()
                    CardAddingContract.Event.SaveCardEvent -> TODO()
                    CardAddingContract.Event.SelectOriginalOriginEvent -> TODO()
                    CardAddingContract.Event.SelectTranslateOriginEvent -> TODO()
                    CardAddingContract.Event.backEvent -> TODO()
                }
            } catch (e: Exception) {
                Log.e(TAG, "event: ${e.message.toString()}")
                _mutableState.update {
                    it.copy(
                        errors = GenericDialogInfo.Builder().title(R.string.something_went_wrong)
                            .description(description = R.drawable.mind_blow)
                            .positive(
                                PositiveAction(
                                    positiveBtnTxt = R.string.ok,
                                    onPositiveAction = { _mutableState.update { it.copy(errors = null) } })
                            ).build()
                    )
                }
            }
        }
    }

    fun onOriginalChange(original : Pair<String , Languages>){
        setOriginal(original)
    }
    private fun setOriginal(original : Pair<String , Languages>) {
        _mutableState.update { it.copy(card = it.card.copy(original = original)) }
    }

    fun onTranslationChange(translate : Pair<String , Languages>){
        setTranslation(translate)
    }
    private fun setTranslation(translate : Pair<String , Languages>) {
        _mutableState.update { it.copy(card = it.card.copy(translate = translate)) }
    }

    fun onSentenceChange(sentence : String) {
        setSentence(sentence)
    }
    private fun setSentence(sentence : String){
        _mutableState.update { it.copy(sentence = sentence) }
    }

    companion object {
        const val STATE_KEY_ORIGINAL = "card.state.original"
        const val STATE_TRANSLATED = "card.state.translated"


    }


}
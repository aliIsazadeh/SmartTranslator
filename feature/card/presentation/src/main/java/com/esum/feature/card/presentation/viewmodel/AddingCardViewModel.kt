package com.esum.feature.card.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.core.ui.component.PositiveAction
import com.esum.feature.card.domain.UsecaseFactory
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
    private val insertCardUsecase: InsertCardUsecase

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
                    CardAddingContract.Event.CancelEvent -> {}
                    CardAddingContract.Event.GenerateSentenceEvent -> {}
                    CardAddingContract.Event.OnlineTranslateEvent -> {}
                    CardAddingContract.Event.SaveCardEvent -> {}
                    CardAddingContract.Event.SelectOriginalOriginEvent -> {}
                    CardAddingContract.Event.SelectTranslateOriginEvent -> {}
                    CardAddingContract.Event.backEvent -> {}
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

    fun onOriginalChange(original : String ){
        setOriginal(original)
    }
    private fun setOriginal(original :String ) {
        _mutableState.update { it.copy(card = it.card.copy(original = original)) }
    }
    fun onOriginalChange(original :Languages){
        setOriginal(original)
    }
    private fun setOriginal(original :  Languages) {
        _mutableState.update { it.copy(card = it.card.copy(originalLanguage = original)) }
    }

    fun onTranslationChange(translate : String ){
        setTranslation(translate)
    }
    private fun setTranslation(translate : String ) {
        _mutableState.update { it.copy(card = it.card.copy(translate = translate)) }
    }
    fun onTranslationChange(translate :  Languages){
        setTranslation(translate)
    }
    private fun setTranslation(translate :  Languages) {
        _mutableState.update { it.copy(card = it.card.copy(translateLanguage = translate)) }
    }

    fun onSentenceChange(sentence : String) {
        setSentence(sentence)
    }
    private fun setSentence(sentence : String){
        _mutableState.update { it.copy(card = it.card.copy(sentence = sentence)) }
    }

    companion object {
        const val STATE_KEY_ORIGINAL = "card.state.original"
        const val STATE_TRANSLATED = "card.state.translated"


    }


}
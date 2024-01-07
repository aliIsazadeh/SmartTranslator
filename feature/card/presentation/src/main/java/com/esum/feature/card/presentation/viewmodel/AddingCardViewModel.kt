package com.esum.feature.card.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.constraints.ResultConstraints
import com.esum.common.constraints.TranslateErrors
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.component.GenericDialogInfo
import com.esum.core.ui.component.PositiveAction
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.remote.usecase.TranslateCardUseCase
import com.esum.feature.card.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import kotlin.math.log

@HiltViewModel
class AddingCardViewModel @Inject constructor(
    private val insertCardUsecase: Provider<InsertCardUsecase>,
    private val translateCardUseCase: Provider<TranslateCardUseCase>

) : ViewModel(), CardAddingContract {

    private val TAG = "CardAddingContract"

    private val _mutableState = MutableStateFlow(
        CardAddingContract.State(
            availableLanguage = listOf(
                Pair<Languages, Int>(Languages.Farsi, R.drawable.iran),
                Pair<Languages, Int>(Languages.English, R.drawable.united_kingdom),
                Pair<Languages, Int>(Languages.French, R.drawable.france),
                Pair<Languages, Int>(Languages.Italian, R.drawable.italy),
                Pair<Languages, Int>(Languages.Arabic, R.drawable.saudi_arabia),
                Pair<Languages, Int>(Languages.Japans, R.drawable.japan),
            )
        )
    )
    override val state: StateFlow<CardAddingContract.State> = _mutableState

    private val effectChannel = Channel<CardAddingContract.Effect>(Channel.UNLIMITED)
    override val effect: Flow<CardAddingContract.Effect> = effectChannel.receiveAsFlow()


    override fun event(event: CardAddingContract.Event) {
        viewModelScope.launch {
            try {
                when (event) {
                    CardAddingContract.Event.CancelEvent -> {}
                    CardAddingContract.Event.GenerateSentenceEvent -> {
                        onlineGenerateSentence()
                    }

                    CardAddingContract.Event.OnlineTranslateEvent -> {
                        onlineTranslate()
                    }

                    CardAddingContract.Event.SaveCardEvent -> {
                        insertCard()
                    }

                    CardAddingContract.Event.SelectOriginalOriginEvent -> {}
                    CardAddingContract.Event.SelectTranslateOriginEvent -> {}
                    CardAddingContract.Event.backEvent -> {
                        throw Exception()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "event: ${e.message.toString()}")
                addError(
                    title = R.string.an_error_accoured,
                    description = R.string.something_went_wrong,
                    sticker = R.drawable.mind_blow
                )
            }
        }
    }

    private fun insertCard() {
        viewModelScope.launch {
            insertCardUsecase.get().invoke(state.value.card).collect() {
                when (it) {
                    is ResultConstraints.Error -> {
                        Log.e(TAG, "insertCard: ${it.message}")
                        addError(
                            title = R.string.an_error_accoured,
                            description = R.string.something_went_wrong,
                            sticker = R.drawable.lagging
                        )
                    }

                    is ResultConstraints.Loading -> {}
                    is ResultConstraints.Success -> {
                        effectChannel.trySend(
                            CardAddingContract.Effect.ShowSnackBar(
                                message = R.string.add_card_success_full,
                                success = true
                            )
                        )
                    }
                }
            }

        }
    }

    private fun onlineTranslate() {
        viewModelScope.launch {
            _mutableState.value.card.apply {
                translateCardUseCase.get().invoke(
                    fromLanguages = originalLanguage,
                    toLanguages = translateLanguage,
                    text = original
                ).onEach  { result ->
                    when (result) {
                        is ResultConstraints.Error -> {
                            _mutableState.update {
                                it.copy(loading = false)
                            }
                            when (TranslateErrors.getTranslateErrorByMessage(
                                result.message ?: ""
                            )) {
                                TranslateErrors.ResponseIsEmpty -> addError(
                                    title = R.string.unable_toTranslate,
                                    description = R.string.no_translation_find,
                                    sticker = R.drawable.dont_know
                                )

                                TranslateErrors.ErrorInRequest -> {
                                    Log.e(TAG, "onlineTranslate: ${result.message}", )
                                    addError(
                                        title = R.string.unable_toTranslate,
                                        description = R.string.something_went_wrong,
                                        sticker = R.drawable.lagging
                                    )
                                }
                            }
                        }
                        is ResultConstraints.Loading -> _mutableState.update {
                            it.copy(loading = true)
                        }
                        is ResultConstraints.Success -> _mutableState.update {
                            it.copy(
                                loading = false,
                                card = it.card.copy(translate = result.data!!.translated)
                            )
                        }
                    }

                }.launchIn(viewModelScope)
            }
        }
    }

    private fun onlineGenerateSentence() {
        addError(
            title = R.string.unable_to_find_sentence,
            description = R.string.something_went_wrong,
            sticker = R.drawable.dont_know
        )
    }


    private fun addError(title: Int, description: Int, sticker: Int) {
        _mutableState.update {
            it.copy(
                errors = GenericDialogInfo.Builder().title(title)
                    .sticker(sticker = sticker)
                    .description(description = description)
                    .positive(
                        PositiveAction(
                            positiveBtnTxt = R.string.ok,
                            onPositiveAction = { _mutableState.update { it.copy(errors = null) } })
                    ).onDismiss {
                        _mutableState.update { it.copy(errors = null) }
                    }.build()
            )
        }
    }


    fun onOriginalChange(original: String) {
        setOriginal(original)
    }

    private fun setOriginal(original: String) {
        _mutableState.update { it.copy(card = it.card.copy(original = original)) }
    }

    fun onOriginalChange(original: Languages) {
        setOriginal(original)
    }

    private fun setOriginal(original: Languages) {
        _mutableState.update { it.copy(card = it.card.copy(originalLanguage = original)) }
    }

    fun onTranslationChange(translate: String) {
        setTranslation(translate)
    }

    private fun setTranslation(translate: String) {
        _mutableState.update { it.copy(card = it.card.copy(translate = translate)) }
    }

    fun onTranslationChange(translate: Languages) {
        setTranslation(translate)
    }

    private fun setTranslation(translate: Languages) {
        _mutableState.update { it.copy(card = it.card.copy(translateLanguage = translate)) }
    }

    fun onSentenceChange(sentence: String) {
        setSentence(sentence)
    }

    private fun setSentence(sentence: String) {
        _mutableState.update { it.copy(card = it.card.copy(sentence = sentence)) }
    }

    companion object {
        const val STATE_KEY_ORIGINAL = "card.state.original"
        const val STATE_TRANSLATED = "card.state.translated"


    }


}
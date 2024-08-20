package com.esum.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.presentation.R
import com.esum.feature.translating.domain.translating.usecase.TranslateCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val translatingCardUseCase: TranslateCardUseCase
) : ViewModel(), TranslateContract {

    private val text: String = savedStateHandle.get<String>("text") ?: ""


    private val _mutableState: MutableStateFlow<TranslateContract.STATE> = MutableStateFlow(
        TranslateContract.STATE(
            text = text, availableLanguage = listOf(
                Pair<Languages, Int>(Languages.Farsi, R.drawable.iran),
                Pair<Languages, Int>(Languages.English, R.drawable.united_kingdom),
                Pair<Languages, Int>(Languages.French, R.drawable.france),
                Pair<Languages, Int>(Languages.Italian, R.drawable.italy),
                Pair<Languages, Int>(Languages.Arabic, R.drawable.saudi_arabia),
                Pair<Languages, Int>(Languages.Japans, R.drawable.japan),
            )
        )
    )

    private val effectChannel: Channel<TranslateContract.EFFECT> =
        Channel<TranslateContract.EFFECT>(Channel.UNLIMITED)

    override val effect: Flow<TranslateContract.EFFECT> = effectChannel.receiveAsFlow()

    override val state: StateFlow<TranslateContract.STATE> = _mutableState.asStateFlow()

    override fun event(event: TranslateContract.EVENT) {
        when (event) {
            is TranslateContract.EVENT.Translate -> {
                translate()
            }
        }
    }

    fun onChangeText(value: String) {
        _mutableState.update {
            it.copy(text = value)
        }
    }

    fun onTranslateChange(value: String) {
        _mutableState.update {
            it.copy(translatedText = value)
        }
    }

    fun onOriginalChange(original: Languages) {
        setOriginal(original)
    }

    private fun setOriginal(original: Languages) {
        _mutableState.update { it.copy(originalLanguages = original) }
    }


    fun onTranslationChange(translate: Languages) {
        setTranslation(translate)
    }

    private fun setTranslation(translate: Languages) {
        _mutableState.update { it.copy(translateLanguages = translate) }
    }

    fun translate() {
        translatingCardUseCase.invoke(
            text = _mutableState.value.text,
            fromLanguages = _mutableState.value.originalLanguages,
            toLanguages = _mutableState.value.translateLanguages
        ).onEach { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    _mutableState.update {
                        it.copy(loading = false)
                    }
                }

                is ResultConstraints.Loading -> {
                    _mutableState.update {
                        it.copy(loading = true)
                    }
                }

                is ResultConstraints.Success -> {
                    result.data?.let { data ->
                        _mutableState.update {
                            it.copy(loading = false, translatedText = data.translated)
                        }

                    }


                }
            }


        }.launchIn(viewModelScope)
    }


}
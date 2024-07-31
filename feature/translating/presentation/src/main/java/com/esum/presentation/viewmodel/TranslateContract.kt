package com.esum.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.UnidirectionalViewModel

interface TranslateContract : UnidirectionalViewModel<TranslateContract.STATE , TranslateContract.EFFECT , TranslateContract.EVENT>{
    @Stable
    data class STATE(
        val loading : Boolean = false,
        val text : String = "",
        val translatedText : String = "",
        val availableLanguage : List<Pair<Languages, Int>> = listOf(),
        val originalLanguages: Languages = Languages.Farsi,
        val translateLanguages: Languages = Languages.English,

        )

    sealed interface EFFECT {

        data class ShowSnackBar(val message : Int) : EFFECT

        data class AlertDialog(val message: Int ,val icon : Int) : EFFECT


    }

    sealed interface EVENT {
        data class Translate (val text : String): EVENT
    }

}
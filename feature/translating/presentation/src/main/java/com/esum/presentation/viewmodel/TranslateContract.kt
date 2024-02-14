package com.esum.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel

interface TranslateContract : UnidirectionalViewModel<TranslateContract.STATE , TranslateContract.EFFECT , TranslateContract.EVENT>{
    @Stable
    data class STATE(
        val loading : Boolean = false
    )

    sealed interface EFFECT {

        data class ShowSnackBar(val message : Int) : EFFECT

        data class AlertDialog(val message: Int ,val icon : Int) : EFFECT


    }

    sealed interface EVENT {
        data object Translate : EVENT

    }

}
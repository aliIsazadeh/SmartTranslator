package com.esum.feature.translator.presentation.viewModel

import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel

interface CameraScreenContract : UnidirectionalViewModel<CameraScreenContract.State , CameraScreenContract.Effect , CameraScreenContract.Event> {


    sealed interface Event {

    }

    sealed interface Effect {

    }

    @Stable
    data class State(
        val loading : Boolean = false,

    )


}
package com.esum.feature.translator.presentation.viewModel

import androidx.camera.core.ImageProxy
import androidx.compose.runtime.Stable
import com.esum.core.ui.UnidirectionalViewModel

interface CameraScreenContract : UnidirectionalViewModel<CameraScreenContract.State , CameraScreenContract.Effect , CameraScreenContract.Event> {


    sealed interface Event {
        data class ImageSelected(val image : ImageProxy) : Event
        data class TextDetected(val text : String) : Event

        data object ImageCapture : Event

    }

    sealed interface Effect {
        data class ShowSnackBar(val message : String) : Effect
        data object ShowResult : Effect
        data object ShowError : Effect
    }

    @Stable
    data class State(
        val text : String = "",
        val loading : Boolean = false,
    )


}
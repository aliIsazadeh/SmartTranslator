package com.esum.feature.image_convertor.presentation.viewModel

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.esum.feature.image_convertor.presentation.analyzer.ImageTextAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ImageTranslateViewModel @Inject constructor() : ViewModel(), CameraScreenContract {


    private val effectChannel = Channel<CameraScreenContract.Effect>(Channel.UNLIMITED)

    private val _mutableState: MutableStateFlow<CameraScreenContract.State> =
        MutableStateFlow(CameraScreenContract.State())
    override val effect: Flow<CameraScreenContract.Effect> = effectChannel.receiveAsFlow()


    override val state: StateFlow<CameraScreenContract.State> = _mutableState.asStateFlow()

    override fun event(event: CameraScreenContract.Event) {
        when (event) {
            is CameraScreenContract.Event.ImageSelected -> imageSelected(event.image)
            is CameraScreenContract.Event.TextDetected -> textSelected(event.text)
            is CameraScreenContract.Event.ImageCapture -> {
                imageCaptured()
            }
        }
    }

    fun onTextChange(text : String){
        textSelected(text)
    }



    private fun imageCaptured(){
        _mutableState.update { it.copy(loading = true) }
    }


    private fun textSelected(text : String) {
        _mutableState.update {
            it.copy(text = text , loading = false)
        }
    }

    private fun imageSelected(imageProxy: ImageProxy) {

        try {


            _mutableState.update { it.copy(loading = true) }

            val imageTextAnalyzer = ImageTextAnalyzer(onTextDetected = { text ->
                _mutableState.update { it.copy(text = text, loading = false) }
            })

            imageTextAnalyzer.analyze(imageProxy)
        }catch (e : Exception){
            effectChannel.trySend(CameraScreenContract.Effect.ShowError)
        }
    }


}
package com.esum.feature.translator.presentation.analyzer

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
class ImageTextAnalyzer(private val onTextDetected: (String) -> Unit) : ImageAnalysis.Analyzer {

    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    private var text: String = ""
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            val img = InputImage.fromMediaImage(
                image,
                imageProxy.imageInfo.rotationDegrees
            )
            textRecognizer.process(img).addOnCompleteListener { task ->
                text = if (!task.isSuccessful) task.exception!!.localizedMessage?.toString()
                    .toString()
                else task.result.text
                imageProxy.close()
                onTextDetected(text)
            }
        }
    }
}
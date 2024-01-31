package com.esum.core.ui.component

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.core.ui.R

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    title: Int,
    sticker: Int?,
    description: Int? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
) {


//    val imageLoader = ImageLoader.Builder(LocalContext.current)
//        .components {
//            if (Build.VERSION.SDK_INT >= 28) {
//                add(ImageDecoderDecoder.Factory())
//            } else {
//                add(GifDecoder.Factory())
//            }
//        }
//        .build()

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        icon = {
//            Image(
//                painter = rememberAsyncImagePainter(
//                    ImageRequest.Builder(LocalContext.current)
//                        .data(data = sticker)
//                        .apply(block = fun ImageRequest.Builder.() {
//                            repeatCount(2)
//                            size(200)
//                        }).build(),
//                    imageLoader = imageLoader
//                ),
//                contentDescription = null,
//            )
           sticker?.let {
               val composition by rememberLottieComposition(
                   spec = LottieCompositionSpec.RawRes(
                       it
                   )
               )
               LottieAnimation(
                   composition = composition,
                   modifier = Modifier.size(200.dp),
                   iterations = 3,
                   restartOnPlay = false,
               )
           }},
        title = { Text(stringResource(title), style = MaterialTheme.typography.bodyMedium) },
        text = {
            if (description != null) {
                Text(
                    text = stringResource(description),
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                )
            }
        },
        confirmButton = {
            if (positiveAction != null) {
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = positiveAction.onPositiveAction,
                    shape = MaterialTheme.shapes.extraSmall
                ) {
                    Text(text = stringResource(positiveAction.positiveBtnTxt))
                }
            }
        },
        dismissButton = {
            if (negativeAction != null) {
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onError),
                    onClick = negativeAction.onNegativeAction,
                    shape = MaterialTheme.shapes.extraSmall
                ) {
                    Text(text = stringResource(negativeAction.negativeBtnTxt))
                }
            }
        }
    )
}

data class PositiveAction(
    val positiveBtnTxt: Int,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: Int,
    val onNegativeAction: () -> Unit,
)


class GenericDialogInfo private constructor(builder: Builder) {

    val title: Int
    val onDismiss: () -> Unit
    val description: Int?
    val sticker: Int?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if (builder.title == null) {
            throw Exception("GenericDialog title cannot be null.")
        }
        if (builder.onDismiss == null) {
            throw Exception("GenericDialog onDismiss function cannot be null.")
        }
        this.title = builder.title!!
        this.onDismiss = builder.onDismiss!!
        this.sticker = builder.sticker
        this.description = builder.description
        this.positiveAction = builder.positiveAction
        this.negativeAction = builder.negativeAction
    }

    class Builder {

        var title: Int? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var description: Int? = null
            private set

        var sticker: Int? = null
            private set


        var positiveAction: PositiveAction? = null
            private set

        var negativeAction: NegativeAction? = null
            private set

        fun title(title: Int): Builder {
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder {
            this.onDismiss = onDismiss
            return this
        }

        fun description(
            description: Int
        ): Builder {
            this.description = description
            return this
        }

        fun sticker(
            sticker: Int
        ): Builder {
            this.sticker = sticker
            return this
        }

        fun positive(
            positiveAction: PositiveAction?,
        ): Builder {
            this.positiveAction = positiveAction
            return this
        }

        fun negative(
            negativeAction: NegativeAction
        ): Builder {
            this.negativeAction = negativeAction
            return this
        }

        fun build() = GenericDialogInfo(this)
    }
}














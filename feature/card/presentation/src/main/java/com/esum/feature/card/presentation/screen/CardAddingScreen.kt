package com.esum.feature.card.presentation.screen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import coil.size.Size
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.feature.card.presentation.R

@Composable
fun CardAddingScreen() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.duck_phone_anime))
    val progress by animateLottieCompositionAsState(composition)

    val image = rememberAsyncImagePainter(model = R.drawable.duck_phone)

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Column(modifier = Modifier.fillMaxSize()  , horizontalAlignment = Alignment.CenterHorizontally  , verticalArrangement = Arrangement.SpaceEvenly){
        LottieAnimation(
            modifier = Modifier.weight(1f),
            composition = composition,
            progress = { progress },
        )
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = R.drawable.duck_phone)
                    .apply(block = fun ImageRequest.Builder.() {
                        repeatCount(1)
                        size(400)
                    }).build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
        )

    }

}

@Preview
@Composable
fun CardAddingScreenPreview(){

    MaterialTheme {
        CardAddingScreen()
    }

}
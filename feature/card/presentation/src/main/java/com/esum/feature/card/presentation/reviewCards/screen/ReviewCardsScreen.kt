package com.esum.feature.card.presentation.reviewCards.screen

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import com.esum.core.ui.CollectInLaunchedEffect
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.components.CardStack
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import com.esum.feature.card.presentation.reviewCards.viewmodel.ReviewCardsContract
import com.esum.feature.card.presentation.reviewCards.viewmodel.ReviewCardsViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@Composable
fun ReviewCardsScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    viewModel: ReviewCardsViewModel = hiltViewModel()
) {

    val (state, effect, event) = use(viewModel)
    ReviewCardsScreen(state, effect, event)
    val cards = viewModel.cardReviews.collectAsState()
}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ReviewCardsScreen(
    state: ReviewCardsContract.State,
    effect: Flow<ReviewCardsContract.Effect>,
    event: (ReviewCardsContract.Event) -> Unit
) {
    var fullScreenOrFlip by remember {
        mutableStateOf(true)
    }
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            DefaultTopBar(modifier = Modifier.fillMaxWidth(), leftComposable = {
                IconButton(onClick = { fullScreenOrFlip = !fullScreenOrFlip }) {
                    Icon(imageVector = Icons.Filled.SwitchLeft, contentDescription = "")
                }
            },
                rightComposable = {    Image(
                    modifier = Modifier.size(
                        40.dp
                    ),
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = R.drawable.review)
                            .apply(block = fun ImageRequest.Builder.() {
                                repeatCount(2)
                                size(70)
                            }).build(),
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                )

                },
                title = stringResource(R.string.review_cards)
            )
//            {
//                IconButton(onClick = { fullScreenOrFlip = !fullScreenOrFlip }) {
//                    Icon(imageVector = Icons.Filled.SwitchLeft, contentDescription = "")
//                }
//            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { event.invoke(ReviewCardsContract.Event.OnKnowClick) },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "I Know This one")
                }
                OutlinedButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = { event.invoke(ReviewCardsContract.Event.OnKnowClick) },
                    border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Need to Learn")
                }
            }
        }
    ) { paddingValues ->
        paddingValues

        var rotated by remember {
            mutableStateOf(true)
        }


        effect.CollectInLaunchedEffect() {
            when (it) {
                is ReviewCardsContract.Effect.ShowSnackBar -> TODO()
                is ReviewCardsContract.Effect.RotateCard -> {
                    rotated = it.boolean ?: !rotated
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            state.cardState?.let {
                CardStack(
                    state = state.cardState,
                    cardListSize = state.listSize,
                    onClick = { event.invoke(ReviewCardsContract.Event.OnRotate(null)) },
                    rotated = rotated,
                    audioClick = { audio ->
                        try {
                            val mediaPlayer = MediaPlayer().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .build()
                                )
                                setDataSource(audio)
                                prepare() // might take long! (for buffering, etc)
                                start()
                            }
                        } catch (e: Exception) {

                            Log.e("mediaPlayer", "CardAddingScreen: ${e.message}")
                        }
                    },
                    fullScreenOrFlip = fullScreenOrFlip
                )
            }
                ?: Box(
                    modifier = Modifier
                )
        }
    }
}


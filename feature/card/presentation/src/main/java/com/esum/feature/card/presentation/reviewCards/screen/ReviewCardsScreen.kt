package com.esum.feature.card.presentation.reviewCards.screen

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.core.ui.CollectInLaunchedEffect
import com.esum.core.ui.component.ResizableTextView
import com.esum.core.ui.component.shimmerLoadingAnimation
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.components.CardStack
import com.esum.feature.card.presentation.reviewCards.state.CardFrontState
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
//    val cards = viewModel..collectAsStateWithLifecycle()
}

@Composable
fun ReviewCardsScreen(
    state: ReviewCardsContract.State,
    effect: Flow<ReviewCardsContract.Effect>,
    event: (ReviewCardsContract.Event) -> Unit
) {
    val fullScreenOrFlip by remember {
        mutableStateOf(true)
    }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            DefaultTopBar(
                modifier = Modifier.fillMaxWidth(), leftComposable = {
                    Text(
                        text = if (state.currentCards < 0) "Done" else if (state.listSize == 0) {
                            "No Card"
                        } else {
                            "${state.currentCards} / ${state.listSize}"
                        },
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            )
                            .padding(8.dp)
                    )
                },
                rightComposable = {

                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            R.raw.review_tgs
                        )
                    )

                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.size(36.dp),
                        iterations = 3,
                        restartOnPlay = false,
                    )

                },
                title = stringResource(R.string.review_cards)
            )
        },
        bottomBar = {
            AnimatedVisibility(
                state.currentCards >= 0 && state.listSize != 0,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                if (state.needToLearn) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            onClick = { event.invoke(ReviewCardsContract.Event.OnLearnClick(state.cardState!!)) }) {
                            ResizableTextView(text = stringResource(id = R.string.review_tomorrow))
                        }
                    }

                } else {
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
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                event.invoke(
                                    ReviewCardsContract.Event.OnKnowClick(
                                        (state.cardState?.descriptionModel?.first?.correctAnswerCount
                                            ?: -1) + 1, state.cardState!!
                                    )
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        ) {
                            ResizableTextView(
                                text = if (state.nexReviewDays == 1) stringResource(id = R.string.review_tomorrow) else stringResource(
                                    id = R.string.reviewat,
                                    state.nexReviewDays.toString(),
                                ),
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = { event.invoke(ReviewCardsContract.Event.NeedToLearn) },
                            border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(text = "Need to Learn")
                        }
                    }
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
        if (state.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .shimmerLoadingAnimation(shape = MaterialTheme.shapes.medium)
                )
            }

        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                contentAlignment = Alignment.Center
            ) {


                state.cardState?.let {
                    CardStack(
                        state = it.let {cardWithLanguage ->
                            ReviewCardState(
                                CardFrontState(
                                    pronunciation = cardWithLanguage.descriptionModel?.first?.description?.phonetic,
                                    audio = cardWithLanguage.descriptionModel?.first?.description?.audio,
                                    correctAnswerCount = cardWithLanguage.descriptionModel?.first?.correctAnswerCount
                                        ?: 0,
                                    example = cardWithLanguage.descriptionModel?.first?.description?.meanings?.find
                                    { descriptionMeanings ->
                                        descriptionMeanings.definitions.any { descriptionDefinition
                                            ->
                                            descriptionDefinition.definition != null
                                        }
                                    }
                                        ?.definitions?.firstOrNull { it.definition?.isNotBlank() == true }?.definition,
                                    original = cardWithLanguage.original,
                                    originalLanguages = cardWithLanguage.originalLanguage,
                                ),
                                cardWithLanguage,
                            )
                        },
                        cardListSize = state.currentCards,
                        onClick = { event.invoke(ReviewCardsContract.Event.OnRotate(null)) },
                        rotated = rotated,
                        audioClick = { audio ->
                            try {
                                MediaPlayer().apply {
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
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.currentCards <= 0 && state.listSize != 0) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                val composition by rememberLottieComposition(
                                    spec = LottieCompositionSpec.RawRes(
                                        R.raw.duck_done_tgs
                                    )
                                )
                                LottieAnimation(
                                    composition = composition,
                                    modifier = Modifier.size(120.dp),
                                    iterations = 3,
                                    restartOnPlay = false,
                                )
                                Text(
                                    text = stringResource(R.string.congratulations_you_have_done_it),
                                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                                )

                            }
                        } else if (state.listSize == 0) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                val composition by rememberLottieComposition(
                                    spec = LottieCompositionSpec.RawRes(
                                        R.raw.bad_boy
                                    )
                                )
                                LottieAnimation(
                                    composition = composition,
                                    modifier = Modifier.size(120.dp),
                                    iterations = 3,
                                    restartOnPlay = false,
                                )
                                Text(
                                    text = stringResource(R.string.no_card_for_today),
                                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                                )
                            }
                        }
                    }
            }
        }
    }
}


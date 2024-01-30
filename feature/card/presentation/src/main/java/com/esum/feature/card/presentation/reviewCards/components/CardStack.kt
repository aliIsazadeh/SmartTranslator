package com.esum.feature.card.presentation.reviewCards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.feature.card.domain.local.model.CardDetails
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.remote.description.model.DescriptionDefinition
import com.esum.feature.card.domain.remote.description.model.DescriptionMeanings
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.feature.card.presentation.component.CardContent
import com.esum.feature.card.presentation.component.FlipCard
import com.esum.feature.card.presentation.component.FullScreenCards
import com.esum.feature.card.presentation.reviewCards.state.CardFrontState
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import com.esum.feature.card.presentation.reviewCards.viewmodel.ReviewCardsContract
import java.util.UUID

@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    state: ReviewCardState,
    cardListSize: Int,
    onClick: () -> Unit,
    rotated: Boolean,
    audioClick: (String) -> Unit,
    fullScreenOrFlip: Boolean,
) {
    val cardStackLimit = 5
    var currentTopCard by remember { mutableStateOf(0) }
//    val cardStack = cards.take(if (cards.size > cardStackLimit) cardStackLimit else cards.size)
    val remainedCards = if (cardListSize > cardStackLimit) cardStackLimit else cardListSize

    var fullScreen by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.Center,
    ) {


        repeat(remainedCards) { it ->
            val index = remainedCards - it

            if (index!=1){
                Box(
                    modifier = Modifier
                        .graphicsLayer {

                            translationX =
                                if (it < currentTopCard) 0f else (it + currentTopCard) * 10f - 100f
                            scaleX = 1f - ((it - currentTopCard) * 0.02f).coerceAtLeast(0f)
                            alpha = if (index < currentTopCard) 0f else 1f
                        },
                    // More card modifiers
                ) {

                    Card(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxHeight(0.7f - (index * 0.05f))
                            .fillMaxWidth(0.7f),
                        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                    }

                }
            }


        }
//        Card(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .fillMaxHeight(0.7f)
//                .fillMaxWidth(0.7f),
//            elevation = CardDefaults.cardElevation(8.dp),
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
//        ) {
//        if (fullScreenOrFlip) {
//            FullScreenCards(
//                modifier = Modifier.fillMaxSize(0.7f),
//                frontContent = {
//                    ReviewCardFront(
//                        state = state.cardFrontState,
//                        onAudioClick = { audioClick(it) })
//                },
//                backContent = {
//                    ReviewCardBack(
//                        state = state.cardBackState,
//                        onAudioClick = { audioClick(it) },
//                    )
//                },
//                rotated = rotated,
//                onClick = { onClick() })
//        } else {


            FlipCard(modifier = Modifier.fillMaxSize(0.7f),
                frontContent = {
                    ReviewCardFront(
                        state = state.cardFrontState,
                        onAudioClick = { audioClick(it) })
                },
                backContent = {
                    if (fullScreen){
                        Dialog(
                            properties = DialogProperties(usePlatformDefaultWidth = false),
                            onDismissRequest = { fullScreen = !fullScreen}) {
                            Surface(modifier = Modifier.fillMaxSize()) {

                                    ReviewCardBack(
                                        state = state.cardBackState,
                                        onAudioClick = { audioClick(it) },
                                        clickFullScreen = { fullScreen = !fullScreen },
                                        fullScreen = fullScreen

                                    )

                            }
                        }
                    }else{
                        ReviewCardBack(
                            state = state.cardBackState,
                            onAudioClick = { audioClick(it) },
                            clickFullScreen = { fullScreen = !fullScreen },
                            fullScreen = fullScreen
                        )
                    }
                },
                rotated = rotated,
                onClick = { onClick() }
            )
        }
 //   }
    //}

}


@Preview
@Composable
fun CardStackPreview() {
    SmartTranslatorTheme {

        val routated by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {

            CardStack(
                rotated = false,
                onClick = {},
                modifier = Modifier,
                cardListSize = 5,
                audioClick = {},
                fullScreenOrFlip = false,
                state = ReviewCardState(
                    cardBackState = CardWithLanguage(
                        id = UUID.randomUUID(),
                        original = "من",
                        active = true,
                        createDate = "",
                        image = "",
                        updateDate = "",
                        originalLanguage = Languages.Farsi,
                        descriptionModel = Pair(
                            CardDetails(
                                id = UUID.randomUUID(),
                                description = DescriptionModel(
                                    id = UUID.randomUUID(),
                                    meanings = listOf(
                                        DescriptionMeanings(
                                            id = UUID.randomUUID(),
                                            partOfSpeech = "pronoun",
                                            definitions = listOf(
                                                DescriptionDefinition(
                                                    id = UUID.randomUUID(),
                                                    example = "Can you hear me?",
                                                    definition = "As the direct object of a verb.",
                                                    synonyms = emptyList(),
                                                    antonyms = emptyList()
                                                )
                                            )
                                        )
                                    ),
                                    phonetic = "/miː/",
                                    licence = "CC BY-SA 3.0",
                                    audio = "https://api.dictionaryapi.dev/media/pronunciations/en/me-1-au.mp3"
                                ),
                                correctAnswerCount = 3,
                                sentence = "",
                                translated = "me"
                            ), Languages.English
                        )
                    ),
                    cardFrontState = CardFrontState(
                        originalLanguages = Languages.English,
                        original = "hello",
                        example = "hello to you sir!",
                        audio = "",
                        pronunciation = "hello",

                        ),
                )


            )

        }
    }
}
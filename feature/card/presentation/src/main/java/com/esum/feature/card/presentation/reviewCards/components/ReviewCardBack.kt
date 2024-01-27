package com.esum.feature.card.presentation.reviewCards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.feature.card.domain.local.model.CardDetails
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.remote.description.model.DescriptionDefinition
import com.esum.feature.card.domain.remote.description.model.DescriptionMeanings
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.component.WordDescriptionItem
import java.util.UUID

@Composable
fun ReviewCardBack(
    modifier: Modifier = Modifier,
    state: CardWithLanguage,
    onAudioClick: (String) -> Unit
) {


    val image: Int = when (state.descriptionModel?.second) {
        Languages.English -> R.drawable.united_kingdom
        Languages.Farsi -> R.drawable.iran
        Languages.Italian -> R.drawable.italy
        Languages.French -> R.drawable.france
        Languages.Arabic -> R.drawable.saudi_arabia
        Languages.Japans -> R.drawable.japan
        Languages.Germany -> R.drawable.germany
        Languages.Turkish -> R.drawable.turkey
        Languages.Spanish -> R.drawable.spain
        else -> {
            R.drawable.united_kingdom
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp , horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.translate_text),
                style = MaterialTheme.typography.labelSmall.copy(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.outline
                ),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.descriptionModel?.first?.translated ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                )
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "flag",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        CorrectAnswerCounter(count = state.descriptionModel?.first?.correctAnswerCount ?: 0)
        WordDescriptionItem(
            value = DescriptionModel(
                id = state.id,
                audio = state.descriptionModel?.first?.description?.audio,
                licence = state.descriptionModel?.first?.description?.licence ?: "",
                phonetic = state.descriptionModel?.first?.description?.phonetic ?: "",
                meanings = state.descriptionModel?.first?.description?.meanings
            ),
            onPlaySoundClick = { onAudioClick(it) }, onSearchClick = {})
    }
}

@Preview
@Composable
fun ReviewCardBackPreview() {

    SmartTranslatorTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
            ) {

                ReviewCardBack(
                    state = CardWithLanguage(
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
                    ), onAudioClick = {}
                )

            }
        }
    }
}
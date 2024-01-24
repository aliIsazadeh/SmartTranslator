package com.esum.feature.card.presentation.reviewCards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.reviewCards.state.CardFrontState

@Composable
fun ReviewCardFront(
    modifier: Modifier = Modifier,
    state: CardFrontState,
    onAudioClick: (String) -> Unit
) {

    val image: Int = when (state.originalLanguages) {
        Languages.English -> R.drawable.united_kingdom
        Languages.Farsi -> R.drawable.iran
        Languages.Italian -> R.drawable.italy
        Languages.French -> R.drawable.france
        Languages.Arabic -> R.drawable.saudi_arabia
        Languages.Japans -> R.drawable.japan
        Languages.Germany -> R.drawable.germany
        Languages.Turkish -> R.drawable.turkey
        Languages.Spanish -> R.drawable.spain
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(vertical = 24.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.original_text),
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
                    text = state.original,
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
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.phonetic),
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
                    text = state.pronunciation ?: "",
                    style = MaterialTheme.typography.bodyMedium
                        .copy(
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary
                        ),
                )
                IconButton(onClick = { onAudioClick(state.audio ?: "") }) {
                    Icon(
                        imageVector = Icons.Filled.VolumeUp,
                        contentDescription = "flag",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        state.example?.let {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.example),
                    style = MaterialTheme.typography.labelSmall.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.outline
                    ),
                )
                Text(
                    text = it, style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewCardFrontPreview() {

    SmartTranslatorTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
            ) {

                ReviewCardFront(
                    state = CardFrontState(
                        originalLanguages = Languages.English,
                        original = "hello",
                        example = "hello to you sir!",
                        audio = "",
                        pronunciation = "hello",
                        click = {}
                    ), onAudioClick = {}
                )

            }
        }
    }

}

package com.esum.feature.card.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import com.esum.feature.card.presentation.R

@Composable
fun WordDescriptionItem(value: DescriptionModel, onPlaySoundClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.phonetic),
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = value.phonetic,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                )
            }
            IconButton(onClick = { onPlaySoundClick(value.audio) }) {
                Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "play_voice")
            }
        }
        value.meanings.forEach { meaning ->

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = meaning.partOfSpeech,
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
            )

            meaning.definitions.forEach { definition ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.description),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = definition.definition,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                if (definition.synonyms.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.synonyms),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                        )
                        definition.synonyms.forEach {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = it ?: "",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                            )
                        }

                    }
                }
                if (definition.antonyms.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.antonyms),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                        )
                        definition.antonyms.forEach {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = it ?: "",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                            )
                        }

                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.example),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = definition.example,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.small
                        )
                )


            }


        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value.licence,
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
        )
    }
}
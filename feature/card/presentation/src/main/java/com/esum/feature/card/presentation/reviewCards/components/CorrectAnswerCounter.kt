package com.esum.feature.card.presentation.reviewCards.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.esum.feature.card.presentation.R

@Composable
fun CorrectAnswerCounter(modifier: Modifier = Modifier, count: Int) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 3) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 4) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.face_duck),
            contentDescription = "duckFace",
            tint = if (count > 5) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        )

    }

}
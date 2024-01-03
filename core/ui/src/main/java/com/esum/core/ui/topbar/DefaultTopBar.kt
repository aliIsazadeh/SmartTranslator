package com.esum.core.ui.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTopBar(
    modifier: Modifier = Modifier,
    leftComposable: @Composable() () -> Unit,
    rightComposable: @Composable() () -> Unit,
    title: String
) {

    Box(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) { leftComposable() }

        Text(
            modifier = Modifier.align(Alignment.Center) ,
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
        )

        Box(modifier = Modifier.align(Alignment.CenterEnd)) { rightComposable() }


    }

}
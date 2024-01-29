package com.esum.feature.card.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun FullScreenCards(
    frontContent: @Composable () -> Unit,
    backContent: @Composable () -> Unit,
    onClick: () -> Unit,
    rotated: Boolean = true,
    modifier: Modifier = Modifier

) {

//    var rotated by remember { mutableStateOf(true) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )


    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
//            .graphicsLayer {
//                rotationY = rotation
//                cameraDistance = 8 * density
//            }
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    )
    {
        Row(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnimatedVisibility(visible = rotated) {
                CardContent(frontContent)
            }
            AnimatedVisibility(visible = !rotated) {
                Dialog(
                    properties = DialogProperties(usePlatformDefaultWidth = false),
                    onDismissRequest = { onClick() }) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            IconButton(
                                onClick = { onClick() },
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "arrow back"
                                )
                            }
                            CardContent(backContent)

                        }
                    }
                }
            }
        }
    }
}


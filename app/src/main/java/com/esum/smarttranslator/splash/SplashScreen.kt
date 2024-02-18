package com.esum.smarttranslator.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.RepeatableSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.common.constraints.CardFeature
import com.esum.smarttranslator.R
import kotlinx.coroutines.delay
import java.time.Duration


@Composable
fun SplashScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) { paddingValues ->
        paddingValues


        // val horizontalBias by rememberTransition(label = "" , transitionState = TransitionState())
//            .animateFloat(
//            initialValue = -1f,
//            targetValue = 0.5f,
//            animationSpec =  RepeatableSpec(iterations = 1, animation = Duration.ofMillis(3000)), label = ""
        //   )



        var animate by remember {
            mutableStateOf(false)
        }
        val animation by animateFloatAsState(
            targetValue = if (animate) 0.05f else -1f,
            animationSpec = tween(3000, easing = LinearEasing), label = "",
            finishedListener = {
                navController.navigate(CardFeature.nestedRoute)

            }
        )
        LaunchedEffect(animate) {
            animate = true
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                        )
                    ),
                ), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash))
            LottieAnimation(
                composition = composition,
                modifier = Modifier.padding(48.dp),
                iterations = 1,
                restartOnPlay = false,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = animation.dp)
            ) {
                Text(
                    text = stringResource(R.string.duck_learn),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 48.sp,
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.onPrimary,
                            offset = Offset(5f, 5f)
                        ),
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.error,
                            )
                        ),
                    ),
                    modifier = Modifier.align(alignment = BiasAlignment(animation, 0f))
                )
            }


        }


    }

}
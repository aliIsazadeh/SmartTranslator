package com.esum.smarttranslator.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.HomeFeature
import com.esum.smarttranslator.R
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderScreen(navController: NavController) {


    val pagerSate = rememberPagerState(pageCount = { 4 }, initialPage = 0)

    val firstPageColors =
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primaryContainer)
    val secondPageColors =
        listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.outline)
    val thirdPageColors =
        listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondaryContainer)
    val fourthPageColors =
        listOf(MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.errorContainer)


    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(modifier = Modifier.weight(1f),contentAlignment = Alignment.CenterStart) {
                    Button(
                        onClick = { navigateToCardHome(navController) },
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Skip", style = MaterialTheme.typography.titleSmall)
                    }
                }

                RevealDotIndicator2(count = 4 , pagerState = pagerSate  , modifier = Modifier.weight(1f))
                Box(modifier = Modifier.weight(1f) , contentAlignment = Alignment.CenterEnd) {
                    Button(
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        onClick = {
                            if (pagerSate.currentPage == 3) navigateToCardHome(navController) else scope.launch {
                                pagerSate.animateScrollToPage(
                                    pagerSate.currentPage + 1
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = if (pagerSate.currentPage == 3) "Lets Go" else "Next",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }

            }
        },
        modifier = Modifier
            .fillMaxSize()

    ) { paddingValues ->
        paddingValues

        HorizontalPager(
            state = pagerSate, modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = if (pagerSate.currentPage == 0) {
                            firstPageColors
                        } else if (pagerSate.currentPage == 1) {
                            secondPageColors
                        } else if (pagerSate.currentPage == 2) {
                            thirdPageColors
                        } else {
                            fourthPageColors
                        },
                        tileMode = TileMode.Clamp
                    )
                )
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            if (it == 0) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            com.esum.feature.card.presentation.R.raw.hello
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally),
                        iterations = 2,
                        restartOnPlay = false,
                    )
                    Text(
                        text = stringResource(id = R.string.wellcome),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                offset = Offset(5f, 5f)
                            )
                        )
                    )
                }
            } else if (it == 1) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            com.esum.feature.card.presentation.R.raw.send_message
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally),
                        iterations = 2,
                        restartOnPlay = false,
                    )
                    Text(
                        text = stringResource(id = R.string.you_can_add_cards),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.surface,
                                offset = Offset(5f, 5f)
                            )
                        )
                    )
                }
            } else if (it == 2) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            com.esum.feature.translator.presentation.R.raw.translating
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally),
                        iterations = 2,
                        restartOnPlay = false,
                    )
                    Text(
                        text = stringResource(id = R.string.you_can_translate_image),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onSecondary,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                offset = Offset(5f, 5f)
                            )
                        )
                    )
                }
            } else if (it == 3) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            com.esum.feature.card.presentation.R.raw.bad_boy
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally),
                        iterations = 2,
                        restartOnPlay = false,
                    )
                    Text(
                        text = stringResource(id = R.string.have_fun),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onError,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.errorContainer,
                                offset = Offset(5f, 5f)
                            ), textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WormIndicator(
    count: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    spacing: Dp = 10.dp,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            modifier = modifier
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(count) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                )
            }
        }

        Box(
            Modifier
                .jumpingDotTransition(pagerState, 0.8f)
                .size(20.dp)
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.jumpingDotTransition(pagerState: PagerState, jumpScale: Float) =
    graphicsLayer {
        val pageOffset = pagerState.currentPageOffsetFraction
        val scrollPosition = pagerState.currentPage + pageOffset
        translationX = scrollPosition * (size.width + 8.dp.roundToPx()) // 8.dp - spacing between dots

        val scale: Float
        val targetScale = jumpScale - 1f

        scale = if (pageOffset.absoluteValue < .5) {
            1.0f + (pageOffset.absoluteValue * 2) * targetScale;
        } else {
            jumpScale + ((1 - (pageOffset.absoluteValue * 2)) * targetScale);
        }

        scaleX = scale
        scaleY = scale
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwapDotIndicators(
    modifier: Modifier,
    count: Int,
    pagerState: PagerState,
) {
    val circleSpacing = 4.dp
    val circleSize = 12.dp

    Box(
        modifier = modifier
            .height(48.dp), contentAlignment = Alignment.Center
    ) {
        val width = (circleSize + circleSpacing) * count

        Canvas(modifier = Modifier.width(width = width)) {
            val spacing = circleSpacing.toPx()
            val dotWidth = width.toPx()
            val dotHeight = 5f

            val activeDotWidth = 8f
            var x = 0f
            val y = center.y

            repeat(count) { i ->
                val posOffset = pagerState.pageOffset
                val dotOffset = posOffset % 1
                val current = posOffset.toInt()

                val factor = (dotOffset * (activeDotWidth - dotWidth))

                val calculatedWidth = when {
                    i == current -> activeDotWidth - factor
                    i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                    else -> dotWidth
                }

                drawIndicator(x, y, calculatedWidth, dotHeight, CornerRadius(20f , 20f))
                x += calculatedWidth + spacing
            }
        }
    }
}

// To get scroll offset
@OptIn(ExperimentalFoundationApi::class)
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffsetFraction


// To get scrolled offset from snap position
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) =
    graphicsLayer {
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        translationX = scrollPosition * distance
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RevealDotIndicator2(
    count: Int,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    val circleSpacing = 8.dp
    val circleSize = 12.dp
    val innerCircle = 14.dp

    Box(
        modifier = modifier
            .height(48.dp), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier , ) {
            val distance = (circleSize + circleSpacing).toPx()

            val centerX = size.width / 2
            val centerY = size.height / 2

            val totalWidth = distance * count
            val startX = centerX - (totalWidth / 2) + (circleSize / 2).toPx()

            repeat(count) {
                val pageOffset = pagerState.calculateCurrentOffsetForPage(it)

                val alpha = 0.8f.coerceAtLeast(1 - pageOffset.absoluteValue)
                val scale = 1f.coerceAtMost(pageOffset.absoluteValue)

                val x = startX + (it * distance)
                val circleCenter = Offset(x, centerY)
                val radius = circleSize.toPx() / 2
                val innerRadius = (innerCircle.toPx() * scale) / 2

                drawCircle(
                    color = Color.White, center = circleCenter,
                    radius = radius, alpha = alpha,
                )

                drawCircle(color = Color(0xFFD6D6D6), center = circleCenter, radius = innerRadius)
            }
        }
    }
}

private fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius
) {
    val rect = RoundRect(
        x +width/2,
        y - height ,
        x + width/2,
        y + height / 2,
        radius
    )

    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = Color.White)
}

fun navigateToCardHome(navController: NavController) {
    navController.navigate(CardFeature.nestedRoute) {
        popUpTo(HomeFeature.sliderScreen) {
            inclusive = true
        }
    }

}
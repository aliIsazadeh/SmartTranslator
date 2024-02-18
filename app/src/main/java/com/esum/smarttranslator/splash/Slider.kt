package com.esum.smarttranslator.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.HomeFeature
import com.esum.smarttranslator.R
import kotlinx.coroutines.launch

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

                Button(
                    onClick = { navigateToCardHome(navController) },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Skip", style = MaterialTheme.typography.titleSmall)
                }
                Button(
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
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
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                    )
                }
            } else if (it == 1) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
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
                        text = stringResource(id = R.string.wellcome),
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            } else if (it == 2) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
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
                        text = stringResource(id = R.string.wellcome),
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSecondary)
                    )
                }
            } else if (it == 3) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
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
                        text = stringResource(id = R.string.wellcome),
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onError)
                    )
                }
            }
        }
    }


}

fun navigateToCardHome(navController: NavController) {
    navController.navigate(CardFeature.nestedRoute) {
        popUpTo(HomeFeature.sliderScreen) {
            inclusive = true
        }
    }

}
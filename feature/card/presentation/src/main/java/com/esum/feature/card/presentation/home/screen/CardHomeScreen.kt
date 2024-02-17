package com.esum.feature.card.presentation.home.screen

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.TranslateFeature
import com.esum.core.ui.component.LineBar
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.core.web_socket.WebSocketListener
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.home.viewmodel.CardHomeContract
import com.esum.feature.card.presentation.home.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CardHomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    windowSizeClass: WindowSizeClass,
) {


    viewModel.messageState.collectAsStateWithLifecycle()

    viewModel.activeCardsState.collectAsState()
    val (state, effect, event) = use(viewModel = viewModel)
    CardHomeScreen(
        state = state, event = event, effect = effect,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHomeScreen(
    state: CardHomeContract.State,
    event: (CardHomeContract.Event) -> Unit,
    effect: Flow<CardHomeContract.Effect>,
    navController: NavController,

    ) {

    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {

            DefaultTopBar(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentHeight(),
                leftComposable = { /*TODO*/ },
                rightComposable = {


                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.hello))
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.size(36.dp),
                        iterations = 3,
                        restartOnPlay = false,)


                },
                title = stringResource(id = R.string.duckLearn)
            )


        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(0.2f)
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "avatar",
                                modifier = Modifier
                                    .background(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.background
                                    )
                                    .size(40.dp)
                                    .align(Alignment.Center)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(0.7f)
                                .fillMaxWidth()
                        ) {
                            LineBar(
                                title = stringResource(id = R.string.completedCards),
                                mainColor = Color(0xFF478848),
                                subColor = Color(0xFFA6B4A6),
                                percentage = state.completedCards.percentage,
                                count = state.completedCards.count
                            )
                            LineBar(
                                title = stringResource(id = R.string.activeCards),
                                mainColor = MaterialTheme.colorScheme.secondary,
                                subColor = MaterialTheme.colorScheme.secondaryContainer,
                                percentage = state.activeCards.percentage,
                                count = state.activeCards.count
                            )
                            LineBar(
                                title = stringResource(id = R.string.needToLearn),
                                mainColor = Color(0xFF923923),
                                subColor = Color(0xFFACA3A3),
                                percentage = state.needToLearnCards.percentage,
                                count = state.needToLearnCards.count
                            )
                        }
                    }

                    LineBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(id = R.string.allCards),
                        mainColor = MaterialTheme.colorScheme.primary,
                        subColor = MaterialTheme.colorScheme.primaryContainer,
                        percentage = state.allCards.percentage,
                        count = state.allCards.count
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .weight(0.5f),
                        onClick = {
                            navController.navigate(CardFeature.addCardScreenRoute)
                        },
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.add_duck),
                                contentDescription = "Review icon",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.add_card),
                                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                            )
                        }

                    }
                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .weight(0.5f),
                        onClick = {
                                  navController.navigate(TranslateFeature.nestedRoute)
                        },
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.duck_photo),
                                contentDescription = "Review icon",
                                modifier = Modifier.size(36.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.translate_image),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }


                    }

                }
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(CardFeature.reviewCardsScreenRoute)
                    },
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.duck_review),
                            contentDescription = "Review icon",
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.reviewCards),
                            style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun CardHomeScreenPreview() {

    SmartTranslatorTheme {
        CardHomeScreen(
            effect = flowOf(), event = {}, state = CardHomeContract.State(),
            navController = rememberNavController()
        )
    }

}
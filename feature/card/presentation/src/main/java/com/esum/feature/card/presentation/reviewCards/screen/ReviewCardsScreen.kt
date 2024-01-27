package com.esum.feature.card.presentation.reviewCards.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esum.core.ui.use
import com.esum.feature.card.presentation.reviewCards.components.CardStack
import com.esum.feature.card.presentation.reviewCards.state.ReviewCardState
import com.esum.feature.card.presentation.reviewCards.viewmodel.ReviewCardsContract
import com.esum.feature.card.presentation.reviewCards.viewmodel.ReviewCardsViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun ReviewCardsScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    viewModel: ReviewCardsViewModel = hiltViewModel()
) {

    val (state, effect, event) = use(viewModel)
    ReviewCardsScreen(state, effect, event)
    val cards = viewModel.cardReviews.collectAsState()
}

@Composable
fun ReviewCardsScreen(
    state: ReviewCardsContract.State,
    effect: Flow<ReviewCardsContract.Effect>,
    event: (ReviewCardsContract.Event) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            Row(modifier = Modifier.padding(8.dp)) {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = { event.invoke(ReviewCardsContract.Event.OnKnowClick) }) {
                    Text(text = "Know")
                }
            }
        }
    ) { paddingValues ->
        paddingValues


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            state.cardState?.let {
                CardStack(
                    state = state.cardState,
                    cardListSize = state.listSize
                )
            }
                ?: Box(
                    modifier = Modifier
                )
        }
    }
}
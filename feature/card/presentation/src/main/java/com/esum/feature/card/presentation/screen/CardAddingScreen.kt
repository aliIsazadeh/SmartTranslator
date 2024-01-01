package com.esum.feature.card.presentation.screen


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esum.core.ui.CollectInLaunchedEffect
import com.esum.core.ui.use
import com.esum.feature.card.presentation.viewmodel.AddingCardViewModel
import com.esum.feature.card.presentation.viewmodel.CardAddingContract
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Composable
fun CardAddingScreen(
    windowSize: WindowSizeClass,
    viewModel: AddingCardViewModel = hiltViewModel(),
    navController: NavController
) {
    val (state, effect, event) = use(viewModel = viewModel)
    CardAddingScreen(state = state, effect = effect, event = event)

}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun CardAddingScreen(
    state: CardAddingContract.State,
    effect: Flow<CardAddingContract.Effect>,
    event: (CardAddingContract.Event) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    effect.CollectInLaunchedEffect { effect ->
        when (effect) {
            is CardAddingContract.Effect.ShowSnackBar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(effect.message, "Ok")
                }
            }
        }
    }
    Scaffold(snackbarHost = {
    }) { padding ->
        padding

    }


}

@Preview
@Composable
fun CardAddingScreenPreview() {

    MaterialTheme {

    }

}